public class Player {

	private String name;
	private String symbol;
	private Player left;
	private Player right;

	private int box ;

	public Player(String name, String symbol){
		this.name = name;
		this.symbol = symbol;
		this.box=1;

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