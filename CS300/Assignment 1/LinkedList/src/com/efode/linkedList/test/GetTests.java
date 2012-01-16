package com.efode.linkedList.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.efode.linkedList.LinkedList;

public class GetTests {


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
	public void testGet() {
		assertEquals("Testing Get","One",strings.get(0));
	}

	@Test
	public void testIndexOf() {
		assertEquals("Testing get Index on Strings", 2,strings.indexOf("Three"));
	}

	@Test
	public void testLastIndexOf() {
		strings.add("Three");
		strings.add("Four");
		assertEquals("Testing lastIndexOf", 3, strings.lastIndexOf("Three"));
	}

	@Test
	public void testListIterator() {
		assertNotNull(strings.iterator());
		Iterator<String> si = strings.iterator();
		assertEquals("Testing iterator value","One",si.next());
		assertEquals("Testing iterator value 2","Two",si.next());
	}



	@Test
	public void testGetFirst() {
		strings.add("Three");
		strings.add("Four");
		strings.add("Three");
		assertEquals("TestingGetFirst","One",strings.getFirst());
		
	}

	@Test
	public void testGetLast() {
		strings.add("Three");
		strings.add("Four");
		strings.add("Five");
		assertEquals("TestingGetFirst","Five",strings.getLast());
	}

	@Test
	public void testOffer() {
		assertEquals("Using Offer",true,strings.offer("Four"));
		assertEquals("Getting last element","Four", strings.getLast());
		assertEquals("Using Offer again",true,strings.offer("Five"));
		assertEquals("Getting last element","Five", strings.getLast());
		
	}

	@Test
	public void testOfferFirst() {
		assertEquals("Using Offer",true,strings.offerFirst("Four"));
		assertEquals("Getting last element","Four", strings.getFirst());
		assertEquals("Using Offer again",true,strings.offerFirst("Five"));
		assertEquals("Getting last element","Five", strings.getFirst());
	}

	@Test
	public void testOfferLast() {
		assertEquals("Using Offer",true,strings.offerLast("Four"));
		assertEquals("Getting last element","Four", strings.getLast());
		assertEquals("Using Offer again",true,strings.offerLast("Five"));
		assertEquals("Getting last element","Five", strings.getLast());
	}

	@Test
	public void testContains() {
		assertTrue("testing Contains", strings.contains("One"));
		assertTrue("testing Contains", strings.contains("Two"));
		assertTrue("testing Contains", strings.contains("Three"));
		
	}

	@Test
	public void testContainsAll() {
		assertTrue("Testing Contains all",strings.containsAll(Arrays.asList(expected)));
	}

	@Test
	public void testIsEmpty() {
		assertFalse("Testing empty", strings.isEmpty());
		strings.clear();
		assertTrue("Testing empty with empty list", strings.isEmpty());
	}

	@Test
	public void testToArray() {
		Object[] stringsOut =  strings.toArray();
		for(int i = 0; i< stringsOut.length; i++)
		{
			assertEquals(stringsOut[i], strings.get(i));
		}
	}

}
