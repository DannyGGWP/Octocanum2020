/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * Add your docs here.
 */
public class OI 
{
    public static Joystick driveJoystick = new Joystick(1);
    public static Button mechanumSwitch = new Joystick(RobotMap.back);
    public static Button tankDrop = new Joystick(RobotMap.leftTrigger);

}
