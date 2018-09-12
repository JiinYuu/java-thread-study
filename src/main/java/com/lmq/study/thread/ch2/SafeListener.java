package com.lmq.study.thread.ch2;

public class SafeListener {
	private final EventListener listener;

	private SafeListener() {
	    this.listener = new EventListener() {
	        public void onEvent(Event e) {
	            doSomething(e);
	        }
	    };
	}
	
	public static SafeListener newInstance(EventSource source) {
	    SafeListener safe = new SafeListener();
	    source.registerListener(safe.listener);
	    return safe;
	}
	
	void doSomething(Event e) {}
	
	
	interface EventSource {
	    void registerListener(EventListener e);
	}
	
	interface EventListener {
	    void onEvent(Event e);
	}
	
	interface Event {}
}
