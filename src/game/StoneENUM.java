package game;

public enum StoneENUM {

	EMPTY, WHITE, BLACK;
	
	private int liberty;
	
	public void setLiberty(int liberty) {
		this.liberty = liberty;
	}
	
	public int getLiberty() {
		return liberty;
	}
	
	/*
	@Override
	public String toString() {
		String color = "";
		if (this == WHITE) {
			color = "WHITE";
		} else if (this == BLACK) {
			color = "BLACK";
		} else {
			color = "EMPTY";
		}
	}
	*/
}

