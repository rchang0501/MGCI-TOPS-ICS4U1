/*Ryan Chang 
ICS4U1
February 10, 2020
Looping Programs Menu*/

import java.util.*;

public class CR_Looping_Menu {
	static Scanner in = new Scanner(System.in);

	// Find the sum using the given formula
	public static void SumExercise() {
		Scanner in = new Scanner(System.in);

		// Declare Variables
		Double n, sum;
		sum = 0.0;

		// Prompt for variable 
		System.out.println("Enter N");
		n = in.nextDouble();
		System.out.println("");

		// Calculation with For Loop
		for (int i = 1; i <= n; i++) { //increment each run until it is equal to n 
			sum = sum + 1.0 / i;
		}

		// Final Result
		System.out.printf("Sum is: " + sum);
	}

	// Find the sum of the squares and cubes from 1 to the prompted number
	public static void SquaresAndCubes() {
		Scanner in = new Scanner(System.in);

		// Declare Variables
		int n, squares, cubes;
		squares = 0;
		cubes = 0;

		// Prompt
		System.out.println("Upper Limit:");
		n = in.nextInt();

		// Calculation with For Loop
		System.out.println("Using Loops");
		for (int i = 1; i <= n; i++) { //increments until it is equal to n
			squares += Math.pow(i, 2); //raises to the second power 
			cubes += Math.pow(i, 3); //raises to the third power 
		}
		System.out.println("The sum of Squares is " + squares);
		System.out.println("The sum of Cubes is " + cubes);

		// Calculation with Mathematical Formulas
		System.out.println("");
		System.out.println("Using Mathematical Formulas");
		System.out.println("The sum of Squares is " + (n * (n + 1) * (2 * n + 1) / 6));
		System.out.printf("The sum of Cubes is %.0f \n", (Math.pow(n, 2) * Math.pow(n + 1, 2)) / 4);
	}

	// Find the sum of the numbers in and out of the prompted range
	public static void InRangeAdder() {
		Scanner in = new Scanner(System.in);

		// Declare Variables
		int low, high, suminrange = 0, sumoutrange = 0, input;

		// Prompt for variables 
		System.out.println("In-range Adder");
		System.out.println("Low end range:");
		low = in.nextInt();
		System.out.println("High end range:");
		high = in.nextInt();

		// Calculation with do while loops and if statement
		do { //do this action until the input is 0 
			System.out.println("Enter Data");
			input = in.nextInt();
			if (input >= low && input <= high) { //add the number if it is in range 
				suminrange = suminrange + input;
			} else { //add the number if it is out of range 
				sumoutrange = sumoutrange + input;
			}
		} while (input != 0);

		// Final Result
		System.out.println("Sum of in range values: " + suminrange);
		System.out.println("Sum of out of range values: " + sumoutrange);
	}

	// Find the cost of a shipping order based on the prompted weight
	public static void ShippingCostCalculator() {
		Scanner in = new Scanner(System.in);

		// Declare Variables
		int weight;
		double cost = 3.00;

		// Calculation with do while loops and if statements
		do { //keep doing the action until the input is 0 
			System.out.println("Weight of Order:");
			weight = in.nextInt();
			if (weight >= 10) { //if the weight is greater than 10 pounds add the costs 
				cost = cost + (weight - 10) * 0.25;
				System.out.printf("Shipping Cost: $%.2f \n", cost);
				System.out.println("");
			} else if (weight == 0) { //if the weight is 0 don't output anything 
				System.out.println("");
			} else { //if the weight is less than 10 pounds output no additional costs 
				System.out.printf("Shipping Cost: $%.2f \n", cost);
				System.out.println("");
			}
		} while (weight != 0);

		// Final Output
		System.out.println("bye");
	}

	// Display the credit card bill based on the prompted balance, interest, and payment
	public static void CreditCardBill() {
		Scanner in = new Scanner(System.in);

		// Declare Variables
		double balance, interest, payment, zero = 0.00;
		int i = 1;

		// Prompt for variables 
		System.out.println("Enter the beginning balance:");
		balance = in.nextDouble();
		System.out.println("Enter the monthly interest in percent:");
		interest = in.nextDouble();
		interest = 1 + (interest / 100);
		System.out.println("Enter the monthly payment:");
		payment = in.nextDouble();

		// Calculation with do while loops and if statements
		do { //keep doing action until balance is 0 
			System.out.printf("%-15s", "Month: " + i);
			balance = balance * interest - payment;
			if (balance <= 0.0) { //if balance is less than zero, only output 0 and not a negative number
				System.out.printf("%-10s%-10.2f", "Balance: $", zero);
			} else { //output balance after interest and payment 
				System.out.printf("%-10s%-10.2f", "Balance: $", balance);
			}
			System.out.printf("%-10s%-10.2f \n", "Total Payments: $", payment * i); //output total amount of payments 
			i++;
		} while (balance >= 0.0);
	}

	// Determine when the drug should be discarded based on potency
	public static void DrugPotency() {
		Scanner in = new Scanner(System.in);

		// Declare Variables
		double effectiveness = 100.0;
		int i = 0;

		// Calculation with do while loops and if statement
		do { //keep doing action until the effectiveness is below 50% 
			System.out.printf("%-15s", "Month: " + i); 
			System.out.printf("%-10s%-10.2f\n", "Effectiveness: ", effectiveness);
			effectiveness = effectiveness * 0.96; //subtract 4% effectiveness each time 
			i++; //increment each month
			if (effectiveness <= 50.0) { //if the effectiveness is below 50, tell user to discard 
				System.out.printf("%-15s", "Month: " + i);
				System.out.printf("%-10s%-10.2f", "Effectiveness: ", effectiveness);
				System.out.printf("%-15s", "DISCARDED");
			}
		} while (effectiveness >= 50.0);
	}

	// Menu starts here
	public static void main(String[] args) {
		int choice;
		do {
			// Prompting for choice
			System.out.println();
			System.out.println("Choose from the following menu:\n");
			System.out.println("Enter 1, 2, 3, 4, 5, 6");
			System.out.println("1 - Sum Exercise");
			System.out.println("2 - Squares and Cubes");
			System.out.println("3 - In-range Adder");
			System.out.println("4 - Shipping Cost Calculator");
			System.out.println("5 - Credit Card Bill");
			System.out.println("6 - Drug Potency");
			System.out.println();
			System.out.println("Enter 0 to exit");
			System.out.println("");
			choice = in.nextInt();

			// Pull the program
			if (choice == 1) {
				SumExercise();
			} else if (choice == 2) {
				SquaresAndCubes();
			} else if (choice == 3) {
				InRangeAdder();
			} else if (choice == 4) {
				ShippingCostCalculator();
			} else if (choice == 5) {
				CreditCardBill();
			} else if (choice == 6) {
				DrugPotency();
			}
		} while (choice != 0);
		System.out.println("The program has terminated"); // End Prompt
	}
}
