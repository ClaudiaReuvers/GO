package test;

import game.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class chainTest {

	private Chain chain;
	private Stone stone1;
	private Stone stone2;
	
	@Before
	public void setUp() {
		chain = new Chain();
		stone1 = new Stone(1,1);
		stone1.setColor(true);
		stone1.setLiberty(3);
		stone2 = new Stone(1,2);
		stone2.setColor(true);
		stone2.setLiberty(3);
	}
	
	@Test
	public void testAdd() {
		chain.add(stone1);
		assertEquals(1, chain.getLength());
		assertEquals(stone1.getChain(), chain);
		assertEquals(3, chain.getLibertyChain());
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
		chain.join(chain2);
		assertEquals(stone1.getChain(), chain);
	}
}
