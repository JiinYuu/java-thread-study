package com.lmq.study.thread.ch1;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaysToCreateThread {
	
	private static CountDownLatch latch;

	public static Thread byExtendsThread() {
		return new ThreadByExtends();
	}
	
	public static Thread byExtendsThread(CountDownLatch latch) {
		WaysToCreateThread.latch = latch;
		return new ThreadByExtends();
	}
	
	public static Thread byImplRunnable(Runnable runnable) {
		return new Thread(runnable);
	}
	
	public static <R> ThreadByCallable<R> byImplCallable(Callable<R> callable) {
		return new ThreadByCallable<>(callable);
	}
	
	private static class ThreadByExtends extends Thread {
		
		public @Override void run() {
			log.info("I am the thread that extends Thread");
			if(Objects.nonNull(latch)) {
				WaysToCreateThread.latch.countDown();
			}
		}
	}
	
	public static class ThreadByCallable<R> {
		
		private FutureTask<R> task;
		private Thread thread;

		private ThreadByCallable(Callable<R> callable) {
			this.task = new FutureTask<>(callable);
			this.thread = new Thread(this.task);
		}
		
		public FutureTask<R> start() {
			this.thread.start();
			return this.task;
		}
	}
}
