
public class Node {
	private char state;
	private Node parentNode;
	private int depth;
	private double path_cost;
	private double total_path_cost;
	
	public Node(char name, Node parentNode){
		this(name);
		this.parentNode = parentNode;
		this.depth = parentNode == null ? 0 : parentNode.getDepth() + 1;
		this.path_cost = calculatePathCost();
		this.total_path_cost = calculateTotalPathCost();
		
	}
	
	private double calculateTotalPathCost() {
		if (parentNode == null)
			return 0;
		double distance = Environment.getDistance(state, parentNode.getState());
		return parentNode.getTotalPathCost() + distance;
	}
	
	private double calculatePathCost() {
		if (parentNode == null)
			return 0;
		return Environment.getDistance(state, parentNode.getState());
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
	
	public double getTotalPathCost(){
		return total_path_cost;
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
