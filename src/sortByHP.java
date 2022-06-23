//Name: Tyler Zeng
//Assignment: Pokemon Collection Assignment (Card sortByHp())
//Date: November 19, 2021
//Description: An implemented interface which compares two card object's hp

import java.util.Comparator;
public class sortByHP implements Comparator<Card>{

	//Method name: compare
	//Purpose: Allows binarysearch and collections.sort to know how to sort the objects. Sorts based on the hp
	//Parameter: Card object 1, card object 2
	//Returns: Integer
	public int compare(Card c1, Card c2) {
		return c1.getHP()-c2.getHP();
	}

}
