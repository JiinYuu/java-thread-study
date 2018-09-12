package com.lmq.study.thread.ch2;

import org.junit.Test;

import com.lmq.study.thread.ch2.ThisEscape.EventListener;
import com.lmq.study.thread.ch2.ThisEscape.EventSource;

public class ThisEscapeTest {

	public @Test void testThisEscape() {
		EventSource source = new EventSource() {
			@Override
			public void registEventListener(EventListener listener) {
				System.out.println(listener);
			}
		};
		new ThisEscape(source);
	}
}
