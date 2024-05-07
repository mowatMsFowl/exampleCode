/**
 * A comparison of several sort techniques
 * Written in java, Sunday May 2 2010
 * Updated:  March 24, 2015
 *
 * @author K Cogswell
 * @version    1.0
 *
 */


import java.util.Scanner;
import java.util.Properties;
import java.util.Date;
import java.util.NoSuchElementException;
import java.io.*;
import java.text.*;

class Example_5_WritingToAFile
{

  /**************************************************************************
   *                                                                        *
   *                         MAIN METHOD                                    *I
   *                                                                        *
   *************************************************************************/
   public static void main (String [] args)
   {
      Scanner sc = new Scanner (System.in);
      char choice = 'd';
      long[] phoneNums, bubbleSortedList;
   
      // read in the phone numbers from file
      phoneNums = readFile("phoneNumsList.txt");
   
      while (choice != 'E' && choice != 'e')
      {
         // print the menu onto console
         printMenu();
         choice = sc.nextLine().charAt(0);
      
         // call sort technique based on input from menu
         switch(choice)
         {
            case 'i':
                 case 'I':   // insertion
               System.out.println("insertion sort");
               break;
            case 's':
                 case 'S':   // selection
               break;
            case 'b':
                 case 'B':   // bubble
               System.out.println("bubble sort");
               bubbleSortedList = bubbleSort(phoneNums);
               break;
            case 'l':
                 case 'L':   // shell
               break;
            case 'm':
                 case 'M':   // merge
               break;
            case 'h':
                 case 'H':   // heap
               break;
            case 'q':
                 case 'Q':   // quick
               break;
         
         }
      }
   }



   /**************************************************************************
   *                                                                         *
   *                         DATETIME METHOD                                 *I
   *                                                                         *
   **************************************************************************/
   public static void dateTime()
   {
      Properties resources = new Properties();
      try
      {
         SimpleDateFormat dateFormatter = new SimpleDateFormat ("yyyy/MM/dd");
         SimpleDateFormat timeFormatter = new SimpleDateFormat ("hh:mm:ss");
         Date now = new Date();
         String dateString = dateFormatter.format(now);
         String timeString = timeFormatter.format(now);
         FileOutputStream fos = new FileOutputStream(resources.getProperty("LogFile"),true);
         PrintWriter pw = new PrintWriter(fos);
         pw.println(dateString + " " + timeString + " ");
         pw.close();
      }
      catch(Exception e)
      {
         System.out.println ("Server - logMessage(): " + e);
      }
   }

   /**************************************************************************
   *                                                                         *
   *                         READFILE METHOD                                 *I
   *                                                                         *
   **************************************************************************/
   public static long[] readFile (String fileName)
   {
      long[] phoneNums;
      try
      {  
         File file = new File(fileName);
         Scanner sc = new Scanner(file);
         int numPhoneNums = 0;
         String line;
      
         // count how many phone numbers there are    
         while( sc.hasNextLine() ) 
         { 
            line = sc.nextLine();
            if (!line.trim().equals(""))
               numPhoneNums++;
         }
      
         // store the phone numbers into an array
         phoneNums = new long[numPhoneNums];
         sc.close();
         sc = new Scanner(file);
      
         for (int i = 0 ; i < numPhoneNums ; i++)
         {
            phoneNums[i] = sc.nextLong();
            sc.nextLine();
         }
         return phoneNums;
      }
      catch ( IOException iox )
      {
         System.out.println("Problem reading " + fileName );
         phoneNums = new long[1];
         phoneNums[0] = 0;
         return phoneNums;
      }
      catch ( NoSuchElementException nsee )
      {
         System.out.println("No such element exception in reading " + fileName );
         phoneNums = new long[1];
         phoneNums[0] = 0;
         return phoneNums;
      }
   }


   /**************************************************************************
   *                                                                         *
   *                         WRITEFILE METHOD                                *I
   *                                                                         *
   **************************************************************************/
   public static void writeFile (long[] numbers, String f)
   {
      String fileName = f ;
      String line = "";
   
      try
      {
         BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
      
         for(int i = 0 ; i < numbers.length ;)
         {
            for (int j = 0 ; j < 6 && j < numbers.length; j++, i++)
               line = line + numbers[i] + " " ;
         
            out.write(line);
            line = "";
         }
         out.close();
      }
      catch (IOException e)
      {
         System.out.println("Problem writing to " + fileName );
      }
   }

   /**************************************************************************
   *                                                                         *
   *                         BUBBLE METHOD                                   *I
   *                                                                         *
   * This method will perform the bubble sort algorithm on the array.        *
   *                                                                         *
   **************************************************************************/
   public static long[] bubbleSort(long[] a)
   {
      long temp;
      for (int i = 0; i < a.length-1 ; i++)
         for (int j = a.length-1 ; i<j ; j--)
            if (a[j] < a[j-1])
            {
               temp = a[j];
               a[j] = a[j-1];
               a[j-1] = temp;
            }
   
      // print out the entire list
      for (int i = 0 ; i < a.length-1 ; i++)
         System.out.println("\t\t"+a[i]);
   
      writeFile(a, "bubblesortedPhoneNums.txt");
      
      return a;
   }

   /**************************************************************************
   *                                                                         *
   *                         PRINTMENU METHOD                                *I
   *                                                                         *
   **************************************************************************/
   public static void printMenu()
   {
      System.out.println("Which sorting technique would you like to use?");
      System.out.println("    B:  bubblesort");
      System.out.println("    I:  insertion sort");
      System.out.println("    S:  selection sort");
      System.out.println("    L:  shell sort");
      System.out.println("    M:  merge sort");
      System.out.println("    H:  heap sort");
      System.out.println("    Q:  quick sort");
      System.out.println("    ");
      System.out.println(" Type \'e\' to exit");
   }
}