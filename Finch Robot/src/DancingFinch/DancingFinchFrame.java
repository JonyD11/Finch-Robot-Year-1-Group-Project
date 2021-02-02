package DancingFinch;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class DancingFinchFrame 
{
	private JFrame DancingFinchFrame;
	private JLabel NewLabel;
	private JTextField textField;
	private JButton ConvertionButton;
	private String HexaValue;
	private int DecValue;
	private String BinValue;
	private JLabel DecimalValue;
	private JTextField DecimalText;
	private JLabel BinaryValue;
	private JTextField BinaryText;
	private JButton DanceButton;
	
	MyFrames frame = new MyFrames();
	private JTextField SpeedText;
	private JLabel FinchSpeed;
	private int Speed;
	

	public DancingFinchFrame() 
	{
		setDancingFinchFrame(new JFrame());
		getDancingFinchFrame().setTitle("Dancing Finch");
		getDancingFinchFrame().setSize(320, 280);
		getDancingFinchFrame().setLocationRelativeTo(null);
		getDancingFinchFrame().setResizable(false);
		getDancingFinchFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		NewLabel = new JLabel("Please enter hexadecimal value");
		NewLabel.setVerticalAlignment(SwingConstants.TOP);
		NewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(20);
		
		ConvertionButton = new JButton("Convert");
		ConvertionButton.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent arg0) 
			{
				HexaValue = textField.getText();
				DecValue = convertor.HexaToDec(HexaValue);
				BinValue = convertor.HexaToBin(HexaValue);
				Speed = SpeedCalc(DecValue);
				if (textField.getText().isEmpty())
				{

				}
				else if (DecValue >= 144 && DecValue <= 255)
				{
					DecimalValue.setVisible(true);
					DecimalText.setVisible(true);
					BinaryValue.setVisible(true);
					BinaryText.setVisible(true);
					DanceButton.setVisible(true);
					FinchSpeed.setVisible(true);
					SpeedText.setVisible(true);
					DecimalText.setText("" + DecValue);
					BinaryText.setText(BinValue);
					SpeedText.setText("" + Speed);
				}
				else
				{
					try
					{
						frame.ErrorFrame("Please Enter A Valid Value");
						frame.ErrorFrame.setVisible(true);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		});
	
		
		DecimalValue = new JLabel("Decimal Value is");
		DecimalValue.setVisible(false);
		DecimalValue.setFont(new Font("Arial", Font.BOLD, 15));
		DecimalValue.setVisible(false);
		
		DecimalText = new JTextField();
		DecimalText.setEditable(false);
		DecimalText.setVisible(false);
		DecimalValue.setLabelFor(DecimalText);
		DecimalText.setColumns(10);
		
		BinaryValue = new JLabel("Binary Value is");
		BinaryValue.setVisible(false);
		BinaryValue.setFont(new Font("Arial", Font.BOLD, 15));
		BinaryValue.setToolTipText("");
		
		BinaryText = new JTextField();
		BinaryText.setEditable(false);
		BinaryText.setVisible(false);
		BinaryValue.setLabelFor(BinaryText);
		BinaryText.setColumns(10);
		
		DanceButton = new JButton("Dance");
		DanceButton.setVisible(false);
		DanceButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int decValue = Integer.parseInt(SpeedText.getText());
				String binValue = BinaryText.getText();
				FinchCommands Command = new FinchCommands();
				Command.Dance(decValue, binValue);
			}
		});
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, DanceButton, 113, SpringLayout.WEST, DancingFinchFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, DanceButton, -32, SpringLayout.SOUTH, DancingFinchFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, DanceButton, -5, SpringLayout.EAST, ConvertionButton);
		springLayout.putConstraint(SpringLayout.WEST, BinaryValue, 34, SpringLayout.WEST, DancingFinchFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, ConvertionButton, 4, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, ConvertionButton, -4, SpringLayout.NORTH, DecimalText);
		springLayout.putConstraint(SpringLayout.NORTH, DecimalValue, 6, SpringLayout.SOUTH, ConvertionButton);
		springLayout.putConstraint(SpringLayout.WEST, DecimalValue, 0, SpringLayout.WEST, BinaryValue);
		springLayout.putConstraint(SpringLayout.NORTH, BinaryValue, 0, SpringLayout.NORTH, BinaryText);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 4, SpringLayout.SOUTH, NewLabel);
		springLayout.putConstraint(SpringLayout.WEST, textField, 69, SpringLayout.WEST, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -69, SpringLayout.EAST, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ConvertionButton, 108, SpringLayout.WEST, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ConvertionButton, -108, SpringLayout.EAST, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, NewLabel, 7, SpringLayout.NORTH, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, NewLabel, -214, SpringLayout.SOUTH, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, NewLabel, 7, SpringLayout.WEST, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, NewLabel, 297, SpringLayout.WEST, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, DecimalText, 96, SpringLayout.NORTH, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, DecimalText, 152, SpringLayout.WEST, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, DecimalText, 264, SpringLayout.WEST, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, BinaryText, 122, SpringLayout.NORTH, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, BinaryText, 152, SpringLayout.WEST, getDancingFinchFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, BinaryText, 264, SpringLayout.WEST, getDancingFinchFrame().getContentPane());
		getDancingFinchFrame().getContentPane().setLayout(springLayout);
		getDancingFinchFrame().getContentPane().add(NewLabel);
		getDancingFinchFrame().getContentPane().add(textField);
		getDancingFinchFrame().getContentPane().add(ConvertionButton);
		getDancingFinchFrame().getContentPane().add(BinaryValue);
		getDancingFinchFrame().getContentPane().add(BinaryText);
		getDancingFinchFrame().getContentPane().add(DecimalValue);
		getDancingFinchFrame().getContentPane().add(DecimalText);
		getDancingFinchFrame().getContentPane().add(DanceButton);
		
		FinchSpeed = new JLabel("Finch Speed is");
		FinchSpeed.setVisible(false);
		springLayout.putConstraint(SpringLayout.WEST, FinchSpeed, 34, SpringLayout.WEST, DancingFinchFrame.getContentPane());
		FinchSpeed.setFont(new Font("Arial", Font.BOLD, 15));
		DancingFinchFrame.getContentPane().add(FinchSpeed);
		
		SpeedText = new JTextField();
		SpeedText.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, DanceButton, 4, SpringLayout.SOUTH, SpeedText);
		springLayout.putConstraint(SpringLayout.WEST, SpeedText, 152, SpringLayout.WEST, DancingFinchFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, FinchSpeed, -19, SpringLayout.WEST, SpeedText);
		springLayout.putConstraint(SpringLayout.EAST, SpeedText, -40, SpringLayout.EAST, DancingFinchFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, FinchSpeed, 0, SpringLayout.NORTH, SpeedText);
		SpeedText.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, SpeedText, 6, SpringLayout.SOUTH, BinaryText);
		DancingFinchFrame.getContentPane().add(SpeedText);
		SpeedText.setColumns(10);
	}


	public JFrame getDancingFinchFrame() 
	{
		return DancingFinchFrame;
	}


	public void setDancingFinchFrame(JFrame dancingFinchFrame) 
	{
		DancingFinchFrame = dancingFinchFrame;
	}
	
	public int SpeedCalc(int DecValue)
	{
		int Remainder = DecValue % 80;
		if (Remainder < 40)
		{
			int decValue = Remainder + 30;
			return decValue;
		}
		else 
		{
			return Remainder;
		}
	}
}
