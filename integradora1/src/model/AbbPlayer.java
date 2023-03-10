import java.sql.SQLOutput;

public class AbbPlayer {
    private Player head;
    private ScorreAbb scorreAbb;

    public AbbPlayer() {
        this.head = null;
        scorreAbb = new ScorreAbb();

    }

    /*
     * public void insert(String name, String symbol) {
     * this.root = insert(this.root, name, symbol);
     * }
     * 
     * private Player insert(Player player, String name, String symbol) {
     * if (player == null) {
     * return new Player(name, symbol);
     * }
     * 
     * if (name.compareTo(player.getName()) < 0) {
     * player.setLeft(insert(player.getLeft(), name, symbol));
     * } else {
     * player.setRight(insert(player.getRight(), name, symbol));
     * }
     * 
     * return player;
     * }
     */

    public void insert(String name, String symbol, int turn) {
        insert(head, name, symbol, turn);
    }

    private void insert(Player pointer, String name, String symbol, int turn) {
        Player node = new Player(name, symbol, turn);
        if (head == null) { // caso base, lista vacía
            head = node;
        } else if (pointer.getNext() == null) { // caso base, se encontro el ultimo
            pointer.setNext(node);
        } else { // se sigue buscando el ultimo recursivamente
            insert(pointer.getNext(), name, symbol, turn);
        }
    }

    public String printPlayer(int position) {
        String massage = "";
        return printPlayer(position, head, massage, 1);
    }

    public String printPlayer(int postion, Player player, String massage, int contador) {
        if (player != null && player.getBox() == postion && contador <= 3) {
            massage += player.getSymbol();
            return printPlayer(postion, player.getNext(), massage, ++contador);

        } else if (contador <= 3) {
            return printPlayer(postion, player.getNext(), massage, ++contador);
        } else {
            return massage;
        }

    }

    public String turn(int n) {
        return turn(n, head);
    }

    public String turn(int n, Player player) {
        if (player != null && player.getTurn() == n) {
            return "Jugador  -" + player.getSymbol() + "-  es tu turno \n";
        } else {
            return turn(n, player.getNext());
        }

    }

    public Player find(Player player, int n) {
        if (player != null && player.getTurn() == n) {
            return player;
        } else {
            return find(player.getNext(), n);
        }
    }

    public int trowDice(int n, int max, int turn) {

        Player player_turn = find(head, turn);
        return throwDice(n, max, player_turn);
    }

    private int throwDice(int n, int max, Player player) {

        int new_position = player.getBox() + n;
        if (new_position == max) {
            return -1;
        } else if (new_position > max) {
            return -2;
        } else {
            return new_position;
        }

    }

    public String setPosition(int n, int turn) {
        Player player_turn = find(head, turn);
        player_turn.setBox(n);
        return "se ha movido jugador";
    }

    public int stopGame(int n, int m) {
        return stopGame(head, n, m, 1);
    }

    private int stopGame(Player player, int n, int m, int count) {

        if (count > 3) {
            return 0;
        } else if (player.getBox() == (n * m) && count <= 3) {
            return -1;
        } else {
            return stopGame(player.getNext(), n, m, ++count);
        }
    }

    public double calculateScore(int seg, int n, int m) {
        return calculateScore(seg, head, n, m, 0);

    }

    private double calculateScore(int seg, Player player, int n, int m, double score) {
        if (player.getBox() == (n * m)) {
            score = (600 - seg) / 6;
            player.setScore(score);
            scorreAbb.insert(player);
            return score;
        } else {
            return calculateScore(seg, player.getNext(), n, m, score);
        }
    }

    public String printPodium() {
        return scorreAbb.inOrderString();
    }

}