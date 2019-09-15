/**
 * ListNode represents a double-link list node
 * Each node contains a value of type V, a key of type K, a reference to the nextNode in the list
 * and a reference to the previous one. 
 */
class ListNode<K, V> {
	
	V value;
	K key;
	
	private ListNode<K, V> nextNode;
	private ListNode<K, V> previousNode;

	/**
	 * Constructor. It initializes key and value of the object and sets next node to null 
	 * @param object a reference to node's data
	 */
	ListNode(K key, V value){
		this( key, value, null);
	} 
	
	/**
	 * constructor creates ListNode with passed data and next node
	 * @param object the reference to node's data
	 * @param node the next node in the list
	 */
	ListNode(K key, V value, ListNode<K,V> node ){
		this.key = key;
		this.value = value;
		previousNode = nextNode;
		nextNode = node;
	}

	/**
	 * @return the reference to node's value
	 */
	public V getValue(){
		return value; 
	}

	/**
	 * @return the reference to node's key
	 */
	public K getKey(){
		return key; 
	}
	
	/**
	 * Get reference to next node
	 * @return the next node
	 */
	ListNode<K, V> getNextNode(){
		return nextNode; 
	} 
	
	/**
	 * Get reference to previous node
	 * @return the previous node
	 */
	ListNode<K, V> getPreviousNode(){
		return previousNode; 
	} 
	
	/**
	 * sets the value of the next node
	 * @param nextNode should be ListNode<K,V> type
	 */
	public void setNextNode(ListNode<K,V> nextNode){
		this.nextNode = nextNode;
	}
	
	/**
	 * sets the value of the previous node
	 * @param nextNode should be ListNode<K,V> type
	 */
	public void setPreviouNode(ListNode<K,V> previousNode){
		this.previousNode = previousNode;
	}
	
	/**
	 * Checks if the given node equals to the node 
	 * of the object we call this method
	 * @param node
	 * @return true if equals, false otherwise
	 */
	public boolean equals(ListNode<K,V> node){
		return (this.key.equals(node.getKey())) ? true : false;
	}
} 