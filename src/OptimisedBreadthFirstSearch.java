import java.util.ArrayList;

public class OptimisedBreadthFirstSearch extends Search {
	Problem problem;
	Node currentNode;
	private ArrayList<Node>  queue;
	ArrayList<Node> explored;

	public ArrayList<Node> search(){
		System.out.println("BFS");
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
			currentNode = pop(queue);
			System.out.println("Exploring node " + (currentNode.getState()-'a'));
			
			explored.add(currentNode);
			
			Node n = processChildren(currentNode);
			if (n != null){
				return solution(n, problem);
			}
		}
	}
	
	public void addToQueue(Node node){
		System.out.println("adding " + (node.getState() - 'a'));
		queue.add(node);
	}

	public OptimisedBreadthFirstSearch(Problem p){
		problem = p;
	}
	
	public void print_queue(){
		for (Node n: queue ){
			System.out.print(n.getState() - 'a' + "  ");
		}
		System.out.println();
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
						addToQueue(childNode);
					}
				}
			}
		}
		return null;
	}

	@Override
	public ArrayList<Node> getExplored() {
		return explored;
	}


	
}
