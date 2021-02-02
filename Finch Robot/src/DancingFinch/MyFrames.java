package DancingFinch;

import javax.swing.*;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MyFrames 
{
	public JFrame ErrorFrame;
	private JButton ErrorButton;
	private JLabel ErrorLabel;
	public JFrame DancingFinchFrame;

	public MyFrames()
	{
		ErrorFrame("");
	}

	public void ErrorFrame(String ErrorMessage)
	{
		ErrorFrame = new JFrame();
		ErrorFrame.setType(Type.POPUP);
		ErrorFrame.setAlwaysOnTop(true);
		ErrorFrame.setTitle("Error");
		ErrorFrame.setSize(400, 160);
		ErrorFrame.setLocationRelativeTo(null);
		ErrorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		ErrorFrame.getContentPane().setLayout(springLayout);
		
		ErrorLabel = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, ErrorLabel, 10, SpringLayout.NORTH, ErrorFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ErrorLabel, 72, SpringLayout.WEST, ErrorFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ErrorLabel, -73, SpringLayout.EAST, ErrorFrame.getContentPane());
		ErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorLabel.setFont(new Font("Arial", Font.BOLD, 15));
		ErrorLabel.setText(ErrorMessage);
		
		ErrorFrame.getContentPane().add(ErrorLabel);
		
		ErrorButton = new JButton("OK");
		ErrorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				ErrorFrame.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, ErrorButton, 70, SpringLayout.NORTH, ErrorFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ErrorButton, -10, SpringLayout.SOUTH, ErrorFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ErrorLabel, -6, SpringLayout.NORTH, ErrorButton);
		springLayout.putConstraint(SpringLayout.WEST, ErrorButton, 143, SpringLayout.WEST, ErrorFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ErrorButton, 240, SpringLayout.WEST, ErrorFrame.getContentPane());
		ErrorFrame.getContentPane().add(ErrorButton);
	}
}

