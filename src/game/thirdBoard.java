package game;

//TODO: also insert GUI?

public class thirdBoard {

	private int DIM;
	private thirdStone[] fields;
	
	public thirdBoard(int boardSize) {
		DIM = boardSize;
		fields = new thirdStone[DIM * DIM];
		for (int i = 0; i < DIM * DIM; i++) {
			fields[i] = new thirdStone();
			
		}
		for (int x = 0; x < DIM; x++) {
			for (int y = 0; y < DIM; y++) {
				thirdStone stone = getField(x, y);
				if (!isOnTopBorder(x, y)) {
					stone.addNeighbour(getField(x, y -1));
				}
				if (!isOnRightBorder(x, y)) {
					stone.addNeighbour(getField(x + 1, y));
				}
				if (!isOnBottomBorder(x, y)) {
					stone.addNeighbour(getField(x, y + 1));
				}
				if (!isOnLeftBorder(x, y)) {
					stone.addNeighbour(getField(x - 1, y));
				}
				stone.addThisToChain();
			}
		}
	}
	
	public void addStone(int x, int y, boolean white) {
		thirdStone stone = getField(x, y);
		stone.setColor(white);
		for (thirdStone surrounding : stone.getNeighbour()) {
			if (surrounding.getState() == stone.getState()) {
				stone.join(surrounding);
				if (surrounding.liberty() == 0) {
					//TODO: add remove method
				}
			}
		}
		if (stone.liberty() == 0) {
			//TODO: add remove method
		}
	}
	
	public thirdStone getField(int x, int y) {
		return fields[coordinatesToIndex(x, y)];
	}
	
	public int coordinatesToIndex(int x, int y) {
		return (x + DIM * y);
	}
	
	private boolean isOnCorner(int x, int y) {
		if (x == 0 || x == DIM - 1) {
			if (y == 0 || y == DIM - 1) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isOnBorder(int x, int y) {
		return (x == 0 || x == DIM - 1 || y == 0 || y == DIM - 1);
	}
	
	private boolean isOnTopBorder(int x, int y) {
		return (y == 0);
	}
	
	private boolean isOnLeftBorder(int x, int y) {
		return (x == 0);
	}
	
	private boolean isOnRightBorder(int x, int y) {
		return (x == DIM - 1);
	}
	
	private boolean isOnBottomBorder(int x, int y) {
		return (y == DIM -1);
	}
	
	public int getDimension() {
		return DIM;
	}
	
	public thirdStone[] getAllFields(){
		return fields;
	}
	
}
