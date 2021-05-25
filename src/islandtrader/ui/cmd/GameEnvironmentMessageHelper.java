package islandtrader.ui.cmd;

import java.util.Scanner;

/**
 * Class to help the messages in the command line
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class GameEnvironmentMessageHelper {
	/**
	 *
	 * @param message to be printed
	 * @return the input read
	 */
	public String readInput(String message) {
		printMessage(message);
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

	/**
	 *
	 * @param message to be printed
	 */
	public void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * read the input from user
	 *
	 * @param message to be read
	 * @return the number from user input
	 */
	public int readInputInteger(String message) {
		int intValue = -1;
		try {
			intValue = Integer.parseInt(readInput(message));
		} catch (NumberFormatException e) {
			intValue = -1;
		}
		return intValue;
	}
}
