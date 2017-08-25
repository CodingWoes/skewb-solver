package com.criticalweb.diamondskewb.model.parts;

import com.criticalweb.diamondskewb.model.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ReZz on 2017-08-15.
 */
public class Corner {

    private Map<Orientation, Color> colors = new HashMap<>();

    public Corner(final Orientation o1, final Color c1, final Orientation o2, final Color c2, final Orientation o3, final Color c3) {
        colors.put(o1, c1);
        colors.put(o2, c2);
        colors.put(o3, c3);
    }

    public void rotate(final Direction d, final CornerSwap c) {
        // loop over primary colors

        final DirectionAwareList<Orientation> primaryColors = c.getPrimaryColors();

        Orientation source;
        if (Direction.CW.equals(d)) {
            // the last element is the first source
            source = primaryColors.get(primaryColors.size()-1);
        } else {
            // the first element is the first source
            source = primaryColors.get(0);
        }

        // set the first source color into the buffer
        Color colorBuffer = colors.get(source);

        DirectionAwareIterator<Orientation> iter = primaryColors.iterator(d);

        while (iter.hasNext()) {
            Orientation target = iter.next();

            // store the color in the target position in the temp buffer
            Color temp = colors.get(target);

            // move the source color from the buffer to the target position
            colors.put(target, colorBuffer);

            // move the target color from the temp buffer to the color buffer (for next iteration)
            colorBuffer = temp;
        }

        // then secondary colors

        final DirectionAwareList<Orientation> secondaryColors = c.getSecondaryColors();

        if (Direction.CW.equals(d)) {
            // the last element is the first source
            source = secondaryColors.get(secondaryColors.size() - 1);
        } else {
            source = secondaryColors.get(0);
        }

        // set the first source color into the buffer
        colorBuffer = colors.get(source);

        iter = secondaryColors.iterator(d);

        while (iter.hasNext()) {
            Orientation target = iter.next();

            // store the color in the target position in the temp buffer
            Color temp = colors.get(target);

            // move the source color from the buffer to the target position
            colors.put(target, colorBuffer);

            // move the target color from the temp buffer to the color buffer (for next iteration)
            colorBuffer = temp;
        }
    }

}
