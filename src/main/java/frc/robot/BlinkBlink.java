/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BlinkBlink extends SubsystemBase {
  /**
   * Creates a new BlinkBlink.
   */
  private PWMSparkMax abeBlinkin = new PWMSparkMax(0);
  private double colorValue;
  public BlinkBlink() {
    colorValue = .67;
  }
  //set to rio sparkvalue
  public void lightOn(double colorValue)
  {
  //  if(lightOff()!=true)
    //{
    this.colorValue = colorValue;
    //}
  }

  public boolean lightOff()
  {
    abeBlinkin.set(0);
    return true;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    abeBlinkin.set(colorValue);
  }
}
