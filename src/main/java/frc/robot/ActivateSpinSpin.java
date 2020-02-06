/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ActivateSpinSpin extends CommandBase
{
  public ActivateSpinSpin() 
  {
    addRequirements(Robot.colorWheel);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() 
  {
    Robot.colorWheel.onWheel();
    Robot.colorWheel.resetCount();
    System.out.println("bitch it works");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() 
  {
    Robot.colorWheel.countColors();
    SmartDashboard.putNumber("Rotations", Robot.colorWheel.getCount("Red"));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() 
  {
    if(Robot.colorWheel.getCount("Red") > 6 )
    {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted)
  {
    Robot.colorWheel.offWheel();
    
  }
}
