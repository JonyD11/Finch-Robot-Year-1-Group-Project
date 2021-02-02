package Morse;

import java.awt.Graphics;
import java.awt.event.PaintEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class MORSE  {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
	Finch myFinch = new Finch(); // creating object
		
        
		   char[] english = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		                  'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
		                  'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
		                  ',', '.', '?' };

		    String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", 
		                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
		                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
		                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
		                "-----", "--..--", ".-.-.-", "..--.." };

		    //Scanner keyboard = new Scanner(System.in);

		    System.out.println(" This is an English to Morse Code Translator.  ");
		    System.out.println(" Please enter what you would like translate ");
		    System.out.println("             into Morse Code. ");
		    System.out.println(" ============================================ ");

		    //String userInput = keyboard.nextLine().toUpperCase();
		    
		    String userInput = JOptionPane.showInputDialog("Enter The 2 capital words to convert it");
		    String [] splitStr = userInput.split("\\s+"); // using split method to split the String
		    
		    // For 1st Word
		    
		    char[] chars = splitStr[0].toCharArray(); // Printing the [0] index of array
		    
		    String str = "";
		    for (int i = 0; i < chars.length; i++){
		        for (int j = 0; j < english.length; j++){

		            if (english[j] == chars[i]){
		                str = str + morse[j] + " ";  
		            }
		        }
		    }
		    JOptionPane.showMessageDialog(null, str );
		    System.out.println("Converting 1st word into Morse " + str);
		    
		    for(int i = 0; i <= str.length() - 1; i++)
		    {
		    	if(str.charAt(i) == '.')
		    	{
		    		myFinch.setLED(0,255,0,2000); // green light will flash in the finch beak for 2 seconds
					myFinch.setLED(255,0,0,2000); // red light will flash for 2 second in the finch beak
		    		JOptionPane.showMessageDialog(null, new JLabel(
		    			    "<html><h2><font color='green'>Now Finch will flash green light</font></h2></html>"));
		    		JOptionPane.showMessageDialog(null, new JLabel(
		    			    "<html><h2><font color='red'>Now Finch will flash red Light</font></h2></html>"));
		    		
		    	}
		    	
		    	if(str.charAt(i) == '-')
		    	{
		    		myFinch.setLED(0,0,255,2000); // Blue light will flash in the finch beak for 2 second
					myFinch.setLED(255,0,0,2000); // red light will flash for 2 seconds in the finch beak 
		    		JOptionPane.showMessageDialog(null, new JLabel(
		    			    "<html><h2><font color='blue'>Now Finch will flash blue light</font></h2></html>"));
		    		JOptionPane.showMessageDialog(null, new JLabel(
		    			    "<html><h2><font color='red'>Now Finch will flash red Light</font></h2></html>"));
		    	}
		    	
		    }
		    
		    //**************************
		    //For 2nd Word
		    // using try catch for handling the exception
		    try 
		    {
		    char[] chars1 = splitStr[1].toCharArray();
		   
		    String str1 = "";
		    for (int i = 0; i < chars1.length; i++){
		    	for (int j = 0; j <english.length; j++){
		    		
		    		if(english[j] == chars1[i]){
		    			str1 = str1 + morse[j] + " ";
		    		}
		    	}
		    }
		    JOptionPane.showMessageDialog(null, str1 );
		    System.out.println("Converting 2nd word into Morse : "+str1);
		    for(int i = 0; i < str1.length() -1; i ++)
		    {
		    	if(str1.charAt(i) == '.')
		    	{
		    		myFinch.setLED(0,255,0,2000); // green light will flash in the finch beak for 2 seconds
					myFinch.setLED(255,0,0,2000); // red light will flash for 2 second in the finch beak
		    		JOptionPane.showMessageDialog(null, new JLabel(
    			    			"<html><h2><font color='green'>Now Finch will flash Green Light</font></h2></html>"));
    				   JOptionPane.showMessageDialog(null, new JLabel(
    			    			"<html><h2><font color='red'>Now Finch will flash red Light</font></h2></html>"));
		    	}
		    	if(str1.charAt(i) == '-')
		    	{
		    		myFinch.setLED(0,0,255,2000); // Blue light will flash in the finch beak for 2 seconds
					myFinch.setLED(255,0,0,2000); // red light will flash for 2 seconds in the finch beak
		    		JOptionPane.showMessageDialog(null, new JLabel(
    			    		"<html><h2><font color='blue'>Now Finch will flash blue light</font></h2></html>"));
    				   JOptionPane.showMessageDialog(null, new JLabel(
    			    		"<html><h2><font color='red'>Now Finch will flash red light</font></h2></html>"));
		    	}
		    		
		    }
		    }
		    catch(ArrayIndexOutOfBoundsException e)
		    {
		    	System.out.println("");
		    }
		 
//*************************************************************************************************************************************************		    
		    //reading the Text from the file and converting it into Morse code
		    FileReader readhandle = new FileReader("D:\\Morse.txt");
		    BufferedReader br = new BufferedReader(readhandle);
		    
		    String line = null;
		    while((line = br.readLine()) != null)
		    {
		    	String [] splitStr1 = line.split("\\s+");
		    	
		    	//For 1st Word
		    	char [] chars2 = splitStr1[0].toCharArray();
		    	
		    	String str2 = "";
		    	for (int i = 0; i < chars2.length; i++){
		    		for (int j = 0; j < english.length; j++){
		    			
		    			if(english[j] == chars2[i]){
		    				str2 = str2 + morse[j] + " ";
		    			}
		    		}
		    	}
		    	JOptionPane.showMessageDialog(null, str2 );
		    	System.out.println("\nConverting 1st word from the file : " + str2);
		    	for(int i = 0; i < str2.length() -1; i ++)
			    {
			    	if(str2.charAt(i) == '.')
			    	{
			    		myFinch.setLED(0,255,0,2000); // green light will flash in the finch beak for 2 seconds
						myFinch.setLED(255,0,0,2000); // red light will flash for 2 second in the finch beak
			    		JOptionPane.showMessageDialog(null, new JLabel(
	    			    			"<html><h2><font color='green'>Now Finch will flash green light</font></h2></html>"));
	    				   JOptionPane.showMessageDialog(null, new JLabel(
	    			    			"<html><h2><font color='red'>Now finch will flash red light</font></h2></html>"));
			    	}
			    	if(str2.charAt(i) == '-')
			    	{
			    		myFinch.setLED(0,0,255,2000); // Blue light will flash in the finch beak for 2 second
						myFinch.setLED(255,0,0,2000); // red light will flash for 2 seconds in the finch beak 
			    		JOptionPane.showMessageDialog(null, new JLabel(
	    			    		"<html><h2><font color='blue'>Now finch will flash blue light</font></h2></html>"));
	    				   JOptionPane.showMessageDialog(null, new JLabel(
	    			    		"<html><h2><font color='red'>Now finch will flash red Light</font></h2></html>"));
			    	}
			    		
			    }
		    	
		    	
		    	
		    
		        //************************************
		    	//For 2nd Word
		    	try
		    	{
		    	char [] chars3 = splitStr1[1].toCharArray(); // reading the [1] Index of the array
		    	String str3 = "";
		    	for(int i = 0; i< chars3.length; i++){
		    		for(int j = 0; j < english.length; j++){
		    			
		    			if (english[j] == chars3[i]){
		    				str3 = str3 + morse[j] + " ";
		    			}
		    		}
		    	}
		    	 JOptionPane.showMessageDialog(null, str3 );
		    	System.out.println("Converting 2nd word into Morse from the File : " + str3);
		    	for(int i = 0; i < str3.length() -1; i ++)
			    {
			    	if(str3.charAt(i) == '.')
			    	{
			    		myFinch.setLED(0,255,0,2000); // green light will flash in the finch beak for 2 seconds
						myFinch.setLED(255,0,0,2000); // red light will flash for 2 second in the finch beak
			    		JOptionPane.showMessageDialog(null, new JLabel(
	    			    			"<html><h2><font color='green'>Now Finch will flash green light</font></h2></html>"));
	    				   JOptionPane.showMessageDialog(null, new JLabel(
	    			    			"<html><h2><font color='red'>Now finch will flash red light</font></h2></html>"));
			    	}
			    	if(str3.charAt(i) == '-')
			    	{
			    		myFinch.setLED(0,0,255,2000); // Blue light will flash in the finch beak for 2 seconds
						myFinch.setLED(255,0,0,2000); // red light will flash for 2 seconds in the finch beak 
			    		JOptionPane.showMessageDialog(null, new JLabel(
	    			    		"<html><h2><font color='green'>Now Finch will Flash green Light</font></h2></html>"));
	    				   JOptionPane.showMessageDialog(null, new JLabel(
	    			    		"<html><h2><font color='red'>Now Finch Flash red Light</font></h2></html>"));
			    	}
			    		
			    }
		    	}
		    	catch(ArrayIndexOutOfBoundsException e)
		    	{
		    		System.out.println("");
		    	}
		    }
		    
		    br.close();
		    readhandle.close(); 
		    
		} 
	
	
		    
	}





