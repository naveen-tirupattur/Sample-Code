package graph;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to store details of a Node
 */

/**
 * @author Naveen
 *
 */
public class Node {
	
	//Store name of node
	public String name;
	
	//Store the neighbors of node
	public HashMap<Integer, ArrayList<Node>> neighborsList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<Integer, ArrayList<Node>> getNeighborsList() {
		return neighborsList;
	}
	public void setNeighborsList(HashMap<Integer, ArrayList<Node>> neighborsList) {
		this.neighborsList = neighborsList;
	}
}
