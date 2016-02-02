/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

import java.io.File;
import org.apache.pdfbox.util.TextPosition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jonny
 */
public class PdfPositionalTest {
    
    public PdfPositionalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class PdfPositional.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PdfPositional.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processTextPosition method, of class PdfPositional.
     */
    @Test
    public void testProcessTextPosition() {
        System.out.println("processTextPosition");
        TextPosition text = null;
        PdfPositional instance = null;
        instance.processTextPosition(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeWord method, of class PdfPositional.
     */
    @Test
    public void testStoreWord() {
        System.out.println("storeWord");
        PdfPositional instance = null;
        instance.storeWord();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputFile method, of class PdfPositional.
     */
    @Test
    public void testGetInputFile() {
        System.out.println("getInputFile");
        PdfPositional instance = null;
        File expResult = null;
        File result = instance.getInputFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInputFile method, of class PdfPositional.
     */
    @Test
    public void testSetInputFile() {
        System.out.println("setInputFile");
        File inputFile = null;
        PdfPositional instance = null;
        instance.setInputFile(inputFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutputFile method, of class PdfPositional.
     */
    @Test
    public void testGetOutputFile() {
        System.out.println("getOutputFile");
        PdfPositional instance = null;
        String expResult = "";
        String result = instance.getOutputFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutputFile method, of class PdfPositional.
     */
    @Test
    public void testSetOutputFile() throws Exception {
        System.out.println("setOutputFile");
        String outputFile = "";
        PdfPositional instance = null;
        instance.setOutputFile(outputFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasOutputFile method, of class PdfPositional.
     */
    @Test
    public void testHasOutputFile() {
        System.out.println("hasOutputFile");
        PdfPositional instance = null;
        boolean expResult = false;
        boolean result = instance.hasOutputFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPageNumber method, of class PdfPositional.
     */
    @Test
    public void testGetPageNumber() {
        System.out.println("getPageNumber");
        PdfPositional instance = null;
        int expResult = 0;
        int result = instance.getPageNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPageNumber method, of class PdfPositional.
     */
    @Test
    public void testSetPageNumber() {
        System.out.println("setPageNumber");
        int pageNumber = 0;
        PdfPositional instance = null;
        instance.setPageNumber(pageNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasPageNumber method, of class PdfPositional.
     */
    @Test
    public void testHasPageNumber() {
        System.out.println("hasPageNumber");
        PdfPositional instance = null;
        boolean expResult = false;
        boolean result = instance.hasPageNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConversion method, of class PdfPositional.
     */
    @Test
    public void testGetConversion() {
        System.out.println("getConversion");
        PdfPositional instance = null;
        Float expResult = null;
        Float result = instance.getConversion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConversion method, of class PdfPositional.
     */
    @Test
    public void testSetConversion() {
        System.out.println("setConversion");
        Float conversion = null;
        PdfPositional instance = null;
        instance.setConversion(conversion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPageData method, of class PdfPositional.
     */
    @Test
    public void testGetPageData() {
        System.out.println("getPageData");
        PdfPositional instance = null;
        JSONArray expResult = null;
        JSONArray result = instance.getPageData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPageData method, of class PdfPositional.
     */
    @Test
    public void testSetPageData() {
        System.out.println("setPageData");
        JSONArray pageData = null;
        PdfPositional instance = null;
        instance.setPageData(pageData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPdfData method, of class PdfPositional.
     */
    @Test
    public void testGetPdfData() {
        System.out.println("getPdfData");
        PdfPositional instance = null;
        JSONObject expResult = null;
        JSONObject result = instance.getPdfData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPdfData method, of class PdfPositional.
     */
    @Test
    public void testSetPdfData() {
        System.out.println("setPdfData");
        JSONObject pdfData = null;
        PdfPositional instance = null;
        instance.setPdfData(pdfData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPageDataToPdfData method, of class PdfPositional.
     */
    @Test
    public void testAddPageDataToPdfData() {
        System.out.println("addPageDataToPdfData");
        PdfPositional instance = null;
        instance.addPageDataToPdfData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepareOutputStream method, of class PdfPositional.
     */
    @Test
    public void testPrepareOutputStream() throws Exception {
        System.out.println("prepareOutputStream");
        PdfPositional instance = null;
        instance.prepareOutputStream();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroyOutputStream method, of class PdfPositional.
     */
    @Test
    public void testDestroyOutputStream() {
        System.out.println("destroyOutputStream");
        PdfPositional instance = null;
        instance.destroyOutputStream();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeJSONToOutputStream method, of class PdfPositional.
     */
    @Test
    public void testWriteJSONToOutputStream() throws Exception {
        System.out.println("writeJSONToOutputStream");
        PdfPositional instance = null;
        instance.writeJSONToOutputStream();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMode method, of class PdfPositional.
     */
    @Test
    public void testGetMode() {
        System.out.println("getMode");
        PdfPositional instance = null;
        String expResult = "";
        String result = instance.getMode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMode method, of class PdfPositional.
     */
    @Test
    public void testSetMode() {
        System.out.println("setMode");
        String mode = "";
        PdfPositional instance = null;
        instance.setMode(mode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
