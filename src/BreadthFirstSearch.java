public class BreadthFirstSearch extends Search {

	public BreadthFirstSearch(Problem p){
		super(p);
	}

	@Override
	public void queuingFn(Node childNode) {
		if (!explored(childNode, explored) && inQueue(childNode, queue)==null){
			queue.add(childNode);
		} 
	}

	
}
