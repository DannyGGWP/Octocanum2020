 /*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class ShootShoot extends SubsystemBase
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands
  private CANSparkMax pewPewMotor;
  private CANPIDController pIDController;
  private CANEncoder canEncoder;
  public double kP,kI,kD,kIZ, kFF,kMaxOutput, kMinOutput, kMaxRPM;
  private static Solenoid gateSolenoid = new Solenoid(52,RobotMap.dropSol);

  public ShootShoot()
  {
    pewPewMotor = new CANSparkMax(RobotMap.shooterSpark, MotorType.kBrushless);
    gateSolenoid.set(false);
    pewPewMotor.setInverted(true);
    pIDController = pewPewMotor.getPIDController();
    canEncoder = pewPewMotor.getEncoder();
    kP = 5e-5;
    kI = 3e-7;
    kD = 0.008;
    kIZ = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput =-1;
    kMaxRPM = 5700;
    pIDController.setP(kP);
    pIDController.setI(kI);
    pIDController.setD(kD);
    pIDController.setIZone(kIZ);
    pIDController.setFF(kFF);
    pIDController.setOutputRange(kMinOutput, kMaxOutput);

  }

  public void onWheel()
  {
    pIDController.setReference(RobotMap.setPoint, ControlType.kVelocity);
  }
  
  public void offWheel()
  {
    //PpIDController.setReference(0, ControlType.kVelocity);
    pewPewMotor.stopMotor();
  }

  public void openGate()
  {
    gateSolenoid.set(true);
  }

  public void closeGate()
  {
    gateSolenoid.set(false);
  }

  public double wheelSpeed()
  {
    return canEncoder.getVelocity();
  }

}
