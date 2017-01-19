package game;

public class Stone {

	private StoneColor color;
	private int liberty;
	private Chain chain;
	private int x;
	private int y;
	
	//private Stone[] surrounding;
	
	/**
	 * Creates an empty <code>Stone</code>.
	 */
	public Stone(int x, int y) {
		this.x = x;
		this.y = y;
		this.color = StoneColor.EMPTY;
		this.liberty = -1;
		chain = null;
	}
	
	/**
	 * Creates a <code>Stone</code> with a given color.
	 * @param white <tt>true</tt> if the color is <tt>WHITE</tt>
	 * <tt>false</tt> if the color is <tt>BLACK</tt>
	 */
	public Stone(boolean white) {
		if (white) {
			this.color = StoneColor.WHITE;
		} else {
			this.color = StoneColor.BLACK;
		}
	}
	
	/**
	 * Set the color of the <code>Stone</code>.
	 * @param white <tt>true</tt> if the color is <tt>WHITE</tt>,
	 * <tt>false</tt> if the color is <tt>BLACK</tt>
	 */
	public void setColor(boolean white) {
		if (white) {
			this.color = StoneColor.WHITE;
		} else {
			this.color = StoneColor.BLACK;
		}
	}
	
	public void setEmpty() {
		this.color = StoneColor.EMPTY;
		this.liberty = -1;
		chain = null;
	}
	
	/**
	 * Sets the number of liberties of the <code>Stone</code>.
	 * @param liberty number of liberties the <code>Stone</code> has
	 */
	public void setLiberty(int liberty) {
		this.liberty = liberty;
	}
	
	/**
	 * Decreases the <tt>liberty</tt> of this <code>Stone</code> with one.
	 * @requires getLiberty() > 0
	 * @ensures getLiberty() == old(getLiberty()) - 1
	 */
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

	public void addChain(Chain chain) {
		this.chain = chain;
	}
	
	public boolean hasChain() {
		return (chain!=null);
	}
	
	/**
	 * Returns if the <code>Stone</code> is empty.
	 * @pure
	 * @return <tt>true</tt> if getColor() == StoneColor.EMPTY
	 */
	public boolean isEmpty() {
		return (color == StoneColor.EMPTY);
	}

	/**
	 * Returns if the <tt>liberty</tt> of this <code>Stone</code> is zero.
	 * @return <tt>true</tt> if getLiberty() == 0
	 */
	public boolean hasNoLiberty() {
		/*
		for (int i = 0; i < surrounding.length; i++) {
			if (surrounding[i].getColor() == StoneColor.EMPTY) {
				return false;
			}
		}
		return true;
		*/
		return (this.liberty == 0);
	}
	
	/**
	 * Returns the number of liberties of the <code>Stone</code>.
	 * @return the number of liberties of the <code>Stone</code>
	 */
	public int getLiberty() {
		return this.liberty;
	}
	
	/**
	 * Returns the color of the <code>Stone</code>.
	 * @return the color of the <code>Stone</code>
	 */
	public StoneColor getColor() {
		return this.color;
	}
	
	/**
	 * Returns if the <tt>color</tt> of the <code>Stone</code> is <tt>BLACK</tt> or <tt>WHITE</tt>
	 * @requires !isEmpty()
	 * @return <tt>true</tt> if the <code>Stone</code> is <tt>WHITE</tt>,
	 * <tt>false</tt> is the <code>Stone</code> is <tt>BLACK</tt>
	 */
	public boolean getBooleanColor() {
		if (color == StoneColor.WHITE) {
			return true;
		} else {
			return false;
		}
	}
	
	public Chain getChain() {
		return chain;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		String msg = "";
		msg += "The stone is placed on (" + x + "," + y;
		msg += "), has " + liberty + " liberties and is " + color + ".";
		return msg;
//		return "This stone is of color " + color + " and has " + liberty + " liberties.";
	}
}