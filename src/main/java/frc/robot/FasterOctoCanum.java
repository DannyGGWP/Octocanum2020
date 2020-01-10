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
public class FasterOctoCanum extends Subsystem 
{
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

  private Boolean m_inMecanumDriveField = true; 
  private Boolean m_inMecanumDriveRobot = true; 
  private Boolean m_inTankDrive = true; 

  private Boolean m_driveStraight = false;
  private double m_angleSetPoint = 0.0; 
  private static final double c_kPcorrection = 0.05; 

  private DriveMode m_previousMode;
  public DriveMode m_driveState;
  public enum DriveMode
	{
    fieldMechanum,
    robotMechanum,
    tank
  };

  @Override

  public void initDefaultCommand() 
  {
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
    m_driveState = fieldMechanum;
  }

  public void enableDropDrive()
  {
    m_inMecanumDrive = false; 
  }
  public void disableDropDrive()
  {
    m_inMecanumDrive = true; 
  }
  public void enableDriveStraight()
  {
    m_driveStraight = true; 
    m_angleSetPoint = m_gyro.getYaw(); 
  }
  public void disableDriveStraight()
  {
    m_driveStraight = false; 
  }

  private void setMode(DriveMode mode)
  {
   /* if(mode != m_driveState)
    {
      m_previousMode = m_driveState;
      m_driveState = mode;
      if(m_driveState = tank)
      {

      }
    }*/
    m_driveState = mode;
  }

  public void enableFieldOriented()
  {
    m_driveState = fieldMechanum;
  }
  public void disableFieldOriented()
  {
    m_driveState = robotMechanum;
  }
  public void enableTank()
  {
    m_previousMode = m_driveState; 
    m_driveState = tank;
  }
  public void disableTank()
  {
    m_driveState = m_previousMode;
  } 

  public DriveMode getMode()
  {
     return m_driveState;
  }

  public void drive(double x, double y, double rotation)
  {
    double error = 0.0; 
    double currentHeading = m_gyro.getYaw(); 
    if (m_driveStraight)
    {
      error = m_angleSetPoint - currentHeading;
      if (Math.abs(error) < 2.0)
      {
        error = 0.0; 
      }
    }
    /*if (m_inMecanumDrive)
    {
      // Only compensate for drift if NOT turning. 
      m_mecanumDrive.driveCartesian(-x, y, rotation, -m_gyro.getAngle());
    }
    else
    {
    */

  switch(m_driveState)
    {
      case fieldMechanum:
          m_driveState= m_mecanumDrive.driveCartesian(-x, y, rotation, -m_gyro.getAngle());
          m_driveState = state;
          break;
      case robotMechanum:
          currentDrive = m_mecanumDrive.driveCartesian(-x, y, rotation); 
          m_driveState = state;
          break;
      case tank:
          currentDrive = m_differentialDrive.arcadeDrive(-x, y);
        if (m_driveStraight && Math.abs(rotation) < RobotMap.c_deadBand)
        {
          rotation = error*c_kPcorrection;
        }
        // We are doing a Turn and want to keep updating the Angle Set Point 
        else if (m_driveStraight)
        {
          m_angleSetPoint = currentHeading; 
        }
          m_driveState = state;
        break;
      default:
      m_differentialDrive.arcadeDrive(0, 0);
      m_mecanumDrive.driveCartesian(0, 0, 0);
      break;
    }
  }
  
}
