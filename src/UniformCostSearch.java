
public class UniformCostSearch extends Search {


	public UniformCostSearch(Problem p) {
		super(p);
	}


	@Override
	public void queuingFn(Node childNode) {
		if (!explored(childNode, explored) && inQueue(childNode, queue)==null){
			if (queueEmpty(queue)){
				queue.add(childNode);
				return;
			}
			double path_cost = childNode.getPathCost();
			int i = 0;
			while (i < queue.size() && queue.get(i).getPathCost() < path_cost)
				i++;
			queue.add(i, childNode);
		} else {
			Node fromQueue = inQueue(childNode, queue);
			if (fromQueue != null && fromQueue.getState() == childNode.getParentNode().getState()){
				if (fromQueue.getPathCost() > childNode.getParentNode().getPathCost()){
					queue = replace(fromQueue, childNode.getParentNode(), queue);
				}
			}
		}
		
	}

}
