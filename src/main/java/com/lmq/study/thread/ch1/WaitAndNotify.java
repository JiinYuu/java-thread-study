package com.lmq.study.thread.ch1;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitAndNotify {

	private static Queue<LocalDateTime> queue = new LinkedList<>();
	private static int MAX_SIZE = 10;
	
	public static class Producer implements Runnable {

		private String name;
		private int count = 0;
		private int max;
		private volatile boolean running = true; // volatile？干嘛的？
		
		public Producer(String name, int max) {
			this.name = name;
			this.max = max;
		}
		
		@Override
		public void run() {
			while(running) {
				synchronized (queue) {
					produce();
				}
				Thread.yield(); // 这是？
				if(++count >= max) { // 这段代码为什么不放在同步块里面？
					log.info("producer[{}] 生产了{}个了，下班了。", this.name, count);
					running = false;
				}
			}
		}

		private void produce() {
			while (queue.size() == MAX_SIZE) { // 为嘛要循环？为嘛不用if？
				try {
					log.info("the size is max, producer[{}] begin to wait", this.name);
					queue.wait();
				} catch (InterruptedException e) {
					// do nothing
				}
			}
			LocalDateTime now = LocalDateTime.now();
			queue.add(now);
			log.info("producer[{}] produce an new element: {}, size: {}", this.name, now, queue.size());
			queue.notifyAll();
		}
	}
	
	public static class Consumer implements Runnable {

		private String name;
		private int count;
		private volatile boolean running = true;
		
		public Consumer(String name) {
			this.name = name;
		}
		
		@Override
		public void run() {
			while(running) {
				synchronized (queue) {
					consume();
				}
				Thread.yield();
			}
		}

		private void consume() {
			while (queue.size() == 0) {
				try {
					log.info("the size is 0, consumer[{}] begin to wait", this.name);
					queue.wait(500); // 带参数的wait和不带参数的wait？
					log.info("consumer[{}]: 等了这么久都没有了，我不买了，回家了，反正一共买了{}个了", this.name, this.count);
					running = false;
					return; // 为什么不生产者不一样？为什么要这里要return？
				} catch (InterruptedException e) {
					// do nothing
				}
			}
			count++;
			LocalDateTime element = queue.poll();
			log.info("consumer[{}] consume an element: {}, size: {}", this.name, element, queue.size());
			queue.notifyAll();
		}
	}
}
