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

		LOG.error("Testing an error!");
		LOG.info("Testing info...");

		final Skewb skewb = new Skewb();

		LOG.debug(skewb);

		skewb.rotate(Orientation.USW, Direction.CCW);

		LOG.debug(skewb);

		final SkewbSolver solver = new SkewbSolver(skewb);

		solver.solve();

//		final String skewb2State = "U:Y#O#W#Y#O|S:O#G#Y#Y#R|W:B#G#O#B#R|E:W#W#B#G#W|N:B#O#G#B#R|D:G#W#R#Y#R";
//		final Skewb skewb2 = new Skewb(skewb2State);
//
//		LOG.debug(skewb2);
//
//		final SkewbSolver solver2 = new SkewbSolver(skewb2);
//
//		List<Operation> operations = solver2.solve();
//		LOG.debug("Starting state: " + skewb2State);
//		LOG.debug(operations);
//		for (Operation o : operations) {
//			LOG.debug(o);
//		}

	}
}
