//Ryan Chang
//March 6, 2020
//ICS4U1
//Box Assignment

import java.util.*;

// Box Class
class CR_Box
{
    private double width, height, length; // instance variables

    // Box Constructor for Rectangular Prism
    public CR_Box (double l, double w, double h)
    {
	width = w; // assign inputed numbers to the instance variables
	height = h;
	length = l;
    }


    // Box Constructor for Cube
    public CR_Box (double s)
    {
	width = s; // assign inputed numbers to the instance variables
	height = s;
	length = s;
    }


    // Box Constructor for Old Box
    public CR_Box (CR_Box oldBox)
    {
	width = oldBox.width (); // set the dimensions of old box to instance variables
	length = oldBox.length ();
	height = oldBox.height ();
    }


    // Volume Method
    public double volume ()
    {
	return width * length * height; // uses the volume of rectangular prism formula
    }


    // Surface Area Method
    public double area ()
    {
	return 2 * (faceArea () + topArea () + sideArea ()); // uses the surface area of rectangular prism formula
    }


    // Face area method
    private double faceArea ()
    {
	return length * height; // uses the area of a rectangle formula for front face
    }


    // Top area method
    private double topArea ()
    {
	return length * width; // uses the area of a rectangle formula for top face
    }


    // Side area method
    private double sideArea ()
    {
	return width * height; // uses the area of a rectangle formula for side face
    }


    // Access Method for length
    public double length ()
    {
	return length; // return instance variable length
    }


    // Access Method for height
    public double height ()
    {
	return height; // return instance variable height
    }


    // Access Method for width
    public double width ()
    {
	return width; // return instance variable width
    }


    // Bigger Box Method
    public void biggerBox (CR_Box oldBox)
    {
	width = 1.25 * oldBox.width (); // set width to 25% bigger than the old width
	length = 1.25 * oldBox.length (); // set length to 25% bigger than the old length
	height = 1.25 * oldBox.height (); // set height to 25% bigger than the old height
    }


    // Smaller Box Method
    public void smallerBox (CR_Box oldBox)
    {
	width = 0.75 * oldBox.width (); // set width to 25% smaller than the old width
	length = 0.75 * oldBox.length (); // set length to 25% smaller than the old length
	height = 0.75 * oldBox.height (); // set height to 25% smaller than the old height
    }


    // Nesting Box Method - determines if the initial box fits inside the outside box
    public boolean nests (CR_Box outsideBox)
    {
	if (width > outsideBox.width ()) // if the initial width is greater it doesn't fit
	    return false;
	if (height > outsideBox.height ()) // if the initial height is greater it doesn't fit
	    return false;
	if (length > outsideBox.length ()) // if the initial length is greater it doesn't fit
	    return false;
	else // otherwise it fits
	    return true;
    }


    // Driver Class
    static class CR_BoxTester
    {
	// Main Method
	public static void main (String[] args)
	{
	    Scanner in = new Scanner (System.in);

	    // Initial Box Dimension Prompt
	    System.out.println ("Enter Dimensions for the Initial Box");
	    System.out.print ("Length: ");
	    double l = in.nextDouble ();
	    System.out.print ("Width: ");
	    double w = in.nextDouble ();
	    System.out.print ("Height: ");
	    double h = in.nextDouble ();
	    System.out.println ("");

	    // Initial Box Output
	    CR_Box box = new CR_Box (l, w, h); // create the initial box object
	    System.out.println ("Initial Box:");
	    System.out.printf ("Area: %.2f, Volume: %.2f\n", box.area (), box.volume ()); // prints surface area and volume
	    System.out.printf ("length: %.2f, width: %.2f, height: %.2f\n", box.length, box.width, box.height); // prints dimensions
	    System.out.printf ("top Area: %.2f\n", box.topArea ()); // prints area of faces
	    System.out.printf ("side Area: %.2f\n", box.sideArea ());
	    System.out.printf ("face Area: %.2f\n", box.faceArea ());
	    System.out.println ("");

	    // Bigger Box Output
	    CR_Box bigger = new CR_Box (0);
	    bigger.biggerBox (box); // create the bigger box object
	    System.out.println ("Bigger Box: (25% Bigger)");
	    System.out.printf ("Area: %.2f, Volume: %.2f\n", bigger.area (), bigger.volume ()); // prints surface area and volume
	    System.out.printf ("length: %.2f, width: %.2f, height: %.2f\n", bigger.length, bigger.width, bigger.height); // prints dimensions
	    System.out.printf ("top Area: %.2f\n", bigger.topArea ()); // prints area of faces
	    System.out.printf ("side Area: %.2f\n", bigger.sideArea ());
	    System.out.printf ("face Area: %.2f\n", bigger.faceArea ());
	    System.out.println ("");

	    // Smaller Box Output
	    CR_Box smaller = new CR_Box (0);
	    smaller.smallerBox (box); // create the smaller box object
	    System.out.println ("Smaller Box: (25% Smaller)");
	    System.out.printf ("Area: %.2f, Volume: %.2f\n", smaller.area (), smaller.volume ()); // prints surface area and volume
	    System.out.printf ("length: %.2f, width: %.2f, height: %.2f\n", smaller.length, smaller.width, smaller.height); // prints dimensions
	    System.out.printf ("top Area: %.2f\n", smaller.topArea ()); // prints area of faces
	    System.out.printf ("side Area: %.2f\n", smaller.sideArea ());
	    System.out.printf ("face Area: %.2f\n", smaller.faceArea ());
	    System.out.println ("");

	    // Outside Box Dimension Prompt
	    System.out.println ("Enter Dimensions for an Outside Box");
	    System.out.print ("Length: ");
	    double l2 = in.nextDouble ();
	    System.out.print ("Width: ");
	    double w2 = in.nextDouble ();
	    System.out.print ("Height: ");
	    double h2 = in.nextDouble ();
	    System.out.println ("");

	    // Determine if the inside box fits inside the outside box
	    CR_Box outsideBox = new CR_Box (l2, w2, h2); // create the outside box object
	    if (box.nests (outsideBox) == true) // determine if the initial box fits
		System.out.println ("The Initial Box fits inside the Outside Box");
	    else
		System.out.println ("The Initial Box does not fit inside the Outside Box");
	}
    }
}
