//Name: Tyler Zeng
//Assignment: Pokemon Collection Assignment (Attack class)
//Date: November 19, 2021
//Description: Deals with altering the attack object within a card object
public class Attack {

	//Variables
	private String name;
	private String description;
	private String dmgAmt;

	//Constructor
	//Method name: Attack
	//Purpose: Allows an Attack object to be created
	//Parameter: Name, description, dmg
	//Returns: n/a
	public Attack(String n, String d, String dmg) {
		name = n.trim();
		description = d.trim();
		dmgAmt = dmg.trim();
	}


	//Constructor
	//Method name: Attack
	//Purpose: Allows an Attack object to be created
	//Parameter: Name, dmg
	//Returns: n/a
	public Attack (String n, String d) {
		name = n;
		dmgAmt = d;
	}

	//Getters and setters
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDmgAmt() {
		return dmgAmt;
	}

	public void setName(String s) {
		name = s;
	}

	public void setDescription(String s) {
		description = s;
	}

	public void setDmg(String s) {
		dmgAmt = s;
	}

	//Method name: toString
	//Purpose: Allows System.out.println() to know how to output the object
	//Parameter: n/a
	//Returns: String
	public String toString() {
		return name + "\n" + description + "\n" + dmgAmt;
	}

}
