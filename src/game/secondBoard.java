package game;

import GUI.GoGUIIntegrator;

public class secondBoard {

	private int dim;
	private secondStone[] fields;
	private GoGUIIntegrator GUI;
	
	//Constructor
	public secondBoard(int boardSize) {
		this.dim = boardSize;
		this.fields = new secondStone[dim*dim];
		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				int liberty;
				if (isOnCorner(x, y)) {
					liberty = 2;
				} else if (isOnBorder(x, y)) {
					liberty = 3;
				} else {
					liberty = 4;
				}
				fields[coordinatesToIndex(x, y)] = new secondStone(x, y, liberty);
			}
		}
		setNeighbours();
		this.GUI = new GoGUIIntegrator(false, true, this.dim);
		//GUI.startGUI();
	}
	
	private void setNeighbours() {
		for (secondStone stones : fields) {
			if (!isOnTopBorder(stones.getX(), stones.getY())) {
				stones.addNeighbours(getField(stones.getX(), stones.getY() - 1));
			}
			if (!isOnBottomBorder(stones.getX(), stones.getY())) {
				stones.addNeighbours(getField(stones.getX(), stones.getY() + 1));
			}
			if (!isOnLeftBorder(stones.getX(), stones.getY())) {
				stones.addNeighbours(getField(stones.getX() - 1, stones.getY()));
			}
			if (!isOnRightBorder(stones.getX(), stones.getY())) {
				stones.addNeighbours(getField(stones.getX() + 1, stones.getY()));
			}
			stones.getChain().add(stones);
		}
	}
	
	
	//Methods
	public void addStone(int x, int y, boolean white) {
		//TODO: add check if (x,y) is empty
		secondStone thisStone = getField(x, y);
		thisStone.setColor(white);
		
		for (secondStone neighbours : thisStone.getNeighbours()) {
			if (!neighbours.isEmpty()) {
				thisStone.decreaseLibertyByOne();
				neighbours.decreaseLibertyByOne();
				checkSurroundingStone(thisStone, neighbours);
			}
			if (neighbours.liberty() == 0) {
				removeStones(neighbours.getChain());
			}
			if (thisStone.liberty() == 0) {
				removeStones(thisStone.getChain());
			}
		}
	}
	
	private void checkSurroundingStone(secondStone thisStone, secondStone surroundingStone) {
		if (thisStone.getColor() == surroundingStone.getColor()) {
			thisStone.getChain().join(surroundingStone);
		}
		
		
	}
	
	private void removeStones(Chain chain) {
		for (secondStone stones : chain.getChain()) {
			int liberty;
			if (isOnCorner(stones.getX(), stones.getY())) {
				liberty = 2;
			} else if (isOnBorder(stones.getX(), stones.getY())) {
				liberty = 3;
			} else {
				liberty = 4;
			}
			stones.setEmpty(liberty);
			fields[this.coordinatesToIndex(stones.getX(), stones.getY())] = stones;
			GUI.removeStone(stones.getX(), stones.getY());
		}
	}
	
	public void clearBoard() {
		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				int liberty;
				if (isOnCorner(x, y)) {
					liberty = 2;
				} else if (isOnBorder(x, y)) {
					liberty = 3;
				} else {
					liberty = 4;
				}
				fields[coordinatesToIndex(x, y)] = new secondStone(x, y, liberty);
			}
		}
	}
	
	private int coordinatesToIndex(int x, int y) {
		return (x + dim * y);
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
	
	//Queries
	public secondStone getField(int x, int y) {
		return fields[this.coordinatesToIndex(x, y)];
	}
	
	public int getDimension() {
		return dim;
	}
	
	public secondStone[] getFields() {
		return fields;
	}
	
	public GoGUIIntegrator getGUI() {
		return GUI;
	}
}
