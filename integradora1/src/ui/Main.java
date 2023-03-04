import java.util.Scanner;

public class Main {

    public static Scanner lector = new Scanner(System.in);

    private Controller control;

    public Main() {
        control = new Controller();
    }

    public static void main(String[] args) {
        Main objMain = new Main();
        objMain.menu();
    }

    public void menu() {
        int option = 0;
        boolean x = true;

        while (x) {
            System.out.println("1.Jugar");
            System.out.println("2.Salir");
            option = lector.nextInt();
            switch (option) {
                case 1:
                    createBoard();
                    break;
                case 2:
                    x = true;
                    break;

                default:
                    System.out.println("ingrese una opcion existente");
                    break;
            }
        }
    }

    public void createBoard() {
        System.out.println("ingrese el numero de filas");
        int n = lector.nextInt();
        System.out.println("ingrese el numero de columnas");
        int m = lector.nextInt();
        System.out.println("ingrese el numero de serpientes");
        int s = lector.nextInt();
        System.out.println("ingrese el numero de escaleras");
        int e = lector.nextInt();
        control.createGame(n, m, s, e);
    }

}