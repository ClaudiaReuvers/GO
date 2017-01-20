package test;

import game.StoneColor;
import game.thirdBoard;
import game.thirdStone;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class testThirdBoard {

	private thirdBoard board;
	
	@Before
	public void setUp() {
		board = new thirdBoard(9);
	}
	
	@Test
	public void testSetUp() {
		assertEquals(9, board.getDimension());
		assertEquals(9 * 9, board.getAllFields().length);
		assertEquals(StoneColor.EMPTY, board.getField(0, 0).getState());
		assertEquals(StoneColor.EMPTY, board.getField(8, 8).getState());
		assertEquals(1, board.getField(0, 0).getChain().getChain().size());
		assertEquals(2, board.getField(0, 0).liberty());
		assertEquals(3, board.getField(5, 0).liberty());
		assertEquals(4, board.getField(1, 1).liberty());
		thirdBoard board19 = new thirdBoard(19);
		assertEquals(19, board19.getDimension());
		assertEquals(StoneColor.EMPTY, board19.getField(0, 0).getState());
		assertEquals(StoneColor.EMPTY, board19.getField(18, 18).getState());
	}
	
	@Test
	public void testAddStoneOne() {
		board.addStone(0, 0, true);
		assertFalse(board.getField(0, 0).isEmpty());
		assertEquals(StoneColor.WHITE, board.getField(0, 0).getState());
		assertEquals(2, board.getField(0, 0).liberty());
		assertEquals(1, board.getField(0, 0).getChain().getChain().size());
		board.addStone(3, 3, false);
		assertEquals(StoneColor.BLACK, board.getField(3, 3).getState());
		assertEquals(4, board.getField(3, 3).liberty());
	}
	
	@Test
	public void testAddStoneTwoOtherColor() {
		board.addStone(1, 1, true);
		board.addStone(1, 2, false);
		assertEquals(3, board.getField(1, 1).liberty());
		assertEquals(3, board.getField(1, 2).liberty());
		assertEquals(1, board.getField(1, 1).getChain().getChain().size());
		assertEquals(1, board.getField(1, 2).getChain().getChain().size());
		board.addStone(1, 0, false);
		assertEquals(2, board.getField(1, 0).liberty());
		assertEquals(2, board.getField(1, 1).liberty());
		board.addStone(0, 0, true);
		assertEquals(1, board.getField(0, 0).liberty());
		assertEquals(1, board.getField(1, 0).liberty());
	}
	
	@Test
	public void testAddStoneTwoSameColor() {
		board.addStone(1, 1, true);
		board.addStone(1, 2, true);
		assertEquals(board.getField(1, 1).getChain(), board.getField(1, 2).getChain());
		assertEquals(6, board.getField(1, 1).liberty());
//		assertEquals(6, board.getField(1, 2).liberty());
		board.addStone(2, 2, false);
		assertEquals(board.getField(1, 1).getChain(), board.getField(1, 2).getChain());
		assertEquals(5, board.getField(1, 1).liberty());
//		assertEquals(5, board.getField(1, 2).liberty());
		assertEquals(3, board.getField(2, 2).liberty());
	}
	
	@Test
	public void testAddStoneThreeSameColorStraight() {
		board.addStone(1, 1, true);
		board.addStone(1, 2, true);
		board.addStone(1, 3, true);
		assertEquals(board.getField(1, 1).getChain(), board.getField(1, 2).getChain());
		assertEquals(board.getField(1, 1).getChain(), board.getField(1, 3).getChain());
		assertEquals(8, board.getField(1, 1).liberty());
	}
	
	@Test
	public void testAddStoneThreeSameColorBend() {
		board.addStone(1, 1, true);
		board.addStone(1, 2, true);
		board.addStone(2, 2, true);
		assertEquals(board.getField(1, 1).getChain(), board.getField(1, 2).getChain());
		assertEquals(board.getField(1, 1).getChain(), board.getField(2, 2).getChain());
		assertEquals(7, board.getField(1, 1).liberty());
	}
	
	@Test
	public void testAddStoneBlock() {
		board.addStone(1, 1, true);
		board.addStone(1, 2, true);
		board.addStone(2, 2, true);
		board.addStone(2, 1, true);
		assertEquals(board.getField(1, 1).getChain(), board.getField(1, 2).getChain());
		assertEquals(board.getField(1, 1).getChain(), board.getField(2, 2).getChain());
		assertEquals(board.getField(1, 1).getChain(), board.getField(2, 1).getChain());
		assertEquals(8, board.getField(1, 1).liberty());
	}
	
	@Test
	public void testJoinTwoStraight() {
		board.addStone(1, 0, true);
		board.addStone(1, 1, true);
		board.addStone(1, 3, true);
		board.addStone(1, 4, true);
		board.addStone(1, 2, true);
		assertEquals(board.getField(1, 0).getChain(), board.getField(1, 1).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(1, 3).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(1, 4).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(1, 2).getChain());
		assertEquals(11, board.getField(1, 0).liberty());
	}
	
	@Test
	public void testJoinTwoBend() {
		board.addStone(1, 0, true);
		board.addStone(1, 1, true);
		board.addStone(2, 1, true);
		board.addStone(2, 3, true);
		board.addStone(2, 4, true);
		board.addStone(3, 4, true);
		board.addStone(2, 2, true);
		assertEquals(board.getField(1, 0).getChain(), board.getField(1, 1).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(2, 1).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(2, 3).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(2, 4).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(3, 4).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(2, 2).getChain());
		assertEquals(12, board.getField(1, 0).liberty());
	}
	
	@Test
	public void testJoinStraighBend() {
		board.addStone(1, 0, true);
		board.addStone(1, 1, true);
		board.addStone(2, 1, true);
		board.addStone(2, 3, true);
		board.addStone(3, 3, true);
		board.addStone(2, 2, true);
		assertEquals(board.getField(1, 0).getChain(), board.getField(1, 1).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(2, 1).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(2, 3).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(3, 3).getChain());
		assertEquals(board.getField(1, 0).getChain(), board.getField(2, 2).getChain());
		assertEquals(10, board.getField(1, 0).liberty());
	}
	
	@Test
	public void testClearBoard() {
		board.addStone(0, 0, true);
		board.addStone(8, 8, false);
		board.addStone(2, 2, false);
		board.clearBoard();
		assertEquals(StoneColor.EMPTY, board.getField(0, 0).getState());
		assertEquals(StoneColor.EMPTY, board.getField(8, 8).getState());
		assertEquals(StoneColor.EMPTY, board.getField(2, 2).getState());
		assertEquals(1, board.getField(0, 0).getChain().getChain().size());
	}
	
	@Test
	public void testRemoveIfOneSurroundedByOwnColor() {
		board.addStone(1, 1, true);
		board.addStone(1, 2, true);
		board.addStone(1, 0, true);
		board.addStone(0, 1, true);
		board.addStone(2, 1, true);
		assertFalse(board.getField(1, 1).isEmpty());
		assertEquals(6, board.getField(1, 1).liberty());
	}
	
	@Test
	public void testRemoveIfOneSurroundedByOtherColor() {
		board.addStone(1, 1, false);
		board.addStone(1, 2, true);
		board.addStone(1, 0, true);
		board.addStone(0, 1, true);
		board.addStone(2, 1, true);
		assertTrue(board.getField(1, 1).isEmpty());
		assertEquals(4, board.getField(1, 2).liberty());
	}
	
	//TODO: add other tests: e.g. test for removal of chains; test for order of removal
}
