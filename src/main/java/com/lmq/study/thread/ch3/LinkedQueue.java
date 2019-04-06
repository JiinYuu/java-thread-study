package com.lmq.study.thread.ch3;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import com.lmq.study.thread.annotation.ThreadSafe;

@ThreadSafe
public class LinkedQueue<E> {

	private final Node<E> dummy = new Node<>(null, null);
	private final AtomicReference<Node<E>> head = new AtomicReference<>(this.dummy);
	private final AtomicReference<Node<E>> tail = new AtomicReference<>(this.dummy);
	
	public boolean put(E item) {
		Node<E> newNode = new Node<>(item, null);
		while (true) {
			Node<E> curTail = this.tail.get();
			Node<E> tailNext = curTail.next.get();
			if (curTail == this.tail.get()) {
				if (Objects.nonNull(tailNext)) {
					// 队列处于中间状态，推进尾节点
					this.tail.compareAndSet(curTail, tailNext);
				} else {
					// 处于稳定状态，尝试插入新节点
					if (curTail.next.compareAndSet(null, newNode)) {
						// 插入操作成功，尝试推进尾节点
						tail.compareAndSet(curTail, newNode);
						return true;
					}
				}
			}
		}
	}
	
	public Node<E> getHead() {
		return this.head.get();
	}
	
	private static class Node<E> {
		@SuppressWarnings("unused")
		private final E item;
		private final AtomicReference<Node<E>> next;
		
		private Node(E item, Node<E> next) {
			this.item = item;
			this.next = new AtomicReference<>(next);
		}
	}
}
