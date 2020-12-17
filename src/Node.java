//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: October 4, 2020


public class Node {
	private Data current;
	private Node next;
	
	/**
	 * Constructor creates a new Node give a Data object
	 * @param record
	 */
	public Node(Data record) { 
		current = record;
	}
	
	/**
	 * Method setNext will set the next node in the linked list of the current node to the given node
	 * @param nextNode
	 */
	public void setNext(Node nextNode) {
		next = nextNode;
	}
	
	/**
	 * Method getNext will return the next node
	 * @return next
	 */
	public Node getNext() {
		return next;
	}
	
	/**
	 * Method get data will return Data object of the current node
	 * @return data
	 */
	public Data getData() {
		return current;
	}
	
	/**
	 * Method getKey will return the key of the Data object
	 * @return key
	 */
	public String getKey() {
		return current.getKey();
	}
	
	/**
	 * Method getKey will return the score of the Data object
	 * @return score
	 */
	public int getScore() {
		return current.getScore();
	}
	
	/**
	 * Method getKey will return the level of the Data object
	 * @return level
	 */
	public int getLevel() {
		return current.getLevel();
	}
}
