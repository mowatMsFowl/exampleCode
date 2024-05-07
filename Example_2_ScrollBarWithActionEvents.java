import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;

///////////////////////////////// CLASS:
class Example_2_ScrollBarWithActionEvents
{
   /*-----------------------------------------------------------------------------------*
   | Method: main                                                                    
   *-----------------------------------------------------------------------------------*/
   public static void main(String[] args)
   {
      ScrollPaneGUI frame = new ScrollPaneGUI();
   }
}


///////////////////////////////// CLASS:
class TheObject
{
   String strValue;
   int numericValue;
   
   public TheObject(String str, int number)
   {
      strValue = str;
      numericValue = number;
   }
}
   
   
     
///////////////////////////////// CLASS: 
class ScrollPaneGUI extends JFrame implements ActionListener, MouseListener
{
   private static final long serialVersionUID = 4L;
   
   ArrayList<TheObject> list = new ArrayList<TheObject>();
   
   // GUI components global accessibility
   JList<String> listOfStrings;
   DefaultListModel<String> listModel; //This is used to change items in the listOfStrings
   JButton stringsBtn;
   JButton numbersBtn;
   JLabel selectedItemLbl;

   // Constructor:
   public ScrollPaneGUI()
   {
      super("scrollPane = new JScrollPane(new JList<String>(listModel))");
      
      // Build the ArrayList (takes place of reading in from a file)
      // Instantiate objects and add to ArrayList
      list.add(new TheObject("A",1));
      list.add(new TheObject("B",2));
      list.add(new TheObject("C",3));
      list.add(new TheObject("D",4));
      list.add(new TheObject("E",5));
      list.add(new TheObject("F",6));
      list.add(new TheObject("G",7));   
   
   
      // define JScrollPanel with JList and DefaultListModel for top    
      listModel = new DefaultListModel<String>();
      listOfStrings = new JList<String>(listModel);
      listOfStrings.setAlignmentX( Component.LEFT_ALIGNMENT );
      listOfStrings.addMouseListener(this);
      JScrollPane scrollPane = new JScrollPane(listOfStrings);
      scrollPane.setBorder(BorderFactory.createTitledBorder("Make a selection:"));
         
      // define a JPanel for the 4 items for the bottom
      JPanel botPanel = new JPanel();   botPanel.setLayout(new GridLayout(2,2));
      stringsBtn = new JButton("Strings");
      stringsBtn.addActionListener(this);
      numbersBtn = new JButton("Numbers");
      numbersBtn.addActionListener(this);
      selectedItemLbl= new JLabel(" ");
      botPanel.add(stringsBtn);  botPanel.add(new JLabel("  Selected Item is: "));
      botPanel.add(numbersBtn);  botPanel.add(selectedItemLbl);
      
      
      // Place all components onto the JFrame - contentPane
      JPanel pane = (JPanel) this.getContentPane();  
      pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
      pane.add(scrollPane);
      pane.add(botPanel);
      
      // configure JFrame
      setSize(500,200);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

   //////////////////////////////////////// 
   // NOTE:  This uses the same method to populate with the 
   //        String field or the numeric field based off of
   //        the boolean value passed to this method.
   public void populateText(boolean isStrValue)
   {
      if(listModel != null)
         listModel.clear();
      
         // NOTE:  the ArrayList ID is "list"
      for(TheObject currentObject : list)
      {  
         if (isStrValue)
         {
            listModel.addElement(currentObject.strValue);
            System.out.println(currentObject.strValue);
         }
         else //populate the textArea with the numeric value
         {
            listModel.addElement(Integer.toString(currentObject.numericValue));
            System.out.println(currentObject.numericValue);
         }
      }   
   }

   // MouseListener and ActionListener interface methods:
   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource() == stringsBtn)
         populateText(true);
      else if(ae.getSource() == numbersBtn)
         populateText(false);
   }

   //Detect selection of question 
   public void mouseClicked(MouseEvent e) 
   {
      if (e.getClickCount() == 1) 
      {
         String selectedItem = listOfStrings.getSelectedValue();
         selectedItemLbl.setText("           "+selectedItem);
         int indexOfQuestionSelectedInList = listOfStrings.locationToIndex(e.getPoint());
         System.out.println("Clicked on listOfStrings at index: " +  indexOfQuestionSelectedInList);
      }
   }
   public void mouseEntered(MouseEvent e) 
   {  }  
   public void mouseExited(MouseEvent e) 
   {  }   
   public void mousePressed(MouseEvent e) 
   {  }   
   public void mouseReleased(MouseEvent e) 
   {  }            
//////////////////////////////////////


}