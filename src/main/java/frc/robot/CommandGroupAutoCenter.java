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
public class CommandGroupAutoCenter extends SequentialCommandGroup {
  /**
   * Creates a new CommandGroupAutoCenter.
   */
  public CommandGroupAutoCenter(LiftLift elevator, FasterOctoCanum driveTrain, ShootShoot shooter) 
  {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new InstantCommand(driveTrain::resetGyro, driveTrain),
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
      new InstantCommand(driveTrain::enableDriveStraight,driveTrain),
      new WaitCommand(0.25),
      new AutoDrive(-.6, 0, 58000, driveTrain),
      //shoot
      new InstantCommand(shooter::onWheel,shooter), 
      new WaitCommand(1),
      new InstantCommand(elevator::elevatorUp, elevator),
      new InstantCommand(shooter::openGate,shooter),
      new WaitCommand(3), 
      new InstantCommand(elevator::elevatorOff, elevator),
      new InstantCommand(shooter::closeGate,shooter),
      new InstantCommand(shooter::offWheel, shooter),
      //strafe
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
      new InstantCommand(driveTrain::disableDriveStraight,driveTrain),
      new InstantCommand(driveTrain::disableTank, driveTrain),
      new WaitCommand(.25),
      new AutoDriveMech(0, -0.3, 100000, driveTrain)



    );
  }
}
