package com.criticalweb.skewb.model;

import com.criticalweb.skewb.model.parts.Corner;
import com.criticalweb.skewb.model.parts.CornerSwap;
import com.criticalweb.skewb.model.parts.Face;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static com.criticalweb.skewb.model.Orientation.*;

/**
 * Created by ReZz on 2017-08-11.
 */
public class Skewb {

	private final SkewbPrinter printer = new SkewbPrinter();

	private SwappableMap<Orientation, Corner> corners = new SwappableMap<>();
	private SwappableMap<Orientation, Face> faces = new SwappableMap<>();

	private Map<Orientation, List> structure = new LinkedHashMap<>();

	private Map<Orientation, CornerSwap> swaps = new HashMap<>();

	private boolean dirty;
	private boolean solved;

	private static final Logger LOG = LogManager.getLogger(Skewb.class);

	/**
	 * This default constructor initializes a solved Skewb.
	 */
	public Skewb() {
		initSkewb();
	}

	public Skewb(final String state) {
		initSkewb();
		printer.parse(state);
	}

	public void rotate(final Orientation position, final Direction direction) {

		final CornerSwap focus = swaps.get(position);
		if (focus == null) {
			throw new IllegalArgumentException("position must be one of : 'UNW', 'UNE', 'USW', 'USE'.");
		}

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

		dirty = true;

	}

	public boolean isSolved() {
		if (!dirty) {
			return solved;
		}

		final long start = System.currentTimeMillis();

//		LOG.debug("Skewb dirty! testing state...");
//		LOG.debug("Current skewb output: " + printer.print());

		solved = true;
		for (Map.Entry<Orientation, List> entry : structure.entrySet()) {
			final Orientation face = entry.getKey();

			final Color color = faces.get(face).getColor();

			// LOG.debug("Testing face: " + face + " | (color " + color + ")");

			for (Orientation c : ((List<Orientation>) entry.getValue())) {
				if (!color.equals(corners.get(c).getColor(face))) {
//					LOG.debug("Skewb not solved! Corner at " + c + " has color " + corners.get(c).getColor(face));
					solved = false;
					break;
				}
			}
			if (!solved) {
				break;
			}
		}

//		LOG.debug("Skewb solved? (" + solved + ") - time taken: " + (System.currentTimeMillis() - start) + "ms");

		dirty = false;
		return solved;
	}

	public String toString() {
		return printer.print();
	}

	private void initSkewb() {

		// init the skewb in a solved state

		// corners
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

		// structure (each face with its corresponding corners)
		structure.put(UP, Arrays.asList(UNW, UNE, USW, USE));
		structure.put(SOUTH, Arrays.asList(USW, USE, DSW, DSE));
		structure.put(WEST, Arrays.asList(UNW, USW, DNW, DSW));
		structure.put(EAST, Arrays.asList(USE, UNE, DSE, DNE));
		structure.put(NORTH, Arrays.asList(UNE, UNW, DNE, DNW));
		structure.put(DOWN, Arrays.asList(DSW, DSE, DNW, DNE));

		// swap sequences
		swaps.put(USE, new CornerSwap(Arrays.asList(USW, UNE, DSE), Arrays.asList(UP, EAST, SOUTH), Arrays.asList(DOWN, WEST, NORTH)));
		swaps.put(USW, new CornerSwap(Arrays.asList(UNW, USE, DSW), Arrays.asList(UP, SOUTH, WEST), Arrays.asList(DOWN, NORTH, EAST)));
		swaps.put(UNE, new CornerSwap(Arrays.asList(USE, UNW, DNE), Arrays.asList(UP, NORTH, EAST), Arrays.asList(DOWN, SOUTH, WEST)));
		swaps.put(UNW, new CornerSwap(Arrays.asList(UNE, USW, DNW), Arrays.asList(UP, WEST, NORTH), Arrays.asList(DOWN, EAST, SOUTH)));

		dirty = false;
		solved = true;

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

		String print() {
			final StringBuilder sb = new StringBuilder();

			for (Map.Entry<Orientation, List> e : structure.entrySet()) {
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

		void parse(final String state) {

			String[] _faces = state.split("\\|");

			// split over each face
			for (String f : _faces) {

				String[] _parts = f.split(":");

				// split face into orientation and colors
				if (_parts.length != 2) {
					throw new IllegalArgumentException("Error parsing input string: " + f);
				}
				String o = _parts[0];
				String c = _parts[1];

				// orientation from string
				Orientation _currentO = Orientation.findByLabel(o);

				// split colors into array of colors
				String[] _colors = c.split("#");

				if (_colors.length != 5) {
					throw new IllegalArgumentException("Error parsing input string: " + c);
				}

				faces.get(_currentO).setColor(Color.findByLabel(_colors[2].charAt(0)));

				List<Orientation> _corners = structure.get(_currentO);

				corners.get(_corners.get(0)).setColor(_currentO, Color.findByLabel(_colors[0].charAt(0)));
				corners.get(_corners.get(1)).setColor(_currentO, Color.findByLabel(_colors[1].charAt(0)));
				corners.get(_corners.get(2)).setColor(_currentO, Color.findByLabel(_colors[3].charAt(0)));
				corners.get(_corners.get(3)).setColor(_currentO, Color.findByLabel(_colors[4].charAt(0)));

			}

			dirty = true;
		}

	}

}