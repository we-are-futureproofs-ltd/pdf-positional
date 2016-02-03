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
public class PdfCharacterTest {
    private PdfCharacter instance;
    private Float conversion1;
    private Float conversion2;
    private TextPosition tPos1;
    private TextPosition tPos2;
    private TextPosition tPos3;
    private TextPosition tPos4;
    
    private String[] nonWhitespace = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
         "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
         "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        
    private String[] whitespace = {"-", "_", "-", ",", ".", " ", "\r", "\n", "\t"};

    private int[] ligaturesCode = {64256, 64257, 64258, 64259, 64260, 64261};
    private String[] ligaturesConv = {"ff", "fi", "fl", "ffi", "ffl", "st"};
    
    public PdfCharacterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tPos1 = createTextPosition("a", 10, 10, 8, 12);
        tPos2 = createTextPosition("b", 20, 10, 8, 12);
        tPos3 = createTextPosition("c", 40, 10, 8, 12);
        tPos4 = createTextPosition("d", 40, 30, 8, 12);
        conversion1 = 1.0f;
        conversion2 = 2.0f;
        instance = new PdfCharacter(tPos1, conversion1);
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
    

    /**
     * Test of getxPos method, of class PdfLocation.
     */
    @Test
    public void testGetxPos() {
        assertEquals(10.0F, instance.getxPos(), 0.0);
        instance.setConversion(conversion2);
        assertEquals(20.0F, instance.getxPos(), 0.0);
    }

    
    /**
     * Test of getyPos method, of class PdfLocation.
     */
    @Test
    public void testGetyPos() {
        assertEquals(10.0F, instance.getyPos(), 0.0);
        instance.setConversion(conversion2);
        assertEquals(20.0F, instance.getyPos(), 0.0);
    }

    /**
     * Test of getWidth method, of class PdfLocation.
     */
    @Test
    public void testGetWidth() {
        assertEquals(8.0f, instance.getWidth(), 0.0);
        instance.setConversion(conversion2);
        assertEquals(16.0f, instance.getWidth(), 0.0);
    }

    /**
     * Test of getHeight method, of class PdfLocation.
     */
    @Test
    public void testGetHeight() {
        assertEquals(12.0f, instance.getHeight(), 0.0);
        instance.setConversion(conversion2);
        assertEquals(24.0f, instance.getHeight(), 0.0);
    }

    /**
     * Test of getPosition method, of class PdfLocation.
     */
    @Test
    public void testGetPosition() {
        assertEquals(tPos1, instance.getPosition());
    }

    /**
     * Test of setPosition method, of class PdfLocation.
     */
    @Test
    public void testSetPosition() {
        assertEquals(tPos1, instance.getPosition());
        
        instance.setPosition(tPos2);
        assertEquals(tPos2, instance.getPosition());
        
        instance.setPosition(tPos3);
        assertEquals(tPos3, instance.getPosition());
    }

    /**
     * Test of getConversion method, of class PdfLocation.
     */
    @Test
    public void testGetConversion() {
        assertEquals(conversion1, instance.getConversion());
    }

    /**
     * Test of setConversion method, of class PdfLocation.
     */
    @Test
    public void testSetConversion() {
        instance.setConversion(conversion1);
        assertEquals(conversion1, instance.getConversion());
        
        instance.setConversion(conversion2);
        assertEquals(conversion2, instance.getConversion());
    }

    /**
     * Test of isSameWord method, of class PdfLocation.
     */
    @Test
    public void testIsSameWord() {
        assertTrue(instance.isSameWord(tPos2));
        assertFalse(instance.isSameWord(tPos3));
        assertFalse(instance.isSameWord(tPos4));
    }

    /**
     * Test of isSameLine method, of class PdfLocation.
     */
    @Test
    public void testIsSameLine() {
        assertTrue(instance.isSameLine(tPos2));
        assertTrue(instance.isSameLine(tPos3));
        assertFalse(instance.isSameLine(tPos4));
    }

    /**
     * Test of isWithinPermittedSpacing method, of class PdfLocation.
     */
    @Test
    public void testIsWithinPermittedSpacing() {
        assertTrue(instance.isWithinPermittedSpacing(tPos2));
        assertFalse(instance.isWithinPermittedSpacing(tPos3));
        assertFalse(instance.isWithinPermittedSpacing(tPos4));
    }

    /**
     * Test of getMaxNextWordXpos method, of class PdfLocation.
     */
    @Test
    public void testGetMaxNextWordXpos() {
        assertEquals(22f, instance.getMaxNextWordXpos(), 0.0);
        instance.setPosition(tPos2);
        assertEquals(32f, instance.getMaxNextWordXpos(), 0.0);
        instance.setPosition(tPos3);
        assertEquals(52f, instance.getMaxNextWordXpos(), 0.0);
        instance.setPosition(tPos4);
        assertEquals(52f, instance.getMaxNextWordXpos(), 0.0);
    }

    /**
     * Test of compareFloat method, of class PdfLocation.
     */
    @Test
    public void testCompareFloat() {
        assertTrue(instance.compareFloat(10f, 10f));
        assertTrue(instance.compareFloat(-10f, -10f));
        assertFalse(instance.compareFloat(10f, 11f));
        
        assertTrue(instance.compareFloat(10.1f, 10.15f));
        assertFalse(instance.compareFloat(10.1f, 10.2f));
    }

    /**
     * Test of isWhiteSpace method, of class PdfCharacter.
     */
    @Test
    public void testIsWhiteSpace() {
        
        for (int i = 0; i < nonWhitespace.length; i++) {
            instance.setPosition(this.createTextPosition(nonWhitespace[i], 0, 0, 0, 0));
            assertFalse(instance.isWhiteSpace());
        }
        
        for (int i = 0; i < whitespace.length; i++) {
            instance.setPosition(this.createTextPosition(whitespace[i], 0, 0, 0, 0));
            assertTrue(instance.isWhiteSpace());
        }
    }

    /**
     * Test of getNormalizedCharacter method, of class PdfCharacter.
     */
    @Test
    public void testGetNormalizedCharacter() {
        for (int i = 0; i < nonWhitespace.length; i++) {
            instance.setPosition(this.createTextPosition(nonWhitespace[i], 0, 0, 0, 0));
            assertEquals(nonWhitespace[i], instance.getNormalizedCharacter());
        }
        
        for (int i = 0; i < ligaturesCode.length; i++) {
            instance.setPosition(this.createTextPosition(Character.toString((char)ligaturesCode[i]), 0, 0, 0, 0));
            assertEquals(ligaturesConv[i], instance.getNormalizedCharacter());
        }
    }
    
}
