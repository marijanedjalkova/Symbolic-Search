import java.util.ArrayList;

public class Engine {
	static int[] resLengths = new int[]{0, 0, 0, 0};
	static int[] explLengths = new int[]{0, 0, 0, 0};
	
	public static void main(String[] args){
		for (int i = 0; i < 22; i++){
			for (int j = 0; j < 22; j++){
				for (int k = 0; k< SearchEnum.values().length; k++){
					generalSearch(SearchEnum.values()[k], i, j);
				}
			}
		}
		double[] resAvgs = new double[4];
		double[] explAvgs = new double[4];
		for (int i = 0; i < 4; i++){
			resAvgs[i] = (double)resLengths[i] / (22*22);
			System.out.print("For enum " + SearchEnum.values()[i]);
			System.out.print(" Res " + resAvgs[i]);
			explAvgs[i] = (double)explLengths[i] / (22*22);
			System.out.println(" Expl " + explAvgs[i]);
		}
	}
	
	public static void generalSearch(SearchEnum search_type, int start, int finish){
		Problem p = new Problem(start, finish);
		ArrayList<Node> result = null;
		ArrayList<Node> expl;
		Search s;
		int resSize;
		int explSize;
		switch (search_type) {
		case BREADTHFIRSTSEARCH:
			s = new BreadthFirstSearch(p);
			result = s.search();
			expl = s.getExplored();
			resSize = (result == null ? 0 : result.size());
			explSize = (expl == null ? 0 : expl.size());
			updateArrays(0, resSize, explSize);
			break;
		case UNIFORMCOSTSEARCH:
			s = new UniformCostSearch(p);
			result = s.search();
			expl = s.getExplored();
			resSize = (result == null ? 0 : result.size());
			explSize = (expl == null ? 0 : expl.size());
			updateArrays(1, resSize, explSize);
			break;
		case GREEDYBESTFIRSTSEARCH:
			s = new GreedyBestSearch(p);
			result = s.search();
			expl = s.getExplored();
			resSize = (result == null ? 0 : result.size());
			explSize = (expl == null ? 0 : expl.size());
			updateArrays(2, resSize, explSize);
			break;
		case ASTARSEARCH:
			s = new AStarSearch(p);
			result = s.search();
			expl = s.getExplored();
			resSize = (result == null ? 0 : result.size());
			explSize = (expl == null ? 0 : expl.size());
			updateArrays(3, resSize, explSize);
			break;
		default:
			s = new BreadthFirstSearch(p);
			result = s.search();
			expl = s.getExplored();
			resSize = (result == null ? 0 : result.size());
			explSize = (expl == null ? 0 : expl.size());
			updateArrays(4, resSize, explSize);
			break;
		}
	}
	
	static void updateArrays(int index, int res, int expl){
		resLengths[index] += res;
		explLengths[index] += expl;
	}

}
