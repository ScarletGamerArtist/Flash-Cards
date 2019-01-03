package QuizCardProject;
/*
 * Alanna Botscharow
 * 1/19
 * 
 * 
 *With this program we use a file object to represent the file. a file reader will do the
 *actual reading and a buffered reader will make reading more efficient.
 *the reading will happen by reading lines in a while loop, ending the loop when the 
 *results of a read line null. This is the most common style for reading data.
 *(Pretty much anything that's not serialized object) reading the stuff in the while loop
 *(Actually a while loop test), terminating when there's nothing left to read.
 */

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class QuizCardPlayer {
	//Setup of class fields.
		private JTextArea display;
		private JTextArea answer;
		private ArrayList<QuizCard> cardList;
		private QuizCard currentCard;
		private int currentCardIndex;
		private JFrame frame;
		private JButton nextButton;
		private boolean isShowAnswer;
		
		
	public static void main(String[] args) {
		QuizCardPlayer reader = new QuizCardPlayer(); //creates a player object
		reader.go();//builds GUI

	}
	
	public void go() {
		//build GUI
		frame = new JFrame("Flash Card Player");
		JPanel mainPanel = new JPanel(); 
		Font bigFont = new Font("sanserif",Font.BOLD,24); 
		
		display = new JTextArea(10,20);
		display.setFont(bigFont);
		display.setLineWrap(true);
		display.setEditable(false);
		
		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		
		nextButton = new JButton("Show Question");
		mainPanel.add(qScroller);
		mainPanel.add(nextButton);
		//nextButton.addActionListener(new NextCardListener());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
		//loadMenuItem.addActionListener(new OpenMenuListener());
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		
		mainPanel.setBackground(Color.lightGray) ;
	
		
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(640,500);
		frame.setVisible(true);
		
		
	}

}
