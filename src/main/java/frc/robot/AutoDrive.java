
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.FasterOctoCanum;

public class AutoDrive extends CommandBase {
  /**
   * Creates a new AutoDrive.
   */
  private double m_x; 
  private double m_y; 
  private double m_distance; 
  private FasterOctoCanum m_drive; 
  //private double currentEncCount;
  private double frontEncCount;
  private boolean m_finished;
  public AutoDrive(double y, double x, double distance, FasterOctoCanum drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    m_x = x;
    m_y = y; 
    m_distance = distance; 
    m_drive = drive; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    frontEncCount = m_drive.getEncPosFront();
    m_finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.enableTank();
    m_drive.drive(m_x, m_y, 0, 0);
    if(m_drive.getEncPosFront() > frontEncCount + m_distance)
    {
      m_drive.drive(0, 0, 0, 0);
      m_finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.drive(0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_finished;
  }
}
