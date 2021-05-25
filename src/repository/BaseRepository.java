/**
 * Repository package contain all the classes used to read the CSV files that contain the
 *  game Objects information, name, description etc.
 */
package repository;

/**
 * package to help read the CSV files
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import islandtrader.core.Entity;

/**
 * An Abstract class that represents a Basic Repository.It helps mocking a
 * Database to create the Entities used in the project(Ship, Island, Store, Item
 * and so on). Used to read the CSV files, which contains information to build
 * these Entities. Each line in the CSV file is used to build an object, with
 * each String in the file representing a property of some object in the
 * project, i.e Item or Store. As the first three informations of each line
 * repeat, they belong to the base repository ID_NUMBER, NAME, DESCRIPTION.
 * Other properties that will vary will be treated in each particular
 * repository.
 *
 * @author Maria Heloisa Balzaretti
 */
public abstract class BaseRepository {
	/**
	 * DELIMITER is the character used to delimiter the strings in the CSV files,
	 *
	 */
	private final static String DELIMITER = "\\|";

	/**
	 * This Item idNumber is at index 0 of the sublist in the specific get method of
	 * each repository that will return the Array of the relevant type
	 */
	protected final int ID_NUMBER = 0;
	/**
	 * this Item name is at index 1 of the sublist in the specific get method of
	 * each repository that will return the Array of the relevant type
	 */
	protected final int NAME = 1;

	/**
	 * this Item description is at index 2 of the sublist in the specific get method
	 * of each repository that will return the Array of the relevant type
	 */
	protected final int DESCRIPTION = 2;

	/**
	 * This method is used to read the CSV files.
	 *
	 * @param filePath is the filepath of the CSV files with the relevant
	 *                 information to build the objects.
	 * @return a resulting list that contains sublists of Strings that are separated
	 *         by a comma.
	 */
	public List<List<String>> getResultListfromFile(String filePath) {
		List<List<String>> resultList = new ArrayList<>();
		InputStream stream = getClass().getResourceAsStream(filePath);
		try (InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
			BufferedReader reader = new BufferedReader(streamReader);
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(DELIMITER);
				resultList.add(Arrays.asList(values));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * This method enforce that every classRepository that uses the baseRepository
	 * to create its own arrayList of type Entity and return the Array with the
	 * proper type, i.e ItemRepository will be used to read the files and return an
	 * ArrayList of all Items in the database, to be added to the Stores later.
	 *
	 * @return ArrayList of the necessary Entity
	 */
	public abstract ArrayList<? extends Entity> getList();

}
