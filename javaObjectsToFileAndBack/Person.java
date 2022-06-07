/*
*Class to represent attributes of Person object
*To be used for Assignment 2
*Author: Niall o'Brien L00170182
*Date: 27/5/22
*/

import java.io.*;

public class Person implements Serializable{

   //instance variables
   private int ppsNo;
   private String name;
   private int age;
   private String gender;
   private double weight;
   private double height;
   private String occupation;
   
   private static int nextPPSNo;
   
   //Constructors
   public Person(){
   
      name = " ";
      age = 0;
      gender = " ";
      weight = 0;
      height = 0;
      occupation = " ";
      ppsNo = nextPPSNo;
      nextPPSNo++;
      
   }
   
   public Person(String n, int a, String g, double w, double h, String occ){
   
      name = n;
      age = a;
      gender = g;
      weight = w;
      height = h;
      occupation = occ;
      ppsNo = nextPPSNo;
      nextPPSNo++;
      
   }
   
   //Methods
   public void setName(String n){
   
      name = n;
      
   }
   
   public String getName(){
   
      return name;
      
   }
   
   public void setAge(int a) throws IllegalArgumentException{
   
      if(a > 0){
      
         age = a;
         
      }
      else{
      
         throw new IllegalArgumentException("Age cannot be negative value.");
         
      }
      
   }
   
   public int getAge(){
   
      return age;
      
   }
   
   public void setGender(String g){
   
      gender = g;
      
   }
   
   public String getGender(){
   
      return gender;
      
   }
   
   public void setWeight(double w) throws IllegalArgumentException{
   
      if(w > 0){
      
         weight = w;
         
      }
      else{
      
         throw new IllegalArgumentException("Weight cannot be negative value.");
      
      }
      
   }
   
   public double getWeight(){
   
      return weight;
      
   }
   
   public void setHeight(double h) throws IllegalArgumentException{
   
      if(h > 0){
      
         height = h;
      
      }
      else{
      
         throw new IllegalArgumentException("Height cannot be a negative value");
      
      }
      
   }
   
   public double getHeight(){
   
      return height;
      
   }
   
   public void setOccupation(String occ){
   
      occupation = occ;
      
   }
   
   public String getOccupation(){
   
      return occupation;
      
   }
   
   public static void setNextPPSNo(int i){
   
      nextPPSNo = i;
      
   }
   
   public int getPPSNo(){
   
      return ppsNo;
      
   }
   
   public String toString(){
   
		return   "Name:\t\t\t" + name + "\n" +
               "PPS:\t\t\t" + ppsNo + "\n" +
               "Age:\t\t\t" + age + "\n" +
               "Gender:\t\t" + gender +"\n" +
               "Weight:\t\t" + weight +"\n" +
               "Height:\t\t" + height +"\n" +
               "Occupation:\t" + occupation + "\n";
      
	}
   
}//close Person class