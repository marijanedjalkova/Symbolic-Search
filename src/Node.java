
public class Node {
	private char state;
	private Node parentNode;
	private int depth;
	private double path_cost;
	
	public Node(char name, Node parentNode, int depth){
		this(name);
		this.parentNode = parentNode;
		this.depth = depth;
		this.path_cost = calculatePathCost();
		
	}
	
	private double calculatePathCost() {
		if (parentNode == null){
			System.out.println("Warning, 0 path cost");
			return 0;
		}
		
		double distance = Environment.getDistance(state, parentNode.getState());
		return parentNode.getPathCost() + distance;
	}

	public Node(char name) {
		this.state = name;
	}

	public void setState(char state){
		this.state = state;
	}
	
	public double getPathCost(){
		return path_cost;
	}
	
	public int getDepth(){
		return depth;
	}
	
	public Node getParentNode(){
		return parentNode;
	}
	
	public char getState(){
		return state;
	}

	public double getDistance(Node goal) {
		return Environment.getDistance(state, goal.getState());
	}
}
