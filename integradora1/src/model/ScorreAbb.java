public class ScorreAbb {
    NodeScore root; 

    public ScorreAbb(){
        this.root = null;

    }
    public void insert(NodeScore player){
		if(root == null){
			root = player; 
		}
		else{
			insert(player, root); 
		}
	}

	private void insert(NodeScore player, NodeScore current){
		// Izquierda 
		if(player.getScore() < current.getScore()){
			if(current.getLeft() == null){
				current.setLeft(player); 
			}
			else{
				insert(player, current.getLeft()); 
			}
		}
		// Derecha 
		else if(player.getScore() > current.getScore()){
			if(current.getRight() == null){
				current.setRight(player); 
			}
			else{
				insert(player, current.getRight()); 
			}
		}
		else{
			// los nodos son iguales 
		}
	}

    public String inOrderString(){
		return "[" + inOrderString(root) + "]"; 
	}

	private String inOrderString(NodeScore current){
		if(current == null){
			return ""; 
		}

		return inOrderString(current.getLeft()) + " " +"jugador "+ current.getName()+" puntaje: " + current.getScore() + " " + inOrderString(current.getRight()); 
	}




}
