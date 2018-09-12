package com.lmq.study.thread.ch2;

public class UnsafeStates {

	private String[] states = new String[] {
		"AK", "AL" // ...
	};
	
	public String[] getStates() {
		return this.states;
	}
}
