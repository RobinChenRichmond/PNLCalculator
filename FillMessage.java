import java.math.BigDecimal;

/**
 * This class stores the information of a single new fill, with the
 * timestamp, symbol name, the price of fill, the size of fill, and whether 
 * the fill is to buy or to sell.
 * 
 * @author guanyuchen
 *
 */
public class FillMessage extends Message{
	private BigDecimal fillPrice;
	private int fillSize;
	private Boolean buy;
	
	/**
	 * the constructor for fill that reads in a line as string and 
	 * build up the information
	 * @param fill
	 */
	public FillMessage(String[] fill){
		super(Long.parseLong(fill[1]),fill[2]);
		this.fillPrice = new BigDecimal(fill[3]);
		this.fillSize = Integer.parseInt(fill[4]);
		this.buy = fill[5].equals("B")?true:false;
	}
	
	/**
	 * get the current price for the fill
	 * @return fillPrice
	 */
	public BigDecimal getFillPrice(){
		return fillPrice;
	}
	
	/**
	 * get the buy/sell size for the fill
	 * @return fillSize
	 */
	public int getFillSize(){
		return fillSize;
	}
	
	/**
	 * determine if this fill is buying
	 * @return buy
	 */
	public boolean isBuy(){
		return buy;
	}
	
	
}
