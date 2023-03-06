public class Box {

	private int number;

	private Box next;

	private  Box previous;

	private Snake snake;

	private Stair stair;

	public Box(int number) {
		this.number = number;
		this.snake=null;
		this.stair=null;
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

	public Box getPrevious() {
		return previous;
	}

	public void setPrevious(Box previous) {
		this.previous = previous;
	}

	public int nullAtributes() {
     if(snake!=null && stair!=null){
		 return 0;
	 }
	 return 1;

	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public Stair getStair() {
		return stair;
	}

	public void setStair(Stair stair) {
		this.stair = stair;
	}

	public String getSymbolSnake(){
		return snake.getSymbol();
	}

	public int getNumberStair(){
		return stair.getNumber();
	}
}