import java.util.ArrayList;

public abstract class Search {
	
	public abstract ArrayList<Node> search();
	public abstract void addToQueue(Node node);
	public abstract Node pop();
	
	public abstract boolean queueEmpty();
	public abstract ArrayList<Node> solution(Node n);
	
	
}
