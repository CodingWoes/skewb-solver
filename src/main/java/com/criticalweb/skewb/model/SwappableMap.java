package com.criticalweb.skewb.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by i840108 on 2017-08-28.
 */
public class SwappableMap<K, V> extends HashMap<K,V> {

	public SwappableMap() {
		super();
	}

	public List<V> swap(final Direction d, final DirectionAwareList<Orientation> list) {

		final List<V> affectedValues = new ArrayList<>();

		final DirectionAwareIterator<K> iter = list.iterator(d);

		V buffer;
		if (Direction.CW.equals(d)) {
			// the last element is the first source
			buffer = this.get(list.get(list.size()-1));
		} else {
			// the first element is the first source
			buffer = this.get(list.get(0));
		}

		while (iter.hasNext()) {
			K target = iter.next();

			affectedValues.add(buffer);

			// store the element in the target position in the temp buffer
			V temp = this.get(target);

			// move the source element from the buffer to the target position
			this.put(target, buffer);

			// move the target element from the temp buffer to the element buffer (for next iteration)
			buffer = temp;
		}

		return affectedValues;

	}

	public String toString() {
		return Arrays.toString(this.entrySet().toArray());
	}

}
