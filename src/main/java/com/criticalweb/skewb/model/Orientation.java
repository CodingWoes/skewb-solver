package com.criticalweb.skewb.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ReZz on 2017-08-15.
 */
public enum Orientation {

    UP("U", "Up"), DOWN("D", "Down"), WEST("W", "West"), EAST("E", "East"), NORTH("N", "North"), SOUTH("S", "South"), USE("USE", "Up-South-East"), USW("USW", "Up-South-West"), UNE("UNE", "Up-North-East"), UNW("UNW", "Up-North-West"), DSE("DSE", "Down-South-East"), DSW("DSW", "Down-South-West"), DNE("DNE", "Down-North-East"), DNW("DNW", "Down-North-West");

    private String label;
    private String name;

    Orientation(final String label, final String name) {
        this.label=label;
        this.name=name;
    }

    private static final Map<String, Orientation> map;
    static {
        map = new HashMap<>();
        for (Orientation o : Orientation.values()) {
            map.put(o.getLabel(), o);
        }
    }

    public String getLabel() {
        return label;
    }

    public static Orientation findByLabel(final String l) {
        return map.get(l);
    }
}
