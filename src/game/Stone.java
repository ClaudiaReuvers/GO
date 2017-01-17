package game;

public class Stone {

	private boolean color;
	private int liberty;
	private Stone[] surrounding;
	
	public Stone(boolean white) {
		this.color = white;
		this.surrounding = new Stone[4];
	}
	
	public void decreaseDegree() {
		if (liberty > 0) {
			liberty--;
		} else {
			System.out.println("Degrees of freedom could not be decrease: stone already has 0 degrees of freedom.");
		}
	}
	
	public boolean noLiberty() {
		return liberty == 0;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public void setLiberty(int liberty) {
		this.liberty = liberty;
	}
	
	public String toString() {
		String color;
		if (this.color) {
			color = "white";
		} else {
			color = "black";
		}
		return "This stone is of color " + color + " and has " + liberty + " degrees of freedom.";
	}
}

/**
 * Represents a mark in the Tic Tac Toe game. There three possible values:
 * Mark.XX, Mark.OO and Mark.EMPTY.
 * Module 2 lab assignment
 * 
 * @author Theo Ruys
 * @version $Revision: 1.4 $
 */
public enum Mark {
    
    EMPTY, XX, OO;

    /*@
       ensures this == Mark.XX ==> \result == Mark.OO;
       ensures this == Mark.OO ==> \result == Mark.XX;
       ensures this == Mark.EMPTY ==> \result == Mark.EMPTY;
     */
    /**
     * Returns the other mark.
     * 
     * @return the other mark is this mark is not EMPTY or EMPTY
     */
    public Mark other() {
        if (this == XX) {
            return OO;
        } else if (this == OO) {
            return XX;
        } else {
            return EMPTY;
        }
    }
    
    public String toString() {
    	String result = " ";
    	if (this == XX) {
    		result = "X";
    	} else if (this == OO) {
    		result = "O";
    	}
    	return result;
    }
}
