import java.util.Scanner;

public class Main {

    public static Scanner lector = new Scanner(System.in);

    private Controller control;

    public Main() {
        control = new Controller();

    }

    public Scanner getLector() {
        return lector;
    }

    public static void main(String[] args) {

        // creación del objeto.
        Main main = new Main();
        // llamdo a uno de los metodos de la clase.
        int option = 0;

        do {

            option = main.getOptionShowMenu();
            main.executeOption(option);

        } while (option != 0);

        main.getLector().close();

    }

    public int getOptionShowMenu() {
        int option = 0;
        System.out.println("<<<<< Snakkes And Ladders >>>>>");
        System.out.println(
                "1. Jugar \n" +
                        "0. Exit. \n");

        option = validateIntegerInput();
        return option;
    }

    public int getMenu2() {
        int option = 0;
        System.out.println("\n escoge que quieres hacer: \n");
        System.out.println(
                "1. Tirar dado \n" +
                        "2. Ver escaleras y Serpientes . \n");

        option = validateIntegerInput();
        return option;
    }

    public void executeOption(int option) {

        switch (option) {
            case 1:
                System.out.println("creemos el tablero");
                createBoard();
                createPlayer(1);

                System.out.println("el tiempo empieza!");
                // control.timer();
                printBoard();

                int option2 = 0;

                do {
                    option2 = getMenu2();
                    executeOption2(option2);
                    if (control.gameOver() == 0) {

                        control.calculateScore();
                        control.printPodium();
                        option2 = 0;

                        break;
                    }

                } while (option2 != 0);

                break;
            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public void executeOption2(int option2) {

        switch (option2) {
            case 1:
                System.out.println(control.turn());
                System.out.println("\n vas a tirar un dado \n");
                control.throwDice();
                printBoard();
                printSE();
                break;

            case 2:
                System.out.println(control.turn());
                System.out.println("vas a ver las serpientes y las escaleras\n");
                printSE();
                System.out.println("tiraras el dado y te moveras...");
                control.throwDice();
                printBoard();

                break;

            default:
                System.out.println("invalid option");
                break;

        }

    }

    public void createBoard() {
        System.out.println("ingrese el numero de filas");
        int n = validateIntegerInput();
        if (n == -1) {
            System.out.println("opcion invalida");

        }
        System.out.println("ingrese el numero de columnas");
        int m = validateIntegerInput();
        if (m == -1) {
            System.out.println("opcion invalida");

        }
        System.out.println("ingrese el numero de serpientes");
        int s = validateIntegerInput();
        if (s == -1) {
            System.out.println("opcion invalida");

        }
        System.out.println("ingrese el numero de escaleras");
        int e = validateIntegerInput();
        if (e == -1) {
            System.out.println("opcion invalida");

        }
        if (e + s >= n * m) {
            System.out.println(
                    "el numero de escaleras y serpientes es mayor al numero de casillas :c, intentalo otra vez\n ");
            createBoard();
        } else {
            control.createGame(n, m, s, e);
        }

    }

    public void createPlayer(int count) {

        System.out.println("\n vamos a registrar los 3 jugadores del juego.\n");

        while (count <= 3) {
            System.out.println("ingrese el nombre de tu jugador");
            String name = lector.next();
            System.out.println("ingrese alguno de estos simbolos:  * ! O X % $ # + &");
            String symbol = lector.next();
            if (symbol.equalsIgnoreCase("*") || symbol.equalsIgnoreCase("!") || symbol.equalsIgnoreCase("O")
                    || symbol.equalsIgnoreCase("X") || symbol.equalsIgnoreCase("%") || symbol.equalsIgnoreCase("$")
                    || symbol.equalsIgnoreCase("#") || symbol.equalsIgnoreCase("+") || symbol.equalsIgnoreCase("&")) {
                String msj = control.createPlayer(name, symbol, count) + " " + count;

                count++;

            } else {
                System.out.println("ingrese un simbolo valido para el jugador " + count);
            }
        }
        System.out.println("ya se registraron todos los jugadores, empecemos a jugar!");

    }

    public int validateIntegerInput() {
        int option = 0;

        if (lector.hasNextInt()) {
            option = lector.nextInt();
            lector.nextLine();

        } else {
            // clear reader.
            lector.nextLine();
            option = -1;
        }

        return option;
    }

    /**
     * validateDoubleInput: this method validates that the option entered by the
     * user is actually an double data type
     * 
     * @return option: is a double or int option.
     */

    public double validateDoubleInput() {
        double option = 0;

        if (lector.hasNextDouble()) {
            option = lector.nextDouble();
        } else {
            // clear reader.
            lector.nextLine();
            option = -1;
        }

        return option;
    }

    public void printBoard() {
        System.out.println(control.print());
    }

    public void printSE() {
        System.out.println(control.printSE());
    }

}