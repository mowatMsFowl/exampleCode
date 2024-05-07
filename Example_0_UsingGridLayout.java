import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

/***************************************************************
* Paint program starting class with the main method
***************************************************************/
public class Example_0_UsingGridLayout
{
   public static void main (String[] args)
   {
      GUIFrame frame = new GUIFrame();
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}

@SuppressWarnings("serial")
class GUIFrame extends JFrame implements ActionListener
{
// FIELDS for the object (global variables)
   static final long serialVersionUID = 2;
   private static final int WIDTH=600, HEIGHT=600;
//Font font = new Font("Chiller", Font.PLAIN,90);
   int numClicks=0; 

// Constructor for PaintFrame object 
   public GUIFrame()
   {
      super ("Cogswell");
   
      JPanel pane = (JPanel) getContentPane(); 
      pane.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20), new LineBorder(Color.BLACK)));
      pane.setLayout(new GridLayout(2,1));
   
   // Make all final buttons, textboxes, ...
      JButton btn1 = new JButton("1");
      JButton btn2 = new JButton("2");
      JButton btn3 = new JButton("3");
      JButton btn4 = new JButton("4");
      JButton btn5 = new JButton("5");
      JTextArea display = new JTextArea();
   
   //bottomPanel 
      JPanel bottomPanel = new JPanel();
      bottomPanel.setLayout(new GridLayout(1,3)); // 1 rows as we need with 3 columns
      bottomPanel.add(btn1);
      bottomPanel.add(btn2);
      bottomPanel.add(btn3);
   
   
   
   //topPanel
      JPanel topPanel = new JPanel();
      topPanel.setLayout(new GridLayout(2,1)); // 2 rows as we need with 1 columns
      JPanel displayPanel = new JPanel();
      displayPanel.add(display);
      JPanel specialFunctionPanel = new JPanel();
      specialFunctionPanel.add(btn4);
      topPanel.add(displayPanel);
      topPanel.add(specialFunctionPanel);
   
   
   
   //contentPane
      pane.add(topPanel);
      pane.add(bottomPanel);
      setSize(WIDTH, HEIGHT);
      setVisible(true); 
   
   }


/***************** ActionListener interface methods *****************/ 
   public void actionPerformed(ActionEvent e) 
   {
      numClicks++;
      System.out.println("Button Clicked " + numClicks + " times");
   } 
}
