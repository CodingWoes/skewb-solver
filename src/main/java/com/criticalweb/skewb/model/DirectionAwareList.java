package com.criticalweb.skewb.model;

import java.util.ArrayList;

/**
 * Created by i840108 on 2017-08-25.
 */
public class DirectionAwareList<E> extends ArrayList<E> {

	public DirectionAwareIterator iterator(final Direction direction) {
		return new DirectionAwareIterator(this, direction);
	}

}
