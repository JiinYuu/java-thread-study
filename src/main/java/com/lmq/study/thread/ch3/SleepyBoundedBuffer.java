package com.lmq.study.thread.ch3;

public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	private int SLEEP_GRANULARITY = 60;
	
	protected SleepyBoundedBuffer(int capacity) {
		super(capacity);
	}

	@Override
	public void put(V v) throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!this.isFull()) {
					this.doPut(v);
					return;
				}
			}
			Thread.sleep(SLEEP_GRANULARITY);
		}
	}

	@Override
	public V take() throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!this.isEmpty()) {
					return this.doTake();
				}
			}
			Thread.sleep(SLEEP_GRANULARITY);
		}
	}

}
