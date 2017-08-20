package com.criticalweb.diamondskewb.model;

/**
 * Created by ReZz on 2017-08-15.
 */
public enum Orientation {

    UP("Up"), DOWN("Down"), LEFT("Left"), RIGHT("Right"), FRONT("Front"), BACK("Back");

    private String name;

    Orientation(final String name) {
        this.name=name;
    }

}
