package pdfpositional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author jonny
 */
abstract class Mapping {
    private final HashMap<Long, String> map = new HashMap<>();
    private final ArrayList<Long> indices = new ArrayList<>();
    private String regex = null;


    /**
     * get map value given key
     * @param key
     * @return 
     */
    public String getValue(Long key) {
        return map.get(key);
    }

    /**
     * add key-value collections to map
     * @param keys
     * @param values 
     */
    public void addItems(Long[] keys, String[] values) {
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
    public void addItem(Long key, String value) {
        map.put(key, value);
        indices.add(key);
    }
    
    /**
     * find regular expression string
     * @param rangeStart
     * @param rangeEnd
     * @return 
     */
    public String getRegexString(Long rangeStart, Long rangeEnd) {
        String regexString = "";
        if (rangeStart != -1 && rangeEnd != -1) {
             if (rangeStart.equals(rangeEnd)) {
                regexString = "\\u" + Long.toHexString(0x10000 | rangeStart).substring(1).toUpperCase();
             } else {
                regexString = "\\u" + Long.toHexString(0x10000 | rangeStart).substring(1).toUpperCase() + 
                    "-\\u" + Long.toHexString(0x10000 | rangeEnd).substring(1).toUpperCase(); 
             }
        }
        
        return regexString;
    }
    
    /**
     * regex getter
     * @return 
     */
    public String getRegex() {
        if (regex != null) {
            return regex;
        }
        
        Collections.sort(indices);
        long rangeStart = -1;
        long rangeEnd = -1;
        regex = "";
        
        for(Long code: indices){
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
