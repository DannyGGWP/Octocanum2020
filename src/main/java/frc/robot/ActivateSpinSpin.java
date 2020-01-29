/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActivateSpinSpin extends Command 
{
  public ActivateSpinSpin() 
  {
    requires(Robot.colorWheel);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    Robot.colorWheel.spinWheel();
    Robot.colorWheel.resetCount();
    System.out.println("bitch it works");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.colorWheel.countColors();
    SmartDashboard.putNumber("Rotations", Robot.colorWheel.getCount("Red"));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    if(Robot.colorWheel.getCount("Red") > 7 )
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
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    Robot.colorWheel.offWheel();
  }
}
