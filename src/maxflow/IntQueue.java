package maxflow;


/*************************************************************************
 *  Compilation:  javac IntQueue.java
 *  Execution:    java IntQueue
 *
 *  A queue of type int, implemented using Java's LinkedList data type.
 *
 *************************************************************************/

import java.util.LinkedList;

public class IntQueue {
    private LinkedList list = new LinkedList();

    public boolean isEmpty() { return list.isEmpty(); }

    public void enqueue(int x) { list.addLast(new Integer(x)); }
 
    public int dequeue() {
        Integer a = (Integer) list.removeFirst(); 
        return a.intValue();
    }


    // a test client
    public static void main(String[] args) {
        IntQueue q = new IntQueue();
        q.enqueue(6);
        q.enqueue(9);
        q.enqueue(2);
        System.out.println(q.dequeue());
        q.enqueue(3);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}

