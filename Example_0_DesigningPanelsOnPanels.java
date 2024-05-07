import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

public class Example_0_DesigningPanelsOnPanels
{
   private static final long serialVersionUID = 1L;
   public static void main(String[] args)
   {
      SomeCoolThing frame = new SomeCoolThing();
   }
}

class SomeCoolThing extends JFrame
{
   private static final long serialVersionUID = 1L;
   JLabel plantPicLbl;
   JButton startBtn;
   JComboBox<String> choosePlantFamilyCmbox;
   JTextArea listOfPlantsTextArea;
   
   //constructor to build the GUI
   public SomeCoolThing()
   {
      super("Plant Information");
      Font consolasFont = new Font("Consolas", Font.PLAIN, 22);
      
      JPanel pane = (JPanel)this.getContentPane(); //set a reference for the content pane
      pane.setLayout(new BorderLayout());
      pane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      
      startBtn = new JButton("start");
      startBtn.setFont(consolasFont);
      plantPicLbl = new JLabel("this will be my picture at some point");
      plantPicLbl.setFont(consolasFont);
      listOfPlantsTextArea = new JTextArea("bunch of stuff goes here");
      listOfPlantsTextArea.setFont(consolasFont);
      JTextField tf = new JTextField("text field");
      tf.setFont(consolasFont);
      
      // make a JPanel for 2 different panels at the top
      JPanel topPanel = new JPanel();
      topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); 
      // put a bunch of buttons on the top
      JPanel topPanel1 = new JPanel();
      topPanel1.setLayout(new GridLayout(1, 6, 25, 25));
      JButton a = new JButton("A");
      JButton b = new JButton("B");
      JButton c = new JButton("C");
      JButton d = new JButton("D");
      JButton e = new JButton("E");
      JButton f = new JButton("F");
      topPanel1.add(a); topPanel1.add(b); topPanel1.add(c); topPanel1.add(d);
      topPanel1.add(e); topPanel1.add(f);  
      //second panel for the top
      JPanel topPanel2 = new JPanel();
      topPanel2.setLayout(new GridLayout(1,4)); 
      // populate plant groups and create comboBox
      String[] flowerGroups = {"annuals", "perennials", "vegetables", "succulents", "vines"};     
      choosePlantFamilyCmbox = new JComboBox<>(flowerGroups);
      choosePlantFamilyCmbox.setBorder(BorderFactory.createTitledBorder("Pick plant type:"));
      topPanel2.add(choosePlantFamilyCmbox);
      topPanel2.add(new JLabel("  "));
      topPanel2.add(new JLabel("  "));
      // place both panels on topPanel
      topPanel.add(topPanel1);
      topPanel.add(topPanel2);
      
      
      pane.add(listOfPlantsTextArea, BorderLayout.CENTER);
      pane.add(topPanel, BorderLayout.NORTH);
      pane.add(startBtn, BorderLayout.SOUTH);
      pane.add(tf, BorderLayout.WEST);
      
      
      // configure the JFrame
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      //options: JFrame.DISPOSE_ON_CLOSE  
      //         JFrame.DO_NOTHING_ON_CLOSE
      //         JFrame.HIDE_ON_CLOSE
      pack(); //put this before setting the size
      setFont(consolasFont);
      setSize(1200, 820);
      setVisible(true);
   }
}