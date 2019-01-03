package QuizCardProject;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
/*
 * Alanna Botscharow
 * 1/19
 * Quiz card builder
 * 
 * This class will allow the user to create a set of quiz cards and use 
 * them for future use with the quiz card player class
 */



public class QuizCardBuilder {
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private JFrame frame;
	
	
	

	public static void main(String[] args) {
		QuizCardBuilder builder = new QuizCardBuilder();
		builder.go();
		
		
	}
	public void go() {
		/*
		 * build GUI
		 * this is all GUI code.
		 */
		frame = new JFrame("Quiz Card Builder");
		JPanel mainPanel = new JPanel(); 
		Font bigFont = new Font("sanserif",Font.BOLD,24);
		
		question = new JTextArea(6,20); 
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);
		
		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		

		answer = new JTextArea(6,20); 
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);
		
		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
		
		JButton nextButton = new JButton("Next Card");
		
		cardList = new ArrayList<QuizCard>();
		
		JLabel qLabel = new JLabel("Question: ");
		JLabel aLabel = new JLabel("Answer: ");
		
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		
		
		/*
		 * We make a menu bar and make a file menu, then put new
		 * and save items into the file menu. We then add the file menu to 
		 * the menu bar. then tell the frame to use this menu bar
		 */
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
		frame.getContentPane().add(BorderLayout.CENTER,  mainPanel);
		frame.setSize(500, 600);
		
		frame.setVisible(true);
		
	}//end of go
	
	public class NextCardListener implements ActionListener{

		public void actionPerformed(ActionEvent ev) {
			QuizCard card = new QuizCard(question.getText(),answer.getText());
			cardList.add(card);
			clearCard();

		}
		
	}//end cardlistener
	
	public class NewMenuListener implements ActionListener{

		public void actionPerformed(ActionEvent ev) {
			cardList.clear();
			clearCard(); 
			
		}
		
	}//End of New menu listener
	public class SaveMenuListener implements ActionListener{

		public void actionPerformed(ActionEvent ev) {
			QuizCard card = new QuizCard(question.getText(),answer.getText());
			cardList.add(card);
			/*
			 * this code brings up a file dialog box and waits on this line
			 * until the user chooses save from the dialog box. all the fil 
			 * dialog navigation and selecting a fil etc... is done for us
			 * by the JFileChooser class
			 */
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
			
		}
		
		
	}
	
	//clears out the card setting question and answer to blank strings
	private void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
		
	}//end of clear card
	
	
	/*
	 * This method does the actual file writing and is called by the save 
	 * menu listener event handler. the argument is the file object the 
	 * user is saving
	 */
	private void saveFile(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for (QuizCard card:cardList) {
				writer.write(card.getQuestion()+"/");
				writer.write(card.getAnswer()+"\n");
			}
			writer.close();
			
			//input output exception
		}catch (IOException ex) {
			System.out.println("Couldn't write the cardlist out");
			ex.printStackTrace();
			
		}
	}

}
