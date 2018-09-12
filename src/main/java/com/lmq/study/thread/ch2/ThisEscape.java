package com.lmq.study.thread.ch2;

public class ThisEscape {

	public ThisEscape(EventSource source) {
		source
		.registEventListener(new EventListener() {
			public void onEvent(Event e) {
				ThisEscape.this.doSomething(e);
			}
		});
	}
	
	void doSomething(Event e) {}
	
	public interface EventSource {
		void registEventListener(EventListener listener);
	}
	
	public interface EventListener {
		void onEvent(Event e);
	}
	
	public interface Event {}
}
