package com.criticalweb.diamondskewb.model;

/**
 * Created by ReZz on 2017-08-15.
 */
public enum Orientation {

    UP("Up"), DOWN("Down"), WEST("West"), EAST("East"), NORTH("North"), SOUTH("South"), USE("Up-South-East"), USW("Up-South-West"), UNE("Up-North-East"), UNW("Up-North-West"), DSE("Down-South-East"), DSW("Down-South-West"), DNE("Down-North-East"), DNW("Down-North-West");

    private String name;

    Orientation(final String name) {
        this.name=name;
    }

}
