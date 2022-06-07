/*Assignment_02 
*Author- Niall o'Brien
*L00170182
*Date 27/05/22
*Program to Read Person Objects from File
*/

import java.util.*;
import java.io.*;
//Code source: https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
import java.text.DecimalFormat;

public class ReadPersonObject{
   
   //declare as class var for scope of finally block
   static ObjectInputStream personIn;

   public static void main(String [] args){
   
      Scanner keyboardIn = new Scanner(System.in);
      
      boolean stop = false;
      int searchPPS;
      String occToSearch;
      
      try{ 
      
         //Declare and initialise input Streams and ArrayList
         FileInputStream personFileIn = new FileInputStream("PersonFile.txt");
         personIn = new ObjectInputStream(personFileIn);
         ArrayList<Person> personList = (ArrayList<Person>) personIn.readObject();
         
         do{
         
            System.out.println("***** Person Processing System *****");
            System.out.println("1. View All Persons.");
            System.out.println("2. View Person by PPS.");
            System.out.println("3. View number of males and females.");
            System.out.println("4. Calculate average age of persons on file.");
            System.out.println("5. Search for and create file of occupation.");
            System.out.println("0. Exit.");
            System.out.print("Please enter a choice from the menu: ");
            
            try{
             
               switch(keyboardIn.nextInt()){
               
                  case 1:
                  
                     viewAllPersons(personList);
                     break;
                     
                  case 2:
                     
                     System.out.print("Enter PPS No. to search: ");
                     searchPPS = keyboardIn.nextInt();
                     viewPersonPPS(personList, searchPPS);
                     break;
                     
                  case 3:
                  
                     countByGender(personList);
                     break;
                     
                  case 4:
                  
                     calcAverageAge(personList);
                     break;
                     
                  case 5:
                     
                     keyboardIn.nextLine();
                     System.out.print("Enter an occupation to search: ");
                     occToSearch = keyboardIn.nextLine();
                     searchOccupation(personList, occToSearch);
                     break;
                     
                  case 0:
                  
                     System.out.println("Goodbye!");
                     stop = true;
                     personIn.close();
                     keyboardIn.close();
                     System.exit(0);
                  
                  default:
                  
                     System.out.println("Please choose an option 0-5 from the menu.");
                   
               }//close switch
               
            }//close try containing switch statement
            catch(InputMismatchException ex){
            
               System.out.println("Please choose 0-5 for menu option.");
               
            }
                         
         }//close do         
         while(!stop);
      
      }//close try 
      catch(ClassNotFoundException ex){
      
         System.out.println("Person.class file was not found.");
         
      } 
      catch(FileNotFoundException ex){
         
         System.out.println("File PersonFile.txt was not found.");
      
      }
      catch(IOException ex){
      
         System.out.println("There was a problem reading the file.");
         
      }
      finally{//code source: https://stackify.com/best-practices-exceptions-java/
      
         if(personIn != null){
         
			   try{
            
				   personIn.close();
               
			   } 
            catch(IOException e){
            
				   System.out.println("There was a problem closing the ObjectInputStream.");
               
			   }
            
		   }//close if
      
      }//close finally
               
   }//close main
      
   //** Menu options methods **//
   
   public static void viewAllPersons(ArrayList<Person> pl){
   
      for(Person p : pl){
      
         System.out.println(p);
         
      }
      
   }//close viewAllPersons
   
   public static void viewPersonPPS(ArrayList<Person> pl, int sPPS){
   
      boolean ppsFound = false;
   
      for(Person p : pl){
      
         if(p.getPPSNo() == sPPS){
         
            ppsFound = true;
            System.out.println(p);
            
         }
         
      }
      
      if(!ppsFound){
      
         System.out.println("The PPS No. " + sPPS + " was not found.");
    
      }
          
   }//close viewPersonPPS
   
   public static void countByGender(ArrayList<Person> pl){
   
      int mCount = 0;
      int fCount = 0;
   
      for(Person p : pl){
      
         if(p.getGender().toLowerCase().charAt(0) == 'm'){
         
            mCount++;
            
         }
         else if(p.getGender().toLowerCase().charAt(0) == 'f'){
         
            fCount++;
            
         }
         else{
         
            System.out.println("There was a problem accessing the file");
            
         }
         
      }
      
      System.out.println("There is/are " + mCount + " male(s) and " + fCount + " female(s) on file.");
         
   }//close countByGender
   
   public static void calcAverageAge(ArrayList<Person> pl){
      
      int personTotal= 0;
      double ageTotal = 0;
      double averageAge;
      
      for(Person p : pl){
      
         ageTotal = ageTotal + (double)p.getAge();
         personTotal++;
         
      }
      
      averageAge = (double)(ageTotal / personTotal);
      
      //Code source: https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
      System.out.println("The average age is: " + String.format("%.2f", averageAge));
      
   }//close calcAverageAge
   
   public static void searchOccupation(ArrayList<Person> pl, String ots){
   
      //Create ArrayList to gather all Persons with searched occupation
      ArrayList<Person> occupationList = new ArrayList<>();  
      
      boolean occupationFound = false;
      int occCount = 0;
      
      Iterator<Person> itr = pl.iterator();
      
      try{
      
         while(itr.hasNext()){
         
            Person p = itr.next();
         
            if(p.getOccupation().equalsIgnoreCase(ots)){
               
               occupationList.add(p);
               occupationFound = true;
               occCount++;
               
            }
            
         }//close Iterator while
      
         if(occupationFound == true){
         
            //Create File and Object OutputStreams
            FileOutputStream occupationFileOut = new FileOutputStream(ots + ".txt");
            ObjectOutputStream occupationOut = new ObjectOutputStream(occupationFileOut);
            
            //Write ArrayList to txt file using ObjectOutputStream
            occupationOut.writeObject(occupationList);
            System.out.println(ots + ".txt successfully created with " + occCount + " entries.");
            
         }
         else{
         
            System.out.println("There are no persons with that occupation on file.");
            
         }
         
         occupationOut.close();
         
      }
      catch(FileNotFoundException ex){
      
         System.out.println("File PersonFile.txt was not found.");
         
      }
      catch(IOException ex){
      
         System.out.println("There was a problem writing to the file.");
         
      }
      
   }//close searchOccupation
    
}//close ReadPersonObject class
