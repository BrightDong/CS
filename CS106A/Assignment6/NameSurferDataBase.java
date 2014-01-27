import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		// You fill this in
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader(filename));
		} catch (IOException ex) {
			System.out.println("can not open that file");
		}
		entries = readLineArray(rd);
		
	}
	private String[] readLineArray(BufferedReader rd) {
		ArrayList<String> lineList = new ArrayList<String>();
		try {
			while(true) {
				String line = rd.readLine();
				if(line == null) break;
				lineList.add(line);
			}
			rd.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
		String[] result = new String[lineList.size()];
		for(int i = 0; i < result.length; i++) {
			result[i] = lineList.get(i);
		}
		return result;
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation
		for(int i = 0; i < entries.length; i++) {
			if(entries[i].contains(name)) {
				return new NameSurferEntry(entries[i]);
			}
		}
		return null;
	}
	
	private String entries[];
}
