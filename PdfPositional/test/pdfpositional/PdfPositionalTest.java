/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.TextPosition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import pdfpositional.exceptions.EncryptedDocumentException;
import pdfpositional.exceptions.ParameterException;
/**
 *
 * @author jonny
 */
public class PdfPositionalTest {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();    
    
    @Rule 
    public ExpectedException thrown = ExpectedException.none();
    
    private PdfPositional instance;
    private File inputFile;
    private PdfWord word;
    private String outputFile;
    
    static String testPath;
    
    public PdfPositionalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        testPath = Paths.get(".").toAbsolutePath().normalize().toString();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        inputFile = new File("file");
        word = createPdfWord("a");
        instance = new PdfPositional(inputFile);
        outputFile = "output.file";
    }
    
    @After
    public void tearDown() {
        if (instance.hasOutputFile()) {
            File f = new File(instance.getOutputFile());
            if (f.exists()) {
                f.delete();
            }
        }
    }
    
    public PdfWord createPdfWord(String character) {
        return new PdfWord(new PdfCharacter(createTextPosition(character), new Float(1)));
    }
    
    public TextPosition createTextPosition(String character) {
        PDPage page = new PDPage();
        Matrix textPositionSt = new Matrix();
        Matrix textPositionEnd = new Matrix();
        float[] individualWidths = {1.1f};
        float spaceWidth = 4.0f;
        float fontSizeValue = 12f;
        int fontSizeInPt = 10;
        float ws = 4f;
        
        return new TextPosition(page, textPositionSt, textPositionEnd, 
            12f, individualWidths, spaceWidth, character, new PDType0Font(), 
            fontSizeValue, fontSizeInPt, ws);
    }
    
    public TextPosition createTextPosition(String character, float xDirAdj, float yDirAdj, float width, float height) {
        PDPage page = new PDPage();
        Matrix textPositionSt = new Matrix();
        Matrix textPositionEnd = new Matrix();
        float[] individualWidths = {};
        float spaceWidth = 4.0f;
        float fontSizeValue = 12f;
        int fontSizeInPt = 10;
        float ws = 4f;
        
        return new TextPosition(page, textPositionSt, textPositionEnd, 
            12f, individualWidths, spaceWidth, character, new PDType0Font(), 
            fontSizeValue, fontSizeInPt, ws){
            @Override
            public float getXDirAdj() {
                    return xDirAdj;
            }
            @Override
            public float getYDirAdj() {
                    return yDirAdj;
            }
            @Override
            public float getWidthDirAdj() {
                    return width;
            }
            @Override
            public float getHeightDir() {
                    return height;
            }
        };
    }
    
    
    @Test
    public void testContructor() {
        assertEquals(inputFile, instance.getInputFile());
        assertEquals(1, instance.getConversion(), 0.0);
        assertEquals(new JSONArray(), instance.getPageData());
        assertEquals(new JSONObject(), instance.getPdfData());
    }

    /**
     * Test of main method, of class PdfPositional.
     */
    @Test
    public void testMainWithNoPDF() {
        exit.expectSystemExitWithStatus(1);
        PdfPositional.main(new String[] {});
        
    }

    @Test
    public void testMainWithInvalidPDF() {
        exit.expectSystemExitWithStatus(1);
        PdfPositional.main(new String[] {"abc.pdf"});
    }

    @Test
    public void testMainWithInvalidReg() {
        exit.expectSystemExitWithStatus(1);
        PdfPositional.main(new String[] {testPath + "/test/pdf"});
    }
    
    @Test
    public void testMainWithInvalidProtected() {
        exit.expectSystemExitWithStatus(1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        PdfPositional.main(new String[] {testPath + "/test/pdf/protected-test123.pdf"});
        assertEquals("Encrypted Document Error", outContent.toString().trim());
        System.setOut(null);
    }

    @Test
    public void testMainWithSuccess() {
        exit.expectSystemExitWithStatus(0);
        PdfPositional.main(new String[] {testPath + "/test/pdf/blank.pdf"});
    }
    
    @Test
    public void testRunWithEncryptedDocumentException() throws IOException, FileNotFoundException, ParameterException, EncryptedDocumentException {
        instance.setInputFile(new File(testPath + "/test/pdf/protected-test123.pdf"));
        thrown.expect(EncryptedDocumentException.class);
        instance.run();
    }

    @Test
    public void testRunWithParameterException() throws IOException, FileNotFoundException, ParameterException, EncryptedDocumentException {
        instance.setInputFile(new File(testPath + "/test/pdf/blank.pdf"));
        instance.setPageNumber(10);
        thrown.expect(ParameterException.class);
        instance.run();
    }

    
    @Test
    public void testProcessParams() throws IOException {
        instance.processParams(new String[] {"--page=100", testPath + "/test/pdf/blank.pdf"});
        assertEquals(100, instance.getPageNumber());
        
        instance.processParams(new String[] {"--output=" + testPath + "/test/pdf/output.json", testPath + "/test/pdf/blank.pdf"});
        assertEquals(testPath + "/test/pdf/output.json", instance.getOutputFile());
        
        instance.processParams(new String[] {"--mode=test", testPath + "/test/pdf/blank.pdf"});
        assertEquals("test", instance.getMode());
    }

    /**
     * Test of processTextPosition method, of class PdfPositional.
     */
    @Test
    public void testProcessTextPosition() {
        // test processing first char
        instance.processTextPosition(createTextPosition("a"));
        
        assertNotNull(instance.lastLocation);
        assertNotNull(instance.currentWord);
        assertEquals("{\"width\":0.0,\"x\":0.0,\"y\":792.0,\"word\":\"a\",\"height\":12.0}", instance.currentWord.toJson().toJSONString());

        // test multi char word acceptance
        instance.processTextPosition(createTextPosition("b"));
        assertNotNull(instance.lastLocation);
        assertNotNull(instance.currentWord);
        assertEquals("{\"width\":0.0,\"x\":0.0,\"y\":792.0,\"word\":\"ab\",\"height\":12.0}", instance.currentWord.toJson().toJSONString());
        
        // test word on next line
        instance.processTextPosition(createTextPosition("c", 0, 100, 0, 0));
        assertNotNull(instance.lastLocation);
        assertNotNull(instance.currentWord);
        assertEquals("{\"width\":0.0,\"x\":0.0,\"y\":100.0,\"word\":\"c\",\"height\":0.0}", instance.currentWord.toJson().toJSONString());
        
        // test whitespace char
        TextPosition text2 = createTextPosition(" ");
        instance.processTextPosition(text2);
        assertNotNull(instance.lastLocation);
        assertNull(instance.currentWord);
        assertEquals("[{\"width\":0.0,\"x\":0.0,\"y\":792.0,\"word\":\"ab\",\"height\":12.0},{\"width\":0.0,\"x\":0.0,\"y\":100.0,\"word\":\"c\",\"height\":0.0}]", instance.getPageData().toJSONString());
    }

    /**
     * Test of storeWord method, of class PdfPositional.
     */
    @Test
    public void testStoreWord() {
        instance.storeWord();
        assertNull(instance.currentWord);
        
        instance.currentWord  = word;
        instance.storeWord();
        JSONArray expected = new JSONArray();
        expected.add(word.toJson());
        assertNull(instance.currentWord);
        assertEquals(expected, instance.getPageData());
    }

    /**
     * Test of getInputFile method, of class PdfPositional.
     */
    @Test
    public void testGetInputFile() {
        assertEquals(inputFile, instance.getInputFile());
    }

    /**
     * Test of setInputFile method, of class PdfPositional.
     */
    @Test
    public void testSetInputFile() {
        File inputFile2 = new File("file2");
        instance.setInputFile(inputFile2);
        assertEquals(inputFile2, instance.getInputFile());
    }

    /**
     * Test of getOutputFile method, of class PdfPositional.
     */
    @Test
    public void testGetOutputFile() throws IOException {
        instance.setOutputFile(outputFile);
        assertEquals(outputFile, instance.getOutputFile());
    }

    /**
     * Test of setOutputFile method, of class PdfPositional.
     */
    @Test
    public void testSetOutputFile() throws Exception {
        instance.setOutputFile(outputFile);
        assertEquals(outputFile, instance.getOutputFile());
        assertNotNull(instance.outputStream);
    }

    /**
     * Test of hasOutputFile method, of class PdfPositional.
     */
    @Test
    public void testHasOutputFile() throws IOException {
        assertFalse(outputFile, instance.hasOutputFile());
        instance.setOutputFile(outputFile);
        assertTrue(outputFile, instance.hasOutputFile());
    }

    /**
     * Test of getPageNumber method, of class PdfPositional.
     */
    @Test
    public void testGetPageNumber() {
        assertEquals(0, instance.getPageNumber());
        instance.setPageNumber(100);
        assertEquals(100, instance.getPageNumber());
    }

    /**
     * Test of setPageNumber method, of class PdfPositional.
     */
    @Test
    public void testSetPageNumber() {
        instance.setPageNumber(999);
        assertEquals(999, instance.getPageNumber());
    }

    /**
     * Test of hasPageNumber method, of class PdfPositional.
     */
    @Test
    public void testHasPageNumber() {
        assertFalse(instance.hasPageNumber());
        instance.setPageNumber(1);
        assertTrue(instance.hasPageNumber());
    }

    /**
     * Test of getConversion method, of class PdfPositional.
     */
    @Test
    public void testGetConversion() {
        assertEquals(1, instance.getConversion(), 0);
        instance.setConversion(12.56f);
        assertEquals(12.56f, instance.getConversion(), 0);
    }

    /**
     * Test of setConversion method, of class PdfPositional.
     */
    @Test
    public void testSetConversion() {
        instance.setConversion(12.56f);
        assertEquals(12.56f, instance.getConversion(), 0);
        
        instance.setConversion(199f);
        assertEquals(199f, instance.getConversion(), 0);
    }

    /**
     * Test of getPageData method, of class PdfPositional.
     */
    @Test
    public void testGetPageData() {
        assertEquals(new JSONArray(), instance.getPageData());
        JSONArray json = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("name", "value");
        json.add(obj);
        
        instance.setPageData(json);
        assertEquals(json, instance.getPageData());
    }

    /**
     * Test of setPageData method, of class PdfPositional.
     */
    @Test
    public void testSetPageData() {
        JSONArray json = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("name1", "value");
        obj.put("name2", 3);
        json.add(obj);
        
        instance.setPageData(json);
        assertEquals(json, instance.getPageData());
        
        instance.setPageData(new JSONArray());
        assertEquals(new JSONArray(), instance.getPageData());
    }

    /**
     * Test of getPdfData method, of class PdfPositional.
     */
    @Test
    public void testGetPdfData() {
        assertEquals(new JSONObject(), instance.getPdfData());
        JSONObject obj = new JSONObject();
        obj.put("name", "value");
        
        instance.setPdfData(obj);
        assertEquals(obj, instance.getPdfData());
    }

    /**
     * Test of setPdfData method, of class PdfPositional.
     */
    @Test
    public void testSetPdfData() {
        JSONObject obj = new JSONObject();
        obj.put("name", "value");
        
        instance.setPdfData(obj);
        assertEquals(obj, instance.getPdfData());
        
        instance.setPdfData(new JSONObject());
        assertEquals(new JSONObject(), instance.getPdfData());
    }

    /**
     * Test of addPageDataToPdfData method, of class PdfPositional.
     */
    @Test
    public void testAddPageDataToPdfData() {
        instance.addPageDataToPdfData();
        assertEquals(new JSONArray(), instance.getPageData());
        assertEquals(new JSONObject(), instance.getPdfData());
        
        JSONObject expected = new JSONObject();
        expected.put(1, new JSONArray());
        
        instance.setPageNumber(1);
        instance.setPageData(new JSONArray());
        instance.addPageDataToPdfData();
        assertEquals(new JSONArray(), instance.getPageData());
        assertEquals(expected, instance.getPdfData());
    }

    /**
     * Test of prepareOutputStream method, of class PdfPositional.
     */
    @Test
    public void testPrepareOutputStream() throws Exception {
        instance.setOutputFile(outputFile);
        instance.prepareOutputStream();
        assertNotNull(instance.outputStream);
    }

    /**
     * Test of destroyOutputStream method, of class PdfPositional.
     */
    @Test
    public void testDestroyOutputStream() throws IOException {
        instance.destroyOutputStream();
        assertNull(instance.outputStream);

        instance.setOutputFile(outputFile);
        instance.prepareOutputStream();
        instance.destroyOutputStream();
        assertNull(instance.outputStream);
    }

    /**
     * Test of writeJSONToOutputStream method, of class PdfPositional.
     */
    @Test
    public void testWriteJSONToOutputStream() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();


        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        
        JSONObject pdfData = new JSONObject();
        pdfData.put("name", "value");
        
        instance.setPdfData(pdfData);
        instance.writeJSONToOutputStream();
        assertEquals(new JSONArray(), instance.getPageData());
        assertEquals(new JSONObject(), instance.getPdfData());
        assertEquals(pdfData.toString(), outContent.toString().trim());
        
        System.setOut(null);
        System.setErr(null);
    }
    
    @Test
    public void testWriteJSONToOutputStreamPrintWriter() throws Exception {
        StringWriter out = new StringWriter();
        JSONObject pdfData = new JSONObject();
        pdfData.put("name", "value");
        instance.setPdfData(pdfData);
        instance.setOutputFile(testPath + "/test/pdf/output.json");
        String expected = instance.getPdfData().toJSONString();

        ByteArrayOutputStream bOut = new ByteArrayOutputStream(); 
        instance.outputStream = new PrintWriter(bOut); 
        instance.writeJSONToOutputStream();
        instance.outputStream.flush(); 
        instance.outputStream.close(); 
        
        assertEquals(expected, bOut.toString().trim());
    }

    /**
     * Test of getMode method, of class PdfPositional.
     */
    @Test
    public void testGetMode() {
        assertEquals("memory", instance.getMode());
        
        instance.setMode("test_mode");
        assertEquals("test_mode", instance.getMode());
    }

    /**
     * Test of setMode method, of class PdfPositional.
     */
    @Test
    public void testSetMode() {
        instance.setMode("123");
        assertEquals("123", instance.getMode());
    }
    
}
