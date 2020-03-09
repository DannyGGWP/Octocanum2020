/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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
  public final SpinSpin wheel = new SpinSpin();
  public final BlinkBlink blinker = new BlinkBlink();
  //public final LightLight lightStrips = new LightLight();

 // public final ActivateSpinSpin spinnerCommand = new ActivateSpinSpin(colorWheel);
  public final PowerDistributionPanel m_pdp = new PowerDistributionPanel(51);
  public static Joystick driveJoystick = new Joystick(1);
  public static Joystick panel = new Joystick(0);
  public static JoystickButton cannonButton = new JoystickButton(panel,RobotMap.shootButton);
 //public JoystickButton compressorButton = new JoystickButton(driveJoystick, RobotMap.buttonX);
 // public static JoystickButton turnButton = new JoystickButton(driveJoystick,RobotMap.buttonB);
  public static JoystickButton autoCenter = new JoystickButton(panel, RobotMap.centerAuto);
  public static JoystickButton autoRightFive = new JoystickButton(panel, RobotMap.rightFiveAuto);
  public static JoystickButton autoLeftFive = new JoystickButton(panel, RobotMap.leftFiveAuto);
  public static JoystickButton autoLeftThree = new JoystickButton(panel, RobotMap.leftThreeAuto);
 // public static JoystickButton auto = new JoystickButton(panel, RobotMap.centerAuto);
//  public static JoystickButton climbButton = new JoystickButton(driveJoystick,RobotMap.climbUp);
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
    public Command getAutonomousCommand() 
    {
      if(autoCenter.get())
      {
        return new CommandGroupAutoCenter(elevatorSubsystem, driveTrain, ballShooter);
      }
      if(autoRightFive.get())
      {
        return new CloseWall5BallAuto(elevatorSubsystem, driveTrain, ballShooter);
      }  
      if(autoLeftFive.get())
      {
        return new FarWall5BallAuto(elevatorSubsystem, driveTrain, ballShooter);
      }
      if(autoLeftThree.get())
      {
        return new FarWallAuto(elevatorSubsystem, driveTrain, ballShooter);
      }
        //return new AutoCenter(ballShooter, driveTrain, elevatorSubsystem); 
        //return new Angle156Auto(elevatorSubsystem, driveTrain, ballShooter);
        //return new FarWall5BallAuto(elevatorSubsystem, driveTrain, ballShooter);
        //return new CloseWallAuto(elevatorSubsystem, driveTrain, ballShooter);
      return new WaitCommand(5);
    }
    private void configureButtonBindings()
    {
      new JoystickButton(driveJoystick,RobotMap.leftBumper)
        .whenPressed(new InstantCommand(driveTrain::toggleTank, driveTrain));
        cannonButton.whenPressed(new Cannon(ballShooter, elevatorSubsystem));
  //      turnButton.whenPressed(new Angle156Auto(elevatorSubsystem, driveTrain, ballShooter));
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
      new JoystickButton(panel, RobotMap.climbUp)
        .whenPressed(new Climbing(hangBoi))
        .whenReleased(hangBoi::armOff, hangBoi);
        
      new JoystickButton(panel, RobotMap.findColor)
        .whenPressed(new SpinToColor(wheel));
      new JoystickButton(panel, RobotMap.rotateColor)
        .whenPressed(new ActivateSpinSpin(wheel));
        
      new JoystickButton(panel, RobotMap.elevatorUp)
        .whenPressed(elevatorSubsystem::elevatorUp,elevatorSubsystem)
        .whenReleased(elevatorSubsystem::elevatorOff,elevatorSubsystem);
      new JoystickButton(panel, RobotMap.elevatorDown)
        .whenPressed(elevatorSubsystem::elevatorDown,elevatorSubsystem)
        .whenReleased(elevatorSubsystem::elevatorOff,elevatorSubsystem);

      new JoystickButton(panel, RobotMap.climbDown)
        .whenPressed(hangBoi::armDown,hangBoi)
        .whenReleased(hangBoi::armOff,hangBoi);
      new JoystickButton(panel,RobotMap.hangEnable)
        .whileHeld(hangBoi::armOff, hangBoi);
      new JoystickButton(driveJoystick, RobotMap.back)
        .whenPressed(driveTrain::disableDriveStraight, driveTrain);

    

//      new JoystickButton(driveJoystick, RobotMap.buttonX)
  //      .whenPressed(blinker:: lightOff, blinker); 
    }
}
