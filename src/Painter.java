import java.awt.*;
import java.util.*;

import easyGui.EasyGui;
import geometry.IntPoint;
import renderables.*;

public class Painter {
private final EasyGui gui;
	
	private int buttonId;
	
	private int breadthFirstSearchID;
	private int uniformCosrtSearchID;
	private int greedyBesrFirstSearchID;
	private int aStarSearchID;
	private int startID;
	private int goalID;
	
	private SearchEnum search_type;

	private final int frameLength = 500;
	private final int frameHeight = 500;
	private RenderablePoint robot;
	
	RenderablePolyline obs1, obs2, obs3, obs4, obs5, obs6;
	
	
	// MAIN
	public static void main(String[] args) {
		Painter canvas = new Painter();
		canvas.run();
	}
	
	public Painter() {
		//Set up the GUI, labels, buttons
		search_type = null;
		gui = new EasyGui(frameLength, frameHeight);
		
		buttonId = gui.addButton(3, 2, "Go!", this, "buttonAction");
		
		gui.addLabel(0, 0, "Starting point:");
		startID = gui.addTextField(0, 1, null);
		gui.addLabel(1, 0, "Goal point:");
		goalID = gui.addTextField(1, 1, null);
		
		
		gui.addLabel(2, 0, "Select algorithm: ");
		breadthFirstSearchID = gui.addButton(2, 1, "Breadth First Search", this, "bfs");
		uniformCosrtSearchID = gui.addButton(2, 2, "Uniform Cost Search", this, "ucs");
		greedyBesrFirstSearchID = gui.addButton(2, 3, "Greedy Best First Search", this, "gbfs");
		aStarSearchID = gui.addButton(2, 4, "A* Search", this, "astar");

		gui.addButton(3, 3, "Quit", this, "quit");
		
		drawObstacles();
	}
	
	public void drawObstacles(){
		
		obs1 = new RenderablePolyline();
		addPoint(obs1, 0);
		addPoint(obs1, 1);
		addPoint(obs1, 2);
		addPoint(obs1, 3);
		addPoint(obs1, 0);
		
		obs2 = new RenderablePolyline();
		addPoint(obs2, 4);
		addPoint(obs2, 5);
		addPoint(obs2, 6);
		addPoint(obs2, 4);
		
		obs3 = new RenderablePolyline();
		addPoint(obs3, 7);
		addPoint(obs3, 8);
		addPoint(obs3, 9);
		addPoint(obs3, 10);
		addPoint(obs3, 7);
		
		obs4= new RenderablePolyline();
		addPoint(obs4, 11);
		addPoint(obs4, 12);
		addPoint(obs4, 13);
		addPoint(obs4, 14);
		addPoint(obs4, 11);
		
		obs5 = new RenderablePolyline();
		addPoint(obs5, 15);
		addPoint(obs5, 16);
		addPoint(obs5, 17);
		addPoint(obs5, 15);

		
		obs6 = new RenderablePolyline();
		addPoint(obs6, 18);
		addPoint(obs6, 19);
		addPoint(obs6, 20);
		addPoint(obs6, 21);
		addPoint(obs6, 18);
		
		gui.draw(obs1);
		gui.draw(obs2);
		gui.draw(obs3);
		gui.draw(obs4);
		gui.draw(obs5);
		gui.draw(obs6);
		gui.update();
		

	}
	
	private void addPoint(RenderablePolyline l, int i){
		IntPoint p = Environment.coordinates[i];
		String s = Integer.toString(i);
		RenderableString rs = new RenderableString(p.x, p.y + 6, s);
		rs.setProperties(Color.BLUE, new Font(Font.SERIF, Font.BOLD, 12));
		l.addPoint(p.x, p.y);
		gui.draw(rs);
		gui.update();
	}
	
	public void bfs(){
		search_type = SearchEnum.BREADTHFIRSTSEARCH;
	}
	
	public void ucs(){
		search_type = SearchEnum.UNIFORMCOSTSEARCH;
	}
	
	public void gbfs(){
		search_type = SearchEnum.GREEDYBESTFIRSTSEARCH;
	}
	
	public void astar(){
		search_type = SearchEnum.ASTARSEARCH;
	}
	
	public void quit() {
		gui.hide();
		System.exit(0);
	}
	
	public void genLine(int x1, int y1, int x2, int y2) {
		RenderablePolyline p = new RenderablePolyline();
		p.addPoint(x1, y1);
		p.addPoint(x2, y2);
		p.setProperties(Color.ORANGE, 2f);
		gui.draw(p);
		gui.update();

	}
	
	/**
	 * Start the GUI 
	 **/
	public void run()
	{
		gui.show();
	}
	
	/**
	 * Get the parameters from the text fields and use these to set the robot moving 
	 **/
	public void buttonAction() throws InterruptedException {
		
		int startPoint = Integer.parseInt((gui.getTextFieldContent(startID)));
		int goalPoint = Integer.parseInt(gui.getTextFieldContent(goalID));
		// already have the serach type
		
		if (search_type == null){
			System.out.println("Search type not chosen, breadth-first search by default");
			search_type = SearchEnum.BREADTHFIRSTSEARCH;
		}

		go(startPoint, goalPoint);
	}
	
	/**
	 * Set the robot moving towards a goal on the screen with set radius, step size, etc.
	 * @param start The coordinates of the starting point
	 * @param goal The coordinates of the goal
	 * @param goalRad The radius of the goal - if the robot falls within this, it wins
	 * @param robotRadius The width of the robot
	 * @param robotSensorRange How far the robot can 'see'
	 * @param robotSensorDensity The number of sensor lines the robot can use
	 * @param robotSpeed The number of moves per second
	 * */
	public void go(int startPoint, int goalPoint) throws InterruptedException
	{
		//Disable all buttons while robot is active
		gui.setButtonEnabled(buttonId, false);
		gui.setButtonEnabled(breadthFirstSearchID, false);
		gui.setButtonEnabled(uniformCosrtSearchID, false);
		gui.setButtonEnabled(greedyBesrFirstSearchID, false);
		gui.setButtonEnabled(aStarSearchID, false);
		Search s;
		Problem p = new Problem(startPoint, goalPoint);
		ArrayList<Node> result = null;
		switch (search_type) {
		case BREADTHFIRSTSEARCH:
			s = new BreadthFirstSearch(p);
			result = s.search();			
			break;
		case UNIFORMCOSTSEARCH:
			s = new UniformCostSearch(p);
			result = s.search();	
			break;
		case GREEDYBESTFIRSTSEARCH:
			s = new GreedyBestSearch(p);
			result = s.search();	
			break;
		case ASTARSEARCH:
			s = new AStarSearch(p);
			result = s.search();	
			break;
		default:
			break;
		}
		
		
		//Create the robot, start & end points, renderables

		RenderablePolyline path = new RenderablePolyline();
		
		path.setProperties(Color.ORANGE, 2f);
	
		//Draw the initial set up
		gui.clearGraphicsPanel();
		
		if(result == null) {
			//Draw message to let the user know that Rob is stuck
			RenderableString rs3 = new RenderableString(500, 500, "I failed!");
			rs3.setProperties(Color.BLUE, new Font(Font.SERIF, Font.BOLD, 14));
			RenderableString rs = new RenderableString(500, 500, "I failed");
			rs.setLayer(456);
			rs.setProperties(Color.BLUE, new Font(Font.SERIF, Font.BOLD, 32));
			gui.draw(rs);	
			gui.update(); 
			return;
		}
		
		
		//Loop until the robot reaches the goal or gets stuck
		for(Node n: result) {
			Thread.sleep(800);
			int state = (int)(n.getState() - 'a');
			IntPoint to_draw = Environment.coordinates[state];
			path.addPoint(to_draw.x, to_draw.y);
			gui.draw(path);
			gui.update();
			drawObstacles();
			drawRobot(to_draw.x, to_draw.y);
			System.out.println(n.getState());

		}
		
		//Re-enable buttons when finished
		gui.setButtonEnabled(buttonId, true);
		gui.setButtonEnabled(breadthFirstSearchID, true);
		gui.setButtonEnabled(uniformCosrtSearchID, true);
		gui.setButtonEnabled(greedyBesrFirstSearchID, true);
		gui.setButtonEnabled(aStarSearchID, true);
	}
	
	
	/**
	 * Draw the robot
	 * */
	private void drawRobot(int x, int y)  {
		robot = new RenderablePoint(x, y);
		robot.setProperties(Color.RED, 10);
		gui.draw(robot);
		gui.update();
		
	}
	
}
