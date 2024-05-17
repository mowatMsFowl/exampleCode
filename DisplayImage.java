import java.awt.Image;
import java.awt.BorderLayout;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.*;

class DisplayImage
{
   public static void main(String[] args)
   {
      Image image = null;
      try {
        // Read from a file
         File file = new File("bubble.jpg");
         image = ImageIO.read(file);
      } catch (IOException e) {
      }
    
      // Use a label to display the image
      JFrame frame = new JFrame();
      JLabel label = new JLabel(new ImageIcon(image));
      frame.getContentPane().add(label, BorderLayout.CENTER);
      frame.pack();
      frame.setVisible(true);
   }
}
