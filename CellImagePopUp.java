// Bharathi K.S.
// CellImagePopUp.java

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

public class CellImagePopUp implements ActionListener
{
	private MemorizeStation msTimer;
	private ClickTerms ctTimer;
	
	public CellImagePopUp(MemorizeStation msT)
	{
		msTimer = msT;
		//System.out.println("in constructor");
	}
	
	public void actionPerformed(ActionEvent evt)
	{

		System.out.println("in ac cipu");
		msTimer.repaint();
	}

}
