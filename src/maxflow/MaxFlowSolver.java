package maxflow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*********************************************************************
 *
 *  This code is adapted from "Algorithms in Java, Third Edition,
 *  by Robert Sedgewick, Addison-Wesley, 2004.
 *
 *********************************************************************/

public class MaxFlowSolver {
	private final static int INFINITY = Integer.MAX_VALUE;

	private Network G;
	private int s, t;
	private int[] dist;
	private Edge[] pred; 
	private int value;

	// max flow in network G from s to t
	public MaxFlowSolver(Network G, int s, int t) {
		this.G = G;
		this.s = s;
		this.t = t;
		this.value = 0;
		dist = new int[G.V()];
		pred = new Edge[G.V()];

		// while there exists an augmenting path, use it
		while (augpath()) {

			// compute bottleneck capacity
			int bottle = INFINITY;
			for (int v = t; v != s; v = ST(v)) {
				bottle = Math.min(bottle, pred[v].capRto(v));
			}

			// augment flow
			for (int v = t; v != s; v = ST(v))
				pred[v].addflowRto(v, bottle); 

			value = value + bottle;
		}
	}


	// helper method to follow augmenting path backwards
	private int ST(int v) { return pred[v].other(v); }


	// return value of max flow
	public int value()  { return value; }


	// is there an augmenting path?
	// if so, use pred[] to store it
	private boolean augpath() {
		IntQueue q = new IntQueue();
		for (int v = 0; v < G.V(); v++) {
			pred[v] = null;
			dist[v] = INFINITY;
		}

		// breadth-first search
		dist[s] = 0;
		q.enqueue(s);
		while (!q.isEmpty()) {
			int v = q.dequeue();

			IntIterator i = G.neighbors(v);
			while(i.hasNext()) {
				Edge e = i.next();
				int w = e.other(v);

				// if residual capacity from v to w
				if (e.capRto(w) > 0) {
					if (dist[w] > dist[v] + 1) {
						dist[w] = dist[v] + 1;
						pred[w] = e;
						q.enqueue(w);
					}
				}
			}
		}

		// does augmenting path exist?
		return (dist[t] < INFINITY);
	}



	// string representation - inefficient because of string concat
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		String out = "";
		out += "Max flow from " + s + " to " + t + NEWLINE;
		out += "Max flow value = " + value() + NEWLINE;
		for (int v = 0; v < G.V(); v++) {
			IntIterator i = G.neighbors(v);
			while(i.hasNext()) {
				Edge e = i.next();
				if (e.from(v) && e.flow() > 0) out += "   " + e + NEWLINE;
			}
		}
		return out;
	}




	// test client that creates random network, solves max flow, and prints results
	public static void main(String[] args) {

		GetGraphFromAdjacencyMatrix get = new GetGraphFromAdjacencyMatrix(4, ".\\Data\\Test.txt");

		System.out.println("Number of Vertices: "+get.vertices);
		System.out.println("Number of Edges: "+get.edges);

		int V = get.vertices;
		int E = get.edges; 

		int[][] maxFlowMatrix = new int[V][V];


		/*Network G = new Network(5,20);*/

		//System.out.println(G);

		/*MaxFlowSolver maxflow = new MaxFlowSolver(G, 0, 4);
		System.out.println(maxflow);*/

		for(int i=0;i<V;i++)
		{
			for(int j=0;j<V;j++)
			{
				if(i != j)
				{
					Network G = new Network(V, E, get.matrix);			
					MaxFlowSolver maxflow = new MaxFlowSolver(G, i, j);
					maxFlowMatrix[i][j] = maxflow.value();
					System.out.println(maxflow);
				}else if(i==j)
					maxFlowMatrix[i][j] = 0;    		  

			}
		}

		writeToFile(maxFlowMatrix, ".\\Data\\MaxFlow.txt");

	}

	public static void writeToFile(int[][] mFMatrix, String fileName)
	{
		try {			
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			for(int i=0;i<mFMatrix.length;i++)
			{
				for(int j=0;j<mFMatrix[0].length;j++)
				{
					writer.write(Integer.toString(mFMatrix[i][j])+ " ");					
				}
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
