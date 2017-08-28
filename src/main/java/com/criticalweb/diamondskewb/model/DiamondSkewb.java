package com.criticalweb.diamondskewb.model;

import com.criticalweb.diamondskewb.model.parts.Corner;
import com.criticalweb.diamondskewb.model.parts.CornerSwap;
import com.criticalweb.diamondskewb.model.parts.Face;

import java.util.*;

import static com.criticalweb.diamondskewb.model.Orientation.*;

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
     * USE - (C0) F / F' -> Front corner clockwise / Front corner anticlockwise
     * USW - (C1) L / L' -> Left corner clockwise / Left corner anticlockwise
     * UNE - (C2) R / R' -> Right corner clockwise / Right corner anticlockwise
	 * UNW - (C3) B / B' -> Back corner clockwise / Back corner anticlockwise
     * <p>
	 * [bottom corners]
	 * DSE - C4 front
	 * DSW - C5 left
	 * DNE - C6 right
	 * DNW - C7 back
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

    SwappableMap<Orientation, Corner> corners = new SwappableMap<>();
	SwappableMap<Orientation, Face> faces = new SwappableMap<>();

	/**
	 * [CW] //// CCW would go the opposite direction in circular orientation loops
	 C0 : C1 > C2 > C4 [circular loop]
		 Up>East
		 Down>West
		 North>Down
		 South>Up
		 East>South
		 West>North

	 	[this is one of those circular orientation loops]
	 	Up>East>South>Up
	 	Down>West>North>Down

	 	CornerSwap
	 		.corners[1,2,4]
	 		.colors[[Up,East,South],[Down,West,North]]

	 C1 : C3 > C0 > C5
		 Up>South
		 Down>North
		 North>East
		 South>West
		 East>Down
		 West>Up

	 	Up>South>West>Up
	 	Down>North>East>Down

	 C2 : C0 > C3 > C6
		 Up>North
		 Down>South
		 North>East
		 South>West
		 East>Up
		 West>Down

	 	Up>North>East>Up
	 	Down>South>West>Down

	 C3 : C2 > C1 > C7
		 Up>West
		 Down>East
		 North>Up
		 South>Down
		 East>South
		 West>North

	 	Up>West>North>Up
	 	Down>East>South>Down

	 */

	private Map<Orientation, CornerSwap> swaps = new HashMap<>();

	/**
	 * This default constructor should initialize a solved Skewb.
	 * <p>
	 * Currently, it returns a skewb rotated once clockwise around C2 (the right corner).
	 */
	public DiamondSkewb() {

		initSwaps();

		// corners
		corners.put(USE, new Corner(UP, Color.YELLOW, SOUTH, Color.ORANGE, EAST, Color.BLUE));
		corners.put(USW, new Corner(UP, Color.RED, SOUTH, Color.GREEN, WEST, Color.WHITE));
		corners.put(UNE, new Corner(UP, Color.YELLOW, NORTH, Color.RED, EAST, Color.BLUE));
		corners.put(UNW, new Corner(UP, Color.YELLOW, NORTH, Color.RED, WEST, Color.GREEN));
		corners.put(DSE, new Corner(DOWN, Color.ORANGE, SOUTH, Color.GREEN, EAST, Color.YELLOW));
		corners.put(DSW, new Corner(DOWN, Color.ORANGE, SOUTH, Color.GREEN, WEST, Color.WHITE));
		corners.put(DNE, new Corner(DOWN, Color.WHITE, NORTH, Color.RED, EAST, Color.BLUE));
		corners.put(DNW, new Corner(DOWN, Color.ORANGE, NORTH, Color.BLUE, WEST, Color.WHITE));

		// faces
		faces.put(UP, new Face(Color.YELLOW));
		faces.put(SOUTH, new Face(Color.GREEN));
		faces.put(EAST, new Face(Color.BLUE));
		faces.put(WEST, new Face(Color.WHITE));
		faces.put(NORTH, new Face(Color.RED));
		faces.put(DOWN, new Face(Color.ORANGE));
	}

	public void rotate(final short position, final Direction direction) {

		// TODO: need to make the swapping behaviour generic, keep repeating it here and in Corner.java

		final CornerSwap focus = swaps.get(position);

		// rotate the focus corner
		corners.get(position).rotate(Direction.CW, focus);

		// move the corners around the focus
		List<Corner> c = corners.swap(direction, focus.getCorners());

		// rotate swapped corners
		for (int i=0; i<c.size(); i++) {
			c.get(i).rotate(direction, focus);
		}

		// move the faces around the focus
		faces.swap(direction, focus.getPrimaryColors());

	}

	private void initSwaps() {
		swaps.put(USE, new CornerSwap(Arrays.asList(USW, UNE, DSW), Arrays.asList(UP, EAST, SOUTH), Arrays.asList(DOWN, WEST, NORTH)));
		swaps.put(USW, new CornerSwap(Arrays.asList(UNW, USE, DSW), Arrays.asList(UP, SOUTH, WEST), Arrays.asList(DOWN, NORTH, EAST)));
		swaps.put(UNE, new CornerSwap(Arrays.asList(USE, UNW, DNE), Arrays.asList(UP, NORTH, EAST), Arrays.asList(DOWN, SOUTH, WEST)));
		swaps.put(UNW, new CornerSwap(Arrays.asList(UNE, USW, DNW), Arrays.asList(UP, WEST, NORTH), Arrays.asList(DOWN, EAST, SOUTH)));
	}


	public String toString() {
		// TODO: implement a method that will return a string representation of the state, for unique representation and cache lookup
		return null;
	}

}