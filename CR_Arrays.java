//Ryan Chang 
//April 13, 2020
//ICS4U1
//Arrays Problem Set

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

// Array Class
class Array {
	private String list[]; // instance array variable
	static Scanner sc = new Scanner(System.in); // scanner object

	// Empty Array Constructor
	public Array() {
		list = new String[0];
	}

	// Array Constructor that accepts a file
	public Array(File text) throws FileNotFoundException {
		int lines = 0;
		Scanner textfile = new Scanner(text);
		// Counts the number of lines in the file
		while (textfile.hasNextLine()) {
			lines++;
			textfile.nextLine();
		}
		textfile.close(); // for better resource mangement

		// Resetting the scanner
		textfile = new Scanner(text);

		// Creates a String array with the length equal to the number of lines
		list = new String[lines];

		// Enters each line as an entry in the array
		for (int count = 0; count < list.length; count++) {
			list[count] = textfile.nextLine();
		}
		textfile.close(); // for better resource management
	}

	// Method that enables the user to directly enter names
	public void enter() {
		String name = ""; // Initializes Name Variable
		System.out.println("Enter names or press 'q' to stop:"); // prompt

		// continue to loop until user enters 'q'
		while (!name.equals("q")) {
			name = sc.nextLine(); // read

			// add the input as long as it wasn't 'q'
			if (!name.equals("q")) {
				add(name);
			}
		}
	}

	// Method that prints the list
	public void print() {
		System.out.println("Current List:"); // title
		int n = 1; // entry counter

		// Cycle through the list
		for (int x = 0; x < list.length; x++) {
			System.out.println(n + ": " + list[x]); // print each element of the list
			n++; // increment entry counter
		}

		// If the list is empty display message
		if (list.length == 0)
			System.out.println("list is empty");
	}

	// Method that adds names
	public void add(String name) {
		String temp[] = new String[list.length + 1]; // create a Larger List

		// Cycle through the list
		for (int i = 0; i < list.length; i++) {
			temp[i] = list[i]; // copy entries
		}
		temp[list.length] = name; // add name to the last entry
		this.list = temp; // reconfigure list
	}

	// Method that removes names
	public void remove(int num) {
		if (list.length > 0) // makes sure the list isn't empty
		{
			String temp[] = new String[list.length - 1]; // Create a Smaller List

			// Cycle through the list up to the removed entry
			for (int i = 0; i < num; i++) {
				temp[i] = list[i]; // copy entries
			}

			// Cycle through the list after the removed entry
			for (int i = num; i < temp.length; i++) {
				temp[i] = list[i + 1]; // copy entries
			}
			list = temp; // reconfigure list
		} else // otherwise display message
			System.out.println("The list is empty");
	}

	// Method that searches the list based on letter of last name
	public Array search(char letter) {
		// Create new array object
		Array newList = new Array();

		// Cycle through the list
		for (int i = 0; i < list.length; i++) {

			// If the letters match up, add the entire entry to newList
			if (list[i].charAt(0) == letter || list[i].charAt(0) + 32 == letter) {
				newList.add(list[i]);
			}
		}
		return newList; // return the list
	}

	// Method that searches the list based on last name
	public Array search(String name) {
		// Searched array object
		Array newList = new Array();

		// Cycle through the entire list
		for (int i = 0; i < list.length; i++) {

			// Creates a string that takes only the last name of each entry
			String entry = list[i].substring(0, list[i].indexOf(','));

			// If the names are the same, add the entire entry to newList
			if (entry.equals(name) == true)
				newList.add(list[i]);
		}
		return newList; // return the list
	}

	// Method that sorts the list in alphabetical order
	public void sort() {
		String temp; // variable declaration

		// Cycle though entire list
		for (int x = 0; x < list.length; x++) {

			// Cycle through rest of the list
			for (int y = x + 1; y < list.length; y++) {

				// If a lower value is found
				if (list[x].compareTo(list[y]) > 0) {
					temp = list[x]; // swap positions
					list[x] = list[y];
					list[y] = temp;
				}
			}
		}
	}
}

//Driver and main method 
class CR_Arrays {
	static Scanner sc = new Scanner(System.in);

	// Method that holds all the functions for the list
	public static void functions(Array list) {
		int choice; // declare choice variable

		// Loop until the user enters 0
		do {
			// Prompts
			System.out.println("\nEnter 1, 2, 3, 4, 5");
			System.out.println("1 - Add Name");
			System.out.println("2 - Remove Name");
			System.out.println("3 - Search List by Letter");
			System.out.println("4 - Search List by Name");
			System.out.println("5 - Sort List in Alphabetical Order");
			System.out.println("0 - Exit Program");

			choice = sc.nextInt(); // read choice

			// Decisions and function calling
			if (choice == 1) {
				System.out.print("Enter a name you want to add: ");
				sc.nextLine();
				list.add(sc.nextLine());
				list.print();
			} else if (choice == 2) {
				System.out.print("Enter the list number you want to remove: ");
				list.remove(sc.nextInt() - 1);
				list.print();
			} else if (choice == 3) {
				System.out.print("Enter the letter you want to search: ");
				sc.nextLine();
				// create a new list that only has the searched values
				Array shortList = list.search(sc.nextLine().charAt(0));
				shortList.print();
			} else if (choice == 4) {
				System.out.print("Enter the name you want to search: ");
				sc.nextLine();
				// create a new list that only has the searched values
				Array shorterList = list.search(sc.nextLine());
				shorterList.print();
			} else if (choice == 5) {
				list.sort();
				list.print();
			}
		} while (choice != 0);
	}

	// Main method
	public static void main(String[] args) {
		int choice; // declare choice variable

		// Loop until user enters 0
		do {
			// Prompts
			System.out.println("Array Problem Set");
			System.out.println("-----------------");
			System.out.println("1 - Enter Names by File Location");
			System.out.println("2 - Enter Names Directly");
			System.out.println("0 - Exit Program");

			choice = sc.nextInt(); // read choice

			if (choice == 1) { // Read file if choice is 1
				System.out.print("Enter file location: "); // prompt
				sc.nextLine();
				String file = sc.nextLine();
				File text = new File(file); // new file object
				// File text = new File("/Users/ryanc/Documents/test.txt"); // alternative input

				try { // if the file location is valid
					Array list = new Array(text); // create new array object that accepts the file
					list.print(); // print array
					functions(list); // loop through functions
				}

				catch (FileNotFoundException e) { // if the file location is not valid
					System.out.print("CAN'T FIND FILE");
				}

			} else if (choice == 2) { // Manually enter file if choice is 2
				Array list = new Array(); // create new array object
				list.enter(); // run the manual enter method
				list.print(); // print array
				functions(list); // loop through functions
			}

		} while (choice != 0);
		System.out.print("The program has terminated"); // end message
	}
}
