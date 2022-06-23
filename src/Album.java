//Name: Tyler Zeng
//Assignment: Pokemon Collection Assignment (Album class)
//Date: November 19, 2021
//Description: Deals with altering the albums of the pokemon collection
import java.util.*;
public class Album implements Comparable<Album>{

	//Variables
	private int albumNum;
	private int maxCards;
	private int totalHp;
	private Date date;
	private ArrayList<Card> cardList = new ArrayList<>(); 
	static int numCards = 0;

	//Constructor
	//Method name: Album
	//Purpose: Allows an Album object to be created
	//Parameter: album number, max cap, arraylist of cards, total hp, date
	//Returns: n/a
	public Album(int num, int max, ArrayList<Card>c, int h, Date d) {
		albumNum = num;
		maxCards = max;
		date = d;
		totalHp = h;
		cardList = new ArrayList<>(c);
		numCards += c.size();
	}

	//Constructor
	//Method name: Album
	//Purpose: Allows an Album object to be created
	//Parameter: album number, max cap, date
	//Returns: n/a
	public Album(int n, Date d, int m) {
		albumNum = n;
		date = d;
		maxCards = m;
	}

	//Setters and getters
	public int getNum() {
		return albumNum;
	}

	public int getMaxCap() {
		return maxCards;
	}

	public ArrayList<Card> getCards(){
		return cardList;
	}

	public int getTotalHP() {
		return totalHp;
	}

	public Date getDate() {
		return date;
	}

	public void setHp(int n) {
		totalHp = n;
	}

	//Method name: compareTo
	//Purpose: Allows binarysearch and collections.sort to know how to sort the objects. Sorts based on the album number
	//Parameter: An album object
	//Returns: Integer
	public int compareTo(Album a) {
		return this.albumNum - a.albumNum;
	}

	//Method name: toString
	//Purpose: Allows System.out.println() to know how to output the object
	//Parameter: n/a
	//Returns: String
	public String toString() {
		return String.format("Album #: %d%nDate: %s%nMax Capacity: %d%n# cards: %d%nTotal HP: %d%n", albumNum, date, maxCards, cardList.size(), totalHp);
	}
}
