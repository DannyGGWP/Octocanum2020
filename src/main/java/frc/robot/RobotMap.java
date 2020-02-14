/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotMap
// we're at 9 bitches
{
	public static int driveTalonFL = 3;
	public static int driveTalonBL = 2;
	public static int driveTalonFR = 5;
	public static int driveTalonBR = 6;
	public static int shooterSpark = 7;
	public static double c_deadBand = 0.1;
	public static double setPoint = 4800;
	public static double deadband = 0;

	public static int back = 9;
	public static int leftTrigger = 7;
	public static int rightTrigger = 8;
	public static int buttonA = 2;
	public static int buttonB = 3;
	public static int rightBumper = 6;
	public static int leftBumper = 5;
	public static int buttonX = 1;
	public static int buttonY = 4;
	public static int l3 = 11;
	public static int r3 = 12;
	public static int start = 10;

	public static int driveSol = 0;
	public static int dropSol = 1;
	public static int succSol = 2;

	public static int wheelMotor = 9;
	public static int elevatorMotor = 9;
	public static int succMotor = 4;

	public static String autoChooser = "0";

	//control panel values
	public static int outtakeButton = 9;
	public static int intakeButton = 10;
	public static int shootButton = 11;

	//DUMB AS FUCK PID BULLSHIT
	public static int p = 0;
	public static int i = 0;
	public static int d = 0;

	public static int elevatorSensor = 0;
	public static int hopperSensor = 1;

}
