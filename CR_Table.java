//Ryan Chang
//ICS4U1
//June 14, 2020
//2D Array Practice

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Table
{
    int[] [] arr; // instance variables
    int r, c;

    // Constructor (normal)
    public Table (int row, int column)
    {
	r = row; // set instance variables
	c = column;
	arr = new int [r] [c];

	// Iterate through rows and columns to fill with numbers from 40-95
	for (int i = 0 ; i < r ; i++)
	{
	    for (int j = 0 ; j < c ; j++)
	    {
		arr [i] [j] = (int) (Math.random () * (95 - 40 + 1)) + 40; // add random numbers
	    }
	}
    }


    // Constructor (file)
    public Table (File f) throws FileNotFoundException
    {
	// rows + columns value
	r = 0;
	c = 0;

	Scanner sc = new Scanner (f);
	// count number of lines
	while (sc.hasNextLine ())
	{
	    r++;
	    sc.nextLine ();
	}
	sc.close ();

	// count columns
	Scanner s2 = new Scanner (f);
	// removes spaces + gets length
	c = s2.nextLine ().replaceAll ("\\s+", "").length ();
	s2.close ();

	// create 2D array
	arr = new int [r] [c];

	// read elements from file into array
	Scanner s3 = new Scanner (f);
	for (int i = 0 ; i < r ; i++)
	{
	    for (int j = 0 ; j < c ; j++)
	    {
		arr [i] [j] = s3.nextInt ();
	    }
	}
	s3.close ();
    }


    // toString Method
    public String toString ()
    {
	String str = "";

	// Iterate through rows and columns
	for (int i = 0 ; i < r ; i++)
	{
	    for (int j = 0 ; j < c ; j++)
	    {
		str += (arr [i] [j] + " "); // add entries
	    }
	    str += "\n"; // add new line
	}

	return str;
    }


    // Average Calculator
    public double average (String choice, int pos)
    {
	double total = 0.0;
	double avg = 0.0;

	// Row Average
	if (choice.charAt (0) == 'r')
	{
	    for (int i = 0 ; i < c ; i++)
	    { // number of values in a row = number of columns
		total += arr [pos - 1] [i]; // column changes, row stays constant
	    }
	    avg = total / c;
	}

	// Column Average
	else if (choice.charAt (0) == 'c')
	{
	    for (int i = 0 ; i < r ; i++)
	    { // number of values in a column = number of rows
		total += arr [i] [pos - 1]; // row changes, column stays constant
	    }
	    avg = total / r;
	}

	return avg;
    }


    // Smoother Method
    public void imageSmoother ()
    {
	int row = arr.length; // find the length of the array
	int width = arr [0].length; // find the height of the array

	int[] [] temp = new int [row] [width]; // create a new temp array
	for (int i = 0 ; i < row ; ++i)
	{
	    for (int j = 0 ; j < width ; ++j)
	    {
		int sum = 0, c = 0;
		for (int k = Math.max (0, i - 1) ; k <= Math.min (i + 1, row - 1) ; k++)
		{
		    for (int u = Math.max (0, j - 1) ; u <= Math.min (j + 1, width - 1) ; u++)
		    {
			sum += arr [k] [u];
			c++;
		    }
		}
		temp [i] [j] = sum / c;
	    }
	}
	arr = temp;
    }
}

class CR_Table
{

    static Scanner sc = new Scanner (System.in);

    // driver method to create a table
    public static Table generate ()
    {
	Scanner sc = new Scanner (System.in);
	System.out.println ("Enter number of rows: ");
	int rows = sc.nextInt ();
	System.out.println ("Enter number of columns: ");
	int columns = sc.nextInt ();
	Table table = new Table (rows, columns);
	return table;
    }


    // Driver for Average
    public static void averageDriver (Table array)
    {
	Scanner sc = new Scanner (System.in);

	System.out.println ("Choose row (r) or column (c): ");
	String choice = sc.nextLine ();

	System.out.println ("Enter the Row or Column Number: ");
	int pos = sc.nextInt ();

	double average = array.average (choice, pos);
	System.out.print ("The average is ");
	System.out.printf ("%.2f", average);
	System.out.println ("\nPress 'enter' to continue.");
	sc.nextLine ();
	sc.nextLine ();
    }


    // Driver for Smoother
    public static void smootherDriver ()
    {
	// System.out.print("Enter file location: "); // prompt
	// sc.nextLine();
	// String file = sc.nextLine();
	// File text = new File(file); // new file object
	File text = new File ("/Users/ryanc/Documents/test.txt"); // alternative input

	try
	{ // if the file location is valid
	    Table table = new Table (text); // create new array object that accepts the file
	    System.out.println (table.toString ()); // print array
	    table.imageSmoother (); // call imageSmoother
	    System.out.println (table.toString ()); // print array
	}

	catch (FileNotFoundException e)
	{ // if the file location is not valid
	    System.out.print ("CAN'T FIND FILE");
	}
    }


    // main method
    public static void main (String[] args)
    {
	int choice = 0;
	boolean print = true;

	System.out.println ("CR_Table");
	System.out.println ("--------");

	Table table = generate ();

	do
	{

	    if (print)
		System.out.print (table.toString ());
	    print = true;

	    System.out.println ("\nOptions:");
	    System.out.println ("1) New Table");
	    System.out.println ("2) Average of a row or column");
	    System.out.println ("3) Image Smoother");
	    System.out.println ("0) Quit");
	    choice = sc.nextInt ();

	    if (choice == 1)
	    {
		table = generate ();

	    }

	    else if (choice == 2)
	    {
		averageDriver (table);
	    }

	    else if (choice == 3)
	    {
		smootherDriver ();
		print = false;
	    }

	}
	while (choice != 0);

    }
}
