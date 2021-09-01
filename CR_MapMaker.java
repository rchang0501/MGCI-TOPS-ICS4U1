//Ryan Chang
//June 17, 2020
//ICS4U1
//Map Maker

import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

class MapGUI extends JFrame implements MouseListener, ActionListener
{
    Map map = new Map (40, 30, 20, 20);
    JComboBox itemBox; // item options
    char code[] = {'S', 'W', 'T', 'D', 'K', 'M', };  // code for item options
    static JTextField found; // display for the number of items found in search
    JTextField display; // file display

    // ======================================================== constructor
    public MapGUI ()
    {
	// 1... Create/initialize components
	JButton searchBtn = new JButton ("Search");
	searchBtn.addActionListener (this);

	JButton saveBtn = new JButton ("Save");
	saveBtn.addActionListener (this);

	JButton loadBtn = new JButton ("Load");
	loadBtn.addActionListener (this);

	String[] list = {"Space", "Wall", "Treasure", "Door", "Key", "Monster"};
	itemBox = new JComboBox (list);
	itemBox.addActionListener (this);

	found = new JTextField (String.valueOf (map.search (String.valueOf (itemBox.getSelectedItem ()))));
	display = new JTextField (40);

	// 2... Create content pane, set layout
	JPanel content = new JPanel (); // Create a content pane
	content.setLayout (new BorderLayout ()); // Use BorderLayout for panel
	JPanel north = new JPanel (); // where the buttons, etc. will be
	north.setLayout (new FlowLayout ()); // Use FlowLayout for input area

	DrawArea board = new DrawArea (800, 600); // Area for map to be displayed

	// 3... Add the components to the input area.
	north.add (itemBox);
	north.add (searchBtn);
	north.add (found);
	north.add (saveBtn);
	north.add (loadBtn);
	content.add (north, "North"); // Input area
	content.add (board, "Center"); // Deck display area

	content.addMouseListener (this);

	// 4... Set this window's attributes.
	setContentPane (content);
	pack ();
	setTitle ("Map Demo");
	setSize (850, 700);
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo (null); // Center window.
    }


    // Action Outputs
    public void actionPerformed (ActionEvent e)
    {

	// Search Function
	if (e.getActionCommand ().equals ("Search"))
	    found.setText (String.valueOf (map.search (String.valueOf (itemBox.getSelectedItem ()))));

	// Save Function
	else if (e.getActionCommand ().equals ("Save"))
	{
	    try
	    {
		map.save (); // save map
	    }
	    catch (IOException ex)
	    {
		ex.printStackTrace ();
	    }
	}

	// Load Function
	else if (e.getActionCommand ().equals ("Load"))
	{
	    // load text file
	    JFileChooser chooser = new JFileChooser ();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter ("txt Files", "txt");
	    chooser.setFileFilter (filter);
	    int returnVal = chooser.showOpenDialog (getParent ());
	    if (returnVal == JFileChooser.APPROVE_OPTION)
	    {
		try
		{
		    map.load (chooser.getSelectedFile ());
		}
		catch (Exception e1)
		{
		    display.setText ("Invalid File");
		}
	    }
	}
	repaint (); // do after each action taken to update deck
    }


    // Draw Area Class
    class DrawArea extends JPanel
    {
	public DrawArea (int width, int height)
	{
	    this.setPreferredSize (new Dimension (width, height)); // size
	}

	public void paintComponent (Graphics g)
	{
	    map.print (g);
	}
    }


    // Must implement all methods from listener
    public void mousePressed (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mouseClicked (MouseEvent e)
    {
	// checks the block being added
	char c = code [itemBox.getSelectedIndex ()];
	// checks clicked area
	int x = e.getX () / 20;
	// buttons take up space --> remove from y-field
	int y = e.getY () / 20 - 2;
	try
	{
	    map.add (x, y, c);
	}
	catch (Exception E)
	{
	}
	repaint ();
    }
}

// Map Class
class Map extends Component
{
    private char[] [] map; // instance variables
    private int width, height, r, c;

    // Constructor (default map)
    public Map (int cols, int rows, int blockwidth, int blockheight)
    {
	width = blockwidth;
	height = blockheight;
	r = rows;
	c = cols;
	map = new char [rows] [cols]; // define 2-d array size

	for (int row = 0 ; row < rows ; row++)
	    for (int col = 0 ; col < cols ; col++)
	    {
		if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1)
		    map [row] [col] = 'W'; // put up a wall
		else
		    map [row] [col] = 'S'; // blank space
	    }
	map [rows - 1] [cols / 2] = 'D'; // make a door
	map [rows - 1] [cols / 2 + 1] = 'D';
    }


    // Print Method to display map on screen
    public void print (Graphics g)
    {
	Image image1 = null, image2 = null, image3 = null; // create image objects

	// Read images
	try
	{
	    image1 = ImageIO.read (new File ("monster.gif")); // load file into Image object
	}
	catch (IOException e)
	{
	    System.out.println ("Monster not found");
	}
	try
	{
	    image2 = ImageIO.read (new File ("chest.png")); // load file into Image object
	}
	catch (IOException e)
	{
	    System.out.println ("Chest not found");
	}
	try
	{
	    image3 = ImageIO.read (new File ("key.jpg")); // load file into Image object
	}
	catch (IOException e)
	{
	    System.out.println ("Key not found");
	}

	for (int row = 0 ; row < map.length ; row++) // number of rows
	{
	    for (int col = 0 ; col < map [0].length ; col++) // length of first row
	    {
		if (map [row] [col] == 'W')
		{ // wall
		    g.setColor (Color.black);
		    g.fillRect (col * width, row * height, width, height); // draw block
		}

		else if (map [row] [col] == 'D')
		{ // door
		    g.setColor (Color.red);
		    g.fillRect (col * width, row * height, width, height); // draw block
		}

		else if (map [row] [col] == 'M')
		{ // monster
		    g.drawImage (image1, col * 20, row * 20, 20, 20, null); // draw image
		}

		else if (map [row] [col] == 'T')
		{ //treasure
		    g.drawImage (image2, col * 20, row * 20, 20, 20, null); // draw image
		}

		else if (map [row] [col] == 'K')
		{ //key
		    g.drawImage (image3, col * 20, row * 20, 20, 20, null); // draw image
		}

		else if (map [row] [col] == 'S')
		{ // blank space
		    g.setColor (Color.white);
		    g.fillRect (col * width, row * height, width, height); // draw block
		}
	    }
	}
    }


    // Adding elements method
    public void add (int x, int y, char item)
    {
	// Add a wall down the entire column when user clicks top wall
	if (y == 0 && item == 'W')
	{
	    for (int i = 0 ; i < map.length ; i++)
	    {
		map [i] [x] = 'W';
	    }
	}
	// Add a wall accross the entire row when user clicks left wall
	if (x == 0 && item == 'W')
	{
	    for (int i = 0 ; i < map [0].length ; i++)
	    {
		map [y] [i] = 'W';
	    }
	}
	// Add element to selected block
	else
	{
	    map [y] [x] = item;
	}
    }


    // Save Method
    public void save () throws IOException
    {
	JFileChooser chooser = new JFileChooser (); // create a file selector
	chooser.setFileSelectionMode (JFileChooser.FILES_ONLY);
	int returnVal = chooser.showOpenDialog (this);
	String path;

	if (returnVal == JFileChooser.APPROVE_OPTION)
	{
	    path = chooser.getSelectedFile ().getAbsolutePath ();
	    StringBuilder s = new StringBuilder (); // create string builder

	    for (int i = 0 ; i < map.length ; i++)
	    { // iterate through array
		for (int j = 0 ; j < map [0].length ; j++)
		{
		    s.append (map [i] [j]);
		}
		s.append ("\n");
	    }
	    FileWriter fileWriter = new FileWriter (path, false);
	    fileWriter.write (s.toString ());
	    fileWriter.close ();
	}
    }


    // Load method
    public void load (File f) throws FileNotFoundException
    {
	int rows = 0, columns = 0; // row and column counters

	// Row counter
	Scanner sc = new Scanner (f);
	while (sc.hasNextLine ())
	{
	    rows++;
	    sc.nextLine ();
	}
	sc.close ();

	// Column Counter
	Scanner sc2 = new Scanner (f);
	columns = sc2.nextLine ().replaceAll ("\\s+", "").length (); // remove noise
	sc2.close ();

	// Make a new 2D array of the same size of the file
	map = new char [rows] [columns];

	// Read and insert elements from the file
	Scanner sc3 = new Scanner (f);
	for (int r = 0 ; r < rows ; r++)
	{
	    for (int c = 0 ; c < columns ; c++)
	    {
		map [r] [c] = sc3.next ().charAt (0);
	    }
	}
	sc3.close ();
    }


    // Search method
    public int search (String s)
    {
	int counter = 0;
	for (int i = 0 ; i < r ; i++)
	{ // iterate through list
	    for (int j = 0 ; j < c ; j++)
	    {
		if (map [i] [j] == s.charAt (0))
		{ // if they match increment counter
		    counter++;
		}
	    }
	}
	return counter;
    }
}

// Main Driver
public class CR_MapMaker
{

    public static void main (String[] args)
    {
	MapGUI map = new MapGUI ();
	map.setVisible (true);
    }
}
