//Name: Tyler Zeng
//Assignment: Pokemon Collection Assignment (Card sortByDate())
//Date: November 19, 2021
//Description: An implemented interface which compares two card object's dates

import java.util.*;
public class sortByDate implements Comparator<Card>{

	//Method name: compare
	//Purpose: Allows binarysearch and collections.sort to know how to sort the objects. Sorts based on the date
	//Parameter: Card object 1, card object 2
	//Returns: Integer
	public int compare(Card a1, Card a2) {	
		if(a1.getDate().getYear() == a2.getDate().getYear()) {
			if(a1.getDate().getMonth() == a2.getDate().getMonth()) {
				return a1.getDate().getDay() - a2.getDate().getDay();
			}
			return a1.getDate().getMonth() - a2.getDate().getMonth();
		}
		return a1.getDate().getYear() - a2.getDate().getYear();
	}
}
