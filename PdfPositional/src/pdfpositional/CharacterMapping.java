package pdfpositional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author jonny
 */
public class CharacterMapping {
    private static final HashMap<Long, String> map = new HashMap<>();
    private static final CharacterMapping instance = new CharacterMapping();
    private static final ArrayList<Long> indexes = new ArrayList<Long>();
    private static String regex = null;

    /**
     * constructor
     */
    private CharacterMapping() {
    }

    /**
     * singleton pattern getInstance
     * @return 
     */
    public static CharacterMapping getInstance() {
        return instance;
    }

    /**
     * get map value given key
     * @param key
     * @return 
     */
    public static String getValue(Long key) {
        return map.get(key);
    }

    /**
     * add key-value collections to map
     * @param keys
     * @param values 
     */
    public static void addItems(Long[] keys, String[] values) {
        if (keys.length != values.length) {
            return;
        }
        
        for (int i = 0; i < keys.length; ++i) {
            addItem(keys[i], values[i]);
        }
    }

    /**
     * add item to map
     * @param key
     * @param value 
     */
    public static void addItem(Long key, String value) {
        map.put(key, value);
        indexes.add(key);
    }
    
    /**
     * find regular expression string
     * @param rangeStart
     * @param rangeEnd
     * @return 
     */
    public static String getRegexString(Long rangeStart, Long rangeEnd) {
        return (rangeStart == -1 || rangeEnd == -1) ? "" : 
                "\\u" + Long.toHexString(0x10000 | rangeStart).substring(1).toUpperCase() + 
                "-\\u" + Long.toHexString(0x10000 | rangeEnd).substring(1).toUpperCase();
    }
    
    /**
     * regex getter
     * @return 
     */
    public static String getRegex() {
        if (regex != null) {
            return regex;
        }
        
        Collections.sort(indexes);
        long rangeStart = -1;
        long rangeEnd = -1;
        regex = "";
        
        for(Long code: indexes){
            if ((rangeStart == -1) || (code != (rangeEnd + 1))) {
                regex += getRegexString(rangeStart, rangeEnd);
                rangeStart = code;
                rangeEnd = code;
                continue;
            } 
            
            rangeEnd = code;
        }
        
        regex += getRegexString(rangeStart, rangeEnd);
        
        return regex;
        
    }

}
