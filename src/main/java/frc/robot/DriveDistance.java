/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDistance extends CommandBase {
  
  private double distance;
  private FasterOctoCanum driveTrain;
  private double currentEncCount;
  private boolean m_finished;
  private double time;
  
  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(double distance, FasterOctoCanum driveTrain) 
  {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    this.distance = distance;
    this.driveTrain = driveTrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    currentEncCount = driveTrain.getEncPos();
    m_finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    driveTrain.enableTank();
    driveTrain.drive(0, -0.6, 0, 0);
    if(driveTrain.getEncPos() > currentEncCount + distance)
    {
      driveTrain.drive(0, 0, 0, 0);
    }
    else
    {
      time = Timer.getFPGATimestamp();
    }
    if(time > Timer.getFPGATimestamp() + 1)
    {
      m_finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    driveTrain.drive(0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return m_finished;
  }
}
