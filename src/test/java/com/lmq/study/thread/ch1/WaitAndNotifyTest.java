package com.lmq.study.thread.ch1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.lmq.study.thread.ch1.WaitAndNotify.Producer;
import com.lmq.study.thread.ch1.WaitAndNotify.Consumer;
import com.lmq.study.thread.ch1.WaitAndNotify.Producer2;
import com.lmq.study.thread.ch1.WaitAndNotify.Consumer2;

public class WaitAndNotifyTest {

	public @Test void testProducerAndConsumer() throws InterruptedException {
		
		List<Thread> threads = new ArrayList<>(15);
		for(int i = 0; i < 10; i++) {
			Runnable producer = new Producer("p" + i, 5);
			Thread producerT = WaysToCreateThread.byImplRunnable(producer);
			threads.add(producerT);
		}
		
		for(int i = 0; i < 5; i++) {
			Runnable consumer = new Consumer("c" + i);
			Thread consumerT = WaysToCreateThread.byImplRunnable(consumer);
			threads.add(consumerT);
		}
		
		Collections.shuffle(threads);
		for(Thread thread : threads) {
			thread.start();
		}
		for(Thread thread : threads) {
			thread.join();
		}
	}
	
	public @Test void testProducerAndConsumer2() throws InterruptedException {
		
		List<Thread> threads = new ArrayList<>(15);
		for(int i = 0; i < 10; i++) {
			Runnable producer = new Producer2("p" + i, 5);
			Thread producerT = WaysToCreateThread.byImplRunnable(producer);
			threads.add(producerT);
		}
		
		for(int i = 0; i < 5; i++) {
			Runnable consumer = new Consumer2("c" + i);
			Thread consumerT = WaysToCreateThread.byImplRunnable(consumer);
			threads.add(consumerT);
		}
		
		Collections.shuffle(threads);
		for(Thread thread : threads) {
			thread.start();
		}
		for(Thread thread : threads) {
			thread.join();
		}
	}
}
