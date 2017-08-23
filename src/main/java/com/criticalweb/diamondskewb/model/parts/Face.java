package com.criticalweb.diamondskewb.model.parts;

import com.criticalweb.diamondskewb.model.Color;
import com.criticalweb.diamondskewb.model.Direction;

/**
 * Created by i840108 on 2017-08-22.
 */
public class Face extends AbstractRotatablePart {

	private Color color;

	public Face(final Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void rotate(final Direction d, final CornerSwap c) {
		// actually do something here
	}
}
