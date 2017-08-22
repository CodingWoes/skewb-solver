package com.criticalweb.diamondskewb.model.parts;

import com.criticalweb.diamondskewb.model.Orientation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i840108 on 2017-08-22.
 */
public class CornerSwap {

	private List<Integer> corners = new ArrayList<>();
	private List<Orientation> primaryColors = new ArrayList<>();
	private List<Orientation> secondaryColors = new ArrayList<>();

	public CornerSwap(final List<Integer> corners, final List<Orientation> primaryColors, final List<Orientation> secondaryColors) {
		setCorners(corners);
		setPrimaryColors(primaryColors);
		setSecondaryColors(secondaryColors);
	}

	public List<Integer> getCorners() {
		return corners;
	}

	public void setCorners(List<Integer> corners) {
		this.corners = corners;
	}

	public List<Orientation> getPrimaryColors() {
		return primaryColors;
	}

	public void setPrimaryColors(List<Orientation> primaryColors) {
		this.primaryColors = primaryColors;
	}

	public List<Orientation> getSecondaryColors() {
		return secondaryColors;
	}

	public void setSecondaryColors(List<Orientation> secondaryColors) {
		this.secondaryColors = secondaryColors;
	}
}
