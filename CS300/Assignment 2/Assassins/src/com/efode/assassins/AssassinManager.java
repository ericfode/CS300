package com.efode.assassins;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class AssassinManager.
 */
public class AssassinManager {
	
	/**
	 * The Class ANode.
	 */
	private class ANode{
		
		/** The next. */
		public ANode next;
		
		/** The name. */
		public String name;
		
		/** The killed by. */
		public String killedBy;
		
		/**
		 * Instantiates a new a node.
		 *
		 * @param name the name of the person contained by the node
		 */
		public ANode(String name)
		{
			this.name = name;
			next = null;
			killedBy = null;
		}
	}
	
	/** The kill ring. */
	private ANode killRing;
	
	/** The graveyard. */
	private ANode graveyard;
	
	/**
	 * End.
	 *
	 * @param the list to find the end of
	 * @return the node at the end of the list
	 */
	private ANode end(ANode cur)
	{
		if(cur.next == null)
		{
			return cur;
		} 
		else
		{
			return end(cur.next);
		}
	}
	
	/**
	 * Count.
	 *
	 * @param the list to find the count of 
	 * @return the count of the items in the list
	 */
	private int count(ANode cur)
	{
		if(cur.next == null)
		{
			return 1;
		}
		else
		{
			return count(cur.next) + 1;
		}
	}
	
	/**
	 * Instantiates a new assassin manager.
	 *
	 * @param names the names to be put in the game
	 */
	public AssassinManager(List<String> names){
		killRing = new ANode(names.get(0));
		for(int i = 1; i < names.size(); i++)
		{
			end(killRing).next = new ANode(names.get(i));
		}
		graveyard = null;
	}
	
	/**
	 * Check for game over
	 *
	 * @return true, if game is over
	 */
	public boolean gameOver() {
		if(count(killRing) == 1)
		{
			return true;
		}
		return false;
	}

	/**
	 * return the name of the winner or null if the game is not over
	 *
	 * @return the name of the winner
	 */
	public String winner() {
		if(gameOver())
		{
			return killRing.name;
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * Prints the graveyard.
	 */
	public void printGraveyard() {
		ANode cur;
		for(cur = graveyard; cur != null; cur = cur.next)
		{
			System.out.println("    " + cur.name + " was killed by " + cur.killedBy);
		}
	}

	/**
	 * Prints the kill ring.
	 */
	public void printKillRing() {
		ANode cur;
		for(cur = killRing; cur !=null; cur = cur.next)
		{
			if(cur.next == null)
			{
				System.out.println("     " + cur.name + " is stalking " + killRing.name);
			}
			else
			{
				System.out.println("     " + cur.name + " is stalking " + cur.next.name);
			}
		}
	}

	/**
	 * check graveyard for name
	 *
	 * @param name the name to check for
	 * @return true if the graveyard contains the name else null
	 */
	public boolean graveyardContains(String name) {
		ANode cur;
		for(cur = graveyard; cur != null; cur = cur.next)
		{
			if(cur.name.equalsIgnoreCase(name))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * check KillRing for name
	 *
	 * @param name the name to check for
	 * @return true if the killRing contains the name, else null
	 */
	public boolean killRingContains(String name) {
		ANode cur;
		for(cur = killRing; cur != null; cur = cur.next)
		{
			if(cur.name.equalsIgnoreCase(name))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Kill a player
	 *
	 * @param name the name of player to kill
	 */
	public void kill(String name) {
		ANode cur,prev;
		
		if(killRingContains(name) != true)
		{
			throw new IllegalArgumentException(name + " does not exsist");
		}
		
		for(cur = killRing, prev = end(killRing); cur.name.equalsIgnoreCase(name) != true;prev = cur, cur = cur.next);
		cur.killedBy = prev.name;
		//if any one but the first person was killed
		if(prev.next != null)
		{
			//reassign the target of the killer to the next person in the list
			prev.next = cur.next;
		}
		//if the first person was killed
		else
		{
			//point the kill ring at the new begining of the list
			killRing = cur.next;
		}
			
			cur.next = graveyard;
			graveyard = cur;
		
	}
}
