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
public class MappingTest {
    
    public MappingTest() {
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
     * Test of getValue method, of class Mapping.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Long key = null;
        Mapping instance = new MappingImpl();
        String expResult = "";
        String result = instance.getValue(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addItems method, of class Mapping.
     */
    @Test
    public void testAddItems() {
        System.out.println("addItems");
        Long[] keys = null;
        String[] values = null;
        Mapping instance = new MappingImpl();
        instance.addItems(keys, values);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addItem method, of class Mapping.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        Long key = null;
        String value = "";
        Mapping instance = new MappingImpl();
        instance.addItem(key, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegexString method, of class Mapping.
     */
    @Test
    public void testGetRegexString() {
        System.out.println("getRegexString");
        Long rangeStart = null;
        Long rangeEnd = null;
        Mapping instance = new MappingImpl();
        String expResult = "";
        String result = instance.getRegexString(rangeStart, rangeEnd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegex method, of class Mapping.
     */
    @Test
    public void testGetRegex() {
        System.out.println("getRegex");
        Mapping instance = new MappingImpl();
        String expResult = "";
        String result = instance.getRegex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class MappingImpl extends Mapping {
    }
    
}
