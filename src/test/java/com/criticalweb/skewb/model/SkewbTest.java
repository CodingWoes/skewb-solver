package com.criticalweb.skewb.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by i840108 on 2017-08-30.
 */
public class SkewbTest {

	@Test
	public void solvedStateNoOp() {
		final Skewb skewb = new Skewb();

		final String expected = "U:Y#Y#Y#Y#Y|S:O#O#O#O#O|W:G#G#G#G#G|E:B#B#B#B#B|N:R#R#R#R#R|D:W#W#W#W#W";

		Assert.assertEquals("Output not as expected on solved state - no op.", expected, skewb.toString());
	}

	@Test
	public void singleCWRotation() {
		final Skewb skewb = new Skewb();

		// initial
		final String expectedBeforeOp = "U:Y#Y#Y#Y#Y|S:O#O#O#O#O|W:G#G#G#G#G|E:B#B#B#B#B|N:R#R#R#R#R|D:W#W#W#W#W";
		Assert.assertEquals("Output not as expected on initial state.", expectedBeforeOp, skewb.toString());

		// UNW
		skewb.rotate(Orientation.UNW, Direction.CW);
		final String expectedAfterUNWOp = "U:R#R#R#R#Y|S:B#O#O#O#O|W:Y#Y#Y#Y#G|E:B#W#B#B#B|N:G#G#G#R#G|D:W#W#W#O#W";
		Assert.assertEquals("Output not as expected after single UNW CW rotation.", expectedAfterUNWOp, skewb.toString());

		// revert back
		skewb.rotate(Orientation.UNW, Direction.CCW);
		Assert.assertEquals("Output not as expected on initial state.", expectedBeforeOp, skewb.toString());

		// UNE
		skewb.rotate(Orientation.UNE, Direction.CW);
		final String expectedAfterUNEOp = "U:B#B#B#Y#B|S:O#W#O#O#O|W:O#G#G#G#G|E:R#R#R#B#R|N:Y#Y#Y#Y#R|D:W#W#W#W#G";
		Assert.assertEquals("Output not as expected after single UNE CW rotation.", expectedAfterUNEOp, skewb.toString());

		// revert back
		skewb.rotate(Orientation.UNE, Direction.CCW);
		Assert.assertEquals("Output not as expected on initial state.", expectedBeforeOp, skewb.toString());

		// USW
		skewb.rotate(Orientation.USW, Direction.CW);
		final String expectedAfterUSWOp = "U:G#Y#G#G#G|S:Y#Y#Y#Y#O|W:O#O#O#G#O|E:R#B#B#B#B|N:R#W#R#R#R|D:B#W#W#W#W";
		Assert.assertEquals("Output not as expected after single USW CW rotation.", expectedAfterUSWOp, skewb.toString());

		// revert back
		skewb.rotate(Orientation.USW, Direction.CCW);
		Assert.assertEquals("Output not as expected on initial state.", expectedBeforeOp, skewb.toString());

		// USE
		skewb.rotate(Orientation.USE, Direction.CW);
		final String expectedAfterUSEOp = "U:Y#O#O#O#O|S:B#B#B#O#B|W:G#W#G#G#G|E:Y#Y#Y#Y#B|N:G#R#R#R#R|D:W#R#W#W#W";
		Assert.assertEquals("Output not as expected after single USE CW rotation.", expectedAfterUSEOp, skewb.toString());

		// revert back
		skewb.rotate(Orientation.USE, Direction.CCW);
		Assert.assertEquals("Output not as expected on initial state.", expectedBeforeOp, skewb.toString());

	}

	@Test
	public void singleCCWRotation() {
		final Skewb skewb = new Skewb();

		final String expectedBeforeOp = "U:Y#Y#Y#Y#Y|S:O#O#O#O#O|W:G#G#G#G#G|E:B#B#B#B#B|N:R#R#R#R#R|D:W#W#W#W#W";

		Assert.assertEquals("Output not as expected on initial state.", expectedBeforeOp, skewb.toString());

		skewb.rotate(Orientation.USW, Direction.CCW);

		final String expectedAfterOp = "U:O#Y#O#O#O|S:G#G#G#G#O|W:Y#Y#Y#G#Y|E:W#B#B#B#B|N:R#B#R#R#R|D:R#W#W#W#W";

		Assert.assertEquals("Output not as expected after single CCW rotation.", expectedAfterOp, skewb.toString());

	}

	@Test
	public void multipleCWRotation() {
		final Skewb skewb = new Skewb();

		final String expectedBeforeOp = "U:Y#Y#Y#Y#Y|S:O#O#O#O#O|W:G#G#G#G#G|E:B#B#B#B#B|N:R#R#R#R#R|D:W#W#W#W#W";

		Assert.assertEquals("Output not as expected on initial state.", expectedBeforeOp, skewb.toString());

		skewb.rotate(Orientation.UNW, Direction.CW);
		skewb.rotate(Orientation.UNE, Direction.CW);
		skewb.rotate(Orientation.USW, Direction.CW);
		skewb.rotate(Orientation.USE, Direction.CW);

		final String expectedAfterOp = "U:G#R#B#O#B|S:B#Y#G#B#G|W:O#W#O#Y#W|E:O#Y#Y#W#G|N:B#W#R#R#G|D:R#R#W#O#Y";

		Assert.assertEquals("Output not as expected after multiple CW rotation.", expectedAfterOp, skewb.toString());

	}

	@Test
	public void initWithString() {

		final String expected = "U:R#R#R#R#Y|S:B#O#O#O#O|W:Y#Y#Y#Y#G|E:B#W#B#B#B|N:G#G#G#R#G|D:W#W#W#O#W";

		final Skewb skewb = new Skewb(expected);

		Assert.assertEquals("Output not as expected after setup with string.", expected, skewb.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidRotateCalls() {

		final Skewb skewb = new Skewb();

		skewb.rotate(Orientation.NORTH, Direction.CW);

	}

	@Test
	public void solved() {
		final Skewb skewb = new Skewb();

		Assert.assertTrue("Initial skewb should be solved!", skewb.isSolved());

		skewb.rotate(Orientation.UNW, Direction.CW);

		Assert.assertFalse("Skewb shouldn't be solved after rotating corner.", skewb.isSolved());

		skewb.rotate(Orientation.UNW, Direction.CCW);

		Assert.assertTrue("Skewb should be solved after reverting first rotation.", skewb.isSolved());
	}

}
