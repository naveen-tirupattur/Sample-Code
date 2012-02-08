/**
 * Class to implement LRU Cache
 */
package my.samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Naveen Tirupattur
 *
 */
public class CLRUCache<K,V> implements ILRUCache<K,V> {

	//Node class of DLL to keep track of values in cache
	class Node
	{
		K key;
		V value;
		Node next, prev;
		
		Node(K key, V value)
		{
			this.key = key;
			this.value = value;
		}		
	}
	
	
	//Max size of Cache
	int maxSize;
		
	//Map for the cache
	Map<K,Node> map;
	
	//Nodes to keep track of head and tail of DLL
	Node head, tail;
	
	CLRUCache(int maxSize)
	{
		this.maxSize = maxSize;
		map = new HashMap<K,Node>();
		head = new Node(null,null);
		tail = new Node(null,null);
		head.next = tail;
		tail.prev = head;
	}
	
	//method to add a node to DLL
	private void add(Node head, Node newNode)
	{
		newNode.next = head.next;
		newNode.prev = head;
		head.next.prev = newNode;
		head.next = newNode;
	}
	
	//method to remove a node from DLL
	private void remove(Node node)
	{
		node.next.prev = node.prev;
		node.prev.next = node.next;		
	}
	
	
	//method to get an element from cache
	@Override
	public V get(K key) {
		//get the node from map
		Node node = map.get(key);
		
		//Check if a node exists
		if(node == null) return null;
		
		if(map.size() == 1) return node.value;
		
		//remove the node and add it to front
		remove(node);
		add(head,node);
		
		return node.value;
		
	}

	//method to put an element into cache
	@Override
	public void put(K key, V value) {
		Node node = map.get(key);
		
		//Check if a value exists in cache for given key
		//If yes
		if(node != null)
		{
			//update the value
			node.value = value;
			//put the value into map
			map.put(key, node);
			
			//remove the node and add it to start
			remove(node);
			add(head,node);
		}else
		{
			//Create a new node
			node = new Node(key,value);
			
			//add it to map
			map.put(key,node);
			
			//add it to the head of DLL
			add(head,node);
			
			//Check if size exceeds maxSize
			if(map.size() > this.maxSize)
			{
				//if yes, remove the last node from DLL and map
				Node rNode = tail.prev;
				System.out.println("Removing Key: "+rNode.key+" Value: "+rNode.value);
				
				remove(rNode);
				
				map.remove(rNode.key);
			}
		}
		
	}

	@Override
	public List<V> display() {
		
		List<V> list = new ArrayList<V>();
		
		Node n = this.head.next;
		while(n!=tail)
		{
			list.add(n.value);
			n = n.next;
		}
		
		// TODO Auto-generated method stub
		return list;
	}

}
