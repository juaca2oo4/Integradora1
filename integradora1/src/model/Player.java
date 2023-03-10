public class Player {

	private String name;
	private String symbol;
	private Player next;
	private int turn;

	private int box;
	private double score;

	public Player(String name, String symbol, int turn) {
		this.name = name;
		this.symbol = symbol;
		this.turn = turn;
		this.box = 1;
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

	public Player getNext() {
		return next;
	}

	public void setNext(Player next) {
		this.next = next;
	}

	public int getBox() {
		return box;
	}

	public void setBox(int box) {
		this.box = box;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

}