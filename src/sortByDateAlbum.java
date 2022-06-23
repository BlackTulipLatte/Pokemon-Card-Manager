//Name: Tyler Zeng
//Assignment: Pokemon Collection Assignment (Card sortByHp())
//Date: November 19, 2021
//Description: An implemented interface which compares two card album's dates
import java.util.*;
public class sortByDateAlbum implements Comparator<Album> {

	//Method name: compare
	//Purpose: Allows binarysearch and collections.sort to know how to sort the objects. Sorts based on the date
	//Parameter: Album object 1 and album object  2.
	//Returns: Integer
	public int compare(Album a1, Album a2) {	
		if(a1.getDate().getYear() == a2.getDate().getYear()) {
			if(a1.getDate().getMonth() == a2.getDate().getMonth()) {
				return a1.getDate().getDay() - a2.getDate().getDay();
			}
			return a1.getDate().getMonth() - a2.getDate().getMonth();
		}
		return a1.getDate().getYear() - a2.getDate().getYear();
	}

}
