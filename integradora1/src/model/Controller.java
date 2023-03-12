import java.time.Duration;
import java.time.Instant;

public class Controller {

    private Board board;

    private AbbPlayer abbPlayer;

    private int turn;
    
    private Instant start;

    private Instant end;

    private Duration duration;

    private Double seg; 

    public Controller() {
        abbPlayer = new AbbPlayer();
        turn = 1;

    }

    public Instant getStart() {
        return start;
    }


    public Instant getEnd() {
        return end;
    }


    public Duration getDuration() {
        return duration;
    }

    public String createGame(int n, int m, int s, int e) {

        start = Instant.now();

        String massage = "";

        if (s * e > n * m) {
            massage = "debes ingresar un numero menor de escaleras y serpientes";
        } else if (e > 26 || s > 26) {
            massage = "debes ingresar un valor menor de  26 serpientes o 26 escaleras";
        } else {
            board = new Board(n, m, s, e);
            massage = "se creo correctamente el tablero";
            board.createBoard();
            board.addStair(1);
            board.addSnake(1);

        }
        return massage;

    }

    public String print() {
        String massage = "";

        int total = board.getN() * board.getM();

        if (board.getN() % 2 == 0) {
            massage = print(total, total - board.getM() + 1, 0, massage, board.getN() - 1, true);
        } else {
            massage = print(total - board.getM() + 1, total, 1, massage, board.getN(), true);
        }

        return massage;
    }

    public String printSE() {
        String massage = "";

        int total = board.getN() * board.getM();

        if (board.getN() % 2 == 0) {
            massage = print(total, total - board.getM() + 1, 0, massage, board.getN() - 1, false);
        } else {
            massage = print(total - board.getM() + 1, total, 1, massage, board.getN(), false);
        }

        return massage;
    }

    private String print(int intervalo1, int intervalo2, int contador, String massage, int filas, boolean tipo) {

        if (contador <= filas) {
            if (contador % 2 == 0) {
                // mayor a menor
                massage = printContrio(intervalo1, intervalo2, massage, tipo) + "\n";
                int x = intervalo2 - board.getM();
                int y = intervalo1 - board.getM();
                return print(x, y, ++contador, massage, filas, tipo);
            } else {
                // menor a mayor
                massage = printOrder(intervalo1, intervalo2, massage, tipo) + "\n";
                int x = intervalo2 - board.getM();
                int y = intervalo1 - board.getM();
                return print(x, y, ++contador, massage, filas, tipo);
            }
        }
        return massage;

    }

    private String printOrder(int intervalo1, int intervalo2, String massage, boolean tipo) {
        // menor a mayor
        if (tipo) {
            if (intervalo1 <= intervalo2) {
                massage += "[" + intervalo1;
                massage += abbPlayer.printPlayer(intervalo1) + "]";
                return printOrder(++intervalo1, intervalo2, massage, tipo);
            } else {
                return massage;
            }
        } else {
            if (intervalo1 <= intervalo2) {
                massage += "[";
                massage += board.tableSE(intervalo1) + "]";
                return printOrder(++intervalo1, intervalo2, massage, tipo);
            } else {
                return massage;
            }
        }

    }

    private String printContrio(int intervalo1, int intervalo2, String massage, boolean tipo) {
        // mayor a menor
        if (tipo) {
            if (intervalo1 >= intervalo2) {
                massage += "[" + intervalo1;
                massage += abbPlayer.printPlayer(intervalo1) + "]";
                return printContrio(--intervalo1, intervalo2, massage, tipo);
            } else {
                return massage;
            }
        } else {
            if (intervalo1 >= intervalo2) {
                massage += "[" ;
                massage += board.tableSE(intervalo1) + "]";
                return printContrio(--intervalo1, intervalo2, massage, tipo);
            } else {
                return massage;
            }
        }

    }

    public String turn() {
        String msj = abbPlayer.turn(turn);
        return msj;
    }

    public String throwDice() {
        String massage = "";
        int number = board.numberRandom(7);
        int position_Player = abbPlayer.trowDice(number, board.getM() * board.getN(), turn);
        System.out.println("la supuesta nueva posicion del jugador es " + position_Player);
        if (position_Player == -1) {

            end = Instant.now();
            duration = Duration.between(start, end);
            seg = duration.toNanos() / 1_000_000_000.0;
            // finish game
        } else if (position_Player == -2) {
            massage = "te pasaste de la ultima casilla, vuelve a intentarlo en tu proximo tiro";
            turn = newTurn(turn);

        } else {
            System.out.println("su dado cayo en el numero " + number + "\n");
            int position = position_Analysis(position_Player);
            massage = abbPlayer.setPosition(position, turn);
            turn = newTurn(turn);

            return massage;
        }

        return massage;
    }

    public int newTurn(int turn) {
        if (turn == 3) {

            return turn = 1;
        } else {
            return ++turn;
        }

    }

    public int position_Analysis(int position_new) {

        return board.position_Analysis(position_new);

    }

    public String createPlayer(String name, String symbol, int turn) {

        abbPlayer.insert(name, symbol, turn);
        return "el jugador se ha registrado, jugador numero:   ";
    }

    public void calculateScore() {
        abbPlayer.calculateScore(seg,turn);
    }

    public String printPodium() {
        return abbPlayer.printPodium();
    }

    public void reset(){
        board = null;
        turn = 1;
        start = null;
        end = null;
        duration = null;
        seg =  null; 
        abbPlayer.deleteList();

    }

}
