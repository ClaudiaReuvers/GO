package game;

public class Stone {

	private StoneColor color;
	private int liberty;
	private Stone[] surrounding;
	
	public Stone(boolean white) {
		if (white) {
			this.color = StoneColor.WHITE;
		} else {
			this.color = StoneColor.BLACK;
		}
	}
	
	public Stone() {
		this.color = StoneColor.EMPTY;
	}
	
	public void setColor(boolean white) {
		if (white) {
			this.color = StoneColor.WHITE;
		} else {
			this.color = StoneColor.BLACK;
		}
	}
	
	public void decreaseLibertyByOne() {
		if (liberty > 0) {
			liberty--;
		} else {
		/*
		for (int i = 0; i < surrounding.length; i++) {
			boolean colorSurrounding = su
			if (otherColor(surrounding[i].getColor())) {
				
			}
		}
		*/
		}
	}
	
	/**
	 * @pure
	 * @return
	 */
	public boolean isEmpty() {
		return (color == StoneColor.EMPTY);
	}
	
	public boolean noLiberty() {
		/*
		for (int i = 0; i < surrounding.length; i++) {
			if (surrounding[i].getColor() == StoneColor.EMPTY) {
				return false;
			}
		}
		return true;
		*/
		return liberty == 0;
	}
	
	public void setLiberty(int liberty) {
		this.liberty = liberty;
	}
	
	public StoneColor getColor() {
		return color;
	}
	
	/**
	 * @requires !isEmpty()
	 * @return
	 */
	public boolean getBooleanColor() {
		if (color == StoneColor.WHITE) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return "This stone is of color " + color + " and has " + liberty + " liberties.";
	}
}