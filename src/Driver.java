
public class Driver 
{
	public static void main(String args[])
	{
		
		System.out.println("Starting");
		Maze m = new Maze("C://Users//Shubham jindal//projects//project-1_maze//src//maze1.txt");
		m.createMaze();
		m.solveMaze();
	}
}
