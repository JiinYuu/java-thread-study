package com.lmq.study.thread.ch3;

import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class CasCounter {

	private SimulatedCAS value;
	
	public int getValue() {
		return this.value.get();
	}
	
	public int increment() {
		int v;
		do {
			v = this.value.get();
		} while (v != this.value.compareAndSwap(v, v + 1));
		return v + 1;
	}
}
