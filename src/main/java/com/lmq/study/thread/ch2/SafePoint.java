package com.lmq.study.thread.ch2;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class SafePoint {
	private @GuardedBy("this") int x, y;
	
	private SafePoint(int[] a) {
		this(a[0], a[1]);
	}
	
	public SafePoint(SafePoint p) {
		this(p.get());
	}
	
	public SafePoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public synchronized int[] get() {
		return new int[] { x, y };
	}
	
	public synchronized void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
