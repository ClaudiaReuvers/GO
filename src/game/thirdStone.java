package game;

import java.util.ArrayList;

public class thirdStone {

	private StoneColor color;
	private thirdChain chain;
	private ArrayList<thirdStone> neighbour;
	private int max = 4;
	
	//Constructor
	public thirdStone() {
		this.color = StoneColor.EMPTY;
		this.chain = new thirdChain();
		this.neighbour = new ArrayList<>();
	}
	
	public void setEmpty() {
		this.color = StoneColor.EMPTY;
		this.chain = new thirdChain();
		addThisToChain();
	}
	
	//Methods
	public void addThisToChain() {
		chain.addStone(this);
	}
	
	public void addChain(thirdChain chain) {
		this.chain = chain;
	}
	
	public void addNeighbour(thirdStone stone) {
		if (neighbour.size() == max) {
			System.out.println("Max nr. of neighbours is exceeded");
		}
		if (!neighbour.contains(stone)) {
			neighbour.add(stone);
		}
	}
	
	public void setColor(boolean white) {
		if (white) {
			this.color = StoneColor.WHITE;
		} else {
			this.color = StoneColor.BLACK;
		}
	}
	
	public void join(thirdStone stone) {
		chain.join(stone.getChain());
		stone.addChain(chain);
	}
	
	//Queries
	public StoneColor getState() {
		return color;
	}
	
	public thirdChain getChain() {
		return chain;
	}
	
	public ArrayList<thirdStone> getNeighbour() {
		return neighbour;
	}
	
	public boolean isEmpty() {
		return (color == StoneColor.EMPTY);
	}
	
	public int liberty() {
		return chain.calculateLibertyChain();
	}
}