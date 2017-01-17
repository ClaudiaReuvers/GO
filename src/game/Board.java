package game;

import GUI.GoGUIIntegrator;

public class Board {

	private int dim;
	private Stone[] fields;
	private GoGUIIntegrator GUI;
	
	public Board(int boardSize) {
		this.dim = boardSize;
		this.fields = new Stone[dim * dim];
		// set 'empty' stones on the board
    	for (int i = 0; i < dim * dim; i++) {
    		fields[i] = new Stone();
    	}
		this.GUI = new GoGUIIntegrator(false, true, this.dim);
		GUI.startGUI();
	}
	
	public Stone getField(int x, int y) {
		return fields[x + dim * y];
	}
	
	public void setStone(int x, int y, Stone stone) {
		fields[x + dim * y] = stone;
	}
	
	public void addStone(int x, int y, boolean white) {
		//Get stone on this location (x,y) and set to the color
		Stone stone = getField(x, y);
		stone.setColor(white);
		
		int liberty = 0;
		// Check if placed stone is on a border
		// If not, check if there is a stone above and if so set the liberty of the stone above one down
		if (!isOnTopBorder(x, y)) {
			Stone stoneAbove = this.getField(x, y - 1);
			if (stoneAbove.isEmpty()) {
				liberty++;
			} else {
				stoneAbove.decreaseLibertyByOne();
				if (stoneAbove.noLiberty()) {
					removeStone(x, y -1);
				}
			}
		}
		if (!isOnBottomBorder(x, y)) {
			Stone stoneBelow = this.getField(x, y + 1);
			if (stoneBelow.isEmpty()) {
				liberty++;
			} else {
				stoneBelow.decreaseLibertyByOne();
				if (stoneBelow.noLiberty()) {
					removeStone(x, y + 1);
				}
			}
		}
		if (!isOnLeftBorder(x, y)) {
			Stone stoneLeft = this.getField(x - 1, y);
			if (stoneLeft.isEmpty()) {
				liberty++;
			} else {
				stoneLeft.decreaseLibertyByOne();
				if (stoneLeft.noLiberty()) {
					removeStone(x - 1, y);
				}
			}
		}
		if (!isOnRightBorder(x, y)) {
			Stone stoneRight = this.getField(x + 1, y);
			if (stoneRight.isEmpty()) {
				liberty++;
			} else {
				stoneRight.decreaseLibertyByOne();
				if (stoneRight.noLiberty()) {
					removeStone(x + 1, y);
				}
			}
		}
		stone.setLiberty(liberty);
		this.setStone(x, y, stone);
		GUI.addStone(x, y, stone.getBooleanColor());
	}
	
	public void removeStone(int x, int y) {
		this.setStone(x, y, new Stone());
		GUI.removeStone(x, y);
	}
	
	public void clearBoard() {
		for (int i = 0; i < dim * dim; i++) {
			fields[i] = new Stone();
		}
		GUI.clearBoard();
	}
	
	public int getBoardSize() {
		return dim;
	}
	
	public GoGUIIntegrator getGUI() {
		return GUI;
	}
	
	private boolean isOnCorner(int x, int y) {
		if (x == 0 || x == dim - 1) {
			if (y == 0 || y == dim - 1) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isOnBorder(int x, int y) {
		return (x == 0 || x == dim - 1 || y == 0 || y == dim - 1);
	}
	
	private boolean isOnTopBorder(int x, int y) {
		return (y == 0);
	}
	
	private boolean isOnLeftBorder(int x, int y) {
		return (x == 0);
	}
	
	private boolean isOnRightBorder(int x, int y) {
		return (x == dim - 1);
	}
	
	private boolean isOnBottomBorder(int x, int y) {
		return (y == dim -1);
	}
	
	public static void main(String[] args) {
		int x = 1; int y = 1;
		Board board = new Board(9);
		board.addStone(1, 1, true);
		board.addStone(0, 1, true);
		board.addStone(2, 1, true);
		board.addStone(1, 2, false);
		board.addStone(1, 0, true);
		//board.removeStone(0, 0);
		Stone stone11 = board.getField(x, y);
		Stone stone22 = board.getField(x-1, y);
		Stone stone33 = board.getField(x+1, y);
		Stone stone44 = board.getField(x, y-1);
		Stone stone55 = board.getField(x, y+1);
		System.out.println(stone11);
		System.out.println(stone22 + "    x-1");
		System.out.println(stone33 + "    x+1");
		System.out.println(stone44 + "    y-1");
		System.out.println(stone55 + "    y+1");
		
		
	}
}
