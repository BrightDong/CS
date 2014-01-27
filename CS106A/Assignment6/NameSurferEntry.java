/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/**
 * Creates a new NameSurferEntry from a data line as it appears in the
 * data file.  Each line begins with the name, which is followed by
 * integers giving the rank of that name for each decade.
 */
	public NameSurferEntry(String line) {
		// You fill this in
		//stringEntry = line;
		entry = new String[NELEMENT];
		String[] lineSplit = line.split(" ");
		for(int i = 0; i < NELEMENT; i++)  //TODO: need do this???maybe yes!!!
			entry[i] = lineSplit[i];		
	}

/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation
		//return null;
		return entry[0];
	}

/**
 * Returns the rank associated with an entry for a particular decade.
 * The decade value is an integer indicating how many decades have passed
 * since the first year in the database, which is given by the constant
 * START_DECADE.  If a name has a rank below 1000, the rank value is 0.
 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation
		//return 0;
		int index = (decade - STARTYEAR) / 10 + 1;
		int rank = Integer.parseInt(entry[index]);
		return rank;		
	}

/**
 * Returns a string that makes it easy to see the value of a NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation
		String str;
		str = "[ ";
		for(int i = 0; i < NELEMENT; i++)
			str += entry[i] + " ";
		str += " ]";
		return str;
		//return "";
	}
	private String entry[];
	private final int NELEMENT = 13;
	private final int STARTYEAR = 1900;
	//private String stringEntry;
}
