package game;

public class Stone {

	private StoneENUM color;
	private int liberty;
	private Stone[] surrounding;
	
	public Stone(boolean white) {
		if (white) {
			this.color = StoneENUM.WHITE;
		} else {
			this.color = StoneENUM.BLACK;
		}
	}
	
	public Stone() {
		this.color = StoneENUM.EMPTY;
	}
	
	public void setColor(boolean white) {
		if (white) {
			this.color = StoneENUM.WHITE;
		} else {
			this.color = StoneENUM.BLACK;
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
		//return liberty == 0;
	}
	
	/**
	 * @pure
	 * @return
	 */
	public boolean isEmpty() {
		return (color == StoneENUM.EMPTY);
	}
	
	public boolean noLiberty() {
		/*
		for (int i = 0; i < surrounding.length; i++) {
			if (surrounding[i].getColor() == StoneENUM.EMPTY) {
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
	
	public StoneENUM getColor() {
		return color;
	}
	
	/**
	 * @requires !isEmpty()
	 * @return
	 */
	public boolean getBooleanColor() {
		if (color == StoneENUM.WHITE) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		//String color;
		//if (this.color) {
		//	color = "white";
		//} else {
		//	color = "black";
		//}
		return "This stone is of color " + color + " and has " + liberty + " liberties.";
	}
}