import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.JFrame;

///////////////////////////////// CLASS:
class Example_1_SimpleJFrameWithScrollPane
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
class ScrollPaneGUI extends JFrame 
{
   private static final long serialVersionUID = 4L;
     
   // FIELDS
   JList<String> listOfStrings;
   DefaultListModel<String> listModel; //This is used to change items in the listOfStrings


   // CONSTRUCTOR
   public ScrollPaneGUI()
   {
      super("ScrollPane");
            
      // contentPane is my JPanel without any defined layout
      JPanel pane = (JPanel) this.getContentPane();    
      pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // don't need this
   
      // define 3 components and align left 
      listModel = new DefaultListModel<String>();           //type: DefaultListModel<String>
      listOfStrings = new JList<String>(listModel);         //type: JList<String> 
      listOfStrings.setAlignmentX( Component.LEFT_ALIGNMENT );
      JScrollPane scrollPane = new JScrollPane(listOfStrings);  //<-- doesn't need to be a field
      scrollPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));  // don't need this
      
      // write information onto screen
      String[] data = {"Just a bunch","of lines","of jibberish","just used","as input","to populate","the listOfStrings", "area","within the","scrollPane"};
      populateListModel(data);
   
      pane.add(scrollPane);
      
      // configure JFrame and make it visible  
      setSize(300,200);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

   //////////////////////////////////////////////////////////////////////////////////
   // Populate the lines in the listModel with the contents of the array passed in
   //////////////////////////////////////////////////////////////////////////////////
   public void populateListModel(String[] data)
   {
      if(listModel != null)
         listModel.clear();
      
      for(String stuffToAdd : data)
            listModel.addElement(stuffToAdd);
   }

}