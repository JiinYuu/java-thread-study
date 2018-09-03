package com.lmq.study.thread.ch1;

import lombok.extern.slf4j.Slf4j;

public @Slf4j class NoVisibility {

	private static boolean ready = false;
	private static int number = 0;
	
	private static class ReaderThread extends Thread {
		public void run() {
			while (!ready) {
				Thread.yield();
			}
			log.info("number: " + number);
		}
	}
	
	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}
}
