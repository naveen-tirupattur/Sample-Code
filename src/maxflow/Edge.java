package maxflow;

public class Edge {
    private int v;       // from
    private int w;       // to 
    private int cap;     // capacity
    private int flow;    // flow

    public Edge(int v, int w, int cap) {
        this.v    = v;
        this.w    = w;  
        this.cap  = cap;
        this.flow = 0;
    }

    public int cap()  { return cap;  }
    public int flow() { return flow; }

    public boolean from(int v)            { return this.v == v;                 }  
    public int other(int v)               { return from(v) ? this.w : this.v;   } 
    public int capRto(int v)              { return from(v) ? flow : cap - flow; }
    public void addflowRto(int v, int d)  { flow += from(v) ? -d : d;           }

    public String toString() {
        return v + "-" + w + " " + flow + "/" + cap;
    }
}
