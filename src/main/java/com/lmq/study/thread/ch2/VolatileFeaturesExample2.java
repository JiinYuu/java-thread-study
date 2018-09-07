package com.lmq.study.thread.ch2;

public class VolatileFeaturesExample2 {

	long vl = 0L; // 64位的long型普通变量
	
	// 对单个普通变量的写用同一个锁同步
	public synchronized void set(long l) {
		this.vl = l;
	}
	
	public long getAndIncrement() {
		long temp = this.get(); // 调用已同步的读方法
		temp = temp + 1; // 普通写操作
		this.set(temp); // 调用已同步的写操作
		return temp;
	}
	
	// 对单个普通变量的读使用同一个锁同步
	public synchronized long get() {
		return this.vl;
	}
}
