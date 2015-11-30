import java.util.ArrayList;
import java.util.Scanner;

public class Engine {
	
	
	public static void main(String[] args){
		/*Scanner in = new Scanner(System.in);
		System.out.println("Please choose the search type");
		System.out.println("1 - BF Search");
		System.out.println("2 - Uniform-cost Search");
		System.out.println("3 - Greedy Best Search");
		System.out.println("4 - A* Search");
		char input = in.next().charAt(0);
		in.close();*/
		char input = '3';
		generalSearch(input);
		
	}
	
	public static void generalSearch(char input){
		Problem p = new Problem(3, 21);
		ArrayList<Node> result = null;
		Search s;
		switch (input) {
		case '1':
			s = new BreadthFirstSearch(p);
			result = s.search();			
			break;
		case '2':
			s = new UniformCostSearch(p);
			result = s.search();	
			break;
		case '3':
			s = new GreedyBestSearch(p);
			result = s.search();	
			break;
		case '4':
			s = new AStarSearch(p);
			result = s.search();	
			break;
		default:
			break;
		}
		System.out.println("Result: ");
		for (Node n : result){
			System.out.println(n.getState() - 'a');
			
		}
		System.out.println("Done.");
	}

}
