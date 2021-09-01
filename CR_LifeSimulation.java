//Ryan Chang
//ICS4U1
//June 16, 2020
//Game of Life

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; // Needed for ActionListener
import javax.swing.event.*; // Needed for ActionListener
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import javax.swing.filechooser.FileNameExtensionFilter;

class CR_LifeSimulation extends JFrame implements ActionListener, ChangeListener
{
    static Colony colony = new Colony (0.1);
    static JSlider speedSlider = new JSlider ();
    static Timer t;
    int r, c, s, xcoord, ycoord;

    JComboBox columns = new JComboBox (); // number of columns combo box
    JComboBox rows = new JComboBox (); // number of rows combo box
    JComboBox success = new JComboBox (); // success rate combo box
    JTextField inputName = new JTextField (25); // save file input field
    JComboBox X = new JComboBox (); // top left x coordinate
    JComboBox Y = new JComboBox (); // top left y coordinate

    // ======================================================== constructor
    public CR_LifeSimulation ()
    {
	// Create Buttons
	JButton simulateBtn = new JButton ("Simulate");
	simulateBtn.addActionListener (this);

	JButton stopBtn = new JButton ("Stop");
	stopBtn.addActionListener (this);

	JButton saveBtn = new JButton ("Save");
	saveBtn.addActionListener (this);

	JButton loadBtn = new JButton ("Load");
	loadBtn.addActionListener (this);

	JButton popBtn = new JButton ("Populate");
	popBtn.addActionListener (this);

	JButton eraBtn = new JButton ("Eradicate");
	eraBtn.addActionListener (this);

	inputName.setText ("file name:");

	speedSlider.addChangeListener (this);

	// Fill Combo Boxes
	X.addItem ("X-Coordinate");
	for (int i = 1 ; i <= colony.getColumn () ; i++)
	{
	    X.addItem (i);
	}

	Y.addItem ("Y-Coordinate");
	for (int i = 1 ; i <= colony.getRow () ; i++)
	{
	    Y.addItem (i);
	}

	rows.addItem ("Row");
	for (int i = 1 ; i <= colony.getRow () ; i++)
	{
	    rows.addItem (i);
	}

	columns.addItem ("Column");
	for (int i = 1 ; i <= colony.getColumn () ; i++)
	{
	    columns.addItem (i);
	}

	success.addItem ("Success Rate");
	for (int i = 5 ; i <= 100 ; i += 5)
	{
	    success.addItem (i + "%");
	}

	// Create Content Pane & Set Layout
	JPanel content = new JPanel (); // Create a content pane
	content.setLayout (new BorderLayout ()); // Use BorderLayout for panel

	JPanel north = new JPanel ();
	north.setLayout (new FlowLayout ()); // Use FlowLayout for input area

	JPanel center = new JPanel ();
	center.setLayout (new FlowLayout ()); // row 2 of buttons

	DrawArea board = new DrawArea (500, 500);

	// Add Buttons to Content Pane
	north.add (simulateBtn);
	north.add (speedSlider);
	north.add (stopBtn);
	north.add (inputName);
	north.add (saveBtn);
	north.add (loadBtn);
	center.add (X);
	center.add (Y);
	center.add (rows);
	center.add (columns);
	center.add (success);
	center.add (popBtn);
	center.add (eraBtn);

	content.add (north, "North"); // Input area
	content.add (center, "Center");
	content.add (board, "South"); // Output area

	// Set Window
	setContentPane (content);
	pack ();
	setTitle ("Life Simulation");
	setSize (900, 650);
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo (null); // Center window.
    }


    // State Change Method
    public void stateChanged (ChangeEvent e)
    {
	if (t != null)
	    t.setDelay (400 - 4 * speedSlider.getValue ()); // 0 to 400 ms
    }


    // Button Functions
    public void actionPerformed (ActionEvent e)
    {
	r = 0; // set instance variable values
	c = 0;
	s = 0;
	xcoord = colony.getColumn () + 100;
	ycoord = colony.getRow () + 100;

	// Simulate Button Function
	if (e.getActionCommand ().equals ("Simulate"))
	{
	    Movement moveColony = new Movement (colony);
	    t = new Timer (200, moveColony); // create timer
	    t.start (); // start simulation
	}

	// Stop Button Function
	else if (e.getActionCommand ().equals ("Stop"))
	{
	    t.stop ();
	}

	// Save Button Function
	else if (e.getActionCommand ().equals ("Save"))
	{
	    try
	    {
		colony.save (inputName.getText ()); // takes in input name
		inputName.setText ("Successfully saved!");
	    }
	    catch (IOException e1)
	    { // error statement
		inputName.setText ("ERROR: please try again.");
	    }
	}

	// Load Button Function
	else if (e.getActionCommand ().equals ("Load"))
	{
	    JFileChooser chooser = new JFileChooser (); // create chooser
	    FileNameExtensionFilter filter = new FileNameExtensionFilter ("txt Files", "txt");
	    chooser.setFileFilter (filter);
	    int selected = chooser.showOpenDialog (getParent ());

	    if (selected == JFileChooser.APPROVE_OPTION)
	    { // if the selected file is valid load the file
		try
		{
		    colony.load (chooser.getSelectedFile ());
		}
		catch (Exception e1)
		{ // invalid file statement
		    inputName.setText ("INVALID FILE");
		}
	    }
	}

	// Populate Button Function
	else if (e.getActionCommand ().equals ("Populate"))
	{
	    try
	    {
		// take in the selected inputs
		xcoord = (int) X.getSelectedItem () - 1;
		ycoord = (int) Y.getSelectedItem () - 1;
		r = (int) rows.getSelectedItem ();
		c = (int) columns.getSelectedItem ();
		s = (int) (5 * success.getSelectedIndex ());

		// and call the populate method with said inputs
		colony.populate (xcoord, ycoord, r, c, s);
	    }
	    catch (Exception f)
	    {

	    }
	}

	// Eradicate Button Function
	else if (e.getActionCommand ().equals ("Delete"))
	{
	    try
	    {
		// take in the selected inputs
		xcoord = (int) X.getSelectedItem () - 1;
		ycoord = (int) Y.getSelectedItem () - 1;
		r = (int) rows.getSelectedItem ();
		c = (int) columns.getSelectedItem ();
		s = (int) (5 * success.getSelectedIndex ());

		// and call the eradicate method with said inputs
		colony.eradicate (xcoord, ycoord, r, c, s);
	    }
	    catch (Exception f)
	    {

	    }
	}

	repaint (); // refresh display
    }


    // DrawArea Class
    class DrawArea extends JPanel
    {
	public DrawArea (int width, int height)
	{
	    this.setPreferredSize (new Dimension (width, height)); // size
	}

	public void paintComponent (Graphics g)
	{
	    colony.show (g);
	}
    }


    // Movement Class
    class Movement implements ActionListener
    {
	private Colony colony;

	public Movement (Colony col)
	{
	    colony = col;
	}

	public void actionPerformed (ActionEvent event)
	{
	    colony.advance ();
	    repaint ();
	}
    }


    // ======================================================== method main
    public static void main (String[] args)
    {
	CR_LifeSimulation window = new CR_LifeSimulation ();
	window.setVisible (true);
    }
}

//Colony Class
class Colony
{
    private boolean grid[] []; // instance variable

    // Constructor
    public Colony (double density)
    {
	grid = new boolean [100] [100]; // create 100 x 100 grid
	for (int row = 0 ; row < grid.length ; row++) // iterate through array
	    for (int col = 0 ; col < grid [0].length ; col++)
		grid [row] [col] = Math.random () < density; // fill grid with random numbers
    }


    // Show the Board
    public void show (Graphics g)
    {
	for (int row = 0 ; row < grid.length ; row++)
	    for (int col = 0 ; col < grid [0].length ; col++)
	    {
		if (grid [row] [col]) // life
		    g.setColor (Color.black);
		else
		    g.setColor (Color.white);
		g.fillRect (col * 5 + 2, row * 5 + 2, 5, 5); // draw life form
	    }
    }


    // Row Accessor Method
    public int getRow ()
    {
	return grid.length; // return number of rows
    }


    // Column Accessor Method
    public int getColumn ()
    {
	return grid [0].length; // return number of columns
    }


    // Live Method (determines life or death of cell)
    public boolean live (int row, int col)
    {
	int neighbours = 0;

	// Count number of living cells
	for (int r = row - 1 ; r <= row + 1 ; r++)
	{ // iterate through array
	    for (int c = col - 1 ; c <= col + 1 ; c++)
	    {
		if (r >= 0 && r < grid.length && c >= 0 && c < grid [0].length && grid [r] [c])
		{
		    neighbours++;
		}
	    }
	}
	if (grid [row] [col])
	{
	    neighbours--;
	}

	// Statements that determine life of death of cell
	if (grid [row] [col] && neighbours > 1 && neighbours < 4)
	{
	    return true;
	}

	else if (!grid [row] [col] && neighbours == 3)
	{
	    return true;
	}

	else
	{
	    return false;
	}
    }


    // Advance Method
    public void advance ()
    {
	boolean nextGen[] [] = new boolean [grid.length] [grid [0].length]; // create next generation of life forms
	for (int row = 0 ; row < grid.length ; row++)
	    for (int col = 0 ; col < grid [0].length ; col++)
		nextGen [row] [col] = live (row, col); // determine life/death status
	grid = nextGen; // update life forms
    }


    // Populate Method
    public void populate (int xcoord, int ycoord, int r, int c, int s)
    {
	double survivalRate = s / 100.0; // compute survival rate

	// Iterate through grid from given x,y coordinates until the end of the
	// row/column
	for (int i = xcoord ; i < xcoord + r ; i++)
	{
	    for (int j = ycoord ; j < ycoord + c ; j++)
	    {
		if (!grid [j] [i] && Math.random () < survivalRate && j < grid.length && i < grid [0].length)
		    grid [j] [i] = true; // populate space
	    }
	}
    }


    // Eradicate Method
    public void eradicate (int xcoord, int ycoord, int r, int c, int s)
    {
	double survivalRate = s / 100.0; // compute survival rate

	// Iterate through grid from given x,y coordinates until the end of the
	// row/column
	for (int i = xcoord ; i < xcoord + r ; i++)
	{
	    for (int j = ycoord ; j < ycoord + c ; j++)
	    {
		if (grid [j] [i] && Math.random () < survivalRate && j < grid.length && i < grid [0].length)
		    grid [j] [i] = false; // kill cell
	    }
	}
    }


    // Method to save to text file
    public void save (String name) throws IOException
    {
	String temp = "";
	for (int r = 0 ; r < grid.length ; r++)
	{ // iterate through array
	    for (int c = 0 ; c < grid [r].length ; c++)
	    {
		if (grid [r] [c])
		{
		    temp += "1 "; // live cell
		}
		else
		{
		    temp += "0 "; // dead cell
		}
	    }
	    temp += "\n"; // make a new line when at the end of the row
	}

	File saveFile = new File ("C:" + name + ".txt");

	// Create and save new file
	try
	{
	    saveFile.createNewFile ();
	}
	catch (IOException e)
	{

	}
	FileWriter fw = new FileWriter (saveFile);
	fw.write (temp); // store file
	fw.close ();
    }


    // Method to load a text file
    public void load (File f) throws FileNotFoundException
    {
	int rows = 0; // row and column counters
	int cols = 0;

	// Row Counter
	Scanner sc = new Scanner (f);
	while (sc.hasNextLine ())
	{
	    rows++;
	    sc.nextLine ();
	}
	sc.close ();

	// Column Counter
	Scanner sc2 = new Scanner (f);
	cols = sc2.nextLine ().replaceAll ("\\s+", "").length (); // remove noise e.g. spaces
	sc2.close ();

	// Create 2D Integer Array
	int[] [] intArr = new int [rows] [cols];

	// Copy elements from file into the new array
	Scanner sc3 = new Scanner (f);
	for (int i = 0 ; i < rows ; i++)
	{
	    for (int j = 0 ; j < cols ; j++)
	    {
		intArr [i] [j] = sc3.nextInt ();
	    }
	}
	sc3.close ();

	// Create 2D Boolean Array (initialize the grid for the program)
	grid = new boolean [rows] [cols];

	// Iterate through the integer array
	for (int i = 0 ; i < rows ; i++)
	{
	    for (int j = 0 ; j < cols ; j++)
	    {
		if (intArr [i] [j] == 1)
		{ // if there's a one create alive cell
		    grid [i] [j] = true;
		}
		else
		{ // if it's a 0 create dead cell
		    grid [i] [j] = false;
		}
	    }
	}
    } // load method
} // Colony Class
