//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: October 4, 2020

@SuppressWarnings("serial")
public class DuplicatedKeyException extends Exception{
	public DuplicatedKeyException() {
		System.out.println("there is already information registered for this key."); //prints this when exception is thrown
	}
}
