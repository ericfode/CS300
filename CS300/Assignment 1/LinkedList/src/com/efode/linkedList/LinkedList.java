package com.efode.linkedList;

import java.io.Serializable;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

import javax.naming.OperationNotSupportedException;

public class LinkedList<E> implements Serializable, Cloneable, Iterable<E>,
		Collection<E>, Deque<E>, List<E>, Queue<E> {

	public class Node<T>{
		public LinkedList<T> list;
		public T value;
		public Node<T> next;
		public Node<T> last;
		public boolean dummy;
		
		public Node(T value,LinkedList<T> list,boolean dummy)
		{
			//the this pointer is being used because the names of the parameters
			//are the same as the name of the fields in the class
			this.list = list;
			this.value = value;
			this.dummy = dummy;
		}

		
		//only support moving node to different locations in the same list



		
		@Override
		public boolean equals(Object o) {
			return (this.value == ((Node<T>)o).value);
		}
		

		



		
	}

	private Node<E> dummy;
	private int size;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7475195061611841528L;

	public LinkedList()
	{
		this.dummy = new Node<E>(null,this, true);
		this.dummy.last = this.dummy;
		this.dummy.next = this.dummy;
	}
	
	public int getNodeIndex(Node<E> cur)
	{
		//recurse through list until the dummy node is reached then add up
		//the number of steps
		//return -1 this is the dummy node
		return cur.dummy ? -1 : getNodeIndex(cur.last) + 1;
	}
	
	//returns reference to new node
	public Node<E> addNode(Node<E> cur, int index,Node<E> newNode) throws IndexOutOfBoundsException
	{
		//check to make sure that the beginning or the end of the list 
		//has not been reached if the list is being iterated
		if(cur.dummy && index != 0)
		{
			throw new IndexOutOfBoundsException();
		}
		
		
		if(index < 0)
		{
			return addNode(cur.last, index+1, newNode);
		}
		else if(index > 0)
		{
			return addNode(cur.next, index-1, newNode);
		}
		
		//if(index == 0)
		else
		{
			newNode.last = cur;
			newNode.next = cur.next;
			
			cur.next.last = newNode;
			cur.next = newNode;
			return newNode;
		}
	}
	
	public Node<E> getNode(Node<E> cur, int index) throws IndexOutOfBoundsException
	{

		//check to make sure that the beginning or the end of the list has not been reached
		if(cur.dummy)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(index== 0)
		{
			return cur;
		}
		else
		{
			return getNode(cur.next,index-1);
		}
	}
	
	public Node<E> findNode(Node<E> cur, E value)
	{
		if(cur.dummy)
		{
			return cur;
		}
		if(cur.value == value)
		{
			return cur;
		}
		else
		{
			return findNode(cur.next,value);
		}
	}
	
	public boolean nodeExsists(Node<E> cur,E value)
	{
		if(cur.dummy)
		{
			return false;
		}
		if(cur.value == value)
		{
			return true;
		}
		else
		{
			return nodeExsists(cur.next, value);
		}
	}
	
	
	public void removeNode(Node<E> node) throws OperationNotSupportedException
	{
		if(node.dummy)
		{
			//don't let the dummy remove it's self
			throw new OperationNotSupportedException();
		}
		else
		{
			node.next.last = node.last;
			node.last.next = node.next;
		}
	}
	
	public void moveNode(Node<E> moveNode, Node<E> newNext, Node<E> newLast)
	{
		moveNode.next.last = moveNode.last;
		moveNode.last.next = moveNode.next;
		moveNode.next = newNext;
		moveNode.last = newLast;
	}
	
	public LinkedList(Collection<? extends E> c)
	{
		
	}
	@Override
	public void add(int index, E item) {
		addNode(dummy,index, new Node<E>(item,this, false));
		size++;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> col) {

		try
		{
			for(E item : col)
			{
				//add a new node to the position requested in the list then
				//Increment the index by one to account for the new item
				this.add(index++, item);
			}
		
			return true;
		} 
		catch(Exception e)
		{
			return false;	
		}
	}

	@Override
	public E get(int index) {
		return getNode(dummy.next, index).value;
	}

	@Override
	public int indexOf(Object item) {
		E itemCast = (E)item;
		return getNodeIndex(findNode(dummy.next, itemCast));
	}

	@Override
	public int lastIndexOf(Object item) {
		E itemCast = (E)item;
		Node<E> curItem = findNode(dummy.next, itemCast);
		Node<E> last = findNode(dummy.next, itemCast);
		while(curItem.dummy == false )
		{
			last = curItem;
			curItem = findNode(curItem, itemCast);
		}
		return getNodeIndex(last);
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) {
		try {
			Node<E> node = getNode(this.dummy, index);
			E val = node.value;
			removeNode(node);
			return val;
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public E set(int index, E value) {
		getNode(this.dummy,index).value = value;
		return value;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E value) {
		add(value);
	}

	@Override
	public void addLast(E item) {
		addNode(this.dummy, size-1, new Node<E>(item,this, false));
	}

	@Override
	public Iterator<E> descendingIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getFirst() {
		return this.dummy.next.value;
	}

	@Override
	public E getLast() {
		return this.dummy.last.value;
	}

	@Override
	public boolean offer(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerFirst(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerLast(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(E value) {
		add(value);
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeFirst() {
		E value = getNode(this.dummy, 0).value;
		remove(0);
		return value;
	}

	@Override
	public boolean removeFirstOccurrence(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeLastOccurrence(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("hiding")
	@Override
	public <E> E[] toArray(E[] arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString(){
	
		return null;
	}
	
	@Override
	public int hashCode(){
		return 0;
	}
	
	@Override
	public boolean equals(Object o){
		return false;
	}
}

