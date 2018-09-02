package com.lmq.study.thread.ch1;

import com.lmq.study.thread.annotation.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {

	private int value;
	
	/** 返回一个唯一的，线性递增的数值。*/
	public int next() {
		return value++;
	}
}
