/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.Color;

import com.mach.LightDrive.LightDriveCAN;

import edu.wpi.first.wpilibj2.command.SubsystemBase; 

public class LightLight extends SubsystemBase 
{
  /**
   * Creates a new LightLight.
   */
  public LightDriveCAN ld_can = new LightDriveCAN();

  int counter = 0;

//  public Servo servo1 = new Servo(0);
//	public Servo servo2 = new Servo(1);

  public LightLight() 
  {
	ld_can.Update();
  }
  public void setLights()
  {
    //Set colors on CAN LightDrive, also set brightness (optional)
    ld_can.SetColor(1, Color.BLUE);
	ld_can.SetColor(2, Color.GREEN);
	ld_can.SetColor(3, Color.RED);
    ld_can.SetColor(4, Color.YELLOW);
    //Send these colors to CAN LightDrive
    ld_can.Update();
    
    counter = 0;
  }
  public void rotateColor()
  {
    	if(counter++ < 100)
		{
			ld_can.SetColor(1, Color.RED);		
		}
		else if(counter < 200)
		{
			ld_can.SetColor(1, Color.GREEN);	
		}
		else if(counter < 400)
		{
			ld_can.SetColor(1, Color.BLUE);	
		}
		else if(counter < 600)
		{
			ld_can.SetColor(1, Color.YELLOW);	
		}
		else if(counter < 800)
		{
			ld_can.SetColor(1, Color.MAGENTA);
		}
		else if(counter < 1000)
		{
			ld_can.SetColor(1, Color.CYAN);
		}
		else if(counter < 1200)
		{
			ld_can.SetColor(1, Color.WHITE);
		}
		else if(counter < 1400)
		{
			ld_can.SetColor(1, Color.BLACK);
		}
		else if(counter > 1600)
		{
			counter = 0;
		}
		
		//Send latest color settings to CAN LightDrive
		ld_can.Update();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
