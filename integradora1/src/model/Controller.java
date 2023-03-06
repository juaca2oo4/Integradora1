public class Controller {

    private Board board;

    private AbbPlayer abbPlayer;

    private int turn; 

    private int seg; 
    
    private boolean stop; 

    public Controller() {
        abbPlayer=new AbbPlayer();
        turn = 1;
        seg = 0;
        stop = false; 

        
    }
    public void timer(){
        while (!stop) {
            try {
                Thread.sleep(1000);
                seg++;
            } catch (InterruptedException ex) {
                System.out.println("se paro el tiempo, el juego se cerró");
            }
            if (-1 == abbPlayer.stopGame(board.getN(), board.getM())) { // Detener después de que jugador llegue a la casilla final
                stop = true;
                System.out.println("se paro el tiempo, se acabo el juego");
            }
        }
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

        if(board.getN()%2==0){
            massage =print2(total, total- board.getM()+1, 0,massage);
        }
        else{
            massage =print1(total- board.getM()+1, total, 1,massage);
        }

        return massage;
    }
    private String print1( int intervalo1, int intervalo2,int contador,String massage) {

     if(contador<= board.getN()){
         if(contador%2==0){
//mayor a menor
             massage=printContrio(intervalo1,intervalo2,massage)+"\n";
             int x= intervalo2-board.getM();
             int y=intervalo1-board.getM();
           return print1(x,y,++contador,massage);
         }
         else{
             // menor a mayor
             massage=printOrder(intervalo1,intervalo2,massage)+"\n";
             int x=intervalo2-board.getM();
             int y=intervalo1-board.getM();
             return print1(x,y,++contador,massage);
         }
     }
     return massage;

   }
    private String print2( int intervalo1, int intervalo2,int contador,String massage) {

        if(contador< board.getN()){
            if(contador%2==0){
//mayor a menor
                massage=printContrio(intervalo1,intervalo2,massage)+"\n";
                int x= intervalo2-board.getM();
                int y=intervalo1-board.getM();
                return print2(x,y,++contador,massage);
            }
            else{
                // menor a mayor
                massage=printOrder(intervalo1,intervalo2,massage)+"\n";
                int x=intervalo2-board.getM();
                int y=intervalo1-board.getM();
                return print2(x,y,++contador,massage);
            }
        }
        return massage;

    }

    private String printOrder( int intervalo1, int intervalo2, String massage){
// menor a mayor
     if(intervalo1<=intervalo2){
         massage+=board.table(intervalo1);
         massage+=abbPlayer.printPlayer(intervalo1);
        return printOrder(++intervalo1,intervalo2,massage);
     }
     else{
         return massage;
     }

    }

    private String printContrio(int intervalo1, int intervalo2, String massage){
        //mayor a menor
        if(intervalo1>=intervalo2){
            massage+=board.table(intervalo1);
            massage+=abbPlayer.printPlayer(intervalo1);
            return printContrio(--intervalo1,intervalo2,massage);
        }
        else{
            return massage;
        }

    }

    public String turn(){
        String msj=  abbPlayer.turn(turn);
        turn = newTurn(turn); 
        return msj; 
    }

    public String throwDice(){
        String massage="";
        int number= board.numberRandom(7);
        int position_Player= abbPlayer.trowDice(7, board.getM() * board.getN(),turn);
      
        if(position_Player==-1){
            //finish game
        }
        else if (position_Player==-2){
            massage="te pasaste de la ultima casilla, vuelve a intentarlo en tu proximo tiro"; 
            turn = newTurn(turn); 
           
        }
        else{
            System.out.println("su dado cayo en el numero " + number+ "\n");
          int position=position_Analysis(number);
          massage = abbPlayer.setPosition(position,turn);
          turn = newTurn(turn); 
          
          return massage; 
        }

        return massage;
    }
    public int newTurn(int turn){
        if(turn ==3){

            return turn =1;
        }else{
            return ++turn;
        }
         
    }
    
    public int position_Analysis(int position_new){

       return board.position_Analysis(position_new);

    }



    public String createPlayer(String name, String symbol){

        abbPlayer.insert(name,symbol);
        return "el jugador se ha registrado, jugador numero:   ";
    }

    public int gameOver(){
        if(-1 == abbPlayer.stopGame(board.getN(), board.getM())){
            return 0; 
        } else{
            return 1; 

        }

    }
    public void  calculateScore(){
        abbPlayer.calculateScore(seg, board.getN(),board.getM()); 
    }

    public String printPodium(){
        return abbPlayer.printPodium(); 
    }



}
