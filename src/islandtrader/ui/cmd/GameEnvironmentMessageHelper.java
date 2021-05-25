package islandtrader.ui.cmd;

import java.util.Scanner;

public class GameEnvironmentMessageHelper {
	public String readInput(String message) {
		printMessage(message);
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

	public void printMessage(String message) {
		System.out.println(message);
	}

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
