/**
 * Gets a graph from Adjacency Matrix
 */
package maxflow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * @author Naveen Tirupattur
 *
 */
public class GetGraphFromAdjacencyMatrix {
	
	int vertices;
	
	int edges;
	
	int[][] matrix;	
	
	
	public GetGraphFromAdjacencyMatrix(int vertices, String fileName)
	{
		matrix = new int[vertices][vertices];
		this.vertices = vertices;
		read(fileName);
	}
	
	public void read(String fileName)
	{		
		try {

			int row = 0, column = 0;

			BufferedReader reader = new BufferedReader(new FileReader(fileName));			

			String line="";		
			

			//Read the Association Matrix file
			while((line = reader.readLine()) != null)
			{
				StringTokenizer tokenizer = new StringTokenizer(line);

				//Check if each line has as many elements as a row of matrix				
				if(tokenizer.countTokens() != vertices) 
				{
					System.out.println("Error in Association matrix");
					System.exit(1);
					break;
				}
				
				column = 0;
				while(tokenizer.hasMoreTokens())
				{
					//Add elements to Matrix
					matrix[row][column] = (int)Double.parseDouble(tokenizer.nextToken());
					
					if(matrix[row][column] > 0.0) edges++;
					column++;					
				}
				row++;
			}
			reader.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
	}

}
