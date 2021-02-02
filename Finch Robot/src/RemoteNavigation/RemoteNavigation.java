package RemoteNavigation;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

//Author Jennifer
//This Program is designed to navigate the Finch robot by specific set of commands
public class RemoteNavigation {
	
	private static Object move;
	private int wheelSpeed = 50;
	private static Finch myfinch = new Finch();
	private static Scanner in = new Scanner(System.in);
	private static ArrayList<Character> instructions = new ArrayList<Character>();
	private static ArrayList<Float> durationsList = new ArrayList<Float>();
	private static ArrayList<Integer> speedList = new ArrayList<Integer>();

	RemoteNavigation() {
		runProgram();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Welcome, its time to Navigate the Finch Robot");
		while(true)
		{
			runProgram();
		}
	}
	
	public static void runProgram() 
	{
		while (true)
		{
			JOptionPane.showMessageDialog(null,"What would you like to do (Forward, Backward, Right, Left, retracing, Quit\ncommands for doing the above function(f/b/r/l/t/q)");
			char direction = JOptionPane.showInputDialog("Enter The direction at Which Finch move (F, B, R, L, T )").charAt(0);
			System.out.println("Duration to move for (1-6) : ");
			float timeDuration = Float.parseFloat(JOptionPane.showInputDialog("Enter Time Duration to Navigate Finch ( Remember Time Must be between 1 sec to 6 sec)"));
			if(timeDuration > 6) timeDuration = 6;
			else if(timeDuration < 1) timeDuration = 1;
			System.out.println("Duration set to " + timeDuration);
			System.out.println("speed of the finch (0 - 100) : ");
			int speedFinch = Integer.parseInt(JOptionPane.showInputDialog("Enter spped of the finch ( Remember speed must be between 0 - 100)"));
			if(speedFinch > 100) speedFinch = 100;
			else if (speedFinch < 0) speedFinch = 0;
			
			selectFunction(direction, timeDuration, speedFinch);
		}
	}
	
	public static void selectFunction(char direction, float timeDuration, int speedFinch) {
		switch (direction) {
		case 'f':
		case 'F':
			forward(timeDuration, speedFinch);
			JOptionPane.showMessageDialog(null, "selected forward");
			break;
		case 'b':
		case 'B':
			backward(timeDuration, speedFinch);
			JOptionPane.showMessageDialog(null, "selected backward");
			break;
		case 'r':
		case 'R':
			turnRight(timeDuration);
			JOptionPane.showMessageDialog(null, "selected Right");
			break;
		case 'l':
		case 'L':
			turnLeft(timeDuration);
			JOptionPane.showMessageDialog(null, "selected Left");
			break;
		case 't':
			if (instructions.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Unavialable");
				return;
			}
			reTracing();
		case 'q':
		case 'Q':
			quit();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Input something Else");
			return;
		}
		
		instructions.add(direction);
		durationsList.add(timeDuration);
		speedList.add(speedFinch);
	}
	
	public static boolean forward(float timeDuration, int finchSpeed)
	{
		//Start time of the method being called
		long start = System.nanoTime();
		//Initializing elapsedTime
		double elapsedTimeInSeconds = 0;
		while (elapsedTimeInSeconds < timeDuration)
		{
			myfinch.setWheelVelocities(finchSpeed, finchSpeed);
			//getting the current time to compare with start time 
			long end = System.nanoTime();
			elapsedTimeInSeconds = end - start;
			//This converts the elapsed time integer seconds
			elapsedTimeInSeconds = (double) elapsedTimeInSeconds/1_000_000_000;
		}
		
		
		//after while loop has broken the wheels will stop
		myfinch.stopWheels();
		return true;
	}
	
	public static boolean backward(float timeDuration, int finchSpeed)
	{
		//myfinch.setWheelVelocities(-WheelSpeed, -WheelSpeed);
		long start = System.nanoTime();
		//initializing elapsedTime
		double elapsedTimeInSeconds = 0;
		while(elapsedTimeInSeconds < timeDuration)
		{
			myfinch.setWheelVelocities(-finchSpeed, -finchSpeed);
			//getting the current time to compare with start time
			long end = System.nanoTime();
			elapsedTimeInSeconds = end - start;
			//This converts the elapsed time integer seconds
			elapsedTimeInSeconds = (double)elapsedTimeInSeconds/1_000_000_000;
		}
		//after while loop has broken the wheels will stop
		myfinch.stopWheels();
		return true;
	}
		
		public static boolean turnRight(float timeDuration) {

			long start = System.nanoTime();
			// initializing elapsedTime.
			double elapsedTimeInSeconds = 0;
			while (elapsedTimeInSeconds < timeDuration) 
			{
				myfinch.setWheelVelocities(90, 0);
				// getting the current time to compare with start time.
				long end = System.nanoTime();
				elapsedTimeInSeconds = end - start;
				// This converts the elapsed time int seconds
				elapsedTimeInSeconds = (double) elapsedTimeInSeconds / 1_000_000_000;
			}
			// after while loop has broken the wheels will stop.
			myfinch.stopWheels();
			return true;
	}
		
		public static boolean turnLeft(float timeDuration) 
		{
			long start = System.nanoTime();
			// initializing elapsedTime.
			double elapsedTimeInSeconds = 0;
			while (elapsedTimeInSeconds < timeDuration) 
			{
				myfinch.setWheelVelocities(0, 90);
				// getting the current time to compare with start time.
				long end = System.nanoTime();
				elapsedTimeInSeconds = end - start;
				// This converts the elapsed time int seconds
				elapsedTimeInSeconds = (double) elapsedTimeInSeconds / 1_000_000_000;
			}
			// after while loop has broken the wheels will stop.
			myfinch.stopWheels();
			return true;
		}
		
		public static void reTracing() 
		{
			System.out.println("how many steps do you want to retrace?");
			int steps = 0;
			try 
			{
				steps = Integer.parseInt(in.next());
			} 
			catch (Exception e) 
			{
				System.out.println("put in an integer");
			}
			int lSize = instructions.size();
			for (int i = lSize - 2; i >= lSize - steps - 1; i++) 
			{
				selectFunction(instructions.get(i), durationsList.get(i), speedList.get(i));
			}
		}
		
		public void Writetotextfile() {
			String lSep = System.getProperty("line.separator");
			String output = "";
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			output += df.format(date) + lSep;
			System.out.println(output);
			
			for (int i = 0; i < instructions.size(); i++) {
				output += instructions.get(i) + " ";
				output += durationsList.get(i) + " ";
				output += speedList.get(i) + lSep;
			}
			try {
				FileWriter writer = new FileWriter("D:\\Finch\\Jennifer.txt", true);
				writer.write(output);
				writer.close();
			} catch (IOException e) {
				System.out.println("error writing stats");
				e.printStackTrace();
			}
		}
		
		public static void quit() 
		{
			myfinch.quit();
			System.exit(0);
		}
}

