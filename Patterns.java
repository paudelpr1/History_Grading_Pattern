/**
Date: 03/07/2022

Course: CSCI 3005 60360

Description: This program uses the hidden patterns dynamic programming approach to calculate partial credit
             for students who incorrectly rank one or more historical events. This program finds the sequence 
             of events which are in correct order relative to each other from a given text file. 
             
On my honor, I have neither given nor received unauthorized help while
completing this assignment.

Name: Prasansha Paudel
CWID: 30120811

*/

/*imports required java classes */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Patterns class compares the two text files to longest sequence of events which are in correct order relative 
 * to each other. 
 * It uses dynamic programming to comapre two text files and gives the events which are in correct order.
 */
public class Patterns {

    //declares and initializes arrylist to store the contents of text file. 
    private List<String> correctAnswers = new ArrayList<>(); //This list stores the list of correct answers. 
    private List<String> givenAnswers = new ArrayList<>();   //This list stores the list of student's answers. 
    private List<String> givenAnswers2 = new ArrayList<>();  //This list stores the list of student's answers.

    /*global variable to store 2d array values */
    private int[][] arrayValues;
    private String[][] formedArraySymbols;

    /**
     * Reads the text file called filename and stores the values of filename in an arraylist
     * @param filename text file containing the correct sequence of events. 
     */
    public Patterns(String filename)
    {
        /*try block reads filename and adds the events inside and arraylist */
        try{
            Scanner in = new Scanner(new File(filename));
            String data = "";

            while(in.hasNextLine())
            {
                data = in.nextLine();

                //Condition to check if filename consists of digits. 
                if(!data.matches("[0-9]+"))
                {
                    correctAnswers.add(data); //Adds String events to arraylist 
                }
            }

            in.close();
        }

        /*Catch block to prevent exception */
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    /**
     * Reads the textfile containing studnet's response and stores the filename into an arraylist.
     * The method compares calls another method, compares two text file, and gives the total number 
     * of correct sequence of events. 
     * @param filename textfile containing a student's responses. 
     * @return result number of events that are in correct order.  
     */
    public int grade(String filename)
    {
        int result = 0;

        /*try block reads filename and adds the events inside and arraylist */ 
        try{
            Scanner in = new Scanner(new File(filename));
            String inputData = "";

            while(in.hasNextLine())
            {
                inputData = in.nextLine();

                //Condition to check if filename consists of digits.
                if(!inputData.matches("[0-9]+")) 
                {
                    givenAnswers.add(inputData); //Adds String events to arraylist             
                }
            } 
            String output = "";

            /*Calls outputPattern method to get the total strings that are in correct order */
            output = outputPattern(correctAnswers, givenAnswers,  arrayValues, formedArraySymbols);
            String[] arrayPattern = output.split(","); 

            /*Calculates the number of correct strings from the output string */
            result = arrayPattern.length;
           
            in.close();         
        }

        /*Catch block to prevent exception */
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }

        /*returns the integer value that represents the correct number of sequence of events from student's response*/
        return result; 
    }

    /**
     * Reads the textfile containing studnet's response and stores the filename into an arraylist.
     * The method compares calls another method, compares two text file, and outputs the strings of events that are in correct order. 
     * @param filename textfile containing a student's responses. 
     * @return output String values that represt the events that are correct order relative to each other. 
     */
    public String pattern(String filename)
    {
        String output = "";
        /*try block reads filename and adds the events inside and arraylist */ 
        try{
            Scanner in = new Scanner(new File(filename));
            String inputData = "";

            while(in.hasNextLine())
            {
                inputData = in.nextLine();
                //Condition to check if filename consists of digits.
                if(!inputData.matches("[0-9]+"))
                {
                    givenAnswers2.add(inputData); //Adds String events to arraylist
                    
                }
            }

            /*Calls outputPattern method to get the string values that are in correct order */
            output = outputPattern(correctAnswers, givenAnswers2,  arrayValues, formedArraySymbols);

            in.close();
        }

        /*Catch block to prevent exception */
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }

        /*returns the string value which represents the events that are in correct order with respect to each other */
        return output;
    }

    /**
     * This method takes two arraylist conisisting of sequence of events and finds the hidden pattern in those list.
     * This method uses dynamic programming approach to create 2d array which stores length and direction of hidden pattern. 
     * @param answerList_1 arraylist to store the correct sequence of events. 
     * @param answerList_2 arraylist to store the sequence of events given by student. 
     * @param firstArray 2D array to compare two arraylist and store the pattern length.  
     * @param secondArray 2D array to record the direction pattern based on pattern length. 
     * @return secondArray 2D array giving the direction of partial optimal results. 
     */
    private String[][] arrayFormation(List<String> answerList_1, List<String> answerList_2, int[][]firstArray, String[][]secondArray)
    {
        //values for array length 
        int row = answerList_1.size() + 1;
        int column = answerList_2.size() + 1; 

        //initializes array
        firstArray = new int[row][column];
        secondArray = new String[row][column];

        for(int i = 0; i < row; i++) //go through each row 
        {
            for(int j = 0; j < column; j++) // go through each column
            {
                //Condition to check if row or column is 0. 
                if(i == 0 || j == 0)
                {
                    firstArray[i][j] = 0;  //assigns 0 to an integer array

                    //condition to create direction pattern for string array with 0 row or column.  
                    if(i == 0 || (i == 0 && j == 0))
                        secondArray[i][j] = "L"; //assigns L to string array if row is 0 or if both row and column is 0. 
                    else
                        secondArray[i][j] = "U"; //assigns U to string array if the column of array is 0. 
                }

                //Condition to check if two arraylist have same values at the given row and column
                else if(answerList_1.get(i-1).equals(answerList_2.get(j-1)))
                {
                    firstArray[i][j] = firstArray[i-1][j-1] + 1;  //increments the corresponding value of an integer array.
                    secondArray[i][j] = "D";  // assigns D the string array if the condition is matched. 
                }
                
                //final condition if the above two conditions are not met.  
                else 
                {
                    firstArray[i][j] = Math.max(firstArray[i-1][j], firstArray[i][j-1]); //assigns the maximum of array[row-1][column] and array[row][column -1] to the integer array
                  
                    //compares the two values of inger array to form direction of string array
                    if(firstArray[i-1][j] > firstArray[i][j-1])
                        secondArray[i][j] = "U"; //assigns U to sting array if the conditon is fulfilled
                    else
                        secondArray[i][j] = "L"; //assigns L if the condition si not possible. 
                }
            }
        }
        
        /*returns string 2D array that the direction of partial optimal result from two arraylist and integer array comparison */
        return secondArray;
    }

    /**
     * This method calculates the output pattern using the 2d directional matrix.
     * This method calls arrayformation method and tracks the directional patterns to display the hidden pattern.
     * @param answerList_1 arraylist to store the correct sequence of events.
     * @param answerList_2 arraylist to store the sequence of events given by student.
     * @param firstArray 2D array to compare two arraylist and store the pattern length.
     * @param secondArray 2D array to record the direction pattern based on pattern length. 
     * @return output output concatenates the string values of hidden patterns from two arraylist. 
     */
    private String outputPattern(List<String> answerList_1, List<String> answerList_2,
                                 int[][] firstArray, String[][] secondArray)
    {
        String output = "";

        //values for array length 
        int row = answerList_1.size() + 1; 
        int column = answerList_2.size() + 1; 

         //initializes array
        String[][] outputArray = new String[row][column]; 

        //calls arrayformation method to get 2D array cionsisting of directional pattern.  
        outputArray = arrayFormation(answerList_1, answerList_2, firstArray, secondArray);

        //uses loop to output the hidden pattern 
        while(row != 0 && column != 0)
        {
            // starts and compares the loop from the bottom right corner
            if(outputArray[row-1][column -1].equals("L")) // if the array has "L" at given index
            {
                column--;  // decreses the column length by 1. 
            }
                
            else if(outputArray[row-1][column-1].equals("U")) // if the array has "U" at given index
            {
                row--; // decreses the row length by 1. 
            }
            else if(outputArray[row-1][column-1].equals("D")) // if the array has "D" at given index
            {
                output = answerList_1.get(row-2) + "," + output; //concatenates the arraylist value at the corresponding index with output
                column--;  // decreses the column length by 1.
                row--;    // decreses the row length by 1.
            }
        }

        // returns the string variable which consists of all the common patterns in two arraylist.  
        return output;

    }

}