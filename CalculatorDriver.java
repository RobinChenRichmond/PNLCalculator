import java.io.FileNotFoundException;

/**
 * The driver works as a switch to initialize the calculator and execute the calculation. 
 * The driver first reads the fills and prices files either from parameters or by default from 
 * files named "fills" and "prices". It then initialize the calculator with the given files, and 
 * finish the calculation.
 * 
 * @author guanyuchen
 *
 */
public class CalculatorDriver {
	/**
	 * The main method takes in the arguments as input parameters, and goes through
	 * the whole program
	 * @param args
	 * @throws WrongFileFormatException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String []args){
		String fills = args.length>1?args[0]:"fills";
		String prices = args.length>1?args[1]:"prices";
		PNLCalculator p = new PNLCalculator(fills,prices);
		p.calculate();
	}
}
