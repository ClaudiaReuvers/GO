package game;

import java.util.ArrayList;

public class thirdChain {

	private ArrayList<thirdStone> chain;
//	private ArrayList<thirdStone> empty;
	
	public thirdChain() {
		chain = new ArrayList<>();
//		empty = new ArrayList<>();
	}
	
	public void addStone(thirdStone stone) {
		if (!chain.contains(stone)) {
			chain.add(stone);
			stone.addChain(this);
		}
	}
	
	public void join(thirdChain otherChain) {
//		System.out.print("Join to stones: " + otherChain.getChain().get(0).getState());
		for (thirdStone stones : otherChain.getChain()) {
//			System.out.println("Join " + stones.getState());
			if (!chain.contains(stones)) {
				chain.add(stones);
			}
		}
		for (thirdStone stones : chain) {
			stones.addChain(this);
		}
	}
	
	public int calculateLibertyChain() {
		ArrayList<thirdStone> empty = new ArrayList<>();
		for (thirdStone stones : chain) {
			for (thirdStone emptyStones : stones.getNeighbour()) {
				if (emptyStones.isEmpty() && !empty.contains(emptyStones)) {
					empty.add(emptyStones);
				}
			}
		}
		return empty.size();
//		for (thirdStone emptyStone : stone.getNeighbour()) {
//			if (!empty.contains(emptyStone)) {
//				empty.add(emptyStone);
//			}
//		}
	}
	
//	public int getLiberty() {
//		return empty.size();
//	}


	public ArrayList<thirdStone> getChain() {
		return chain;
	}
	
	public boolean contains(thirdStone stone) {
		return chain.contains(stone);
	}
	
//	public ArrayList<thirdStone> getEmptyStones() {
//		return empty;
//	}
}
