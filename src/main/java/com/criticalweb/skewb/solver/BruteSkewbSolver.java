package com.criticalweb.skewb.solver;

import com.criticalweb.skewb.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by i840108 on 2017-08-31.
 */
public class BruteSkewbSolver implements SkewbSolver {

	private static final Logger LOG = LogManager.getLogger(BruteSkewbSolver.class);

	private final List<Orientation> axes = new ArrayList<>();
	private final List<Direction> directions = new ArrayList<>();

	private Map<String, List> cache;

	private Queue<String> queue;

	public BruteSkewbSolver() {

		axes.addAll(Arrays.asList(Orientation.UNW, Orientation.UNE, Orientation.USW, Orientation.USE));
		directions.addAll(Arrays.asList(Direction.CW, Direction.CCW));
	}

	public Solution solve(final Skewb skewb) {

		// reset cache and queue
		cache = new HashMap<>();
		queue = new LinkedList<>();

		final String originalState = skewb.toString();

		if (skewb.isSolved()) {
			LOG.warn("Skewb already solved!");
			throw new IllegalArgumentException("Skewb already solved.");
		}

		if (LOG.isTraceEnabled()) {
			LOG.trace("Beginning solve of skewb: " + skewb);
		}

		long start = System.currentTimeMillis();

		boolean solved = false;

		int counter = 0;

		// store the initial state in the cache, along with an empty op-list
		cache.put(originalState, new ArrayList<Operation>());

		// add the initial state to the queue
		queue.add(originalState);

		while (!solved) {

			String result = processQueue();

			if (result != null) {
				solved = true;
				long timeTaken = System.currentTimeMillis() - start;
				if (LOG.isTraceEnabled()) {
					LOG.trace("Found " + cache.size() + " different states.");
					LOG.trace("Process complete. Time taken: " + timeTaken + "ms");
				}
				final Solution solution = new Solution();
				solution.setStartingSkewb(skewb);
				solution.setOperations(cache.get(result));
				solution.setTimeTaken(timeTaken);
				logSolution(solution);
				return solution;
			}

			counter++;
			if (LOG.isTraceEnabled()) {
				LOG.trace("Finished pass " + counter + " (time so far: " + (System.currentTimeMillis() - start) + "ms)");
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

		if (LOG.isTraceEnabled()) {
			LOG.trace("Elements in queue: " + queue.size());
		}

		final Queue<String> newQueue = new LinkedList<>();

		while (!queue.isEmpty()) {
			String state = queue.remove();

			for (Orientation o : axes) {
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

	private void logSolution(final Solution solution) {
		final String startingState = solution.getStartingSkewb().toString();

		if (LOG.isDebugEnabled()) {
			LOG.debug("###############");
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("Solution found in " + solution.getOperations().size() + " steps. Time taken: " + solution.getTimeTaken() + "ms.");
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Starting state: " + startingState);
		}
		LOG.trace(solution.getOperations());
		if (LOG.isDebugEnabled()) {
			LOG.debug("---------------");
			LOG.debug("Operations to solve the skewb:");
			for (Operation o : solution.getOperations()) {
				LOG.debug(o);
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("###############");
		}
	}

}
