/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ShootShoot extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands

  private final CANSparkMax pewPewMotor;
  private static Solenoid gateSolenoid = new Solenoid(51,RobotMap.dropSol);

  public ShootShoot()
  {
    pewPewMotor = new CANSparkMax(RobotMap.shooterSpark, MotorType.kBrushless);
    gateSolenoid.set(false);
  }

  public void onWheel()
  {
    pewPewMotor.set(1.0);
  }
  
  public void offWheel()
  {
    pewPewMotor.set(0.0);
  }

  public void openGate()
  {
    gateSolenoid.set(true);
  }

  public void closeGate()
  {
    gateSolenoid.set(false);
  }

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
