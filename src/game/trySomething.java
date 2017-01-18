package game;

import GUI.GoGUIIntegrator;

public class trySomething {

	private int dim;
	private Stone[] fields;
	private GoGUIIntegrator GUI;
	
	public trySomething(int boardSize) {
		this.dim = boardSize;
		this.fields = new Stone[dim * dim];
		// set 'empty' stones on the board
    	for (int x = 0; x < dim; x++) {
    		for (int y = 0; y < dim; y++) {
    			fields[coordinatesToIndex(x, y)] = new Stone(x, y);
    		}
    	}
    	this.GUI = new GoGUIIntegrator(false, true, this.dim);
		GUI.startGUI();
	}
	
	public int getBoardSize() {
		return dim;
	}
	
	public Stone getField(int x, int y) {
		return fields[coordinatesToIndex(x, y)];
	}
	
	public void setStone(Stone stone) {
		fields[coordinatesToIndex(stone.getX(), stone.getY())] = stone;
	}
	
	private int coordinatesToIndex(int x, int y) {
		return (x + dim * y);
	}
	
	/*
	private int[] indexToCoordinates(int index) {
		int[] coordinates = new int[2];
		coordinates[0] = index % dim;
		coordinates[1] = index / dim;
		return coordinates;
	}
	*/
	
	public void addStone(int x, int y, boolean white) {
		//TODO: add check if field is empty
		//int index = coordinatesToIndex(x, y);
		//Get stone on (x,y) and set to the color
		Stone stone = getField(x,y);
		stone.setColor(white);
		
		//Look at surrounding fields
		int liberty = 0;
		if (!this.isOnTopBorder(x, y)) {
			Stone above = this.getField(x, y - 1);
			if (above.isEmpty()) {
				liberty = liberty + 1;
			} else {
				above.decreaseLibertyByOne();
				if (above.getColor() == stone.getColor()) {
					addToChain(above, stone);
				}
			}
		}
		if (!this.isOnBottomBorder(x, y)) {
			Stone below = this.getField(x, y + 1);
			if (below.isEmpty()) {
				liberty = liberty + 1;
			} else {
				below.decreaseLibertyByOne();
				if (below.getColor() == stone.getColor()) {
					addToChain(below, stone);
				}
			}
		}
		if (!this.isOnLeftBorder(x, y)) {
			Stone left = this.getField(x - 1, y);
			if (left.isEmpty()) {
				liberty = liberty + 1;
			} else {
				left.decreaseLibertyByOne();
				if (left.getColor() == stone.getColor()) {
					addToChain(left, stone);
				}
			}
		}
		if (!this.isOnRightBorder(x, y)) {
			Stone right = this.getField(x + 1, y);
			if (right.isEmpty()) {
				liberty = liberty + 1;
			} else {
				right.decreaseLibertyByOne();
				if (right.getColor() == stone.getColor()) {
					addToChain(right, stone);
				}
			}
		}
		stone.setLiberty(liberty);
		//Remove chain if it has zero liberties
		
		Chain currentChain = stone.getChain();
		System.out.println(currentChain);
		if (stone.getChain() == null || stone.getChain().getLibertyChain() != 0) {
			setStone(stone);
			GUI.addStone(x, y, stone.getBooleanColor());
		} else {
			removeStones(stone.getChain());
		}
		//TODO: add removal of notCurrentChain
	}
	
	private void addToChain(Stone stoneOld, Stone stoneNew) {
		if (stoneOld.getChain() == null) {
			Chain chain = new Chain();
			chain.add(stoneOld);
			chain.add(stoneNew);
		} else {
			stoneOld.getChain().add(stoneNew);;
		}
	}
	
	public void removeStones(Chain chain) {
		for (Stone stones : chain.getChain()) {
			stones.setEmpty();
			fields[coordinatesToIndex(stones.getX(), stones.getY())] = stones;
			GUI.removeStone(stones.getX(), stones.getY());
		}
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
	
	public static void main(String args[]) {
		trySomething board = new trySomething(5);
		board.addStone(0, 0, true);
		//board.addStone(2, 1, true);
		//board.addStone(1, 1, true);
		board.addStone(0, 1, true);
		board.addStone(1, 0, false);
		board.addStone(1, 1, false);
		board.addStone(1, 2, false);
		board.addStone(0, 2, false);
		System.out.println(board.getField(0, 0));
		System.out.println(board.getField(0, 1));
		System.out.println(board.getField(0, 0).getChain());
		System.out.println(board.getField(0, 1).getChain());
		System.out.println(board.getField(0, 0).getChain().getLibertyChain());
		Chain chain1 = board.getField(1, 0).getChain();
		System.out.println("Chain: " + chain1 + " liberties: " + chain1.getLibertyChain());
		Chain chain2 = board.getField(1, 1).getChain();
		System.out.println("Chain: " + chain2 + " liberties: " + chain2.getLibertyChain());
		Chain chain3 = board.getField(0, 2).getChain();
		System.out.println("Chain: " + chain3 + " liberties: " + chain3.getLibertyChain());
		Chain chain4 = board.getField(1, 2).getChain();
		System.out.println("Chain: " + chain4 + " liberties: " + chain4.getLibertyChain());

	}
}
