/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import com.ctre.phoenix.ButtonMonitor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Add your docs here.
 */
public class OI 
{
    public static Joystick driveJoystick = new Joystick(1);
    public static JoystickButton mechanumSwitch = new JoystickButton(driveJoystick,RobotMap.back);
    public static JoystickButton tankDrop = new JoystickButton(driveJoystick,RobotMap.leftTrigger);
    public static JoystickButton cannonButton = new JoystickButton(driveJoystick,RobotMap.buttonA);
    public static JoystickButton spinnerButton = new JoystickButton(driveJoystick, RobotMap.buttonB);
    public static JoystickButton gateButton = new JoystickButton(driveJoystick, RobotMap.rightBumper);
    public static JoystickButton wheelCountButton = new JoystickButton(driveJoystick, RobotMap.buttonX);
    public static JoystickButton elevatorButton = new JoystickButton(driveJoystick, RobotMap.buttonY);
    /**
     *
     */
    

}
