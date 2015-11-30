public class Problem {
	private Node initial;
	private Node goal;
	
	public Problem(int initial, int goal){
		char initc = (char)('a' + initial);
		char goalc = (char)('a' + goal);
		this.initial = new Node(initc, null);
		this.goal = new Node(goalc);
	}
	
	public boolean goalTest(Node currentNode){
		return currentNode.getState()==goal.getState();
	}
	
	public char getInitialState(){
		return initial.getState();
	}
	
	public Node getInitial(){
		return initial;
	}
	
	public void setInitialState(char state){
		this.initial.setState(state);
	}

	public Node getGoal() {
		return goal;
	}	
}
