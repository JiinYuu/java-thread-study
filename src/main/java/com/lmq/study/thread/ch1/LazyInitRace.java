package com.lmq.study.thread.ch1;

import java.util.Objects;

import com.lmq.study.thread.annotation.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {

	private ExpensiveObject instance = null;
	
	public ExpensiveObject getInstance() {
		if(Objects.isNull(this.instance)) {
			this.instance = new ExpensiveObject();
		}
		return this.instance;
	}
	
	public static class ExpensiveObject {}
}
