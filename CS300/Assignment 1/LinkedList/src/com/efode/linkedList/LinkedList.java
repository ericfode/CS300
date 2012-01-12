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

		
		public boolean exsists(T value)
		{
			if(this.dummy)
			{
				return false;
			}
			if(this.value == value)
			{
				return true;
			}
			else
			{
				return this.next.exsists(value);
			}
		}
		
		public Node<T> find(T value)
		{
			if(this.dummy)
			{
				return this;
			}
			if(this.value == value)
			{
				return this;
			}
			else
			{
				return this.next.find(value);
			}
		}
		
		@Override
		public boolean equals(Object o) {
			return (this.value == ((Node<T>)o).value);
		}
		
		public Node<T> get(int index) throws IndexOutOfBoundsException
		{

			//check to make sure that the beginning or the end of the list has not been reached
			if(this.dummy)
			{
				throw new IndexOutOfBoundsException();
			}
			
			if(index== 0)
			{
				return this;
			}
			else
			{
				return this.next.get(index-1);
			}
		}
		

		//returns reference to new node
		public Node<T> addNext(int index,Node<T> newNode) throws IndexOutOfBoundsException
		{
			//check to make sure that the beginning or the end of the list has not been reached
			if(this.dummy)
			{
				throw new IndexOutOfBoundsException();
			}
			
			
			if(index < 0)
			{
				return this.last.addNext(index+1, newNode);
			}
			else if(index > 0)
			{
				return this.next.addNext(index-1, newNode);
			}
			
			//if(index == 0)
			else
			{
				newNode.last = this;
				newNode.next = this.next;
				
				this.next.last = newNode;
				this.next = newNode;
				return newNode;
			}
		}
		public int getIndex()
		{
			//recurse through list until the dummy node is reached then add up
			//the number of steps
			//return -1 this is the dummy node
			return this.dummy ? -1 : this.last.getIndex() + 1;
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
	
	public void remove(Node<E> node) throws OperationNotSupportedException
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
	
	public void move(Node<E> moveNode, Node<E> newNext, Node<E> newLast)
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
		dummy.addNext(index, new Node<E>(item,this, false));
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
		return dummy.next.get(index).value;
	}

	@Override
	public int indexOf(Object item) {
		E itemCast = (E)item;
		return dummy.next.find(itemCast).getIndex();
	}

	@Override
	public int lastIndexOf(Object item) {
		E itemCast = (E)item;
		Node<E> curItem;
		while(curItem = )
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
	public E remove(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int arg0, E arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLast(E arg0) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getLast() {
		// TODO Auto-generated method stub
		return null;
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
	public void push(E arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		return null;
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

