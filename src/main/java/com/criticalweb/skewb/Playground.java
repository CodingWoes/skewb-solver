package com.criticalweb.skewb;

import com.criticalweb.skewb.model.Direction;
import com.criticalweb.skewb.model.Orientation;
import com.criticalweb.skewb.model.Skewb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by i840108 on 2017-09-08.
 */
public class Playground {

	private static final Logger LOG = LogManager.getLogger(Playground.class);

	public static void main(String[] args) {
		LOG.info("Entering the playground...");

		final Skewb s = new Skewb();
		LOG.info(s.toString());
		s.rotate(Orientation.UNW, Direction.CW);
		LOG.info(s.toString());
		s.rotate(Orientation.UNW, Direction.CW);
		LOG.info(s.toString());

		/* TODO: what I could do is find, for each possible operation, how the string changes - what goes where
		         for each operation, 15 fixed "bits" move around in a predetermined way
		         by mapping these operations and how it affects the string, I might be able to write a more efficient
		         solver that wouldn't need to recreate a new Skewb instance for every possible state
		*/

	}

}
