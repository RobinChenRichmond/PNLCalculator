import java.math.BigDecimal;

/**
 * This class stores the information for a single price update, with
 * the timestamp, symbol name and the updated price.
 * 
 * @author guanyuchen
 *
 */
public class PriceMessage extends Message{
	private BigDecimal price;
	
	/**
	 * the constructor for price that reads in a line as string and 
	 * build up the information
	 * @param price
	 */
	public PriceMessage(String[] price){
		super(Long.parseLong(price[1]),price[2]);
		this.price = new BigDecimal(price[3]);
	}

	/**
	 * get the price
	 * @return price
	 */
	public BigDecimal getPrice(){
		return price;
	}
}
