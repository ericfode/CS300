package efode.recursion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Tester {
	public static void main(String args[])
	{
		Parser parser = new Parser();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt")));
			String str = "";
			try {
				str = reader.readLine();
				while(str != null)
				{
					if(parser.Parse(str))
					{
						System.out.println(str + " is Valid");
					}
					else
					{
						System.out.println(str + " is NOT Valid");
					}
					str = reader.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
