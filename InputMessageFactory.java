/**
 * This class generates input messages based on the input string
 * 
 * @author guanyuchen
 *
 */
public class InputMessageFactory {
	
	/**
	 * convert a string message into corresponding Message instance
	 * @param message
	 * @return corresponding Message instance
	 * @throws WrongMessageFormatException
	 */
	public Message getMessage(String message) throws WrongFormatException{
		//throw exception if the message has the wrong format
		if(message==null || message.length()==0){
			throw new WrongFormatException();
		}
		String[] messageInfo = message.split(" ");
		if(messageInfo[0].equals("P")){
			return new PriceMessage(messageInfo);
		} else if(messageInfo[0].equals("F")){
			return new FillMessage(messageInfo);
		} else{
			throw new WrongFormatException();
		}
	}
	
}
