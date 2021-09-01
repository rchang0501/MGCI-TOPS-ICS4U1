/*Ryan Chang 
ICS4U1
February 5, 2020
Set 1 Menu Program*/

import java.util.*;

public class CR_Set1_Menu {
	static Scanner in = new Scanner(System.in);

	// Calculating the price of each package of ground beef and finding which one has more value
	public static void GroundBeef() {
		Scanner in = new Scanner(System.in);

		// Declare variables used for prompt
		Double perPoundA, percentPackageA, perPoundB, percentPackageB;

		// Declare variables used for calculations
		Double costPackageA, costPackageB;

		// Prompt for each variable
		System.out.println("Price per pound package A:");
		perPoundA = in.nextDouble();
		System.out.println("Percent lean package A:");
		percentPackageA = in.nextDouble();
		System.out.println("Price per pound package B:");
		perPoundB = in.nextDouble();
		System.out.println("Percent lean package B:");
		percentPackageB = in.nextDouble();

		// Package Cost Calculations
		costPackageA = perPoundA / percentPackageA; // finding the cost of lean beef per pound for package A
		costPackageB = perPoundB / percentPackageB; // finding the cost of lean beef per pound for package B

		// Cost per pound of lean for both Packages
		System.out.println("");
		System.out.format("Package A cost per pound of lean: $%.2f \n", costPackageA * 100); // print the cost to 2 decimal places for package A
		System.out.format("Package B cost per pound of lean: $%.2f \n", costPackageB * 100); // print the cost to 2 decimal places for package B

		// Find the best value between Package A and Package B by comparing prices
		if (costPackageA > costPackageB) {
			System.out.println("Package B is the best value");
		} else {
			System.out.println("Package A is the best value");
		}
	}
	
	//Make an order form for a delicatessen 
	public static void InternetDelicatessen() {
		Scanner in = new Scanner(System.in);

		// Declare Variables or Prompt
		String foodItem;
		Double costInCents;
		int overnightDelivery;

		// Declare Variables for Calculations
		Double costInDollars, shipping, overNightCost, totalShipping, totalCost;

		// Prompt for each variable
		System.out.print("Enter the item: ");
		foodItem = in.nextLine();
		System.out.print("Enter the price: ");
		costInCents = in.nextDouble();
		System.out.print("Overnight delivery (0==no, 1==yes): ");
		overnightDelivery = in.nextInt();

		// Set the value of over night costs based on user decision
		if (overnightDelivery == 1) {
			overNightCost = 5.00;
		} else {
			overNightCost = 0.00;
		}

		// Set the base shipping cost based on the price of the item in cents
		if (costInCents < 1000) {
			shipping = 2.00;
		} else {
			shipping = 3.00;
		}

		// Calculations
		costInDollars = costInCents / 100; // conversion from cents to dollars
		totalShipping = shipping + overNightCost; // add up base and overnight shipping costs
		totalCost = totalShipping + costInDollars; // add up all the costs

		// Invoice Output
		System.out.println("");
		System.out.println("Invoice:");
		System.out.printf("   %-12s $%-10.2f %n", foodItem, costInDollars);
		System.out.printf("   %-12s $%-10.2f %n", "shipping", totalShipping);
		System.out.printf("   %-12s $%-10.2f %n", "total", totalCost);
	}

	//Determine if the tire pressure for a vehicle is ok 
	public static void TirePressure() {
		//Declare variables 
		Double rightFront, leftFront, rightBack, leftBack
		
		//Prompt for Variables 
		System.out.println("Input right front pressure ");
		rightFront = in.nextDouble();
		System.out.println("Input left front pressure ");
		leftFront = in.nextDouble();
		System.out.println("Input right back pressure ");
		rightBack = in.nextDouble();
		System.out.println("Input left back pressure ");
		leftBack = in.nextDouble();
		
		//Find the absolute value of the pressure is within 3 psi of each other  
		if ((Math.abs(rightFront - leftFront) <= 3) && (Math.abs(rightBack - leftBack) <= 3)) {
			System.out.println("Inflation is OK");
		} else {
			System.out.println("Inflation is BAD");
		}
	}

	//Menu starts here 
	public static void main(String[] args) {
		int choice;
		do {
			// Prompting for choice
			System.out.println();
			System.out.println("Choose from the following menu:\n");
			System.out.println("Enter 1, 2, 3");
			System.out.println("1 - Ground Beef");
			System.out.println("2 - Internet Delicatessen");
			System.out.println("3 - Tire Pressure");
			System.out.println();
			System.out.println("Enter 0 to exit");
			choice = in.nextInt();

			// Pull the program
			if (choice == 1) {
				GroundBeef();
			} else if (choice == 2) {
				InternetDelicatessen();
			} else if (choice == 3) {
				TirePressure();
			}
		} while (choice != 0);
		System.out.println("The program has terminated"); // End Prompt
	}
}
