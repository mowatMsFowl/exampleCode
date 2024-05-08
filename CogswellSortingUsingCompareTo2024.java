import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
   
/*-----------------------------------------------------------------------------------*
| class: CogswellStudentDBBubbleSort                                                                      
*-----------------------------------------------------------------------------------*/
public class CogswellSortingUsingCompareTo2024
{
   /*-----------------------------------------------------------------------------------*
   | Method: main                                                                    
   *-----------------------------------------------------------------------------------*/
   public static void main(String[] args)
   {
      ArrayList<Student> students = readInData("dataStudent2.txt"); 
       
      // use built-in forEach method to print all Students 
      System.out.println("Beginning list: ");
      students.forEach((s) -> System.out.println(s.lastName));
      
      //Using Collections class to sort by student number
      Collections.sort(students);
      System.out.println("\nlist after sorted by student number using compareTo:\n");
      students.forEach((s) -> System.out.println(s.getStudentNum()));  
      
      //how to implement the binary search from the Collections class
      int index = Collections.binarySearch(students, new Student(777412345L));
      if (index == -1)
         System.out.println("Student not found");
      else
         System.out.println("Student found: "+students.get(index));   
   }
   
   /*-----------------------------------------------------------------------------------*
   | Method: readInData                                                         
   | 
   | Description: This method will read records from a file, separate each field of the 
   |              record into individual pieces of data, instantiate the Student object 
   |              from the data and then add the Student object to the ArrayList.  It 
   |              will continue in this fashion line by line until all records have been
   |              read in and the ArrayList contains all Student objects.              
   *-----------------------------------------------------------------------------------*/  
   public static ArrayList<Student> readInData(String fileName)
   { 
      ArrayList<Student> students = new ArrayList<Student>();
      
      try
      {
         System.getProperty("user.dir");
         BufferedReader reader = new BufferedReader(new FileReader(fileName));
         String line;
         
         while ((line = reader.readLine())!=null)
         {
            // split the line into an array (String.split())
            String[] data = line.split(",");
            
            // build marks array
            double[] marks = new double[8];
            for(int i = 0 ; i<marks.length ; i++)
               if (!data[i+3].equals("") && !data[i+3].equals(null))
                  marks[i] = Double.valueOf(data[i+3]);
               else
                  marks[i] = 0.0;
            
            // instantiate a student object with the data
            Student myStudent = new Student(Long.valueOf(data[0]), data[1],data[2],marks,data[11]);
            
            // add that student to the hashtable
            students.add(myStudent);
         }
         return students;
      }
      catch ( IOException iox )
      {
         System.out.println("Problem reading " + fileName );
      }
     
      return students;
   }

} // end of class

/*-----------------------------------------------------------------------------------*
| Class: Student                                                        
*-----------------------------------------------------------------------------------*/
class Student implements Comparable<Student>
{
   private long studentNum;
   String firstName;
   String lastName;
   double[] marks = new double[8];
   String university;
   
   /*-----------------------------------------------------------------------------------*
   | Constructor: set all values for Student object                                                       
   *-----------------------------------------------------------------------------------*/
   public Student(long studentNum, String fn, String ln, double[] m, String uni)
   {
      this.studentNum = studentNum;
      firstName = fn;
      lastName = ln;
      university = uni;
      
      for (int i = 0 ; i<marks.length ; i++)
         marks[i] = m[i];
   }
   
   
   /*-----------------------------------------------------------------------------------*
   | Constructor: set student number and name                                                      
   *-----------------------------------------------------------------------------------*/
   public Student(long sn)
   {
      this(sn, "", "", new double[8], "");
   }

   
   /*-----------------------------------------------------------------------------------*
   | Constructor: To clone a Student object                                                        
   *-----------------------------------------------------------------------------------*/
   public Student (Student obj)
   {
      this(obj.studentNum, obj.firstName, obj.lastName, obj.marks, obj.university);
   }
 
   /*----------------------------------------------------------------------------
   | Method: 
   | Description:  
   *----------------------------------------------------------------------------*/
   public long getStudentNum()
   {
      return this.studentNum;
   }
 
   /*-----------------------------------------------------------------------------------*
   | Method: toString
   | Description: Override the Object class toString method                                                        
   *-----------------------------------------------------------------------------------*/  
   public String toString()
   {
      return firstName+" "+lastName+" ("+studentNum+") -- "+
             "\n\tGrade 12 marks: "+marks[0]+ " "+marks[1]+ " "+
             marks[2]+ " "+marks[3]+ " "+marks[4]+ " "+marks[5]+ 
             " "+marks[6]+ " "+marks[7]+ "\n\tAttending: "+university;
   }
   
   /*-----------------------------------------------------------------------------------*
   | Method: equals
   | Description: Override the Object class equals method                                                        
   *-----------------------------------------------------------------------------------*/ 
   public boolean equals(Student s)
   {
      return this.studentNum==s.studentNum;
   } 
   
   /*----------------------------------------------------------------------------
   | Method: compareTo
   | Description:  Override the String class compareTo method
   *----------------------------------------------------------------------------*/
   public int compareTo(Student s)
   {
      return (int)(this.studentNum - s.getStudentNum());
      
      // must make sure all Student abjects have a student number
   }
}