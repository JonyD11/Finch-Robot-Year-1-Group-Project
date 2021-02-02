package FinchZigZag;

import java.util.*;
import javax.swing.*;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class finzag {
	
	public static int section, length, distance;
	public static Finch myfinch = new Finch();
	
	public static void main(String[] args) throws Exception{
		
		ArrayList<Integer> numbers = new ArrayList<Integer>(7);

		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);
		numbers.add(7);
		numbers.add(8);

		JOptionPane.showMessageDialog(null, "Welcome, its showtime let’s START .Are you ready ?");

		do 
		{
		try 
		{
			section = Integer.parseInt(JOptionPane.showInputDialog("Enter number of the ZigZag ( Remember lines must be less or equal to 8)"));

		if (numbers.contains(section)==true) 
		{
			break;
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Try again, Remember input number of lines");
		}
		}
		
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Try again, Remember input length of lines");
		}
		}
		while(numbers.contains(section)==false);
		
		do 
		{
		try 
		{
			length = Integer.parseInt(JOptionPane.showInputDialog("Enter length of the ZigZag ( Remember length must greater than 39cm and less then 71cm.)"));

		if (length<=70 && length>=40) 
		{
			distance = length;

			break;
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Try again");
		}
		}
		catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Invalid ZigZag",null, JOptionPane.ERROR_MESSAGE);
		}
		}
		
		while(length>70||length<40);
		fin.g(distance);

	}

}

