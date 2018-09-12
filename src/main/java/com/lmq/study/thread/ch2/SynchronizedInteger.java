package com.lmq.study.thread.ch2;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class SynchronizedInteger {

	private @GuardedBy("this") int value;
	
	public synchronized int get() {
		return this.value;
	}
	
	public synchronized void set(int value) {
		this.value = value;
	}
}
