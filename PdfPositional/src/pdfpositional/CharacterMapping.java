/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

import java.util.HashMap;

/**
 *
 * @author jonny
 */
public class CharacterMapping {
    private static final HashMap<Long, String> map = new HashMap<>();
    private static final CharacterMapping instance = new CharacterMapping();

    private CharacterMapping() {
    }

    public static CharacterMapping getInstance() {
        return instance;
    }

    public static String getValue(Long key) {
        return map.get(key);
    }

    public static void addItems(Long[] keys, String[] values) {
        for (int i = 0; i < keys.length; ++i) {
            addItem(keys[i], values[i]);
        }
    }

    public static void addItem(Long key, String value) {
        map.put(key, value);
    }

}
