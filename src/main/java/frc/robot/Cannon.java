/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Cannon extends Command {

  private boolean m_finished;
  private double time;

  public Cannon() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.ballShooter);

    time = Timer.getFPGATimestamp();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    m_finished = false;
    Robot.ballShooter.offWheel();
    Robot.ballShooter.closeGate();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //order 66
    Robot.ballShooter.onWheel();

    if(Timer.getFPGATimestamp() > time + 1) {
      Robot.ballShooter.openGate();
      time = Timer.getFPGATimestamp();
    }

    if(Timer.getFPGATimestamp() > time + 1) {
      Robot.ballShooter.offWheel();
      Robot.ballShooter.closeGate();
      time = Timer.getFPGATimestamp();
      m_finished = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return m_finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.ballShooter.offWheel();
    Robot.ballShooter.closeGate();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.ballShooter.offWheel();
    Robot.ballShooter.closeGate();
  }
}
