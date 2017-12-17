import java.math.BigDecimal;

/**
 * This class stores the information of a PNL Message, with 
 * the timestamp, the symbol name, the size of owned symbol, and the 
 * total cash. It also enables the function to calculate the total 
 * PNL value, and to print out the whole message.
 * 
 * @author guanyuchen
 *
 */
public class PNLMessage extends Message{
	private int sizeOwned;
	private BigDecimal totalCash;
	
	/**
	 * the constructor initializes a PNL message with only timestamp
	 * and the name of the symbol
	 * @param price
	 */
	public PNLMessage(String symbolName){
		super(0,symbolName);
		this.sizeOwned = 0;
		this.totalCash = new BigDecimal(0);
	}
	
	/**
	 * get the total PNL by adding the total cash and the mark to market 
	 * value of current holding
	 * @param price
	 * @return the total PNL value
	 */
	public BigDecimal getPNL(PriceMessage price){
		return totalCash.add(price.getPrice().multiply(new BigDecimal(sizeOwned)));
	}
	
	/**
	 * set the timestamp to a new time
	 * @param newTime
	 */
	public void setTime(long newTime){
		timestamp = newTime;
	}
	
	/**
	 * update the owned size of certain symbol according to 
	 * the fill
	 * @param fill
	 */
	public void updateOwnedSize(FillMessage fill){
		// decrease the size if the fill is buying, otherwise increase the size
		if(fill.isBuy()){
			sizeOwned-=fill.getFillSize();
		} else{
			sizeOwned+=fill.getFillSize();
		}
	}
	
	/**
	 * update the toal cash of certain symbol according to
	 * the fill
	 * @param fill
	 */
	public void updateCash(FillMessage fill){
		// increase the total cash if the fill is buying, otherwise decrease the cash
		if(fill.isBuy()){
			totalCash = totalCash.add(fill.getFillPrice().multiply(new BigDecimal(fill.getFillSize())));
		} else{
			totalCash = totalCash.subtract(fill.getFillPrice().multiply(new BigDecimal(fill.getFillSize())));
		}
	}
	
	/**
	 * combine all the information for a PNL message and print the message out
	 * @param price
	 */
	public void printMessage(PriceMessage price){
		System.out.println("PNL "+Long.toString(timestamp)+" "+symbolName+" "+Integer.toString(sizeOwned)+" "+getPNL(price));
	}
}
