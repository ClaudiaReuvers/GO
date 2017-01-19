package game;

import java.util.ArrayList;

public class secondStone {

	private StoneColor color;
	private Chain chain;
	private int x;
	private int y;
	private int liberty;
	private ArrayList<secondStone> neighbours;
	
	//Constructor
	public secondStone(int x, int y, int liberty) {
		this.x = x;
		this.y = y;
		this.color = StoneColor.EMPTY;
		this.chain = new Chain();
//		chain.add(this);
		this.liberty = liberty;
		this.neighbours = new ArrayList<>();
	}
	
	//Methods
	public void setColor(boolean white) {
		if (white) {
			this.color = StoneColor.WHITE;
		} else {
			this.color = StoneColor.BLACK;
		}
	}
	
	public void setEmpty(int liberty) {
		this.color = StoneColor.EMPTY;
		this.chain = new Chain();
		chain.add(this);
		this.liberty = liberty;
	}
	
	public void decreaseLibertyByOne() {
		liberty = liberty - 1;
	}
	
	public void setChain(Chain chain) {
		this.chain = chain;
	}
	
	public int liberty() {
//		int lib = 0;
//		for (secondStone stone : chain.getFreeStones()) {
//			lib += stone.getLiberty();
//		}
//		return lib;
		return chain.getLibertyChain();
	}
	
	public void addNeighbours(secondStone neighbour) {
		this.neighbours.add(neighbour);
	}
	
	//Queries
	public boolean isEmpty() {
		return (color == StoneColor.EMPTY);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int[] getLocation() {
		int[] location = new int[2];
		location[0] = x;
		location[1] = y;
		return location;
	}
	
	public Chain getChain() {
		return chain;
	}
	
	public StoneColor getColor() {
		return color;
	}
	
	public int getLiberty() {
		return liberty;
	}
	
	public ArrayList<secondStone> getNeighbours() {
		return neighbours;
	}
	
	public String toString() {
		String msg = "";
		msg += "The stone is placed on (" + x + "," + y;
		msg += "), has " + liberty + " liberties and is " + color + ".";
		return msg;
//		return "This stone is of color " + color + " and has " + liberty + " liberties.";
	}
	
	public String neighboursToString() {
		String msg = "Neighbours of " + toString() + ":\n";
		for (secondStone stones : this.neighbours) {
			msg += stones.toString() + "\n";
		}
		return msg;
	}
}
