//@author: Marcelo Mariduena
import java.util.*;
import java.io.*;

public class LetterStats {
  
  public static void main(String[] args){

    // Scanner object for inputs
    Scanner input = new Scanner(System.in);
    // Prompt user for file name
    System.out.print("Enter filename: ");
    String fileName = input.nextLine();
    // Creates an File object named tempFile of the file with the named passed into the filename String variable
    File tempFile = new File(fileName);
    // If the file does not exist, terminate the program
    if(!tempFile.exists()){
      System.out.println("The file " + fileName + " does not exist.");
      System.exit(1); // terminates program
    }
    
    // Execute the following code if the user entered a valid readable file
    try {
      // Create a Scanner for file
      Scanner inputFile = new Scanner(tempFile);
      
      // Variables
      String line = "";
      String onlyLetters = "";
      String[] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
      int[] counts = new int[26];
      int numberOfLetters = 0;
      
      // Iterate through all the lines 
      while(inputFile.hasNextLine()){
        // Set the line variable to the current line
        line = inputFile.nextLine();
        // Gets rid of all non-letters in the line and add the result to noSpaces String variable
        onlyLetters += line.toUpperCase().replaceAll("[^A-Z]","");
      }
      numberOfLetters = onlyLetters.length();
      
      // The line bellow displays only the letters of the original file. Uncomment to see.
      // System.out.println(onlyLetters);
      
      
      // Count the letters in the onlyLetters String
      for (String letter : onlyLetters.split("")){
       // Get the current index value of the letter in the alphabet
       int temp = Arrays.asList(alphabets).indexOf(letter);
       counts[temp] = counts[temp] + 1;
      }
      
      // Create an ArrayList of stats
      ArrayList<ArrayObject> stats = new ArrayList<ArrayObject>();
      for (int i = 0; i < 26; i++) {
       stats.add(new ArrayObject(alphabets[i], counts[i], (double)counts[i]/numberOfLetters * 100));
      }
      
      // Rearrange the stats by count using the sort method of the Collections object
      // Pass into the arguments the stats ArrayList of objects and the Comparator of the ArrayObject object
      Collections.sort(stats, new Comparator<ArrayObject>() {
       // Override the compare method of sort to arrange the objects of the stats ArrayList by its count attribute
          @Override public int compare(ArrayObject o1, ArrayObject o2) {
              return o2.getCount() - o1.getCount(); // Descending
          }
      });

      // Display the stats 
      System.out.println("\nLetter's Frequencies");
      System.out.println("------------------------");
      System.out.println("Letter\tFreq\tPercentage");
      for (int i = 0; i < 26; i++) {
       System.out.printf("%s\t%s\t%.2f%% \n", stats.get(i).getLetter(), stats.get(i).getCount(), stats.get(i).getPercentage());
      }
      
      System.out.println("Total letters: " + numberOfLetters);
      inputFile.close();
      
    } catch (FileNotFoundException E ){
      System.out.println("That is not a file!");
    }
    input.close();
  }
}

class ArrayObject{
 private String letter;
 private int count;
 private double percentage;
 ArrayObject(String _letter, int _count, double _percentage){
  letter = _letter;
  count = _count;
  percentage = _percentage;
 }
 public String getLetter() {return letter;}
 public int getCount() {return count;}
 public double getPercentage() {return percentage;}
}
