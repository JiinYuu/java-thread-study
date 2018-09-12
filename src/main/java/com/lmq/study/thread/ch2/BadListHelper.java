package com.lmq.study.thread.ch2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lmq.study.thread.annotation.NotThreadSafe;

public @NotThreadSafe class BadListHelper<E> {
	public List<E> list = Collections.synchronizedList(new ArrayList<>());
	
	public synchronized boolean putIfAbsent(E e) {
		boolean absent = !this.list.contains(e);
		if (absent) {
			this.list.add(e);
		}
		return absent;
	}
}
