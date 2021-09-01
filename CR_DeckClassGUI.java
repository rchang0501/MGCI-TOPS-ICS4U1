//Ryan Chang
//June 14, 2020
//ICS4U1
//Deck Assignment

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class DeckGUI extends JFrame
{
    static Deck deck = new Deck ();
    JComboBox < String > addCombo = new JComboBox < String > (); // add combo box
    JComboBox < Integer > dealCombo = new JComboBox < Integer > (); // deal combo box
    JComboBox < Character > suitCombo = new JComboBox < Character > (); // suit combo box
    JButton deal = new JButton ("Deal"); // deal button
    JPanel north = new JPanel ();

    // ======================================================== constructor
    public DeckGUI ()
    {
	// 1... Create/initialize components
	BtnListener btnListener = new BtnListener (); // listener for all buttons

	// Shuffle Button
	JButton shuffleBtn = new JButton ("Shuffle");
	shuffleBtn.addActionListener (btnListener);

	// Sort Rank Button
	JButton sortBtnR = new JButton ("Sort Rank");
	sortBtnR.addActionListener (btnListener);

	// Sort Suit Button
	JButton sortBtnS = new JButton ("Sort Suit");
	sortBtnS.addActionListener (btnListener);

	// Deal Button
	deal.addActionListener (btnListener);

	// Add Button
	JButton add = new JButton ("Add");
	add.addActionListener (btnListener);

	// Search Button
	JButton search = new JButton ("Search");
	search.addActionListener (btnListener);
	for (int i = 1 ; i <= deck.size () ; i++)
	{
	    dealCombo.addItem (i); // add numbers 1 to size
	}
	for (int i = 2 ; i < 11 ; i++)
	{ // 2 to 10
	    addCombo.addItem (i + ""); // add cards to combo box
	}

	// Add Suits
	suitCombo.addItem ('S');
	suitCombo.addItem ('H');
	suitCombo.addItem ('C');
	suitCombo.addItem ('D');

	// Add Royals
	addCombo.addItem ("J");
	addCombo.addItem ("Q");
	addCombo.addItem ("K");
	addCombo.addItem ("A");

	// 2... Create content pane, set layout
	JPanel content = new JPanel (); // Create a content pane
	content.setLayout (new BorderLayout ()); // Use BorderLayout for panel
	north.setLayout (new FlowLayout ()); // Use FlowLayout for input area

	DrawArea board = new DrawArea (700, 400); // Area for cards to be displayed

	// 3... Add the components to the input area.
	north.add (shuffleBtn);
	north.add (sortBtnR);
	north.add (sortBtnS);
	north.add (add);
	north.add (search);
	north.add (addCombo);
	north.add (suitCombo);
	content.add (north, "North"); // Input area
	content.add (board, "South"); // Deck display area
	north.add (deal);
	north.add (dealCombo);

	// 4... Set this window's attributes.
	setContentPane (content);
	pack ();
	setTitle ("Deck Demo");
	setSize (700, 500);
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo (null); // Center window.

    }


    // put ActionListener class for your buttons here
    class BtnListener implements ActionListener
    {
	public void actionPerformed (ActionEvent e)
	{
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		deck.get (i).setFaceup (true); // show all cards
	    }

	    // Shuffle Implementation
	    if (e.getActionCommand ().equals ("Shuffle"))
		deck.shuffle ();

	    // Sort Rank Implementation
	    else if (e.getActionCommand ().equals ("Sort Rank"))
		deck.quickSort (deck.list ());

	    // Sort Suit Implementation
	    else if (e.getActionCommand ().equals ("Sort Suit"))
		deck.selectionSort ();

	    // Deal Implementation
	    else if (e.getActionCommand ().equals ("Deal"))
	    {
		deck.deal (dealCombo.getSelectedIndex ());
		dealCombo.removeItem (deck.size () + 1); // update combo box
	    }

	    // Add Implementation
	    else if (e.getActionCommand ().equals ("Add"))
	    {
		int cardNum = addCombo.getSelectedIndex ();
		cardNum += suitCombo.getSelectedIndex () * 13;
		deck.add (new Card (cardNum));
		dealCombo.addItem (deck.size ()); // update combo box
	    }

	    // Search Implementation
	    else if (e.getActionCommand ().equals ("Search"))
	    {
		int cardNum = addCombo.getSelectedIndex (); // get number
		cardNum += suitCombo.getSelectedIndex () * 13; // get suit
		int[] found = deck.search (new Card (cardNum)); // get card positions
		ArrayList < Integer > found2 = new ArrayList < > ();
		for (int i = 0 ; i < found.length ; i++)
		{ // loop through found cards
		    found2.add (found [i]); // add elements to found2
		}
		for (int i = 0 ; i < deck.size () ; i++)
		{
		    if (!found2.contains (i)) // if it is not the searched card set it face down
			deck.get (i).setFaceup (false);
		}
	    }

	    repaint (); // do after each action taken to update deck
	    if (deck.size () == 0)
	    {
		deal.setEnabled (false); // disable deal button if there are no cards
	    }
	    else
	    {
		deal.setEnabled (true);
	    }
	}
    }


    class DrawArea extends JPanel
    {
	public DrawArea (int width, int height)
	{
	    this.setPreferredSize (new Dimension (width, height)); // size
	}

	public void paintComponent (Graphics g)
	{
	    deck.show (g);
	}
    }
}

//-------------------------------------- Card Class ------------------------------------------------------
class Card
{
    private int rank, suit; // Instance variables
    private Image image;
    private boolean faceup;
    private static Image back;

    // Constructor
    public Card (int cardNum)
    {
	rank = cardNum % 13;
	suit = cardNum / 13;
	setFaceup (true);

	image = null;

	try
	{
	    image = ImageIO.read (new File ("cards/" + (cardNum + 1) + ".gif"));
	    back = ImageIO.read (new File ("cards/b.gif"));
	}
	catch (IOException e)
	{
	    System.out.println ("File not found");
	}
    }


    // Show Method
    public void show (Graphics g, int x, int y)
    { // draws card face up or face down
	if (isFaceup ())
	    g.drawImage (image, x, y, null);
	else
	    g.drawImage (back, x, y, null);

    }


    // Rank Accessor
    public int rank ()
    {
	return rank;
    }


    // Rank Accessor
    public int suit ()
    {
	return suit;
    }


    // Faceup Identifier
    public boolean isFaceup ()
    {
	return faceup;
    }


    // Card Orientation Changer Method
    public void setFaceup (boolean faceup)
    {
	this.faceup = faceup;
    }


    // Comparing if the Cards are the same Method
    public boolean equals (Card c)
    {
	if (rank == c.rank () && suit == c.suit ())
	    return true;
	return false;
    }
}

//-------------------------------------- Deck Class ------------------------------------------------------
class Deck
{
    private ArrayList < Card > deck; // instance variable

    // Constructor
    public Deck ()
    {
	deck = new ArrayList < Card > ();
	for (int i = 0 ; i < 52 ; i++)
	{
	    deck.add (new Card (i));
	}
    }


    // Card in Deck Accessor Method
    public Card get (int index)
    {
	return deck.get (index);
    }


    // Deck Size Accessor Method
    public int size ()
    {
	return deck.size ();
    }


    // Deck Accessor Method
    public ArrayList < Card > list ()
    {
	return deck;
    }


    // Card Display Method
    public void show (Graphics g)
    { // draws card face up or face down
	for (int c = 0 ; c < deck.size () ; c++)
	{
	    deck.get (c).show (g, c % 13 * 20 + 150, c / 13 * 50 + 20);
	}
    }


    // Shuffler Method
    public void shuffle ()
    {
	Random r = new Random ();
	ArrayList < Card > temp = new ArrayList < Card > (); // create temporary list
	int index = 0;
	while (index < deck.size ())
	{
	    int x = r.nextInt (deck.size ());
	    temp.add (deck.remove (x));
	}
	deck = temp; // destroy temp and make deck equal to it
    }


    // Sorting Helper Method
    public void sort (ArrayList < Card > deck, int low, int high)
    {
	int i = low;
	int j = high;
	Card pivot = deck.get (low + (high - low) / 1); // the pivot acts as the middle bound
	while (i <= j)
	{

	    // Increment for each element to the left of pivot (smaller)
	    while (deck.get (i).rank () < pivot.rank ())
	    {
		i++;
	    }
	    // Increment for each element to the right of pivot (bigger)
	    while (deck.get (j).rank () > pivot.rank ())
	    {
		j--;
	    }

	    // If upper bound is greater than lower bound
	    if (i <= j)
	    {
		Card temp = deck.get (i);
		deck.set (i, deck.get (j));
		deck.set (j, temp);
		i++;
		j--;
	    }
	}
	if (low < j)
	    sort (deck, low, j);
	if (i < high)
	    sort (deck, i, high);
    }


    // Quick Sort Method
    public void quickSort (ArrayList < Card > d)
    {
	if (d.size () > 1)
	{
	    sort (d, 0, d.size () - 1); // helper call
	}
    }


    // Selection Sort Method
    public void selectionSort ()
    {
	for (int i = 0 ; i < deck.size () - 1 ; i++)
	{
	    int max = i; // assume current card is the highest
	    for (int j = i + 1 ; j < deck.size () ; j++)
	    {
		if (deck.get (j).suit () < deck.get (max).suit ())
		{
		    max = j;
		}
		else if (deck.get (j).suit () == deck.get (max).suit ())
		{ // condition for the same suit
		    if (deck.get (j).rank () > deck.get (max).rank ())
		    {
			max = j;
		    }
		}
	    }
	    Card temp = deck.get (max);
	    deck.set (max, deck.get (i));
	    deck.set (i, temp);
	}
    }


    // Add Method
    public void add (Card card)
    {
	deck.add (card);
    }


    // Search Method
    public int[] search (Card card)
    {
	int[] arr = new int [0];
	int[] temp = new int [1];
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    if (deck.get (i).equals (card))
	    { // if the card matches iterate through list and add elements
		for (int j = 0 ; j < arr.length ; j++)
		{
		    temp [j] = arr [j];
		}
		temp [arr.length] = i;
		arr = temp; // update arr
		temp = new int [arr.length + 1]; // update size of temp
	    }
	}
	return arr; // return the array
    }


    // Deal Method
    public Card deal (int index)
    {
	return deck.remove (index);
    }
}

//-------------------------------------- Driver ------------------------------------------------------
class CR_DeckClassGUI
{
    // ======================================================== method main
    public static void main (String[] args)
    {
	DeckGUI window = new DeckGUI ();
	window.setVisible (true);
    }
}
