/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Cannon extends CommandBase
{
  private double time;
  private ShootShoot ballShooter;
  private LiftLift elevatorSubsystem;  
  public Cannon(ShootShoot shooter, LiftLift elevator) 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    ballShooter = shooter;
    elevatorSubsystem = elevator; 
    addRequirements(ballShooter);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() 
  {
    time = Timer.getFPGATimestamp();
    ballShooter.onWheel();
    ballShooter.closeGate();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() 
  {
    //order 66
    if(ballShooter.wheelSpeed() > RobotMap.setPoint - 100) 
    {
      ballShooter.openGate();
      elevatorSubsystem.elevatorUp();
  //    time = Timer.getFPGATimestamp();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() 
  {
    if(!OI.cannonButton.get())
    {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) 
  {
    ballShooter.offWheel();
    elevatorSubsystem.elevatorOff();
    ballShooter.closeGate();
  }
}
