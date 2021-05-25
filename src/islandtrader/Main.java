//Equivalent to Main

package islandtrader;

import islandtrader.ui.gui.GameEnvironmentSwing;

/**
 * Class where application execution begins. If {@code cmd} is passed as a
 * program argument the application will run as command line application,
 * otherwise a GUI will be used.
 *
 * @author Maria Balzaretti
 *
 */

public class Main {
	/**
	 * Application entry point for the Island Trader application.
	 *
	 * @param args The command line arguments. This application supports a single
	 *             argument: {@code cmd}. If {@code cmd} is present the app will use
	 *             a command line interface. When no argument is specified the app
	 *             will use a graphical interface.
	 */
	public static void main(String[] args) {

		GameEnvironmentSwing game = new GameEnvironmentSwing();
		game.startGame();
	}

}
