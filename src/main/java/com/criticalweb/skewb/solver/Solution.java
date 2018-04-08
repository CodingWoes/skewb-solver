package com.criticalweb.skewb.solver;

import com.criticalweb.skewb.model.Operation;
import com.criticalweb.skewb.model.Skewb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ReZz on 2017-09-08.
 */
public class Solution {

    private Skewb startingSkewb;
    private long timeTaken;
    private List<Operation> operations;

    public Solution() {
        operations = new ArrayList<>();
    }

    public Skewb getStartingSkewb() { return startingSkewb; }

    public long getTimeTaken() {
        return timeTaken;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setStartingSkewb(final Skewb s) { this.startingSkewb = s; }

    public void setTimeTaken(final long t) {
        this.timeTaken = t;
    }

    public void setOperations(final List<Operation> operations) {
        this.operations = operations;
    }
}
