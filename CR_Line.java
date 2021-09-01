//Ryan Chang
//March 23, 2020
//ICS4U1
//Line Assignment

import java.util.*;

// Line Class
class Line
{
    private int A, B, C; // instance variables

    // Line constructor
    public Line (int a, int b, int c)
    {
	A = a; // assign inputed numbers to instance variables
	B = b;
	C = c;
    }


    // Converts the line into a string
    public String toString ()
    {
	String sLine = ""; // Declare empty string variable for the line

	// Decisions:
	if (A == 0 && B == 0) // if both the x and y terms are 0 then the line doesn't exist
	    return "line doesn't exist";
	else
	{
	    // Conditions for the x term
	    if (A == 0) // if the coefficient is 0 don't include an x term
		sLine += "";
	    else if (A == 1) // if the coefficient is 1 don't include the 1
		sLine += "x";
	    else if (A == -1) // if the coefficient is -1 don't include the 1 but keep sign
		sLine += "-x";
	    else // otherwise add the coefficient followed by the variable
		sLine += A + "x";

	    // Conditions for the y term
	    if (B == 0) // if the coefficient is 0 don't include a y term
		sLine += "";
	    else if (A == 0 && B == 1) // if there is no A term don't add a sign in front
		sLine += "y";
	    else if (B == 1) // don't include the 1
		sLine += " + y";
	    else if (A == 0 && B == -1) // of there's no A term don't add a sign in front
		sLine += "-y";
	    else if (B == -1) // only include the sign not the 1
		sLine += " - y";
	    else if (A != 0 && B > 0) // if A exists and B is positive add a positive term
		sLine += " + " + B + "y";
	    else if (A != 0 && B < 0) // if A exists and B is negative add a negative term
		sLine += " - " + Math.abs (B) + "y";
	    else // otherwise add the coefficient followed by the variable
		sLine += B + "y";

	    // Conditions for the constant
	    if (C == 0) // if there is no C don't add a term
		sLine += "";
	    else if (C > 0) // if C is positive add a + sign in front
		sLine += " + " + C;
	    else // if C is negative add a - sign in front
		sLine += " - " + Math.abs (C);

	    // Add the = 0 at the end
	    sLine += " = 0";

	    return sLine; // return the line
	}
    }


    // Reads the user inputed line until a valid equation has been entered
    public void readLine (Scanner in)
    {
	boolean okay = false;
	while (!okay)
	{ // loop will continue until a valid line has been inputed
	    try
	    {
		int xpos = -1, ypos = -1; // position of variables in string
		String Apos = null, Bpos = null, Cpos = null; // position of coefficients in string
		String eq = in.nextLine (); // read in line as string

		// For loop that runs through all the characters of the string
		for (int i = 0 ; i < eq.length () ; i++)
		{
		    char c = eq.charAt (i);

		    // Only read valid characters in the string
		    if (((int) c > 47 && (int) c < 58) || c == 'x' || c == 'y' || c == '+' || eq.charAt (i) == '-'
			    || c == '=' || c == ' '){

			// Setting the A value
			if (eq.charAt (i) == 'x')
			{
			    xpos = eq.indexOf ('x'); // set x position to its index in the string
			    if (i == 0) // if x is the first term with sign or coefficient, A = 1
				A = 1;
			    else if (eq.charAt (i - 1) == '-') // if x has a negative sign in front, A = -1
				A = -1;
			    else
			    { // otherwise A is the coefficient in front of x
				Apos = eq.substring (0, xpos);
				A = Integer.parseInt (Apos); // change the A value from a string to integer
			    }
			}

			// Setting the B value
			else if (eq.charAt (i) == 'y')
			{
			    ypos = eq.indexOf ('y'); // set y position to its index in the string
			    if (i == 0)
				B = 1;
			    else if (eq.charAt (i - 1) == '+') // if y has a positive sign in front, B = 1
				B = 1;
			    else if (eq.charAt (i - 1) == '-') // if y has a negative sign in front, B = -1
				B = -1;
			    else
			    { // otherwise B is the coefficient in front of y
				Bpos = eq.substring (xpos + 1, ypos);
				B = Integer.parseInt (Bpos); // change the B value from a string to integer
			    }
			}

			// Setting the C value
			else if (eq.charAt (i) == '=')
			{
			    if (ypos != i - 1 && xpos != i - 1 && ypos != -1)
			    { // if there's a C term and y is before
				Cpos = eq.substring (ypos + 1, i); // C is right after the y term
				C = Integer.parseInt (Cpos); // change the C value from a string to integer
			    }
			    else if (ypos != i - 1 && xpos != i - 1 && ypos == -1 && xpos != -1)
			    { // if there is only
				// an x
				Cpos = eq.substring (xpos + 1, i); // C is right after the x term
				C = Integer.parseInt (Cpos); // change the C value from a string to integer
			    }
			}

			// Determining if the inputed equation is valid
			if (xpos == -1 && ypos == -1) // if there is no x or y value in the equation
			    okay = false;
			else // otherwise the equation is valid
			    okay = true;
		    }
		    else   // this is from the valid characters if statement - if there are invalid
			// characters start again
			okay = false;
		}

		// Error message
		if (okay == false)
		    System.out.print ("Please enter a valid line: ");
	    }

	    // Catch statement to prevent crashes
	    catch (Exception e)
	    { // trap string out of bounds or number format exceptions
		okay = false;
		System.out.print ("Please enter a valid line:"); // output error message if there's an invalid input
	    }
	}
    }


    // Find the x-intercept
    public double xint ()
    {
	return (double) - C / A;
    }


    // Find the y-intercept
    public double yint ()
    {
	return (double) - C / B;
    }


    // Find the slope
    public double slope ()
    {
	return (double) - A / B;
    }


    // Check if the line is vertical
    public boolean verticalCheck ()
    {
	// return B == 0;
	if (B == 0)
	    return true;
	else
	    return false;
    }


    // Check if the line is horizontal
    public boolean horizontalCheck ()
    {
	// return A == 0;
	if (A == 0)
	    return true;
	else
	    return false;
    }


    // Point Class
    class Point
    {
	private double x, y; // instance variables

	// Constructor for points
	public Point (double xcoord, double ycoord)
	{
	    x = xcoord;
	    y = ycoord;
	}

	// Format the points to the proper coordinate format
	public String toString ()
	{
	    return "(" + x + ", " + y + ")";
	}
    }


    // Method to find the POI of the lines
    public Point intersect (Line lineNum2)
    {
	double xpoi, ypoi;
	if (slope () == lineNum2.slope ())
	{ // if the slopes are the same return nothing
	    return null;
	}
	else if (verticalCheck () == true)
	{ // If line #1 is vertical
	    xpoi = xint (); // set xpoi to the first line's x intercept
	    ypoi = lineNum2.slope () * xpoi + lineNum2.yint (); // plug ^ value into the second line's equation
	}
	else if (lineNum2.verticalCheck () == true)
	{ // if line #2 is vertical
	    xpoi = lineNum2.xint (); // set x poi to the second line's x intercept
	    ypoi = slope () * xpoi + yint (); // plug ^ value into the first line's equation
	}
	else
	{ // if not a unique situation then apply the formula to find intersection
	    xpoi = (lineNum2.yint () - yint ()) / (slope () - lineNum2.slope ());
	    ypoi = lineNum2.slope () * xpoi + lineNum2.yint ();
	}
	return new Point (xpoi, ypoi); // return the (x,y) of the POI
    }
}

//Driver method to test the Line class
public class CR_Line
{
    static Scanner in;

    public static void main (String[] args)
    {
	in = new Scanner (System.in);

	// Title & Instructions
	System.out.println ("Line Class Assignment");
	System.out.println ("Enter lines in the form Ax+By+C=0");
	System.out.println ("----------------------------------------");
	System.out.println ("");

	// Prompt for line #1
	System.out.print ("Enter the equation for line #1: ");
	Line line1 = new Line (0, 0, 0); // create new line object
	line1.readLine (in); // use the read line method to process the input

	// Display line #1
	System.out.println ("Line #1: " + line1);

	// Display the x-intercept for line 1
	if (line1.horizontalCheck () == true) // checks if the line is horizontal
	    System.out.println ("x-intercept: this line doesn't have a x-intercept.");
	else
	    System.out.println ("x-intercept: " + line1.xint ());

	// Display the y-intercept for line 1
	if (line1.verticalCheck () == true) // checks if the line is vertical
	    System.out.println ("y-intercept: this line doesn't have a y-intercept.");
	else
	    System.out.println ("y-intercept: " + line1.yint ());

	// Display the slope
	if (line1.verticalCheck () == true) // if the line is vertical it doesn't have a slope
	    System.out.println ("slope: this line's slope is undefined or infinite");
	else
	    System.out.println ("slope: " + line1.slope ());

	// Prompt for line #2
	System.out.print ("\nEnter the equation for line #2: ");
	Line line2 = new Line (0, 0, 0); // create a new line object
	line2.readLine (in); // use the read line method to process the input

	// Display line #2
	System.out.println ("Line #2: " + line2);

	// Display the x-intercept for line 1
	if (line2.horizontalCheck () == true) // checks if the line is horizontal
	    System.out.println ("x-intercept: this line doesn't have a x-intercept.");
	else
	    System.out.println ("x-intercept: " + line2.xint ());

	// Display the y-intercept for line 1
	if (line2.verticalCheck () == true) // checks if the line is vertical
	    System.out.println ("y-intercept: this line doesn't have a y-intercept.");
	else
	    System.out.println ("y-intercept: " + line2.yint ());

	// Display the slope
	if (line2.verticalCheck () == true) // if the line is vertical it doesn't have a slope
	    System.out.println ("slope: this line's slope is undefined or infinite");
	else
	    System.out.println ("slope: " + line2.slope ());

	// Display the point of intersection
	if (line1.intersect (line2) != null) // makes sure the lines are not parallel
	    System.out.println ("\nPOI: " + line1.intersect (line2));
	else
	    System.out.println ("\nPOI: the lines are parallel");
    }
}
