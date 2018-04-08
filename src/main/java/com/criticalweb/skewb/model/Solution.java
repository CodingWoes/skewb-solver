package com.criticalweb.skewb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ReZz on 2017-09-08.
 */
public class Solution {

    private long timeTaken;
    private List<Operation> operations;

    public Solution() {
        operations = new ArrayList<>();
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setTimeTaken(final long t) {
        this.timeTaken = t;
    }

    public void setOperations(final List<Operation> operations) {
        this.operations = operations;
    }
}
