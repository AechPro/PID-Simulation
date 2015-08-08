package PIDSims;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PIDsim extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 2L;
	
	private Timer timer;
	private Wave[] waves;
	private PID controller;
	private int goal = 100000;
    private int maxWaves = 1000;
    private int curWaves=14;
    private int wavelength;
    private static int WIDTH, HEIGHT;
    private static long prevTime = System.nanoTime();
    private static long passedTime;
    
    public PIDsim()
    {
    	this.setFocusable(true);
        this.setDoubleBuffered(true);
        
        WIDTH = 1280;
        HEIGHT = 768;
        wavelength=10;
        timer = new Timer(16, this);
        waves = new  Wave[maxWaves];
        controller = new PID();
        initializeWaves();
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
    		
    	for(int i=0;i<curWaves;i++)
    	{
    		if(wavelength!=goal)
        	{
        		PIDcontrol();
        	}
	        waves[i].move();
	        
	        if(waves[i].getX3() <=0)
	        {
	        	/*if(wavelength<400){wavelength+=40;}
	        	else if(wavelength>=400){wavelength=100;}*/
	        	//wavelength = (int) ((float)Math.random()*300.0f)+10;
	        	//wavelength = 10;
	            waves[i] = new Wave(wavelength);
	            
	            //waves[i].setY1((int) ((float)Math.random()*450.0f));
	            
	            if(i>0)
	            {
	            	waves[i].setX2(waves[i-1].getX3()+1);
	            	waves[i].equalizeX();
	            }
	            else
	            {
	            	waves[i].setX2(waves[curWaves-1].getX3()+1);
	            	waves[i].equalizeX();
	            }
	        }
    	}
    	repaint();
    }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	for(int i=0;i<curWaves;i++)
    	{
    		waves[i].paint(g);
    	}
    }
    
    public void initializeWaves()
    {
    	//wavelength = (int) ((float)Math.random()*300.0f)+10;
    	//wavelength = 10;
    	
        for(int i=0;i<curWaves;i++)
        {
        	if(wavelength<goal)
        	{
        		PIDcontrol();
        	}
            waves[i] = new Wave(wavelength);
            //waves[i].setY1((int) ((float)Math.random()*450.0f));
            if(i>0)
            {
            	waves[i].setX2(waves[i-1].getX3()+1);
            	waves[i].equalizeX();
            }
            
            else
            {
            	waves[i].setX2(1400);
            	waves[i].equalizeX();
            }
        }
    }
    
    public void PIDcontrol()
    {
		passedTime = System.nanoTime() - prevTime;
        if(passedTime/1000000000 >= 1)
        {
        	prevTime = System.nanoTime();
        	
        	controller.Setpoint = goal;
        	controller.Compute((controller.Setpoint - wavelength), passedTime/1000000000);
        	controller.Input = controller.Output;
        	wavelength+=(int) controller.Output;
        	
        	System.out.println("input: "+controller.Input+" wavelength: "+wavelength+" setpoint: "+controller.Setpoint);
		}
    }
    
    public static void main(String[] args)
    {
        JFrame f = new JFrame("PIDsim");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PIDsim window = new PIDsim();
        f.add(window); 
        f.setSize(WIDTH,HEIGHT);
        f.setVisible(true);
        window.timer.start();
    }
}
