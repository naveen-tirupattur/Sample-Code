/**
 * Interface for LRU Cache
 */
package my.samples;

import java.util.List;

/**
 * @author Naveen Tirupattur
 *
 */
public interface ILRUCache<K,V> {
	
	//Method to get the value from cache
	V get(K key);
	
	//Method to put key and value into cache
	void put(K key, V value);
	
	//Method to display the elements in cache
	List<V> display();
}
