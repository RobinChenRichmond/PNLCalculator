/**
 * Handle the Exception that message and file has wrong format.
 * 
 * @author guanyuchen
 *
 */
public class WrongFormatException extends Exception{
	private static final long serialVersionUID = 1L;

	public WrongFormatException(){
		super();
		System.err.println("Wrong input format.");
	}
}
