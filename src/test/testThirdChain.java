package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.thirdStone;
import game.thirdChain;

import org.junit.Before;

public class testThirdChain {

	private thirdStone stone1;
	private thirdStone stone2;
	private thirdChain chain;
	
	@Before
	public void setUp() {
		stone1 = new thirdStone();
		stone1.addNeighbour(new thirdStone());
		stone1.addNeighbour(new thirdStone());
		stone1.addNeighbour(new thirdStone());
		stone1.addNeighbour(new thirdStone());
		stone2 = new thirdStone();
		stone2.addNeighbour(new thirdStone());
		stone2.addNeighbour(new thirdStone());
		stone2.addNeighbour(new thirdStone());
		stone2.addNeighbour(new thirdStone());
		chain = new thirdChain();
	}
	
	@Test
	public void testSetUp() {
		assertTrue(chain.getChain().isEmpty());
//		assertTrue(chain.getEmptyStones().isEmpty());
	}
	
	@Test
	public void testAddStone() {
		chain.addStone(stone1);
		assertEquals(stone1, chain.getChain().get(0));
		assertEquals(chain, stone1.getChain());
		assertEquals(4, chain.calculateLibertyChain());
		chain.addStone(stone2);
		assertEquals(stone2, chain.getChain().get(1));
		assertEquals(chain, stone2.getChain());
		assertEquals(chain, stone1.getChain());
		assertEquals(8, chain.calculateLibertyChain());
	}
	
	@Test
	public void testGetLibertiesIf() {
		thirdStone stone3 = new thirdStone();
		stone3.addNeighbour(new thirdStone());
		stone3.addNeighbour(new thirdStone());
		stone3.addNeighbour(new thirdStone());
		thirdStone stone4 = new thirdStone();
		stone4.setColor(true);
		stone3.addNeighbour(stone4);
		chain.addStone(stone3);
		assertEquals(3, chain.calculateLibertyChain());
		chain.addStone(stone2);
		assertEquals(7, chain.calculateLibertyChain());
	}

}
