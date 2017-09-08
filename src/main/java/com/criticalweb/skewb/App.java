package com.criticalweb.skewb;

import com.criticalweb.skewb.model.Direction;
import com.criticalweb.skewb.model.Orientation;
import com.criticalweb.skewb.model.Skewb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by i840108 on 2017-08-29.
 */
public class App {

	private static final Logger LOG = LogManager.getLogger(App.class);

	public static void main(String[] args) {

		final Skewb skewb = new Skewb();

		LOG.debug(skewb);

		solveSkewb(skewb);

		skewb.rotate(Orientation.USW, Direction.CCW);

		LOG.debug(skewb);

		solveSkewb(skewb);

		final String skewb2State = "U:Y#O#W#Y#O|S:O#G#Y#Y#R|W:B#G#O#B#R|E:W#W#B#G#W|N:B#O#G#B#R|D:G#W#R#Y#R";
		final Skewb skewb2 = new Skewb(skewb2State);

		solveSkewb(skewb2);

	}

	private static void solveSkewb(final Skewb skewb) {

		final String startingState = skewb.toString();

		final SkewbSolver solver = new SkewbSolver(skewb);
		try {
			final List<Operation> solution = solver.solve();

			if (LOG.isInfoEnabled()) {
				LOG.info("Starting state: " + startingState);
			}
			LOG.debug(solution);
			if (LOG.isInfoEnabled()) {
				for (Operation o : solution) {
					LOG.info(o);
				}
			}
 		} catch (IllegalArgumentException e) {
			LOG.debug(e);
		}

	}
}
