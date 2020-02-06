/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoCenter extends CommandBase
{
  private double time;
  private State currentState;
  private enum State
  {
    start,
    moveToGoal,
    shoot,
    finished
    
  };
    

  public AutoCenter() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    addRequirements(Robot.ballShooter);
    addRequirements(Robot.driveTrain);
    addRequirements(Robot.elevatorSubsystem);
    
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() 
  {
    currentState = State.start;
    time = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() 
  {
    switch(currentState)
    {
      case start:
        currentState = State.moveToGoal;
        break;
      case moveToGoal:
        Robot.driveTrain.enableTank();
        Robot.driveTrain.drive(.2, .2, 0, 0);
        if(Robot.driveTrain.getEncPos() > 10000)
        {
          Robot.driveTrain.drive(0, 0, 0, 0);
          currentState = State.shoot;
        }
        break;
      case shoot:
        Robot.ballShooter.onWheel();
        if(Robot.ballShooter.wheelSpeed() > RobotMap.setPoint - 100) 
        {
          Robot.ballShooter.openGate();
          Robot.elevatorSubsystem.elevatorUp();
          time = Timer.getFPGATimestamp();
          if(Timer.getFPGATimestamp() > time + 5)
          {
            Robot.ballShooter.offWheel();
            Robot.elevatorSubsystem.elevatorOff();
            Robot.ballShooter.closeGate();
            currentState = State.finished;
          }
        }
        break;
       default:
        break;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() 
  {
    if(currentState == State.finished) 
    {
      return true;
    }
    return false;

  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) 
  {
    Robot.ballShooter.offWheel();
    Robot.elevatorSubsystem.elevatorOff();
    Robot.ballShooter.closeGate();
    Robot.driveTrain.drive(0, 0, 0, 0);
  }

}