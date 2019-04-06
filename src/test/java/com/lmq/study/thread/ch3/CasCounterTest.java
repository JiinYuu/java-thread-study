package com.lmq.study.thread.ch3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import lombok.RequiredArgsConstructor;

public class CasCounterTest {

	public @Test void testCasCounter() throws InterruptedException {
		final CasCounter counter = new CasCounter();
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executor.submit(new CounterTask(counter));
		}
		executor.shutdown();
		executor.awaitTermination(500, TimeUnit.SECONDS);
		Assert.assertEquals(1000, counter.getValue());
	}
	
	@RequiredArgsConstructor
	private static class CounterTask implements Runnable {
		private final CasCounter counter;
		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				counter.increment();
			}
		}
	}
}
