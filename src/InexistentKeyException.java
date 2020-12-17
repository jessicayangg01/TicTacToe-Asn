//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: October 4, 2020


@SuppressWarnings("serial")
public class InexistentKeyException extends Exception {
	public InexistentKeyException() {
		System.out.println("the key does not exist"); //prints this when exception us thrown
	}
}
