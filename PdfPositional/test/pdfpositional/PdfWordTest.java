/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLocationStart method, of class PdfWord.
     */
    @Test
    public void testGetLocationStart() {
        System.out.println("getLocationStart");
        PdfWord instance = null;
        PdfCharacter expResult = null;
        PdfCharacter result = instance.getLocationStart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLocationStart method, of class PdfWord.
     */
    @Test
    public void testSetLocationStart() {
        System.out.println("setLocationStart");
        PdfCharacter locationStart = null;
        PdfWord instance = null;
        instance.setLocationStart(locationStart);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLocationEnd method, of class PdfWord.
     */
    @Test
    public void testGetLocationEnd() {
        System.out.println("getLocationEnd");
        PdfWord instance = null;
        PdfCharacter expResult = null;
        PdfCharacter result = instance.getLocationEnd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLocationEnd method, of class PdfWord.
     */
    @Test
    public void testSetLocationEnd() {
        System.out.println("setLocationEnd");
        PdfCharacter locationEnd = null;
        PdfWord instance = null;
        instance.setLocationEnd(locationEnd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWord method, of class PdfWord.
     */
    @Test
    public void testGetWord() {
        System.out.println("getWord");
        PdfWord instance = null;
        String expResult = "";
        String result = instance.getWord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWord method, of class PdfWord.
     */
    @Test
    public void testSetWord() {
        System.out.println("setWord");
        String word = "";
        PdfWord instance = null;
        instance.setWord(word);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCharacter method, of class PdfWord.
     */
    @Test
    public void testAddCharacter() {
        System.out.println("addCharacter");
        String character = "";
        PdfCharacter location = null;
        PdfWord instance = null;
        instance.addCharacter(character, location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class PdfWord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PdfWord instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toJson method, of class PdfWord.
     */
    @Test
    public void testToJson() {
        System.out.println("toJson");
        PdfWord instance = null;
        JSONObject expResult = null;
        JSONObject result = instance.toJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
