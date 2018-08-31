package com.lmq.study.thread.ch1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

import com.lmq.study.thread.ch1.WaysToCreateThread.ThreadByCallable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaysToCreateThreadTest {

	private static final String OK = "OK";

	public @Test void testByExtendsThread() throws InterruptedException {
		Thread thread = WaysToCreateThread.byExtendsThread();
		thread.start();
		thread.join();
	}
	
	public @Test void testByImplRunnable() throws InterruptedException {
		Thread thread = WaysToCreateThread.byImplRunnable(() -> log.info("I am the thread that implements Runnable"));
		thread.start();
		thread.join();
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
		Thread thread = WaysToCreateThread.byExtendsThread();
		Thread thread2 = WaysToCreateThread.byImplRunnable(() -> log.info("I am the thread that implements Runnable"));
		ThreadByCallable<String> thread3 = WaysToCreateThread.byImplCallable(() -> {
			log.info("I am the thread that implements Callable");
			return OK;
		});
		thread.start();
		thread2.start();
		FutureTask<String> future = thread3.start();
		log.info("Thread3 done, result: " + future.get());
		thread.join();
		thread2.join();
	}
}
