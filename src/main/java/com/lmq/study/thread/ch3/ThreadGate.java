package com.lmq.study.thread.ch3;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class ThreadGate {
	
    // 条件谓词: opened-since(n) (isOpen || generation > n)
	
	private @GuardedBy("this") boolean isOpen;
	private @GuardedBy("this") int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    // 阻塞直到: opened-since(generation on entry)
    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation) {
        	wait();
        }
    }
}
