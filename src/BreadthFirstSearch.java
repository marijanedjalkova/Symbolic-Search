import java.util.ArrayList;

public class BreadthFirstSearch extends Search {
	Problem problem;
	Node currentNode;
	private ArrayList<Node>  queue;
	ArrayList<Node> explored;

	public ArrayList<Node> search(){
		currentNode = problem.getInitial();
		if (problem.goalTest(currentNode)){
			return solution(currentNode, problem);
		}
		queue = new ArrayList<Node>();
		addToQueue(currentNode);
		explored = new ArrayList<Node>();
		while(true){
			if (queueEmpty(queue))
				return null;
			currentNode = pop();
			explored.add(currentNode);
			
			Node n = processChildren(currentNode);
			if (n != null){
				return solution(n, problem);
			}
		}
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
	
	public Node processChildren(Node n){
		int current_state_index = n.getState() - 'a';
		for (int i =0; i < 22; i++){
			if (Environment.visibility[current_state_index][i]==1){
				char name = (char)('a' + i);
				Node childNode = new Node(name, n);
				if (!explored(childNode, explored) && inQueue(childNode, queue)==null){
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


	
}
