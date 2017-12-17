/**
 * This class stores the basic information of a message
 * 
 * @author guanyuchen
 *
 */
public abstract class Message{
	long timestamp;
	String symbolName;
	
	/**
	 * initialize the Message with timestamp and name of symbol
	 * @param timestamp
	 * @param symbolName
	 */
	public Message(long timestamp, String symbolName){
		this.timestamp = timestamp;
		this.symbolName = symbolName;
	}
	
	/**
	 * get the timestamp of the message
	 * @return timestamp
	 */
	public long getTime(){
		return timestamp;
	}
	
	/**
	 * get the symbol name of the message
	 * @return symbolName
	 */
	public String getName(){
		return symbolName;
	}
}
