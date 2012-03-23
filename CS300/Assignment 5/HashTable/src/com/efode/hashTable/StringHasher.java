//Author: Public Domain
//Source: http://www.cse.yorku.ca/~oz/hash.html
//Name: sdbm Hash function

package com.efode.hashTable;

public class StringHasher {
   public static long hash(String s)
   {
	   
	   long hash = 0;
	   
	   for(char c : s.toCharArray())
	   {
		   hash = c + (hash << 6)  + (hash << 16) - hash;
	   }
	   return Math.abs(hash);
   }
}
