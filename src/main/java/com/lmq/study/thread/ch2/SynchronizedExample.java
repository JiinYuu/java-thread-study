package com.lmq.study.thread.ch2;

public class SynchronizedExample {
	
	int a = 0;
	boolean flag = false;
	
	public synchronized void write() {
		a = 1; // 1
		flag = true; // 2
	}
	
	public synchronized void read() {
		if (flag) { // 3
			int i = a * a; // 4
			System.out.println(i);
		}
	}
}
