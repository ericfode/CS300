package com.efode.linkedList.test;

import static org.junit.Assert.*;

import org.junit.Test;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import com.efode.linkedList.LinkedList;

public class AddTests {

	public static ArrayList<String> testStrings= new ArrayList<String>(Arrays.asList("One","Two","Three"));
	public static ArrayList<Integer> testInts = new ArrayList<Integer>(Arrays.asList(1,2,3));
	public static String[] expected = {"One", "Two", "Three"};
	public static int[] expectedInts = {1,2,3};
	
	@Test
	public void testAddNode() {
		LinkedList<String> strings = new LinkedList<String>(testStrings);
		
		assertArrayEquals("Testing basic add and to array",expected, strings.toArray());
	}

	@Test
	public void testAddIntE() {
		LinkedList<Integer> ints = new LinkedList<Integer>(testInts);
		ints.add(4);
		Object[] expected = {1,2,3,4};
		assertArrayEquals("Testing add item", expected , ints.toArray());
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		LinkedList<String> strings = new LinkedList<String>(testStrings);
		strings.addAll(testStrings);
		String[] expected = {"One", "Two", "Three","One", "Two", "Three"};
		assertArrayEquals("Testing add collection", expected, strings.toArray());
	}

	@Test
	public void testAddLast() {
		LinkedList<String> strings = new LinkedList<String>(testStrings);
		strings.addLast("Four");
		String[] expected = {"One", "Two", "Three", "Four"};
		assertArrayEquals("Testing addLast", expected, strings.toArray());
	}
	

	@Test
	public void testPush() {
		LinkedList<String> strings = new LinkedList<String>(testStrings);
		strings.push("Four");
		String[] expected = {"One", "Two", "Three", "Four"};
		assertArrayEquals("Testing addLast", expected, strings.toArray());
	}

	@Test
	public void testAddE() {
		LinkedList<String> strings = new LinkedList<String>(testStrings);
		strings.add("Four");
		String[] expected = {"One", "Two", "Three", "Four"};
		assertArrayEquals("Testing addLast", expected, strings.toArray());
	}



}
