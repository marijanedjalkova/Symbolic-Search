import java.util.ArrayList;

public class GreedyBestSearch extends Search {
	Problem problem;
	Node currentNode;
	private ArrayList<Node>  queue;
	ArrayList<Node> explored;
	int depth;
	
	public GreedyBestSearch(Problem p) {
		problem = p;
	}

	@Override
	public ArrayList<Node> search() {
		currentNode = problem.getInitial();
		queue = new ArrayList<Node>();
		addToQueue(currentNode);
		explored = new ArrayList<Node>();
		while(true){
			if (queueEmpty(queue))
				return null;
			currentNode = pop(queue);
			if (problem.goalTest(currentNode)){
				return solution(currentNode, problem);
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
				Node childNode = new Node(name, n);
				if (!explored(childNode, explored) && inQueue(childNode, queue)==null){
					addToQueue(childNode);
				} else {
					Node fromQueue = inQueue(childNode, queue);
					if (fromQueue != null && fromQueue.getState() == n.getState()){
						if (fromQueue.getDistance(problem.getGoal()) > n.getDistance(problem.getGoal())){
							queue = replace(fromQueue, n, queue);
						}
					}
				}
			}
		}
	}

	@Override
	public void addToQueue(Node node) {
		if (queueEmpty(queue)){
			queue.add(node);
			return;
		}
		double distance = node.getDistance(problem.getGoal());
		int i = 0;
		while (i < queue.size() && queue.get(i).getDistance(problem.getGoal()) < distance)
			i++;
		queue.add(i, node);
	}

}
