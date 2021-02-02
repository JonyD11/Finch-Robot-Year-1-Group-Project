package DancingFinch;

import java.awt.EventQueue;

public class Main 
{
	MyFrames EFrame = new MyFrames();

	public static void main(String[] args) 
	{	
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					DancingFinchFrame frame = new DancingFinchFrame();
					frame.getDancingFinchFrame().setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main()
	{
		EFrame.ErrorFrame("Please Enter A Valid Value");
	}
}

