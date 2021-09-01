/*Ryan Chang 
 ICS4U1
 Mexico Dice Game 
 February 18, 2020*/

import java.util.Scanner;
import java.util.Random;

public class CR_MexicoDiceGame {
	static Scanner in = new Scanner(System.in);
	static Random dice = new Random();

	// Assigns a score to each roll combination to distinguish high and low rolls
	public static int roll(int roll1, int roll2) {
		int score;
		// Mexico Roll
		if (roll1 == 2 && roll2 == 1 || roll1 == 1 && roll2 == 2) {
			score = 100;
		// Pairs Rolls
		} else if (roll1 == 6 && roll2 == 6) {
			score = 99;
		} else if (roll1 == 5 && roll2 == 5) {
			score = 98;
		} else if (roll1 == 4 && roll2 == 4) {
			score = 97;
		} else if (roll1 == 3 && roll2 == 3) {
			score = 96;
		} else if (roll1 == 2 && roll2 == 2) {
			score = 95;
		} else if (roll1 == 1 && roll2 == 1) {
			score = 94;
		// All Other Rolls
		} else if (roll1 == 6 && roll2 == 5 || roll1 == 5 && roll2 == 6) {
			score = 93;
		} else if (roll1 == 6 && roll2 == 4 || roll1 == 4 && roll2 == 6) {
			score = 92;
		} else if (roll1 == 6 && roll2 == 3 || roll1 == 3 && roll2 == 6) {
			score = 91;
		} else if (roll1 == 6 && roll2 == 2 || roll1 == 2 && roll2 == 6) {
			score = 90;
		} else if (roll1 == 6 && roll2 == 1 || roll1 == 1 && roll2 == 6) {
			score = 89;
		} else if (roll1 == 6 && roll2 == 5 || roll1 == 5 && roll2 == 6) {
			score = 88;
		} else if (roll1 == 5 && roll2 == 4 || roll1 == 4 && roll2 == 5) {
			score = 87;
		} else if (roll1 == 5 && roll2 == 3 || roll1 == 3 && roll2 == 5) {
			score = 86;
		} else if (roll1 == 5 && roll2 == 2 || roll1 == 2 && roll2 == 5) {
			score = 85;
		} else if (roll1 == 5 && roll2 == 1 || roll1 == 1 && roll2 == 5) {
			score = 84;
		} else if (roll1 == 4 && roll2 == 3 || roll1 == 3 && roll2 == 4) {
			score = 83;
		} else if (roll1 == 4 && roll2 == 2 || roll1 == 2 && roll2 == 4) {
			score = 82;
		} else if (roll1 == 4 && roll2 == 1 || roll1 == 1 && roll2 == 4) {
			score = 81;
		} else if (roll1 == 3 && roll2 == 2 || roll1 == 2 && roll2 == 3) {
			score = 80;
		} else {
			score = 79;
		}
		return score; // returns the score of the roll
	}

	// Main Method
	public static void main(String[] args) {
		System.out.println("MEXICO DICE GAME\n");
		int roll1, roll2;// the roll value of each die
		int turns1 = 0, turns2 = 1, turns3 = 1, again;// player turns and rolling again
		int score1, score2, score3;// player scores
		int life1 = 6, life2 = 6, life3 = 6;// player lives
		String start;// for starting the next round

		// Game Loop
		do {
			// Start Next Round
			System.out.print("Enter Any Letter to Start the Next Round ");
			start = in.next();
			System.out.println("");

			// Player 1 Loop
			do {
				turns1++;// increments each subsequent turn
				// Assigns each roll a random number from 1 to 6
				roll1 = dice.nextInt(6) + 1;
				roll2 = dice.nextInt(6) + 1;
				// Roll Display
				System.out.println("Player 1: " + turns1 + " rolls played");
				System.out.println("Your rolls were: " + roll1 + " and " + roll2);
				System.out.println("Enter any number to roll again, enter 0 to not");
				again = in.nextInt();// asks for rolling again or not
				score1 = roll(roll1, roll2);// retrieves a score for player's roll using the roll method
			} while (again != 0 && turns1 < 3);// continue rolling until player doesn't want to or turns exceed 3

			// Player 2 Loop
			do {
				// Assigns each roll a random number from 1 to 6
				roll1 = dice.nextInt(6) + 1;
				roll2 = dice.nextInt(6) + 1;
				// Roll Display
				System.out.println("Player 2: " + turns2 + " rolls played");
				System.out.println("Your rolls were: " + roll1 + " and " + roll2);
				System.out.println("Enter any number to roll again, enter 0 to not");
				again = in.nextInt();// asks for rolling again or not
				turns2++;// increments each subsequent turn
				score2 = roll(roll1, roll2);// retrieves a score for player's roll using the roll method
			} while (again != 0 && turns2 <= turns1);// continue rolling until player doesn't want to or turns exceed 3

			// Player 3 Loop
			do {
				// Assigns each roll a random number from 1 to 6
				roll1 = dice.nextInt(6) + 1;
				roll2 = dice.nextInt(6) + 1;
				// Roll Display
				System.out.println("Player 3: " + turns3 + " rolls played");
				System.out.println("Your rolls were: " + roll1 + " and " + roll2);
				System.out.println("Enter any number to roll again, enter 0 to not");
				again = in.nextInt();// asks for rolling again or not
				turns3++;// increments each subsequent turn
				score3 = roll(roll1, roll2);// retrieves a score for player's roll using the roll method
			} while (again != 0 && turns3 <= turns1);// continue rolling until player doesn't want to or turns exceed 3

			// Who loses output
			System.out.println("");
			System.out.println("THIS ROUND'S RESULTS:");
			// Situations
			if (score1 < score2 && score1 < score3) {
				System.out.println("Player 1 loses one life");
				life1--;// subtracts a life from player 1
			} else if (score2 < score1 && score2 < score3) {
				System.out.println("Player 2 loses one life");
				life2--;// subtracts a life from player 2
			} else if (score3 < score1 && score3 < score2) {
				System.out.println("Player 3 loses one life");
				life3--;// subtracts a life from player 3
			} else if (score1 == score2 && score3 > score1 && score3 > score2) {
				System.out.println("Player 1 and Player 2 each lose one life");
				life1--;
				life2--;
			} else if (score2 == score3 && score1 > score2 && score1 > score3) {
				System.out.println("Player 2 and Player 3 each lose one life");
				life2--;
				life3--;
			} else if (score1 == score3 && score2 > score1 && score2 > score3) {
				System.out.println("Player 1 and Player 3 each lose one life");
				life1--;
				life3--;
			} else {
				System.out.println("Each player loses one life");
				life1--;
				life2--;
				life3--;
			}
			// End of round results
			System.out.println("-----------------------------");
			System.out.println("Player 1: " + life1 + " lives remaining");
			System.out.println("Player 2: " + life2 + " lives remaining");
			System.out.println("Player 3: " + life3 + " lives remaining");
			System.out.println("-----------------------------");
			System.out.println("");

			// Reset the turn number for each player
			turns1 = 0;
			turns2 = 1;
			turns3 = 1;
		} while (life1 != 0 && life2 != 0 && life3 != 0);// stop the game when someone loses all lives

		// Determining the winner of the game
		System.out.println("FINAL RESULT:");
		// Situations
		if (life1 == 0 && life2 > life3 || life3 == 0 && life2 > life1) {
			System.out.println("Player 2 is the winner");
		} else if (life2 == 0 && life1 > life3 || life3 == 0 && life1 > life2) {
			System.out.println("Player 1 is the winner");
		} else if (life2 == 0 && life3 > life1 || life1 == 0 && life3 > life2) {
			System.out.println("Player 3 is the winner");
		} else if (life1 == 0 && life2 == 0 && life3 == 0) {
			System.out.println("All three players have tied");
		} else if (life1 == 0 && life2 == life3) {
			System.out.println("Player 2 and Player 3 tied");
		} else if (life2 == 0 && life1 == life3) {
			System.out.println("Player 1 and Player 3 tied");
		} else {
			System.out.println("Player 1 and Player 2 tied");
		}

		// Final Output
		System.out.println("");
		System.out.println("Thanks for playing!");
	}
}
