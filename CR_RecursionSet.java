//Ryan Chang 
//February 24, 2020
//ICS4U1
//Recursion Methods Problem Set 

import java.util.*;

public class CR_RecursionSet {

	static Scanner in = new Scanner(System.in);

	// Determines if the inputed number is prime using the checkPrime method
	public static boolean prime(int n) {
		if (checkPrime(n, n / 2)) // input the number and n/2 for optimal time results
			return true;
		else
			return false;
	}

	// Determines whether or not the number is prime recursively
	public static boolean checkPrime(int n, int d) {
		if (n < 2) // eliminates obvious non-primes
			return false;
		else if (n % d == 0 && d != 1) // eliminates numbers with factors other than 1
			return false;
		else if (n % d != 0) // recursively subtract 1 from d to check for factors
			return checkPrime(n, d - 1);
		else // if all conditions are passed, the number is a prime
			return true;
	}

	// Prompt and output results for prime
	public static void primeDriver() {
		int n;
		System.out.println("Enter a positive integer");
		n = in.nextInt();
		if (n < 0) { // eliminates non-positive integers
			System.out.println("Invalid number");
			primeDriver();
		} else if (prime(n)) { // calls method
			System.out.println("It is a prime number");
		} else {
			System.out.println("It is not a prime number");
		}
	}

	// Prompt and output results for perrin sequence (recursive)
	public static void perrinDriver() {
		int num;
		System.out.println("Enter a positive number: ");
		num = in.nextInt();
		if (num < 0) { // eliminates non-positive integers
			System.out.println("Invalid number");
			perrinDriver();
		} else {
			System.out.println("The perrin value is " + perrin(num));
		}
	}

	// Recursive method that calculates the value of the perrin sequence for num
	public static int perrin(int num) {
		if (num == 0) // Check for 0, return 3
			return 3;
		else if (num == 1) // Check for 1, return 0
			return 0;
		else if (num == 2) // Check for 2, return 2
			return 2;
		else
			return (perrin(num - 2) + perrin(num - 3)); // Calculate the recursive sequence 
	}

	// Perrin sequence solution without recursion (using loops)  
	public static void perrinLoops() {

		//Variable declaration 
		int num, x = 3, y = 0, z = 2, w = 0;

		//Prompt for num 
		System.out.println("Enter a positive number: ");
		num = in.nextInt();
		if (num < 0) { //eliminates non-positive values 
			System.out.println("Invalid number");
			perrinLoops();
		}
		
		//Outputs 
		else {
			if (num == 0) // Check for 0, output 3
				System.out.println("The perrin value is 3");
			else if (num == 1) // Check for 1, output 0
				System.out.println("The perrin value is 0");
			else if (num == 2) // Check for 2, output 2
				System.out.println("The perrin value is 2");
			while (num > 2) { // Loop to calculate perrin value 
				w = x + y;
				x = y;
				y = z;
				z = w;
				num--;
			}
			System.out.println("The perrin value is " + w);
		}
	}

	//Main method 
	public static void main(String[] args) {

		int choice;
		do {
			// Display menu and prompt for choice
			System.out.println("\nRecursion Methods Problem Set");
			System.out.println("By: Ryan Chang");
			System.out.println("----------------------------");
			System.out.println("1: Prime Number Checker");
			System.out.println("2: Perrin Sequence with Recursion");
			System.out.println("3: Perrin Sequence without Recursion");
			System.out.println("<Press 0 to Quit>");
			choice = in.nextInt();

			// Call methods based on choice
			if (choice == 1)
				primeDriver();
			else if (choice == 2)
				perrinDriver();
			else if (choice == 3)
				perrinLoops();

		} while (choice != 0); // exit when user inputs 0
		System.out.println("\nThe Program has been Terminated");
	}
}
