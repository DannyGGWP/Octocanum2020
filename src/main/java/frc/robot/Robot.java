/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.FasterOctoCanum.DriveMode;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /** 
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  */
  
  public static OI m_oi;
  public static FasterOctoCanum driveTrain = new FasterOctoCanum();
  public static SpinSpin colorWheel = new SpinSpin();
  public static ShootShoot ballShooter = new ShootShoot();
  public static Cannon cannonCommand = new Cannon();
  public static PowerDistributionPanel m_pdp = new PowerDistributionPanel(51);
  private boolean backFlag = false;
  private boolean tankFlag = false;
 
  private ColorMatch m_colorMatcher = new ColorMatch();

  private Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public static Compressor compressor = new Compressor(52);
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    compressor.start();
    driveTrain.disableFieldOriented();

    /** 
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    */
    //driveTrain.enableDriveStraight();
    CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {/** 
    Color detectedColor = colorWheel.getColor();

    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) 
    {
      colorString = "Blue";
    }
    else if (match.color == kRedTarget)
    {
      colorString = "Red";
    }
    else if (match.color ==kGreenTarget)
    {
      colorString = "Green";
    }
    else if (match.color == kYellowTarget) 
    {
      colorString = "Yellow";
    } 
    else 
    {
      colorString = "Unknown"; 
    }
    */

    //SmartDashboard.putString("Detected Color", colorString);
  }
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
   // m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    //System.out.println("Auto selected: " + m_autoSelected);
    //driveTrain.enableDropDrive();
    //driveTrain.enableDriveStraight();
    //driveTrain.enableFieldOriented();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    /*
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
    */
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    if(OI.mechanumSwitch.get() && !backFlag)
    {
      backFlag = true;
      if(driveTrain.getMode() == DriveMode.fieldMechanum)
      {
          //driveTrain.setMode(DriveMode.robotMechanum);
        driveTrain.disableFieldOriented();
      }
      else if(driveTrain.getMode() == DriveMode.robotMechanum)
      {
          //driveTrain.setMode(DriveMode.fieldMechanum);
        driveTrain.disableFieldOriented();
      }
    }
    else if (!OI.mechanumSwitch.get())
    {
      backFlag = false;
    }
    if(OI.tankDrop.get() && !tankFlag)
    {
      //driveTrain.setMode(DriveMode.tank);
      tankFlag = true;
      if(driveTrain.getMode() == DriveMode.tank)
      {
        driveTrain.disableTank();
      }
      else
      {
        driveTrain.enableTank();
      }
    }
    else if (!OI.tankDrop.get())
    {
      tankFlag = false;
    }
    driveTrain.drive(OI.driveJoystick.getX(),OI.driveJoystick.getY()*.5,OI.driveJoystick.getRawAxis(2)*.5);

    if(OI.cannonButton.get() && !cannonCommand.isRunning())
    {
      //cannonCommand.start();
    }
    if (OI.cannonButton.get())
    {
      ballShooter.onWheel();
    }
    else 
    {
      ballShooter.offWheel();
    }

    if(OI.spinnerButton.get())
    {
      colorWheel.stringColor();
    }
  }
 
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
