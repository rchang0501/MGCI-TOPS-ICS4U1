/*Ryan Chang 
ICS4U1
February 13, 2020
Methods Set - Returning Values */

import java.util.*;

public class CR_Methods {
	static Scanner in = new Scanner(System.in);

	//Accepts a double radius, then calculates and returns the area of the circle with that radius
	public static double areaCircle(double radius) {
		return Math.PI * Math.pow(radius, 2); // returns value for the area
	}

	//Accepts an integer, then returns true if the integer is odd
	public static boolean odd(double num) {
		if (num % 2 != 0){
			return true;} // return true if num is odd
		else {
			return false;} // return false is num is even 
	}

	//Accepts an integer month, then returns the name of the corresponding month
	public static String monthName(int month) {
		if (month == 1)
			return "January"; 
		else if (month == 2)
			return "February"; 
		else if (month == 3)
			return "March";
		else if (month == 4)
			return "April";
		else if (month == 5)
			return "May";
		else if (month == 6)
			return "June";
		else if (month == 7)
			return "July";
		else if (month == 8)
			return "August";
		else if (month == 9)
			return "September";
		else if (month == 10)
			return "October";
		else if (month == 11)
			return "November";
		else if (month == 12)
			return "December";
		else
			return "invalid"; // if not a proper month number 
	}

	//Accepts an integer month, then returns the number of days in the corresponding month
	public static int daysinMonth(int month) {
		if (month < 1 || month > 12)
			return 0; // for improper months
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;} // for months that have 31 days
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;} // for months that have 30 days
		else {
			return 28;} // for February (without the leap-year) 
	}

	//Accepts a minimum and maximum integer, then generates and returns a random value in that range (inclusive)
	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min); 
	}

	//Generates a random number and returns an 'H' or 'T' each 50% of the time
	public static char flip() {
		int num = random(0, 1);
		if (num == 1) {
			return 'T';} // returns 'T' if num is 1
		if (num == 0) {
			return 'H';} // returns 'H' if num is zero
		else {
			return 'O';} // returns 'O' if num is neither
	}

	//Generates a random number and returns a corresponding upper-case letter of the alphabet
	public static char randomLetter(int asciiLetter) {
		return (char) asciiLetter; 
	}

	// Menu starts here
	public static void main(String[] args) {
		int choice;
		do {
			//Prompting for choice 
			System.out.println("");
			System.out.println("Choose from the following menu:\n");
			System.out.println("Enter 1, 2, 3");
			System.out.println("1 - Circle Area");
			System.out.println("2 - Month Days");
			System.out.println("3 - Random Flips and Letters");
			System.out.println();
			System.out.println("Enter 0 to exit");
			System.out.println("");
			choice = in.nextInt();

			// Circle Area
			if (choice == 1) {
				// Prompt of radius
				System.out.print("Enter the radius of circle: ");
				double radius = in.nextDouble();

				// Use the areaCircle method to calculate area
				double area = areaCircle(radius);

				// conditional outputs
				if (odd(radius) == true) {
					System.out.println("The radius is odd "); // output if radius is odd
				} else
					System.out.println("The radius is even "); // output if radius is ever
				System.out.printf("Area = %.1f square units \n", area); // area to one decimal place
			}

			// Month Days
			else if (choice == 2) {
				System.out.printf("%-20s%-15s\n", "Month", "Days");
				for (int i = 1; i <= 12; i++) { // print month and # of days for each additional month
					System.out.printf("%-20s%-15s\n", monthName(i), daysinMonth(i)); 
				}
			}

			// Random Flips and Letters
			else if (choice == 3) {
				//Random Coin Flips
				System.out.print("10 coin flips: ");
				int tailsNum = 0; 
				for (int j = 1; j <= 10; j++) {
					char coin = flip(); // flip a coin
					System.out.print(coin); // display results (either heads or tails)
					if (flip() == 'T')
						tailsNum++; // count number of times tails was the result 
				}
				System.out.print(" " + (tailsNum * 10) + "% tails"); // output for the % of tails
				System.out.println();
				//Random Letters 
				System.out.print("Five random 4-letter combos: ");
				for (int k = 1; k <= 5; k++) {
					for (int a = 1; a <= 4; a++) {
						System.out.print(randomLetter(random(65, 90))); // generates random letter between A to Z using the ascii
					}
					System.out.print(" ");
				}
				System.out.println("");
			}
		} while (choice != 0);
		System.out.println("The program has terminated"); // End Prompt
	}
}
