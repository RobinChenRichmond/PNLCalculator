import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This class reads in the fills and prices files, calculates the number 
 * of symbols, and finally prints out the PNL messages as the prices update.
 * 
 * @author guanyuchen
 *
 */
public class PNLCalculator {
	private int numOfSymbols;
	private MessageReader reader;
	private MessageWriter writer;

	
	/**
	 * Initialize the PNLCalculator with file names and calculate the number of symbols
	 * @param fills
	 * @param prices
	 */
	public PNLCalculator(String fills, String prices){
        reader = new MessageReader(new File(fills),new File(prices));
        writer = new MessageWriter();
    	numOfSymbols = calculateNumberOfSymbols(prices);
	}
	
	
	/**
	 * Scan the first several lines of the prices file to confirm the total
	 * number of symbols in the file
	 * @param prices
	 */
	public int calculateNumberOfSymbols(String prices){
        int count = -1;
        Scanner priceReader = null;
        // store the number of different timestamps
        Set<String> timestamps = new HashSet<>();
        
        try{
        	// read the file
        	priceReader = new Scanner(new File(prices));
        	
        	// stop the loop when more than 1 timestamps appears
        	while(priceReader.hasNextLine() && timestamps.size()<2){
        		String[] text = priceReader.nextLine().split(" ");
        		timestamps.add(text[1]);
        		count++;
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        priceReader.close();
		return count;
	}
	
	
	/**
	 * Read the fills and prices files, and print out the PNL messages as each time the prices
	 * update
	 */
	public void calculate(){
        // count the iterations of timestamp
        int iteration = 0;
        
        // store the prices and PNL messages for each symbol
    	Map<String,PriceMessage> priceMessages = new HashMap<>();
    	Map<String,PNLMessage> PNLMessages = new HashMap<>();

    	FillMessage upcomingFill = null; 
    	long priceCurrTime = -1;
        
        try {
        	while(reader.hasPrice()){
        		// update all the prices in the same timestamp
        		List<String> updateOrder = new ArrayList<>();
        		for(int i = 0; i < numOfSymbols; i++){
        			// read and store the new price's update
        			PriceMessage p = reader.readPrice();
        			priceMessages.put(p.getName(), p);
            		
            		// store the order of prices' updates in a list
            		updateOrder.add(p.getName());
            		
            		// update the current timestamp for price
            		priceCurrTime = p.getTime();
        		}
        		
        		// initialize the PNL messages in the first iteration
        		if(iteration == 0){
        			for(String s:priceMessages.keySet()){
        				PNLMessages.put(s, writer.writePNL(s));
        			}
        		}
        		
        		// if there's a stored upcoming fill, we first update the PNL message according
    			// to the upcoming fill
    			if(upcomingFill!=null){
    				updatePNLMessage(PNLMessages,upcomingFill);
    				upcomingFill = null;
    			}
    				
    			long fillCurrTime = -1;
    			while(fillCurrTime < priceCurrTime && reader.hasFill()){
    				// read the file and create the fill
    				FillMessage currFill = reader.readFill();
            		
    				// if current timestamp goes beyond the prices time, we store the fill
        			// and wait for the price to update
            		if(currFill.getTime()>priceCurrTime){
            			upcomingFill = currFill;
            		} else{
            			updatePNLMessage(PNLMessages,currFill);
            		}
            		
            		// update the current timestamp for fill
            		fillCurrTime = currFill.getTime();
    			}

        		// update the PNL messages' timestamp and print the messages according to 
        		// the prices updates' order
        		for(int i = 0; i < updateOrder.size(); i++){
        			PNLMessage m = PNLMessages.get(updateOrder.get(i));
        			PriceMessage p = priceMessages.get(updateOrder.get(i));
        			m.setTime(priceCurrTime);
    				m.printMessage(p);
                }
        		
        		// update the number of iterations
        		iteration++;
        	}
        	reader.closeAll();
        }catch (WrongFormatException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * works as a helper method to update the corresponding PNL message based on the given
	 * Fill message
	 * @param PNLMessages
	 * @param fill
	 */
	private void updatePNLMessage(Map<String, PNLMessage> PNLMessages, FillMessage fill) {
		PNLMessage temp = PNLMessages.get(fill.getName());
		temp.updateOwnedSize(fill);
		temp.updateCash(fill);
	}
}
