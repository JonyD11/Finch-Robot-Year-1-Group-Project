package MovementUsingKeyBoard;

import javax.swing.*;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MovementUsingKey extends JFrame implements KeyListener 
{
  
    //Finch
    public static Finch myFinch;
    public static boolean endProgram = true;
    
    //Finch Movements
    private static boolean leftKeyPressed = false;
    private static boolean rightKeyPressed = false;
    private static boolean upKeyPressed = false;
    private static boolean downKeyPressed = false;
    private static boolean dKeyPressed = false;
    private static boolean sKeyPressed = false;
    private static boolean aKeyPressed = false;
    // JPanel
    JPanel pnlButton = new JPanel();
    // Buttons
    JButton btnColor = new JButton("Set Color");
    JButton btnEnd = new JButton("End Program");

    public MovementUsingKey() 
    {
        //Set Button Bounds
        btnColor.setBounds(60, 400, 220, 30);
        btnEnd.setBounds(60, 400, 220, 30);
        //JPanel bounds
        pnlButton.setBounds(800, 800, 200, 100);

        //Adding to JFrame
        pnlButton.add(btnEnd);
        pnlButton.add(btnColor);
        add(pnlButton);
        btnColor.addActionListener(new ActionListener() 
        {
            //Open JColorChooser to Change the Finch's Beak Color
            public void actionPerformed(ActionEvent e) 
            {
                Color initialBackground = Color.BLUE;
                Color background = JColorChooser.showDialog(null, "Change Finch's Beak Color",
                        initialBackground);
                if (background != null) 
                {
                    myFinch.setLED(background);
                }
            }
        });
        btnEnd.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                //Ends Program
                endProgram = false;
            }
        });
        btnEnd.addKeyListener(this);
        //JFrame properties
        setSize(800, 400);
        setBackground(Color.BLACK);
        setTitle("Finch Program");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(final String[] args) 
    {
        new MovementUsingKey();
        // Instantiating the Finch object
        myFinch = new Finch();
        myFinch.setLED(255, 0, 0);

        while (endProgram) 
        {
            //Update Finch's Movement
            int leftVelocity = 0;
            int rightVelocity = 0;

            if (upKeyPressed) 
            {
                leftVelocity = 255;
                rightVelocity = 255;
                if (leftKeyPressed && !rightKeyPressed)
                    leftVelocity = 127;
                else if (!leftKeyPressed && rightKeyPressed)
                    rightVelocity = 127;
            } 
            else if (downKeyPressed) 
            {
                leftVelocity = -255;
                rightVelocity = -255;
                if (leftKeyPressed && !rightKeyPressed)
                    leftVelocity = -127;
                else if (!leftKeyPressed && rightKeyPressed)
                    rightVelocity = -127;
            }
            
            myFinch.setWheelVelocities(leftVelocity, rightVelocity);
            myFinch.sleep(10);
        }
        // Always end your program with finch.quit()
        myFinch.quit();
        System.exit(0);
    }

    public void keyTyped(KeyEvent e) 
    {
      //Needed for KeyListener interface
    }

   
    public void keyPressed(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        char keyVal = e.getKeyChar();
        
        //Updates keyPressed variables
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upKeyPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downKeyPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftKeyPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightKeyPressed = true;
                break;
        }
        
        if(keyVal == 'D' || keyVal == 'd')
          dKeyPressed = false;
        if(keyVal == 'S' || keyVal == 's')
          sKeyPressed = false;
        if(keyVal == 'A' || keyVal == 'a')
          aKeyPressed = false;
    }

    
    public void keyReleased(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        char keyVal = e.getKeyChar();
        //Updates keyPressed variables
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upKeyPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downKeyPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftKeyPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightKeyPressed = false;
                break;
        }
        if(keyVal == 'D' || keyVal == 'd')
          dKeyPressed = true;
        if(keyVal == 'S' || keyVal == 's')
          sKeyPressed = true;
        if(keyVal == 'A' || keyVal == 'a')
          aKeyPressed = true;
    }
}


