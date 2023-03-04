public class Box {

	private int number;

	private Box next;

	private Snake snake;

	private Stair stair;

	public Box(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Box getNext() {
		return next;
	}

	public void setNext(Box next) {
		this.next = next;
	}

	public void CreateSnake(String symbol) {
		this.snake = new Snake(symbol);
	}

	public void CreateStair(int number) {
		this.stair = new Stair(number);
	}

	public int nullAtributes() {
		int x = 0;

		if (snake == null && stair == null) {
			return x;
		} else {
			return x = 1;
		}

	}

}