package com.lmq.study.thread.ch2;

public class CountingSheep {

	volatile boolean asleep;

    void tryToSleep() {
        while (!asleep)
            countSomeSheep();
    }

    void countSomeSheep() {
        // One, two, three...
    }
}
