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
     * (C0) F / F' -> Front corner clockwise / Front corner anticlockwise
     * (C1) L / L' -> Left corner clockwise / Left corner anticlockwise
     * (C2) R / R' -> Right corner clockwise / Right corner anticlockwise
	 * (C3) B / B' -> Back corner clockwise / Back corner anticlockwise
     * <p>
	 * [bottom corners]
	 * C4 front
	 * C5 left
	 * C6 right
	 * C7 back
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
	Map<Orientation, Face> faces = new HashMap<>();

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

	private List<CornerSwap> swaps = new ArrayList<>();

	private int[][] cw = new int[][]{
			{1,2,4},
			{3,0,5},
			{0,3,6},
			{2,1,7},
			{0,6,5},
			{1,4,7},
			{2,7,4},
			{3,5,6}
	};

	/**
	 * This default constructor should initialize a solved Skewb.
	 * <p>
	 * Currently, it returns a skewb rotated once clockwise around C2 (the right corner).
	 */
	public DiamondSkewb() {

		initSwaps();

		// corners
		corners.add(new Corner(UP, Color.YELLOW, SOUTH, Color.ORANGE, EAST, Color.BLUE));
		corners.add(new Corner(UP, Color.RED, SOUTH, Color.GREEN, WEST, Color.WHITE));
		corners.add(new Corner(UP, Color.YELLOW, NORTH, Color.RED, EAST, Color.BLUE));
		corners.add(new Corner(UP, Color.YELLOW, NORTH, Color.RED, WEST, Color.GREEN));
		corners.add(new Corner(DOWN, Color.ORANGE, SOUTH, Color.GREEN, EAST, Color.YELLOW));
		corners.add(new Corner(DOWN, Color.ORANGE, SOUTH, Color.GREEN, WEST, Color.WHITE));
		corners.add(new Corner(DOWN, Color.WHITE, NORTH, Color.RED, EAST, Color.BLUE));
		corners.add(new Corner(DOWN, Color.ORANGE, NORTH, Color.BLUE, WEST, Color.WHITE));

		// faces
		faces.put(UP, new Face(Color.YELLOW));
		faces.put(SOUTH, new Face(Color.GREEN));
		faces.put(EAST, new Face(Color.BLUE));
		faces.put(WEST, new Face(Color.WHITE));
		faces.put(NORTH, new Face(Color.RED));
		faces.put(DOWN, new Face(Color.ORANGE));
	}

	public void rotate(final short position, final Direction direction) {
		if (Direction.CW.equals(direction)) {
			rotateCW(position);
		} else if (Direction.CCW.equals(direction)) {
			rotateCCW(position);
		} else {
			throw(new IllegalArgumentException("Direction must be one of Direction.CW or Direction.CCW"));
		}
	}

	private void rotateCW(final short position) {
		final CornerSwap focus = swaps.get(position);
		final Iterator<Integer> iter = focus.getCorners().iterator();

		// the last element is the first source
		Integer source = focus.getCorners().get(focus.getCorners().size()-1);
		// set the first source corner into the buffer
		Corner cornerBuffer = corners.get(source);

		while (iter.hasNext()) {
			// rotate the corner's colors while we're at it
			cornerBuffer.rotate(Direction.CW, focus);

			Integer target = iter.next();
			// store the corner in the target position in the temp buffer
			Corner temp=corners.get(target);
			// move the source corner from the buffer to the target position
			corners.set(target, cornerBuffer);

			// move the target corner from the temp buffer to the corner buffer (for next iteration)
			cornerBuffer = temp;
		}

	}

	private void rotateCCW(final short position) {

	}

	private void initSwaps() {
		swaps.add(new CornerSwap(Arrays.asList(1,2,4), Arrays.asList(UP, EAST, SOUTH), Arrays.asList(DOWN, WEST, NORTH)));
		swaps.add(new CornerSwap(Arrays.asList(3,0,5), Arrays.asList(UP, SOUTH, WEST), Arrays.asList(DOWN, NORTH, EAST)));
		swaps.add(new CornerSwap(Arrays.asList(0,3,6), Arrays.asList(UP, NORTH, EAST), Arrays.asList(DOWN, SOUTH, WEST)));
		swaps.add(new CornerSwap(Arrays.asList(2,1,7), Arrays.asList(UP, WEST, NORTH), Arrays.asList(DOWN, EAST, SOUTH)));
	}

	public String toString() {
		// implement a method that will return a string representation of the state, for unique representation and cache lookup
		return null;
	}

}