//Name: Tyler Zeng
//Assignment: Pokemon Collection Assignment (Date calss)
//Date: November 19, 2021
//Description: Deals with altering the attack object within a card object
import java.util.*;
public class Date {	

	//Variables
	private int day;
	private int month;
	private int year;

	//Constructor
	//Method name: Date
	//Purpose: Allows a Date object to be created
	//Parameter: String
	//Returns: n/a
	public Date(String s) {
		if(s.length()>=4) {
			month = Integer.parseInt(s.substring(0,s.indexOf("/")));
			day = Integer.parseInt(s.substring(s.indexOf("/")+1,s.lastIndexOf("/")));
			year = Integer.parseInt(s.substring(s.lastIndexOf("/")+1));
		}
	}

	//Setters and getters
	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getYear() {
		return year;
	}

	//Method name: toString
	//Purpose: Allows System.out.println() to know how to output the object
	//Parameter: n/a
	//Returns: String
	public String toString() {
		return String.format("%02d/%02d/%4d", month, day, year);
	}

}
