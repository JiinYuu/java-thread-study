package com.lmq.study.thread.ch1;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadSafe {

	private static boolean running = true;
	private static volatile boolean running2 = true;
	private static AtomicBoolean safe = new AtomicBoolean(false);
	
	public static class ThreadNotSafe implements Runnable {
		
		@Override
		public void run() {
			while (running) {
				safe.set(running);
			}
		}
		
	}
	
	public static class ThreadMayBeSafe implements Runnable {
		
		@Override
		public void run() {
			while (running2) {
				safe.set(running2);
			}
		}
		
	}
	
	public static class ThreadBeSafe implements Runnable {
		
		@Override
		public void run() {
			boolean bool = false;
			while (bool = running()) {
				safe.set(bool);
			}
		}
		
	}
	
	public static void stop() {
		running = false;
	}
	
	public static void stop2() {
		running2 = false;
	}
	
	public static boolean safe() {
		return safe.get();
	}
	
	private static synchronized boolean running() {
		return running;
	}
	
	public static synchronized void completeSafeStop() {
		running = false;
	}
}
