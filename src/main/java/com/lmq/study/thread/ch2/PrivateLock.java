package com.lmq.study.thread.ch2;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.ch1.Widget;

public class PrivateLock {
	private final Object myLock = new Object();
    @GuardedBy("myLock") Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // ...
        }
    }
}
