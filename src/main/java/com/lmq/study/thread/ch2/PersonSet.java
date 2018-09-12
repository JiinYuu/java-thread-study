package com.lmq.study.thread.ch2;

import java.util.HashSet;
import java.util.Set;

import com.lmq.study.thread.annotation.GuardedBy;
import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class PersonSet {
	private @GuardedBy("this") final Set<Person> mySet = new HashSet<Person>();

	public synchronized void addPerson(Person p) {
		this.mySet.add(p);
	}

	public synchronized boolean containsPerson(Person p) {
		return this.mySet.contains(p);
	}

	interface Person {}
}
