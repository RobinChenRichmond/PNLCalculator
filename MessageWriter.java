/**
 * This class writes out the message in PNL message forms
 * 
 * @author guanyuchen
 *
 */
public class MessageWriter {
	private OutputMessageFactory output;
	public MessageWriter(){
		output = new OutputMessageFactory();
	}
	
	/**
	 * write a initialized form of PNL message with given symbol name
	 * @param symbolName
	 * @return a PNL message
	 * @throws WrongMessageFormatException
	 */
	public PNLMessage writePNL(String symbolName) throws WrongFormatException{
		return (PNLMessage)output.getMessage("PNL",symbolName);
	}
}
