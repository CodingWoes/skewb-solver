package com.criticalweb.diamondskewb.model;

import com.criticalweb.diamondskewb.model.parts.Corner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ReZz on 2017-08-11.
 */
public class DiamondSkewb {

    /**
     * Four different operations. See Skewb Square sketchup file
     * <p>
     * Each operation can be done clockwise or anticlockwise around the selected corner.
     * <p>
     * We'll use only the top corners (C0-C3), as the bottom corners mirror their top counterparts.
     * <p>
     * (C0) F / F' -> Front corner clockwise / Front corner anticlockwise
     * (C3) B / B' -> Back corner clockwise / Back corner anticlockwise
     * (C1) L / L' -> Left corner clockwise / Left corner anticlockwise
     * (C2) R / R' -> Right corner clockwise / Right corner anticlockwise
     * <p>
	 * (F0) Up
	 * (F1) South
	 * (F2) East
	 * (F3) West
	 * (F4) North
	 * (F5) Down
     * 3 operations in the same direction returns the cube to its original state.
     * <p>
     * 2 operations in one direction are the same as doing 1 operation in the opposing direction.
     */

    List<Corner> corners = new ArrayList<>();
	List<Color> faces = new ArrayList<>();

	/**
	 * This default constructor should initialize a solved Skewb.
	 * <p>
	 * Currently, it returns a skewb rotated once clockwise around C2 (the right corner).
	 */
	public DiamondSkewb() {
		// corners
		corners.add(new Corner(Orientation.UP, Color.YELLOW, Orientation.SOUTH, Color.ORANGE, Orientation.EAST, Color.BLUE));
		corners.add(new Corner(Orientation.UP, Color.RED, Orientation.SOUTH, Color.GREEN, Orientation.WEST, Color.WHITE));
		corners.add(new Corner(Orientation.UP, Color.YELLOW, Orientation.NORTH, Color.RED, Orientation.EAST, Color.BLUE));
		corners.add(new Corner(Orientation.UP, Color.YELLOW, Orientation.NORTH, Color.RED, Orientation.WEST, Color.GREEN));
		corners.add(new Corner(Orientation.DOWN, Color.ORANGE, Orientation.SOUTH, Color.GREEN, Orientation.EAST, Color.YELLOW));
		corners.add(new Corner(Orientation.DOWN, Color.ORANGE, Orientation.SOUTH, Color.GREEN, Orientation.WEST, Color.WHITE));
		corners.add(new Corner(Orientation.DOWN, Color.WHITE, Orientation.NORTH, Color.RED, Orientation.EAST, Color.BLUE));
		corners.add(new Corner(Orientation.DOWN, Color.ORANGE, Orientation.NORTH, Color.BLUE, Orientation.WEST, Color.WHITE));

		// faces
		faces.add(Color.YELLOW);
		faces.add(Color.GREEN);
		faces.add(Color.BLUE);
		faces.add(Color.WHITE);
		faces.add(Color.RED);
		faces.add(Color.ORANGE);
	}


    // state stores the color of each piece by face and then clockwise, starting from the center and then the upper-left corner
    // sequence of faces: UP, DOWN, LEFT, RIGHT, BACK, FRONT

    // THIS IS A SAMPLE STATE, THIS SHOULD BE PASSED IN DURING INIT
    private Color[][] state = {
            {Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE}, // UP
            {Color.GREEN, Color.ORANGE, Color.WHITE, Color.RED, Color.WHITE}, // DOWN
            {Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN}, // LEFT
            {Color.WHITE, Color.WHITE, Color.WHITE, Color.ORANGE, Color.RED}, // RIGHT
            {Color.RED, Color.RED, Color.RED, Color.GREEN, Color.YELLOW}, // BACK
            {Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.GREEN}}; // FRONT



}
