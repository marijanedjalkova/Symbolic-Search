public class GreedyBestSearch extends Search {
	
	public GreedyBestSearch(Problem p) {
		super(p);
	}


	@Override
	public void queuingFn(Node childNode) {
		if (!explored(childNode, explored) && inQueue(childNode, queue)==null){
			if (queueEmpty(queue)){
				queue.add(childNode);
				return;
			}
			double distance = childNode.getDistance(problem.getGoal());
			int i = 0;
			while (i < queue.size() && queue.get(i).getDistance(problem.getGoal()) < distance)
				i++;
			queue.add(i, childNode);
		} else {
			Node fromQueue = inQueue(childNode, queue);
			if (fromQueue != null && fromQueue.getState() == childNode.getParentNode().getState()){
				if (fromQueue.getDistance(problem.getGoal()) > childNode.getParentNode().getDistance(problem.getGoal())){
					queue = replace(fromQueue, childNode.getParentNode(), queue);
				}
			}
		}
		
	}


}
