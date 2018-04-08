package com.criticalweb.skewb.model.parts;

import com.criticalweb.skewb.model.DirectionAwareList;
import com.criticalweb.skewb.model.Orientation;

import java.util.List;

/**
 * Created by i840108 on 2017-08-22.
 */
public class CornerSwap {

	private DirectionAwareList<Orientation> corners = new DirectionAwareList<>();
	private DirectionAwareList<Orientation> primaryColors = new DirectionAwareList<>();
	private DirectionAwareList<Orientation> secondaryColors = new DirectionAwareList<>();

	public CornerSwap(final List<Orientation> corners, final List<Orientation> primaryColors, final List<Orientation> secondaryColors) {
		setCorners(corners);
		setPrimaryColors(primaryColors);
		setSecondaryColors(secondaryColors);
	}

	public DirectionAwareList<Orientation> getCorners() {
		return corners;
	}

	public void setCorners(List<Orientation> corners) {
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
