/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FarWallAuto extends SequentialCommandGroup 
{
  /**
   * Creates a new FarWallAuto.
   */
  public FarWallAuto(LiftLift elevator, FasterOctoCanum driveTrain, ShootShoot shooter) 
  {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(
      new DriveDistance(100000, driveTrain),
      new TurnToAngle(90, driveTrain, 0.02, 0.004, 0.0),
      new AutoCenter(shooter, driveTrain, elevator)
    );
  }
}
