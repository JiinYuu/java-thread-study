package com.lmq.study.thread.ch3;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class OneShotLatch {
	
	private Sync sync = new Sync();
	
	public void singal() {
		this.sync.releaseShared(0);
	}
	
	public void await() throws InterruptedException {
		this.sync.acquireSharedInterruptibly(0);
	}

	private class Sync extends AbstractQueuedSynchronizer {
		private static final long serialVersionUID = 826799366970592885L;

		@Override
		protected int tryAcquireShared(int ignored) {
			// 如果闭锁是开的（state == 1），那么这个操作将成功（以非独占方式获取成功），否则将失败
			return this.getState() == 1 ? 1 : -1;
		}

		@Override
		protected boolean tryReleaseShared(int ignored) {
			this.setState(1); // 打开闭锁
			return true; // 让其他线程可以获取该闭锁
		}
		
	}
}
