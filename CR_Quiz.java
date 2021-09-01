//Ryan Chang
//May 4, 2020
//ICS4U1
//Classes Quiz

import java.util.*;

// Date class
class Date
{
    private int month, day; // data fields

    // Constructor
    public Date (int m, int d)
    {
	month = m; // assigns inputed values to instance variables
	day = d;
    }


    // To string method
    public String toString ()
    {
	return String.format ("%d/%02d", month, day);
    }


    // Mutator method that advances the date
    public void advance ()
    {
	day++; // increase the day

	// mutation if it's the last day of February
	if (month == 2 && day > 28)
	{
	    month = 3;
	    day = 1;
	}
	// mutation if it's the last day of December
	else if (month == 12 && day > 31)
	{
	    month = 1;
	    day = 1;
	}
	// mutation if it's the last day of a month with 30 days
	else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
	{
	    month++;
	    day = 1;
	}
	// mutation if it's the last day of a month with 31 days
	else if (day > 31)
	{
	    month++;
	    day = 1;
	}
    }
}

// Driver
public class CR_Quiz
{
    // Main
    public static void main (String[] args)
    {
	Date lastDay = new Date (6, 17);
	lastDay.advance ();
	System.out.print ("The next day is " + lastDay.toString ());
    }
}
