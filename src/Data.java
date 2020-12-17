//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: October 4, 2020

public class Data {
	
	// initialize variables stored in Data
	private String key;
	private int score;
	private int level;
	
	/**
	 * Constructor method will store the given key, score and level 
	 * @param key
	 * @param score
	 * @param level
	 */
	public Data(String key, int score, int level) {
		this.key = key;
		this.score = score;
		this.level = level;
	}
	
	/**
	 * Method getKey will return the key of the data item
	 * @return key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Method getKey will return the score of the data item
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Method getKey will return the level of the data item
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
}
