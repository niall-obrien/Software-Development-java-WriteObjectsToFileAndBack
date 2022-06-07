/*Assignment_02 
*Author- Niall o'Brien
*L00170182
*Date 27/05/22
*Program to Write Person Object to File
*/

import java.util.*;
import java.io.*;
//Code source: https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html
import java.nio.file.Files;

public class WritePersonObject{
   
   private static char ans;
   
   public static void main(String [] args){
   
      Scanner keyboardIn = new Scanner(System.in);
      
      //Create ArrayList for Person Objects
      ArrayList<Person> personList = new ArrayList<>();
      
      //declare input variables
      String n, g, occ;
      int a;
      double w, h;
      
      //dec and init File Object (path) to check if file exists
      //code source: https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
      File f = new File("PersonFile.txt");
      
      //declare and init validity booleans
      boolean validAge, validWeight, validHeight;
            
      try{
      
         //Check if file already exists
         if(f.exists()){
      
            //Dec & init File and Object InputStreams to open and read from file, and ArrayList to gather Objects from Stream
            FileInputStream personFileIn = new FileInputStream("PersonFile.txt");
            ObjectInputStream personIn = new ObjectInputStream(personFileIn);
            personList = (ArrayList<Person>) personIn.readObject();
            //Retrieve last PPS No. from ArrayList, increment and send value to setNextPPSNo
            Person.setNextPPSNo(personList.get(personList.size()-1).getPPSNo() + 1);
            personIn.close();
            
         }
         else{
         
            Person.setNextPPSNo(100);
            
         }
         
         //Dec & init File and Object OutputStreams to open and write to file
         FileOutputStream personFileOut = new FileOutputStream("PersonFile.txt");
         ObjectOutputStream personOut = new ObjectOutputStream(personFileOut);
          
         do{
            
            //reset all booleans for data entry validity checking
            validAge = false;
            validWeight = false;
            validHeight = false;
            
            try{
               
               System.out.print("Enter person's name: ");
               n = keyboardIn.nextLine();
            
               do{
                  
                  System.out.print("Enter person's age: ");
                  a = keyboardIn.nextInt();
                  
                  if(a > 0){
                     
                     validAge = true;
                     break;
                     
                  }
                  else{
                  
                     System.out.println("Age cannot be a negative value");
                  
                  }
                  
               }   
               while(!validAge);
               
               System.out.print("Enter person's gender: ");
               g = keyboardIn.next();
               
               do{
                  
                  System.out.print("Enter person's weight (kg): ");
                  w = keyboardIn.nextDouble();
                  
                  if(w > 0){
                  
                     validWeight = true;
                     break;
                     
                  }
                  else{
                  
                     System.out.println("Weight cannot be negative value");
                     
                  }
                  
               }
               while(!validWeight);
               
               do{
                  
                  System.out.print("Enter persons's height (m): ");
                  h = keyboardIn.nextDouble();
                  
                  if(h > 0){
                  
                     validHeight = true;
                     break;
                     
                  }
                  else{
                  
                     System.out.println("Height cannot be negative value");
                     
                  }
                  
               }
               while(!validHeight);
               
               System.out.print("Enter person's occupation: ");
               occ = keyboardIn.next();
               
            }//close try to check age, weight, height inputs
            catch(InputMismatchException ex){
            
               System.out.println("Please enter numeric values for age, weight and height");
               keyboardIn.nextLine();
               continue;
               
            }
            
            if(validAge && validWeight && validHeight){
            
               personList.add(new Person(n, a, g, w, h, occ));
               
            }
            
            System.out.print("Would you like to add another person? (Y/N): ");
            ans = keyboardIn.next().charAt(0);
            keyboardIn.nextLine();
            
         }while(ans == 'y' || ans == 'Y');  //continue entering persons if user enters y or Y
         
         if(ans != 'y' || ans != 'Y'){
         
            //Write ArrayList to File
            personOut.writeObject(personList);
            System.out.println("PersonFile.txt successfully updated.");
            personOut.close();
            
         }
         
         //Input & Output Streams closed in finally block
         
      }//close try to set up I/O Streams
      catch(ClassNotFoundException ex){
      
         System.out.println("The Person.class file was not found.");
         
      }
      catch(FileNotFoundException ex){
      
         System.out.println("PersonFile.txt was not found.");
         
      }
      catch(IOException ex){
      
         System.out.println("There was a problem reading/writing the file.");
         ex.printStackTrace();
         
      }
      
   }//close main
   
}//close WritePersonObject class