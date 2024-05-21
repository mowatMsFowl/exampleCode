import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

/**************************
|        Main Class       |
**************************/
public class Main {
  private static final long serialVersionUID = 1L;

  public static void main(String[] args) {
    AnimalSearch frame = new AnimalSearch();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    //options: JFrame.DISPOSE_ON_CLOSE  
    //         JFrame.DO_NOTHING_ON_CLOSE
    //         JFrame.HIDE_ON_CLOSE
  }
}

/********************************|
|     Method: ActionListener     |
|********************************/
public interface ActionListener extends EventListener {
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == btnLoadData)
      loadData();
    if (ae.getSource() == btnBubbleSort)
      bubbleSort();
    if (ae.getSource() == btnSaveData)
      saveToFile();         
    //current data
    System.out.println("dataList " + dataList);
  } 
}
//Test
/***************************************************|
|                 Method: readFile                  |                    
|***************************************************/
public void readFile(String fileName) {
  dataList = new ArrayList<Long>();
  String line; 
  try {
    BufferedReader in = new BufferedReader(new FileReader(fileName));
    line = in.readLine();
    while (line != null) {
      String[] tempArray = line.split(" ");
      for (String strNum : tempArray)
        dataList.add(Long.valueOf(strNum));
      line = in.readLine();
    }
    in.close();
  } catch (IOException iox) {
    System.out.println("Problem reading " + fileName);
  }
}

/****************************************************|
|                 Method: Load Data                  |     
|****************************************************/
public void loadData() {
  String path = System.getProperty("user.dir");
  readFile(path + "\\phoneNums.txt");
  textAreaUnsorted.setText(formatData(dataList));
}

class AnimalSearch extends JFrame {
  private static final long serialVersionUID = 1L;
  JButton startBtn;
  JPanel picturePanel;
  JComboBox<String> chooseAnimalClassCmbox;
  JTextArea listOfAnimalClassTextArea;

  //constructor to build the GUI
  public AnimalSearch() {
    super("Animal Search");
    Font consolasFont = new Font("Consolas", Font.PLAIN, 22);

    JPanel pane = (JPanel) this.getContentPane(); //set a reference for the content pane
    pane.setLayout(new BorderLayout());
    // Space between boxes and edges of the screen
    pane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    startBtn = new JButton("Launch Program");
    startBtn.setFont(consolasFont);
    listOfAnimalClassTextArea = new JTextArea("bunch of stuff goes here");
    listOfAnimalClassTextArea.setFont(consolasFont);
    JTextField tf = new JTextField("text field");
    tf.setFont(consolasFont);

    // make a JPanel for 2 different panels at the top
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
    JPanel middlePanel = new JPanel();
    middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS)); 
    // put a bunch of buttons on the top
    JPanel topPanel1 = new JPanel();
    //second panel for the top
    JPanel topPanel2 = new JPanel();
    topPanel2.setLayout(new GridLayout(1, 2)); 
    // populate Animal Classes and create comboBox
    String[] animalClass = {"Amphibian", "Bird", "Fish", "Insect", "Mammal", "Reptile"};     
    chooseAnimalClassCmbox = new JComboBox<>(animalClass);
    chooseAnimalClassCmbox.setBorder(BorderFactory.createTitledBorder("Filter by Animal Class:"));
    topPanel2.add(chooseAnimalClassCmbox);
    topPanel2.add(new JLabel("  "));
    topPanel2.add(new JLabel("  "));
    // place both panels on topPanel
    topPanel.add(topPanel1);
    topPanel.add(topPanel2);

    pane.add(listOfAnimalClassTextArea, BorderLayout.CENTER);
    pane.add(topPanel, BorderLayout.NORTH);
    pane.add(startBtn, BorderLayout.SOUTH);
    pane.add(tf, BorderLayout.WEST);

    pack(); //put this before setting the size
    setFont(consolasFont);
    setSize(1200, 840);
    setVisible(true);  
  }
}