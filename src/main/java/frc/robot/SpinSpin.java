/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Add your docs here.
 */
public class SpinSpin extends Subsystem {
  private ColorSensorV3 m_colorSensor;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public SpinSpin()
  {
    m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
  }

  public Color getColor()
  {
    return m_colorSensor.getColor();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
