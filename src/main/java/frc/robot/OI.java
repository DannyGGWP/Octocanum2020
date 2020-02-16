/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Add your docs here.
 */
public class OI 
{
  public final FasterOctoCanum driveTrain = new FasterOctoCanum();
  //public final SpinSpin colorWheel = new SpinSpin();
  public final ShootShoot ballShooter = new ShootShoot();
  public final LiftLift elevatorSubsystem = new LiftLift();
  public final UpUp hangBoi = new UpUp();

 // public final ActivateSpinSpin spinnerCommand = new ActivateSpinSpin(colorWheel);
  public final PowerDistributionPanel m_pdp = new PowerDistributionPanel(51);
  public static Joystick driveJoystick = new Joystick(1);
  public static Joystick panel = new Joystick(0);
  public static JoystickButton cannonButton = new JoystickButton(panel,RobotMap.shootButton);
  public static JoystickButton turnButton = new JoystickButton(driveJoystick,RobotMap.buttonB);
  //public static JoystickButton shooterButton = new JoystickButton(driveJoystick, RobotMap.buttonA);
  /*
    public static JoystickButton mechanumSwitch = new JoystickButton(driveJoystick,RobotMap.back);
    public static JoystickButton tankDrop = new JoystickButton(driveJoystick,RobotMap.leftTrigger);
    public static JoystickButton spinnerButton = new JoystickButton(driveJoystick, RobotMap.buttonB);
  //public static JoystickButton gateButton = new JoystickButton(driveJoystick, RobotMap.rightBumper);
    public static JoystickButton wheelCountButton = new JoystickButton(driveJoystick, RobotMap.buttonX);
    public static JoystickButton elevatorButton = new JoystickButton(driveJoystick, RobotMap.buttonY);
    /**
     *
     */

     public OI()
     {
       configureButtonBindings();
       driveTrain.setDefaultCommand(
         new RunCommand(() -> driveTrain
         .drive(driveJoystick.getX(),driveJoystick.getY(),driveJoystick.getRawAxis(2),driveJoystick.getRawAxis(3)),driveTrain)
       );
     }
    public Command getAutonomousCommand() {
      if(RobotMap.autoChooser.equals("left"))
      {
        return new FarWallAuto(elevatorSubsystem, driveTrain, ballShooter);
      }
      if(RobotMap.autoChooser.equals("right"))
      {
        return new CloseWallAuto(elevatorSubsystem, driveTrain, ballShooter);
      }
      if(RobotMap.autoChooser.equals("center"))
      {
        return new AutoCenter(ballShooter, driveTrain, elevatorSubsystem); 
      }
     // return new AutoCenter(ballShooter, driveTrain, elevatorSubsystem); 
        //return new Angle156Auto(elevatorSubsystem, driveTrain, ballShooter);
        return new CloseWall5BallAuto(elevatorSubsystem, driveTrain, ballShooter);
    }
    private void configureButtonBindings()
    {
      new JoystickButton(driveJoystick,RobotMap.leftBumper)
        .whenPressed(new InstantCommand(driveTrain::toggleTank, driveTrain));
        cannonButton.whenPressed(new Cannon(ballShooter, elevatorSubsystem));
        turnButton.whenPressed(new Angle156Auto(elevatorSubsystem, driveTrain, ballShooter));
      new JoystickButton(driveJoystick, RobotMap.buttonY)
        .whenPressed(new InstantCommand(elevatorSubsystem::elevatorUp, elevatorSubsystem))
       .whenReleased(elevatorSubsystem::elevatorOff, elevatorSubsystem);
     // new JoystickButton(driveJoystick, RobotMap.leftTrigger)
       // .whenPressed(new InstantCommand(elevatorSubsystem::intake, elevatorSubsystem))
        //.whenReleased(new InstantCommand(elevatorSubsystem::offTake,elevatorSubsystem));
//        new JoystickButton(driveJoystick, RobotMap.leftBumper)
//        .whenHeld(
//          new InstantCommand(elevatorSubsystem::elevatorDown, elevatorSubsystem).alongWith(
//            new InstantCommand(elevatorSubsystem::succSuccOuttake,elevatorSubsystem).withInterrupt(condition)
//            )
//          );
      //new JoystickButton(driveJoystick, RobotMap.leftBumper)
      //  .whenReleased(elevatorSubsystem::succSuccOff, elevatorSubsystem);
      new JoystickButton(driveJoystick, RobotMap.rightBumper)
        .whenReleased(elevatorSubsystem::succSuccOff, elevatorSubsystem);
      new JoystickButton(driveJoystick, RobotMap.buttonA)
        .whenPressed(elevatorSubsystem::succSolExtend, elevatorSubsystem)
        .whenReleased(elevatorSubsystem::succSolRetract, elevatorSubsystem); 
      new JoystickButton(panel, RobotMap.outtakeButton)
        .whenPressed(elevatorSubsystem::outtake, elevatorSubsystem)
        .whenReleased(elevatorSubsystem::offTake, elevatorSubsystem);
      new JoystickButton(panel, RobotMap.intakeButton)
        .whileHeld(new SensorIntake(elevatorSubsystem))
        .whenReleased(elevatorSubsystem::offTake, elevatorSubsystem);
      new JoystickButton( panel, RobotMap.climbUp)
        .whenPressed(hangBoi :: climbUp, hangBoi)
        .whenReleased(hangBoi :: climbOff, hangBoi);
      new JoystickButton(panel, RobotMap.climbDown)
        .whenPressed(hangBoi :: climbDown,hangBoi)
        .whenReleased(hangBoi :: climbOff,hangBoi);
      new JoystickButton(panel,RobotMap.hangEnable)
        .whileHeld(hangBoi :: climbOff, hangBoi);

      
    }
}
