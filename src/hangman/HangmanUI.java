/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

/**
 *
 * @author Slim74562
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HangmanUI extends JFrame implements ActionListener, KeyListener
{
    
    final int FRAME_WIDTH = 500;
    final int FRAME_HEIGHT = 500;
    static String[] words;
    JTextField letter = new JTextField(5);
    static String randomWord;
    static int randomNumber ;
    static Random random = new Random();
    static String correctLetters = "";
    String letterGuessed = "";
    JLabel lettersUsed = new JLabel("");
    static int[] numberUsed;
    static char[] letterArray;
    static int zero = 0;
    
    public HangmanUI()
    {
        super("Spelling Word Hangman");
       
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        JLabel title = new JLabel("Guess a letter");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        letter.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton accept = new JButton("Accept");        
        
        JPanel pnlGuess = new JPanel();
        JPanel pnlList = new JPanel();
        String[] arrayFirstWords;
        String[] arraySecondWords;
        
        
        
        arrayFirstWords = new String[words.length/2];
        arraySecondWords = new String[words.length/2];
        
        for (int wordCounter = 1; wordCounter < words.length; wordCounter += 1)
        {
            int remainder = wordCounter % 2;
            if (remainder == 0)
            {
                arrayFirstWords[wordCounter] = words[wordCounter];
            }
            else if (remainder == 1)
            {
                arraySecondWords[wordCounter] = words[wordCounter];
            }
            System.out.println(words[wordCounter - 1]);            
        }
        
        JTable wordList;
        
        String[][] rowWords;
        if (arrayFirstWords.length > arraySecondWords.length)
        {
            wordList = new JTable(arrayFirstWords.length,2);
            rowWords = new String[2][arrayFirstWords.length];
        }
        else 
        {
            wordList = new JTable(arraySecondWords.length, 2);
            rowWords = new String[2][arraySecondWords.length];
        }
                
        pnlGuess.add(title);
        pnlGuess.add(letter);
        pnlGuess.add(accept);
        pnlGuess.add(lettersUsed);
                
        pnlGuess.setLayout(new FlowLayout());
        pnlList.add(wordList);
        wordList.setAlignmentX(CENTER_ALIGNMENT);
        add(pnlList, BorderLayout.NORTH);
        add(pnlGuess, BorderLayout.SOUTH);

        accept.addActionListener(this);
        letter.addKeyListener(this);
        letter.hasFocus();
        
        
    }
           
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        DatabaseRead.getWords();
        numberUsed = new int[words.length];
        HangmanUI frame = new HangmanUI();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setRandomWord();       
        
        System.out.println("random = " + randomWord); 
        
    }

    public static void setRandomNumber()
    {
        
        System.out.println(randomNumber);
        int max = words.length - 1;
        final int min = 0;
        randomNumber = random.nextInt(max - min + 1) + min;        
        numberUsed[randomNumber] = randomNumber;
        
    }
    
    public static void setRandomWord()
    {
        setRandomNumber();
        if (randomNumber == 0 && zero != 1)
           {
               zero += 1;
           }
    else
    {
        while (randomNumber == numberUsed[randomNumber] && zero == 1)
        {
           setRandomNumber();
            
        }
    }
    numberUsed[randomNumber] = randomNumber;
        letterArray = new char[words[randomNumber].length()];
        randomWord = words[randomNumber];
        letterArray = randomWord.toCharArray();

    }
    public void action()
    {
       letterGuessed = letter.getText();
       letter.setText("");
       if (randomWord.contains(letterGuessed))
               {                
                                      
                   if (correctLetters.contains(letterGuessed))
                           {
                               System.out.println("Letter Used");
                           }
                   else
                   {
                       System.out.println("Correct!");
                       
                       
                       for(int indexCount = 0; indexCount < randomWord.length(); 
                               indexCount += 1)
                       {
                           if (letterArray[indexCount] == letterGuessed.charAt(0))
                           {
                               correctLetters += letterGuessed;                                       ;
                           }
                       }
                       
                       
                   }
                   
                   System.out.println(letterGuessed);
                   
                   
               }
       else
       {
           System.out.println("Incorrect");           
       }
       if (correctLetters.length() >= randomWord.length())
                   {
                       
                       setRandomWord();
                       correctLetters = "";
                       System.out.println(randomWord);
                       
                   }
       lettersUsed.setText(correctLetters);
       letter.isFocusable();
       
       letter.hasFocus();
    }

    
    
    public void actionPerformed(ActionEvent e)
    {
        action();
    }
    @Override
    public void keyTyped(KeyEvent ke)
    {
        
        
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        
        
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {        
        action();
    }
    
}
