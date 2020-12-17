//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: October 4, 2020


import java.util.*;
public class Dictionary {

	// constant number used to multiply in the polynomial hash function
	private int X = 31;
	// default size of hash table
	private int size = 9973;
	// data items
	private int dataItems = 0;
	private LinkedList<Node>[] hashtable;

	/**
	 * Constructor method creates new hash table
	 */
	public Dictionary() {
		hashtable = new LinkedList[size];
	}
	public Dictionary(int size) {
		this.size = size;
		hashtable = new LinkedList[this.size];
	}

	/**
	 * Method Put adds a new Data record into the hash table
	 * @param record
	 * @return returns 1 if there has been a collision, 0 if no collisions occurred
	 * @throws DuplicatedKeyException
	 */
	public int put (Data record) throws DuplicatedKeyException{

		int returnvalue = 0;
		int hashkey = getHash(record.getKey()); //gets the hash key using the hash function
		Node recordNode = new Node(record); // creates the node that stores the data item

		if (hashtable[hashkey]== null) { //if nothing is recorded yet
			hashtable[hashkey] = new LinkedList<Node>();
			hashtable[hashkey].add(recordNode);
			return returnvalue;
		}

		returnvalue =1; //if there is already something there
		Node currentNode = hashtable[hashkey].getFirst(); 
		if (currentNode.getKey().equals(record.getKey())) throw new DuplicatedKeyException(); //if the first key is the same as the data key, throws exception
		while(currentNode.getNext()!= null){ // goes through each of the remaining keys to check if there are duplicates
			if (currentNode.getKey().equals(record.getKey())) throw new DuplicatedKeyException();
			currentNode = currentNode.getNext();
		}
		// once it goes through every key to ensure no duplicates exist, adds the new record Node
		currentNode.setNext(recordNode);
		dataItems ++;


		return returnvalue;
	}

	/**
	 * Method remove will remove the Node containing the data item with the given key
	 * @param key
	 * @throws InexistentKeyException
	 */
	public void remove (String key) throws InexistentKeyException{
		
		int hashkey = getHash(key); // obtains the hash key from the hash function
		if (hashtable[hashkey] == null) throw new InexistentKeyException(); //if the list is empty	
		
		if (get(key)!=null) { //uses get method to check if the key exists in the hash table
			// if the key does exist, remove the Node containing the key
			Node removeNode = new Node(get(key)); 
			hashtable[hashkey].remove(removeNode);
			dataItems --;

		}
		
		// otherwise, if the key does not exist, throws exception
		else throw new InexistentKeyException();
		
	}

	/**
	 * Method get find the Data with the given key in the hash table
	 * @param key
	 * @return returns the Data item associated with the given key
	 */
	public Data get (String key) {
		int hashkey = getHash(key); // obtains the hash key using the hash function

		if (hashtable[hashkey]== null) return null; //if the list is empty

		// goes through the linked list to check if the key exists
		Node currentNode = hashtable[hashkey].getFirst();
		while(!currentNode.getKey().equals(key)){ // will keep looping while it has not reached the key yet
			currentNode = currentNode.getNext();
			if (currentNode == null) return null; // returns null at the end of the linked list if no key has been found
		} 
		return currentNode.getData();
	}

	/**
	 * Method numDataItems will return the amount of data items in the hash table
	 * @return dataItems
	 */
	public int numDataItems() {
		return dataItems;
	}

	/**
	 * Method get hash will produce a hash key which will be used for storing and retrieving data from the hash table
	 * @param key
	 * @return returns the hash key
	 */
	private int getHash(String key) {
		
		long hash_sum = 0; //stores the sum of the polynomial function
		int current_letter = 0; //used to add the integer value of each char

		// will loop through every string index and using the polynomial function, adds to the sum
 		for (int i=0 ; i<key.length(); i++) {
			current_letter = key.charAt(i); 
			hash_sum += current_letter*Math.pow(X, i%9);
		}

		hash_sum = hash_sum%size; // produces hash key by mod-ing the end sum by the size of the table
		return (int)(hash_sum);
	}


}
