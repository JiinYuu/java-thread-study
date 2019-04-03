package com.lmq.study.thread.ch3;

import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}

	@Override
	public synchronized void put(V v) throws BufferFullException {
		if (this.isFull()) {
			throw new BufferFullException();
		}
		this.doPut(v);
	}

	@Override
	public synchronized V take() throws BufferEmptyException {
		if (this.isEmpty()) {
			throw new BufferEmptyException();
		}
		return this.doTake();
	}
	
	private static class BufferFullException extends RuntimeException {
		private static final long serialVersionUID = -544569550497037137L;
	}
	
	private static class BufferEmptyException extends RuntimeException {
		private static final long serialVersionUID = -544569550497037137L;
	}
	
	public static class ExampleUsage {
		private GrumpyBoundedBuffer<String> buffer;
		private int SLEEP_GRANULARITY = 50;
		
		public void useBuffer() throws InterruptedException {
			while (true) {
				try {
					String item = buffer.take();
					// 对item执行一些操作
					System.out.println(item);
					break;
				} catch (BufferEmptyException e) {
					Thread.sleep(SLEEP_GRANULARITY);
				}
			}
		}
	}
}
