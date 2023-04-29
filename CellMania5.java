// Bharathi Kaliraj Shanmugapriya
// CellMania5.java
/* Description: This game now has a front page that has the buttons with all
 * Action Listeners. When the user clicks on the play button, they are taken
 * to a page where they can select their levels. Both in Levels 1 and 2,
 * three random images are shown and they are taken to the Click Terms page. In that page,
 * the users will need to click on the terms in the correct order (using
 * what was shown in the previous page). If they get it wrong, or if they run 
 * out of the time, they are taken to the questions page. In lEvel1, the
 * questions are just a little different and they are mulitple choice, and
 * they only need to answer 1 question.In level 2, the questions are text entry based questions
 * and they need to answer 3 questions. If they get it
 * wrong, They need to type the correct answer thrice. If they get it right,
 * they are taken back the the Memrize panel (the page that shows the user
 * the three random images). However, if they do clikc on the correct
 * terms in the correct order from the Click Terms page,
 * they are taken back to the Memorize Page. In the Settings page,
 * they can adjust the time they get in the Click Terms page.
 * Working on:
 * All the concepts we've learned so far
 * - FileIO, reading and writing to Files
 * - Layouts, the panels use a unique layout to format the components
 * - Graphics, Drawing images, strings, and more
 * - Components include JtextArea, text fields, Radio buttons and more
 * - ActionListeners, Action events to respond to the compoenents and to check answers
 * - Methods and Classes and Construcots, we pass in various parameters in the
 * 		constructors and methods
 * - Arrays - going through arrays, changing it and using it to generate rand quesitons
 * */


import java.lang.Math;
import java.util.Scanner;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* This small class creates a JFrame that holds all the panels
 * of the entire game.
 * */
public class CellMania5 extends JFrame
{
	public static void main(String[] args)
	{
		CellMania5 game = new CellMania5();
		game.runIt();
	}
	
	/* Instantiates a JFrame object with title CelL MANIA and calls
	 * the Panel class that has the card layout. It is at last set visible to true.
	 * */
	public void runIt()
	{
		JFrame gameFrame = new JFrame("Cell MANIA!!!");
		gameFrame.setSize(1300, 1000);
		gameFrame.setLocation(10, 10);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CellMania5Panels gp = new CellMania5Panels();
		gameFrame.getContentPane().add(gp);
		gameFrame.setVisible(true);
	}
}

/* This class has a CardLayout, and calls the classes of the other panels.
 * These panels are added to the CardLayout of this panel.
 * */
class CellMania5Panels extends JPanel
{
	private CardLayout gameCards; // Card Layout instance is used to pass to other classes
	
	/* All panel classes are declared and instantiated, and added to the card layout.
	 * */
	public CellMania5Panels()
	{
		gameCards = new CardLayout();
		setLayout(gameCards);
		
		Directions dr = new Directions(this, gameCards);
		SelectLevel sl = new SelectLevel(this, gameCards);
		MemorizeStation ms = new MemorizeStation(this, gameCards);
		ClickTerms ct = new ClickTerms(this, gameCards, ms, sl);
		FirstPage fp = new FirstPage(this, gameCards);
		Questions qs = new Questions(this, gameCards, ct);
		ScoreBoard sb = new ScoreBoard(this, gameCards, ct);
		Glossary glossry = new Glossary(this, gameCards);
		Settings setting = new Settings(this, gameCards, ct);
		QuestionsMultipleChoice qmc = new QuestionsMultipleChoice(this, gameCards, ct);
		TypeThrice tt = new TypeThrice(this, gameCards, qs, sl, qmc);
		EnterName en = new EnterName(this, gameCards, ct);
		
		add(fp, "First page");
		add(dr, "Directions");
		add(sb, "Score Board");
		add(ms, "Memorize station");
		add(ct, "Click terms");
		add(qs, "Questions");
		add(glossry, "Glossary");
		add(setting, "Settings");
		add(tt, "Type Thrice");
		add(en, "Enter Name");
		add(sl, "Select Level");
		add(qmc, "Questions Multiple Choice");
	}
}

/* This class consists of the FirstPage, or the home page.
 * */
class FirstPage extends JPanel
{
	private CellMania5Panels firPgGP; // object of CellMania5Panels is set equal to this variable, for navigation using CardLayout
	private CardLayout clFirPg; // object of the card layout (used in CellManiaPanels class, also used for navigation using CardLayout
	private String fipCellImageName; // name of the cell image that will be displayed on the First Page
	private Image fpImage; // stores the image that will be displayed on the first image
	
	/* There are a few buttons created here, that leads to the other panels. This
	 * helps user navigate through the different panels. All of them have
	 * an action listener class that flips to the according card.
	 * CellMania5Panels object and CardLayout for switching between the card layout.
	 * */
	public FirstPage(CellMania5Panels fpGP, CardLayout clFP)
	{
		fipCellImageName = new String("First_page_cell_Image.jpeg");
		fpImage = null;
		getFPImage();
		
		firPgGP = fpGP;
		clFirPg = clFP;
		setLayout(null);
		setBackground(Color.BLUE);
		
		DirButtonListener dbl = new DirButtonListener();
		Font titleFont = new Font("Times New Roman", Font.BOLD, 50);
		JPanel titleHold = new JPanel();
		JLabel title = new JLabel("Cell MANIA!!!");
		title.setFont(titleFont);
		titleHold.setBounds(0, 0, 1300, 100);
		titleHold.add(title);
		add(titleHold);
		
		JButton start = new JButton("START");
		start.setBounds(425, 475, 375, 100);
		start.addActionListener(dbl);
		add(start);
		
		JButton dir = new JButton("Directions");
		dir.setBounds(435, 600, 325, 50);
		dir.addActionListener(dbl);
		add(dir);
		
		JButton scoreBoard = new JButton("Score Board");
		scoreBoard.setBounds(435, 675, 325, 50);
		scoreBoard.addActionListener(dbl);
		add(scoreBoard);
		
		JButton gloss = new JButton("Glossary");
		gloss.setBounds(380, 413, 450, 50);
		gloss.addActionListener(dbl);
		add(gloss);
		
		JButton exit = new JButton("EXIT");
		exit.setBounds(450, 750, 300, 50);
		exit.addActionListener(dbl);
		add(exit);
		
		JButton set = new JButton("Settings");
		set.setBounds(30, 100, 200, 50);
		set.addActionListener(dbl);
		add(set);
	}
	
	/* This method paints the chosen image, stored in the variable fpImage.
	 * */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(fpImage, 450, 200, 300, 200, this);
	}
	
	/* This method attemps to read a file (a picture file) and saves it into fpImage.
	 * Else the error message will be printed. This is done using a try-catch
	 * block.
	 * */
	public void getFPImage()
	{
		File fpPicFile = new File(fipCellImageName);
		try
		{
			fpImage = ImageIO.read(fpPicFile);
		}
		catch(IOException e)
		{
			System.err.println("\n\n\n" + fipCellImageName + " can't be found.\n\n\n");
			e.printStackTrace();
		}
	}
	
	/* This is the action listener class all the buttons use.
	 * */
	class DirButtonListener implements ActionListener
	{	
		/* The string sBCommand is used to save the action done by the user,
		 * or more specifially what button was clicked. Using if-else blocks,
		 * the corresponding panel is shown.
		 * */ 
		public void actionPerformed(ActionEvent evt)
		{
			String sBCommand = evt.getActionCommand();
			if (sBCommand.equals("Directions"))
			{
				clFirPg.show(firPgGP, "Directions");
			}
			
			else if(sBCommand.equals("Score Board") )
			{
				clFirPg.show(firPgGP, "Score Board");
			}
			
			else if(sBCommand.equals("START") )
			{
				clFirPg.show(firPgGP, "Select Level");
			}
			
			else if(sBCommand.equals("Glossary") )
			{
				clFirPg.show(firPgGP, "Glossary");
			}
			
			else if(sBCommand.equals("EXIT") )
			{
				System.exit(3);
			}
			
			else if (sBCommand.equals("Settings") )
			{
				clFirPg.show(firPgGP, "Settings");
			}
		}
	}
}

/* This class consists of the Directions panel.
 * */
class Directions extends JPanel
{
	private CellMania5Panels dirGP; // object of CellMania5Panels is set equal to this variable, for navigation using CardLayout
	private CardLayout clDir; // object of the card layout (used in CellManiaPanels class, also used for navigation using CardLayout
	
	/* There is a home button and text area here, which attempts to explain
	 * the instructions of the game. It also recieves CellMania5Panels object
	 * and CardLayout for switching between the card layout.
	 * */
	public Directions(CellMania5Panels dGP, CardLayout clD)
	{
		dirGP = dGP;
		clDir = clD;
		
		setLayout(new BorderLayout() );
		setBackground(Color.YELLOW);
		
		Font dirTitleFont = new Font("Times New Roman", Font.BOLD, 40);
		JLabel dirLabel = new JLabel("Directions");
		dirLabel.setFont(dirTitleFont);
		JPanel dirLabelPanel = new JPanel();
		dirLabelPanel.add(dirLabel);
		add(dirLabelPanel, BorderLayout.NORTH);
		
		JButton DButton = new JButton("HOME");
		JPanel DButtonP = new JPanel();
		DButtonP.setLayout(new FlowLayout());
		DButtonP.setBackground(Color.CYAN);
		HomeButtonListener hbl = new HomeButtonListener(dirGP, clDir);
		DButton.addActionListener(hbl);
		DButtonP.add(DButton);
		add(DButtonP, BorderLayout.WEST);
		
		JPanel pan = new JPanel();
		pan.setLayout(null);
		JTextArea ta = new JTextArea("Welcome to Cell MANIA!!!\n\nThis is an amazing place to test your bio skills, as well as enhance them! Get your memory fired up to rock and roll!\n\nAs you first click on the \"START\" button, a page will ask you to choose a level. There are two levels: Although the questions are similar (but not the same!) in both levels, Level1 is multiple choice type and level2 is text field entry. After you make a decision, three images of cell related terms will pop up in a short period of time. Prompty after that, you will be shown a variety of terms in a panel. Recall those images, identify the terms if you can, and click them IN THE RIGHT ORDER. If you get it right, you will enjoy playing another round! If not, you will be asked 3 questions (in the second level) to improve your understanding in the subject! If you've chosen Level1, you only need to answer them once. Get them all right and you can get right back in the memory game! You will also get points for your winnings! 3 points if you get each memorization question right, and one point each for a correct question!\n\nIf you get 10 questions wrong, your game will end and you can check your scores on your score board.\n\nThat's all I have to say! Go HOME and start your playing!", 75, 10);
		ta.setBounds(200, 100, 800, 800);
		ta.setEditable(false);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		pan.add(ta);
		add(pan, BorderLayout.CENTER);
	}
	
}

/* This class is used as the common Home Button Listener from all the 
 * panels. If the home button is clicked, it switches to the Home Panel.
 * */
class HomeButtonListener implements ActionListener
{
	private CellMania5Panels passedGPobj; // object of CellMania5Panels is set equal to this variable, for navigation using CardLayout (Home Page in this case)
	private CardLayout passedhBLHButtonLis; // object of the card layout (used in CellManiaPanels class, also used for navigation using CardLayout
	
	/* It recieves the CellMania5Panels and CardLayout objects from
	 * the panels its calling from, and they are saved to the private
	 * field variables here.
	 * */
	public HomeButtonListener(CellMania5Panels GPobj, CardLayout hblCardLayout)
	{
		passedGPobj = GPobj;
		passedhBLHButtonLis = hblCardLayout;
	}
	
	/* Similar to the other listeners, if the user clicks on the home button,
	 * the Home Panel will be shown on the Frame.
	 * */
	public void actionPerformed(ActionEvent evt)
	{
		String homeClickedDir = evt.getActionCommand();
		if (homeClickedDir.equals("HOME"))
		{
			passedhBLHButtonLis.show(passedGPobj, "First page");
		}
	}
}

// This class consists of the panel that is the Score Board. It prints 
// the scores of the players.
class ScoreBoard extends JPanel
{
	private CellMania5Panels scoBoGP; // object of CellMania5Panels is set equal to this variable, for navigation using CardLayout
	private CardLayout clScoBo; // object of the card layout (used in CellManiaPanels class, also used for navigation using CardLayout
	private ClickTerms ctScoBo; // Object of ClickTerms class
	private int scoreSB; // Score variable got from ClickTerms class
	private JTextArea taSB; // TextArea in which the scores are printed
	private String scoresFileNameSB; // Name of Scores file
	private Scanner scoreReaderSB; // Reads the Scores.txt file

	/* There is a home button and text area here, which displays the scores of the players. 
	 * It also recieves CellMania5Panels object and CardLayout for switching 
	 * between the card layout.
	 * */
	public ScoreBoard(CellMania5Panels sbGP, CardLayout clSB, ClickTerms ctSB)
	{
		setLayout(new BorderLayout() );
		scoBoGP = sbGP;
		clScoBo = clSB;
		ctScoBo = ctSB;
		scoreSB = 0;
		scoresFileNameSB = new String("Scores.txt");
		scoreReaderSB = null;
		setBackground(Color.GREEN);
		
		Font SBTitleFont = new Font("Times New Roman", Font.BOLD, 30);
		JLabel SBLabel = new JLabel("Score Board");
		SBLabel.setFont(SBTitleFont);
		JPanel SBLabelPanel = new JPanel();
		SBLabelPanel.add(SBLabel);
		add(SBLabelPanel, BorderLayout.NORTH);
		
		JButton hSBButton = new JButton("HOME");
		JPanel SBButtonP = new JPanel();
		SBButtonP.setLayout(new FlowLayout());
		SBButtonP.setBackground(Color.CYAN);
		HomeButtonListener hbl = new HomeButtonListener(scoBoGP, clScoBo);
		hSBButton.addActionListener(hbl);
		SBButtonP.add(hSBButton);
		add(SBButtonP, BorderLayout.WEST);
		
		JPanel SBpanforText = new JPanel();
		SBpanforText.setLayout(null);
		SBpanforText.setBackground(Color.BLUE);
		taSB = new JTextArea("", 75, 10);
		taSB.setBounds(200, 100, 800, 800);
		SBpanforText.add(taSB);
		add(SBpanforText, BorderLayout.CENTER);
	}
	
	/* This method reads, using a try-catch block, the Scores.txt file and stores each line 
	 * in a variable called readScore. This is then made to appear in the 
	 * JTextArea of that class.
	 * */
	public void readScores()
	{
		File scoFile = new File(scoresFileNameSB);
		try
		{
			scoreReaderSB = new Scanner (scoFile);
		}
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", scoresFileNameSB);
			System.exit(1);
		}
		String readScore = new String("");
		while (scoreReaderSB.hasNext() )
		{
			readScore = scoreReaderSB.nextLine();
			taSB.append(readScore + "\n" );
		}
	}
	
	/* The paintComponent method simply erases all the text from the TextField
	 * and calls the method readScores() again. All the text is erased so 
	 * that all the data doesn't repeat itself while getting appended in the
	 * Text Area.
	 * */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		taSB.setText("");
		readScores();
	}
}

// This class is where the user will be able to see flashing images of
// different terms. For now, it has two buttons, to lead to the questions
// panel and the ClickTerms panel (later these won't be there, they are 
// called by themselves). It also now has 3 images flashing in a 
// random order. After the images flash, they are taken to the Click terms
// page. Now there are more images to choose from (12).
class MemorizeStation extends JPanel implements ActionListener
{
	private CellMania5Panels MemStaGP; // object of CellMania5Panels is set equal to this variable, for navigation using CardLayout
	private CardLayout clMemSt; // object of the card layout (used in CellManiaPanels class, also used for navigation using CardLayout
	private String memPicFileName1; // This string has the name of the image stored from the array (that contains image names)
	private Image memImage1; // This contains the picture being read at the time (which is added to the Images array)
	
	private String[] memImageNames; // The array has the images' names stored.
	private Image[] memImages; // This array has the Images stored.
	
	private Timer countDownImage; // A timer object that calls actionPerformed every 1000 milliseconds
	private int time; // This is set to 5 in the constructor, and is reduced by one each time paintComponent is called.
	// (cont from previous line). So that this page stays for only 5 seconds.
	
	private int randImIndex; // This is the random index that is stored from the generateRandImage() method
	private String[] theThreeTrio; // This string array stores the names of the 3 images chosen. MADE STATIC AND PUBLIC. NOT WORKING
	private String imageNamesFileName; // Stores the name of the file that contains names of images
	private Scanner imgNameFile; // Object is used to read ImageNames.txt file
	
	private int storer; // the variable that denotes the index of the theThreeTrio
	// in which the selected image names are stored
	private int counter; // Variable used to count how many images are displayed
	// and used to print the image # being displayed
	
	/* 2 buttons are created, the question and the click away, and
	 * each have action listeners. When clicked, each are taken
	 * to the respective panels. The method readFromImageNames is called,
	 * where the file with the image names stored is read. Then the
	 * readMemorizeImage method is called, where the images are read.
	 * And a timer object countDownImage is instantiated and the timer is started as well.
	 * */
	public MemorizeStation(CellMania5Panels MSGP, CardLayout clMS)
	{
		memImageNames = new String[12];
		imageNamesFileName = new String("ImageNames.txt");
		memImages = new Image[12];
		randImIndex = 0;
		imgNameFile = null;
		theThreeTrio = new String[3];
		counter = 1;
		readFromImageNames();
		
		storer = 0;
		
		time = 5;
		MemStaGP = MSGP;
		clMemSt = clMS;
		setBackground(Color.CYAN);
		
		readMemorizeImage();
		
		countDownImage = new Timer(1000, this);
		countDownImage.start();
	}
	
	/* Every one second the actionPerformed method is called by the Timer,
	 * paintComponent is called.
	 * */
	public void actionPerformed(ActionEvent evt)
	{
		repaint();
	}
	
	/* This method reads the file that contains the images' names, using a 
	 * try-catch block. Each line is read from the file using a while loop
	 * and the content in each line is stored in the memImageNames array.
	 * */
	public void readFromImageNames()
	{
		File imgNFile = new File(imageNamesFileName);
		try
		{
			imgNameFile = new Scanner (imgNFile);
		}
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", imageNamesFileName);
			System.exit(1);
		}
		String imgNameFromFile = new String("");
		int imgNcount = 0;
		while (imgNameFile.hasNext() )
		{
			imgNameFromFile = imgNameFile.next();
			memImageNames[imgNcount] = imgNameFromFile;
			imgNcount ++;
		}
	}
	
	/* This method tries to read all the images by using a for loop.
	 * Images are attempted to be read using the names from the image names
	 * array, using a try-catch block. And the images are then stored
	 * into the Images memImages array.
	 * */
	public void readMemorizeImage()
	{
		for (int i = 0; i < memImageNames.length; i ++)
		{
			memPicFileName1 = new String(memImageNames[i]);
			File memPicFile1 = new File(memPicFileName1);
			try
			{
				memImage1 = ImageIO.read(memPicFile1);
			}
			catch(IOException e)
			{
				System.err.println("\n\n\n" + memPicFileName1 + " can't be found.\n\n\n");
				e.printStackTrace();
			}
			memImages[i] = memImage1;
		}
	}
	
	/* Here simply a random integer from 0 to 11 is printed and saved to
	 * randImIndex.
	 * */
	public void generateRandImage()
	{
		randImIndex = (int)(Math.random()*12);
	}
	
	/* First the method generateRandImage() is called. And if there are
	 * less than 5 seconds left, the image from the Images array in the 
	 * randImIndex index is drawn. These three images are also saved 
	 * to another Images array called theThreeTrio. Or if there is 
	 * 0 seconds left, the time is set to five and the ClickTerms page
	 * is shown. Time is also decremented.
	 * */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Font imageNum = new Font("Times New Roman", Font.BOLD, 20);
		g.setFont(imageNum);
		generateRandImage();
		if (time > 0 && time < 4)
		{
			g.drawImage(memImages[randImIndex], 500, 300, 400, 400, this);
			g.drawString("Image " + counter, 1000, 500);
			theThreeTrio[storer] = memImageNames[randImIndex];
			counter ++;
			storer ++;
		}
		else if (time == 0)
		{
			time = 5;
			clMemSt.show(MemStaGP, "Click terms");
			storer = 0;
			counter = 1;
		}
		
		time --;
	}
	
	/* This is the getter method utilized by the ClickTerms class. It returns
	 * theThreeTrio array.
	 * */
	public String[] getTrio()
	{
		return theThreeTrio;
	}
	
	/* This method is also called by the ClickTerms class. It returns the 
	 * memImagesNames array.
	 * */
	public String[] getAllImageNamesArray()
	{
		return memImageNames;
	}
}

/* This class contain many buttons in which the user has to click on.
 * This will be done by remembering the images flashed on the previous 
 * panel. The user needs to click on the correct buttons in the correct order.
 * If the time runs out or if the wrong button in the wrong order is clicked,
 * we are taken to the Questions page.
 * */
class ClickTerms extends JPanel
{
	private CellMania5Panels clTeGP; // object of CellMania5Panels is set equal to this variable, for navigation using CardLayout
	private CardLayout clCliTer; // object of the card layout (used in CellManiaPanels class, also used for navigation using CardLayout
	private int cTSeconds; // Set to 5, and is decremented each time it enters paintComponent allwoing the page to stay fro 5 seconds
	private Timer cTTimer; // The timer object that calls actionPerformed metod of AfterClickingTermsTimer every 1000 milliseconds.
	private String buttonNamesFileName; // the name of the file that contains button names
	private Scanner buttonNamesFile; // the file that contains button names
	private String[] buttonNamesArray; // array that saves all the button names
	private MemorizeStation memStatCliTerms; // ClickTerms's object of MemorizeStation
	private String[] chosenTrio; // the array that consists of the names of
	private String[] allImagesFromMS; // Images' names array that is gotten from Memorize Station class
	private boolean clickedCorrect; // denotes whether the user clicked on the button correctly (expand)
	private int noClicked; // number of times the user clicked (the buttons)
	private int score; // Stores the score the user earns by clicking on the right terms
	private int setBackTo; // The time that the user sets to from the Slider object,
	// which is what the time is set back to after user runs out of time,
	// or if user exit.
	private SelectLevel slCliTer; // an object of SelectLevel class to recieve level
	private String levelCT; // stores the string returned by Select Level
	// which has the name of Questions page it should go to
	
	/* There is the HOME button, and the JLabel is set. Now there are also
	 * twelve buttons without ActionListeners. A timer object is started and
	 * its listener is instantiated.
	 * */
	public ClickTerms(CellMania5Panels stGP, CardLayout clCT, MemorizeStation msGet, SelectLevel slCT)
	{	
		setLayout(new BorderLayout() );
		clTeGP = stGP;
		clCliTer = clCT;
		setBackground(Color.YELLOW);
		
		memStatCliTerms = msGet;
		slCliTer = slCT;
		
		levelCT = new String("");
		buttonNamesFileName = new String("ButtonNames.txt");
		buttonNamesFile = null;
		chosenTrio = new String[3];
		buttonNamesArray = new String[12];
		allImagesFromMS = new String[12];
		clickedCorrect = false;
		noClicked = 0;
		score = 0;
		setBackTo = 20;
		
		readButtonNames();
		
		Font CTTitleFont = new Font("Times New Roman", Font.BOLD, 30);
		JLabel CTLabel = new JLabel("Click Terms");
		CTLabel.setFont(CTTitleFont);
		JPanel CTLabelPanel = new JPanel();
		CTLabelPanel.add(CTLabel);
		add(CTLabelPanel, BorderLayout.NORTH);
		
		JButton cTButton = new JButton("HOME");
		JPanel cTButtonP = new JPanel();
		cTButtonP.setLayout(new FlowLayout());
		cTButtonP.setBackground(Color.CYAN);
		CTButtonListener ctbl = new CTButtonListener();
		cTButton.addActionListener(ctbl);
		cTButtonP.add(cTButton);
		add(cTButtonP, BorderLayout.WEST);
		
		AfterClickingTermsTimer actt = new AfterClickingTermsTimer(this);
		cTSeconds = 20;
		cTTimer = new Timer(1000, actt);
		cTTimer.start();
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(6, 2) );
		
		JButton choice1 = new JButton(buttonNamesArray[0]);
		JButton choice2 = new JButton(buttonNamesArray[1]);
		JButton choice3 = new JButton(buttonNamesArray[2]);
		JButton choice4 = new JButton(buttonNamesArray[3]);
		JButton choice5 = new JButton(buttonNamesArray[4]);
		JButton choice6 = new JButton(buttonNamesArray[5]);
		JButton choice7 = new JButton(buttonNamesArray[6]);
		JButton choice8 = new JButton(buttonNamesArray[7]);
		JButton choice9 = new JButton(buttonNamesArray[8]);
		JButton choice10 = new JButton(buttonNamesArray[9]);
		JButton choice11 = new JButton(buttonNamesArray[10]);
		JButton choice12 = new JButton(buttonNamesArray[11]);

		buttonPanel.add(choice1);
		buttonPanel.add(choice2);
		buttonPanel.add(choice3);
		buttonPanel.add(choice4);
		buttonPanel.add(choice5);
		buttonPanel.add(choice6);
		buttonPanel.add(choice7);
		buttonPanel.add(choice8);
		buttonPanel.add(choice9);
		buttonPanel.add(choice10);
		buttonPanel.add(choice11);
		buttonPanel.add(choice12);

		choice1.addActionListener(ctbl);
		choice2.addActionListener(ctbl);
		choice3.addActionListener(ctbl);
		choice4.addActionListener(ctbl);
		choice5.addActionListener(ctbl);
		choice6.addActionListener(ctbl);
		choice7.addActionListener(ctbl);
		choice8.addActionListener(ctbl);
		choice9.addActionListener(ctbl);
		choice10.addActionListener(ctbl);
		choice11.addActionListener(ctbl);
		choice12.addActionListener(ctbl);
		
		add(buttonPanel, BorderLayout.CENTER);
	}
	
	/* A method that gets the string (which is the name of the Questions
	 * page it should go) from the Select Level class. 
	 * */
	public void recieveLevel()
	{
		levelCT = slCliTer.getLevel();
	}
	
	/* This method is the setter method that sets cTSeconds to the time
	 * the user has set in the slider. And the variable setBackTo is
	 * set to cTSeconds.
	 * */
	public void setTimeFromSlide(int gotTime)
	{
		cTSeconds = gotTime;
		setBackTo = cTSeconds;
	}
	
	/* Another setter method used to change the score value,
	 * called from Questions class.
	 * */
	public void setScore(int quesPoints)
	{
		score = score + quesPoints;
	}
	
	/* Setter method is used to set the score back to 0. It is called
	 * by the Questions class so that the score can get reset if the user
	 * presses home button from Questions page.
	 * */
	public void setBackToZero(int numToSetBackTo)
	{
		score = numToSetBackTo;
	}
	
	/* This is a getter method that return the score the player has
	 * earned throughout the game.
	 * */
	public int getScore()
	{
		return score;
	}
	
	/* This method reads the file ButtonNames.txt using a try-catch block.
	 * It also uses a while loop to store the names of the buttons in 
	 * a String array called buttonNamesArray.
	 * */
	public void readButtonNames()
	{
		File buttonNFile = new File(buttonNamesFileName);
		try
		{
			buttonNamesFile = new Scanner (buttonNFile);
		}
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", buttonNamesFileName);
			System.exit(1);
		}
		
		String buttonName = new String("");
		int buttonNCounter = 0;
		while (buttonNamesFile.hasNext() )
		{
			buttonName = buttonNamesFile.next();
			buttonNamesArray[buttonNCounter] = buttonName;
			buttonNCounter ++;
		}
	}
	
	/* The getTrio method from MemorizeStation is called to assign the 
	 * chosenTrio array to the theThreeTrio array from MemorizeStation.
	 * A variable names allImagesFromMS stores the allImageNamesArray
	 * from MemorizeStation. The changeChosenTrio method is also called.
	 * After the time is over for this page, the Questions page is shown.
	 * Every time, cTSeconds is decrements which count downs for the
	 * time.
	 * */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		recieveLevel();
		chosenTrio = memStatCliTerms.getTrio();
		allImagesFromMS = memStatCliTerms.getAllImageNamesArray();
		changeChosenTrio();

		if (cTSeconds == 0)
		{
			clCliTer.show(clTeGP, levelCT);
			cTSeconds = setBackTo;
		} 
		cTSeconds --;
	}
	
	/* This method does the important work of changing the Image Names from
	 * the chosenTrio array to the actual names (from the ButtonNames.txt).
	 * It compares each term in the chosenTrio array to all the Image names
	 * from allImagesFromMS array. Since all the names in the buttonNamesArray
	 * and the allImagesFromMS array correspond, the Image Name is then replaced
	 * with the corresponding term from buttonNamesArray. This happens
	 * for all the chosen terms.
	 * */
	public void changeChosenTrio()
	{
		String nominated = new String("");
		
		for (int checkToChange = 0; checkToChange < 3; checkToChange ++ )
		{
			for (int compareWithEach = 0; compareWithEach < 12; compareWithEach ++)
			{
				if (chosenTrio[checkToChange].equals(allImagesFromMS[compareWithEach] ) )
				{
					nominated = buttonNamesArray[compareWithEach];
					chosenTrio[checkToChange] = nominated;
				}
			}
		}
	}
	
	/* This class is a separate Button Listener for the buttons
	 * belonging to the ClickTerms panel. This is created because the timer
	 * cTSeconds needs to be reset when the player exits the ClickTerms panel.
	 * */
	class CTButtonListener implements ActionListener
	{
		/* When the String com (or button clicked) is equal to "HOME",
		 * the Enter Name page is shown and the time is reset so that the next time 
		 * the player comes to the ClickTerms panel, they still get their time.
		 * If the player clicks on the correct terms on the correct term,
		 * they are taken back to the Memorize Station page. If the button 
		 * clicked (depending on when the user clicked it) is equal to the
		 * noClicked of the chosenTrio, it is counted correct. For example,
		 * if the first image clicked was Lysosome, noClicked will be 0.
		 * If that is equal to noClicked of chosenTrio, the answer is
		 * correct. If they click correctly for the third time, the variables
		 * are reset, score is incremented, and Memorize Station is shown.
		 * If the answer is wrong, variables are reset and the according Questions
		 * page is shown. For this, the levelCT varibale, which has the
		 * name of the panel it wants to switch to, is used.
		 * */
		public void actionPerformed(ActionEvent evt)
		{
			recieveLevel();
			
			String com = evt.getActionCommand();
			if (com.equals("HOME") )
			{
				clCliTer.show(clTeGP, "Enter Name");
				cTSeconds = setBackTo;
			}

			else if(com.equalsIgnoreCase(chosenTrio[noClicked]) )
			{
				if (noClicked == 2)
				{
					clCliTer.show(clTeGP, "Memorize station");
					noClicked = -1;
					cTSeconds = setBackTo;
					score = score + 3; 
				}
				clickedCorrect = true;
				noClicked ++;
			}
			else if(!( com.equalsIgnoreCase(chosenTrio[noClicked] ) ) )
			{
				noClicked = 0;
				cTSeconds = setBackTo;
				clCliTer.show(clTeGP, levelCT);
			}
		}
		
	}
}

/* This class now has random questions that is printed, and a Text Field
 * in which the user needs to type an answer. If the answers are right,
 * the user is returned to the Memorize station page. If they are wrong,
 * are taken to the TypeThrice page.
 * */
class Questions extends JPanel
{
	private CellMania5Panels quesGP; // object of CellMania5Panels is set equal to this variable, for navigation using CardLayout
	private CardLayout clQues; // object of the card layout (used in CellManiaPanels class, also used for navigation using CardLayout
	private ClickTerms ctQues; // object of ClickTerms class
	
	private String questionsFileName; // Name of the file that contains Questions
	private String quesImagesFileName; // Name of file that contains corresponding images for questions
	private String ansFileName; // Name of file that contains answers for questions
	
	private String[] questionsArray; // All the questions are stored in this array
	private String[] quesImgArray; // All the names of images are stored in this array
	private Image[] quesImagesArray; // All the Images are stored in this array
	private String[] ansArray; // All the answers are stored in this array
	
	private Scanner quesReader; // Scanner object to read questions from file
	private Scanner quesImgNameReader; // Scanner object to read image names from file
	private Scanner answersReader; // Scanner object to read answers from file
	private Image quesImage; // Image object to read images

	private int randQuesIndex; // Stores random index which determines random question
	private String chosenQuestion; // The chosen question is stored in the variable
	private TAPanel tapan; // Object of TApanel
	private ImagePanel imgpan; // Obejct of Imagepanel
	private String needToTypeThrice; // The word which the user needs to type thrice (in a diff class)
	
	private int singleScore; // the point the user learns by answering a Question right
	private int quesCount; // the count of the questions answered
	private int totalWrongQuestions; // the number of the questions the user answered wrong
	private boolean[] askedQuestions; // the boolean that has 42 slots. When a random number
	// is generated, that index in this array is made true. It is used to make
	// sure that a question doesn't repeat.
	
	/* All the names of the files are instantiated, and variables are instantiated.
	 * Home button is drawn, and there is a HOME button. There are smaller
	 * panels inside htat has the question, corresponding Image, and a text field.
	 * */
	public Questions(CellMania5Panels qGP, CardLayout clQ, ClickTerms ctQ)
	{
		setLayout(new BorderLayout() );
		quesGP = qGP;
		clQues = clQ;
		ctQues = ctQ;
		setBackground(Color.RED);
		questionsFileName = new String("Questions.txt");
		quesImagesFileName = new String("QuestionsImages.txt");
		ansFileName = new String("Answers.txt");
		needToTypeThrice = new String("");
		singleScore = 0;
		quesCount = 0;
		totalWrongQuestions = 0;
		
		questionsArray = new String[42];
		quesImgArray = new String[42];
		quesImagesArray = new Image[42];
		ansArray = new String[42];
		askedQuestions = new boolean[42];
		
		quesReader = null;
		quesImgNameReader = null;
		answersReader = null;
		quesImage = null;
	
		randQuesIndex = 0;
		chosenQuestion = new String("");
		
		Font QTitleFont = new Font("Times New Roman", Font.BOLD, 30);
		JLabel QLabel = new JLabel("Questions");
		QLabel.setFont(QTitleFont);
		JPanel QLabelPanel = new JPanel();
		QLabelPanel.add(QLabel);
		add(QLabelPanel, BorderLayout.NORTH);
		
		JButton QButton = new JButton("HOME");
		JPanel QButtonP = new JPanel();
		QButtonP.setLayout(new FlowLayout());
		QButtonP.setBackground(Color.CYAN);
		HomeButtonListenerQue hbl = new HomeButtonListenerQue();
		QButton.addActionListener(hbl);
		QButtonP.add(QButton);
		add(QButtonP, BorderLayout.WEST);
		
		JPanel centerP = new JPanel();
		centerP.setLayout(new GridLayout(1, 2) );
		JPanel QPanels = new JPanel();
		QPanels.setLayout(new GridLayout(2, 1) );
		
		tapan = new TAPanel();
		QPanels.add(tapan);
		imgpan = new ImagePanel();
		QPanels.add(imgpan);
		
		JPanel options = new JPanel();
		options.setLayout(new GridLayout (4, 1) );
		options.setBackground(Color.YELLOW);
		AnswersChecker ac = new AnswersChecker();
		JTextField answersF = new JTextField("");
		answersF.addActionListener(ac);
		options.add(answersF);
		
		centerP.add(QPanels);
		centerP.add(options);
		add(centerP);
		
		readQuestionsFile();
		readQuesImagesNamesFile();
		readQuesImages();
		readAnswersFile();
	}
	
	/* This class acts as a HomeButton listener. If the home button is pressed,
	 * they are taken to the Enter Name page, where they enter their name.
	 * The boolean array is also instantiated again to reset the values.
	 * */
	class HomeButtonListenerQue implements ActionListener
	{
		/* If user clicks HOME, they are taken to Enter name page.
		 * */
		public void actionPerformed(ActionEvent evt)
		{
			String homeClQue = evt.getActionCommand();
			if (homeClQue.equals("HOME"));
			{
				askedQuestions = new boolean[42];
				clQues.show(quesGP, "Enter Name");
			}
		}
	}
	
	/* This is a setter method that sets the quesCount variable to zero.
	 * */
	public void setQuesCont(int zero)
	{
		quesCount = zero;
	}
	
	// A getter method that return the quesCount variable to another class
	public int getQuesCount()
	{
		return quesCount;
	}
	
	/* This getter method returns the word which the user needs to type
	 * thrice. It is called from the TypeThrice class.
	 * */
	public String getNeedToType()
	{
		return needToTypeThrice;
	}
	
	/* This method tries to read the answers file using a try-catch block.
	 * Using a while loop, the answer is stored into the ansArray array.
	 * This array now has the corresponding answers to the questions
	 * from the Questions array.
	 * */
	public void readAnswersFile()
	{
		File answerFile = new File(ansFileName);
		try
		{
			answersReader = new Scanner (answerFile);
		}
		
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", ansFileName);
			System.exit(1);
		}
		String ans = new String("");
		int ansCounter = 0;
		while (answersReader.hasNext() )
		{
			ans = answersReader.nextLine();
			ansArray[ansCounter] = ans;
			ansCounter ++;
		}
	}
	
	/* This method reads the Questions file using a try-catch block. It also
	 * uses a while loop, and the questionis storeed in an array called
	 * questionsArray array.
	 * */
	public void readQuestionsFile()
	{
		File questionFile = new File(questionsFileName);
		try
		{
			quesReader = new Scanner (questionFile);
		}
		
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", questionsFileName);
			System.exit(1);
		}
		String question = new String("");
		int questionCounter = 0;
		while (quesReader.hasNext() )
		{
			question = quesReader.nextLine();
			questionsArray[questionCounter] = question;
			questionCounter ++;
		}
	}
	
	/* This method reads from the file that has the Image Names stored, using
	 * the try-catch block. In the while loop, each line that has the image
	 * names is stored in the quesImgArray.
	 * */
	public void readQuesImagesNamesFile()
	{
		File quesimgnamefild = new File(quesImagesFileName);
		try
		{
			quesImgNameReader = new Scanner (quesimgnamefild);
		}
		
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", quesImagesFileName);
			System.exit(1);
		}
		String questIMG = new String("");
		int questionIMGCounter = 0;
		while (quesImgNameReader.hasNext() )
		{
			questIMG = quesImgNameReader.nextLine();
			quesImgArray[questionIMGCounter] = questIMG;
			questionIMGCounter ++;
		}
	}
	
	/* This method reads all the images, the names of which are stored in
	 * the quesImgArray. There is a for loop that goes through each name
	 * in that array, and a try-catch block is used to read that image.
	 * The image is then stored into the quesImagesArray.
	 * */
	public void readQuesImages()
	{
		for (int quesimgImgcount = 0; quesimgImgcount < 42; quesimgImgcount ++)
		{
			File quesimgforquestions = new File(quesImgArray[quesimgImgcount]);
			try
			{
				quesImage = ImageIO.read(quesimgforquestions);
			}
			
			catch (IOException e)
			{
				System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", quesImgArray[quesimgImgcount]);
				System.exit(1);
			}
			quesImagesArray[quesimgImgcount] = quesImage;
		}
	}
	
	/* This method simply uses the Math class to produce a random Index,
	 * which determines the random question being chosen for the user to answer.
	 * */
	public void generateRandomQuestion()
	{
		randQuesIndex = (int)(Math.random()*42);
	}
	
	/* This paintComponent method calls the generateRandomQuestion to get 
	 * the random index, and the question in the random index of the array is
	 * assigned to a variable called chosenQuestion. In the askedQuestions
	 * array, there are preset false values in all the fields. So when
	 * a question is selected, the question's index in the questionsArray
	 * is made true. We check if the random question generated the next
	 * time has a true value in the questionsArray. If it does, another
	 * random number is generated. If not, that index in the questionArray
	 * is made true. Then, then painComponent methods of TAPanel and ImagePanel classes are called.
	 * */
	public void paintComponent(Graphics g)
	{
		boolean isTrueForCheckAll = false;
		generateRandomQuestion();
		chosenQuestion = questionsArray[randQuesIndex];
		if (askedQuestions[randQuesIndex] == true)
		{
			for (int checkIfAllFalse = 0; checkIfAllFalse < 42; checkIfAllFalse ++)
			{
				if (askedQuestions[checkIfAllFalse])
				{
					isTrueForCheckAll = true;
				}
				else if (!(askedQuestions[checkIfAllFalse] ))
				{
					isTrueForCheckAll = false;
					checkIfAllFalse = 999; 
				}
			}
			if (isTrueForCheckAll == true)
			{
				askedQuestions = new boolean[42];
				clQues.show(quesGP, "Enter Name");
			}
			repaint();
		}
		else
		{
			askedQuestions[randQuesIndex] = true;
		}

		tapan.repaint();
		imgpan.repaint();
	}
	
	// The class prints the chosenQuestion in a JTextArea.
	class TAPanel extends JPanel
	{
		private JTextArea questionArea; // The JTextArea that has the question.
		
		/* Sets a Green background colour, and instantiates and adds a JTextArea
		 * object.
		 * */
		public TAPanel()
		{
			setBackground(Color.GREEN);
			questionArea = new JTextArea("What is the name of this?", 30, 10);
			add(questionArea);
		}
		
		/* This method simply prints the chosen question into the JTextArea.
		 * */
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			questionArea.setText(chosenQuestion);
		}
	}
	
	// This class prints the image that corresponds to the question.
	class ImagePanel extends JPanel
	{
		// The constructor simply changes the background color to red.
		public ImagePanel()
		{
			setBackground(Color.RED);
		}
		
		/* The constructor prints the Image in the quesImagesArray with
		 * the same randQuesIndex. This works because the image names correspond
		 * to the questions, so the same Index can be used.
		 * */
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawString("Image", 150, 175);
			g.drawImage(quesImagesArray[randQuesIndex], 150, 175, 100, 100, this);
		}
	}
	
	/* This class checks if the answer entered in the text field slot
	 * of this panel is correct. Depending on that, it directs the user
	 * to different panels.
	 * */
	class AnswersChecker implements ActionListener
	{
		/* This method checks if the answer the user entered is correct. Since the 
		 * answers correspond to the questions, the same randQuesIndex
		 * variable can be used. If the answer entered is same as the
		 * string in the randQuesIndex of ansArray, they are right.
		 * else they are wrong. If they are right, singleScore is set to 1,
		 * and it is passed into the setScore method of Click Terms. Then,
		 * the user is directed to MemorizeStation panel. However if the user
		 * is wrong, the needToTypeThrice variable is set to the actual
		 * answer (from the ansArray), and the Type Thrice panel is shown.
		 * If the totoalWrongQuestions is 11 (10 questions are wrong),
		 * the game ends and the user is automatically taken to the Enter
		 * Name panel.
		 * */
		public void actionPerformed(ActionEvent evt)
		{
			String ansEntered = evt.getActionCommand();
			if (totalWrongQuestions == 11)
			{
				clQues.show(quesGP, "Enter Name");
			}
			else if (ansEntered.equalsIgnoreCase(ansArray[randQuesIndex] ) )
			{
				singleScore = 1;
				ctQues.setScore(singleScore);
				if (quesCount < 2)
					repaint();
				else if (quesCount == 2)
				{
					quesCount = -1;
					clQues.show(quesGP, "Memorize station");
				}
				singleScore = 0;
				quesCount ++;
			}
			else if (!(ansEntered.equalsIgnoreCase(ansArray[randQuesIndex] ) ) )
			{
				needToTypeThrice = ansArray[randQuesIndex];
				clQues.show(quesGP, "Type Thrice");
				quesCount ++;
				totalWrongQuestions ++;
			}
		}
	}
}

/* This class is designed for players wanting to take Level 1. It contains
 * similar questions as Level 2, but they are multiple choice.
 * */
class QuestionsMultipleChoice extends JPanel
{
	private String MCquesName; // The name of the multiple choice questions file
	private Scanner msQRead; // Scanner object to read Questions file
	private int randMCQ; // Stores a random number from 0 to 40 to determine a random question
	private String[] MCquesArr; // Stores all the questions from the questions file
	private JTextArea quesMC; // A TextArea that shows the users the random question
	private String ansMCfileName; // The name of the multiple choice answers file
	private Scanner mcAnsReader; // Scanner object to read answers file
	private String[] mcAnswers; // The array that contains all the corresponding answers
	private String[] mcOptions; // The array that contains all the corresponding choices
	private JPanel optionsqmc; // A JPanel for the choices to a question
	private JRadioButton op1; // JRadioButton 1 as choice 1 for a selected question
	private JRadioButton op2; // JRadioButton 2 as choice 1 for a selected question
	private JRadioButton op3; // JRadioButton 3 as choice 1 for a selected question
	private TAQMCPanel tapanqcm; // A Panel that holds the questions TextField
	private CellMania5Panels cmQuesMulChoCMO; // An object of CellMania5Panels to switch between cards
	private CardLayout clQuesMulChoCL; // Object of CardLayout to get access to all cards
	private ClickTerms ctQuesMulCho; // Object of ClickTerms 
	private int totalQuesMulWrong; // Stores the number of questions the user got wrong
	private boolean[] askedQuestionsMC; // the boolean that has 42 slots. When a random number
	// is generated, that index in this array is made true. It is used to make
	// sure that a question doesn't repeat.
	
	/* All variables including file names are instantiated, as well as arrays.
	 * A Border Layout is sey. In the middle with a grid layout, the questions
	 * are on the left side and the choices are on the right side. JButtons
	 * are instantiated and given their action listeners too. The methods
	 * readMultipleChoiceQues and readMultipleChoiceAns are called to
	 * read the question and answers file.
	 * */
	public QuestionsMultipleChoice(CellMania5Panels cmQMCObj, CardLayout cmQMC, ClickTerms clQMC)
	{
		setBackground(Color.BLUE);
		setLayout(new BorderLayout() );
		cmQuesMulChoCMO = cmQMCObj;
		clQuesMulChoCL = cmQMC;
		ctQuesMulCho = clQMC;
		
		totalQuesMulWrong = 0;
		randMCQ = 0;
		MCquesName = new String("MultipleChoiceQuestions.txt");
		msQRead = null;
		MCquesArr = new String[41];
		ansMCfileName = new String("MultipleChoiceAnswers.txt");
		mcAnswers = new String[41];
		mcOptions = new String[41];
		askedQuestionsMC = new boolean[41];
		
		Font qmcFont = new Font("Times New Roman", Font.BOLD, 30);
		JLabel QmlLabel = new JLabel("Questions");
		QmlLabel.setFont(qmcFont);
		JPanel QmchLabelPanel = new JPanel();
		QmchLabelPanel.add(QmlLabel);
		add(QmchLabelPanel, BorderLayout.NORTH);
		
		MutipleChoiceListener mcl = new MutipleChoiceListener();
		JButton QcButton = new JButton("HOME");
		QcButton.addActionListener(mcl);
		JPanel QcButtonP = new JPanel();
		QcButtonP.setLayout(new FlowLayout());
		QcButtonP.setBackground(Color.CYAN);
		
		QcButtonP.add(QcButton);
		add(QcButtonP, BorderLayout.WEST);
		
		JPanel centerPQM = new JPanel();
		centerPQM.setLayout(new GridLayout(1, 2) );
		JPanel QcPanels = new JPanel();
		QcPanels.setLayout(new GridLayout(1, 1) );
	
		tapanqcm = new TAQMCPanel();
		QcPanels.add(tapanqcm);

		optionsqmc = new JPanel();
		optionsqmc.setLayout(new GridLayout (3, 1) );
		op1 = new JRadioButton("");
		op1.addActionListener(mcl);
		op2 = new JRadioButton("");
		op2.addActionListener(mcl);
		op3 = new JRadioButton("");
		op3.addActionListener(mcl);
		optionsqmc.setBackground(Color.YELLOW);
		optionsqmc.add(op1);
		optionsqmc.add(op2);
		optionsqmc.add(op3);
		
		centerPQM.add(QcPanels);
		centerPQM.add(optionsqmc);
		add(centerPQM);
		
		readMultipleChoiceQues();
		readMultipleChoiceAns();
	}
	
	/* This method uses a try-catch block to read the Questions file. In a 
	 * while loop, each line (which has a question) is stored in the questions
	 * array until the file reaches the end.
	 * */
	public void readMultipleChoiceQues()
	{
		File quesnamefilemc = new File(MCquesName);
		try
		{
			msQRead = new Scanner (quesnamefilemc);
		}
		
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", MCquesName);
			System.exit(1);
		}
		int mcqcont = 0;
		while (msQRead.hasNext() )
		{
			String qLine = msQRead.nextLine();
			MCquesArr[mcqcont] = qLine;
			mcqcont ++;
		}
	}
	
	/* This method reads the answers file and uses a try-catch block. Using the substring method,
	 * the answer is separated and stored into the Answers array and 
	 * the three options are stored in the options array.
	 * */
	public void readMultipleChoiceAns()
	{
		File ansnamefilemc = new File(ansMCfileName);
		try
		{
			mcAnsReader = new Scanner (ansnamefilemc);
		}
		
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", ansMCfileName);
			System.exit(1);
		}
		int mcacont = 0;
		while (mcAnsReader.hasNext() )
		{
			String aLine = mcAnsReader.nextLine();
			mcAnswers[mcacont] = aLine.substring(0, aLine.indexOf(" ") );
			mcOptions[mcacont] = aLine.substring(aLine.indexOf(" ") + 1);
			mcacont ++;
		}
	}
	
	/* This method generates a random number from 0 - 40, and stores it into
	 * the variable randMCQ.
	 * */
	public void generateRandIndMC()
	{
		randMCQ = (int)(Math.random() * 41);
	}
	
	/* Firs the generateRandIndMC mehtod is called to produce a random number.
	 * In the askedQuestions array, there are preset false values in all the fields. So when
	 * a question is selected, the question's index in the questionsArray
	 * is made true. We check if the random question generated the next
	 * time has a true value in the questionsArray. If it does, another
	 * random number is generated. If not, that index in the questionArray
	 * is made true. Using the random index, the buttons are set to have
	 * the names of the options from the options array. We also change
	 * the button names are also set depending on the content on the 
	 * options array.
	 * */
	public void paintComponent(Graphics g)
	{
		boolean isTrueForCheckAllMCQ = false;
		super.paintComponent(g);
		g.drawString("hello", 300, 400);
		generateRandIndMC();
		if (askedQuestionsMC[randMCQ] == true)
		{
			for (int checkIfAllFalseMCQ = 0; checkIfAllFalseMCQ < 41; checkIfAllFalseMCQ ++)
			{
				if (askedQuestionsMC[checkIfAllFalseMCQ])
				{
					isTrueForCheckAllMCQ = true;
				}
				else if (!(askedQuestionsMC[checkIfAllFalseMCQ] ))
				{
					isTrueForCheckAllMCQ = false;
					checkIfAllFalseMCQ = 999; 
				}
			}
			if (isTrueForCheckAllMCQ == true)
			{
				askedQuestionsMC = new boolean[41];
				clQuesMulChoCL.show(cmQuesMulChoCMO, "Enter Name");
			}
			repaint();
		}
		else
			askedQuestionsMC[randMCQ] = true;
		tapanqcm.repaint();
		String options = mcOptions[randMCQ];
		ButtonGroup opMC = new ButtonGroup();
		op1.setText(options.substring(0, options.indexOf(",") ) );
		opMC.add(op1);
		optionsqmc.add(op1);
		op2.setText(options.substring(options.indexOf(",") + 1, options.indexOf(".") ) );
		opMC.add(op2);
		optionsqmc.add(op2);
		op3.setText(options.substring(options.indexOf(".") + 1 ) );
		opMC.add(op3);
		optionsqmc.add(op3);
	}
	
	/* This getter method returns the term the user needs to type if
	 * they got the answer wrong.
	 * */
	public String getNeedToType()
	{
		return mcAnswers[randMCQ];
	}
	
	/* This class is a listener for the answer the user responds with.
	 * It checks if the user is right and directs them to the corresponding page.
	 * */
	class MutipleChoiceListener implements ActionListener
	{
		/* If the user clicks on the correct radio button, the user is
		 * taken back to the Memorize Station. If not, they are taken
		 * to the Type Thrice page. If the user answers 10 questions wrong,
		 * they are automatically taken to the enter name page, as 
		 * the game has finished. Also, getting a question right can increase
		 * a point, which is why a setter method in click terms is called.
		 * The boolean array is also reset when the Home button is clicked
		 * so that all the values are reset to false.
		 * */
		public void actionPerformed(ActionEvent evt)
		{
			String MCClicked = evt.getActionCommand();
			if (MCClicked.equalsIgnoreCase(mcAnswers[randMCQ] ) )
			{
				ctQuesMulCho.setScore(1);
				clQuesMulChoCL.show(cmQuesMulChoCMO, "Memorize station");
			}
			else if(MCClicked.equalsIgnoreCase("HOME") )
			{
				askedQuestionsMC = new boolean[41];
				clQuesMulChoCL.show(cmQuesMulChoCMO, "Enter Name");
			} 
			else if(!(MCClicked.equalsIgnoreCase(mcAnswers[randMCQ] ) ) )
			{
				totalQuesMulWrong ++;
				clQuesMulChoCL.show(cmQuesMulChoCMO, "Type Thrice");
				if (totalQuesMulWrong == 11)
				{
					clQuesMulChoCL.show(cmQuesMulChoCMO, "Enter Name");
				}
			}
		}
	}
	
	/* A class that holds a JTextField that has the questions.
	 * */
	class TAQMCPanel extends JPanel
	{
		// It has an explty JTextArea, and it is added.
		public TAQMCPanel()
		{
			setBackground(Color.RED);
			quesMC = new JTextArea("", 30, 10);
			add(quesMC);
		}
		
		/* The chosen question is made to display in this text field.
		 * */
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			String questionMC = MCquesArr[randMCQ];
			quesMC.setText(questionMC);
		}
	}
}

// This class contains the Glossary panel.
class Glossary extends JPanel
{
	private CellMania5Panels glossGP; // object of CellMania5Panels is set equal to this variable, for navigation using CardLayout
	private CardLayout clGloss; // object of the card layout (used in CellManiaPanels class, also used for navigation using CardLayout
	
	/* There is a home button and text area here, which attempts to explain
	 * the instructions of the game. It also recieves CellMania5Panels object
	 * and CardLayout for switching between the card layout.
	 * */
	public Glossary(CellMania5Panels gGP, CardLayout clG)
	{
		glossGP = gGP;
		clGloss = clG;
		
		setLayout(new BorderLayout() );
		setBackground(Color.YELLOW);
		
		Font glossTitleFont = new Font("Times New Roman", Font.BOLD, 40);
		JLabel glossLabel = new JLabel("Directions");
		glossLabel.setFont(glossTitleFont);
		JPanel glossLabelPanel = new JPanel();
		glossLabelPanel.add(glossLabel);
		add(glossLabelPanel, BorderLayout.NORTH);
		
		
		JPanel glossPan = new JPanel();
		glossPan.setLayout(null);
		JTextArea taGlossary = new JTextArea("Cell Parts:\nNucleolus: Locatin the the cell where ribosomes"
			+ "are produced.\nRibosome: Assembles proteins from its monomers using information" + 
			"DNA.\nGolgi Apparatus: Packages and sends proteins out of the" 
			+ "cell.\nLysosome: Contains enzymes that break old biomolecules" + 
			"and organelles so that parts can be reused or disposed.\nVacuole:"
			+ "Much larger in plant cells; stores water and other materials for the cell."
			+ "\nChloroplast: Captures energy from the sun and creates glucose."
			+ "\nMitochondria: Breaks down glucose to release energy (ATP)\n"
			+ "Nucleus: Contains cell's genetic material; controls cell's activities.\n"
			+ "Cytoskeleton: network of protein filaments that help determine " + 
			"cell shape for animals that DONT have cell walls.\nCell Membrane: "
			+ "Surrounds cell; protect cell and allows them to transport materials "
			+ "in and out of the cell.\nCell Wall: Cellulose layer outside " 
			+ "of the membrane to give cell shape; for plants only.\n"
			+ "Cytoplasm: Jelly-like substance, made of 80% water. Provides "
			+ "structure for cell.\n\nDNA:\nFour bases: Adenine, Thymine, " 
			+ "Cytosine, Guanine. We have 46 chromosomes!\n\nBiomolecules: "
			+ "Carbohydrates:\nMonomer: Monosaccharide\nPolymer: Polysaccharide"
			+ "\nLipids:\nMonomer: Subunits\nPolymer:Fatty Acids\nNuclic Acids: " 
			+ "Monomer: Nucleotides\nPolymer: Nucleic Acid\nProtein:\n" 
			+ "Monomer: AminoAcid\nPolymer: Protein\n\nOther Info:\n" 
			+ "Hypertonic: Water flows into the cell and it swells.\nHypotonic:"
			+ " Water flows out of the cell and it shrinks.\nIsotonic: "
			+ "There is a net flow of water, cell remains the same.", 75, 10);
		taGlossary.setBounds(200, 100, 800, 800);
		glossPan.add(taGlossary);
		add(glossPan, BorderLayout.CENTER);
		
		JButton GButton = new JButton("HOME");
		JPanel GButtonP = new JPanel();
		GButtonP.setLayout(new FlowLayout());
		GButtonP.setBackground(Color.CYAN);
		HomeButtonListener hbl = new HomeButtonListener(glossGP, clGloss);
		GButton.addActionListener(hbl);
		GButtonP.add(GButton);
		add(GButtonP, BorderLayout.WEST);
	}
}

// This class contains the panel that has the Settings options. The time
// provided in the Click Terms panel changes according to the values
// of the slider.
class Settings extends JPanel
{
	private CellMania5Panels settingsGP; // object of CellMania5Panels is set equal to this variable, for navigation using CardLayout
	private CardLayout setGloss; // object of the card layout (used in CellManiaPanels class, also used for navigation using CardLayout
	private int time; // Stores the time the user adjusts the slider to.
	private JSlider timeSlider; // Slider that lets user adjust the time constraints.
	private ClickTerms settCT; // ClickTerms object so that the class has access to some getter setter methods.
	
	/* There is a home button and JSlider here, which is now able 
	 * to change the time limits of the game. It also recieves CellMania5Panels object
	 * and CardLayout for switching between the card layout.
	 * */
	public Settings(CellMania5Panels sGP, CardLayout clS, ClickTerms sCT)
	{
		settingsGP = sGP;
		setGloss = clS;
		time = 20;
		settCT = sCT;
		
		setLayout(new BorderLayout() );
		setBackground(Color.BLACK);
		
		Font setTitleFont = new Font("Times New Roman", Font.BOLD, 40);
		JLabel settingLabel = new JLabel("Settings");
		settingLabel.setFont(setTitleFont);
		JPanel settingLabelPanel = new JPanel();
		settingLabelPanel.add(settingLabel);
		add(settingLabelPanel, BorderLayout.NORTH);
		
		
		JPanel setPan = new JPanel();
		setPan.setLayout(new BorderLayout() );
		JTextField setTime = new JTextField("Adjust the slider!", 75);
		setTime.setBounds(200, 100, 800, 800);
		setPan.add(setTime, BorderLayout.NORTH);
		TimeSliderListener tsl = new TimeSliderListener();
		timeSlider = new JSlider(10, 30, 20);
		timeSlider.setMajorTickSpacing(1);
		timeSlider.setPaintTicks(true);
		timeSlider.setLabelTable( timeSlider.createStandardLabels(1) );
		timeSlider.setPaintLabels(true);
		timeSlider.setOrientation(JSlider.HORIZONTAL);
		timeSlider.addChangeListener(tsl);
		setPan.add(timeSlider, BorderLayout.CENTER);
		add(setPan, BorderLayout.CENTER);
		
		JButton sButton = new JButton("HOME");
		JPanel sButtonP = new JPanel();
		sButton.setLayout(new FlowLayout());
		sButton.setBackground(Color.CYAN);
		HomeButtonListener hbl = new HomeButtonListener(settingsGP, setGloss);
		sButton.addActionListener(hbl);
		sButtonP.add(sButton);
		add(sButtonP, BorderLayout.WEST);
	}
	
	/* This is the listener class of the slider. It sets the time in the
	 * ClickTerms class depending on the slider's values.
	 * */
	class TimeSliderListener implements ChangeListener
	{
		/* Firstly, this method gets the gets the value of the slider
		 * that the user adjusts to. Then, it sets that value to the
		 * ClickTerms class using the setTimeFromSlider method.
		 * */
		public void stateChanged(ChangeEvent evt)
		{
			time = timeSlider.getValue();
			settCT.setTimeFromSlide(time);
		}
	}
}

/* This class makes up the panel that makes the users type their missed
 * word (from the Questions class) thrice.
 * */
class TypeThrice extends JPanel
{
	private String typeGoal; // the word that needs to be typed thrice, passed in from Question class
	private CellMania5Panels cmpTyTh; // Object of CellMania5Panels
	private CardLayout clTyTh; // object of CardLayout from CellMania5Panels
	private Questions quesTT; // object of Questions class
	private int noOfRepeat; // number of times the user has tryped in the word
	private JTextField practice; // the Text Field that the user types the word on
	private int quesConttt; // stores how many questions have already been answered from "Questions" panel
	private SelectLevel slTyTh; // Object of SelectLevel
	private String levelTT; // stores the level of the user
	private QuestionsMultipleChoice qmcTypThr; // Object of QuestionsMultipleChoice
	private Font whatToTypeFont; // The font of the line that informs the reader on what word to type
	
	/* A JTextField is instantiated and added, and all the variables are
	 * initialised as well. A listener is added for the TextField.
	 * */
	public TypeThrice(CellMania5Panels cmpTT, CardLayout clTT, Questions qsTT, SelectLevel slTT, QuestionsMultipleChoice qmcTT)
	{
		setLayout(new BorderLayout() );
		JPanel titleFrTT = new JPanel();
		Font titleTT = new Font("Times New Roman", Font.BOLD, 40);
		JLabel typThrLabel = new JLabel("Type Thrice!");
		typThrLabel.setFont(titleTT);
		titleFrTT.add(typThrLabel);
		add(titleFrTT, BorderLayout.NORTH);
		
		whatToTypeFont = new Font("Times New Roman", Font.BOLD, 30);
		quesConttt = 0;
		noOfRepeat = 0;
		cmpTyTh = cmpTT;
		clTyTh = clTT;
		quesTT = qsTT;
		slTyTh = slTT;
		qmcTypThr = qmcTT;
		levelTT = new String("");
		setBackground(Color.GREEN);
		ThriceListener tl = new ThriceListener();
		practice = new JTextField("Type the word thrice");
		practice.addActionListener(tl);
		
		add(practice, BorderLayout.SOUTH);
	}
	
	/* This method uses the Questions class object to get the word
	 * that needs to be typed thrice and saves it into the variable typeGoal.
	 * It does the same action as well with the Questions Multiple Choice class.
	 * */
	public void receiveTypeThreeWord()
	{
		levelTT = slTyTh.getLevel();
		if (levelTT.equalsIgnoreCase("Questions") )
			typeGoal = quesTT.getNeedToType();
		else if(levelTT.equalsIgnoreCase("Questions Multiple Choice") )
			typeGoal = qmcTypThr.getNeedToType();
	}
	
	/* This method simply sets the text field blank.
	 * */
	public void setFieldBlank()
	{
		practice.setText(" ");
	}
	
	/* It calls the receiveTypeThreeWord and prints the word which the user
	 * needs to type thrice.
	 * */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(whatToTypeFont);
		receiveTypeThreeWord();
		g.drawString("The word you need to type thrice is: " + typeGoal, 500, 500);
	}
	
	/* This class acts as a listener for the Text Field. For the Questions class
	 * a quesConttt is received to keep track of how many
	 * questions were already answered. If the word the user types in
	 * is equal to the typeGoal, then the text field prints "AGAIN" for another
	 * 2 times. If the user types the word correctly for 3 times,
	 * then the Memorize Panel panel is shown.
	 * */
	class ThriceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if (levelTT.equals("Questions") )
			{
				quesConttt = quesTT.getQuesCount() - 1;
				String goalTypedWord = evt.getActionCommand();
				if (goalTypedWord.equalsIgnoreCase(typeGoal) )
				{
					if (noOfRepeat == 2)
					{
						if (quesConttt < 2)
							clTyTh.show(cmpTyTh, "Questions");
						else
						{
							clTyTh.show(cmpTyTh, "Memorize station");
							quesTT.setQuesCont(0);
						}
						noOfRepeat = 0;
						practice.setText("Type the word thrice");
					}
					else
					{
						noOfRepeat ++;
						practice.setText("AGAIN!");
						repaint();
					}
				}
			}
			else if (levelTT.equals("Questions Multiple Choice") )
			{
				String goalTypedWord = evt.getActionCommand();
				if (goalTypedWord.equalsIgnoreCase(typeGoal) )
				{
					if (noOfRepeat == 2)
					{
						clTyTh.show(cmpTyTh, "Memorize station");
						practice.setText("Type the word thrice");
						noOfRepeat = 0;
					}
					else
					{
						noOfRepeat ++;
						practice.setText("AGAIN!");
						repaint();
					}
				}
			}
		}
	}
}

/* This class makes up the Enter Name panel, which tells the user to
 * enter the name of the user, to store the score information in the
 * Scores file.
 * */
class EnterName extends JPanel
{
	private CellMania5Panels cmEnNa; // Object of CellMania5Panels
	private CardLayout clEnNa; // CardLayout object from CellMania5Panels
	private ClickTerms ctEnNa; // ClickTerms object to access scores
	private int scoreEN; // The score the user has earned white playing the game, passed from ClickTerms
	private PrintWriter scoreWriter; // PrintWriter object to write to Scores file
	private String scoreFileName; // Name of the Score file
	
	/* Initalises variables, and a TextField is created, and an action
	 * listener is added to it as well.
	* */
	public EnterName(CellMania5Panels cmEN, CardLayout clEN, ClickTerms ctEN)
	{
		setLayout(new BorderLayout() );
		JLabel enLab = new JLabel("Enter Your Name!");
		Font enLabFont = new Font("Times New Roman", Font.BOLD, 40);
		enLab.setFont(enLabFont);
		JPanel enLabPan = new JPanel();
		enLabPan.add(enLab);
		add(enLabPan, BorderLayout.NORTH);
		
		cmEnNa = cmEN;
		clEnNa = clEN;
		ctEnNa = ctEN;
		scoreFileName = new String("Scores.txt");
		NameListener nl = new NameListener();
		
		JPanel nfPan = new JPanel();
		nfPan.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 100));
		JTextField nameField = new JTextField("Enter Name");
		nameField.addActionListener(nl);
		setBackground(Color.RED);
		nfPan.add(nameField);
		nfPan.setBackground(Color.RED);
		JTextField feedback = new JTextField("Good Job! Try to soar up next time!!!");
		nfPan.add(feedback);
		add(nfPan, BorderLayout.CENTER);
	}
	
	/* This method does the work of opening the Scores file so that the
	 * scores can be written. It is opened in a way that allows appending
	 * of informaiton to the file.
	 * */
	public void writeScoresFile() 
	{
		File scoreWr = new File(scoreFileName);
		try
		{
			scoreWriter = new PrintWriter (new FileWriter(scoreWr, true));
		}
		catch (IOException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s\n\n\n", scoreFileName);
			System.exit(1);
		}
	}
	
	/* This acts as the listener class for the name text field. The score
	 * from the ClickTerms class is reveived and stored into the variable
	 * scoreEN. Then the writeScoresFile method is called. The name
	 * along with the score is appened into the file. The file is closed,
	 * and the player is taken to the first page.
	 * */
	class NameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String nameTyped = evt.getActionCommand();
			scoreEN = ctEnNa.getScore();
			writeScoresFile();
			scoreWriter.print(nameTyped + " " + scoreEN + "\n");
			scoreWriter.close();
			ctEnNa.setBackToZero(0);
			clEnNa.show(cmEnNa, "First page");
		}
	}
}

/* This class allows the user to select their level of difficulty for the game.
 * It only has 2 radio buttons, Level1 and Level2 to let the users choose.
 * */
class SelectLevel extends JPanel
{
	private CellMania5Panels cmSelLev; // Object of CellMania5Panels to have access to cards
	private CardLayout clSelLev; // Object of CardLayout to switch between cards
	private String level; // This will contain the name of the Questions page
	// the user has to go to once it's time
	
	/* There are two button objects instantiated, and added to the panel.
	 * */
	public SelectLevel(CellMania5Panels cmSL, CardLayout clSL)
	{
		setLayout(new BorderLayout() );
		JLabel slLab = new JLabel("Select A Level!");
		Font slLabFont = new Font("Times New Roman", Font.BOLD, 40);
		slLab.setFont(slLabFont);
		JPanel slLabPan = new JPanel();
		slLabPan.add(slLab);
		add(slLabPan, BorderLayout.NORTH);
		
		level = new String("");
		cmSelLev = cmSL;
		clSelLev = clSL;
		LevelListener ll = new LevelListener();
		JPanel buttonsLevel = new JPanel();
		JButton Lev1 = new JButton("Level 1");
		JButton Lev2 = new JButton("Level 2");
		Lev1.addActionListener(ll);
		Lev2.addActionListener(ll);
		buttonsLevel.setLayout(new GridLayout(1, 2) );
		buttonsLevel.add(Lev1);
		buttonsLevel.add(Lev2);
		add(buttonsLevel);
	}
	
	/* This class is a listener for the levle the user clicks. varibale
	 * level is changed according to this.
	 * */
	class LevelListener implements ActionListener
	{
		/* If the user clicks Level 2, then level is "Questions". But if
		 * the user clicks Level 1, level is "Questions Multiple Choice".
		 * Either way, they are taken to Memorize station after that.
		 * */
		public void actionPerformed(ActionEvent evt)
		{
			String levelcli = evt.getActionCommand();
			if (levelcli.equals("Level 2") )
			{
				clSelLev.show(cmSelLev, "Memorize station");
				level = "Questions";
			}
			else if(levelcli.equals("Level 1") )
			{
				clSelLev.show(cmSelLev, "Memorize station");
				level = "Questions Multiple Choice";
			}
		}
	}
	
	/* This is a getter method used by other classes to get information
	 * on the Questions page they need to go to. It returns the variabel level.
	 * */
	public String getLevel()
	{
		return level;
	}
}

