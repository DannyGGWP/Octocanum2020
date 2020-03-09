/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.Color;

//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.mach.LightDrive.LightDriveCAN;

public class Cannon extends CommandBase
{
  //private double time;
  private ShootShoot ballShooter;
  private LiftLift elevatorSubsystem;
  public static LightDriveCAN m_lightDrive = new LightDriveCAN(); 

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
    //time = Timer.getFPGATimestamp();
    ballShooter.onWheel();
    ballShooter.closeGate();
    Robot.m_oi.blinker.lightOn(-.39);
/*    m_lightDrive.SetColor(1, Color.CYAN);
    m_lightDrive.SetColor(2, Color.CYAN);
    m_lightDrive.SetColor(3, Color.CYAN);
    m_lightDrive.SetColor(4, Color.CYAN);
    //m_lightDrive.Update();
    */
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() 
  {
    //order 66

    if(ballShooter.wheelSpeed() > RobotMap.setPoint - 100) 
    {
      /*
      m_lightDrive.SetColor(1, Color.GREEN);
      m_lightDrive.SetColor(2, Color.GREEN);
      m_lightDrive.SetColor(3, Color.GREEN);
      m_lightDrive.SetColor(4, Color.GREEN);
      //m_lightDrive.Update();
      */
      Robot.m_oi.blinker.lightOn(-.39);
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
      Robot.m_oi.blinker.lightOn(.67);
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
    Robot.m_oi.blinker.lightOn(.67);
  }
}
