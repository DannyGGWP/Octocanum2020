/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class LiftLift extends Subsystem 
{
  private WPI_TalonSRX liftyMotor = new WPI_TalonSRX(RobotMap.elevatorMotor);
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
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
