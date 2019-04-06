package com.lmq.study.thread.ch3;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class ConcurrentStack<E> {

	private AtomicReference<Node<E>> top = new AtomicReference<>();
	
	public void push(E item) {
		Node<E> newTop = new Node<>(item);
		Node<E> oldTop;
		do {
			oldTop = top.get();
			newTop.next = oldTop;
		} while (!this.top.compareAndSet(oldTop, newTop));
	}
	
	public E pop() {
		Node<E> oldTop;
		Node<E> newTop;
		do {
			oldTop = top.get();
			if (Objects.isNull(oldTop)) {
				return null;
			}
			newTop = oldTop.next;
		} while (!this.top.compareAndSet(oldTop, newTop));
		return oldTop.item;
	}
	
	private static class Node<E> {
		private final E item;
		private Node<E> next;
		
		private Node(E item) {
			this.item = item;
		}
	}
}
