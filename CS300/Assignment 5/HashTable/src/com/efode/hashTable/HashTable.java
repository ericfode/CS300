//Author: Eric Fode
//Email : Ericfode@gmail.com

package com.efode.hashTable;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;


public class HashTable {
	private int numOfItems;
	private int collisions = 0;
	private ArrayList<KeyValue>[] table;
	private int tableSize;
	
	@SuppressWarnings("unchecked")
	public HashTable(Collection<KeyValue> items)
	{
		numOfItems = items.size();
		tableSize = findTableSize(numOfItems*2+1);
		table = (ArrayList<KeyValue>[]) Array.newInstance(ArrayList.class, tableSize);
		for(KeyValue p : items)
		{
			if(add(p)){collisions++;};
		}
	}
	
	private int findTableSize(int numOfItems)
	{
		return computePrime(numOfItems); 
	}
	
	private boolean add(KeyValue item)
	{
		if(item == null)
		{
			return false;
		}
		boolean col =true;
		int loc = (int) (item.keyHashCode()%tableSize);
		if(table[loc] == null)
		{
			col = false;
			table[loc] = new ArrayList<KeyValue>();
		}
		table[loc].add(item);
		return col;
	}
	
	public ArrayList<KeyValue> lookupKey(String key)
	{
		ArrayList<KeyValue> kvl = table[(int) (StringHasher.hash(key) % tableSize)];
		kvl = filterKeys(kvl,key);
		return kvl;
	}
	
	private ArrayList<KeyValue> filterKeys(ArrayList<KeyValue> list,String key)
	{
		if(list == null)
		{
			return new ArrayList<KeyValue>();
		}
		for(Iterator<KeyValue> it = list.iterator(); it.hasNext();)
		{
			KeyValue kv = it.next();
			if(!kv.getKey().equals(key))
			{
				it.remove();
			}
		}
		return list;
	}
	
	int computePrime(int n)
	{
		LinkedList<Integer> ints = new LinkedList<Integer>();
		LinkedList<Integer> primes = new LinkedList<Integer>();
		for(int i = 2; i < n; i++)
		{
			ints.add(i);
		}
		Integer p =0;
		do{
			p = ints.poll();
			primes.add(p);
			
			for(Iterator<Integer> it = ints.iterator(); it.hasNext();)
			{
				Integer curInt = it.next(); 
				if(curInt%p == 0)
				{
					it.remove();
				}
			}
		}while(p < Math.sqrt(n));
		return ints.getLast();
	}

	public int getCollisions()
	{
		return collisions;
	}
	
}
