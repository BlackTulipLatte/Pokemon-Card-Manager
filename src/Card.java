//Name: Tyler Zeng
//Assignment: Pokemon Collection Assignment (Card class)
//Date: November 19, 2021
//Description: Deals with altering the card object within an album

import java.util.*;
public class Card implements Comparable<Card>{

	//Variables
	private String name;
	private int hp;
	private String type;
	private Date date;
	private ArrayList<Attack> attackList = new ArrayList<>();

	//Constructor
	//Method name: Card
	//Purpose: Allows a Card object to be created
	//Parameter: Name, hp, type, date
	//Returns: n/a
	public Card (String n, int h, String t, Date d) {
		name = n;
		hp = h;
		type = t;
		date = d;
	}

	//Constructor
	//Method name: Card
	//Purpose: Allows a Card object to be created
	//Parameter: Name, hp, type, date, arraylist of attack
	//Returns: n/a
	public Card (String n, int h, String t, Date d, ArrayList<Attack>a) {
		name = n;
		hp = h;
		type = t;
		date = d;
		attackList = new ArrayList<>(a);
	}

	//Setters and getters
	public int getHP() {
		return hp;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Date getDate() {
		return date;
	}

	public ArrayList<Attack> getAttack() {
		return attackList;
	}

	//Method name: compareTo
	//Purpose: Allows binarysearch and collections.sort to know how to sort the objects. Sorts based on the name
	//Parameter: A card object
	//Returns: Integer
	public int compareTo(Card c) {
		return this.name.compareToIgnoreCase(c.name);
	}

	//Method name: toString
	//Purpose: Allows System.out.println() to know how to output the object
	//Parameter: n/a
	//Returns: String
	public String toString() {
		String s = String.format("Name: %s%nHP: %d%nType: %s%nAttacks:",name,hp,type);
		for(int i = 0; i < attackList.size(); i++) {
			int atckNum = i+1;
			s += String.format("%n%d)", atckNum);
			if(attackList.get(i).getDescription() == null) {
				s += String.format("Name: %s%nDescription: None%nDamage: %s", attackList.get(i).getName(), attackList.get(i).getDmgAmt());
			}else {
				s += String.format("Name: %s%nDescription: %s%nDamage: %s", attackList.get(i).getName(), attackList.get(i).getDescription(), attackList.get(i).getDmgAmt());	
			}
		}
		s += String.format("%nDate: %02d/%02d/%d", date.getMonth(), date.getDay(), date.getYear());
		return s;
	}
}
