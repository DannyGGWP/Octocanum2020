/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinToColor extends Command 
{
  private String targetColor;
  private int buffer;
  private int bufferMax;

  public SpinToColor() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.colorWheel);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    bufferMax = 0;
    buffer = bufferMax;
    Robot.colorWheel.onWheel();
    Robot.colorWheel.countColors();
    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    if(gameData.length() > 0)
    {
      switch(gameData.charAt(0))
      {
        case 'B':
          targetColor = "Blue";
          break;
        case 'R':
          targetColor = "Red";
          break;
        case 'G':
          targetColor = "Green";
          break;
        case 'Y':
          targetColor = "Yellow";
          break;
        default:
          targetColor = Robot.colorWheel.stringColor();
          break;
      }
    }
    SmartDashboard.putString("Target Color", targetColor);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(targetColor == Robot.colorWheel.stringColor())
    {
      buffer--;
    }
    else
    {
      buffer = bufferMax;
    }
    SmartDashboard.putString("Seen Color", Robot.colorWheel.stringColor());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    if(buffer < 0)
    {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.colorWheel.offWheel();
    Robot.colorWheel.resetCount();
  
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    Robot.colorWheel.offWheel();
    Robot.colorWheel.resetCount();
  
  }
}
