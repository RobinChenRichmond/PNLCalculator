/**
 * This class generates output messages based on labels and information string
 * 
 * @author guanyuchen
 *
 */
public class OutputMessageFactory{
	
	/**
	 * take label and information as input and generate a corresponding message
	 * @param label
	 * @param info
	 * @return corresponding Nessage instance
	 * @throws WrongMessageFormatException
	 */
	public Message getMessage(String label, String info) throws WrongFormatException{
		//throw exception if the message has the wrong format
		if(label==null || label.length()==0 || info==null || info.length()==0){
			 throw new WrongFormatException();
		}
		if(label.equals("PNL")){
			return new PNLMessage(info);
		} else{
			throw new WrongFormatException();
		}
		
	}
	
}
