public class Controller {

    private Long start;

    private Long finish;

    private Board board;

    public Controller() {
    }

    public String createGame(int n, int m, int s, int e) {
        String massage = "";

        if (s * e > n * m) {
            massage = "debes ingresar un numero menor de escaleras y serpientes";
        } else if (e > 26 || s > 26) {
            massage = "debes ingresar un valor menor de  26 serpientes o 26 escaleras";
        } else {
            board = new Board(n, m, s, e);
            massage = "se creo correctamente el tablero";
            board.createBoard();
            board.addSnake(0);
            board.addStair(0);
        }
        return massage;

    }

}
