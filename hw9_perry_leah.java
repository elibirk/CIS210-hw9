package hw9_perry_leah;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


public class hw9_perry_leah {
	/*PROGRAM: File Analyzer / Assignment 9 
	 * AUTHOR: Leah Perry
	 * Due Date: November 12, 2015
	 * SUMMARY: Finds data about an input file
	 */
	public static void main(String[]args) throws IOException, FileNotFoundException{
		
		Scanner scanner = new Scanner(new File("input.txt"));//opens file
		Scanner inputfile = scanner.useDelimiter(":|\\n");//delimiter to ensure it works properly
		
		int lineCount = 0;//used to count lines
		int wordCount = 0;
		int sentences = 0;
		int characters = 0;
		int digits = 0;
		int space = 0;
		int punk = 0;
		int upperCaseWords = 0;
		String filestr = "";//used to store file with newlines
		String linelessStr = "";//used to store file as is
		String arrayStr = "";//used to store file with space to break up for array
		String temp;//used to temporarily store string to add to files
		String finalstr = "";//used to create final string to print with %n newlines
		ArrayList <String> words = new ArrayList <String>();//used to store words in file
		PrintWriter outputfile = new PrintWriter("output.txt");//creates output file
		
		
		while(inputfile.hasNext()) {//while there is more in input
			lineCount++;//add to count of lines
			temp = inputfile.nextLine();//temp = line
			linelessStr = linelessStr + temp;//add temp to lineless
			arrayStr = arrayStr + temp + " ";//add temp to arraystr
			filestr = filestr + temp + "\n";//add temp to filestr
			finalstr = finalstr + temp + "%n"; //add temp to finalstr
		}//end while
		
		StringTokenizer strs = new StringTokenizer(filestr, " .,;'\n");//breaks up filestr
		while (strs.hasMoreTokens()) {
			if(Character.isUpperCase(strs.nextToken().charAt(0))){//if starts w/ upper case
				upperCaseWords++;
			}//end if
	        wordCount++;
	    }//end while
		
		sentences = sentenceCount(linelessStr);//counts sentences
		
		punk = punctuationCount(filestr);//counts punctuation marks
		 
		characters = charCount(filestr);//counts characters
	    
	    digits = numCount(filestr);//counts numbers
	    
	    space = spaceCount(filestr);//counts whitespace
	    
	    //print everything except frequency bc that's in the freqCount function
	    System.out.printf(finalstr);
	    System.out.println("Words: " + wordCount);
	    System.out.println("Lines: " + lineCount); 
	    System.out.println("Sentences: " + sentences);
	    System.out.println("Alphabetic characters: " + characters);
	    System.out.println("Digits: " + digits);
	    System.out.println("Whitespace: " + space);
	    System.out.println("Punctuation: " + punk);
	    System.out.println("Words beginning with uppercase: " + upperCaseWords);
		
	    outputfile.printf(finalstr);
	    outputfile.println("Words: " + wordCount);
	    outputfile.println("Lines: " + lineCount); 
		outputfile.println("Sentences: " + sentences);
		outputfile.println("Alphabetic characters: " + characters);
		outputfile.println("Digits: " + digits);
		outputfile.println("Whitespace: " + space);
		outputfile.println("Punctuation: " + punk);
		outputfile.println("Words beginning with uppercase: " + upperCaseWords);
	    
	    frequencyCount(arrayStr, words, outputfile);//counts frequency of words
	    
	    System.out.println("Program complete! output.txt contains all data.");
	    
		scanner.close();
		inputfile.close();
		outputfile.close();
		
	}//end main
	
	public static int sentenceCount (String linelessStr){
		/* FUNCTION: sentenceCount
		 * PURPOSE: counts sentences in a string
		 * @Parameter	filestr			string to evaluate
		 * 				sentences		number of sentences
		 * 				i				increment variable for for loop
		 * 				temp			used to increment tokens to prevent an infinite loop
		 */
		int sentences = 0;
		StringTokenizer strSentences = new StringTokenizer(linelessStr, ".,;");
		while (strSentences.hasMoreTokens()) {
			String temp = strSentences.nextToken();//increments token to prevent infinite loop
			sentences++;
		}//end while
	
		return sentences;	
	}//end sentenceCount
	
	public static int punctuationCount (String filestr) {
		/* FUNCTION: punctiationCount
		 * PURPOSE: counts punctuation in a string
		 * @Parameter	filestr			string to evaluate
		 * 				punk			number of punctuation marks
		 * 				i				increment variable for for loop
		 */
		int punk = 0;
		for (int i = 0; i < filestr.length(); i++) {
		    if (!Character.isLetter(filestr.charAt(i)) && !Character.isDigit(filestr.charAt(i))){
		    	punk++;//if char is not letter or digit, it's punct
		    }//end if
		}//end for
		return punk;
	}//end punctuationCount
	
	public static int charCount (String filestr) {
		/* FUNCTION: charCount
		 * PURPOSE: counts alphabetic characters in a string
		 * @Parameter	filestr			string to evaluate
		 * 				characters		number of letters
		 * 				i				increment variable for for loop
		 */
		int characters = 0;
		for (int i = 0; i < filestr.length(); i++) {
			if (Character.isLetter(filestr.charAt(i))){//if letter
				characters++;
			}//end if
		}//end for
		return characters;
	}//end charCount
	
	public static int numCount (String filestr) {
		/* FUNCTION: numCount
		 * PURPOSE: counts numbers in a string
		 * @Parameter	filestr			string to evaluate
		 * 				digits			number of digits
		 * 				i				increment variable for for loop
		 */
		int digits = 0;
		for (int i = 0; i < filestr.length(); i++) {//if digit
	    	if (Character.isDigit(filestr.charAt(i))){
	    		digits++;
	    	}//end if
		}//end for
		return digits;
	}//end numCount
	
	public static int spaceCount (String filestr) {
		/* FUNCTION: spaceCount
		 * PURPOSE: counts white space in a string
		 * @Parameter	filestr			string to evaluate
		 * 				space			number of white-spaces
		 * 				i				increment variable for for loop
		 */
		int space = 0;
		for (int i = 0; i < filestr.length(); i++) {
	    	if (Character.isWhitespace(filestr.charAt(i))){
	    		space++;
	    	}//end if
		}//end for
		return space;
	}//end spaceCount
	
	public static void frequencyCount(String arrayStr, ArrayList <String> words, PrintWriter out){
		/* FUNCTION: frequenctCount
		 * PURPOSE: counts frequency of words in a string
		 * @Parameter	arrayStr		string to evaluate
		 * 				words			array to store words in
		 * 				outputfile		file to output data to
		 * 				i				increment variable for for loop
		 * 				j				increment variable for for loop
		 * 				freqCount		keeps track of frequency
		 */
		int freqCount = 0;//integer used to count frequency
		StringTokenizer arrayStrs = new StringTokenizer(arrayStr, " .,;'\n");
	    while (arrayStrs.hasMoreTokens()){
	    	words.add(arrayStrs.nextToken());//adds each word to the array
	    }//end while
	    Collections.sort(words, String.CASE_INSENSITIVE_ORDER);//puts array in alphabetical order for freq count
	    
	    for(int i = 0; i < words.size();) {//increments piece to find frequency
	    	for (int j = 1 + i; j < words.size(); j++){//increments piece to compare with
	    		if (i != j && words.get(i).equalsIgnoreCase(words.get(j))) {//if they're the same
	    			freqCount++;
	    		}//end else if
	    	}//end for
	    	System.out.println("Frequency of '" + words.get(i) + "': " + freqCount);//print frequency
	    	out.println("Frequency of '" + words.get(i) + "': " + freqCount);//print frequency
	    	i = i + freqCount;//increments i since it's alphabetic to prevent re-checking frequency
    		freqCount = 1;
	    }//end for	
	    out.close();
	}//end frequencyCount

}//end hw9
