/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;

import java.io.StringWriter;
import org.apache.pdfbox.exceptions.CryptographyException;

import pdfpositional.exceptions.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;
import org.apache.pdfbox.io.RandomAccessFile;


/**
 *
 * @author jonny
 */
public class PdfPositional extends PDFTextStripper {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // check file param
            if (args.length == 0) {
                throw new ParameterException("No file parameter specified");
            }
            
            String file = args[args.length - 1];
            Pattern patternFile = Pattern.compile("(?i)^[\\w,\\s-()/]+\\.pdf$");
            Matcher matcherFile = patternFile.matcher(file);
        
            // check file is valid format
            if (!matcherFile.find()) {
                throw new ParameterException("File parameter invalid: " + file);
            }

            // check if file exists
            File input = new File(file);
            if(!input.exists()) {
                throw new ParameterException("File does not exist: " + file);
            }

            PdfPositional pdfPositional = new PdfPositional(input);
            pdfPositional.setConversion(new Float(1.388888888889));

            pdfPositional.processParams(args);

            pdfPositional.run();
            
            System.exit(0);
        } catch (ParameterException ex) {
            System.out.println("Parameter Error: " + ex.getMessage());
            System.exit(1);
        } catch (EncryptedDocumentException ex) {
            System.out.println("Encrypted Document Error");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("IO Error" + ex.getMessage());
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormat Error: " + ex.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * run extraction process
     * @throws FileNotFoundException
     * @throws IOException
     * @throws EncryptedDocumentException
     * @throws ParameterException 
     */
    protected void run () throws FileNotFoundException, IOException, EncryptedDocumentException, ParameterException {
        PDDocument pdfDocument;
        RandomAccessFile scratchFile = null;

        if (this.getMode().equals("scratch")) {
            File tmp = new File("temp.tmp");
            tmp.deleteOnExit();
            scratchFile = new RandomAccessFile(tmp, "rw");
            pdfDocument = PDDocument.loadNonSeq(this.getInputFile(), scratchFile);
        } else {
            pdfDocument = PDDocument.load(this.getInputFile());
        }

        // check for encrypted document
        if (pdfDocument.isEncrypted()) {
            try {
                pdfDocument.decrypt("");
            } catch (CryptographyException | IOException e) {
                pdfDocument.close();
                throw new EncryptedDocumentException();
            }
        }

        List allPages = pdfDocument.getDocumentCatalog().getAllPages();
        if (this.hasPageNumber()) { 
            if (pdfDocument.getNumberOfPages() < this.getPageNumber()) {
                throw new ParameterException("illegal page number");
            }
            PDPage page = (PDPage) allPages.get(this.getPageNumber() - 1);
            PDStream contents = page.getContents();
            if (contents != null) {
                this.processStream(page, page.findResources(), page.getContents().getStream());
                this.addPageDataToPdfData();
                this.writeJSONToOutputStream();
            }
        } else {
            for (int i = 0; i < allPages.size(); i++) {
                System.out.println(i);
                this.setPageNumber(i + 1);
                PDPage page = (PDPage) allPages.get(i);
                try {
                    PDStream contents = page.getContents();

                    if (contents != null) {
                        this.processStream(page, page.findResources(), page.getContents().getStream());
                        this.addPageDataToPdfData();
                        this.writeJSONToOutputStream();
                    }

                } catch (Exception ex) {
                   System.out.println("caught you: " + ex.getMessage()); 
                } finally {
                    page.clear();
                }
            }
        }

        this.destroyOutputStream();
        pdfDocument.close();
        if (scratchFile != null) {
            scratchFile.close();
        }
    }
    
    /**
     * process arguments
     * @param args
     * @throws IOException 
     */
    protected void processParams (String[] args) throws IOException {
        Pattern patternArgument = Pattern.compile("^-{2}([^=]+)[=]([\\s\\S]+)$");
        Matcher matcher;
        for (int i = 0; i < args.length - 1; i++) {
            matcher = patternArgument.matcher(args[i]);
            while (matcher.find()) {
                switch (matcher.group(1)) {
                    case "page":
                        this.setPageNumber(Integer.parseInt(matcher.group(2)));
                        break;
                    case "output":
                        this.setOutputFile(matcher.group(2));
                        break;
                    case "mode":
                        this.setMode(matcher.group(2).toLowerCase());
                        break;
                }
            }
        }
    }
    
    public PdfWord currentWord;
    public PdfCharacter lastLocation;
    
    @Override
    protected void processTextPosition(TextPosition text) {
        boolean lineMatch = (lastLocation == null) ? false : lastLocation.isSameWord(text);

        lastLocation = new PdfCharacter(text, this.getConversion());

        // if char is not punctuation or whitespace
        if (!lastLocation.isWhiteSpace()) {
            if ((currentWord != null) && (lineMatch == true)) {
                currentWord.addCharacter(lastLocation);
            } else if (currentWord == null) {
                currentWord = new PdfWord(lastLocation);
            } else  {
                this.storeWord();
                currentWord = new PdfWord(lastLocation);
            }
        } else {
            this.storeWord();
        }
    }

    /**
     * store word
     */
    protected void storeWord () {
        if (currentWord != null){
            this.getPageData().add(currentWord.toJson());
        } 
        currentWord = null;
    }
    
    private String mode = "memory";

    /**
     * Get the value of mode
     *
     * @return the value of mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the value of mode
     *
     * @param mode new value of mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    
    private File inputFile;

    /**
     * Get the value of input File
     * 
     * @return File
     */
    public File getInputFile() {
        return inputFile;
    }

    /**
     * Set the value of input
     * 
     * @param inputFile 
     */
    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    
    private String outputFile;

    /**
     * Get the value of outputFile
     *
     * @return the value of outputFile
     */
    public final String getOutputFile() {
        return outputFile;
    }

    /**
     * Set the value of outputFile
     * 
     * @param outputFile
     * @throws IOException 
     */
    public final void setOutputFile(String outputFile) throws IOException {
        this.outputFile = outputFile;
        this.prepareOutputStream();
    }
    
    /**
     * outputFile checker
     * 
     * @return boolean
     */
    public final boolean hasOutputFile() {
        return (this.getOutputFile() != null && this.getOutputFile().length() > 0);
    }
    
    private int pageNumber;

    /**
     * Get the value of pageNumber
     *
     * @return the value of pageNumber
     */
    public final int getPageNumber() {
        return pageNumber;
    }

    /**
     * Set the value of pageNumber
     *
     * @param pageNumber new value of pageNumber
     */
    public final void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    /**
     * Check to ensure that we have the page number
     * 
     * @return boolean
     */
    public final boolean hasPageNumber() {
        return this.getPageNumber() > 0;
    }
    
    private Float conversion;

    /**
     * Get the value of conversion
     *
     * @return the value of conversion
     */
    public Float getConversion() {
        return conversion;
    }

    /**
     * Set the value of conversion
     *
     * @param conversion new value of conversion
     */
    public void setConversion(Float conversion) {
        this.conversion = conversion;
    }

    private JSONArray pageData;

    /**
     * Get the value of pageData
     *
     * @return the value of pageData
     */
    public JSONArray getPageData() {
        return pageData;
    }

    /**
     * Set the value of pageData
     *
     * @param pageData new value of pageData
     */
    public void setPageData(JSONArray pageData) {
        this.pageData = pageData;
    }

    private JSONObject pdfData;

    /**
     * Get the value of pdfData
     *
     * @return the value of pdfData
     */
    public JSONObject getPdfData() {
        return pdfData;
    }

    /**
     * Set the value of pdfData
     *
     * @param pdfData new value of pdfData
     */
    public void setPdfData(JSONObject pdfData) {
        this.pdfData = pdfData;
    }

    /**
     * add page data to PDFData structure
     */
    public void addPageDataToPdfData() {
        if (this.hasPageNumber()) {
            this.getPdfData().put(this.getPageNumber(), this.getPageData());
            this.setPageData(new JSONArray());
        }
    }
    
    /**
     * constructor
     * 
     * @param file
     * @throws IOException 
     */
    public PdfPositional(File file) throws IOException {
        // set file param
        this.inputFile = file;
        
        // set default conversion value
        this.conversion = new Float(1);
        
        // initialize page and pdf data
        this.pageData = new JSONArray();
        this.pdfData = new JSONObject();
        
        super.setSortByPosition(true);
    }
    
    PrintWriter outputStream;
    
    /**
     * setup output stream
     * @throws IOException 
     */
    public void prepareOutputStream () throws IOException {
        File saveFile;
        saveFile = new File(this.getOutputFile());
        if (saveFile.exists()) {
            saveFile.delete();
        }

        outputStream = new PrintWriter(new BufferedWriter(new FileWriter(this.getOutputFile(), true)));
    }
    
    /**
     * ensure that output stream is closed
     */
    public void destroyOutputStream () {
        if (outputStream != null) {
            outputStream.close();
            outputStream = null;
        }
    }
    
    /**
     * write JSON data to output stream
     * @throws IOException 
     */
    public void writeJSONToOutputStream () throws IOException {
        StringWriter out = new StringWriter();
        this.getPdfData().writeJSONString(out);
        if (!this.hasOutputFile() || (this.outputStream == null)) {
            System.out.println(out.toString());
        } else {
            this.outputStream.println(out.toString());
            this.outputStream.flush(); 
        }
        
        // initialize page and pdf data
        this.pageData = new JSONArray();
        this.pdfData = new JSONObject();
    }
}
