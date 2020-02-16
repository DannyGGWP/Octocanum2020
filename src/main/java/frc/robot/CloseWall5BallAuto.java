/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class CloseWall5BallAuto extends SequentialCommandGroup 
{
  /**
   * Creates a new CloseWall5BallAuto.
   */
  public CloseWall5BallAuto(LiftLift elevator, FasterOctoCanum driveTrain, ShootShoot shooter) 
  {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      //new InstantCommand(driveTrain::toggleTank,driveTrain),
      new WaitCommand(0.5),
      new InstantCommand(driveTrain::resetGyro, driveTrain),
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
      new InstantCommand(elevator::intake,elevator),
      new InstantCommand(driveTrain::enableDriveStraight,driveTrain),
      new AutoDrive(-0.3,0.0,115000.0, driveTrain),
      new WaitCommand(1),
      new InstantCommand(elevator::offTake,elevator),
      //new AutoDrive(0.5,0.0,115000.0, driveTrain), 
      new InstantCommand(driveTrain::disableDriveStraight,driveTrain)//,
      //new InstantCommand(driveTrain::toggleTank,driveTrain)/*,
      /*new TurnToAngle(150, driveTrain, 0.02, 0.0001, 0.0001, 5)/*,
      new AutoDrive(-0.6,0.0,200000,driveTrain),
      new TurnToAngle(179,driveTrain,0.02, 0.0001, 0.0001, 3),
      new AutoDrive(-0.6,0.0,80000,driveTrain),
      new InstantCommand(shooter::onWheel,shooter), 
      new WaitCommand(1),
      new InstantCommand(shooter::openGate,shooter)*/
    );
  }
}
