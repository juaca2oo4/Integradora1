import java.sql.SQLOutput;

public class AbbPlayer {
    private Player root;
    private ScorreAbb scorreAbb;  

    public AbbPlayer(){
        this.root = null;
        scorreAbb = new ScorreAbb(); 

    }
    public void insert(String name, String symbol) {
        this.root = insert(this.root, name, symbol);
    }

    private Player insert(Player player, String name, String symbol) {
        if (player == null) {
            return new Player(name, symbol);
        }

        if (name.compareTo(player.getName()) < 0) {
            player.setLeft(insert(player.getLeft(), name, symbol));
        } else {
            player.setRight(insert(player.getRight(), name, symbol));
        }

        return player;
    }
    public String printPlayer(int position){
     String massage="";
        return printPlayer2(position, root, massage);
    }

     public String printPlayer2(int postion , Player player,String massage){
        if(player==null){
            return "";
        }
        if (player.getBox()==postion){
           massage+= player.getSymbol() ;
           if(player.getLeft()!=null && player.getLeft().getBox()==postion){
               System.out.println("");
               massage+=player.getLeft().getSymbol();
           }
            if(player.getRight()!=null && player.getRight().getBox()==postion){
                System.out.println("");
                massage+=player.getRight().getSymbol();
            }
            return massage+="]";
        }
         String massage_Right= printPlayer2(postion, player.getRight(),"");
         String massage_left =printPlayer2(postion, player.getLeft(),"");
         if(massage_left.equalsIgnoreCase("")&& massage_Right.equalsIgnoreCase("")){
             return massage="] ";
         }
         return massage+"] ";
     }


     public String turn(int n){
        if(n==1){
            return "Jugador  -"+ root.getSymbol()+"-  es tu turno \n";
        }
        else if(n==2){
            return "Jugador  -"+ root.getLeft().getSymbol()+"-  es tu turno\n";
        }
        else{
            return "Jugador  -"+ root.getRight().getSymbol()+"-   es tu turno\n";
        }

     }
     public int trowDice(int n,int max, int turn){
         if(turn==1){
             return throwDice(n,max,root);
         }
         else if(turn==2){
             return throwDice(n,max,root.getLeft());
         }
         else{
             return throwDice(n,max,root.getRight());
         }
     }
     private int throwDice(int n,int max, Player player){

             int new_position=player.getBox()+n;
             if(new_position== max){
                 return -1;
             }
             else if(new_position>max){
                 return -2;
             }
             else{
                 return new_position;
             }

     }

     public String setPosition(int n, int turn){
         if(turn==1){
             root.setBox(n);
             return "se ha movido jugador";
         }
         else if(turn==2){
             root.getLeft().setBox(n);
             return "se ha movido jugador";
         }
         else{
             root.getRight().setBox(n);
             return "se ha movido jugador";
         }
     }
public int stopGame(int n, int m){
    return stopGame(root, n, m);
}
private int  stopGame(Player player, int n, int m){
     
    if(player.getBox() == (n*m)){
        return -1; 
    }
    if(player.getLeft().getBox() == (n*m)){ 
        return -1;
    } 
    if(player.getRight().getBox() == (n*m)){
        return -1;
    } else{
        return 0;
    }       
}
public double calculateScore(int seg, int n, int m){
    return calculateScore(seg, root,  n,  m);

}
private double calculateScore(int seg, Player player, int n, int m){
    
    double score =0.0; 
    if(player.getBox() == (n*m)){
        score = (600-seg)/6;
        player.setScore(score);
        scorreAbb.insert(player);
        return score; 
    }
    if(player.getLeft().getBox() == (n*m)){ 
        score = (600-seg)/6;
        player.getLeft().setScore(score);
        scorreAbb.insert(player.getLeft());
        return score; 
    } 
    if(player.getRight().getBox() == (n*m)){
        score = (600-seg)/6;
        player.getRight().setScore(score);
        scorreAbb.insert(player.getRight());
        return score; 
    } else{
        return score;
    }

}
public String printPodium(){
    return scorreAbb.inOrderString(); 
}

}