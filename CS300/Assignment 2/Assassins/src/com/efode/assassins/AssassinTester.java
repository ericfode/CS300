/*
 * 
 */
package com.efode.assassins;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


// TODO: Auto-generated Javadoc
/**
 * The Class AssassinTester.
 */
public class AssassinTester {

	/** The as. */
	AssassinManager as;
	
	/** The init names. */
	List<String> initNames = Arrays.asList("Bob Jones", "Bill Cosby", "Sue Janes", "Tom Perry");
	
	/** The kill order. */
	List<String> killOrder = Arrays.asList("Bill Cosby", "Sue Janes","Bob Jones");
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		as = new AssassinManager(initNames);
	}

	/**
	 * Test setup.
	 */
	@Test

	public void testSetup(){
		for(String s: killOrder)
		{
			assertTrue(as.killRingContains(s));
		}
		for(String s: killOrder)
		{
			assertFalse(as.graveyardContains(s));
		}
		assertFalse(as.gameOver());
	}
	
	
	/**
	 * Test kill front.
	 */
	@Test
	public void testKillFront()
	{
		as.kill(initNames.get(0));
		assertFalse(as.killRingContains(initNames.get(0)));
		assertTrue(as.graveyardContains(initNames.get(0)));
	}
	
	/**
	 * Test kill back.
	 */
	@Test
	public void testKillBack()
	{
		as.kill(initNames.get(initNames.size()-1));
		assertFalse(as.killRingContains(initNames.get(initNames.size()-1)));
		assertTrue(as.graveyardContains(initNames.get(initNames.size()-1)));
	}
	
	/**
	 * Test kill mid.
	 */
	@Test
	public void testKillMid()
	{

		as.kill(killOrder.get(0));
		assertFalse(as.killRingContains(killOrder.get(0)));
		assertTrue(as.graveyardContains(killOrder.get(0)));
	}
	
	/**
	 * Test kill multiple.
	 */
	@Test
	public void testKillMultiple()
	{
		for(String s: killOrder)
		{
			as.kill(s);
			assertFalse(as.killRingContains(s));
			assertTrue(as.graveyardContains(s));
		}
	}
	

	/**
	 * Test end game.
	 */
	@Test
	public void testEndGame()
	{
		for(String s: killOrder)
		{
			as.kill(s);
			assertFalse(as.killRingContains(s));
			assertTrue(as.graveyardContains(s));
		}
		
		assertTrue(as.gameOver());
		assertEquals("Tom Perry", as.winner());
	}
	
	
}
