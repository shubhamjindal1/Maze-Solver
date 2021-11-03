import java.util.Stack;

public class Solution 
{
	private Stack solution;
	private Stack steps;
	
	public Solution()
	{
		solution = new Stack();
	}
	
	public void buildSteps()														//........
	{
		steps = new Stack();
		Stack tempSolution = new Stack();
		tempSolution = solution;
		while(!tempSolution.isEmpty())
		{
			steps.push(tempSolution.pop());
		}
	}
	
	public void push(Coordinate c)
	{
		solution.push(c);
	}
	
	public Coordinate pop()
	{
		return (Coordinate)solution.pop();
	}
	
	public Coordinate peek()
	{
		return (Coordinate) solution.peek();
	}
	
	public Stack getSteps()															//.....................
	{
		return steps;
	}
	public boolean is_Empty()
	{
		if(solution.isEmpty())
			return true;
		else 
			return false;
	}
}
