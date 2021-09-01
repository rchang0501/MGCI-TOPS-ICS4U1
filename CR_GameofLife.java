//Ryan Chang
//ICS4U1
//June 16, 2020
//Game of Life

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

class LifeSimulationGUI extends JFrame implements ActionListener, ChangeListener
{
    static Scanner sc = new Scanner (System.in); // scanner
    static Colony colony = new Colony (0.1); // colony
    static Timer t; // timer
    static int x1, y1, x2, y2; // coordinate points on the grid
    static JSlider speedSlider = new JSlider (); // simulate speed slider
    static JComboBox < String > menu; // combo box for populate/eradicate
    JTextField inputName = new JTextField (15); // save file input field

    // ======================================================== constructor
    public LifeSimulationGUI ()
    {
	// 1... Create/initialize components
	// Simulate Button
	JButton simulateBtn = new JButton ("Simulate");
	simulateBtn.addActionListener (this);

	// Populate/Eradicate Drop-down menu
	String[] s1 = {"Populate", "Eradicate"};
	menu = new JComboBox < > (s1);
	menu.addActionListener (this);

	// Save Button
	JButton save = new JButton ("Save");
	save.addActionListener (this);

	// Load Button
	JButton load = new JButton ("Load");
	load.addActionListener (this);

	// Save file text file
	inputName.setText ("file name:");

	// Initialize coordinates
	x1 = y1 = x2 = y2 = 0;

	// Mound Listener
	MouseListener listener = new MouseListener ();
	addMouseListener (listener);
	addMouseMotionListener (listener);

	// Speed Slider
	speedSlider.addChangeListener (this);

	// 2... Create content pane, set layout
	JPanel content = new JPanel (); // Create a content pane
	content.setLayout (new BorderLayout ()); // Use BorderLayout for panel

	JPanel north = new JPanel ();
	north.setLayout (new GridLayout ()); // Use FlowLayout for input area

	DrawArea board = new DrawArea (500, 500);

	// 3... Add the components to the input area.
	north.add (simulateBtn);
	north.add (speedSlider);
	north.add (inputName);
	north.add (save);
	north.add (load);
	north.add (menu);

	content.add (north, "North"); // Input area
	content.add (board, "South"); // Output area

	// 4... Set this window's attributes.
	setContentPane (content);
	pack ();
	setTitle ("Life Simulation Demo");
	setSize (510, 570);
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo (null); // Center window.
    }


    // Given State Changed Method
    public void stateChanged (ChangeEvent e)
    {
	if (t != null)
	    t.setDelay (400 - 4 * speedSlider.getValue ()); // 0 to 400 ms
    }


    // Button Functions
    public void actionPerformed (ActionEvent e)
    {

	// Simulate Button Function
	if (e.getActionCommand ().equals ("Simulate"))
	{
	    Movement moveColony = new Movement (colony); // ActionListener
	    t = new Timer (200, moveColony); // set up timer
	    t.start (); // start simulation
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
		    // inputName.setText("INVALID FILE");
		}
	    }
	}
	repaint (); // refresh display of deck
    }


    // Draw Area Class
    static class DrawArea extends JPanel
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


    // Mouse Listener (to determine the populate/eradicate area)
    class MouseListener extends MouseAdapter
    {

	public void mousePressed (MouseEvent e)
	{
	    x1 = e.getX (); // start points for drag
	    y1 = e.getY ();
	}

	public void mouseReleased (MouseEvent e)
	{
	    x2 = e.getX (); // end points for drag
	    y2 = e.getY ();

	    String s = (String) Objects.requireNonNull (menu.getSelectedItem ());
	    if (s.equals ("Populate"))
	    { // populate or eradicate area based on user choice
		colony.populate (x1, y1, x2, y2);
	    }
	    else
	    {
		colony.eradicate (x1, y1, x2, y2);
	    }
	    repaint ();
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
}

// Colony Class
class Colony
{
    private boolean[] [] grid; // instance variable

    // Constructor
    public Colony (double density)
    {
	grid = new boolean [100] [100]; // create 100 x 100 grid
	for (int row = 0 ; row < grid.length ; row++) // iterate through array
	    for (int col = 0 ; col < grid [0].length ; col++)
		grid [row] [col] = Math.random () < density; // fill grid with random numbers
    }


    // Show Grid
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
	boolean[] [] nextGen = new boolean [grid.length] [grid [0].length]; // create next generation of life forms
	for (int row = 0 ; row < grid.length ; row++)
	    for (int col = 0 ; col < grid [0].length ; col++)
		nextGen [row] [col] = live (row, col); // determine life/death status
	grid = nextGen; // update life forms
    }


    // Populate Method
    public void populate (int x1, int y1, int x2, int y2)
    {
	for (int i = Math.min (y1, y2) / 6 ; i < Math.min (Math.max (y1, y2) / 6, 100) ; i++)
	{
	    for (int j = Math.min (x1, x2) / 6 ; j < Math.min (Math.max (x1, x2) / 6, 100) ; j++)
	    {
		double randomDouble = Math.random ();
		if (randomDouble > 0.1)
		{
		    grid [i] [j] = true;
		}
	    }
	}
    }


    // Eradicate Method
    public void eradicate (int x1, int y1, int x2, int y2)
    {
	for (int i = Math.min (y1, y2) / 6 ; i < Math.min (Math.max (y1, y2) / 6, 100) ; i++)
	{
	    for (int j = Math.min (x1, x2) / 6 ; j < Math.min (Math.max (x1, x2) / 6, 100) ; j++)
	    {
		double randomDouble = Math.random ();
		if (randomDouble > 0.1)
		{
		    grid [i] [j] = false;
		}
	    }
	}
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
}

// Main Driver
public class CR_GameofLife
{
    public static void main (String[] args)
    {
	LifeSimulationGUI window = new LifeSimulationGUI ();
	window.setVisible (true);
    }
}
