package com.efode.hashTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;

public class AnagramFinder {
	
	private HashTable lookup;
	
	public AnagramFinder(Collection<String> dict)
	{
		ArrayList<KeyValue> kvs = new ArrayList<KeyValue>();
		for(String s: dict)
		{
			kvs.add(new KeyValue(sortString(s), s));
		}
		lookup = new HashTable(kvs);
	}
	
	private String sortString(String s)
	{
		char[] sortedChars = s.toCharArray();
		Arrays.sort(sortedChars);
		return new String(sortedChars);
	}
	
	public void PrintAnagrams(String s)
	{
		ArrayList<KeyValue> results = lookup.lookupKey(sortString(s));
		System.out.print(s + " " + results.size() + " ");
		for(KeyValue kv : results)
		{
			System.out.print(kv.getValue() + " ");
		}
		System.out.println();
	}
}

