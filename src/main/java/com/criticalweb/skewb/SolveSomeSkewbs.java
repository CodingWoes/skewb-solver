package com.criticalweb.skewb;

import com.criticalweb.skewb.model.*;
import com.criticalweb.skewb.solver.BruteSkewbSolver;
import com.criticalweb.skewb.solver.SkewbSolver;
import com.criticalweb.skewb.solver.Solution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by i840108 on 2017-08-29.
 */
public class SolveSomeSkewbs {

	private static final Logger LOG = LogManager.getLogger(SolveSomeSkewbs.class);

	public static void main(String[] args) {

		final SkewbSolver brutus = new BruteSkewbSolver();

		final Skewb skewb = new Skewb();

		LOG.debug(skewb);

		solveSkewb(brutus, skewb);

		skewb.rotate(Orientation.USW, Direction.CCW);

		LOG.debug(skewb);

		solveSkewb(brutus, skewb);

		final String skewb2State = "U:W#B#W#W#O|S:R#G#R#Y#G|W:B#G#O#B#O|E:W#R#Y#Y#Y|N:Y#R#G#G#O|D:B#O#B#W#R";
		final Skewb skewb2 = new Skewb(skewb2State);

		solveSkewb(brutus, skewb2);

	}

	private static void solveSkewb(final SkewbSolver solver, final Skewb skewb) {

		final String startingState = skewb.toString();

		try {

			final Solution solution = solver.solve(skewb);

 		} catch (IllegalArgumentException e) {
			LOG.debug(e);
		}

	}
}
