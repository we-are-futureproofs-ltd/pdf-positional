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
    Long key = 1L;
    String value = "value";
    Long[] keys = {1L, 2L, 3L};
    String[] values = {"val1", "val2", "val3"};
    Long[] keys2 = {1L};
    
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
        Mapping instance = new MappingImpl();
        
        assertEquals(null, instance.getValue(key));

        instance.addItem(key, value);
        assertEquals(value, instance.getValue(key));
    }

    /**
     * Test of addItems method, of class Mapping.
     */
    @Test
    public void testAddItems() {
        Mapping instance = new MappingImpl();
        instance.addItems(keys, values);
        
        assertEquals(values[0], instance.getValue(keys[0]));
        assertEquals(values[1], instance.getValue(keys[1]));
        assertEquals(values[2], instance.getValue(keys[2]));
        
        instance = new MappingImpl();
        instance.addItems(keys2, values);
        assertNotEquals(values[0], instance.getValue(keys2[0]));
    }

    /**
     * Test of addItem method, of class Mapping.
     */
    @Test
    public void testAddItem() {
        Mapping instance = new MappingImpl();
        instance.addItem(key, value);
        assertEquals(value, instance.getValue(key));
    }

    /**
     * Test of getRegexString method, of class Mapping.
     */
    @Test
    public void testGetRegexString() {
        Mapping instance = new MappingImpl();
        
        assertEquals("\\u0001-\\u000A", instance.getRegexString(1L, 10L));
        assertEquals("\\u0001", instance.getRegexString(1L, 1L));
        assertEquals("", instance.getRegexString(-1L, -1L));
        assertEquals("", instance.getRegexString(-1L, 1L));
        assertEquals("", instance.getRegexString(1L, -1L));
    }

    /**
     * Test of getRegex method, of class Mapping.
     */
    @Test
    public void testGetRegex() {
        Mapping instance = new MappingImpl();
        instance.addItems(keys, values);

        assertEquals("\\u0001-\\u0003", instance.getRegex());
        instance.addItem(4L, "val4");
        assertEquals("\\u0001-\\u0003", instance.getRegex());
    }

    public class MappingImpl extends Mapping {
    }
    
}
