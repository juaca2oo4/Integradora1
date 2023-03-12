public class NodeScore {

    private double score;
	private NodeScore right; 
	private NodeScore left;
    private String name;  

	public NodeScore(String name, Double score) {
		this.score = score;
        this.name = name; 
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public double getScore() {
		return score;
	}

	public NodeScore getRight() {
		return right;
	}

	public void setRight(NodeScore right) {
		this.right = right;
	}

	public NodeScore getLeft() {
		return left;
	}

	public void setLeft(NodeScore left) {
		this.left = left;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
