import java.util.ArrayList;
import java.util.Collections;

public class UniformCostSearch extends Search {
	Problem problem;
	Node currentNode;
	private ArrayList<Node>  queue;
	ArrayList<Node> explored;
	

	public UniformCostSearch(Problem p) {
		problem = p;
	}

	@Override
	public ArrayList<Node> search() {
		currentNode = problem.getInitial();
		queue = new ArrayList<Node>();
		addToQueue(currentNode);
		explored = new ArrayList<Node>();
		while(true){
			if (queueEmpty())
				return null;
			currentNode = pop();
			if (problem.goalTest(currentNode)){
				return solution(currentNode);
			}
			explored.add(currentNode);
			processChildren(currentNode);
		}
	}

	private void processChildren(Node n) {
		int current_state_index = n.getState() - 'a';
		for (int i =0; i < 22; i++){
			if (Environment.visibility[current_state_index][i]==1){
				char name = (char)('a' + i);
				Node childNode = new Node(name, n, 1);
				if (!explored(childNode) && inQueue(childNode)==null){
					addToQueue(childNode);
				} else {
					Node fromQueue = inQueue(childNode);
					if (fromQueue != null && fromQueue.getState() == n.getState()){
						if (fromQueue.getPathCost() > n.getPathCost()){
							replace(fromQueue, n);
						}
					}
				}
			}
		}
	}
	
	public void replace(Node node1, Node node2){
		int index = queue.indexOf(node1);
		queue.remove(index);
		queue.add(index, node2);
	}

	@Override
	public void addToQueue(Node node) {
		if (queueEmpty()){
			queue.add(node);
			return;
		}
		double path_cost = node.getPathCost();
		int i = 0;
		while (i < queue.size() && queue.get(i).getPathCost() < path_cost)
			i++;
		queue.add(i, node);
		
	}

	@Override
	public Node pop(){
		Node first = queue.get(0);
		queue.remove(0);
		return first;
	}

	@Override
	public boolean queueEmpty(){
		return queue.size() == 0;
	}

	@Override
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
	
	public boolean explored(Node n){
		for (int i = 0; i < explored.size(); i++){
			if (explored.get(i).getState()==n.getState())
				return true;
		}
		return false;
	}
	
	public Node inQueue(Node n){
		for (int i = 0; i < queue.size(); i++){
			if (queue.get(i).getState()==n.getState())
				return queue.get(i);
		}
		return null;
	}

}
