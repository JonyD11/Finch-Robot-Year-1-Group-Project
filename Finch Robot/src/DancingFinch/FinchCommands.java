package DancingFinch;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class FinchCommands 
{
	Finch myf = new Finch();

	public void Dance(int decValue, String binValue) 
	{
//		myf.buzz(659, 500);
//		myf.sleep(100);
//		myf.buzz(0, 100);
//		myf.sleep(100);
//		myf.buzz(659, 500);
//		myf.sleep(100);
		
		
		for (int i = binValue.length() - 1; i >= 0; i--)
		{
			if (binValue.charAt(i) == '1')
			{
				myf.setLED(0, 128, 0);
				myf.buzz(392, 6000);
//				myf.sleep(100);
				myf.setWheelVelocities(decValue, decValue, 6000);
			}
			else
			{
				myf.setLED(255, 0, 0);
				myf.buzz(783, 5000);
//				myf.sleep(100);
				myf.setWheelVelocities(-decValue, -decValue, 5000);
			}
		}
		myf.quit();
	}
}
