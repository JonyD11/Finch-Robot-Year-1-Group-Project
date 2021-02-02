package FinchZigZag;

import java.util.*;

import javax.swing.JOptionPane;

import java.io.*;
public class fin 
{
	public static void g (int Length) throws IOException
	{
		
		int WheelSpeed = 255;
		int Distance = Length;
		double Speed = (double) ((WheelSpeed));
		double Time2 = Distance/Speed;
		int Time3 = (int) ((Time2*1000)/0.117);
		JOptionPane.showMessageDialog(null,"Random Speed Generated "+ WheelSpeed + "\n Drawing Zig Zag");
		System.out.println("");
		
		
		for (int i=0; i<finzag.section ; i++)
		{

			finzag.myfinch.setLED(200,50,25);
			finzag.myfinch.setWheelVelocities(WheelSpeed, WheelSpeed, Time3);
			finzag.myfinch.setWheelVelocities(-50, 50, 2000);
			finzag.myfinch.setLED(0,255,0);
			finzag.myfinch.setWheelVelocities(WheelSpeed, WheelSpeed, Time3);
			finzag.myfinch.setWheelVelocities(50, -50, 2000);
		
		}

		finzag.myfinch.setWheelVelocities(60, -50, 2000);

		for (int i=0; i<finzag.section ; i++)
		{

			finzag.myfinch.setLED(200,50,25);
			finzag.myfinch.setWheelVelocities(WheelSpeed, WheelSpeed, Time3);
			finzag.myfinch.setWheelVelocities(50, -50, 2000);
			finzag.myfinch.setLED(0,255,0);
			finzag.myfinch.setWheelVelocities(WheelSpeed, WheelSpeed, Time3);
			finzag.myfinch.setWheelVelocities(-50, 50, 2000);
		
		}

		//finzag.myfinch.playClip("  ");
		finzag.myfinch.setWheelVelocities(-60, 60, 1900);
		finzag.myfinch.buzz(10000,2000);

		for (int i=0; i<=6; i++)
		{

			finzag.myfinch.setLED(255,255,0, 250);
			finzag.myfinch.setLED(0,0,0, 250);
		}

		finzag.myfinch.setLED(0,0,0, 3000);
		finzag.myfinch.quit();
	}
}

