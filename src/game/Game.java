package game;

import communication.Client;

public class Game {

	private Client client1;
	private Client client2;
	private Board board;
	
	public Game(Client client1, Client client2, int dimension) {
		this.client1 = client1;
		this.client2 = client2;
		board = new Board(dimension);
	}
}
