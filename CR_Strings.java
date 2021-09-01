//Ryan Chang 
//March 2, 2020
//ICS4U1
//Strings Problem Set 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class CR_Strings {

	static Scanner in = new Scanner(System.in);

	// Palindrome method computations
	public static boolean palindrome(String word) {
		// declare variables
		String forward = "", reverse = "";
		int length;
		length = word.length();// set length to the length of the word
		word = word.toUpperCase();// change the string to upper case

		// Loops to make forwards version of the original without characters
		for (int i = 0; i < length; i++) {
			if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')// remove characters
				forward = forward + word.charAt(i);
		}

		// Loops to make backwards version of the original without characters
		for (int i = length - 1; i >= 0; i--) {
			if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')// remove characters
				reverse = reverse + word.charAt(i);
		}

		// Compare the forwards and backwards versions
		if (forward.equals(reverse)) // if forwards = backwards it is a palindrome
			return true;
		else // if forwards != backwards it is not a palindrome
			return false;
	}

	// Driver method for palindrome
	public static void palindromeDriver() {
		Scanner in = new Scanner(System.in);
		String word; // declare variable

		// Prompt for variable
		System.out.println("Enter a word/phrase");
		word = in.nextLine();

		// Outputs for palindrome or not
		if (palindrome(word) == true)
			System.out.println("Entered word/phrase is a palindrome.");
		else
			System.out.println("Entered word/phrase isn't a palindrome.");
	}

	// ------------------------------------------------------------------------------------

	// Computation for shift code
	public static String shiftcode(String phrase, int shift) {
		// declare variables
		int length = phrase.length();
		char character = 0;
		String shifted = "";

		// loop to push the letter the prompted shift value
		for (int i = 0; i < length; i++) {
			character = phrase.charAt(i);
			if (character >= 'A' && character <= 'Z') { // function applies only to upper case letters
				character -= 'A';// Subtract the ascii 'A' value to get the letter number
				character += shift;// Add the shift
				character %= 26;// find the remainder to determine the position of the letter in the alphabet
				character += 'A';// Add back the ascii 'A' value to get the new character.
			} else if (character >= 'a' && character <= 'z') { // function applies only to lower case letters
				character -= 'a';// Subtract the ascii 'a' value to get the letter number
				character += shift;// Add the shift
				character %= 26;// find the remainder to determine the position of the letter in the alphabet
				character += 'a';// Add back the ascii 'a' value to get the new character.
			}
			shifted += character; // return the shifted characters to the new phrase
		}
		return shifted; // return the shifted phrase
	}

	// Driver method for shift code
	public static void shiftcodeDriver() {
		Scanner in = new Scanner(System.in);
		// declare variables
		String phrase;
		int shift;

		// prompt for variables
		System.out.println("Enter a word/phrase");
		phrase = in.nextLine();
		System.out.println("Enter a shift value");
		shift = in.nextInt();

		// output result
		System.out.println("\nThe shifted phrase is: " + shiftcode(phrase, shift));
	}

	// -------------------------------------------------------------------------------------

	// Method for shuffling the alphabet in cryptocode
	public static char[] shuffle(char[] alphabet) {
		Random rand = new Random(); // create a random object

		// shuffle the alphabet
		for (int i = 0; i < alphabet.length - 1; i++) {
			int n = rand.nextInt(alphabet.length - i) + i; // declare int n that generates a random number in the range
			char temp = alphabet[i]; // declare char temp that is equal to the character at index i of array
			alphabet[i] = alphabet[n]; // make array value at index i equal to the array value at index n
			alphabet[n] = temp; // make temp equal to the array value at index n
		}
		return alphabet; // return the shuffled alphabet code
	}

	// Method for primary cryptocode computations
	public static String cryptocode(String newWord) {
		// create alphabet array
		char[] alphabet = shuffle(new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' });

		// declare variables
		String scrambled = "";
		char letter;

		// run through each character in the new word and swap the letters
		for (int i = 0; i < newWord.length(); i++) {
			letter = newWord.charAt(i); // set letter to the character at the given position

			// swap letter according to the new alphabet code - upper cases
			if (letter >= 'A' && letter <= 'Z') // limits to only the upper case letter
				letter = alphabet[letter - 'A'];

			// swap letter according to the new alphabet code - lower cases
			else if (letter >= 'a' && letter <= 'z') // limits to only the lower case letter
				letter = (char) (alphabet[letter - 'a'] + 32);
			scrambled += letter; // add the letter to the scrambled phrase
		}

		// output the alphabet code
		System.out.print("Using the alphabet code: ");
		System.out.print(alphabet);
		System.out.println("");

		// return the scrambled phrase
		return scrambled;
	}

	// Driver method for cryptocode
	public static void cryptocodeDriver() {
		String newWord; // declare variable

		// prompt for variable
		in.nextLine();
		System.out.println("Enter a word/phrase:");
		newWord = in.nextLine();

		// output result
		System.out.println("Coded message: " + cryptocode(newWord));
	}

	// -------------------------------------------------------------------------------------

	// Method that reads the text file for secret code
	public static String secretcode(String file) throws FileNotFoundException {
		File text = new File(file);

		// creating a scanner to read files in java 
		Scanner inn = new Scanner(text);

		// declare variables 
		String keyPhrase = inn.nextLine();
		String nums = inn.nextLine();

		// form an array containing the encoded integers in text as strings and split the string at the spaces
		String[] codeArray = nums.split(" ");

		// form an integer array with the same number of terms as there are encoded integers for inputs  
		int[] input = new int[codeArray.length];

		// form a character array with the same number of terms as there are encoded integers for outputs 
		char[] output = new char[codeArray.length];

		// change the encoded integers from string data to integer data and put it into the input array
		for (int i = 0; i < codeArray.length; i++) {
			input[i] = Integer.parseInt(codeArray[i]);
		}

		// output array will add characters from the key phrase based on the inputed number
		for (int i = 0; i < input.length; i++) {
			output[i] = keyPhrase.charAt(input[i]);
		}

		//create final output string that contains the secret message 
		String str = new String(output);
		return str; // return the secret message 
	}

	// Driver method for secret code
	public static void secretcodeDriver() throws FileNotFoundException {
		Scanner in = new Scanner(System.in);

		// prompt for text file
		System.out.println("Enter the location of your text file");
		String file = in.nextLine();

		// output result
		System.out.println(secretcode(file));
	}

	// --------------------------------------------------------------------------------------

	// Main method
	public static void main(String[] args) throws FileNotFoundException {

		int choice; // declare choice variable
		do {
			// Display menu and prompt for choice
			System.out.println("\nStrings Problem Set");
			System.out.println("Ryan Chang");
			System.out.println("----------------------------");
			System.out.println("1: Palindrome Checker");
			System.out.println("2: Shift Code");
			System.out.println("3: Crypto Code");
			System.out.println("4: Secret Code");
			System.out.println("<Press 0 to Quit>");
			choice = in.nextInt();

			// Call methods based on choice
			if (choice == 1)
				palindromeDriver();
			else if (choice == 2)
				shiftcodeDriver();
			else if (choice == 3)
				cryptocodeDriver();
			else if (choice == 4)
				secretcodeDriver();

		} while (choice != 0); // exit when user inputs 0
		System.out.println("\nThe Program has been Terminated");
	}
}
