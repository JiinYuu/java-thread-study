package com.lmq.study.thread.ch2;

import java.util.Vector;

import com.lmq.study.thread.annotation.ThreadSafe;

public @ThreadSafe class BetterVector<E> extends Vector<E> {
	private static final long serialVersionUID = 2394399897970037934L;

	public synchronized boolean putIfAbsent(E e) {
		boolean absent = !this.contains(e);
		if (absent) {
			this.add(e);
		}
		return absent;
	}
}
