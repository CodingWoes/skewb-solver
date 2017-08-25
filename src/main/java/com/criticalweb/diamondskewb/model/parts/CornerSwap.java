package com.criticalweb.diamondskewb.model.parts;

import com.criticalweb.diamondskewb.model.DirectionAwareList;
import com.criticalweb.diamondskewb.model.Orientation;

import java.util.List;

/**
 * Created by i840108 on 2017-08-22.
 */
public class CornerSwap {

	private DirectionAwareList<Integer> corners = new DirectionAwareList<>();
	private DirectionAwareList<Orientation> primaryColors = new DirectionAwareList<>();
	private DirectionAwareList<Orientation> secondaryColors = new DirectionAwareList<>();

	public CornerSwap(final List<Integer> corners, final List<Orientation> primaryColors, final List<Orientation> secondaryColors) {
		setCorners(corners);
		setPrimaryColors(primaryColors);
		setSecondaryColors(secondaryColors);
	}

	public DirectionAwareList<Integer> getCorners() {
		return corners;
	}

	public void setCorners(List<Integer> corners) {
		this.corners.addAll(corners);
	}

	public DirectionAwareList<Orientation> getPrimaryColors() {
		return primaryColors;
	}

	public void setPrimaryColors(List<Orientation> primaryColors) {
		this.primaryColors.addAll(primaryColors);
	}

	public DirectionAwareList<Orientation> getSecondaryColors() {
		return secondaryColors;
	}

	public void setSecondaryColors(List<Orientation> secondaryColors) {
		this.secondaryColors.addAll(secondaryColors);
	}
}
