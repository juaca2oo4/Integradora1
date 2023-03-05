import java.util.Scanner;

public class Main {

    public static Scanner lector = new Scanner(System.in);

    private Controller control;

    public Main() {
        control = new Controller();
    }
    public Scanner getLector(){
        return lector;
    }

    public static void main(String[] args) {

        // creación del objeto.
        Main main = new Main();
        // llamdo a uno de los metodos de la clase.
        int option = 0;

        do{

            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);

        main.getLector().close();

    }

    public int getOptionShowMenu(){
        int option = 0;
        System.out.println("<<<<< Snakkes And Ladders >>>>>");
        System.out.println(
                        "1. Jugar \n" +
                        "0. Exit. \n");

        option =  validateIntegerInput();
        return option;
    }

    public void executeOption(int option){

        switch(option){
            case 1:
                System.out.println("creemos el tablero");
                createBoard();
                createPlayer(1);
                printBoard();

                break;
            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }
    }


public void createBoard() {
System.out.println("ingrese el numero de filas");
        int n = validateIntegerInput();
        if(n == -1){
            System.out.println("opcion invalida");

        }
        System.out.println("ingrese el numero de columnas");
        int m =validateIntegerInput();
        if(m == -1){
            System.out.println("opcion invalida");

        }
        System.out.println("ingrese el numero de serpientes");
        int s = validateIntegerInput();
        if(s == -1){
            System.out.println("opcion invalida");

        }
        System.out.println("ingrese el numero de escaleras");
        int e = validateIntegerInput();
        if(e == -1){
            System.out.println("opcion invalida");

        }
        control.createGame(n, m, s, e);
    }
    public void createPlayer(int count){

        System.out.println("vamos a registrar los 3 jugadores del juego.");
        while (count <=3){
            System.out.println("ingrese el nombre de tu jugador");
            String name = lector.next();
            System.out.println("ingrese alguno de estos simbolos:  * ! O X % $ # + &");
            String symbol = lector.next();
            if(symbol.equalsIgnoreCase("*") || symbol.equalsIgnoreCase("!") || symbol.equalsIgnoreCase("O") || symbol.equalsIgnoreCase("X") || symbol.equalsIgnoreCase("$") || symbol.equalsIgnoreCase("#") || symbol.equalsIgnoreCase("+") || symbol.equalsIgnoreCase("&")) {
                String msj = control.createPlayer(name, symbol) + "";
                count++;
            }
            else{
                System.out.println("ingrese un simbolo valido para el juego" + count);
            }
        }
        System.out.println("ya se registraron todos los jugadores, empecemos a jugar!");

    }


    public int validateIntegerInput(){
        int option = 0;

        if(lector.hasNextInt()){
            option = lector.nextInt();
            lector.nextLine();

        }
        else{
            // clear reader.
            lector.nextLine();
            option = -1;
        }

        return option;
    }
    /**
     * validateDoubleInput: this method validates that the option entered by the user is actually an double data type
     * @return option: is a double or int  option.
     */

    public double validateDoubleInput(){
        double option = 0;

        if(lector.hasNextDouble()){
            option = lector.nextDouble();
        }
        else{
            // clear reader.
            lector.nextLine();
            option = -1;
        }

        return option;
    }


    public void printBoard(){
        System.out.println(control.print());
    }


}