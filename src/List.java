/**
 * Double-link List. Uses {@link ListNode} for list nodes.
 */
public final class List<K, V> {
	
	private ListNode<K, V> firstNode;
	private ListNode<K, V> lastNode;

	/**
	 * constructor creates an empty List
	 * @param listName the list name
	 */
	public List() {
		firstNode = lastNode = null;
	}

	/**
	 * Inserts an element at the front of the list
	 * @param insertItem the inserted listNode
	 */
	public void insertAtFront(ListNode<K,V> insertItem) {
		
		if (isEmpty()) // firstNode and lastNode refer to same object
			firstNode = lastNode = insertItem;
		
		else { // firstNode refers to new node
			insertItem.setNextNode(firstNode);
			firstNode.setPreviouNode(insertItem); 
			firstNode = insertItem;			
		}
	}
	
	/**
	 * Moves the node given as the parameter to the 
	 * head of the list
	 */
	public void moveToHead(ListNode<K,V> node){
		
		if(!node.equals(firstNode)){
			node.getPreviousNode().setNextNode(node.getNextNode());
			
			if(!node.equals(lastNode)){
				node.getNextNode().setPreviouNode(node.getPreviousNode());
			}else{
				lastNode = lastNode.getPreviousNode();
				lastNode.setNextNode(null);
			}
			insertAtFront(node);
		}
	}

	/**
	 * Removes the given node
	 * @param node
	 * @return true if the node was removed properly
	 */
	public boolean remove(ListNode<K,V> node){
			
		if (node == firstNode){
			firstNode = node.getNextNode();
			return true;
		}
		
		ListNode<K,V> current = firstNode.getNextNode();
		
		while(current != null){
			if (current.equals(node)){
				node.getPreviousNode().setNextNode(node.getNextNode());
				return true;
			}
			current = current.getNextNode();
		}
		return false;
	}
	
	/**
	 * Checks if the list contains this key
	 * @param key the key to search
	 * @return the node with the specific key, null otherwise
	 */	
	public ListNode<K,V> contains(K key) {
		
		ListNode<K, V> current = firstNode;
		
		while(current != null){
			if (current.key.equals(key))
				return current;
			current = current.getNextNode();
		}
		
		return null;
	}
	
	
	/**
	 * Returns and removes the node from the list tail
	 * @return the node that was removed from the list tail
	 * @throws EmptyListException if the list is empty
	 */
	public ListNode<K, V> removeFromBack() throws EmptyListException {
		
		if ( isEmpty() )
			throw new EmptyListException();

		ListNode<K, V> removedItem = lastNode;

		if ( firstNode == lastNode )
			firstNode = lastNode = null;
		
		else {
			ListNode<K,V> node = lastNode.getPreviousNode(); 
			node.setNextNode(null); 
			lastNode = node; 
		}

		return removedItem; 
	
	} 

	/**
	 * Determine whether list is empty
	 * @return true if list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return firstNode == null; 
	} 

	/**
	 * @return firstNode, the head of the list
	 */
	public ListNode<K,V> getFirstNode() {		
		return firstNode;
	}
	
	/**
	 * @return lastNode, the tail of the list
	 */
	public ListNode<K,V> getLastNode() {		
		return lastNode;
	}
	
	/**
	 * Set the value of the head of the list
	 * with the given node
	 * @param firstNode should be ListNode<K,V> type
	 */
	public void setFirstNode(ListNode<K,V>  firstNode){
		this.firstNode = firstNode;
	}
	
}
