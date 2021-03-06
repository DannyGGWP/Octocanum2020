/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class StrafeTest extends CommandBase 
{
  private FasterOctoCanum driveTrain;
  private double frontEncCount;
  private double distance;
  private boolean m_finished;

  /**
   * Creates a new StrafeTest.
   */
  public StrafeTest(double distance, FasterOctoCanum driveTrain) 
  {
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    this.distance = distance;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    frontEncCount = driveTrain.getEncPosFront();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    driveTrain.drive(0.5, 0, 0, 0);
    if(driveTrain.getEncPosFront() > frontEncCount + distance)
    {
      driveTrain.drive(0, 0, 0, 0);
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
