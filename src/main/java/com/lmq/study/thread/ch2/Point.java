package com.lmq.study.thread.ch2;

import com.lmq.study.thread.annotation.Immutable;

@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
