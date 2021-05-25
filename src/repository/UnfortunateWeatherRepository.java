/**
 * Repository package contain all the classes used to read the CSV files that contain the
 *  game Objects information, name, description etc.
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import islandtrader.core.UnfortunateWeather;

/**
 * Represents a database for the UnfortunateWeather RandomEvent, it is used to
 * create an ArrayList of type UnfortunateWeather, with information from
 * UnfortunateWeather.csv. Each constant property represents a property located
 * at the index of the sublist that will be used to build the UnfortunateWeather
 * object.
 *
 * @author Maria Heloisa Balzaretti
 *
 */
public class UnfortunateWeatherRepository extends BaseRepository {

	/**
	 * The file path to the Unfortunate Weather CSV file with the information about
	 * all the unfortunate weather possible situations for the game
	 */
	private final String UNFORTUNATE_WEATHER_CSV_FILEPATH = "csvFiles/unfortunateWeather.csv";

	/**
	 * A helper method to the getUnfortunateWeather( unfortunateWeatherList) and
	 * used to get the list of sublists of Strings that represent the properties for
	 * the UnfortunateWeather. It removes the first line that contains the
	 * headers/names of properties.
	 */
	@Override
	public ArrayList<UnfortunateWeather> getList() {
		List<List<String>> unfortunateWeatherList = getResultListfromFile(UNFORTUNATE_WEATHER_CSV_FILEPATH);
		unfortunateWeatherList.remove(0);
		return getUnfortunateWeather(unfortunateWeatherList);
	}

	/**
	 * This method is used to convert the String List unfortunateWeatherList to the
	 * UnfortunateWeather type, creating a new instance of UnfortunateWeather for
	 * each sublist and adding it to the ArrayList and so creates the
	 * UnfortunateWeather "database" for the project. uses the previously set
	 * constants to set the right properties for each instance of
	 * UnfortunateWeather.
	 *
	 * @param unfortunateWeatherList
	 * @return ArrayList of UnfortunateWeather objects
	 */
	private ArrayList<UnfortunateWeather> getUnfortunateWeather(List<List<String>> unfortunateWeatherList) {
		ArrayList<UnfortunateWeather> unfortunateWeather = new ArrayList<>();
		for (List<String> weather : unfortunateWeatherList) {
			int idNumber = Integer.parseInt(weather.get(ID_NUMBER));
			String name = weather.get(NAME);
			String description = weather.get(DESCRIPTION);

			UnfortunateWeather newunfortunateWeather = new UnfortunateWeather(idNumber, name, description);
			unfortunateWeather.add(newunfortunateWeather);
		}
		return unfortunateWeather;
	}
}
