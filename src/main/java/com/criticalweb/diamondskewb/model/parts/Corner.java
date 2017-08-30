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

    private SwappableMap<Orientation, Color> colors = new SwappableMap<>();

    public Corner(final Orientation o1, final Color c1, final Orientation o2, final Color c2, final Orientation o3, final Color c3) {
        colors.put(o1, c1);
        colors.put(o2, c2);
        colors.put(o3, c3);
    }

    public Color getColor(final Orientation o) {
        return colors.get(o);
    }

    public void rotate(final Direction d, final CornerSwap c) {

        // loop over primary colors
        colors.swap(d, c.getPrimaryColors());

        // then secondary colors
        colors.swap(d, c.getSecondaryColors());

    }

    public SwappableMap<Orientation, Color> getColors() {
        return colors;
    }

}
