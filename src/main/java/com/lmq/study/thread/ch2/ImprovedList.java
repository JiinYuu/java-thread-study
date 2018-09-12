package com.lmq.study.thread.ch2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.lmq.study.thread.annotation.ThreadSafe;

public @ThreadSafe class ImprovedList<T> implements List<T> {
	private final List<T> list;

	/**
	 * 前提：同Collections.synchronizedList一样，客户端不再使用原list
	 * @param list
	 */
	public ImprovedList(List<T> list) {
		this.list = list;
	}
	
	public synchronized boolean putIfAbsent(T t) {
		boolean absent = !this.contains(t);
		if (absent) {
			this.add(t);
		}
		return absent;
	}
	
	public int size() {
		return this.list.size();
	}

	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	public boolean contains(Object o) {
		return this.list.contains(o);
	}

	public Iterator<T> iterator() {
		return this.list.iterator();
	}

	public Object[] toArray() {
		return this.list.toArray();
	}

	public <S> S[] toArray(S[] a) {
		return this.list.toArray(a);
	}

	public synchronized boolean add(T e) {
		return this.list.add(e);
	}

	public synchronized boolean remove(Object o) {
		return this.list.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return this.list.containsAll(c);
	}

	public synchronized boolean addAll(Collection<? extends T> c) {
		return this.list.addAll(c);
	}

	public synchronized boolean addAll(int index, Collection<? extends T> c) {
		return this.list.addAll(index, c);
	}

	public synchronized boolean removeAll(Collection<?> c) {
		return this.list.removeAll(c);
	}

	public synchronized boolean retainAll(Collection<?> c) {
		return this.list.retainAll(c);
	}

	public synchronized void clear() {
		this.list.clear();
	}

	public T get(int index) {
		return this.list.get(index);
	}

	public synchronized T set(int index, T element) {
		return this.list.set(index, element);
	}

	public synchronized void add(int index, T element) {
		this.list.add(index, element);
	}

	public synchronized T remove(int index) {
		return this.list.remove(index);
	}

	public int indexOf(Object o) {
		return this.list.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return this.list.lastIndexOf(o);
	}

	public ListIterator<T> listIterator() {
		return this.list.listIterator();
	}

	public ListIterator<T> listIterator(int index) {
		return this.list.listIterator(index);
	}

	public List<T> subList(int fromIndex, int toIndex) {
		return this.list.subList(fromIndex, toIndex);
	}
}
