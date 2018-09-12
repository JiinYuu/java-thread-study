package com.lmq.study.thread.ch2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lmq.study.thread.annotation.ThreadSafe;

public @ThreadSafe class GoodListHelper<E> {
	public List<E> list = Collections.synchronizedList(new ArrayList<>());
	
	public boolean putIfAbsent(E e) {
		synchronized (this.list) {
			boolean absent = !this.list.contains(e);
			if (absent) {
				this.list.add(e);
			}
			return absent;
		}
	}
}
