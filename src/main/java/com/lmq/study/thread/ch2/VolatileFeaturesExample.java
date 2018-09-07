package com.lmq.study.thread.ch2;

public class VolatileFeaturesExample {

	volatile long vl = 0L; // 使用volatile声明64位的long型变量
	
	public void set(long l) {
		this.vl = l; // 单个volatile变量的写
	}
	
	public long getAndIncrement() {
		return this.vl++; // 复合（多个）volatile变量的读写
	}
	
	public long get() {
		return this.vl; // 单个volatile变量的读
	}
}
