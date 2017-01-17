package game;

import GUI.GoGUIIntegrator;

public class Board {

	private int dim;
	private Stone[] fields;
	private GoGUIIntegrator GUI;
	
	public Board(int boardSize) {
		this.dim = boardSize;
		this.fields = new Stone[dim * dim];
    	for (int i = 0; i < dim * dim; i++) {
    		//fields[i] = Mark.EMPTY;
    		//TODO: add empty fields
    	}
		this.GUI = new GoGUIIntegrator(false, true, this.dim);
		GUI.startGUI();
	}
	
	public Stone getField(int x, int y) {
		int index = x + dim * y;
		return fields[index];
	}
	
	public void setStone(int x, int y, Stone stone) {
		fields[x + dim * y] = stone;
	}
	
	public void addStone(int x, int y, Stone stone) {
		int liberty = 0;
		if (!this.isOnTopBorder(x, y)) {
			Stone stoneAbove = this.getField(x, y - 1);
			if (stoneAbove == null) {
				liberty++;
			} else {
				stoneAbove.decreaseDegree();
				if (stoneAbove.noLiberty()) {
					removeStone(x, y - 1);
				}
			}
		}
		if (!this.isOnBottomBorder(x, y)) {
			Stone stoneBelow = this.getField(x, y + 1);
			if (stoneBelow == null) {
				liberty++;
			} else {
				stoneBelow.decreaseDegree();
				if (stoneBelow.noLiberty()) {
					removeStone(x, y + 1);
				}
			}
		}
		if (!this.isOnLeftBorder(x, y)) {
			Stone stoneLeft = this.getField(x - 1, y);
			if (stoneLeft == null) {
				liberty++;
			} else {
				stoneLeft.decreaseDegree();
				if (stoneLeft.noLiberty()) {
					removeStone(x - 1, y);
				}
			}
		}
		if (!this.isOnRightBorder(x, y)) {
			Stone stoneRight = this.getField(x + 1, y);
			if (stoneRight == null) {
				liberty++;
			} else {
				stoneRight.decreaseDegree();
				if (stoneRight.noLiberty()) {
					removeStone(x + 1, y);
				}
			}
		}
		stone.setLiberty(liberty);
		this.setStone(x, y, stone);
		GUI.addStone(x, y, stone.getColor());
	}
	
	public void removeStone(int x, int y) {
		this.setStone(x, y, null);
		GUI.removeStone(x, y);
	}
	
	public void clearBoard() {
		for (int i = 0; i < dim * dim; i++) {
			fields[i] = null;
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
		Stone stone1 = new Stone(true);
		Stone stone2 = new Stone(false);
		Stone stone3 = new Stone(false);
		board.addStone(1, 1, stone1);
		board.addStone(0, 1, stone2);
		board.addStone(2, 1, stone3);
		board.addStone(1, 2, new Stone(false));
		board.addStone(1, 0, new Stone(true));
		//board.removeStone(0, 0);
		Stone stone11 = board.getField(x, y);
		Stone stone22 = board.getField(x-1, y);
		Stone stone33 = board.getField(x+1, y);
		System.out.println(stone11);
		System.out.println(stone22 + "    x-1");
		System.out.println(stone33 + "    x+1");
		
	}
}
