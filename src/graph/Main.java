package graph;

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ReadData reader = new ReadData();
		ArrayList<Node> nodesList = reader.read(".\\Data\\target.txt");
		for(Node n: nodesList)
		{
			System.out.println("Node: "+n.getName());
			System.out.println("Neighbors"+n.getNeighborsList().toString());
		}
		
		

	}

}
