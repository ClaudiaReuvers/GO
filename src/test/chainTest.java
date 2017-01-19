package test;

import game.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class chainTest {

	private Chain chain;
	private secondStone stone1;
	private secondStone stone2;
	
	@Before
	public void setUp() {
		chain = new Chain();
		stone1 = new secondStone(1,1,4);
		stone1.setColor(true);
		stone1.addNeighbours(new secondStone(0,1,3));
		stone1.addNeighbours(new secondStone(1,0,3));
		stone1.addNeighbours(new secondStone(2,1,4));
		stone2 = new secondStone(1,2,4);
		stone1.addNeighbours(stone2);
//		stone2.setColor(true);
		stone2.addNeighbours(stone1);
		stone2.addNeighbours(new secondStone(2,2,4));
		stone2.addNeighbours(new secondStone(1,3,4));
		stone2.addNeighbours(new secondStone(0,2,3));
	}
	
	@Test
	public void testAdd() {
		chain.add(stone1);
		assertEquals(1, chain.getLength());
		assertEquals(stone1.getChain(), chain);
		assertEquals(4, chain.getLibertyChain());
		assertEquals(4, chain.getAdjacentStones().size());
		stone2.setColor(true);
		chain.add(stone2);
		assertEquals(2, chain.getLength());
		assertEquals(stone2.getChain(), chain);
		assertEquals(6, chain.getLibertyChain());
	}
	
	@Test
	public void testJoin() {
		Chain chain2 = new Chain();
		chain.add(stone1);
		chain2.add(stone2);
		chain.join(stone2);
		assertEquals(chain, stone1.getChain());
		assertEquals(chain, stone2.getChain());
	}
}
