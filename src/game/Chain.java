package game;

import java.util.ArrayList;

public class Chain {

	private ArrayList<secondStone> chain;
	private ArrayList<secondStone> adjacentStones;
	
	public Chain() {
		chain = new ArrayList<>();
		adjacentStones = new ArrayList<>();
	}
	
	public ArrayList<secondStone> getChain() {
		return chain;
	}
	
	public ArrayList<secondStone> getAdjacentStones() {
		return adjacentStones;
	}
	
	public int getLength() {
		return chain.size();
	}
	
	public void add(secondStone stone) {
		stone.setChain(this);
		chain.add(stone);
//		for (secondStone stones : stone.getNeighbours()) {
//			if (stones != null && stones.isEmpty() && !adjacentStones.contains(stones)) {
////				System.out.println(stones);
//				adjacentStones.add(stones);
//			}
//		}
		setAdjacentStones();
	}
	
	private void setAdjacentStones() {
		for (secondStone chainStones : chain) {
			for (secondStone neighbours : chainStones.getNeighbours()) {
				if (!adjacentStones.contains(neighbours)) {
					adjacentStones.add(neighbours);
				}
			}
		}
	}
	
	public void join(secondStone stone) {
		for (secondStone stones : stone.getChain().getChain()) {
			chain.add(stones);
		}
		stone.setChain(this);
	}
	
	public int getLibertyChain() {
//		int liberty = 0;
//		for (secondStone stone: chain) {
//			liberty += stone.getLiberty();
//		}
//		return liberty;
		int lib = 0;
		for (secondStone stones : adjacentStones) {
			if (stones.isEmpty()) {
				lib++;
			}
		}
		return lib;
//		return adjacentStones.size();
//		return 0;
	}
	
	@Override
	public String toString() {
		String msg = "";
		int count = 0;
		for (secondStone stone: chain) {
			count++;
			msg += "Stone " + count +": (" + stone.getX() + "," + stone.getY() + ")/n";
		}
		return msg;
	}
}
