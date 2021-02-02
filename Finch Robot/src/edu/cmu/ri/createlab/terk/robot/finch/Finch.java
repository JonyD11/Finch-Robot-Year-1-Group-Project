package edu.cmu.ri.createlab.terk.robot.finch;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.Component;
import java.io.File;
import java.io.IOException;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.util.concurrent.TimeUnit;


//This class is designed by bharat contains all methods of Finch robot 


public class Finch {
	
	//Finalizing the specific values
	private static final int PACKET_LENGTH = 8;
	private static final int LED_MAX = 255;
	private static final int LED_MIN = 0;
	private static final int MOTOR_MAX = 255;
	private static final int MOTOR_MIN = -255;
	
	
	//*********************************** Below CODE for Flashing the LED of the Finch Robot
	
	
	//set the color of LED in finch beak using color objects
	public void setLED(final Color colour )
	{
		if (colour != null )
		{
			setLED(colour.getRed(), colour.getGreen(), colour.getBlue());
		}
		else
		{
			System.out.println("colour object was null, LED is not set");
		}
		
	}
	
	//set the colour of LED in the finch beak
	public void setLED(final int red, final int green, final int blue)
	{
		boolean inRange = true;
		if (red > LED_MAX)
		{
			inRange = false;
			System.out.println("Red value is more than original value (0 - 255), LED will not be set");
		}
		if (red < LED_MIN)
		{
			inRange = false;
			System.out.println("Value of red is Negative, LED will not be set");
		}
		if (green > LED_MAX)
		{
			inRange = false;
			System.out.println("Green value is more than original value (0 - 255), LED will not be set");
		}
		if (green < LED_MIN)
		{
			inRange = false;
			System.out.println("Value of green is Negative, LED will not be set ");
		}
		if (blue > LED_MAX)
		{
			inRange = false;
			System.out.println("Blue value is more than original value (0 - 255), LED will not be set");
		}
		if (blue < LED_MIN)
		{
			inRange = false;
			System.out.println("Value of Blue is Negative, LED will not be set ");
		}
		if (inRange)
		{
			byte[] message = new byte [PACKET_LENGTH];
			message[0] = '0';
			message[1] = (byte) red;
			message[2] = (byte) green;
			message[3] = (byte) blue;
		}
	}
	
	//set the color of led in the finch using color object for specific duration of time
	public void setLED(final Color colour, final int duration)
	{
		if (colour != null)
		{
			setLED(colour.getRed(), colour.getGreen(), colour.getBlue(), duration );
		}
		else
		{
			System.out.println("Colour object was null, LED could not be set ");
		}
		
	}
	
	//set the color of finch for the specific duration of time
	public void setLED(int red, int green, int blue, int duration) 
	{
	
		setLED(red, green, blue);
		sleep(duration);
		setLED(0, 0, 0);
	}
	
	
	//************************************ Below CODE for Movement of the Finch Robot
	
	
	//for stopping the both wheels
 	public void stopWheels()
	{
		setWheelVelocities(0, 0);
	}
	
	//the method set the velocities of both wheels, value ranging from -255 to 255, negative value represent finch move backward
	public void setWheelVelocities(final int leftVelocity, final int rightVelocity)
	{
		setWheelVelocities(leftVelocity, rightVelocity, -1);
	}
	
	//this method blocks the further program execution for the time specified by TimeToHold and then stop the wheels when time is finish
	public void setWheelVelocities(final int leftVelocity, final int rightVelocity, final int timeToHold)
    {
        if (leftVelocity <= MOTOR_MAX && leftVelocity >= MOTOR_MIN && rightVelocity <= MOTOR_MAX && rightVelocity >= MOTOR_MIN)
        {
            byte[] message = new byte[PACKET_LENGTH];
            message[0] = 'M'; 
            if(leftVelocity >= 0) {
                message[1] = 0;
                message[2] = (byte) leftVelocity;
            }
            else {
                message[1] = 1;
                message[2] = (byte)(leftVelocity * -1);
            }
            if(rightVelocity >= 0) {
                message[3] = 0;
                message[4] = (byte) rightVelocity;
            }
            else {
                message[3] = 1;
                message[4] = (byte)(rightVelocity * -1);
            }
            if (timeToHold > 0)
            {
                sleep(timeToHold);
                stopWheels();
            }
        }
        else
        {
            System.out.println("Velocity values out of range");
        }
    }
	
	//This method uses thread.sleep for the currently running to sleep for the specified seconds
	public void sleep( final int ms) 
	{
		if(ms < 0)
		{
			System.out.println("Negative time is sent by program for sleep");
		}
		else
		{
			try
			{
				Thread.sleep(ms);
			}
			catch (InterruptedException igmored)
			{
				System.out.println("Error: sleep was interrupted for some reason"); 
			}
		} 
	}
	
	
	//********************************Below code is for Accelerations
	
	
	//this method return the current X-axis accelerations value experienced by the robot 
	//range from -1.5 to +1.5g. The X-axis is the beak tail axis 
	public double getXAcceleration ()
	{
		double [] accels = getAccelerations();
		if(accels != null)
		{
			return accels [0];
		}
		System.out.println("Accelerometer not responding check Finch connection ");
		return 0.0;
	}
	//this method return the current Y-axis acceleration experienced by the robot 
	//range from -1.5 to +1.5g. the Y axis is the wheel to wheel axis 
	public double getYAcceleration()
	{
		double [] accels = getAccelerations();
		if(accels != null)
		{
			return accels[1];
		}
		System.out.println("Accelerometer not responding chech finch coonection ");
		return 0.0;
	}
	
	// this method return the current Z-axis acceleration value experienced by the robot 
	//range from -1.5g to +1.5g the Z axis run perpendicular to finch circuit board
	public double getZAcceleration()
	{
		double [] accels = getAccelerations();
		if(accels != null)
		{
			return accels [2];
		}
		System.out.println("Accelerometer not responsding check finch connection ");
		return 0.0;
	}
	
	//Use this method to return the current X,Y and Z accelerations experienced by the robot
	//value of accelerations can be in the range of -1.5g to +1.5g. when the robot is on the flat surface
	//X and Y should be close to 0g and Z should be near +1.0g
	
	// return a array of 3 doubles containing the X, Y and Z acceleration values
	
	public double[] getAccelerations()
    {
        byte [] command = new byte [PACKET_LENGTH];
        command[0] = 'A';
        byte[] rawAccelerometers = readFinch(command);
       
        if (rawAccelerometers != null)
        {
            final double[] accelerations = new double[3];
            accelerations[0] = rawToGs(rawAccelerometers[1]);
            accelerations[1] = rawToGs(rawAccelerometers[2]);
            accelerations[2] = rawToGs(rawAccelerometers[3]);
            return accelerations;
        }
        System.out.println("Accelerometer not responding, check Finch connection");
        return null;
    }
	
	private byte[] readFinch(byte[] command) {
		// TODO Auto-generated method stub
		return null;
	}

	// function that convert raw accelerometer value to g force 
	private double rawToGs(byte val) {
        double accel;

        if(val < 0x20) {
            accel = (double) (val & 0xFF) * 1.5 / 32;
        }
        else {
            accel = ((double)(val & 0xFF) - 64) * 1.5 / 32;
        }
            return accel;
        }

	
	//*********************************Below code is for the finch beak
	
	
	//this method return true if the beak is up otherwise return false
	public boolean isBeakUp()
	{
		final double [] accels = getAccelerations();
		if(accels != null)
		{
			if(accels[0] < -0.7 && accels[0] > -1.5 && accels[1] > -0.5 && accels[1] < 0.5 && accels[2] > -0.5 && accels[2] < 0.5)
			{
				return true;
			}
		}
		return false;
	}
	
	//this method return true if the beak is pointed at the floor, otherwise return false
    public boolean idBeakDown()
    {
    	final double[] accels = getAccelerations();
    	if (accels != null)
    	{
    		if(accels[0] < 1.5 && accels[0] > 0.7 && accels[1] > -0.5 && accels[1] < 0.5 && accels[2] > -0.5 && accels[2] < 0.5)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    
    //***********************************Below code is for finch is for surface 
    
    
    //this method return true if the finch is on the flat surface, otherwise return false 
    public boolean isFinchLevel()
    {
    	final double[] accels = getAccelerations();
    	if(accels != null)
    	{
    		if(accels[0] > -0.5 && accels[0] < 0.5 && accels[1] > -0.5 && accels[1] < 0.5 && accels[2] > 0.65 && accels[2] < 1.5)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    //this method return true if the finch is upside down, otherwise return false
    public boolean isFinchUpsideDown()
    {
    	final double[] accels = getAccelerations();
    	if(accels != null)
    	{
    		if(accels[0] > -0.5 && accels[0] < 0.5 && accels[1] > -0.5 && accels[1] < 0.5 && accels[2] > -1.5 && accels[2] < -0.65)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    
    //***********************************Below code is for finch Wings
    
    
    // thid method return true if the finch left wing is pointed at the ground
    public boolean isLeftWingDown()
    {
    	final double[] accels = getAccelerations();
    	if(accels != null)
    	{
    		if(accels[0] > -0.5 && accels[0] < 0.5 && accels[1] > 0.7 && accels[1] < 1.5 && accels[2] > -0.5 && accels[2] < 0.5)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    //this method return true if the finch right wing pointed at the ground, otherwise return false
    public boolean isRightWingDown()
    {
    	final double[] accels = getAccelerations();
    	if(accels != null)
    	{
    		if(accels[0] > -0.5 && accels[0] < 0.5 && accels[1] > -1.5 && accels[1] < -0.7 && accels[2] > -0.5 && accels[2] < 0.5)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    
    //**********************************Below code is for finch shaken
    
    
    //return true if the finch has been shaken since the last accelerometer read, otherwise return false
    public boolean isShaken()
    {
    	byte [] command = new byte [PACKET_LENGTH];
    	command[0] = 'A';
    	byte[] rawAccelerometer = readFinch(command);
    	if(rawAccelerometer != null)
    	{
    		if((rawAccelerometer[4] & 0x80)> 0)
    		   return true;
    		else
    			return false;
    	}
    	System.out.println("Accelerometer not responding, check Finch connection");
    	return false;
    }
    
    
    //***********************************Below code is whether finch is tapped or not
    
    
    //this method return true if the finch has been tapped since the last accelerometer read, otherwise return false
    public boolean isTapped()
    {
    	byte [] command = new byte [PACKET_LENGTH];
    	command[0] = 'A';
    	byte [] rawAccelerometers = readFinch(command);
    	if(rawAccelerometers != null)
    	{
    		if((rawAccelerometers[4] & 0x20) > 0 )
    			return true;
    		else
    			return false;
    	}
    	System.out.println("Accelerometer not responding, Check Finch conection");
    	return false;
    }
    
    
    //************************************Below code is for playing sound in finch
    
    
	//playing the sound from finch for specified duration on the Finch's internal buzzer
	public void playTone(final int frequency, final int duration)
	{
		playTone(frequency,10,duration);
	}
	
	//plays tone over the computer speakers or headphones at the given frequency(in hertz) for
	// a specified duration in millisecond at a specified volume 
	// for more about frequencies http://www.phy.mtu.edu/~suits/notefreqs.html
    public void playTone(final int frequency, final int volume, final int duration)
    {
    	playTone(frequency,volume,duration);
    }
    
    //************************************Below Code is for saySomething
    
    
   
    
    
    
	//************************************Below code is for Buzzer
    
    
    
    
    
	public void buzz(final int frequency, final int durartion)
	{
		byte[] message = new byte[PACKET_LENGTH];
		message[0] = 'B';
		message[1] = (byte) (durartion/256);
		message[2] = (byte) (durartion%256);
		message[3] = (byte) (durartion/256);
		message[4] = (byte) (durartion%256);
	}
	public void buzzBlocking(final int frequency, final int duration)
	{
		buzz(frequency, duration);
		sleep(duration);
	}
	
	
	//**************************************Below code is for finch sensors
	
	
	//return the value of let sensors, valid value range from 0 to 255
	// values indicating more light is detected by the sensors
	public int getLeftLightSensor()
	{
		return getLightSensor(0);
	}
	
	//Returns the value of the right light sensors. valid range from 0 to 255
	//values indicating more light is detected by the sensors
	public int getRightLightsensor()
	{
		return getLightSensor(1);
	}
	
	private int getLightSensor(final int id)
	{
		final int[] values = getLightSensors();
		if(values != null)
		{
			return values[id];
		}
		System.out.println("Light sensor not responding, check finch connection");
        return 0;	
	}
	
	//Return 2 integer array containing the current values of both light sensors
	//The left sensor is the 0th array element, and the right sensor is the 1st element
	//Return 2 integer array containing both light sensors readings
	public int[] getLightSensors()
	{
		byte[] command = new byte[PACKET_LENGTH];
		command[0] = 'L';
		byte[] data = readFinch(command);
		
		if (data == null)
		{
			System.out.println("Light sensor not responding, check finch connection");
			return null;
		}
		else
		{
			int[] lightSensors = new int[2];
			lightSensors[0] = (int) (data[0] & 0xFF);
			lightSensors[1] = (int) (data[1] & 0xFF);
			return lightSensors;
		}
	}
	
	//Return true if the left light sensors is greater that value specified by limit
	//otherwise return false
	public boolean isLeftLightSensor(final int limit)
	{
		return (limit < getLeftLightSensor());
	}
	
	//Return true if the right light sensors is greater that value specified by limit
    //otherwise return false
	public boolean isRightLightSensor(final int limit)
	{
		return (limit < getRightLightsensor());
	}
	
	//Returns true if there is an obstruction in front of the left side of the robot.
	public boolean isObstacleLeftSide()
	{
		return isObstactleDetected(0);
	}
	
	//Returns true if there is an obstruction in front of the right side of the robot.
	public boolean isObstacleRightSide()
	{
		return isObstactleDetected(1);
	}
	
	private boolean isObstactleDetected(final int id)
	{
		boolean [] obstacles = getObstacleSensors();
		if(obstacles != null)
			return obstacles[id];
		return false;
	}
	
	//Returns true if either left or right obstacle sensor detect an obstacle.
	public boolean isObstacle()
	{
		return isObstacleLeftSide() || isObstacleRightSide();
	}
	
	//Returns the value of both obstacle sensors as 2 element boolean array.
    //The left sensor is the 0th element, and the right sensor is the 1st element.
	public boolean [] getObstacleSensors()
	{
		byte [] command = new byte[PACKET_LENGTH];
		command[0] = 'I';
		byte[] data = readFinch(command);
		
		if(data == null)
		{
			System.out.println("Obstacle Sensor not responding, check finch connection");
			return null;
		}
		else
		{
			boolean[] obstacle = new boolean[2];
			if(data[0] > 0)
				obstacle[0] = true;
			else
				obstacle[0] = false;
			if(data[1] > 0)
				obstacle[1] = true;
			else
				obstacle[1] = false;
			return obstacle;
			}
	}
	
	
	//**************************************Below code code Temperature
	
	
	//The current temperature reading at the temperature.  The value
    // returned is in Celsius.  To get Fahrenheit from Celsius, multiply the number
    //by 1.8 and then add 32.
	public double getTemperature()
	{
		byte [] command = new byte[PACKET_LENGTH];
		command[0] = 'T';
		byte[] data = readFinch(command);
		
		if(data == null)
		{
			System.out.println("Temperature sensor is not responding, check Finch connection");
			return 0.0;
		}
		else
		{
			Double temperature = (double)((int)(data[0] & 0xFF)- 127) /2.4 + 25;
			return temperature;
		}
	}
	
	//Returns true if the temperature is greater than the value specified
	//otherwise return false
	public boolean isTemperature(final double limit)
	{
		return (limit < getTemperature());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//**************************************Below code if for quit
	
	// this closes the connection with finch and reset the finch so it is immediately ready for another program
	public void quit() 
	{
		byte[] message = new byte[PACKET_LENGTH];
		message[0] = 'R';
	
		
	}

}
