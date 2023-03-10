import java.util.Random;

public class Board {

	private static final int MAX_ATTEMPTS = 999;
	private int n;
	private int m;
	private int s;
	private int e;

	private Box root;

	private Box tail;

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
		createBoard(null, total);
	}

	public Box createBoard(Box pointer, int total) {
		if (total == 1) {
			root = new Box(1); // Crea el primer nodo de la lista
			return root;
		} else {
			pointer = createBoard(pointer, total - 1); // Crea la lista hasta el número anterior
			Box newBox = new Box(total); // Crea un nuevo nodo con el valor actual
			pointer.setNext(newBox); // Establece el nuevo nodo como el siguiente del nodo actual
			return newBox; // Retorna el nuevo nodo como el nodo actual
		}
	}

	/*
	 * public void addSnake(int creates) {
	 * 
	 * if (creates <= s) {
	 * int position1 = numberRandom(n * m);
	 * int position2 = numberRandom(n * m);
	 * 
	 * if (position1 == position2 || position1 == 1 || position2 == 1 || position1
	 * == n * m
	 * || position2 == n * m) {
	 * addSnake(creates);
	 * } else {
	 * String letter = obtenerLetra(creates);
	 * Box box1 = getBox(root, position1);
	 * Box box2 = getBox(root, position2);
	 * if (box1.nullAtributes() == 0 && box2.nullAtributes() == 0) {
	 * 
	 * box1.CreateSnake(letter);
	 * box2.CreateSnake(letter);
	 * addSnake(creates=creates+1);
	 * } else {
	 * addSnake(creates);
	 * 
	 * 
	 * }
	 * 
	 * }
	 * }
	 * else{
	 * return;
	 * }
	 * 
	 * }
	 */

	public void addSnake(int creates) {
		int maxAttempts = 100;
		int attempts = 0;
		while (creates <= s) {
			if (attempts > maxAttempts) {
				// Detener el proceso de recursión si se ha intentado muchas veces sin éxito
				return;
			}
			int position1 = numberRandom(n * m);
			int position2 = numberRandom(n * m);

			if (position1 == position2 || position1 == 1 || position2 == 1 || position1 == n * m
					|| position2 == n * m) {
				// Seleccionar dos posiciones diferentes que no sean las esquinas ni la primera
				// ni la última fila/columna
				continue;
			}
			String letter = obtenerLetra(creates);
			Box box1 = getBox(root, position1);
			Box box2 = getBox(root, position2);
			if (box1.nullAtributes() == 0 && box2.nullAtributes() == 0) {
				box1.CreateSnake(letter);
				box2.CreateSnake(letter);
				creates++;
				attempts = 0; // Reiniciar el contador de intentos si se ha creado una serpiente exitosamente
			} else {
				attempts++;
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
		if (pointer == null) {
			return null;
		} else if (pointer.getNumber() == position) {
			return pointer;
		} else {
			return getBox(pointer.getNext(), position);
		}
	}

	public int numberRandom(int n) {
		Random random = new Random();
		return random.nextInt(n) + 1;

	}

	public String table(int n) {
		String lista = "";
		return table(root, lista, n);
	}

	public String table(Box pointer, String lista, int n) {
		if (pointer == null) {
			return null;
		}
		if (pointer.getNumber() == n) {
			lista += "[" + pointer.getNumber();
			return lista;
		} else {
			return table(pointer.getNext(), lista, n);
		}
	}

	public String tableSE(int n) {
		String lista = "";

		return tableSE(root, lista, n);
	}

	public String tableSE(Box pointer, String lista, int n) {
		if (pointer == null) {
			return null;
		}
		if (pointer.getNumber() == n) {
			if (pointer.getSnake() != null) {
				return lista += pointer.getSymbolSnake();
			} else if (pointer.getStair() != null) {
				return lista += pointer.getNumberStair();
			} else {
				return lista;
			}
		} else {
			return tableSE(pointer.getNext(), lista, n);
		}

	}

	public int position_Analysis(int n) {
		return position_Analysis(n, root);
	}

	private int position_Analysis(int n, Box pointer) {
		if (pointer.getNumber() != n) {
			position_Analysis(n, pointer.getNext());
		} else {
			if (pointer.getSnake() != null) {
				String symbol = pointer.getSymbolSnake();
				int position_other_Snake = searchSnake(symbol, root);
				if (position_other_Snake < n) {
					return position_other_Snake;
				} else {
					return n;
				}
			} else if (pointer.getStair() != null) {
				int number = pointer.getNumberStair();
				int position_other_stair = searchStair(number, root);
				if (position_other_stair > n) {
					return position_other_stair;
				} else {
					return n;
				}
			} else {
				return n;
			}
		}

		return n;
	}

	private int searchSnake(String symbol, Box pointer) {
		if (pointer.getSnake() != null && pointer.getSymbolSnake().equalsIgnoreCase(symbol)) {
			return pointer.getNumber();
		} else {
			return searchSnake(symbol, pointer.getNext());
		}
	}

	private int searchStair(int number, Box pointer) {
		if (pointer.getSnake() != null && pointer.getNumberStair() == number) {
			return pointer.getNumber();
		} else {
			return searchStair(number, pointer.getNext());
		}
	}

}