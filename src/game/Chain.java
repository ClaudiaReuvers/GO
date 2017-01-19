package game;

import java.util.ArrayList;

public class Chain {

	private ArrayList<Stone> chain;
	
	public Chain() {
		chain = new ArrayList<>();
	}
	
	public ArrayList<Stone> getChain() {
		return chain;
	}
	
	public int getLength() {
		return chain.size();
	}
	
	public void add(Stone stone) {
		stone.addChain(this);
		chain.add(stone);
	}
	
	public void join(Chain secondChain) {
		for (Stone stone : secondChain.getChain()) {
			chain.add(stone);
		}
	}
	
	public int getLibertyChain() {
		int liberty = 0;
		for (Stone stone: chain) {
			liberty += stone.getLiberty();
		}
		return liberty;
	}
	
	@Override
	public String toString() {
		String msg = "";
		int count = 0;
		for (Stone stone: chain) {
			count++;
			msg += "Stone " + count +": " + stone.toString() + ")/n";
		}
		return msg;
	}
}
