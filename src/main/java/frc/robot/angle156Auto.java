/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Angle156Auto extends SequentialCommandGroup {
  /**
   * Creates a new angle156Auto.
   */
  public Angle156Auto(LiftLift elevator, FasterOctoCanum driveTrain, ShootShoot shooter) 
  {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
      addCommands
      (
      new InstantCommand(driveTrain::resetGyro, driveTrain),
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
      new AutoCenter(shooter, driveTrain, elevator),
      new InstantCommand(driveTrain::resetGyro, driveTrain),
      new InstantCommand(driveTrain::resetEncoders, driveTrain),
    //  new RunCommand(toRun, requirements)
    // new DriveDistance(-10000.0, driveTrain),
    //  new TurnToAngle(156, driveTrain, 0.01, 0.0001, 0.0001),
      new TurnToAngle(146, driveTrain, 0.01, 0.0001, 0.0001, 5),
      //new WaitCommand(1),
      //new TurnToAngle(156, driveTrain, 0.01, 0.0001, 0.0001),
    //  new DriveDistance(150000.0, driveTrain),
      new AutoDrive(-0.6, 0.0, 182000, driveTrain),
      new TurnToAngle(170, driveTrain, 0.02, 0.0001, 0.0001, 3),
      new InstantCommand(elevator::intake,elevator),
      //new AutoDrive(-0.4,0.0,26500.0, driveTrain),
      new AutoDrive(-0.3,0.0,53000.0, driveTrain),
      new WaitCommand(2),
    // new DriveDistance(27000.0, driveTrain),
      //new WaitCommand(2),
      new InstantCommand(elevator::offTake,elevator)
      );
    
  }
}
