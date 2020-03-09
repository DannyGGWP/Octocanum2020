/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinToColor extends CommandBase
{
  private String targetColor;
  private int buffer;
  private int bufferMax;
  private SpinSpin colorWheel; 
  public SpinToColor(SpinSpin wheel) 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    colorWheel = wheel; 
    addRequirements(colorWheel);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() 
  {
    bufferMax = 0;
    buffer = bufferMax;
    colorWheel.slowWheel();
    colorWheel.countColors();
    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    if(!gameData.isBlank() && gameData.length() > 0)
    {
      switch(gameData.charAt(0))
      {
        case 'B':
          targetColor = "Red";
          Robot.m_oi.blinker.lightOn(.61);
          break;
        case 'R':
          targetColor = "Blue";
          Robot.m_oi.blinker.lightOn(.87);
          break;
        case 'G':
          targetColor = "Yellow";
          Robot.m_oi.blinker.lightOn(.69);
          break;
        case 'Y':
          targetColor = "Green";
          Robot.m_oi.blinker.lightOn(.71);
          break;
        default:
          targetColor = colorWheel.stringColor();
          break;
      }
    }
    else
    {
      targetColor = colorWheel.stringColor();
    }
    SmartDashboard.putString("Target Color", targetColor);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() 
  {
    if(targetColor == colorWheel.stringColor())
    {
      buffer--;
    }
    else
    {
      buffer = bufferMax;
    }
    SmartDashboard.putString("Seen Color", colorWheel.stringColor());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() 
  {
    if(buffer < 0)
    {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) 
  {
    Robot.m_oi.blinker.lightOn(.67);
    colorWheel.offWheel();
    colorWheel.resetCount();
  
  }
}
