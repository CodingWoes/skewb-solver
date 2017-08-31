package com.criticalweb.skewb.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ReZz on 2017-08-15.
 */
public enum Color {

    BLUE('B', "Blue"), GREEN('G', "Green"), YELLOW('Y', "Yellow"), WHITE('W', "White"), RED('R', "Red"), ORANGE('O', "Orange");

    private char label;
    private String name;

    Color(final char label, final String name) {
        this.label=label;
        this.name=name;
    }

    private static final Map<Character, Color> map;
    static {
        map = new HashMap<>();
        for (Color c : Color.values()) {
            map.put(c.getLabel(), c);
        }
    }

    public char getLabel() { return this.label; }

    public static Color findByLabel(final char label) {
        return map.get(label);
    }

}
