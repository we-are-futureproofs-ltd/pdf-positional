/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.TextPosition;
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
public class PdfWordTest {
    TextPosition textPosition;
    private PdfCharacter char1;
    private PdfCharacter char2;
    private PdfWord instance;
    
    public PdfWordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        prepSingletons();
        char1 = this.createPdfCharacter("a");
        char2 = this.createPdfCharacter("b");
        
        instance = new PdfWord(char1);
    }
    
    @After
    public void tearDown() {
    }
    
    public PdfCharacter createPdfCharacter(String character) {
        PDPage page = new PDPage();
        Matrix textPositionSt = new Matrix();
        Matrix textPositionEnd = new Matrix();
        float[] individualWidths = {1.1f};
        float spaceWidth = 4.0f;
        float fontSizeValue = 12f;
        int fontSizeInPt = 10;
        float ws = 4f;
        
        return new PdfCharacter(new TextPosition(page, textPositionSt, textPositionEnd, 
            12f, individualWidths, spaceWidth, character, new PDType0Font(), 
            fontSizeValue, fontSizeInPt, ws), new Float(1));
    }
    
    public void prepSingletons() {
        Long[] sbKeys = {173L, 45L, 8208L};
        String[] sbValues = {"-", "-", "-"};
        MappingSoftBreak.getInstance().addItems(sbKeys, sbValues);
        
        Long[] subKeys = {192L, 198L, 199L};
        String[] subValues = {"A", "AE", "C"};
        MappingSubstitution.getInstance().addItems(subKeys, subValues);
    }

    /**
     * Test of getLocationStart method, of class PdfWord.
     */
    @Test
    public void testGetPositions() {
        ArrayList<PdfWordPosition> posTmp = instance.getPositions();
        assertEquals(posTmp.size(), 1);
        assertEquals(posTmp.get(0).getStart(), char1);
        assertEquals(posTmp.get(0).getEnd(), char1);
    }

    /**
     * Test of getWord method, of class PdfWord.
     */
    @Test
    public void testGetWord() {
        instance.setWord("test word");
        assertEquals(instance.getWord(), "test word");

        instance.setWord("");
        assertEquals(instance.getWord(), "");
    }

    /**
     * Test of setWord method, of class PdfWord.
     */
    @Test
    public void testSetWord() {
        instance.setWord("test word");
        assertEquals(instance.getWord(), "test word");
    }

    /**
     * Test of addCharacter method, of class PdfWord.
     */
    @Test
    public void testAddCharacter() {
        instance.addCharacter(char2);
        ArrayList<PdfWordPosition> posTmp = instance.getPositions();
        
        assertEquals(instance.getWord(), "ab");
        assertEquals(posTmp.size(), 1);
        assertEquals(posTmp.get(0).getStart(), char1);
        assertEquals(posTmp.get(0).getEnd(), char2);
        
    }

    /**
     * Test of toString method, of class PdfWord.
     */
    @Test
    public void testToString() {
        assertEquals(instance.toString(), "a");

        instance.setWord("test word");
        assertEquals(instance.toString(), "test word");

        instance.setWord("");
        assertEquals(instance.toString(), "");

    }

    /**
     * Test of toJson method, of class PdfWord.
     */
    @Test
    public void testToJson() {
        assertTrue(instance.toJson() instanceof JSONObject);
        String expectedJson = "{\"layout\":[{\"width\":0.0,\"x\":0.0,\"y\":792.0,\"height\":12.0}],\"word\":\"a\"}";
        StringWriter out = new StringWriter();
        try {
            instance.toJson().writeJSONString(out);
            assertEquals(out.toString(), expectedJson);
        } catch (IOException ex) {
            fail("JSON data mismatch");
        }

    }
    
    /**
     * Test of toJson method, of class PdfWord.
     */
    @Test
    public void testConstructor() {
        PdfWord instanceTmp = new PdfWord(char1);
        ArrayList<PdfWordPosition> posTmp = instanceTmp.getPositions();
        
        assertEquals(instanceTmp.getWord(), "a");
        assertEquals(posTmp.size(), 1);
        assertEquals(posTmp.get(0).getStart(), char1);
        assertEquals(posTmp.get(0).getEnd(), char1);
    }
    
}
