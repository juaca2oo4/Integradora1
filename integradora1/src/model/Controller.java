public class Controller {

    private Long start;

    private Long finish;

    private Board board;

    private AbbPlayer abbPlayer;

    public Controller() {
        abbPlayer=new AbbPlayer();
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
            board.addStair(1);
            board.addSnake(1);

        }
        return massage;

    }

    public String print(){
        String massage="";

        int total=board.getN()*board.getM();

        massage =print(total, board.getN(), board.getM(), 0,massage);


        return massage;
    }
    private String print( int total,int n, int m,int contador,String massage) {

       if (contador <= total) {
           if (contador % 2 == 0) {
                massage += printMayor(n,m,massage);

           } else{
               massage += printMenor(n,m,massage);
           }
           return massage;
       }
       return massage;


   }

    private String printMayor( int n, int m, String massage){
        if (n <= m) {
            massage += abbPlayer.printPlayer(n);
            return printMayor(++n, m, massage);
        } else {
            return massage;
        }
    }

    private String printMenor(int n , int m ,String massage){
        if(n <=m){
            massage+=abbPlayer.printPlayer(n);
            return printMenor(--n,m,massage);
        }
        else{
            return massage;
        }
    }






    public String createPlayer(String name, String symbol){

        abbPlayer.insert(name,symbol);
        return "el jugador se ha registrado, jugador numero:   ";
    }





}
