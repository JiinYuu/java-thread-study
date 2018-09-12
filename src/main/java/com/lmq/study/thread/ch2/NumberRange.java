package com.lmq.study.thread.ch2;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRange {
    // INVARIANT: lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > this.upper.get()) {
        	throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        this.lower.set(i);
    }

    public void setUpper(int i) {
        if (i < this.lower.get()) {
        	throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        }
        this.upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= this.lower.get() && i <= this.upper.get());
    }
}
