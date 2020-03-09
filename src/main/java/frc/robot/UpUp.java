/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UpUp extends SubsystemBase 
{
  /**
   * Creates a new UpUp.
   */
  private WPI_TalonSRX climberMotor = new WPI_TalonSRX(RobotMap.climberMotor);
  private Solenoid lockSol = new Solenoid(52, RobotMap.climberSol);

  public UpUp() 
  {
    climberMotor.set(ControlMode.PercentOutput, 0);
  }

  public void armUp()
  {
    Robot.m_oi.blinker.lightOn(-0.87 );
    climberMotor.set(1.0);
   // lockEngage();
  }
  public void armOff()
  {
    climberMotor.set(0.0);
    lockDeengage();
  }
  public void armDown()
  {
    climberMotor.set(-1.0);
    lockDeengage();
  }
  public void lockEngage()
  {
    lockSol.set(true);
  }
  public void lockDeengage()
  {
    lockSol.set(false);
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
