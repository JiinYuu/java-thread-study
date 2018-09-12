package com.lmq.study.thread.ch2;

import com.lmq.study.thread.annotation.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {

	private int value;
	
	public int get() {
		return this.value;
	}
	
	public void set(int value) {
		this.value = value;
	}
}
