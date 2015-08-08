package PIDSims;
public class PID {
	
	/*working variables*/
	static long lastTime = System.nanoTime()/1000000000;
	static double Input=10, Output, Setpoint=1000;
	static double integral, lastErr;
	static double kp, ki, kd;
	static double system=100;
	static double derivative;
	static long now;
	
	public PID() 
	{
		SetTunings(1D,0.2D,0.1D);
	}
	
	void Compute(double error, float dt)
	   {
	        // Compute proportional component
	        Output = error * kp;
	 
	        // Compute derivative component if time has elapsed
	        if (kd > 0 && dt > 0)
	        {
                derivative = (error - lastErr) / dt;
 
                // update state
                lastErr = error;
 
                // add in derivative component
                Output    += kd * derivative;
	        }
	 
	        // Compute integral component if time has elapsed
	        if (ki > 0 && dt > 0) 
	        {
                integral += (error * ki) * dt;
                Output    += integral;
	        }
	 
	      // System.out.println("proportional: "+error*kp+" integral: "+integral+" derivative: "+derivative);
	   }
	
	/*public static void Compute()
	{
	   /*How long since we last calculated
	   now = System.nanoTime()/1000000000;
	   double timeChange = (double)(now - lastTime);
	  
	   /*Compute all the working error variables
	   double error = Setpoint - Input;
	   errSum += (error * timeChange);
	   if(timeChange>0){dErr = (error - lastErr) / timeChange;}
	  
	   /*Compute PID Output
	   Output = (kp * error) + (ki * errSum) + (kd * dErr);
	  
	   /*Remember some variables for next time
	   lastErr = error;
	   lastTime = now;
	   //System.out.println("now: "+now+" last time: "+lastTime+" timeChange: "+timeChange);
	}*/
	  
	static void SetTunings(double Kp, double Ki, double Kd)
	{
	   kp = Kp;
	   ki = Ki;
	   kd = Kd;
	}
	
	public void setGoal(int i){Setpoint = i;}
	public void setInput(int i){Input = i;}
	
	
}
