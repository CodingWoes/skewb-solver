package com.criticalweb.diamondskewb;

import com.criticalweb.diamondskewb.model.DiamondSkewb;
import com.criticalweb.diamondskewb.model.Direction;
import com.criticalweb.diamondskewb.model.Orientation;

/**
 * Created by i840108 on 2017-08-29.
 */
public class App {

	public static void main(String[] args) {
		System.out.println("Hello world");

		final DiamondSkewb skewb = new DiamondSkewb();

		System.out.println(skewb);

		skewb.rotate(Orientation.USW, Direction.CCW);

		System.out.println(skewb);

	}
}
