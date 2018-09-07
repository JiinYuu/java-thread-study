package com.lmq.study.thread.ch2;

public class VolatileExample {

	int a = 0;
	volatile boolean flag;
	
	public void write() {
		a = 1; // 1
		flag = true; // 2
	}
	
	public void read() {
		if (flag) { // 3
			int i = a; // 4
			System.out.println(i);
		}
	}
}
