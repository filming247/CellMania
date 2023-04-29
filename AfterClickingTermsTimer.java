// Bharathi K.S.
// CellImagePopUp.java
/* Description: This class is the Timer listener class for ClickTerms class.
 * It calls the paintComponent method of ClickTerms every second.
 * */

import java.awt.Image;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AfterClickingTermsTimer implements ActionListener
{
	private ClickTerms ctTimerAfter;
	/* Conctructor sets ctT to ctTimerAfter.
	 * */
	public AfterClickingTermsTimer(ClickTerms ctT)
	{
		ctTimerAfter = ctT;
	}
	
	/* Calls paintComponent method of ClickTerms object passed in.
	 * */
	public void actionPerformed(ActionEvent evt)
	{
		ctTimerAfter.repaint();
	}

}
