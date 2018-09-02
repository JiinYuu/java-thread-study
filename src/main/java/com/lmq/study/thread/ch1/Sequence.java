package com.lmq.study.thread.ch1;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class Sequence {

	private @GuardedBy("this") int value;
	
	public synchronized int next() {
		return value++;
	}
}
