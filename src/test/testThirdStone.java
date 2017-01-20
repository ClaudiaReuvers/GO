package test;

import game.thirdStone;
import game.StoneColor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class testThirdStone {

	private thirdStone stone1;
	private thirdStone stone2;
	
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
	}
	
	@Test
	public void testSetUp() {
		assertEquals(StoneColor.EMPTY, stone1.getState());
		assertTrue(stone1.isEmpty());
		assertTrue(stone1.getChain().getChain().isEmpty());
		assertEquals(4, stone1.getNeighbour().size());
	}
	
	@Test
	public void testAddThisToChain() {
		stone1.addThisToChain();
		assertEquals(stone1, stone1.getChain().getChain().get(0));
	}
	
	@Test
	public void testSetColor() {
		stone1.setColor(true);
		assertEquals(StoneColor.WHITE, stone1.getState());
		assertFalse(stone1.isEmpty());
		stone1.setColor(false);
		assertEquals(StoneColor.BLACK, stone1.getState());
		assertFalse(stone1.isEmpty());
	}
}
