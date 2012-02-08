/**
 * 
 */
package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import graph.Node;

/**
 * @author Naveen
 *
 */
public class ReadData {

	public ArrayList<Node> readNodeNames(String fileName)
	{

		ArrayList<Node> nodesList = new ArrayList<Node>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = "";
			while((line = reader.readLine())!= null){

				Pattern p = Pattern.compile("0 - [A-Za-z]");

				Matcher m = p.matcher(line);

				if(m.find())
				{
					StringTokenizer tokenizer = new StringTokenizer(line);					

					StringBuffer tokens = new StringBuffer();
					while(tokenizer.hasMoreTokens())
					{
						tokens.setLength(0);
						tokens.append(tokenizer.nextToken());

						Pattern subP = Pattern.compile("[A-Za-z]");							

						Matcher subM = subP.matcher(tokens.toString());

						if(subM.find())
						{
							Node n = new Node();
							n.setName(tokens.toString().toLowerCase());
							nodesList.add(n);
						}

					}
					tokens = null;					
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nodesList;
	}


	public ArrayList<Node> read(String fileName)
	{
		ArrayList<Node> nodesList = readNodeNames(fileName);	

		try {

			BufferedReader reader = new BufferedReader(new FileReader(fileName));			

			for(int i=0;i<nodesList.size();i++)
			{
				Node node = nodesList.get(i);

				HashMap<Integer,ArrayList<Node>> nodesMap = new HashMap<Integer, ArrayList<Node>>();

				

				for(int j=0;j<nodesList.size();j++)
				{
					String line = "";
					if((line = reader.readLine())!= null)
					{
						Pattern p = Pattern.compile("[0-9] - [A-Za-z]");
						Matcher m = p.matcher(line);

						if(m.find())
						{
							String subStr = line.substring(m.start());

							/*System.out.println("Distance:"+subStr.substring(0,subStr.indexOf("-")).trim());
						System.out.println("Node:"+subStr.substring(subStr.indexOf("-")+1).trim());*/

							Integer distance = Integer.parseInt(subStr.substring(0,subStr.indexOf("-")).trim());

							Node n = new Node();
							n.setName(subStr.substring(subStr.indexOf("-")+1).trim().toLowerCase());

							ArrayList<Node> adjacentNodesList = nodesMap.get(distance);
							if(adjacentNodesList == null)
							{
								adjacentNodesList = new ArrayList<Node>();

							}
							adjacentNodesList.add(n);
							nodesMap.put(distance, adjacentNodesList);

						}					
					}	
					node.setNeighborsList(nodesMap);

				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodesList;
	}

}
