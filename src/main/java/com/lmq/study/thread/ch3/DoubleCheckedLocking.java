package com.lmq.study.thread.ch3;

import com.lmq.study.thread.annotation.NotThreadSafe;

@NotThreadSafe
public class DoubleCheckedLocking {
	
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null)
                    resource = new Resource();
            }
        }
        return resource;
    }

    private static class Resource {
    }
}
