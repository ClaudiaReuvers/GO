package test;

import game.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class stoneTest {

	private Stone stone;
	
	@Before
	public void setUp() {
		stone = new Stone();
	}
	
	@Test
	public void testSetUp() {
		assertEquals(StoneColor.EMPTY, stone.getColor());
		assertTrue(stone.isEmpty());
		Stone stone2 = new Stone(true);
		assertEquals(StoneColor.WHITE, stone2.getColor());
		assertFalse(stone2.isEmpty());
		assertTrue(stone2.getBooleanColor());
		Stone stone3 = new Stone(false);
		assertEquals(StoneColor.BLACK, stone3.getColor());
		assertFalse(stone3.isEmpty());
		assertFalse(stone3.getBooleanColor());
	}
	
	@Test
	public void testSetColor() {
		stone.setColor(true);
		assertEquals(StoneColor.WHITE, stone.getColor());
		stone.setColor(false);
		assertEquals(StoneColor.BLACK, stone.getColor());
	}
	
	@Test
	public void testLiberty() {
		stone.setLiberty(1);
		assertEquals(1, stone.getLiberty());
		assertFalse(stone.hasNoLiberty());
		stone.decreaseLibertyByOne();
		assertEquals(0, stone.getLiberty());
		assertTrue(stone.hasNoLiberty());
	}
	
}
