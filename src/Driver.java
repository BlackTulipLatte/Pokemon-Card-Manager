//Name: Tyler Zeng
//Assignment: Pokemon Collection Assignment
//Date: November 19, 2021
//Description: Add, remove/manage your pokemon collections and the cards within those collections
import java.util.*;
import java.io.*;
public class Driver {
	public static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException{
		//MAIN MENU
		if (menuNum == 0){
			System.out.println ("----------  MAIN MENU  -----------");
			System.out.println ("1) Accessing your list of albums");
			System.out.println ("2) Accessing within a particular album");
			System.out.println ("3) Exit");
			System.out.println ("----------------------------------");
		}
		//SUB MENU 1
		else if (menuNum == 1){
			System.out.println ("\n---------  SUB-MENU #1  ----------");
			System.out.println ("1) Display a list of all albums");
			System.out.println ("2) Display info on a particular album");
			System.out.println ("3) Add an album");
			System.out.println ("4) Remove an album");
			System.out.println ("5) Show statistics");
			System.out.println ("6) Return back to main menu.");
			System.out.println ("----------------------------------");
		}
		//SUB MENU 2
		else if(menuNum == 2){
			System.out.println ("\n---------  SUB-MENU #2  ----------");
			System.out.println ("1) Display all cards (in the last sorted order) ");
			System.out.println ("2) Display info on a particular card");
			System.out.println ("3) Add a card");
			System.out.println ("4) Remove a card (4 options)");
			System.out.println ("5) Edit attack");
			System.out.println ("6) Sort cards (3 options)");
			System.out.println ("7) Return back to main menu");
			System.out.println ("----------------------------------");
		}
		System.out.print ("\n\nPlease enter your choice:  ");
		int choice = 0;
		try {
			choice = Integer.parseInt (stdIn.readLine ());
		}catch(NumberFormatException e) {
		}
		return choice;
	}

	//Method name: displayAlbum
	//Purpose: Shows the content information of a specific album
	//Parameter: An album arraylist and the album choice
	//Returns: void
	public static void displayAlbum(ArrayList<Album> aList, int n) {
		System.out.println("\n"+aList.get(n));
	}

	//Method name: addAlbum
	//Purpose: Add albums into an album arraylist from a text file
	//Parameter: An album arraylist and the text file name
	//Returns: void
	public static void addAlbum(ArrayList<Album> aList, int n, int m, ArrayList<Card> c, int hp, Date d) {
		Collections.sort(aList);
		//NEED SORTING
		if(Collections.binarySearch(aList, new Album(n, new Date(""),m)) >= 0) {
			System.out.println("Album already in the collection");
		}
		else {
			aList.add(new Album(n,m,c,hp,d));
			System.out.println("Added successfully");	
		}
	}

	//Method name: removeAlbum
	//Purpose: Removes an album from the album list
	//Parameter: An album arraylist and the index
	//Returns: boolean
	public static boolean removeAlbum(ArrayList<Album> aList, int n, int type, String date) {
		int counter = 0;
		if(type == 1) {
			int hasEle = Collections.binarySearch(aList, new Album(n,new Date(""), 0));
			if(hasEle >= 0) {
				aList.remove(hasEle);
				return true;
			}
		}
		else if(type == 2) {
			Collections.sort(aList, new sortByDateAlbum());
			int index = Collections.binarySearch(aList, new Album(0, new Date(date),0), new sortByDateAlbum());
			int begin = index;
			int end = index;
			int lower=0;
			int higher=0;
			if(index >=0) {
				while((end < aList.size()) && (aList.get(index).getDate().getDay()== aList.get(end).getDate().getDay())&&(aList.get(index).getDate().getMonth()== aList.get(end).getDate().getMonth())&& (aList.get(index).getDate().getYear()== aList.get(end).getDate().getYear())) {
					higher = end;
					end++;
				}
				while((begin >= 0 && aList.get(index).getDate().getDay()== aList.get(begin).getDate().getDay())&&(aList.get(index).getDate().getMonth()== aList.get(begin).getDate().getMonth())&& (aList.get(index).getDate().getYear()== aList.get(begin).getDate().getYear())) {
					lower = begin;
					begin--;
				}
			}
			int temp = 0;
			for(int i = lower; i <= higher; i++) {
				aList.remove(i-temp);
				temp++;
				counter++;
			}		
		}		
		if(counter>0)
			return true;
		return false;
	}

	//Method name: displayStats
	//Purpose: Displays the statistics of each album and all the album comined
	//Parameter: An album arraylist
	//Returns: void
	public static void displayStats(ArrayList<Album> aList) {
		if(aList.size() == 0) {
			System.out.println("No albums!");
			return;
		}
		for(int i = 0; i < aList.size(); i++) {
			int totalHp = 0;
			for(int j = 0; j < aList.get(i).getCards().size(); j++) {
				totalHp += aList.get(i).getCards().get(j).getHP();
			}
			totalHp /= aList.get(i).getCards().size();
			System.out.println("Album #" + aList.get(i).getNum() + ": " + aList.get(i).getCards().size() + " cards out of " + aList.get(i).getMaxCap());
			System.out.println("Average HP: " + totalHp+"\n");
		}
		int totalCards = 0;
		int totalCap = 0;
		int allHp = 0;
		for(int i = 0; i < aList.size(); i++) {
			totalCards += aList.get(i).getCards().size();
			totalCap += aList.get(i).getMaxCap();
			for(int j = 0; j < aList.get(i).getCards().size(); j++) {
				allHp += aList.get(i).getCards().get(j).getHP();                
			}
		}
		allHp/=totalCards;
		System.out.printf("All albums: " + totalCards + " out of " + totalCap + "\nAverage HP: " + allHp + "\n\n");
	}

	//Method name: displayAllCards
	//Purpose: Displays the details of every card in a particular album in the last sorted order
	//Parameter: An album arraylist, the album choice, and the previous sort order
	//Returns: void
	public static void displayAllCards(ArrayList<Album>albumList, int alChoice, int prevSort) {
		if(prevSort == 1) 
			Collections.sort(albumList.get(alChoice-1).getCards());  
		else if(prevSort == 2) 
			Collections.sort(albumList.get(alChoice-1).getCards(), new sortByHP());
		else if(prevSort == 3) 
			Collections.sort(albumList.get(alChoice-1).getCards(), new sortByDate());
		for(int i = 0; i < albumList.get(alChoice-1).getCards().size(); i++) {
			System.out.println("Name: "+ albumList.get(alChoice-1).getCards().get(i).getName()+ " || Date: " + albumList.get(alChoice-1).getCards().get(i).getDate() + " || HP: " + albumList.get(alChoice-1).getCards().get(i).getHP());
		}
	}

	//Method name: displayOneCard
	//Purpose: Displays the details of one particular card in the album
	//Parameter: An album arraylist, the album choice, and the card position
	//Returns: void
	public static void displayOneCard(ArrayList<Album>a, int c, int alChoice) {
		System.out.println("\n"+a.get(alChoice-1).getCards().get(c-1)+"\n");	
	}

	//Method name: addCard
	//Purpose: Adds a card to an album
	//Parameter: An album arraylist, album choice, name of card, hp of card, type of card, attack arraylist of card, date of card
	//Returns: void
	public static void addCard(ArrayList<Album>a,int alChoice, String name, int hp, String type, ArrayList<Attack>al, String date) {
		Date d = new Date(date);
		a.get(alChoice-1).getCards().add(new Card(name,hp,type,d,al));
	}

	//Method name: removeCard
	//Purpose: Removes a card from the album based on name, hp, or top or last card in the card arraylist based on previous sort order
	//Parameter: An album arraylist, album choice, the previous sort order, the name/hp to be removed
	//Returns: void
	public static void removeCard(ArrayList<Album>a,int alChoice, int choice, String name) {
		if(choice == 1) {
			int index = Collections.binarySearch(a.get(alChoice-1).getCards(), new Card(name,0,"",new Date("")));
			int begin = index;
			int end = index;
			int lower=0;
			int higher=0;
			if(index >=0) {
				while((end < a.get(alChoice-1).getCards().size()) && a.get(alChoice-1).getCards().get(index).getName().equals(a.get(alChoice-1).getCards().get(end).getName())) {
					higher = end;
					end++;
				}
				while((begin >= 0 && begin < a.get(alChoice-1).getCards().size()) && a.get(alChoice-1).getCards().get(index).getName().equals(a.get(alChoice-1).getCards().get(begin).getName())) {
					lower = begin;
					begin--;
				}
			}
			int temp = 0;
			for(int i = lower; i <= higher; i++) {
				a.get(alChoice-1).setHp(a.get(alChoice-1).getTotalHP()-a.get(alChoice-1).getCards().get((i-temp)).getHP());
				a.get(alChoice-1).getCards().remove(i-temp);
				temp++;
				Album.numCards--;
			}
		}

		else if(choice == 2) {
			int index = Collections.binarySearch(a.get(alChoice-1).getCards(), new Card("",Integer.parseInt(name),"",new Date("")), new sortByHP());
			int begin = index;
			int end = index;
			int lower=0;
			int higher=0;	
			if(index >=0) {
				while((end < a.get(alChoice-1).getCards().size()) && a.get(alChoice-1).getCards().get(index).getHP() ==a.get(alChoice-1).getCards().get(end).getHP()) {
					higher = end;
					end++;
				}
				while((begin >= 0 && begin < a.get(alChoice-1).getCards().size()) && a.get(alChoice-1).getCards().get(index).getHP()==a.get(alChoice-1).getCards().get(begin).getHP()) {
					lower = begin;
					begin--;
				}
			}
			int temp = 0;
			for(int i = lower; i <= higher; i++) {
				a.get(alChoice-1).setHp(a.get(alChoice-1).getTotalHP()-a.get(alChoice-1).getCards().get((i-temp)).getHP());
				a.get(alChoice-1).getCards().remove(i-temp);
				temp++;
				Album.numCards--;
			}
		}
		else if(choice == 3||choice == 4) {
			if(choice == 3) {
				a.get(alChoice-1).setHp(a.get(alChoice-1).getTotalHP()-a.get(alChoice-1).getCards().get(0).getHP());
				a.get(alChoice-1).getCards().remove(0);
				Album.numCards--;
			}
			if(choice == 4) {
				a.get(alChoice-1).setHp(a.get(alChoice-1).getTotalHP()-a.get(alChoice-1).getCards().get(a.get(alChoice-1).getCards().size()-1).getHP());
				a.get(alChoice-1).getCards().remove(a.get(alChoice-1).getCards().size()-1);
				Album.numCards--;
			}
		}
	}

	//Method name: editAttack
	//Purpose: Changes a part of the attack element of a certain card
	//Parameter: An album arraylist, album choice, the card, the attack, the parameter which is being changed, the string that will replace the previous string
	//Returns: void
	public static void editAttack(ArrayList<Album>a, int alChoice, int card, int attack, int which, String edited) {
		if(which == 1) {
			a.get(alChoice-1).getCards().get(card-1).getAttack().get(attack-1).setName(edited);
		}
		else if(which == 2) {
			a.get(alChoice-1).getCards().get(card-1).getAttack().get(attack-1).setDescription(edited);
		}
		else if(which == 3) {
			a.get(alChoice-1).getCards().get(card-1).getAttack().get(attack-1).setDmg(edited);;
		}
	}

	//Method name: sortCards
	//Purpose: Sorts the cards within a particular album given a sorting rule
	//Parameter: An album arraylist, album choice, the previous sort order
	//Returns: void
	public static void sortCards(ArrayList<Album>a, int alChoice, int sortOrder) {
		if(sortOrder == 1) {
			Collections.sort(a.get(alChoice-1).getCards());
		}
		else if(sortOrder == 2) {
			Collections.sort(a.get(alChoice-1).getCards(), new sortByHP());
		}
		else if(sortOrder == 3) {
			Collections.sort(a.get(alChoice-1).getCards(), new sortByDate());
		}
		System.out.println();
		for(int i = 0; i < a.get(alChoice-1).getCards().size();i++) {
			System.out.println(a.get(alChoice-1).getCards().get(i));
			System.out.println();
		}
	}

	//////////////////////////// MAIN METHOD ////////////////////////////
	public static void main (String[] args) throws IOException, FileNotFoundException, NumberFormatException{
		BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
		//Important variables and arraylists
		int mainMenuChoice=0, subMenuChoice=0;
		Scanner in = new Scanner(System.in);
		ArrayList<Album> albumList = new ArrayList<>();
		ArrayList<Card> cardList = new ArrayList<>();
		ArrayList<Attack> atkList = new ArrayList<>();

		//User input
		while(true) {
			//MAIN MENU
			do{
				mainMenuChoice = displayMenu (0, stdIn);
				if(mainMenuChoice == 3)
					System.exit(32);
			}while(mainMenuChoice != 1 && mainMenuChoice != 2);	
			boolean ex = false;
			//SUBMENU #1 AND SUBMENU #2
			//SUBMENU #1///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (mainMenuChoice == 1) {
				while(true) {
					do {
						subMenuChoice = displayMenu (1, stdIn);

						//ALBUM MENU OPTIONS
						//CHOICE 1 ------------- DISPLAY LIST OF ALBUMS
						if(subMenuChoice == 1) {
							if(albumList.size()>0) {
								Collections.sort(albumList);
								for(int i = 0; i < albumList.size(); i++) {
									int pos = i+1;
									System.out.println("#"+pos + ") Album number: " + albumList.get(i).getNum() + " Date: " + albumList.get(i).getDate());
								}
							}
							else {
								System.out.println("No albums!");
							}
							System.out.println();
						}

						//CHOICE 2 ------------- DISPLAY INFORMATION OF A PARTICULAR ALBUM
						else if(subMenuChoice == 2) {
							if(albumList.size() == 0) {
								System.out.println("No albums entered yet!");
							}
							else {
								Collections.sort(albumList);
								for(int i = 0; i < albumList.size(); i++) {
									int pos = i+1;
									System.out.println("#"+pos + ") Album number: " + albumList.get(i).getNum() + " Date: " + albumList.get(i).getDate());
								}
								int input =-1;
								do {
									System.out.print("Choose a position: ");
									try {
										input = Integer.parseInt(in.nextLine());
									}catch(NumberFormatException e) {
									}
								}while(input <= 0 ||input > albumList.size());

								displayAlbum(albumList, input-1);
							}
						}

						//CHOICE 3 ------------- ADD ALBUM TO ARRAYLIST
						else if(subMenuChoice == 3) {

							System.out.print("Enter a file name which contains your album (name.txt): ");
							String fileName = in.nextLine();
							try {
								//BufferedReader
								BufferedReader fileIn = new BufferedReader(new FileReader(fileName));

								//First 3 lines: The album number, the album date, the album max 
								Album newAlbum = new Album(Integer.parseInt(fileIn.readLine()), new Date(fileIn.readLine()),Integer.parseInt(fileIn.readLine()));

								//The number of cards in this album
								int numCards = Integer.parseInt(fileIn.readLine());
								Card newCard;

								//Loops through all the card objects and adds them to a temp card object
								for(int i = 0; i < numCards; i++) {

									//Sets the card object
									newCard = new Card(fileIn.readLine(),Integer.parseInt(fileIn.readLine()),fileIn.readLine(),new Date(fileIn.readLine()));

									//Finds the number of attacks for each card
									int numAttack = Integer.parseInt(fileIn.readLine());

									//Loops through all the attacks for a certain card
									for(int k = 0; k < numAttack; k++) {
										String line = fileIn.readLine();
										Attack atck;
										if(line.indexOf("-") >= 0) {
											atck = new Attack(line.substring(0, line.indexOf("-")), line.substring(line.indexOf("-")+2),fileIn.readLine());
										}
										else {
											atck = new Attack(line, fileIn.readLine());
										}
										atkList.add(atck);
									}
									//Adds everything needed to a card object
									cardList.add(new Card(newCard.getName(), newCard.getHP(), newCard.getType(), newCard.getDate(), atkList));	
									atkList.clear();
								}
								int totalHp = 0;
								for(int i = 0; i < cardList.size();i++) {
									totalHp += cardList.get(i).getHP();
								}
								//Adds album to the album arraylist
								addAlbum(albumList, newAlbum.getNum(), newAlbum.getMaxCap(), cardList, totalHp, newAlbum.getDate());
								cardList.clear();	
							}
							catch(FileNotFoundException e) {
								System.out.println("File not found!");
							}
						}

						//CHOICE 4 ------------- REMOVE AN ALBUM FROM THE COLLECTION
						else if(subMenuChoice == 4) {
							if(albumList.size() == 0) {
								System.out.println("Nothing to remove");
							}
							else {
								int choice = 0;
								do {
									System.out.print("Would you like to remove by album number (1) or by date (2)?: ");
									try {
										choice = Integer.parseInt(in.nextLine());
									}catch(NumberFormatException e) {
									}
								}while(choice != 1 && choice != 2);
								Collections.sort(albumList);
								for(int i = 0; i < albumList.size(); i++) {
									System.out.println("Album number: " + albumList.get(i).getNum() + " Date: " + albumList.get(i).getDate());
								}

								//Remove by album number
								if(choice == 1) {
									int removeNum = 0;
									try {
										while(true) {
											System.out.print("Which album number would you like to remove?: ");
											removeNum = Integer.parseInt(in.nextLine());
											if(removeAlbum(albumList, removeNum, 1, "")) {
												System.out.println("Album removed!");
											}
											else {
												System.out.println("Album number not found!");
											}
											break;
										}
									}
									catch(NumberFormatException e) {
										System.out.println("Invalid input!");
									}
								}

								//Remove by date
								else {
									String inD = "";							
									do{
										System.out.print("Which date would you like to remove?: ");
										inD = in.nextLine();
									}while(inD.length()!=10 || (inD.charAt(2)!= '/' || inD.charAt(5)!='/') || (Integer.parseInt(inD.substring(0,2))<1 ||Integer.parseInt(inD.substring(0,2))>12||Integer.parseInt(inD.substring(inD.indexOf('/')+1,inD.lastIndexOf('/')))>31 || Integer.parseInt(inD.substring(inD.indexOf('/')+1,inD.lastIndexOf('/')))<1));
									if(removeAlbum(albumList, 0,2,inD)) {
										System.out.println("Album removed!");
									}
									else {
										System.out.println("Album not found!");
									}
								}
							}
						}

						//CHOICE 5 ------------- DISPLAY STATISTICS ABOUT THE ALBUM
						else if(subMenuChoice == 5) {
							Collections.sort(albumList);
							displayStats(albumList);
						}

						//CHOICE 6 ------------- BREAK OUT OF THE LOOP
						else if(subMenuChoice == 6) {
							ex = true;
							break;
						}
					}while(subMenuChoice <= 0 || subMenuChoice > 6);
					if(ex) {
						break;
					}
				}
			}
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//SUBMENU #2
			else if (mainMenuChoice == 2) {
				while(true) {
					int prevSort = 0;
					if(albumList.size()>= 1) {
						Collections.sort(albumList);
						for(int i = 0; i < albumList.size(); i++) {
							int pos = i+1;
							System.out.println("#"+pos + ") Album number: " + albumList.get(i).getNum() + " Date: " + albumList.get(i).getDate());
						}

						//User input for the album list they want to alter
						int alChoice = -1;
						do {
							try {
								System.out.print("Choose an album position: ");
								alChoice = Integer.parseInt(in.nextLine());
							}catch(NumberFormatException e) {
							}
						}while(alChoice <= 0 || alChoice > albumList.size());

						do {
							subMenuChoice = -10;
							while(subMenuChoice - 7 < -6 || subMenuChoice - 7 > 0) {
								subMenuChoice = displayMenu (2, stdIn);
							}

							//CHOICE 1 ---------- DISPLAY ALL CARDS
							if(subMenuChoice == 1) {
								//Checks the previous sorted order
								displayAllCards(albumList, alChoice, prevSort);
							}

							//CHOICE 2 ---------- DISPLAY A PARTICULAR CARD
							else if(subMenuChoice == 2) {
								if(albumList.get(alChoice-1).getCards().size()==0) {
									System.out.println("No more cards to remove!");
								}
								else{Collections.sort(albumList.get(alChoice-1).getCards());
								for(int i = 0; i < albumList.get(alChoice-1).getCards().size(); i++) {
									int pos = i+1;
									System.out.println(pos+") " + albumList.get(alChoice-1).getCards().get(i).getName() + " || HP: " + albumList.get(alChoice-1).getCards().get(i).getHP());
								}
								System.out.println();

								int choice = 0;
								do {
									try {
										System.out.print("Choose a card: ");
										choice = Integer.parseInt(in.nextLine());
									}
									catch(NumberFormatException e) {
									}
								}while(choice <= 0 || choice >= albumList.get(alChoice-1).getCards().size()+1);
								displayOneCard(albumList,choice,alChoice);		
								}
							}

							//CHOICE 3 ---------- ADD A CARD
							else if(subMenuChoice == 3) {

								if(albumList.get(alChoice-1).getCards().size()+1>albumList.get(alChoice-1).getMaxCap()) 
									System.out.println("Album is already full!");
								else {
									//Card variables
									ArrayList<Attack> atcL = new ArrayList<>();
									String name ="";
									int hp = 0;
									String type = "";
									int numA =0;
									String atckName = "";
									String atckDesc = "";
									String atckDmg = "";
									String date = "";

									//Name 
									do {
										System.out.print("Enter a name: ");
										name = in.nextLine().trim();
									}while(name.trim().equals(""));

									//Hp
									do {
										try {
											System.out.print("Enter a HP: ");
											hp = Integer.parseInt(in.nextLine());
										}
										catch(NumberFormatException e) {
										}
									}while(hp <= 0);

									//Type
									do {
										System.out.print("Enter a type: ");
										type = in.nextLine().trim();
									}while(type.trim().equals(""));

									//Number of attacks
									do {
										try {
											System.out.print("How many attacks would you like to enter: ");
											numA = Integer.parseInt(in.nextLine());
										}
										catch(NumberFormatException e) {
										}
									}while(numA < 1);

									//Adding each attack
									for(int i = 0; i < numA; i++) {
										//Attack name
										do {
											System.out.print("Enter an attack name: ");
											atckName = in.nextLine().trim();

										}while(atckName.trim().equals(""));

										//Attack desc
										System.out.print("Enter an attack description: ");
										atckDesc = in.nextLine().trim();

										//Attack damage
										do {
											System.out.print("Enter an attack damage: ");
											atckDmg = in.nextLine().trim();
										}while(atckDmg.trim().equals(""));					
										atcL.add(new Attack(atckName,atckDesc,atckDmg));
									}

									//Date
									while(true) {
						
										try {
											do {
												System.out.print("Enter a date: ");
												date = in.nextLine();
											}while(date.length()!=10 || (date.charAt(2)!= '/' || date.charAt(5)!='/') || (Integer.parseInt(date.substring(0,2))<1 ||Integer.parseInt(date.substring(0,2))>12||Integer.parseInt(date.substring(date.indexOf('/')+1,date.lastIndexOf('/')))>31 || Integer.parseInt(date.substring(date.indexOf('/')+1,date.lastIndexOf('/')))<1 || Integer.parseInt(date.substring(6,10))<1900));
											albumList.get(alChoice-1).setHp(albumList.get(alChoice-1).getTotalHP()+hp);
											addCard(albumList,alChoice,name,hp,type,atcL,date);
											atcL.clear();
											Album.numCards++;
											break;
										}
										catch(NumberFormatException e) {
										}
									}
								}
							}

							//CHOICE 4 ---------- REMOVE A CARD
							else if(subMenuChoice == 4) {
								if(albumList.get(alChoice-1).getCards().size()==0) {
									System.out.println("No more cards to remove!");
								}
								else{

									//User input on how the user would like to remove a card
									int removeC = 0;
									do {
										System.out.print("How would you like to remove a card? (1) Name (2) HP (3) First listed card (4) Last listed card: ");
										try {
											removeC = Integer.parseInt(in.nextLine());
										}catch(NumberFormatException e) {
										}
									}while(removeC - 4 < -4 || removeC - 4 > 0);

									//Remove by name
									if(removeC == 1) {
										Collections.sort(albumList.get(alChoice-1).getCards());
										for(int i = 0; i < albumList.get(alChoice-1).getCards().size(); i++) {
											int pos = i+1;
											System.out.println("#"+pos +") " + albumList.get(alChoice-1).getCards().get(i).getName());
										}
										String nameIn = "";
										do {
											System.out.print("Choose a card name: ");
											nameIn = in.nextLine();
										}while((Collections.binarySearch(albumList.get(alChoice-1).getCards(), new Card(nameIn ,0,"", new Date("")))<=-1));
										removeCard(albumList, alChoice, 1, nameIn);
									}

									//Remove by HP
									else if(removeC == 2) {
										Collections.sort(albumList.get(alChoice-1).getCards(), new sortByHP());
										for(int i = 0; i < albumList.get(alChoice-1).getCards().size(); i++) {
											int pos = i+1;
											System.out.println("#"+pos +") " + albumList.get(alChoice-1).getCards().get(i).getName() + " HP: " + albumList.get(alChoice-1).getCards().get(i).getHP());
										}
										int hpIn = 0;
										do {
											try {
												System.out.print("Input a HP: ");
												hpIn = Integer.parseInt(in.nextLine());
											}
											catch(NumberFormatException e) {
											}
										}while((Collections.binarySearch(albumList.get(alChoice-1).getCards(), new Card("" ,hpIn,"", new Date("")), new sortByHP())<=-1));
										removeCard(albumList, alChoice, 2, Integer.toString(hpIn));
									}

									//Remove first card from last sorted order
									else if(removeC == 3) {
										if (prevSort == 0) removeCard(albumList,alChoice,3,"");
										if(prevSort >0) {
											if(prevSort == 1) Collections.sort(albumList.get(alChoice-1).getCards());
											else if(prevSort == 2) Collections.sort(albumList.get(alChoice-1).getCards(), new sortByHP());
											else if(prevSort == 3) Collections.sort(albumList.get(alChoice-1).getCards(), new sortByDate());
											removeCard(albumList,alChoice,3,"");
										}
									}

									//Remove last card from last sorted order
									else if(removeC == 4) {
										if (prevSort == 0) removeCard(albumList,alChoice,4,"");
										if(prevSort >0) {
											if(prevSort == 1) Collections.sort(albumList.get(alChoice-1).getCards());
											else if(prevSort == 2) Collections.sort(albumList.get(alChoice-1).getCards(), new sortByHP());
											else if(prevSort == 3) Collections.sort(albumList.get(alChoice-1).getCards(), new sortByDate());
											removeCard(albumList,alChoice,4,"");
										}
									}		
								}
							}

							//CHOICE 5 ---------- EDIT ATTACK
							else if(subMenuChoice == 5) {
								if(albumList.get(alChoice-1).getCards().size()==0) {
									System.out.println("No cards");
								}
								else {
									for(int i = 0; i < albumList.get(alChoice-1).getCards().size(); i++) {
										int pos = i+1;
										System.out.println("#" + pos + ") " + albumList.get(alChoice-1).getCards().get(i).getName() + " || HP: " + albumList.get(alChoice-1).getCards().get(i).getHP());
									}
									//Choose a card within album
									int chooseCard = 0;
									do {
										try {
											System.out.print("Choose a card: ");
											chooseCard = Integer.parseInt(in.nextLine());
										}catch(NumberFormatException e) {
										}
									}while(chooseCard<=0||chooseCard>albumList.get(alChoice-1).getCards().size());
									System.out.println("\nAttacks: ");
									for(int i = 0; i < albumList.get(alChoice-1).getCards().get(chooseCard-1).getAttack().size(); i++) {
										int pos = i+1;
										System.out.println("#" + pos +") " + albumList.get(alChoice-1).getCards().get(chooseCard-1).getAttack().get(i));
									}

									//Choose the attack of the card to modify
									int atckC = 0;
									do {
										try {
											System.out.print("Choose an attack to modify: ");
											atckC = Integer.parseInt(in.nextLine());
										}catch(NumberFormatException e) {

										}
									}while(atckC <= 0 || atckC > albumList.get(alChoice-1).getCards().get(chooseCard-1).getAttack().size());

									//Choose parameter to edit
									int mod = 0;
									do {
										try {
											System.out.print("Choose a parameter to modify: \n1)Name\n2)Description\n3)Damage\nChoice: ");
											mod = Integer.parseInt(in.nextLine());	
										}catch(NumberFormatException e) {	
										}
									}while(mod<=0||mod>3);		
									String edit ="";
									do {
										System.out.print("Enter new/revised attribute: ");
										edit = in.nextLine().trim();
										if(mod == 2)
											break;
									}while(edit.trim().equals(""));
									editAttack(albumList, alChoice, chooseCard, atckC, mod, edit);
								}
							}

							//CHOICE 6 ---------- SORT
							else if(subMenuChoice == 6) {
								if(albumList.get(alChoice-1).getCards().size()==0) {
									System.out.println("No cards");
								}
								else {
									int sortChoice = 0;
									do {
										try {
											System.out.print("How would you like to sort?\n1)By name\n2)By HP\n3)By date\nChoice: ");
											sortChoice = Integer.parseInt(in.nextLine());
										}catch(NumberFormatException e) {
										}
									}while(sortChoice <= 0 || sortChoice > 3);
									if(sortChoice == 1) {
										prevSort = 1;
										sortCards(albumList, alChoice, 1);
									}
									else if(sortChoice == 2) {
										prevSort = 2;
										sortCards(albumList, alChoice, 2);
									}
									else if(sortChoice == 3) {
										prevSort = 3;
										sortCards(albumList, alChoice, 3);
									}
								}
							}
							//CHOICE 7 ---------- EXIT TO SUBMENU
							else if(subMenuChoice == 7) {
								ex = true;
								break;
							}
						}while(subMenuChoice - 7 > -7 && subMenuChoice - 7 < 1);
						if(ex) {
							break;
						}
					}
					else {
						System.out.println("No album in the collection yet!\n");
						break;
					}
				}
			}
		}
	}
}