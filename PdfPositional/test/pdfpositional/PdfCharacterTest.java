/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getxPos method, of class PdfLocation.
     */
    @Test
    public void testGetxPos() {
        System.out.println("getxPos");
        PdfCharacter instance = null;
        float expResult = 0.0F;
        float result = instance.getxPos();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    /**
     * Test of getyPos method, of class PdfLocation.
     */
    @Test
    public void testGetyPos() {
        System.out.println("getyPos");
        PdfCharacter instance = null;
        float expResult = 0.0F;
        float result = instance.getyPos();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class PdfLocation.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        PdfCharacter instance = null;
        float expResult = 0.0F;
        float result = instance.getWidth();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class PdfLocation.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        PdfCharacter instance = null;
        float expResult = 0.0F;
        float result = instance.getHeight();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosition method, of class PdfLocation.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        PdfCharacter instance = null;
        TextPosition expResult = null;
        TextPosition result = instance.getPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosition method, of class PdfLocation.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        TextPosition position = null;
        PdfCharacter instance = null;
        instance.setPosition(position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConversion method, of class PdfLocation.
     */
    @Test
    public void testGetConversion() {
        System.out.println("getConversion");
        PdfCharacter instance = null;
        Float expResult = null;
        Float result = instance.getConversion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConversion method, of class PdfLocation.
     */
    @Test
    public void testSetConversion() {
        System.out.println("setConversion");
        Float conversion = null;
        PdfCharacter instance = null;
        instance.setConversion(conversion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSameWord method, of class PdfLocation.
     */
    @Test
    public void testIsSameWord() {
        System.out.println("isSameWord");
        TextPosition charPosition = null;
        PdfCharacter instance = null;
        boolean expResult = false;
        boolean result = instance.isSameWord(charPosition);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSameLine method, of class PdfLocation.
     */
    @Test
    public void testIsSameLine() {
        System.out.println("isSameLine");
        TextPosition charPosition = null;
        PdfCharacter instance = null;
        boolean expResult = false;
        boolean result = instance.isSameLine(charPosition);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isWithinPermittedSpacing method, of class PdfLocation.
     */
    @Test
    public void testIsWithinPermittedSpacing() {
        System.out.println("isWithinPermittedSpacing");
        TextPosition charPosition = null;
        PdfCharacter instance = null;
        boolean expResult = false;
        boolean result = instance.isWithinPermittedSpacing(charPosition);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxNextWordXpos method, of class PdfLocation.
     */
    @Test
    public void testGetMaxNextWordXpos() {
        System.out.println("getMaxNextWordXpos");
        PdfCharacter instance = null;
        Float expResult = null;
        Float result = instance.getMaxNextWordXpos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareFloat method, of class PdfLocation.
     */
    @Test
    public void testCompareFloat() {
        System.out.println("compareFloat");
        float val1 = 0.0F;
        float val2 = 0.0F;
        PdfCharacter instance = null;
        boolean expResult = false;
        boolean result = instance.compareFloat(val1, val2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isWhiteSpace method, of class PdfCharacter.
     */
    @Test
    public void testIsWhiteSpace() {
        System.out.println("isWhiteSpace");
        PdfCharacter instance = null;
        boolean expResult = false;
        boolean result = instance.isWhiteSpace();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNormalizedCharacter method, of class PdfCharacter.
     */
    @Test
    public void testGetNormalizedCharacter() {
        System.out.println("getNormalizedCharacter");
        PdfCharacter instance = null;
        String expResult = "";
        String result = instance.getNormalizedCharacter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
