import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
 
/****************************************************************
 * class with the main method and "instantiates" the 
 *       JFrame with all components.
 ****************************************************************/   
public class Example_4_WorkingComboBoxes
{ 
   static final long serialVersionUID = 2;
   
   public static void main(String args[]) throws Exception 
   { 
      AdventureGame frame = new AdventureGame();
      frame.beginGame();
   }// end main method
}// end class 
  
 
  
  
/****************************************************************
 * Graphics Adventure Game Class
 ****************************************************************/    
class AdventureGame extends JFrame implements ActionListener
{
   ////////////////////////// FIELDS ////////////////////////////
   static final long serialVersionUID = 2;
   
   // Picture area details
   BufferedImage buffer;   // instance variable for double buffering
   //final int WIDTH = 1328, HEIGHT = 872;   //my screen resolution:  1348x892
   final int WIDTH = 1000, HEIGHT = 600; 
   int picWidth = 910;
   Clip clip;
   // Set up fonts for the picture area
   Font chillerFont = new Font("Chiller", Font.PLAIN, 48); 
   Font smallArialFont = new Font("Arial", Font.BOLD, 20); 
   Font largeArialFont = new Font("Arial", Font.BOLD, 40); 
   
   // GUI components that will need to be accessed from many methods
   JPanel interactionPanel;
   String[] choices;
   JComboBox<String> choiceComboBox;
   JTextArea storyTextArea, inventoryTextArea;
   JPanel picturePanel;
   ///////////////////// END FIELDS ////////////////////////////
   
      
     
   /*************************************************************************
   *  AdventureWindow method                                                *
   *     Constructor                                                        *
   *     Builds the main window and all of the components that we see.      *
   *************************************************************************/ 
   public AdventureGame()
   {
      super ("Cogswell Adventure");
      int interactionPanelWidth = 400;
      picWidth = WIDTH-interactionPanelWidth;
      buffer = new BufferedImage(picWidth, HEIGHT, BufferedImage.TYPE_INT_RGB);
   
     /////////////////////picturePanel /////////////////////
     //Picture stuff foer the left panel 
      picturePanel = new JPanel();
      picturePanel.setDoubleBuffered(true);
     //////////////////////////////////////////////////////
   
     ////////////////////interactionPanel///////////////
     //interactive components for the rigth side
      interactionPanel = new JPanel();
      interactionPanel.setLayout(new BorderLayout(5,10));
      interactionPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
   
     //choice panel:combo box
      JPanel choicePanel = new JPanel();
   
     //titled border for combo box
      Border blackline = BorderFactory.createLineBorder(Color.black);
      TitledBorder choiceTitle = BorderFactory.createTitledBorder(blackline,"what do you want");
      choicePanel.setBorder(choiceTitle);
      String[]tempStrArray ={" ","Start the adventure game.","Quit."};
      choices = tempStrArray;
      choiceComboBox = new JComboBox<>(choices); 
      choiceComboBox.setFont(new Font("Serif",Font.PLAIN,20));
      choiceComboBox.setEditable(false);
      choiceComboBox.setPreferredSize(new Dimension(interactionPanelWidth-40,30));
      choiceComboBox.addActionListener(this);
      choicePanel.add(choiceComboBox);
     
     //green area with story
      storyTextArea = new JTextArea();
      storyTextArea.setBackground(new Color(119,247,200));
      storyTextArea.setMargin(new Insets(10,10,10,10));
      storyTextArea.setFont(new Font("Serif",Font.PLAIN,20));
      storyTextArea.setLineWrap(true);
      storyTextArea.setWrapStyleWord(true);
      storyTextArea.setEditable(false);
      storyTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createRaisedBevelBorder())); 
   
      interactionPanel.add(choicePanel,BorderLayout.PAGE_START);
      interactionPanel.add(storyTextArea,BorderLayout.CENTER);
     ///////////////////////////////////////////////////
   
     
     ////////////////////////////// contentPane //////////////////////
     // Set sizes and borders of both panels     
      picturePanel.setPreferredSize(new Dimension(WIDTH-interactionPanelWidth,HEIGHT));
      picturePanel.setMinimumSize(new Dimension(WIDTH-interactionPanelWidth,HEIGHT));
      picturePanel.setBorder(BorderFactory.createLineBorder(Color.black));
   
      interactionPanel.setPreferredSize(new Dimension(interactionPanelWidth,HEIGHT));
      interactionPanel.setMinimumSize(new Dimension(interactionPanelWidth,HEIGHT));    
      interactionPanel.setBorder(BorderFactory.createCompoundBorder(
                                    BorderFactory.createEmptyBorder(10,10,10,10),
                                    BorderFactory.createLoweredBevelBorder()));
   
     // Set an identifier for the screen area and place the panels on it
      JPanel pane = (JPanel) getContentPane(); 
      pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS)); //left to right
      pane.add(picturePanel);
      pane.add(interactionPanel);
     ////////////////////////////// end contentPane //////////////////////
   
     //make a JFrame visible on screen
      setSize(WIDTH,HEIGHT);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
   }
    

   
  /************************************************************************
  *  Method: beginGame
  *
  *  Purpose:  <put the purpose of this method here> 
  **************************************************************************/   
   public void beginGame()
   {   
      Graphics2D b = buffer.createGraphics();
      drawIntroScreen(b);
      b.dispose();             
      drawScreen(); 
   }  

   // calls to your wrapper methods to draw on your screen in this method
   public void drawIntroScreen(Graphics2D b)
   {
      rectangle(b, new Color(164, 163, 228), 0, 0,picWidth,HEIGHT,0);
      rectangle(b,Color.yellow,100,100,200,100,0);
      rectangle(b,Color.cyan,500,100,200,100,40);
      rectangle(b,Color.pink,600,100,200,100,10);
      rectangle(b,Color.pink,600,100,200,100,10);  
   
      write(b, Color.cyan, "Chiller", Font.PLAIN, 72, "Scariest Game Ever", 150, 100);
      write(b, Color.green, "Arial", Font.ITALIC | Font.BOLD, 100, "Boo!", 200, 400);
      
      //oval(b,new Color(52,183,235),350,400,250,-1);
   
      //draw a triangle
      triangle(b,new Color(255,200,188),0,   130,550,   175,400, 220,550);
      
      //candy cane
      arc(b, Color.white, 300, 300, 160, 160, 0, 180, 50);
      line(b, Color.white, 300,430,300,600,50);
      line(b, new Color(168, 96, 24), 275,430,325,460,10);
      

      // small snowflakes in the background
      for (int i=0; i<100 ; i++)
      {
         int x = (int)(Math.random() * picWidth);
         int y = (int)(Math.random() * HEIGHT);
         write(b,Color.white, "Chiller", Font.PLAIN, 24, "*", x, y);
      }
      // large snowflakes in the foreground
      for (int i=0; i<40 ; i++)
      {
         int x = (int)(Math.random() * picWidth);
         int y = (int)(Math.random() * HEIGHT);
         write(b,Color.white, "Chiller", Font.PLAIN, 72, "*", x, y);
      }
      rectangle(b, Color.blue,2,12,picWidth-15,HEIGHT-15,5);

   }
   public void arc(Graphics2D b, Color c, int x, int y, int w, int h, int startAngle, int angleSize, int strokeSize)
   {
      b.setColor(c);
      
      if (strokeSize >= 0)
      {
          b.setStroke(new BasicStroke(strokeSize));
          b.drawArc(x,y,w,h,startAngle,angleSize);
       }
       else
          b.fillArc(x,y,w,h,startAngle,angleSize);   
   }
   public void line(Graphics2D b, Color c, int x1, int y1, int x2, int y2, int strokeSize)
   {
      b.setColor(c);
      b.setStroke(new BasicStroke(strokeSize));
      b.drawLine(x1,y1,x2,y2);
    }
 
   public void write(Graphics2D b, Color c, String font, int fontStyle, int fontSize, String message, int x, int y)
   {
      b.setColor(c);
      Font newFont = new Font(font, fontStyle, fontSize); //what font, italics/bold/plain, pixel size
      b.setFont(newFont);
      b.drawString(message, x, y);
   }
 
 
   // call:   triangle(b,new Color(255,200,188),0,   130,550,   175,400, 220,550);
   public void triangle (Graphics2D b, Color c, int strokeSize, int x1, int y1, int x2, int y2, int x3, int y3 )
   {
      b.setColor(c);
      int[] xValues ={x1,x2,x3};
      int[] yValues ={y1,y2,y3};
      if (strokeSize > 0)
      {
         b.setStroke(new BasicStroke(strokeSize));
         b.drawPolygon(xValues, yValues,3);
      }
      else
         b.fillPolygon(xValues, yValues,3);
   }

   
   
  //rectangle method
   public void rectangle(Graphics2D b,Color c, int x,int y, int w, int h, int strokeSize)
   {
      b.setColor(c);
   
      if (strokeSize > 0)
      { 
         b.setStroke(new BasicStroke(strokeSize));
         b.drawRect(x,y,w,h); //outline
      }
      else
         b.fillRect(x,y,w,h);  //filled in rectangle
   }
  
  //oval method
   public void oval(Graphics2D b, Color c, int x, int y, int w, int h, int strokeSize)
   {
      b.setColor(c);
   
      if (strokeSize > 0)
      {
         b.setStroke(new BasicStroke(strokeSize));
         b.drawOval(x,y,w,h);
      }
      else
         b.fillOval(x,y,w,h);
   }


 /************************************************************************
  *  Method: drawScreen
  *
  *  Purpose:  <put the purpose of this method here> 
  **************************************************************************/ 
   public void drawScreen()
   {
      Graphics2D g = (Graphics2D)this.getGraphics();
        
      g.drawImage(buffer,10,10,this);
      Toolkit.getDefaultToolkit().sync();
      g.dispose();
   }


  /**********************************
  *method: Paint
  *
  *********************************************/   
  public void paint(Graphics g)
   {
      try
      {
         Thread.sleep(1000);
         drawScreen();
         interactionPanel.repaint();
      }
      catch(Exception e)
      {
         System.out.println("Problem painting the screen.");
         e.printStackTrace();
      }
   }
   
   
   
  /**********************************
  *method: actionPerformed
  *
  *********************************************/
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource() == choiceComboBox)
      {
         String selected = choiceComboBox.getSelectedItem().toString();
         if(selected.equals("Start the adventure game."))
            outside();
         if(selected.equals("Quit."))
            System.exit(0); 

      }
   }
   
   
   /*******************************************************************************
   *  Method: addPicture
   *
   *  Purpose: Place an image in a specific x,y position.  Scale picture if width
   *           and height values are non-zero.
   *******************************************************************************/  
   public void addPicture(Graphics2D b, String picFileName, int width, int height, int x, int y)
   {
      BufferedImage img = null;
      Image newImage = null;
      try 
      {
         img = ImageIO.read( new File(picFileName ));
         if (width != 0 && height != 0)
         {
            newImage = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            b.drawImage(newImage, x,y, this);
         }
         else
            b.drawImage(img, x, y,  this);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         String msg1 = "Runtime error caught in addPicture: \""+picFileName+"\" not found or read properly.";
         String msg2 = "Make sure that your picture file is in the same folder as your .java file.";
         System.out.println(msg1+"\n"+msg2);
         //text(b, Color.ORANGE, msg1, 20,50, new Font("Arial", Font.BOLD,14);
         write(b, Color.ORANGE, "Arial", Font.BOLD, 14,msg1, 20, 50 );
         write(b, Color.ORANGE, "Arial", Font.BOLD, 14,msg2, 20, 75);
      }
   }

   
  /********************************
  *method: outside
  ************************************/
  public void outside()
  {
      Graphics2D b = buffer.createGraphics();
      // input:  Graphics2D b, String picFileName, int width, int height, int x, int y
      addPicture(b, "scaryHouse.png", picWidth, HEIGHT, 0,0);
      
      storyTextArea.setText("This will show up in the green area of my screen.");
      String[] newItems={"  ", "Go upstairs.","Walk down the long hallway."};
      setChoices(newItems);
      drawScreen();
      b.dispose();
  }
  
  /*******************************************
  * Method:  setChoices
  *
  * Purpose:  Create a new set of choices for 
  *           the combo box
  ********************************************/
  public void setChoices(String[] choices)
  {
      choiceComboBox.removeAllItems();
      for(String s: choices)
         choiceComboBox.addItem(s);
  }
  
  

  /****************************************************************
  *Method: play a wave FIELD
  *****************************************************************/
   public void play(Clip myClip, String song)
   {
      try{
         AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource(song));
         myClip = AudioSystem.getClip();
         myClip.open(audioInputStream);
         myClip.start();
      }
      catch(Exception ex) {
         System.out.println("caught error"+ex);
      }
   }
  //play(clip,"alone-with-the-darkness.wav");

   /*******************************************************
   * METHOD: playSFX() <BR>
   *     Plays desired SFX by specifying path
   * @Params: String pathway <BR>
   *     pathway will take the pathway of the .wav file
   *******************************************************/
   public void playSFX(String pathway, float volume)
   {
      try 
      {
         File audioFile = new File (pathway);
         AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
         Clip clip = AudioSystem.getClip();
         clip.open(audioStream);
         FloatControl setVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
         setVolume.setValue(volume); // Reduce volume by 15 decibels - pass in -15.0f as volume
         clip.start();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.out.println("Run time error when adding audio file " + pathway);
         System.out.println("Make sure audio file is in .wav format and path is specified properly.");
      }
   }
   //playSFX("alone-with-the-darkness.wav", 0.15f);
  
} //end of class AdventureGame