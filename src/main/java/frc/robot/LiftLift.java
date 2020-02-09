/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class LiftLift extends SubsystemBase
{
  private WPI_TalonSRX liftyMotor = new WPI_TalonSRX(RobotMap.elevatorMotor);
  private WPI_TalonSRX succMotor = new WPI_TalonSRX(RobotMap.succMotor);
  private static Solenoid succSolenoid = new Solenoid(52,RobotMap.succSol);
  public boolean succIntakeState = false;
  public boolean succOuttakeState = false;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public LiftLift()
  {
    liftyMotor.set(ControlMode.PercentOutput,0);
  }

  public void elevatorUp()
  {
    liftyMotor.set(-1.0);
  }

  public void elevatorDown()
  {
    liftyMotor.set(1.0);
  }
  public void elevatorOff()
  {
    liftyMotor.set(0.0);
  }
  
//Start SuccSucc

  public void succSuccIntake()
  {
    succMotor.set(-0.8);
    succIntakeState = true;
    succOuttakeState = false;
  }

  public void succSuccOuttake()
  {
    succMotor.set(0.8);
    succIntakeState = false;
    succOuttakeState = true;
  }
  
  public void succSuccOff()
  {
    succMotor.set(0.0);
    succIntakeState = false;
    succOuttakeState = false;
  }

  public boolean isSuccSuccIntake()
  {
    return succIntakeState;
  }

  public boolean isSuccSuccOuttake()
  {
    return succOuttakeState;
  }
  
  public void succSolExtend()
  {
    succSolenoid.set(true);
  }
  
  public void succSolRetract()
  {
    succSolenoid.set(false);
  }
  
  public void intake()
  {
    succSolExtend();
    succSuccIntake();
    elevatorUp();
  }

  public void outtake()
  {
    succSolRetract();
    succSuccOuttake();
    elevatorDown();
  }

  public void offTake()
  {
    succSolRetract();
    succSuccOff();
    elevatorOff();
  }
}
