import java.sql.SQLOutput;

public class AbbPlayer {
    Player root;

    public AbbPlayer(){
        this.root = null;

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

}