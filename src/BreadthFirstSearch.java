import java.util.ArrayList;
import java.util.Collections;
public class BreadthFirstSearch extends Search {
	Problem problem;
	Node currentNode;
	private ArrayList<Node>  queue;
	ArrayList<Node> explored;

	public ArrayList<Node> search(){
		currentNode = problem.getInitial();
		if (problem.goalTest(currentNode)){
			return solution(currentNode);
		}
		queue = new ArrayList<Node>();
		addToQueue(currentNode);
		explored = new ArrayList<Node>();
		while(true){
			if (queueEmpty())
				return null;
			currentNode = pop();
			explored.add(currentNode);
			
			Node n = processChildren(currentNode);
			if (n != null){
				return solution(n);
			}
		}
	}
	
	public ArrayList<Node> solution(Node n){
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
	
	public void addToQueue(Node node){
		queue.add(node);
	}

	public BreadthFirstSearch(Problem p){
		problem = p;
	}
	
	public Node pop(){
		Node first = queue.get(0);
		queue.remove(0);
		return first;
	}
	
	public boolean queueEmpty(){
		return queue.size() == 0;
	}
	
	public Node processChildren(Node n){
		int current_state_index = n.getState() - 'a';
		for (int i =0; i < 22; i++){
			if (Environment.visibility[current_state_index][i]==1){
				char name = (char)('a' + i);
				Node childNode = new Node(name, n, 1);
				if (!explored(childNode) || inQueue(childNode)){
					if (problem.goalTest(childNode)){
						explored.add(childNode);
						return childNode;
					}
					else {
						queue.add(childNode);
					}
				}
			}
		}
		return null;
	}
	
	public boolean explored(Node n){
		for (int i = 0; i < explored.size(); i++){
			if (explored.get(i).getState()==n.getState())
				return true;
		}
		return false;
	}
	
	public boolean inQueue(Node n){
		for (int i = 0; i < queue.size(); i++){
			if (queue.get(i).getState()==n.getState())
				return true;
		}
		return false;
	}


	
}
