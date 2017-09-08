package com.criticalweb.skewb.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by i840108 on 2017-08-31.
 */
public class SkewbSolver {

	private Skewb skewb;

	private static final Logger LOG = LogManager.getLogger(SkewbSolver.class);

	private final List<Orientation> axes = new ArrayList<>();
	private final List<Direction> directions = new ArrayList<>();

	private Map<String, List> cache = new HashMap<>();

	private Queue<String> queue = new LinkedList<>();

	private String originalState;

	private boolean solved;

	public SkewbSolver(final Skewb skewb) {
		this.skewb = skewb;
		originalState = skewb.toString();

		axes.addAll(Arrays.asList(Orientation.UNW, Orientation.UNE, Orientation.USW, Orientation.USE));
		directions.addAll(Arrays.asList(Direction.CW, Direction.CCW));
	}

	public Solution solve() {
		if (skewb.isSolved()) {
			LOG.warn("Skewb already solved!");
			throw new IllegalArgumentException("Skewb already solved.");
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("Beginning solve of skewb: " + skewb);
		}

		long start = System.currentTimeMillis();

		solved = false;

		int counter=0;

		// store the initial state in the cache, along with an empty op-list
		cache.put(originalState, new ArrayList<Operation>());

		// add the initial state to the queue
		queue.add(originalState);

		while (!solved) {

			String result = processQueue();

			if (result != null) {
				solved = true;
				long timeTaken = System.currentTimeMillis() - start;
				if (LOG.isDebugEnabled()) {
					LOG.debug("Found " + cache.size() + " different states.");
					LOG.debug("Process complete. Time taken: " + timeTaken + "ms");
				}
				final Solution solution = new Solution();
				solution.setOperations(cache.get(result));
				solution.setTimeTaken(timeTaken);
				return solution;
			}

			counter++;
			if (LOG.isDebugEnabled()) {
				LOG.debug("Finished pass " + counter + " (time so far: " + (System.currentTimeMillis() - start) + "ms)");
			}

			// TODO: this should be removed eventually
			if (counter >= 100) {
				LOG.error("Fail safe reached, exiting solver.");
				break;
			}

		}

		return null;

	}

	private String processQueue() {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Elements in queue: " + queue.size());
		}

		final Queue<String> newQueue = new LinkedList<>();

		while (!queue.isEmpty()) {
			String state = queue.remove();

			for (Orientation o : axes ) {
				for (Direction d : directions) {
					Skewb s = new Skewb(state);
					s.rotate(o, d);

					String newState = s.toString();

					if (cache.containsKey(newState)) {
						continue;
					}

					List<Operation> operations = new ArrayList<>();
					operations.addAll(cache.get(state));
					operations.add(new Operation(o, d));

					cache.put(newState, operations);

					if (s.isSolved()) {
						return newState;
					}

					newQueue.add(newState);

				}
			}

		}

		queue.addAll(newQueue);

		return null;
	}

}
