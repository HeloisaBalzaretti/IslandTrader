//Equivalent to Main

package islandtrader;

import islandTraderSwing.ui.gui.GameEnvironmentSwing;

//import islandtrader.core.Trader; //Maybe another class??
/**
 * Class where application execution begins. If {@code cmd} is passed as a
 * program argument the application will run as command line application,
 * otherwise a GUI will be used.
 *
 * @author Maria Balzaretti & Sebastian Sosa
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

//		//Do something Here! Like add the islands and prompt user name.
//		String maybeIslands = "Add something here!";
//
//		IslandTraderUi ui;
//
//		if(args.length > 0 && (args[0].equals("cmd"))) {
//			ui = new CmdLineUi();
//			IslandTrader manager = new IslandTrader(ui, maybeIslands);
//			manager.start();
//		}else {
//			ui = new Gui();
//			IslandTrader manager = new IslandTrader(ui, maybeIslands);
//
//
//		}
		GameEnvironmentSwing game = new GameEnvironmentSwing();
		game.startGame();
	}

}
