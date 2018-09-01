package com.lmq.study.thread.ch1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import com.lmq.study.thread.ch1.ThreadSafe.ThreadNotSafe;
import com.lmq.study.thread.ch1.ThreadSafe.ThreadBeSafe;

public class ThreadSafeTest {

	public @Test void testThreadNotSafe() throws InterruptedException {
		Runnable r1 = new ThreadNotSafe();
		List<Thread> ts = new ArrayList<>(20);
		for(int i = 0; i < 20; i++) {
			Thread t = WaysToCreateThread.byImplRunnable(r1);
			ts.add(t);
		}
		for(Thread t : ts) {
			t.start();
		}
		Thread.sleep(1000);
		ThreadSafe.stop();
		for(Thread t : ts) {
			t.join();
		}
		Assert.assertTrue(ThreadSafe.safe()); // 可能断言成功，也可能失败，但大概率成功
	}
	
	public @Test void testThreadBeSafe() throws InterruptedException {
		Runnable r1 = new ThreadBeSafe();
		List<Thread> ts = new ArrayList<>(20);
		for(int i = 0; i < 20; i++) {
			Thread t = WaysToCreateThread.byImplRunnable(r1);
			ts.add(t);
		}
		for(Thread t : ts) {
			t.start();
		}
		Thread.sleep(1000);
		ThreadSafe.stop2();
		for(Thread t : ts) {
			t.join();
		}
		Assert.assertFalse(ThreadSafe.safe()); // 可能断言成功，也可能失败，但较大概率成功
	}
}
