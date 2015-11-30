import java.util.ArrayList;
import java.util.Collections;

public abstract class Search {
	
	public abstract ArrayList<Node> search();
	public abstract void addToQueue(Node node);
	
	public boolean queueEmpty(ArrayList<Node>  queue) {
		return queue.size() == 0;
	}
	
	public ArrayList<Node> solution(Node n, Problem problem) {
		ArrayList<Node> solution = new ArrayList<Node>();
		Node current = n;
		solution.add(current);
		while (current.getState() != problem.getInitialState()){
			current = current.getParentNode();
			solution.add(current);
		}
		Collections.reverse(solution);
		return solution;
	}
	
	public Node inQueue(Node n, ArrayList<Node> queue){
		for (int i = 0; i < queue.size(); i++){
			if (queue.get(i).getState()==n.getState())
				return queue.get(i);
		}
		return null;
	}
	
	public boolean explored(Node n, ArrayList<Node> explored){
		for (int i = 0; i < explored.size(); i++){
			if (explored.get(i).getState()==n.getState())
				return true;
		}
		return false;
	}
	
	public Node pop(ArrayList<Node> queue){
		Node first = queue.get(0);
		queue.remove(0);
		return first;
	}
	
	public ArrayList<Node> replace(Node node1, Node node2, ArrayList<Node>  queue){
		int index = queue.indexOf(node1);
		queue.remove(index);
		queue.add(index, node2);
		return queue;
	}
	
}
