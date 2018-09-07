package com.lmq.study.thread.ch2;

public class FinalExample {

	int i; // 普通变量
	final int j; // final变量
	static FinalExample obj;
	
	public FinalExample() {
		i = 1; // 写普通域
		j = 2; // 写final域
	}
	
	public static void write() {
		obj = new FinalExample();
	}
	
	public static void read() {
		FinalExample fe = obj;
		int a = fe.i;
		int b = fe.j;
		System.out.println("a: " + a + ", b: " + b);
	}
}
