package test;

import game.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class boardTest {
	
	private trySomething board9;

	@Before
    public void setUp() {
    	board9 = new trySomething(9);
    }
	
    @Test
    public void testSetUp() {
    	trySomething board13 = new trySomething(13);
    	assertEquals(9, board9.getBoardSize());
    	assertEquals(13, board13.getBoardSize());
    	assertEquals(StoneColor.EMPTY, board9.getField(0, 0).getColor());
    	assertEquals(StoneColor.EMPTY, board9.getField(8, 8).getColor());
    	assertEquals(StoneColor.EMPTY, board13.getField(0, 0).getColor());
    	assertEquals(StoneColor.EMPTY, board13.getField(12, 12).getColor());
    }
    
    @Test
    public void testSetAndGetOneStone() {
    	board9.addStone(1, 1, true);
    	assertFalse(board9.getField(1, 1).isEmpty());
    	assertEquals(StoneColor.WHITE, board9.getField(1, 1).getColor());
    	assertEquals(board9.getField(1, 1).getLiberty(), 4);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 1);
    	board9.addStone(8, 7, false);
    	assertEquals(board9.getField(8, 7).getColor(), StoneColor.BLACK);
    	assertEquals(board9.getField(8, 7).getLiberty(), 3);
    	assertEquals(board9.getField(8, 7).getChain().getLength(), 1);
    	board9.addStone(0, 0, true);
    	assertEquals(board9.getField(0, 0).getLiberty(), 2);
    	assertEquals(board9.getField(0, 0).getChain().getLength(), 1);
    }
    
    @Test
    public void testTwoStonesOtherColor() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, false);
    	assertEquals(board9.getField(1, 1).getLiberty(), 3);
    	assertEquals(board9.getField(1, 2).getLiberty(), 3);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 1);
    	assertEquals(board9.getField(1, 2).getChain().getLength(), 1);
    	board9.addStone(1, 0, false);
    	assertEquals(board9.getField(1, 0).getLiberty(), 2);
    	assertEquals(board9.getField(1, 1).getLiberty(), 2);
    	board9.addStone(0, 0, true);
    	assertEquals(board9.getField(0, 0).getLiberty(), 1);
    	assertEquals(board9.getField(1, 0).getLiberty(), 1);
    }
    
    @Test
    public void testTwoStonesSameColor() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, true);
    	assertEquals(board9.getField(1, 1).getLiberty(), 6);
    	assertEquals(board9.getField(1, 2).getLiberty(), 6);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 2);
    	assertEquals(board9.getField(1, 2).getChain().getLength(), 2);
    	board9.addStone(2, 2, false);
    	assertEquals(board9.getField(1, 1).getLiberty(), 5);
    	assertEquals(board9.getField(1, 2).getLiberty(), 5);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 2);
    	assertEquals(board9.getField(1, 2).getChain().getLength(), 2);
    }
    
    @Test
    public void testStraightChainOfThree() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, true);
    	board9.addStone(1, 3, true);
    	assertEquals(board9.getField(1, 1).getLiberty(), 8);
    	assertEquals(board9.getField(1, 2).getLiberty(), 8);
    	assertEquals(board9.getField(1, 3).getLiberty(), 8);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 3);
    	assertEquals(board9.getField(1, 2).getChain().getLength(), 3);
    	assertEquals(board9.getField(1, 3).getChain().getLength(), 3);
    }
    
    @Test
    public void testBendChainOfThree() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, true);
    	board9.addStone(2, 2, true);
    	assertEquals(board9.getField(1, 1).getLiberty(), 7);
    	assertEquals(board9.getField(1, 2).getLiberty(), 7);
    	assertEquals(board9.getField(2, 2).getLiberty(), 7);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 3);
    	assertEquals(board9.getField(1, 2).getChain().getLength(), 3);
    	assertEquals(board9.getField(2, 2).getChain().getLength(), 3);
    }
    
    @Test
    public void testBlockChain() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, true);
    	board9.addStone(2, 2, true);
    	board9.addStone(2, 1, true);
    	assertEquals(board9.getField(1, 1).getLiberty(), 8);
    	assertEquals(board9.getField(1, 2).getLiberty(), 8);
    	assertEquals(board9.getField(2, 2).getLiberty(), 8);
    	assertEquals(board9.getField(2, 1).getLiberty(), 8);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 4);
    	assertEquals(board9.getField(1, 2).getChain().getLength(), 4);
    	assertEquals(board9.getField(2, 2).getChain().getLength(), 4);
    	assertEquals(board9.getField(2, 1).getChain().getLength(), 4);
    }
    
    @Test
    public void testJoinTwoStraight() {
    	board9.addStone(1, 0, true);
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 3, true);
    	board9.addStone(1, 4, true);
    	board9.addStone(1, 2, true);
    	assertEquals(board9.getField(1, 0).getLiberty(), 11);
    	assertEquals(board9.getField(1, 1).getLiberty(), 11);
    	assertEquals(board9.getField(1, 3).getLiberty(), 11);
    	assertEquals(board9.getField(1, 4).getLiberty(), 11);
    	assertEquals(board9.getField(1, 2).getLiberty(), 11);
    	assertEquals(board9.getField(1, 0).getChain().getLength(), 5);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 5);
    	assertEquals(board9.getField(1, 3).getChain().getLength(), 5);
    	assertEquals(board9.getField(1, 4).getChain().getLength(), 5);
    	assertEquals(board9.getField(1, 2).getChain().getLength(), 5);
    }
    
    @Test
    public void testJoinTwoBend() {
    	board9.addStone(1, 0, true);
    	board9.addStone(1, 1, true);
    	board9.addStone(2, 1, true);
    	board9.addStone(2, 3, true);
    	board9.addStone(2, 4, true);
    	board9.addStone(3, 4, true);
    	board9.addStone(2, 2, true);
    	assertEquals(board9.getField(1, 0).getLiberty(), 11);
    	assertEquals(board9.getField(1, 1).getLiberty(), 11);
    	assertEquals(board9.getField(2, 1).getLiberty(), 11);
    	assertEquals(board9.getField(2, 3).getLiberty(), 11);
    	assertEquals(board9.getField(2, 4).getLiberty(), 11);
    	assertEquals(board9.getField(3, 4).getLiberty(), 11);
    	assertEquals(board9.getField(2, 2).getLiberty(), 11);
    	assertEquals(board9.getField(1, 0).getChain().getLength(), 7);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 7);
    	assertEquals(board9.getField(2, 1).getChain().getLength(), 7);
    	assertEquals(board9.getField(2, 3).getChain().getLength(), 7);
    	assertEquals(board9.getField(2, 4).getChain().getLength(), 7);
    	assertEquals(board9.getField(3, 4).getChain().getLength(), 7);
    	assertEquals(board9.getField(2, 2).getChain().getLength(), 7);
    }
    
    @Test
    public void testJoinStraightBend() {
    	board9.addStone(1, 0, true);
    	board9.addStone(1, 1, true);
    	board9.addStone(2, 1, true);
    	board9.addStone(2, 3, true);
    	board9.addStone(3, 3, true);
    	board9.addStone(2, 2, true);
    	assertEquals(board9.getField(1, 0).getLiberty(), 10);
    	assertEquals(board9.getField(1, 1).getLiberty(), 10);
    	assertEquals(board9.getField(2, 1).getLiberty(), 10);
    	assertEquals(board9.getField(2, 3).getLiberty(), 10);
    	assertEquals(board9.getField(3, 3).getLiberty(), 10);
    	assertEquals(board9.getField(2, 2).getLiberty(), 10);
    	assertEquals(board9.getField(1, 0).getChain().getLength(), 6);
    	assertEquals(board9.getField(1, 1).getChain().getLength(), 6);
    	assertEquals(board9.getField(2, 1).getChain().getLength(), 6);
    	assertEquals(board9.getField(2, 3).getChain().getLength(), 6);
    	assertEquals(board9.getField(3, 3).getChain().getLength(), 6);
    	assertEquals(board9.getField(2, 2).getChain().getLength(), 6);
    }
    
    @Test
    public void testClearBoard() {
    	board9.addStone(0, 0, true);
    	board9.addStone(3, 6, true);
    	board9.clearBoard();
    	assertEquals(StoneColor.EMPTY, board9.getField(0, 0).getColor());
    	assertEquals(StoneColor.EMPTY, board9.getField(3, 6).getColor());
    	assertEquals(StoneColor.EMPTY, board9.getField(8, 8).getColor());
    }
    
    @Test
    public void testRemoveIfOneSurroundedByOwnColor() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, true);
    	board9.addStone(1, 0, true);
    	board9.addStone(0, 1, true);
    	board9.addStone(2, 1, true);
    	assertFalse(board9.getField(1, 1).isEmpty());
    }
    
    @Test
    public void testRemoveIfOneSurroundedByOtherColor() {
    	board9.addStone(1, 1, false);
    	board9.addStone(1, 2, true);
    	board9.addStone(1, 0, true);
    	board9.addStone(0, 1, true);
    	board9.addStone(2, 1, true);
    	assertTrue(board9.getField(1, 1).isEmpty());
    }
    
    //TODO: add test for removal of chains
    //TODO: add test for order of removal
    
}
