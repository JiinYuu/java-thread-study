package com.lmq.study.thread.ch2;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class Counter {

	private @GuardedBy("this") long value = 0;

	public synchronized long getValue() {
		return this.value;
	}

	public synchronized long increment() {
		if (this.value == Long.MAX_VALUE) {
			throw new IllegalStateException("counter overflow");
		}
		return ++value;
	}
}
