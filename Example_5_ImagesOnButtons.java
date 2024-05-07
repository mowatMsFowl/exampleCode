/***************************************************************************************
 *                                                                                     *
 * Author:  K. Cogswell                                                                *
 * Date:    April 24, 2014                                                             *
 * Name:                                                                               *
 * Updated:  May 8, 2022                                                                *
 *                                                                                     *
 **************************************************************************************/
 
   import javax.swing.*;      // swing is newer graphics package
   import java.awt.*;         // abstract windowing toolkit
   import java.awt.event.*;   // for the WindowEvent object
   import java.util.Random;
   
    public class Example_5_ImagesOnButtons
   {
       public static void main (String[] args) throws Exception 
      {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      
          // instantiate the game
         ThreeByThree buttonGrid = new ThreeByThree();  
         buttonGrid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
   }


  /*****************************************************************
   * This class builds the JFrame object of the minesweeper game.
   *****************************************************************/
    class ThreeByThree extends JFrame implements ActionListener
   {
      static final long serialVersionUID = 5L;
      // set up the constants used in the program
      public static final int COL = 3, ROW = 3 ;
      
      // set up the other global variables (what we call the fields of the object)
      public Color buttonColour;    
      
      JButton[][] buttons = new JButton[ROW][COL];    // the actual board 
      Image forest = Toolkit.getDefaultToolkit().getImage("forest4.jpg");
      Image grumpy = Toolkit.getDefaultToolkit().getImage("GrumpyCat.jpg");
      Image[] pictures = {forest, forest, grumpy, forest, forest, forest, forest, forest, forest};     // images to put on the buttons
   
    
     /*******************************************************************************************
      *  CONSTRUCTOR
		*******************************************************************************************/
      ThreeByThree()
      {
         // get the button colour and save it to restore the colour for resetting the game
         buttonColour = getBackground();
         
               
         // Create the Physical Board  
         setTitle("Images");
         JPanel pane = (JPanel) getContentPane();  // returns reference to a container object & JPanel is subclass---Container -> JComponent -> JPanel
         pane.setLayout(new GridLayout(ROW,COL));
         
         // Create and then add all buttons to JPanel.  Each button has an action listener.
         for (int row = 0 ; row < ROW ; row++)
           for (int column = 0 ; column < COL; column++)
            {
               buttons[row][column] = new JButton("");  
               buttons[row][column].addActionListener(this);
               pane.add(buttons[row][column]);
            }
            
      
         // Now display the board on the screen 
         pack();
         setLocationRelativeTo(null);   // top left corner to centre of screen.
         setSize(400,300);    // width is first argument, height is second 
         setVisible(true);
      }  //END OF CONSTRUCTOR
    
    
      
     /*******************************************************************************************
      *******************************************************************************************/
      public void actionPerformed (ActionEvent e)
      {
        for (int row = 0 ; row < ROW ; row++)
          for (int column = 0 ; column < COL; column++)
            if(e.getSource() == buttons[row][column])
              //  Put a picture onto the button that was pressed 
              //buttons[row][column].setIcon(new ImageIcon(pictures[row+column]));
              buttons[row][column].setIcon(new ImageIcon(pictures[row+column].getScaledInstance(130,120,Image.SCALE_SMOOTH)));
      }  // END OF ACTION PERFORMED
    
   }   // END OF THE CLASS