/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Notifier;

/**
 * Add your docs here.
 */
public class OctoCanumDrive extends Subsystem 
{
  private WPI_TalonSRX leftDrive = new WPI_TalonSRX(RobotMap.driveTalonFL);
	private WPI_TalonSRX leftDriveSlave = new WPI_TalonSRX(RobotMap.driveTalonBL);
	private WPI_TalonSRX rightDrive = new WPI_TalonSRX(RobotMap.driveTalonFR);
	private WPI_TalonSRX rightDriveSlave = new WPI_TalonSRX(RobotMap.driveTalonBR);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public OctoCanumDrive()
  {

  }
  public void setDrives(double x, double y)
  {

  }

  public double getEncPos()
  {
    return 0.0;
  }

  public double getYaw()
  {
    return 0.0;
  }

  public void zeroYaw()
  {

  }

  public void zeroEnc()
  {

  }

  public void rotateTo(double targetAngle)
  {

  }

  public void disableRotateTo()
  {

  }

  public void setPIDValues(double P, double I, double D)
  {

  }

  public void pidDrive(double speed)
  {

  }

  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
