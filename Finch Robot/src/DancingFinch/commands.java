package DancingFinch;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class commands {
	
Finch  myf = new Finch();

public commands (int decValue , String binValue) {
	
	
	for (int a = binValue.length() -1; a >=0; a-- ) {
		
		
		if (binValue.charAt(a) == '1')
		{
			myf.setLED(240, 114, 114);
			myf.buzz(100, 6000);
			myf.setWheelVelocities(decValue, decValue, 6000);
		}
		else
		{
			myf.setLED(81, 40, 40);
			myf.buzz(50, 5000);
			myf.setWheelVelocities(-decValue, -decValue, 5000);
		}
	}
	}
	
}

//for (a = 0; a <= binValue.len() -1; a++)
//Start from left to rig
