/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * Add your docs here.
 */
public class FasterOctoCanum extends SubsystemBase 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private MecanumDrive m_mecanumDrive;
  private DifferentialDrive m_differentialDrive; 
  public AHRS m_gyro; 
  public WPI_TalonFX m_frontLeft; 
  public WPI_TalonFX m_frontRight; 
  public WPI_TalonFX m_backLeft; 
  public WPI_TalonFX m_backRight; 
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
  Faults _faults = new Faults();

  public static Solenoid solenoid = new Solenoid(52, RobotMap.driveSol);


  public enum DriveMode
	{
    fieldMechanum,
    robotMechanum,
    tank
  };


  public FasterOctoCanum()
  {
    m_frontLeft = new WPI_TalonFX(RobotMap.driveTalonFL);
    m_frontRight = new WPI_TalonFX(RobotMap.driveTalonFR);
    m_backLeft = new WPI_TalonFX(RobotMap.driveTalonBL); 
    m_backRight = new WPI_TalonFX(RobotMap.driveTalonBR); 

    m_frontRight.setSensorPhase(false);
    m_backRight.setSensorPhase(false);
    m_frontLeft.setSensorPhase(false);
    m_backLeft.setSensorPhase(false);
    m_frontRight.setSelectedSensorPosition(0);
    m_backRight.setSelectedSensorPosition(0);
    m_frontLeft.setSelectedSensorPosition(0);
    m_backLeft.setSelectedSensorPosition(0);
    /*
    m_frontLeft.setSensorPhase(true);
    m_backLeft.setInverted(true);
    m_frontRight.setInverted(false);
    m_backRight.setInverted(false);
    */
    /*
    m_frontRight.setSensorPhase(false);
    m_backRight.setSensorPhase(false);
    m_frontLeft.setSensorPhase(true);
    m_backLeft.setSensorPhase(true);
    */

    System.out.println("Out Of Phase:" + _faults.SensorOutOfPhase);
    
    //m_backLeft.setInverted(InvertType.InvertMotorOutput);
    //m_frontRight.setInverted(InvertType.InvertMotorOutput);
    m_leftSideDifferentialGroup = new SpeedControllerGroup(m_frontLeft, m_backLeft);
    m_rightSideDifferentialGroup = new SpeedControllerGroup(m_frontRight, m_backRight);
    m_gyro = new AHRS(Port.kMXP);
    
    //m_differentialDrive = new DifferentialDrive(m_leftSideDifferentialGroup, m_rightSideDifferentialGroup);
    //m_mecanumDrive.setDeadband(RobotMap.c_deadBand);
    //m_differentialDrive.setDeadband(RobotMap.c_deadBand);
    m_driveState = DriveMode.fieldMechanum;
    initMechanum();
    initTank();
    disableTank();
    //m_mecanumDrive.setSafetyEnabled(false);
  }

  public void initMechanum()
  {
    m_mecanumDrive = new MecanumDrive(m_frontLeft, m_backLeft, m_frontRight, m_backRight);
    m_mecanumDrive.setDeadband(RobotMap.c_deadBand);
  }
  public void initTank()
  {
    m_differentialDrive = new DifferentialDrive(m_leftSideDifferentialGroup, m_rightSideDifferentialGroup);
    m_differentialDrive.setDeadband(RobotMap.c_deadBand);
  }
  public void resetGyro()
  {
    m_gyro.reset();
  }
  
  public void resetEncoders()
  {
    m_frontRight.setSelectedSensorPosition(0);
    m_backRight.setSelectedSensorPosition(0);
    m_frontLeft.setSelectedSensorPosition(0);
    m_backLeft.setSelectedSensorPosition(0);
  }
  public double getEncPos()
  {
    int frontLeftEnc = -m_frontLeft.getSelectedSensorPosition(0);
    int frontRightEnc = m_frontRight.getSelectedSensorPosition(0);
    int backLeftEnc = -m_backLeft.getSelectedSensorPosition(0);
    int backRightEnc = m_backRight.getSelectedSensorPosition(0);
    SmartDashboard.putNumber("Front Left Enc", frontLeftEnc);
    SmartDashboard.putNumber("Front Right Enc", frontRightEnc);
    SmartDashboard.putNumber("Back Left Enc", backLeftEnc);
    SmartDashboard.putNumber("Back Right Enc", backRightEnc);
    double position = ((frontLeftEnc + frontRightEnc + backLeftEnc + backRightEnc) / 4);

    return position;
  }
  public double getHeading()
  {
    return Math.IEEEremainder(m_gyro.getAngle(), 360);
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
    m_driveState = DriveMode.fieldMechanum;
  }

  public void disableFieldOriented()
  {
    m_driveState = DriveMode.robotMechanum;
  }
  public void toggleTank()
  {
    if (m_driveState == DriveMode.tank)
    {
      disableTank();
    }
    else 
    {
      enableTank();
    }
  }
  public void enableTank()
  {
    m_previousMode = m_driveState; 
    m_driveState = DriveMode.tank;
    m_mecanumDrive.setSafetyEnabled(false);
    m_differentialDrive.setSafetyEnabled(true); 
    //m_mecanumDrive = new MecanumDrive(null, null, null, null); 
    solenoid.set(true);
  }

  public void disableTank()
  {
    m_driveState = DriveMode.robotMechanum;
    //m_differentialDrive = new DifferentialDrive(null, null); 
    m_differentialDrive.setSafetyEnabled(false);
    m_mecanumDrive.setSafetyEnabled(true);
    solenoid.set(false);
  } 

  public DriveMode getMode()
  {
     return m_driveState;
  }

  /**
   * @param x left drive speed
   * @param y right drive speed
   * @param rotation used in mechanam to pivot a speed
   */
  public void drive(double x, double y, double rotation, double tankY)
  {
    double error = 0.0; 
    
    SmartDashboard.putNumber("Heading", Math.IEEEremainder(m_gyro.getAngle(), 360));
    
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
          if (m_mecanumDrive != null){
            m_mecanumDrive.driveCartesian(-x, y, -rotation, -m_gyro.getAngle());
          }
          
          break;
      case robotMechanum:

          if (m_mecanumDrive != null){
            m_mecanumDrive.driveCartesian(-x, y, -rotation);
          }
          break;
      case tank:
      // Y and X are flipped intentionally 
      if (m_differentialDrive != null)
      {
        m_differentialDrive.arcadeDrive(y, x);
      }
        if (m_driveStraight && Math.abs(rotation) < RobotMap.c_deadBand)
        {
          rotation = error*c_kPcorrection;
        }
        // We are doing a Turn and want to keep updating the Angle Set Point 
        else if (m_driveStraight)
        {
          m_angleSetPoint = currentHeading; 
        }
        break;
      default:
        //m_differentialDrive.arcadeDrive(0, 0);
        //m_mecanumDrive.driveCartesian(0, 0, 0);
      break;
    }
  }
  
}
