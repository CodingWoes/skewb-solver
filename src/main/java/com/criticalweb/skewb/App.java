package com.criticalweb.skewb;

import com.criticalweb.skewb.model.*;
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

		final String skewb2State = "U:W#B#W#W#O|S:R#G#R#Y#G|W:B#G#O#B#O|E:W#R#Y#Y#Y|N:Y#R#G#G#O|D:B#O#B#W#R";
		final Skewb skewb2 = new Skewb(skewb2State);

		solveSkewb(skewb2);

	}

	private static void solveSkewb(final Skewb skewb) {

		final String startingState = skewb.toString();

		final SkewbSolver solver = new SkewbSolver(skewb);
		try {
			final Solution solution = solver.solve();

			if (LOG.isInfoEnabled()) {
				LOG.info("###############");
				LOG.info("Solution found in " + solution.getOperations().size() + " steps. Time taken: " + solution.getTimeTaken() + "ms.");
				LOG.info("Starting state: " + startingState);
			}
			LOG.debug(solution);
			if (LOG.isInfoEnabled()) {
				LOG.info("---------------");
				LOG.info("Operations to solve the skewb:");
				for (Operation o : solution.getOperations()) {
					LOG.info(o);
				}
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("###############");
			}
 		} catch (IllegalArgumentException e) {
			LOG.debug(e);
		}

	}
}
