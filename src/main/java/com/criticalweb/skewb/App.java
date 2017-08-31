package com.criticalweb.skewb;

import com.criticalweb.skewb.model.Skewb;
import com.criticalweb.skewb.model.Direction;
import com.criticalweb.skewb.model.Orientation;

/**
 * Created by i840108 on 2017-08-29.
 */
public class App {

	public static void main(String[] args) {
		System.out.println("Hello world");

		final Skewb skewb = new Skewb();

		System.out.println(skewb);

		skewb.rotate(Orientation.USW, Direction.CCW);

		System.out.println(skewb);

	}
}
