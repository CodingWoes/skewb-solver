package com.criticalweb.skewb.model;

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

    public char getLabel() { return this.label; }

}
