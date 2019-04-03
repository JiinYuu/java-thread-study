package com.lmq.study.thread.ch3;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

	// 条件谓词：not-full (!isFull())
	// 条件谓词：not-empty (!isEmpty)
	
	protected BoundedBuffer(int capacity) {
		super(capacity);
	}

	// 阻塞直到：not-full
	@Override
	public synchronized void put(V v) throws InterruptedException {
		while (this.isFull()) {
			this.wait();
		}
		this.doPut(v);
		this.notifyAll();
	}

	// 阻塞直到：not-empty
	@Override
	public V take() throws InterruptedException {
		while (this.isEmpty()) {
			this.wait();
		}
		V v = this.doTake();
		this.notifyAll();
		return v;
	}
	
	// 阻塞直到: not-full
    // 使用条件通知实现的另一种put方法
    public synchronized void alternatePut(V v) throws InterruptedException {
        while (this.isFull()) {
        	wait();
        }
        boolean wasEmpty = this.isEmpty();
        doPut(v);
        if (wasEmpty) {
        	notifyAll();
        }
    }
}
