import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
public class Maze 
{
	private String fileName = "";
	private Coordinate startCoordinate;
	private int[][] mazeArray;
	private int numRows;
	private int numCols;
	private boolean isSolved;
	private Solution solution;
	private Stack stepss;
	
	public Maze(String inputName)			// parameterized constructor- sent file name of the txt file from which we have to load no. of rows, no. of columns /n  maze /n starting coordinate 
	{
		fileName = inputName;
		isSolved = false;
		solution = new Solution();
		
	}
	public void createMaze()	//.............TESTING PASSED...............................
	{
		String lineBuffer = "";				// will store each line from the file into the lineBuffer
		String [] mazeLineBuffer;			// lineBuffer ko split kerke mazeLineBuffer[] mein daal diya spilt kerke
		int lineCounter = 0;
		
		try
		{
			BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
			lineBuffer = buffReader.readLine();
			mazeLineBuffer = lineBuffer.split(" ");
			
			numRows = Integer.parseInt(mazeLineBuffer[0]);
			numCols = Integer.parseInt(mazeLineBuffer[1]);
			
			mazeArray = new int[numRows][numCols];
			
			while(buffReader.ready()) 						// ????- but isse last line which conteian sthe starting coordinate only will also be read and staored in the array itself - samajgya done
			{
				lineBuffer = buffReader.readLine();
				mazeLineBuffer = lineBuffer.split(" ");
				
				if(lineCounter < numRows)
				{
					for(int i =0; i<mazeLineBuffer.length; i++)
					{
						mazeArray[lineCounter][i] = Integer.parseInt(mazeLineBuffer[i]);
					}
				}
				else if(lineCounter == numRows)
				{
					startCoordinate = new Coordinate(Integer.parseInt(mazeLineBuffer[0]),Integer.parseInt((mazeLineBuffer[1])));
				}
			
				lineCounter ++;
			}
			
		}
		catch(Exception e)
		{	
			System.out.println("error : "+ e+"\n"+e.getLocalizedMessage());
		}
		
	}

	public void solveMaze()			//.............TESTING PASSED............................... 
	{
		
		Coordinate current = new Coordinate(0,0);					// could have been made just a reference , not an object 
		current = startCoordinate;
		solution.push(current);
		Coordinate retrieved = new Coordinate(0,0);					// could have been made just a reference , not an object 
		
		while(!isSolved)
		{
			retrieved = lookNorth(current);
			if(!(checkSameSpot(retrieved, current)))
			{
				mazeArray[current.getRow()][current.getCol()] = 1;
				current = retrieved ;
				solution.push(current);
				isSolved = checkFinished(current);
				continue;
			}
			
			retrieved = lookEast(current);
			if(!(checkSameSpot(retrieved, current)))
			{
				mazeArray[current.getRow()][current.getCol()] = 1;
				current = retrieved ;
				solution.push(current);
				isSolved = checkFinished(current);
				continue;
			}
			
			retrieved = lookSouth(current);
			if(!(checkSameSpot(retrieved, current)))
			{
				mazeArray[current.getRow()][current.getCol()] = 1;
				current = retrieved ;
				solution.push(current);
				isSolved = checkFinished(current);
				continue;
			}
			
			retrieved = lookWest(current);
			if(!(checkSameSpot(retrieved, current)))
			{
				mazeArray[current.getRow()][current.getCol()] = 1;
				current = retrieved ;
				solution.push(current);
				isSolved = checkFinished(current);
				continue;
			}
			
			// if we've made it to this point, the spots have been the same. that is we have reached the dead end...!
			mazeArray[current.getRow()][current.getCol()] = 2;
			solution.pop();
			current = solution.peek();
			isSolved = checkFinished(current);
		}
		
		solution.buildSteps();
		stepss = solution.getSteps();
		System.out.println("FINISHED");
		while(!stepss.isEmpty())
		{
			Coordinate poped = (Coordinate)stepss.pop();
			System.out.println(poped.getRow() +" "+ poped.getCol());
		}
	
	
	/*	while(!solution.is_Empty())														
		{
			Coordinate poped = solution.pop();
			System.out.println(poped.getRow() +" "+ poped.getCol());
		}
	*/	
	}
	
	private Coordinate lookNorth(Coordinate c)
	{
		if((c.getRow()-1) < 0)
		{
			return c;
		}
		else 
		{
			Coordinate spot = new Coordinate(c.getRow()-1, c.getCol());
			if(checkSafe(spot))
			{
				return spot;
			}
			else 
			{
				return c;
			}
		}
	}
	private Coordinate lookEast(Coordinate c)
	{
		if((c.getCol()+1) == numCols)
		{
			return c;
		}
		else 
		{
			Coordinate spot = new Coordinate(c.getRow(), c.getCol()+1);
			if(checkSafe(spot))
			{
				return spot;
			}
			else 
			{
				return c;
			}
		}
	}
	private Coordinate lookSouth(Coordinate c)
	{
		if((c.getRow()+1) == numRows)
		{
			return c;
		}
		else 
		{
			Coordinate spot = new Coordinate(c.getRow()+1, c.getCol());
			if(checkSafe(spot))
			{
				return spot;
			}
			else 
			{
				return c;
			}
		}
	}
	private Coordinate lookWest(Coordinate c)
	{
		if((c.getCol()-1) < 0)
		{
			return c;
		}
		else 
		{
			Coordinate spot = new Coordinate(c.getRow(), c.getCol()-1);
			if(checkSafe(spot))
			{
				return spot;
			}
			else 
			{
				return c;
			}
		}
	}
	
	private boolean checkSafe(Coordinate c)
	{
		if(mazeArray[c.getRow()][c.getCol()] == 0)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	private boolean checkSameSpot(Coordinate a, Coordinate b)
	{
		if(a.getRow() == b.getRow() && a.getCol() == b.getCol())
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	private boolean checkFinished(Coordinate c)
	{
		if(checkSameSpot(c,startCoordinate))
		{
			return false;
		}
		else
		{
			if(c.getCol() ==0 || c.getCol() ==(numCols -1))
			{
				return true;
			}
			if(c.getRow() ==0 || c.getRow() ==(numRows -1))
			{
				return true;
			}
		}
		return false;
	}
}
