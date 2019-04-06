package com.lmq.study.thread.ch3;

import java.util.concurrent.atomic.AtomicReference;

import com.lmq.study.thread.annotation.Immutable;

public class CasNumberRange {

	@Immutable
	private static class IntPair {
		private final int lower; // 不变性条件：lower <= upper
		private final int upper;
		
		private IntPair(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
	}
	
	private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0, 0));
	
	public int getLower() {
		return this.values.get().lower;
	}
	
	public int getUpper() {
		return this.values.get().upper;
	}
	
	public void setLower(int lower) {
		while (true) {
			IntPair old = this.values.get();
			if (lower > old.upper) {
				throw new IllegalArgumentException("Can't set lower to " + lower + " > upper");
			}
			IntPair newP = new IntPair(lower, old.upper);
			if (this.values.compareAndSet(old, newP)) {
				return;
			}
		}
	}
	
	public void setUpper(int upper) {
		while (true) {
			IntPair old = this.values.get();
			if (upper < old.lower) {
				throw new IllegalArgumentException("Can't set upper to " + upper + " < lower");
			}
			IntPair newP = new IntPair(old.lower, upper);
			if (this.values.compareAndSet(old, newP)) {
				return;
			}
		}
	}
}
