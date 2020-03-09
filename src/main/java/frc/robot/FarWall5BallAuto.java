/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FarWall5BallAuto extends SequentialCommandGroup 
{
  /**
   * Creates a new FarWall5BallAuto.
   */
  public FarWall5BallAuto(LiftLift elevator, FasterOctoCanum driveTrain, ShootShoot shooter) 
  {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

//DON'T CHANGE , IT GOOD

    super(
      //drive forward
      new InstantCommand(driveTrain::resetGyro, driveTrain),
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
      new InstantCommand(driveTrain::enableDriveStraight,driveTrain),
      new WaitCommand(0.25),
      new InstantCommand(elevator::intake,elevator),
      new AutoDrive(-0.6,0.0,95000.0, driveTrain),
      //brake
      new InstantCommand(driveTrain::enableBrake, driveTrain),
      new WaitCommand(.25),
      new InstantCommand(driveTrain::disableBrake, driveTrain),
      new WaitCommand(.25),
      //strafe
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
      new InstantCommand(driveTrain::disableTank,driveTrain),
      new InstantCommand(driveTrain::disableDriveStraight,driveTrain),
      new WaitCommand(0.25),
      new AutoDriveMech(0,-0.3, 55000, driveTrain),

    //drive back
      new InstantCommand(driveTrain::enableTank,driveTrain),
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
      new WaitCommand(.75),
      new InstantCommand(elevator::offTake,elevator),
      new AutoDrive(0.8,0.0,100000.0, driveTrain),

      //turn 
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
    //new InstantCommand(driveTrain::resetGyro, driveTrain),
      new WaitCommand(.25),
      new InstantCommand(driveTrain::toggleTank,driveTrain),
      new TurnToAngle(-90, driveTrain, 0.010, 0.00300, 0.0016, 4),

      //drive across w/brake
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
      new InstantCommand(driveTrain::enableDriveStraight,driveTrain),
      new WaitCommand(0.25),
      new AutoDrive(-0.8,0.0,160000.0, driveTrain),
      new InstantCommand(driveTrain::enableBrake, driveTrain),
      new WaitCommand(.25),
      new InstantCommand(driveTrain::disableBrake, driveTrain),

      //turn w/shooter reader
      new InstantCommand(driveTrain::resetGyro, driveTrain),	     
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
      new InstantCommand(driveTrain::disableDriveStraight,driveTrain),
      new WaitCommand(0.25),
      new InstantCommand(driveTrain::toggleTank,driveTrain),
      new TurnToAngle(-90, driveTrain, 0.010, 0.00300, 0.0016, 4),
      new InstantCommand(shooter::onWheel,shooter), 

      //drive forward
      new InstantCommand(driveTrain::enableDriveStraight,driveTrain),
      new AutoDrive(-0.6,0.0,60000,driveTrain),
      new InstantCommand(driveTrain::enableBrake, driveTrain),
      new WaitCommand(.25),
      new InstantCommand(driveTrain::disableBrake, driveTrain),
      new InstantCommand(driveTrain::disableDriveStraight,driveTrain),
     
      // SHOOT!
      new WaitCommand(0.75),
      new InstantCommand(elevator::elevatorUp, elevator),
      new InstantCommand(shooter::openGate,shooter),
      new WaitCommand(5), 
      new InstantCommand(elevator::elevatorOff, elevator),
      new InstantCommand(shooter::closeGate,shooter),
      new InstantCommand(shooter::offWheel, shooter)
      
      
      
    );
  }
}
