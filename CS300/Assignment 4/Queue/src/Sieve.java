import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
//This class uses the Sieve of Eratosthenes to compute all of the prime up to a max number passed in by computeTo
//Author: Eric Fode (ericfode@gmail.com)
public class Sieve {
	
	private Queue<Integer> ints;
	private Queue<Integer> primes;
	private Boolean calc = false;
	private int max;
	

	Sieve()
	{
		ints = (Queue<Integer>) new LinkedList<Integer>();
		primes = (Queue<Integer>) new LinkedList<Integer>();
	}
	
	void computeTo(int n)
	{
		max = n;
		calc = true;
		ints.clear();
		primes.clear();
		
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
		primes.addAll(ints);
	}
	
	void reportResults()
	{
		if(!calc)
		{
			throw new IllegalStateException();
		}
		for(Integer i : primes)
		{
			System.out.print(i.toString() + " " );
		}
		System.out.print("\n");
	}
	
	int getMax()
	{
		if(!calc)
		{
			throw new IllegalStateException();
		}
		return max;
		
	}
	
	int getCount()
	{
		if(!calc)
		{
			throw new IllegalStateException();
		}
		return primes.size();
	}
}
