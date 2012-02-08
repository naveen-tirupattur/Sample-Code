package maxflow;

/*********************************************************************
 *
 *  This code is adapted from "Algorithms in Java, Third Edition,   
 *  by Robert Sedgewick, Addison-Wesley, 2004. 
 *
 *********************************************************************/

public class Network {
    private int V;       // number of vertices
    private int E;       // number of edges
    private Node adj[];  // adjacency lists

    private class Node {
        Edge e;
        Node next;
        Node(Edge e, Node next) { this.e = e; this.next = next; }
    }


    // empty graph with V vertices
    public Network(int V) {
        this.V = V;
        this.E = 0;
        adj = new Node[V];
    }

    // random graph with V vertices and E edges
    public Network(int V, int E) {
        this.V = V;
        this.E = 0;
        adj = new Node[V];
        while (this.E < E) {
            int v        = (int) (Math.random() * V);
            int w        = (int) (Math.random() * V);
            int capacity = (int) (Math.random() * 100000);
            insert(v, w, capacity);
        }
    }

    // random graph with V vertices and E edges
    public Network(int V, int E, int[][] adjacencyMatrix) {
        this.V = V;
        this.E = 0;
        adj = new Node[V];
        while (this.E < E) {
        	
        	for(int i=0;i<V;i++)
        	{
        		for(int j=0;j<V;j++)
        		{
        			 insert(i, j, adjacencyMatrix[i][j]);
        		}
        	}
           
        }
    }

    // accessor methods
    public int V() { return V; }
    public int E() { return E; }

    // insert edge e
    public void insert(int v, int w, int capacity) {
        Edge e = new Edge(v, w, capacity);
        adj[v] = new Node(e, adj[v]);
        adj[w] = new Node(e, adj[w]); 
        E++;
    } 


    // return iterator for adjacency list
    public IntIterator neighbors(int v) {
        return new AdjListIterator(adj[v]);
    }

    // iterator for adjacency list
    private class AdjListIterator implements IntIterator {
        private Node x;
        AdjListIterator(Node x)   { this.x = x;       }
        public boolean hasNext()  { return x != null; }
        public Edge next() { 
            Edge e = x.e;
            x = x.next;
            return e;
        }
    }


    // string representation of Graph - takes quadratic time
    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        String s = "";
        s += "V = " + V + NEWLINE;
        s += "E = " + E + NEWLINE;
        for (int v = 0; v < V; v++) {
            String t = v + ": ";
            IntIterator i = neighbors(v);
            while(i.hasNext()) {
                Edge w = i.next();
                t += w + " ";
            }
            s += t + NEWLINE;
        }
        return s;
    }


}
