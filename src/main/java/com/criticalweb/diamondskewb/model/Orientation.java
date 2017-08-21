package com.criticalweb.diamondskewb.model;

/**
 * Created by ReZz on 2017-08-15.
 */
public enum Orientation {

    UP("Up"), DOWN("Down"), WEST("West"), EAST("East"), NORTH("North"), SOUTH("South");

    private String name;

    Orientation(final String name) {
        this.name=name;
    }

}
