/// Did the following extra credit
// Used Generics


package com.efode.linkedList;

import java.io.Serializable;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.naming.OperationNotSupportedException;

public class LinkedList<E> implements Serializable, Cloneable, Iterable<E>,
		Collection<E>, Deque<E>, List<E>, Queue<E> {

	public class LinkedListIterator<T> implements Iterator<T>{
		private Node<T> cur;
		private LinkedList<T> list;
		public LinkedListIterator(Node<T> dummy)
		{
			this.cur =  dummy;
			this.list = dummy.list;			
		}
		
		@Override
		public boolean hasNext() {
			if(cur.next.dummy)
			{
				return false;
			}else
			{
				return true;
			}
		}

		@Override
		public T next() {
			if(hasNext())
			{
				cur = cur.next;		
				return cur.value;
			}else
			{
				throw new NoSuchElementException("End of list");
			}
			
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("No Remove availble");
		}
		
	}
	private class Node<T>{
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
		if(index > size)
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
			size++;
			return newNode;
		}
	}
	
	public Node<E> getNode(Node<E> cur, int index)
	{

		//check to make sure that the beginning or the end of the list has not been reached
		if(cur.dummy)
		{
			throw new NoSuchElementException();
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
			this.size--;
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
		this.dummy = new Node<E>(null,this, true);
		this.dummy.next = this.dummy;
		this.dummy.last = this.dummy;
		addAll(0, c);
	}
	@Override
	public void add(int index, E item) {
		addNode(dummy,index, new Node<E>(item,this, false));
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
		//until the current item is the dummy node
		while(curItem.dummy == false )
		{
			//save the current node
			last = curItem;
			//incerment the curNode
			//search from cur node on 
			curItem = findNode(curItem.next, itemCast);
			
			
		}
		return getNodeIndex(last);
	}

	@Override
	public ListIterator<E> listIterator() {
		LinkedListIterator<E> iterator = new LinkedListIterator<E>(this.dummy);
		return (ListIterator<E>) iterator;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) {
		try {
			Node<E> node = getNode(this.dummy.next, index);
			E val = node.value;
			removeNode(node);
			return val;
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
	
			throw new NoSuchElementException();
		}
	}

	@Override
	public E set(int index, E value) {
		getNode(this.dummy.next,index).value = value;
		return value;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		//TODO: figure out how to implement a sub list
		return null;
	}

	@Override
	public void addFirst(E value) {
		addNode(this.dummy,0,new Node<E>(value,this,false));
	}

	@Override
	public void addLast(E item) {
		addNode(this.dummy, size, new Node<E>(item,this, false));
	}

	@Override
	public Iterator<E> descendingIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public E element() {
		return getFirst();
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
	public boolean offer(E value) {
		addLast(value);
		return true;
	}

	@Override
	public boolean offerFirst(E value) {
		addFirst(value);
		return true;
	}

	@Override
	public boolean offerLast(E value) {
		addLast(value);
		return true;
	}

	@Override
	public E peek() {
		return getFirst();
	}

	@Override
	public E peekFirst() {
		if(size() == 0)
		{
			return null;
		}else
		{
			E value = getFirst();
			return value;
		}
	}

	@Override
	public E peekLast() {
		if(size() == 0)
		{
			return null;
		}else
		{
			E value = getLast();
			return value;
		}
	}

	@Override
	public E poll() {
		if(size() == 0)
		{
			return null;
		}

		E value = getFirst();
		removeFirst();
		return value;
		
	}

	@Override
	public E pollFirst() {
		return poll();
	}

	@Override
	public E pollLast() {

		if(size() == 0)
		{
			return null;
		}
		E value = getLast();
		removeLast();
		return value;
	}

	@Override
	public E pop() {
		return poll();
	}

	@Override
	public void push(E value) {
		addFirst(value);
	}

	@Override
	public E remove() {
		E val = getFirst();
		removeFirst();
		return val;
	}

	@Override
	public E removeFirst() {
		E value = getNode(this.dummy.next, 0).value;
		remove(0);
		return value;
	}

	@Override
	public boolean removeFirstOccurrence(Object value) {
		int index =indexOf(value);
		if(index != -1)
		{
			remove(index);
			return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public E removeLast() {
		E val = getLast();
		remove(size-1);
		return val;
	}

	@Override
	public boolean removeLastOccurrence(Object value) {
		int index = lastIndexOf(value);
		if(index != -1)
		{
			remove(index);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> values) {
		return addAll(0, values); 
	}

	@Override
	public void clear() {
		this.dummy.next = this.dummy;
		this.dummy.last = this.dummy;
		this.size = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object value) {
		if(findNode(this.dummy.next, (E)value).dummy != true)
		{
			return true;
		}
		else
		{
			return false;	
		}
	}

	@Override
	public boolean containsAll(Collection<?> values) {
		for(Object o : values){
			if(contains(o) != true)
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		
		return this.size == 0;
	}

	@Override
	public boolean remove(Object value) {
		int index = indexOf(value);
		if(index == -1)
		{
			return false;
		}
		else
		{
			remove(index);
			return true;
		}
	}

	@Override
	public boolean removeAll(Collection<?> values) {
		boolean returnVal = true;
		for(Object o: values)
		{
			if(!remove(o))
			{
				returnVal = false;
			}
		}
		return returnVal;
	}

	@Override
	public boolean retainAll(Collection<?> values) {
		boolean returnVal = false;
		for(E val: this)
		{
			//if the value is not in the retain list
			if(values.contains(val) == false)
			{
				remove(val);
				returnVal = true;
			}
		}
		return returnVal;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] vals = new Object[size];
		int i = 0;
		for(E val: this)
		{
			vals[i]	= val;
			i++;
		}
		return vals;
	}

	@SuppressWarnings("hiding")
	@Override
	public <E> E[] toArray(E[] arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<E>(this.dummy);
	}
	
	@Override
	public String toString(){
		String out = "";
		for(E val: this)
		{
			out += val.toString()+ "\n";
		}
		return out;
	}
	
	@Override
	public int hashCode(){
		int hashCode = 0;
		for(E val: this)
		{
			hashCode+= val.hashCode();
		}
		return hashCode;
	}
	
	@Override
	public boolean equals(Object o){
		try
		{
			LinkedList listCast = (LinkedList)o;
			return containsAll(listCast);
		}
		catch(Exception e)
		{
			return false;
		}
	}
}

