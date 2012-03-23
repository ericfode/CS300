package com.efode.hashTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args)
	{
		String[] dict = getStrArray("words.txt");
		AnagramFinder af = new AnagramFinder(Arrays.asList(dict));
		String[] words = getStrArray(args[0]);
		for(String s : words)
		{
			af.PrintAnagrams(s);
		}
		//FileOutputStream output = new FileOutputStream("output_file.txt");

	}
	
	private static String[] getStrArray(String file)
	{
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] buffer  = new byte[(int) new File(file).length()];
			
				in.read(buffer);
	
			String s = new String(buffer);
			return s.split("\\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
