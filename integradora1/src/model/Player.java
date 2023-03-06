public class Player {

	private String name;
	private String symbol;
	private Player left;
	private Player right;

	private int box ;
	private double score; 

	public Player(String name, String symbol){
		this.name = name;
		this.symbol = symbol;
		this.box=1;
		this.score = 0.0; 

	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Player getLeft() {
		return left;
	}

	public void setLeft(Player left) {
		this.left = left;
	}

	public Player getRight() {
		return right;
	}

	public void setRight(Player right) {
		this.right = right;
	}

	public int getBox() {
		return box;
	}

	public void setBox(int box) {
		this.box = box;
	}


}