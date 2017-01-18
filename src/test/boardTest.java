package test;

import game.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

public class boardTest {
	
	private Board board9;

	@Before
    public void setUp() {
    	board9 = new Board(9);
    }
	
    @Test
    public void testSetUp() {
    	Board board13 = new Board(13);
    	assertEquals(9, board9.getBoardSize());
    	assertEquals(13, board13.getBoardSize());
    	assertEquals(StoneColor.EMPTY, board9.getField(0, 0).getColor());
    	assertEquals(StoneColor.EMPTY, board9.getField(8, 8).getColor());
    	assertEquals(StoneColor.EMPTY, board13.getField(0, 0).getColor());
    	assertEquals(StoneColor.EMPTY, board13.getField(12, 12).getColor());
    }
    
    @Test
    public void testSetAndGetStone() {
    	board9.addStone(1, 1, true);
    	assertEquals(StoneColor.WHITE, board9.getField(1, 1).getColor());
    	assertEquals(StoneColor.EMPTY, board9.getField(0, 0).getColor());
    	assertEquals(StoneColor.EMPTY, board9.getField(2, 5).getColor());
    	board9.addStone(2, 2, false);
    	assertEquals(StoneColor.BLACK, board9.getField(2, 2).getColor());
    }
    
    @Test
    public void testLibertiesAfterSet() {
    	board9.addStone(0, 0, true);
    	assertEquals(2, board9.getField(0, 0).getLiberty());
    	board9.addStone(7, 8, true);
    	assertEquals(3, board9.getField(7, 8).getLiberty());
    	board9.addStone(1, 1, true);
    	assertEquals(4, board9.getField(1, 1).getLiberty());
    	board9.addStone(1, 2, true);
    	assertEquals(3, board9.getField(1, 2).getLiberty());
    	assertEquals(3, board9.getField(1, 1).getLiberty());
    }
    
    @Test
    public void testRemoveStone() {
    	board9.addStone(1, 1, true);
    	board9.removeStone(1, 1);
    	assertEquals(StoneColor.EMPTY, board9.getField(1, 1).getColor());
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
}
