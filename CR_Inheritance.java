//Ryan Chang
//March 27, 2020
//ICS4U1
//Inheritance Assignment

import java.util.*;

//Costs Interface
interface Costs
{
    public static final double tax = 1.13; // constants
    double price = 249999.99; // (public static final omitted since it's defaulted)

    public double payment (); // (the public can be omitted since it's implied)
}

//Security Interface
interface Security
{
    String guardName = "John Smith"; // constant

    String information (); // method header
}

//Building Abstract Parent Class
abstract class Building
{ // (extends Object)
    protected int floors; // instance variables - protected instead of private to let child class access
    protected String address;

    // New building constructor
    public Building (String a, int f)
    {
	// (automatic super() put here by compiler)
	// e.g. of an implicit use of super since the object class has a no argument constructor
	address = a;
	floors = f;
    }


    // Convert building info to string form
    public String toString ()
    {
	return "Building Details\n----------------\nAddress: " + address + ", Floors: " + floors;
    }


    // Abstract method that welcomes the user to new building
    public abstract void welcome ();
}

//House Child Class
class House extends Building implements Costs
{ // adds an interface as well
    protected int bathrooms, livingRooms, kitchens, bedrooms; // new instance variables

    // New house constructor
    public House (String a, int f, int bathR, int livingR, int k, int bedR)
    {
	super (a, f); // explicit use of the 'super' key word for inheritance
	bathrooms = bathR;
	livingRooms = livingR;
	kitchens = k;
	bedrooms = bedR;
    }


    // Overriding and using super in child's method
    public String toString ()
    {
	return super.toString () + ", Bathrooms: " + bathrooms + ", Living Rooms: " + livingRooms + ", Kitchens: "
	    + kitchens + ", Bedrooms: " + bedrooms;
    }


    // Non-abstract welcome method
    public void welcome ()
    {
	System.out.println ("Welcome to your new house!");
    }


    // Payment method that's implemented from the interface Costs
    public double payment ()
    { // body of method here instead of in the interface
	return floors * price * tax;
    }
}

//Apartment Child Class
class Apartment extends Building implements Costs, Security
{ // adds 2 interfaces
    private int units; // new instance variables
    private boolean pool, exerciseRoom;

    // New house constructor
    public Apartment (String a, int f, int u, boolean p, boolean exR)
    {
	super (a, f); // explicit use of the 'super' key word for inheritance
	units = u;
	pool = p;
	exerciseRoom = exR;
    }


    // Overriding and using super in child's method
    public String toString ()
    {
	return super.toString () + ", Units: " + units + ", Pool: " + pool + ", Exercise Room: " + exerciseRoom;
    }


    // Non-abstract welcome method
    public void welcome ()
    {
	System.out.println ("Welcome to your new appartment!");
    }


    // Payment method that's implemented from the interface 'Costs'
    public double payment ()
    {
	return floors * price * tax;
    }


    // Method that tells user the security guard's name from the interface 'Security'
    public String information ()
    {
	return "The building's security guard's name is: " + guardName;
    }
}

//Bungalow Child Class of the House Class
class Bungalow extends House
{ // includes the interface as well

    // New bungalow house constructor
    public Bungalow (String a, int f, int bathR, int livingR, int k, int bedR)
    {
	super (a, f, bathR, livingR, k, bedR); // explicit use of the 'super' key word for inheritance
    }


    // Overriding and using super in child's method
    public String toString ()
    {
	return super.toString ();
    }


    // Non-abstract welcome method
    public void welcome ()
    {
	System.out.println ("Welcome to your new bungalow house!");
    }


    // Payment method that's implemented from the interface 'Costs'
    public double payment ()
    {
	return floors * price * tax;
    }
}


//Driver
public class CR_Inheritance
{
    static Scanner in;

    // Main Method
    public static void main (String[] args)
    {
	in = new Scanner (System.in);

	// Title
	System.out.println ("Inheritance Assignment - Buildings");
	System.out.println ("");

	// Building
	//Building home = new Building("135 Overlea Blvd", 3); // create new building object
	//System.out.println(home.toString());
	//System.out.println("");
	//(above code no longer work because the Building class is abstract and cannot be instantiated)
	//(if the abstract method welcome() is removed then the above code would work)

	// House
	Building home = new House ("135 Overlea Blvd", 3, 3, 1, 1, 5); // create new house object
	home.welcome ();
	System.out.println (home.toString ());
	System.out.println ("");

	// Apartment
	home = new Apartment ("135 Overlea Blvd", 20, 200, true, true); // using polymorphism with home
	home.welcome ();
	System.out.println (home.toString ());
	System.out.println ("");

	// Distinction to interface versions
	System.out.println ("** with interface verions: **");
	System.out.println ("");
	// Note:
	// distinction is made because a variable can only hold a reference to an object
	// whose class is a descendant of the class of the variable. Because we want to
	// use an interface that doesn't use a method from the Building parent class, a
	// new variable needs to be made with the class of the children that use the
	// interface

	// House - interface version (uses a method not in the 'Building' parent class)
	House home2 = new House ("135 Overlea Blvd", 3, 3, 1, 1, 5); // create new house object
	home2.welcome ();
	System.out.println (home2.toString ());
	System.out.print ("Property Value: $");
	System.out.printf ("%.2f\n", home2.payment ());
	System.out.println ("");

	// Bungalow house
	Bungalow home3 = new Bungalow ("135 Overlea Blvd", 2, 3, 1, 1, 5); // create new bungalow house object
	home2 = home3; // polymorphism works b/c variable of the root type 'House' can be used with any of its descendants
	home2.welcome ();
	System.out.println (home2.toString ());
	System.out.print ("Property Value: $");
	System.out.printf ("%.2f\n", home2.payment ());
	System.out.println ("");

	// Apartment - interface version (uses 2 methods not in the 'Building' parent class)
	Apartment home4 = new Apartment ("135 Overlea Blvd", 20, 200, true, true); // create new apartment object
	home4.welcome ();
	System.out.println (home4.toString ());
	System.out.print ("Property Value: $");
	System.out.printf ("%.2f\n", home4.payment ());
	System.out.println (home4.information ());
    }
}
