package com.criticalweb.skewb.model;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by i840108 on 2017-08-25.
 */
public class DirectionAwareIterator<E> implements Iterator<E> {

	private Direction direction;
	private ListIterator<E> itr;

	DirectionAwareIterator(final DirectionAwareList<E> list, final Direction direction) {
		this.direction = direction;
		this.itr = list.listIterator(Direction.CCW.equals(direction) ? list.size() : 0);
	}

	@Override
	public boolean hasNext() {
		if (Direction.CW.equals(direction)) {
			return this.itr.hasNext();
		} else if (Direction.CCW.equals(direction)) {
			return this.itr.hasPrevious();
		}
		return false;
	}

	@Override
	public E next() {
		if (Direction.CW.equals(direction)) {
			return this.itr.next();
		} else if (Direction.CCW.equals(direction)) {
			return this.itr.previous();
		}
		return null;
	}
}
