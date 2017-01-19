package test;

import game.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class boardTest {
	
	private secondBoard board9;

	@Before
    public void setUp() {
    	board9 = new secondBoard(9);
    }
	
    @Test
    public void testSetUp() {
    	secondBoard board13 = new secondBoard(13);
    	assertEquals(9, board9.getDimension());
    	assertEquals(13, board13.getDimension());
    	assertEquals(StoneColor.EMPTY, board9.getField(0, 0).getColor());
    	assertEquals(StoneColor.EMPTY, board9.getField(8, 8).getColor());
    	assertEquals(StoneColor.EMPTY, board13.getField(0, 0).getColor());
    	assertEquals(StoneColor.EMPTY, board13.getField(12, 12).getColor());
    	assertEquals(2, board9.getField(0, 0).getNeighbours().size());
    	assertEquals(3, board9.getField(0, 1).getNeighbours().size());
    	assertEquals(4, board9.getField(6, 6).getNeighbours().size());
    	assertEquals(StoneColor.EMPTY, board9.getField(0, 0).getNeighbours().get(0).getColor());
    	assertEquals(StoneColor.EMPTY, board9.getField(0, 0).getNeighbours().get(1).getColor());
//    	assertEquals(4, )
    }
    
    @Test
    public void testSetAndGetOneStone() {
    	board9.addStone(1, 1, true);
    	assertFalse(board9.getField(1, 1).isEmpty());
    	assertEquals(StoneColor.WHITE, board9.getField(1, 1).getColor());
    	assertEquals(4, board9.getField(1, 1).liberty());
    	assertEquals(1, board9.getField(1, 1).getChain().getLength());
    	board9.addStone(8, 7, false);
    	assertEquals(StoneColor.BLACK, board9.getField(8, 7).getColor());
    	assertEquals(3, board9.getField(8, 7).liberty());
    	assertEquals(1, board9.getField(8, 7).getChain().getLength());
    	board9.addStone(0, 0, true);
    	assertEquals(2, board9.getField(0, 0).liberty());
    	assertEquals(1, board9.getField(0, 0).getChain().getLength());
    }
    
    @Test
    public void testTwoStonesOtherColor() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, false);
    	assertEquals(3, board9.getField(1, 1).liberty());
    	assertEquals(3, board9.getField(1, 2).liberty());
    	assertEquals(1, board9.getField(1, 1).getChain().getLength());
    	assertEquals(1, board9.getField(1, 2).getChain().getLength());
    	board9.addStone(1, 0, false);
    	assertEquals(2, board9.getField(1, 0).liberty());
    	assertEquals(2, board9.getField(1, 1).liberty());
    	board9.addStone(0, 0, true);
    	assertEquals(1, board9.getField(0, 0).liberty());
    	assertEquals(1, board9.getField(1, 0).liberty());
    }
    
    @Test
    public void testTwoStonesSameColor() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, true);
    	assertEquals(2, board9.getField(1, 1).getChain().getLength());
    	assertEquals(2, board9.getField(1, 2).getChain().getLength());
    	assertEquals(6, board9.getField(1, 1).getChain().getAdjacentStones().size());
    	assertEquals(6, board9.getField(1, 2).getChain().getAdjacentStones().size());
    	assertEquals(6, board9.getField(1, 1).liberty());
    	assertEquals(6, board9.getField(1, 2).liberty());
    	board9.addStone(2, 2, false);
    	assertEquals(5, board9.getField(1, 1).liberty());
    	assertEquals(5, board9.getField(1, 2).liberty());
    	assertEquals(2, board9.getField(1, 1).getChain().getLength());
    	assertEquals(2, board9.getField(1, 2).getChain().getLength());
    }
    
    @Test
    public void testStraightChainOfThree() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, true);
    	board9.addStone(1, 3, true);
    	assertEquals(8, board9.getField(1, 1).liberty());
    	assertEquals(8, board9.getField(1, 2).liberty());
    	assertEquals(8, board9.getField(1, 3).liberty());
    	assertEquals(3, board9.getField(1, 1).getChain().getLength());
    	assertEquals(3, board9.getField(1, 2).getChain().getLength());
    	assertEquals(3, board9.getField(1, 3).getChain().getLength());
    }
    
    @Test
    public void testBendChainOfThree() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, true);
    	board9.addStone(2, 2, true);
    	assertEquals(7, board9.getField(1, 1).liberty());
    	assertEquals(7, board9.getField(1, 2).liberty());
    	assertEquals(7, board9.getField(2, 2).liberty());
    	assertEquals(3, board9.getField(1, 1).getChain().getLength());
    	assertEquals(3, board9.getField(1, 2).getChain().getLength());
    	assertEquals(3, board9.getField(2, 2).getChain().getLength());
    }
    
    @Test
    public void testBlockChain() {
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 2, true);
    	board9.addStone(2, 2, true);
    	board9.addStone(2, 1, true);
    	assertEquals(8, board9.getField(1, 1).liberty());
    	assertEquals(8, board9.getField(1, 2).liberty());
    	assertEquals(8, board9.getField(2, 2).liberty());
    	assertEquals(8, board9.getField(2, 1).liberty());
    	assertEquals(4, board9.getField(1, 1).getChain().getLength());
    	assertEquals(4, board9.getField(1, 2).getChain().getLength());
    	assertEquals(4, board9.getField(2, 2).getChain().getLength());
    	assertEquals(4, board9.getField(2, 1).getChain().getLength());
    }
    
    @Test
    public void testJoinTwoStraight() {
    	board9.addStone(1, 0, true);
    	board9.addStone(1, 1, true);
    	board9.addStone(1, 3, true);
    	board9.addStone(1, 4, true);
    	board9.addStone(1, 2, true);
    	assertEquals(11, board9.getField(1, 0).liberty());
    	assertEquals(11, board9.getField(1, 1).liberty());
    	assertEquals(11, board9.getField(1, 3).liberty());
    	assertEquals(11, board9.getField(1, 4).liberty());
    	assertEquals(11, board9.getField(1, 2).liberty());
    	assertEquals(5, board9.getField(1, 0).getChain().getLength());
    	assertEquals(5, board9.getField(1, 1).getChain().getLength());
    	assertEquals(5, board9.getField(1, 3).getChain().getLength());
    	assertEquals(5, board9.getField(1, 4).getChain().getLength());
    	assertEquals(5, board9.getField(1, 2).getChain().getLength());
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
    	assertEquals(11, board9.getField(1, 0).liberty());
    	assertEquals(11, board9.getField(1, 1).liberty());
    	assertEquals(11, board9.getField(2, 1).liberty());
    	assertEquals(11, board9.getField(2, 3).liberty());
    	assertEquals(11, board9.getField(2, 4).liberty());
    	assertEquals(11, board9.getField(3, 4).liberty());
    	assertEquals(11, board9.getField(2, 2).liberty());
    	assertEquals(7, board9.getField(1, 0).getChain().getLength());
    	assertEquals(7, board9.getField(1, 1).getChain().getLength());
    	assertEquals(7, board9.getField(2, 1).getChain().getLength());
    	assertEquals(7, board9.getField(2, 3).getChain().getLength());
    	assertEquals(7, board9.getField(2, 4).getChain().getLength());
    	assertEquals(7, board9.getField(3, 4).getChain().getLength());
    	assertEquals(7, board9.getField(2, 2).getChain().getLength());
    }
    
    @Test
    public void testJoinStraightBend() {
    	board9.addStone(1, 0, true);
    	board9.addStone(1, 1, true);
    	board9.addStone(2, 1, true);
    	board9.addStone(2, 3, true);
    	board9.addStone(3, 3, true);
    	board9.addStone(2, 2, true);
    	assertEquals(10, board9.getField(1, 0).liberty());
    	assertEquals(10, board9.getField(1, 1).liberty());
    	assertEquals(10, board9.getField(2, 1).liberty());
    	assertEquals(10, board9.getField(2, 3).liberty());
    	assertEquals(10, board9.getField(3, 3).liberty());
    	assertEquals(10, board9.getField(2, 2).liberty());
    	assertEquals(6, board9.getField(1, 0).getChain().getLength());
    	assertEquals(6, board9.getField(1, 1).getChain().getLength());
    	assertEquals(6, board9.getField(2, 1).getChain().getLength());
    	assertEquals(6, board9.getField(2, 3).getChain().getLength());
    	assertEquals(6, board9.getField(3, 3).getChain().getLength());
    	assertEquals(6, board9.getField(2, 2).getChain().getLength());
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
    
    //TODO: broaden testSetUp() for neighbours
    //TODO: add test for removal of chains
    //TODO: add test for order of removal
    
}
