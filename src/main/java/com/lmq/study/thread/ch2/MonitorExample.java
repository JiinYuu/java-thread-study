package com.lmq.study.thread.ch2;

public class MonitorExample {

	int a = 0;
	
	public synchronized void write() { // 1
		a++; // 2
	} // 3
	
	public synchronized void read() { // 4
		int i = a; // 5
		System.out.println(i);
	} // 6
}
