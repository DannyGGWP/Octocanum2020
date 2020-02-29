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
  private ShootShoot ballShooter;
  private FasterOctoCanum driveTrain;
  private LiftLift elevatorSubsystem;
  private double currentEncCount; 
  private enum State
  {
    start,
    moveToGoal,
    shoot,
    strafe,
    finished
    
  };
    

  public AutoCenter(ShootShoot shooter, FasterOctoCanum drive, LiftLift elevator) 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    ballShooter = shooter; 
    driveTrain = drive; 
    elevatorSubsystem = elevator; 
    addRequirements(ballShooter);
    addRequirements(driveTrain);
    addRequirements(elevatorSubsystem);
    
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
        currentEncCount = driveTrain.getEncPos();
        break;
      case moveToGoal:
        driveTrain.enableTank();
        driveTrain.drive(0, -0.6, 0, 0);
        if(driveTrain.getEncPos() > currentEncCount + 52000)
        {
          driveTrain.drive(0, 0, 0, 0);
          currentState = State.shoot;
        }
        break;
        case shoot:
        ballShooter.onWheel();
        if(ballShooter.wheelSpeed() > RobotMap.setPoint - 100) 
        {
          ballShooter.openGate();
          //elevatorSubsystem.elevatorUp();
          if(Timer.getFPGATimestamp() > time + 1.2)
          {
            ballShooter.offWheel();
            //elevatorSubsystem.elevatorOff();
            ballShooter.closeGate();
            currentState = State.strafe;
          }
        }
        else 
        {
          time = Timer.getFPGATimestamp();
        }
        break;
        case strafe:
        driveTrain.disableTank();
        driveTrain.resetEncoders();
     //   if(Timer.getFPGATimestamp() > time + 1.2)
       // {
        driveTrain.drive(-0.6, 0, 0, 0);
        if(driveTrain.getEncPos() > currentEncCount + 50000)
        {
          driveTrain.drive(0, 0, 0, 0);
          currentState = State.finished;
   //     }
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
    ballShooter.offWheel();
    elevatorSubsystem.elevatorOff();
    ballShooter.closeGate();
    driveTrain.drive(0, 0, 0, 0);
  }

}
