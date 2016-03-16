/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStart method, of class PdfWordPosition.
     */
    @Test
    public void testGetStart() {
        System.out.println("getStart");
        PdfWordPosition instance = null;
        PdfCharacter expResult = null;
        PdfCharacter result = instance.getStart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStart method, of class PdfWordPosition.
     */
    @Test
    public void testSetStart() {
        System.out.println("setStart");
        PdfCharacter start = null;
        PdfWordPosition instance = null;
        instance.setStart(start);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnd method, of class PdfWordPosition.
     */
    @Test
    public void testGetEnd() {
        System.out.println("getEnd");
        PdfWordPosition instance = null;
        PdfCharacter expResult = null;
        PdfCharacter result = instance.getEnd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnd method, of class PdfWordPosition.
     */
    @Test
    public void testSetEnd() {
        System.out.println("setEnd");
        PdfCharacter end = null;
        PdfWordPosition instance = null;
        instance.setEnd(end);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class PdfWordPosition.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        PdfWordPosition instance = null;
        float expResult = 0.0F;
        float result = instance.getWidth();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class PdfWordPosition.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        PdfWordPosition instance = null;
        float expResult = 0.0F;
        float result = instance.getHeight();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartX method, of class PdfWordPosition.
     */
    @Test
    public void testGetStartX() {
        System.out.println("getStartX");
        PdfWordPosition instance = null;
        float expResult = 0.0F;
        float result = instance.getStartX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartY method, of class PdfWordPosition.
     */
    @Test
    public void testGetStartY() {
        System.out.println("getStartY");
        PdfWordPosition instance = null;
        float expResult = 0.0F;
        float result = instance.getStartY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
