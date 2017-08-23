package com.criticalweb.diamondskewb.model.parts;

import com.criticalweb.diamondskewb.model.Color;
import com.criticalweb.diamondskewb.model.Direction;
import com.criticalweb.diamondskewb.model.Orientation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ReZz on 2017-08-15.
 */
public class Corner extends AbstractRotatablePart {

    private Map<Orientation, Color> colors = new HashMap<>();

    public Corner(final Orientation o1, final Color c1, final Orientation o2, final Color c2, final Orientation o3, final Color c3) {
        colors.put(o1, c1);
        colors.put(o2, c2);
        colors.put(o3, c3);
    }

    @Override
    public void rotate(final Direction d, final CornerSwap c) {
        // actually do something here
    }
}
