import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class reads in the fills and prices files and keeps track of the messages
 * in files with scanners
 * 
 * @author guanyuchen
 *
 */
public class MessageReader {
	private InputMessageFactory input;
	private Scanner fillReader;
	private Scanner priceReader;
	
	/**
	 * initialize the reader with fills and prices files
	 * @param fills
	 * @param prices
	 */
	public MessageReader(File fills, File prices){
		input = new InputMessageFactory();
		try {
			fillReader = new Scanner(fills);
			priceReader = new Scanner(prices);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * read the next line of fills file to generate a message of fill
	 * @return a message of fill
	 * @throws WrongMessageFormatException
	 */
	public FillMessage readFill() throws WrongFormatException{
		return (FillMessage) input.getMessage(fillReader.nextLine());
	}
	
	/**
	 * check if the fills file has the next line to read
	 * @return true if the file has next line to read
	 */
	public boolean hasFill(){
		return fillReader.hasNextLine();
	}
	
	/**
	 * read the next line of prices file to generate a message of price
	 * @return a message of price
	 * @throws WrongMessageFormatException
	 */
	public PriceMessage readPrice() throws WrongFormatException{
		return (PriceMessage) input.getMessage(priceReader.nextLine());
	}
	
	/**
	 * check if the prices file has the next line to read
	 * @return true if the file has next line to read
	 */
	public boolean hasPrice(){
		return priceReader.hasNextLine();
	}
	
	/**
	 * close all the scanners
	 */
	public void closeAll(){
		fillReader.close();
		priceReader.close();
	}
}
