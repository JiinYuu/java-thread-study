package com.lmq.study.thread.ch3;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public abstract class BaseBoundedBuffer<V> {

	private @GuardedBy("this") final V[] buf;
	private @GuardedBy("this") int tail;
	private @GuardedBy("this") int head;
	private @GuardedBy("this") int count;
	
	@SuppressWarnings("unchecked")
	protected BaseBoundedBuffer(int capacity) {
		this.buf = (V[]) new Object[capacity];
	}
	
	protected synchronized final void doPut(V v) {
		buf[tail] = v;
		if (++tail == buf.length) {
			tail = 0;
		}
		++count;
	}
	
	protected synchronized final V doTake() {
		V v = buf[head];
		buf[head] = null;
		if (++head == buf.length) {
			head = 0;
		}
		--count;
		return v;
	}
	
	public synchronized final boolean isFull() {
		return count ==  buf.length;
	}
	
	public synchronized final boolean isEmpty() {
		return count == 0;
	}
	
	public abstract void put(V v) throws InterruptedException;
	
	public abstract V take() throws InterruptedException;
}
