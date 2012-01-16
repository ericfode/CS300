package com.efode.linkedList.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.efode.linkedList.LinkedList;

public class RemoveTests {

	public static ArrayList<String> testStrings= new ArrayList<String>(Arrays.asList("One","Two","Three"));
	public static ArrayList<Integer> testInts = new ArrayList<Integer>(Arrays.asList(1,2,3));
	public static String[] expected = {"One", "Two", "Three"};
	public static int[] expectedInts = {1,2,3};
	
	public LinkedList<String> strings;
	public LinkedList<Integer> ints;
	@Before
	public void setUp() throws Exception {
		strings = new LinkedList<String>(testStrings);
		ints = new LinkedList<Integer>(testInts);
	}

	

	@Test
	public void testRemoveStringLocation() {
		assertEquals("Testing remove at","Two",strings.remove(1));
		String[] expected = {"One","Three"};
		assertArrayEquals("Testing remove result", expected,strings.toArray());
	}

	@Test
	public void testPeek() {
		assertEquals("Testing peek", "One", strings.peek());
		assertEquals("Testing peek again", "One", strings.peek());
		assertArrayEquals("Testing Peek Result",expected, strings.toArray());
	}

	@Test
	public void testPeekFirst() {
		assertEquals("Testing peek", "One", strings.peekFirst());
		assertEquals("Testing peek again", "One", strings.peekFirst());
		assertArrayEquals("Testing Peek Result",expected, strings.toArray());
	}

	@Test
	public void testPeekLast() {
		assertEquals("Testing peek", "Three", strings.peekLast());
		assertEquals("Testing peek again", "Three", strings.peekLast());
		assertArrayEquals("Testing Peek Result",expected, strings.toArray());
	}

	@Test
	public void testPoll() {
		assertEquals("Testing Poll","One", strings.poll());
		assertEquals("Testing poll again", "Two", strings.poll());
		assertEquals("Testing poll again", "Three", strings.poll());
		assertEquals("Testing poll again", null, strings.poll());
	}

	@Test
	public void testPollFirst() {
		assertEquals("Testing PollFirst","One", strings.pollFirst());
		assertEquals("Testing pollFirst again", "Two", strings.pollFirst());
		assertEquals("Testing pollFirst again", "Three", strings.pollFirst());
		assertEquals("Testing pollFirst again", null, strings.pollFirst());
	}

	@Test
	public void testPollLast() {
		assertEquals("Testing PollLast","Three", strings.pollLast());
		assertEquals("Testing pollLast again", "Two", strings.pollLast());
		assertEquals("Testing pollLast again", "One", strings.pollLast());
		assertEquals("Testing pollLast again", null, strings.pollLast());
	}

	@Test
	public void testPop() {

		assertEquals("Testing pop","One", strings.pop());
		assertEquals("Testing pop again", "Two", strings.pop());
		assertEquals("Testing pop again", "Three", strings.pop());
		assertEquals("Testing pop again", null, strings.pop());
	}

	@Test
	public void testRemove() {
		assertEquals("Testing remove","One", strings.remove());
		assertEquals("Testing remove","Two", strings.remove());
		assertEquals("Testing remove","Three", strings.remove());
		try
		{
			strings.remove();
			assert(false);
		}
		catch(NoSuchElementException e)
		{
			assert(true);
		}
		
	}

	@Test
	public void testRemoveFirst() {
		assertEquals("Testing removeFirst","One", strings.removeFirst());
		assertEquals("Testing removeFirst","Two", strings.removeFirst());
		assertEquals("Testing removeFirst","Three", strings.removeFirst());
		try
		{
			strings.remove();
			assert(false);
		}
		catch(NoSuchElementException e)
		{
			assert(true);
		}
	}

	@Test
	public void testRemoveFirstOccurrence() {
		strings.add("Four");
		strings.add("Three");
		assertEquals("testing RemoveFirstOccurrence",true, strings.removeFirstOccurrence("Three"));
		assertEquals("testing RemoveFirstOccurrence",false, strings.removeFirstOccurrence("Five"));
		String[] expected = {"One", "Two","Four","Three"};
		assertArrayEquals("Testing Result",expected, strings.toArray() );
		
	}

	@Test
	public void testRemoveLast() {
		assertEquals("testing removeLast","Three",strings.removeLast());
		assertEquals("testing removeLast","Two",strings.removeLast());
		assertEquals("testing removeLast","One",strings.removeLast());

		try
		{
			assertEquals("testing removeLast","Three",strings.removeLast());
			assert(false);
		}catch(NoSuchElementException e )
		{
			assert(true);
		}
		
	}

	@Test
	public void testRemoveLastOccurrence() {
		strings.add("Four");
		strings.add("Three");
		assertEquals("testing RemoveLastOccurrence",true, strings.removeLastOccurrence("Three"));
		assertEquals("testing RemoveLastOccurrence",false, strings.removeLastOccurrence("Five"));
		String[] expected = {"One", "Two", "Three","Four"};
		assertArrayEquals("Testing removeLastOccurrence result",expected, strings.toArray());
	}

	@Test
	public void testClear() {
		strings.clear();
		assertEquals("Testing clear",0,strings.size());
	}

	@Test
	public void testRemoveObject() {
		assertTrue("testing RemoveObject",strings.remove("Three"));
		assertFalse("Testing removeObject",strings.remove("Five"));
		String[] expected = {"One", "Two"};
		assertArrayEquals("Testing removeObject result",expected, strings.toArray());
	}

	@Test
	public void testRemoveAll() {
		strings.add("Three");
		List<String> remove = Arrays.asList("One","Three");
		assertTrue("Testing removeAll",strings.removeAll(remove));
		assertFalse("Testing removeAll",strings.removeAll(remove));
		String[] expected = {"Two"};
		assertArrayEquals(expected, strings.toArray());
	}

	@Test
	public void testRetainAll() {
		strings.add("Three");
		strings.add("Four");
		List<String> retain = Arrays.asList("Three", "Four");
		assertTrue("Testing retainAll", strings.retainAll(retain));
		assertFalse("Testing retainAll", strings.retainAll(retain));
		String[] expected = {"Three", "Three","Four"};
		assertArrayEquals("Testing retain result",expected, strings.toArray());
	}

}
