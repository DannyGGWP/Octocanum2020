/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Cannon extends Command 
{
  private double time;

  public Cannon() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.ballShooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    time = Timer.getFPGATimestamp();
    Robot.ballShooter.onWheel();
    Robot.ballShooter.closeGate();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    //order 66
    if(Robot.ballShooter.wheelSpeed() > RobotMap.setPoint - 100) 
    {
      Robot.ballShooter.openGate();
      Robot.elevatorSubsystem.elevatorUp();
  //    time = Timer.getFPGATimestamp();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    if(!OI.cannonButton.get())
    {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.ballShooter.offWheel();
    Robot.elevatorSubsystem.elevatorOff();
    Robot.ballShooter.closeGate();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    Robot.ballShooter.offWheel();
    Robot.elevatorSubsystem.elevatorOff();
    Robot.ballShooter.closeGate();
  }
}
