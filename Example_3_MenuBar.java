/**********************************************************************************
 * This example of how menus work was taken from 
 *                http://zerioh.tripod.com/ressources/menu.html
 * and modified to show how to pop up new JFrames by Cogswell                      
 **********************************************************************************/
 
  import javax.swing.*;
  import java.awt.event.*;

  public class Example_3_MenuBar
  {
      public static void main(String[] args)
      {
         BestMenuExample app = new BestMenuExample();
      }
  }


  class BestMenuExample extends JFrame implements ActionListener
  {
      static final long serialVersionUID = 2L;
      JMenuItem newItem, openItem, exitItem;
   
      // this is the constructor for the class
      public BestMenuExample()
      {
         super("Menu example program");
      
         // The MENU is created
         JMenu menuOne = new JMenu("MENU");
         menuOne.setMnemonic('M');
         
         // The ITEMS to put into the menu are created
         newItem = new JMenuItem("New");
         newItem.setMnemonic('N');
         menuOne.add(newItem);
         
         openItem = new JMenuItem("Secret");
         openItem.setMnemonic('S');
         menuOne.add(openItem);	
         
         exitItem = new JMenuItem("Exit");
         exitItem.setMnemonic('x');
         menuOne.add(exitItem);
      
         //adding action listener to menu items
         newItem.addActionListener(this);
         openItem.addActionListener(this);
         exitItem.addActionListener(this);
            	
         // adding menu bar to the JFrame with "MENU"    			
         JMenuBar bar = new JMenuBar();
         setJMenuBar(bar);
         bar.add(menuOne);
      
         getContentPane();
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(200, 200);
         setVisible(true);
      }
      
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == newItem)
            System.out.println("New is pressed");
         if (e.getSource() == openItem)   
         {
            System.out.println("Secret is pressed");
            ANewJFrame howItWorks = new ANewJFrame();
         }
         if (e.getSource() == exitItem)   
         {
            System.out.println("Exit is pressed");
            System.exit(0);
         }
      }
   }
   
   class ANewJFrame extends JFrame
   {
      static final long serialVersionUID = 2L;
      public ANewJFrame()
      {
         JScrollPane jScrollPane1 = new JScrollPane();
         JTextPane jTextPane1 = new JTextPane();
      
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setTitle("How the Game Works!");
         jTextPane1.setBackground(new java.awt.Color(212, 208, 200));
         jTextPane1.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
         jTextPane1.setText("If you want to know how this program works, then read on!\n\nThis program is doing a simple binary to decimal conversion for you.\n\nEach card represents a place holder in binary,\n    i.e.:  card 1 is the 8's place\n           card 2 is the 4's place\n           card 3 is the 2's place\n           card 4 is the 1's place\n\nThe four card questions that you answer give the program the binary number equivalent for your decimal number.  For instance, if your mystery number is 12, the binary equivalent is 1100.  The 12 appears in card 1 and card 2, but not in cards 3 and 4.  Thus, you answer your questions as YES, YES, NO, NO which is converted to 1100.  One simple mathematical equation converts this to 12.\n\nTaDa...Your mystery number is 12!");
         jTextPane1.setPreferredSize(new java.awt.Dimension(600, 400));
         jScrollPane1.setViewportView(jTextPane1);
      
         getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
         setSize(200, 200);
         setLocationRelativeTo(null);
         setVisible(true);
      
         pack();
      }
      
   }