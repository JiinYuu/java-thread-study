package com.lmq.study.thread.ch2;

public class ReorderExample {

	int a = 0;
	boolean flag = false;
	
	public void write() {
		a = 1; // 1
		flag = true; // 2
	}
	
	public void read() {
		if (flag) { // 3
			int i = a * a; // 4
			System.out.println(i);
		}
	}
}
