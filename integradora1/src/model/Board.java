import java.util.Random;

public class Board {

	private int n;
	private int m;
	private int s;
	private int e;

	private Box root;

	public Board(int n, int m, int s, int e) {
		this.n = n;
		this.m = m;
		this.s = s;
		this.e = e;
		root = null;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public Box getRoot() {
		return root;
	}

	public void setRoot(Box root) {
		this.root = root;
	}

	public void createBoard() {
		int total = n * m;
		Box pointer = new Box(1);
		createBoard(pointer, 1, total);
	}

	public void createBoard(Box pointer, int position, int total) {
		if (position > total) {
			return;
		} else {
			if (root == null) {
				root = pointer;

				root.setNext(new Box(++position));
				createBoard(root.getNext(), position = position + 2, total);
			} else {
				pointer.setNext(new Box(position));
				createBoard(pointer, ++position, total);
			}

		}
	}

	public void addSnake(int creates) {

		if (creates <= s) {
			int position1 = numberRandom(n * m);
			int position2 = numberRandom(n * m);

			if (position1 == position2 || position1 == 1 || position2 == 1 || position1 == n * m
					|| position2 == n * m) {
				addSnake(creates);
			} else {
				String letter = obtenerLetra(creates);
				Box box1 = getBox(root, position1);
				Box box2 = getBox(root, position2);
				if (box1.nullAtributes() == 0 && box2.nullAtributes() == 0) {
					box1.CreateSnake(letter);
					box2.CreateSnake(letter);
					addSnake(++creates);
				} else {
					addSnake(creates);

				}

			}
		}

	}

	public String obtenerLetra(int numero) {

		int codigoAscii = 'A' + numero - 1;

		return Character.toString((char) codigoAscii);
	}

	public void addStair(int creates) {
		if (creates <= s) {
			int position1 = numberRandom(n * m);
			int position2 = numberRandom(n * m);

			if (position1 == position2 || position1 == 1 || position2 == 1 || position1 == n * m
					|| position2 == n * m) {
				addSnake(creates);
			} else {
				System.out.println(position1);
				System.out.println(position2);

				Box box1 = getBox(root, position1);
				Box box2 = getBox(root, position2);
				if (box1.nullAtributes() == 0 && box2.nullAtributes() == 0) {
					box1.CreateStair(creates);
					box2.CreateStair(creates);
					addSnake(++creates);
				} else {
					addSnake(creates);
				}

			}
		}

	}

	public Box getBox(Box pointer, int position) {
		if (pointer != null && pointer.getNumber() != position) {
			getBox(pointer.getNext(), position);
		} else {
			return pointer;
		}
		return pointer;

	}

	public int numberRandom(int n) {
		int number = 0;
		Random r = new Random();

		number = r.nextInt(n);

		return number;

	}

}