package com.criticalweb.skewb.model;

import com.criticalweb.skewb.model.parts.Corner;
import com.criticalweb.skewb.model.parts.CornerSwap;
import com.criticalweb.skewb.model.parts.Face;

import java.util.*;

import static com.criticalweb.skewb.model.Orientation.*;

/**
 * Created by ReZz on 2017-08-11.
 */
public class Skewb {

	private final SkewbPrinter printer = new SkewbPrinter();

    SwappableMap<Orientation, Corner> corners = new SwappableMap<>();
	SwappableMap<Orientation, Face> faces = new SwappableMap<>();

	private Map<Orientation, CornerSwap> swaps = new HashMap<>();

	/**
	 * This default constructor should initialize a solved Skewb.
	 * <p>
	 * Currently, it returns a skewb rotated once clockwise around C2 (the right corner).
	 */
	public Skewb() {

		initSwaps();

		corners.put(USE, new Corner(UP, Color.YELLOW, SOUTH, Color.ORANGE, EAST, Color.BLUE));
		corners.put(USW, new Corner(UP, Color.YELLOW, SOUTH, Color.ORANGE, WEST, Color.GREEN));
		corners.put(UNE, new Corner(UP, Color.YELLOW, NORTH, Color.RED, EAST, Color.BLUE));
		corners.put(UNW, new Corner(UP, Color.YELLOW, NORTH, Color.RED, WEST, Color.GREEN));
		corners.put(DSE, new Corner(DOWN, Color.WHITE, SOUTH, Color.ORANGE, EAST, Color.BLUE));
		corners.put(DSW, new Corner(DOWN, Color.WHITE, SOUTH, Color.ORANGE, WEST, Color.GREEN));
		corners.put(DNE, new Corner(DOWN, Color.WHITE, NORTH, Color.RED, EAST, Color.BLUE));
		corners.put(DNW, new Corner(DOWN, Color.WHITE, NORTH, Color.RED, WEST, Color.GREEN));

		// faces
		faces.put(UP, new Face(Color.YELLOW));
		faces.put(SOUTH, new Face(Color.ORANGE));
		faces.put(EAST, new Face(Color.BLUE));
		faces.put(WEST, new Face(Color.GREEN));
		faces.put(NORTH, new Face(Color.RED));
		faces.put(DOWN, new Face(Color.WHITE));

		/*
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
		 */
	}

	public void rotate(final Orientation position, final Direction direction) {

		final CornerSwap focus = swaps.get(position);

		// rotate the focus corner
		corners.get(position).rotate(direction, focus);

		// move the corners around the focus
		List<Corner> c = corners.swap(direction, focus.getCorners());

		// rotate swapped corners
		for (Corner cc : c) {
			cc.rotate(direction, focus);
		}

		// move the faces around the focus
		faces.swap(direction, focus.getPrimaryColors());

	}

	private void initSwaps() {
		swaps.put(USE, new CornerSwap(Arrays.asList(USW, UNE, DSE), Arrays.asList(UP, EAST, SOUTH), Arrays.asList(DOWN, WEST, NORTH)));
		swaps.put(USW, new CornerSwap(Arrays.asList(UNW, USE, DSW), Arrays.asList(UP, SOUTH, WEST), Arrays.asList(DOWN, NORTH, EAST)));
		swaps.put(UNE, new CornerSwap(Arrays.asList(USE, UNW, DNE), Arrays.asList(UP, NORTH, EAST), Arrays.asList(DOWN, SOUTH, WEST)));
		swaps.put(UNW, new CornerSwap(Arrays.asList(UNE, USW, DNW), Arrays.asList(UP, WEST, NORTH), Arrays.asList(DOWN, EAST, SOUTH)));
	}

	public String toString() {
		return printer.print();
	}

	/**
	 * Returns a string representing the cube in this format:
	 * <p>
	 * U:#Y#Y#Y#Y#Y|S:#O#O#O#O#O|W:#G#G#G#G#G|E:#B#B#B#B#B|N:#R#R#R#R#R|D:#W#W#W#W#W
	 * <p>
	 * Where each face is represented in turn, pipe-separated, preempted by the face's orientation's label.
	 * <p>
	 * The colors on the face are represented by their label, hash-separated, in the following order (assuming the face
	 * is looked at directly, with the edge facing up being either NORTH, UP or, in the case of the DOWN face, SOUTH):
	 * <p>
	 * Upper-left corner # Upper-right corner # Center piece # Lower-left corner # Lower-right corner
	 *
	 * @return
	 */
	private class SkewbPrinter {

		final Map<Orientation, List> outputOrder = new LinkedHashMap<>();

		public SkewbPrinter() {
			outputOrder.put(UP, Arrays.asList(UNW, UNE, USW, USE));
			outputOrder.put(SOUTH, Arrays.asList(USW, USE, DSW, DSE));
			outputOrder.put(WEST, Arrays.asList(UNW, USW, DNW, DSW));
			outputOrder.put(EAST, Arrays.asList(USE, UNE, DSE, DNE));
			outputOrder.put(NORTH, Arrays.asList(UNE, UNW, DNE, DNW));
			outputOrder.put(DOWN, Arrays.asList(DSW, DSE, DNW, DNE));
		}

		public String print() {
			final StringBuilder sb = new StringBuilder();

			for (Map.Entry<Orientation, List> e : outputOrder.entrySet()) {
				final Orientation k = e.getKey();
				final List<Orientation> v = e.getValue();
				if (sb.length() != 0) {
					sb.append("|");
				}
				sb.append(k.getLabel()).append(":");
				sb.append(corners.get(v.get(0)).getColor(k).getLabel());
				sb.append("#");
				sb.append(corners.get(v.get(1)).getColor(k).getLabel());
				sb.append("#");
				sb.append(faces.get(k).getColor().getLabel());
				sb.append("#");
				sb.append(corners.get(v.get(2)).getColor(k).getLabel());
				sb.append("#");
				sb.append(corners.get(v.get(3)).getColor(k).getLabel());
			}

			return sb.toString();
		}

	}

}