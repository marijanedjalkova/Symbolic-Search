import java.util.ArrayList;
import java.util.Collections;

public abstract class Search {
	Problem problem;
	Node currentNode;
	protected ArrayList<Node>  queue;
	ArrayList<Node> explored;
	
	public Search(Problem p){
		problem = p;
	}
	
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
		if (explored== null)
			return false;
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
	
	public ArrayList<Node> search(){
		currentNode = problem.getInitial();
		queue = new ArrayList<Node>();
		explored = new ArrayList<Node>();
		queuingFn(currentNode);
		while(true){
			if (queueEmpty(queue)){
				return null;
			}
			currentNode = pop(queue);
			if (problem.goalTest(currentNode)){
				ArrayList<Node> res = solution(currentNode, problem);
				System.out.println("Res length " + res.size());
				return res;
			}
			explored.add(currentNode);
			processChildren(currentNode);
		}
		
	}
	
	public abstract void queuingFn(Node node);
	
	public void processChildren(Node n) {
		int current_state_index = n.getState() - 'a';
		for (int i =0; i < 22; i++){
			if (Environment.visibility[current_state_index][i]==1){
				char name = (char)('a' + i);
				Node childNode = new Node(name, n);
				queuingFn(childNode);
			}
		}
	}
	
	public ArrayList<Node> getExplored() {
		return explored;
	}
	
}
