package com.criticalweb.skewb.model;

import com.criticalweb.skewb.model.Direction;
import com.criticalweb.skewb.model.Orientation;

/**
 * Created by i840108 on 2017-09-05.
 */
public class Operation {

	private Orientation axis;
	private Direction direction;

	public Operation(final Orientation o, final Direction d) {
		this.axis = o;
		this.direction = d;
	}

	public Orientation getAxis() {
		return axis;
	}

	public Direction getDirection() {
		return direction;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("axis: " + axis + " - direction: " + direction);
		return sb.toString();
	}

}
