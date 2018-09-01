package com.lmq.study.thread.ch1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

import com.lmq.study.thread.ch1.WaysToCreateThread.ThreadByCallable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaysToCreateThreadTest2 {

	private static final String OK = "OK";
	private CountDownLatch latch = new CountDownLatch(1);

	public @Test void testByExtendsThread() throws InterruptedException {
		Thread thread = WaysToCreateThread.byExtendsThread(latch);
		thread.start();
		latch.await(); // 这是？取代join的？
	}
	
	public @Test void testByImplRunnable() throws InterruptedException {
		Thread thread = WaysToCreateThread.byImplRunnable(() -> {
			log.info("I am the thread that implements Runnable");
			latch.countDown();
		});
		thread.start();
		latch.await();
	}
	
	public @Test void testByImplCallable() throws InterruptedException, ExecutionException {
		ThreadByCallable<String> threadByCall = WaysToCreateThread.byImplCallable(() -> {
			log.info("I am the thread that implements Callable");
			return OK;
		});
		FutureTask<String> future = threadByCall.start();
		log.info("Result: " + future.get());
	}
	
	public @Test void testAll() throws InterruptedException, ExecutionException {
		this.latch = new CountDownLatch(2);
		Thread thread = WaysToCreateThread.byExtendsThread();
		Thread thread2 = WaysToCreateThread.byImplRunnable(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Do nothing
			}
			log.info("I am the thread that implements Runnable");
			latch.countDown();
		});
		ThreadByCallable<String> thread3 = WaysToCreateThread.byImplCallable(() -> {
			log.info("I am the thread that implements Callable");
			latch.countDown();
			return OK;
		});
		FutureTask<String> future = thread3.start();
		thread.start();
		thread2.start();
		log.info("Thread3 done, result: " + future.get());
		latch.await();
	}
}
