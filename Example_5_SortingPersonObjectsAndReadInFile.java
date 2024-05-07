import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Example_5_SortingPersonObjectsAndReadInFile
{  
   public static void main(String[] args)
   {
      ArrayList<Person> people = readInData("1 DataFile 2022 (comma delimited).csv");
   
      // print out the list in the order that it is given in the input file
      System.out.println("Names before sort: ");
      for(Person aPerson : people)
         System.out.println(aPerson.name);
   
      mergeSort(people);
     
      // use built-in forEach method to print all records to show that the readInData method works properly
      System.out.println("\nAfter sort by name: ");
      for(Person aPerson : people)
         System.out.println(aPerson.name);
   }
   
   
   public static void mergeSort(ArrayList<Person> people)
   {
      ArrayList<Person> first = new ArrayList<Person>();
      ArrayList<Person> second = new ArrayList<Person>();
      
      // split our list into two halves
      for (int i=0 ; i<people.size() ; i++)
         if (i < people.size()/2)
            first.add(people.get(i));
         else
            second.add(people.get(i));
            
      // keep splitting until each list is of size 1
      if (first.size() > 1)
         mergeSort(first);
      if (second.size() > 1)   
         mergeSort(second);  
         
      // merge the lists back together sorting as we go
      int fIndx=0, sIndx=0, i=0;
      for ( ; i<first.size() + second.size() && fIndx<first.size() && sIndx<second.size() ; i++)
      {
         String fName = first.get(fIndx).name;
         String sName = second.get(sIndx).name;
         if (fName.compareTo(sName) < 0 )
            people.set(i, first.get(fIndx++));
         else
            people.set(i, second.get(sIndx++));  
      }
      
      // move any left over Person objects into the main people list
      while(fIndx < first.size())
         people.set(i++, first.get(fIndx++));
      while(sIndx < second.size())
         people.set(i++, second.get(sIndx++));
   }
   
   
   public static void bubbleSort( ArrayList<Person> people)
   {
      for (int j = people.size()-1 ; j>0; j--)  //don't keep sorting the sorted section at bottom
         for (int i = 0 ; i<j ; i++)
         {    
            String left = people.get(i).name;
            String right = people.get(i+1).name;
            if (left.compareTo(right) > 0)
               swap(people, i, i+1);
         }
   }

   public static void swap(ArrayList<Person> people, int firstIndex, int secondIndex)
   {
      Person temp = people.get(firstIndex);
      people.set(firstIndex, people.get(secondIndex));
      people.set(secondIndex, temp); 
   }
  
   //
   public static ArrayList<Person> readInData(String fileName)
   {
      // instantiate an ArrayList for all record data
      ArrayList<Person> people = new ArrayList<Person>();
   
      try
      {
         String folder = System.getProperty("user.dir");  //data file is in same folder as .java file
         BufferedReader reader = new BufferedReader(new FileReader(fileName));
         String line;
      
         while ((line = reader.readLine())!=null)
         {
            // split the line into an array (String.split())
            String[] data = line.split(",");
                        
            // instantiate the object based off of the type given in the first field & add to ArrayList
            if (data[0].equals("Student"))
               people.add(new Student(data[1],
                                      Integer.valueOf(data[2]),
                                      data[3],data[4],data[5],
                                      Long.valueOf(data[6]),
                                      Integer.valueOf(data[7]),
                                      Double.valueOf(data[8]))); 
            else if (data[0].equals("Teacher"))
            {
               String[] teachables = data[7].split(";");
               people.add(new Teacher(data[1],
                                      Integer.valueOf(data[2]),
                                      data[3],data[4],data[5],
                                      Long.valueOf(data[6]),
                                      teachables));
            }   
         }     
      
      }
      catch ( IOException iox )
      {
         System.out.println("Problem reading " + fileName );
      }
                                                
      return people;
   }                                             
} //end of class



/**
* Person
*/
class Person
{
   String name;
   int age;
   String address;
   String phoneNumber;
   
   public Person(String name, int age, String address, String phoneNumber)
   {
      this.name = name;
      this.age = age;
      this.address = address;
      this.phoneNumber=phoneNumber;
   }

    // a constructor to "clone" the Person
   public Person(Person p)
   {
      this(p.name, p.age, p.address, p.phoneNumber);
   }
   
   public String toString()
   {
      return name+" lives at:\n\t"+address;
   }
}

/**
* Student Person
*/
class Student extends Person
{
   String school;
   long studentNumber;
   int creditsEarned;
   double gpa;
   
   public Student(String name, int age, String address, String phoneNumber, String school, long sn, int ce, double gpa)
   {
      super(name, age, address, phoneNumber);
      this.school = school;
      studentNumber = sn;
      creditsEarned = ce;
      this.gpa = gpa;
   }

   public Student(Student s)
   {
      this (s.name, s.age, s.address, s.phoneNumber, s.school, s.studentNumber, s.creditsEarned, s.gpa);
   }
   
   public String toString()
   {
      return name+" goes to "+school+" and has a "+gpa+" grade point average.";
   }
}

/**
* SchoolStaff Person
*/
class SchoolStaff extends Person
{
   String school;
   long employeeNumber;   
   
   public SchoolStaff(String name, int age, String address, String phoneNumber, String school, long en)
   {
      super(name, age, address, phoneNumber);
      this.school = school;
      employeeNumber = en;
   }

   public SchoolStaff(SchoolStaff s)
   {
      this(s.name, s.age, s.address, s.phoneNumber, s.school, s.employeeNumber);
   }
}   
   
   
/**
* Teacher SchoolStaff Person
*/
class Teacher extends SchoolStaff
{
   String[] teachableSubjects;
   
   public Teacher(String name, int age, String address, String phoneNumber, String school, long en, String[] teachables)
   {
      super(name, age, address, phoneNumber, school, en);
      employeeNumber = en;
      teachableSubjects = teachables;
   }

   public Teacher(Teacher t)
   {
      this(t.name, t.age, t.address, t.phoneNumber, t.school, t.employeeNumber, t.teachableSubjects);
   }
   
   public String toString()
   {
      String message = name+" who is a teacher at "+school+" has the following teachables:";
      for(String subject : teachableSubjects)
         message = message + "\n\t"+subject;
      return message;   
   }
   
   
}