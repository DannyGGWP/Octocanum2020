/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SerialPort.Port;
/**
 * Add your docs here.
 */
public class FasterOctoCanum extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private MecanumDrive m_mecanumDrive;
  private DifferentialDrive m_differentialDrive; 
  private AHRS m_gyro; 
  private WPI_TalonSRX m_frontLeft; 
  private WPI_TalonSRX m_frontRight; 
  private WPI_TalonSRX m_backLeft; 
  private WPI_TalonSRX m_backRight; 
  private SpeedControllerGroup m_leftSideDifferentialGroup; 
  private SpeedControllerGroup m_rightSideDifferentialGroup; 
  private Boolean m_inMecanumDrive = true; 
  private Boolean m_driveStraight = false;
  private double m_angleSetPoint = 0.0; 
  private static final double c_kPcorrection = 0.2; 
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public FasterOctoCanum()
  {
    m_frontLeft = new WPI_TalonSRX(5);
    m_frontRight = new WPI_TalonSRX(2);
    m_backLeft = new WPI_TalonSRX(3); 
    m_backRight = new WPI_TalonSRX(1); 
    m_leftSideDifferentialGroup = new SpeedControllerGroup(m_frontLeft, m_backLeft);
    m_rightSideDifferentialGroup = new SpeedControllerGroup(m_frontRight, m_backRight);
    m_gyro = new AHRS(Port.kMXP);
    m_mecanumDrive = new MecanumDrive(m_frontLeft, m_backLeft, m_frontRight, m_backRight);
    m_differentialDrive = new DifferentialDrive(m_leftSideDifferentialGroup, m_rightSideDifferentialGroup);
    m_mecanumDrive.setDeadband(RobotMap.c_deadBand);
    m_differentialDrive.setDeadband(RobotMap.c_deadBand);
  }
  public void enableDriveStraight()
  {
    m_driveStraight = true; 
  }
  public void disableDriveStraight()
  {
    m_driveStraight = false; 
  }
  public void drive(double x, double y, double rotation)
  {
    double error = 0.0; 
    double currentHeading = -m_gyro.getAngle(); 
    if (m_driveStraight)
    {
      error = m_angleSetPoint - currentHeading;
      if (Math.abs(error) < 2.0)
      {
        error = 0.0; 
      }

    }
    if (m_inMecanumDrive){
      // Only compensate for drift if NOT turning. 
      if (m_driveStraight && Math.abs(rotation) < RobotMap.c_deadBand)
      {
        rotation = error*c_kPcorrection;
      }
      // We are doing a Turn and want to keep updating the Angle Set Point 
      else if (m_driveStraight)
      {
        m_angleSetPoint = currentHeading; 
      }
      m_mecanumDrive.driveCartesian(-x, y, rotation, currentHeading);
    }
    else {
      m_differentialDrive.arcadeDrive(-x, y);
    }
  }
}
