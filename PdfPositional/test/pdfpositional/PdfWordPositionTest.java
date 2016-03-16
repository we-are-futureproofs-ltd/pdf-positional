/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.TextPosition;
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
public class PdfWordPositionTest {
    PdfCharacter char1;
    PdfCharacter char2;
    
    public PdfWordPositionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        char1 = createPdfCharacter("a", 0, 100, 10, 10);
        char2 = createPdfCharacter("b", 10, 100, 10, 10);
    }
    
    @After
    public void tearDown() {
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
    
    public PdfCharacter createPdfCharacter(String character, float xDirAdj, float yDirAdj, float width, float height) {
        return new PdfCharacter(createTextPosition(character, xDirAdj, yDirAdj, width, height), new Float(1));
    }

    /**
     * Test of getStart method, of class PdfWordPosition.
     */
    @Test
    public void testGetStart() {
        PdfWordPosition instance = new PdfWordPosition(char1, char1);
        assertEquals(char1, instance.getStart());
    }

    /**
     * Test of setStart method, of class PdfWordPosition.
     */
    @Test
    public void testSetStart() {
        PdfWordPosition instance = new PdfWordPosition(char1, char1);
        instance.setStart(char2);
        assertEquals(char2, instance.getStart());
    }

    /**
     * Test of getEnd method, of class PdfWordPosition.
     */
    @Test
    public void testGetEnd() {
        PdfWordPosition instance = new PdfWordPosition(char1, char1);
        assertEquals(char1, instance.getEnd());
    }

    /**
     * Test of setEnd method, of class PdfWordPosition.
     */
    @Test
    public void testSetEnd() {
        PdfWordPosition instance = new PdfWordPosition(char1, char1);
        instance.setEnd(char2);
        assertEquals(char2, instance.getEnd());
    }

    /**
     * Test of getWidth method, of class PdfWordPosition.
     */
    @Test
    public void testGetWidth() {
        PdfWordPosition instance = new PdfWordPosition(char1, char1);
        assertEquals(10.0F, instance.getWidth(), 0.0);
        
        instance = new PdfWordPosition(char1, char2);
        assertEquals(20.0F, instance.getWidth(), 0.0);
    }

    /**
     * Test of getHeight method, of class PdfWordPosition.
     */
    @Test
    public void testGetHeight() {
        PdfWordPosition instance = new PdfWordPosition(char1, char1);
        assertEquals(10.0F, instance.getHeight(), 0.0);
    }

    /**
     * Test of getStartX method, of class PdfWordPosition.
     */
    @Test
    public void testGetStartX() {
        PdfWordPosition instance = new PdfWordPosition(char1, char1);
        assertEquals(0.0F, instance.getStartX(), 0.0);
    }

    /**
     * Test of getStartY method, of class PdfWordPosition.
     */
    @Test
    public void testGetStartY() {
        PdfWordPosition instance = new PdfWordPosition(char1, char1);
        assertEquals(100.0F, instance.getStartY(), 0.0);
    }
    
}
