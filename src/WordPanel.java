
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
		
		private int counter = 0;
		private boolean[] availableWords;
		private Score score;

		//find the first available word in "words"
		public synchronized int useWord(){
			//find the position of the first available WordRecord
			while (availableWords[counter]==true){
				counter++;
			}
			//set this value to true (represents occupied)
			availableWords[counter] = true;
			return counter;
		}
		
		public void paintComponent(Graphics g) {
			int width = getWidth();
			int height = getHeight();
			g.clearRect(0,0,width,height);
			g.setColor(Color.red);
			g.fillRect(0,maxY-10,width,height);

			g.setColor(Color.black);
			g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		//draw the words
		//animation must be added 
			for (int i=0;i<noWords;i++){	    	
				//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
				g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
			}
		   
		}
		
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work? probs not, what do?
			noWords = words.length;
			done=false;
			this.maxY=maxY;
			this.score = score;
			availableWords = new boolean[words.length];
			for (int i = 0; i < words.length; i++){
				availableWords[i] = false;		
			}
		}
		
		public void run() {
			//add in code to animate this

			//get first available "WordRecord"
			int usedWord = useWord();
			
			//this keeps looping until the program reaches the max words
			//or till the max amount of words are reached
			while (words[usedWord].dropped()==false){
				
				//exits loop if the exit button was pressed
				if (words[usedWord].getWord().equals("")){
					break;
				}
				
				//drops the word
				words[usedWord].drop(10);

				try {
					
//                                  //sleeps for the duration of the word's speed then paints it
					Thread.sleep(words[usedWord].getSpeed());
					repaint(); 
					
				} catch (InterruptedException ex) {
					Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				//resets the the word and adds to the score if the word has reached the red zone
				if (words[usedWord].dropped()==true){
					
					//exits if exit button was pressed
					if (words[usedWord].getWord().equals("")){
					break;
				}
					
					words[usedWord].resetWord();
					score.missedWord();
					repaint();
			}
                            
		}

		}

	}


