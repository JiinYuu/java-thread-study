package com.lmq.study.thread.ch1;

import lombok.extern.slf4j.Slf4j;

public @Slf4j class LoggingWidget extends Widget {

	public synchronized void doSomething() {
		log.info("{}: calling doSomething", this);
		super.doSomething();
	}
}
