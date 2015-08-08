package PIDSims;

import java.awt.*;
public class Wave
{
    private int x1,x2,x3;
    private int y1,y2;
    private int wavelength ;
    private int spd=20;
    private static int HEIGHT;
    
    public Wave(int length)
    {
        this.wavelength=length;
        
        HEIGHT = 768;
        y1=(HEIGHT/2);
        
        y2=y1+100;
    }
    
    public void move()
    {
        x2-=spd;
        equalizeX();
    }
    
    public void paint(Graphics g)
    {
        
        g.drawLine(x1, y1, x2,y2);
        g.drawLine(x1,y1,x3,y2);
    }
    
    //getters
    public int getX1(){return x1;}
    public int getX2(){return x2;}
    public int getX3(){return x3;}
    public int getY1(){return y1;}
    public int getY2(){return y2;}
    public int getWavelength(){return this.wavelength;}
    
    //setters
    public void setX1(int i){x1=i;}
    public void setX2(int i){x2=i;}
    public void setX3(int i){x3=i;}
    public void setY1(int i){y1=i;}
    public void setY2(int i){y2=i;}
    public void setWavelength(int i){this.wavelength = i;}
    
    public void equalizeX()
    {
    	x1=x2+wavelength/2;
        x3 =x1+wavelength/2;
    }
}
