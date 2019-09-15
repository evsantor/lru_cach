/**
 * Simple implementation of a HashTable. It uses a 2 maps, the first one
 * stores just one element in every cell. If we have a collision, the element
 * is stored with a different hashmap function in the second map that uses lists
 * in case of more collisions
 */
public final class HashMap<K, V> {
		 
	public List<K, V>[] map1;
	public List<K, V>[] map2;
	private List<K, V> listOfEntries = new List<K, V>();
	
	/**
	 * keeps the number of items currently inserted
	 */
	private int size;
	
	/**
	 *keeps the maximum size a cache should have 
	 */
	private static int maximumSize;
	private final static float LF = 0.025f; //load factor --> small to favor speed over space
	
	/**
	 * Class constructor, initialize the maps with arrays 
	 * @param arraySize1
	 * @param arraySize2
	 * @param cacheSize
	 */
	@SuppressWarnings("unchecked")
	public HashMap(int arraySize1, int arraySize2, int cacheSize) {
		
		maximumSize = cacheSize;
		int size1 = (int) (arraySize1/LF);
		int size2 = (int) (arraySize2/LF);
		this.map1 = (List<K, V>[]) new List[size1];
		this.map2 = (List<K, V>[]) new List[size2];
		
		for(int i = 0; i < size1; i++)
			map1[i] = new List<K, V>();
		
		for(int i = 0; i < size2; i++)
			map2[i] = new List<K, V>();

		size = 0;

	}

	/**
	 * Insert a list node with the specific key an value to the main list
	 * and then to the right map
	 * @param key 
	 * @param value
	 */
	public void add(K key, V value){	

		if (isFull() && listOfEntries != null)
			removeLRU();
		
		int hash = hashfunction1(key);
		ListNode<K, V> insertedNode = new ListNode<K, V>(key, value);			
		listOfEntries.insertAtFront(insertedNode);
		size++;
		
		if (map1[hash].isEmpty())
			map1[hash].insertAtFront(insertedNode);
		
		else{
			hash = hashfunction2(key);
			if (map2[hash] == null)
				map2[hash] = new List<K, V>();

			map2[hash].insertAtFront(insertedNode);
		}

		
		
	}
	
	/**
	 * Removes the least recently used item from the central list of entries
	 * and finally its reference from the hash table
	 */
	protected void removeLRU() {
		
		ListNode<K,V> removedNode = listOfEntries.removeFromBack(); //get lru
		size--;
		
		int hash = hashfunction1(removedNode.getKey());		
	
		if (!map1[hash].isEmpty()){
			if (map1[hash].remove(removedNode) == true) // removal done
				return;
		}
		
		hash = hashfunction2(removedNode.getKey());	
		if (!map2[hash].isEmpty()){
			if (map2[hash].remove(removedNode) == true) // removal done
				return;
		}
		
	}

	/**
	 * Search the element with the specific key in maps,
	 * move it in the front of the list because it is the most
	 * recently used, and returns it's value
	 * @param key
	 * @return the value of the key we are looking for
	 */
	public V search(K key){
		
		ListNode<K,V> node = null;
		
		int hash = hashfunction1(key);
		if (!map1[hash].isEmpty()){
			if (map1[hash].getFirstNode().key.equals(key))			
				node = map1[hash].getFirstNode();
		}
		
		hash = hashfunction2(key);
		if (!map2[hash].isEmpty())
			node = map2[hash].contains(key);
		
		if (node != null){
			listOfEntries.moveToHead(node);
			return node.getValue();
		}
		return null;
	}
	
	public boolean isFull(){
		return size == maximumSize;
	}
	
	/**
	 * Get the number of items inserted into the hash table
	 * @return the number of inserted items  
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Calculates the position the given key should have in map1,
	 * according to our hash function
	 * @param key
	 * @return the index of the array
	 */
	public int hashfunction1(K key){
		return Math.abs(key.hashCode() % map1.length);
	}
	
	/**
	 * Calculates the position the given key should have in map2,
	 * according to our hash function
	 * @param key
	 * @return the index of the array
	 */
	public int hashfunction2(K key){
		return Math.abs(key.hashCode() % map2.length);
	}
}
