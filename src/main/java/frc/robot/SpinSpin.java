/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Add your docs here.
 */
public class SpinSpin extends Subsystem {
  private ColorSensorV3 m_colorSensor;
  private ColorMatch m_colorMatcher = new ColorMatch();
  private WPI_TalonFX spinnyMotor = new WPI_TalonFX(RobotMap.wheelMotor);
  private Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private int m_greenCount, m_redCount, m_yellowCount, m_blueCount; 
  private String previousColor;
  private String currentColor;
  private final I2C.Port i2cport = I2C.Port.kOnboard;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public SpinSpin()
  {
    m_colorSensor = new ColorSensorV3(i2cport);
    spinnyMotor.set(ControlMode.PercentOutput, 0);
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_blueCount=0;
    m_greenCount=0;
    m_redCount=0;
    m_yellowCount=0;
  }

  public Color getColor()
  {
    return m_colorSensor.getColor();
  }

  public void spinWheel()
  {
    spinnyMotor.set(1.0);
  }

  public void offWheel()
  {
    spinnyMotor.set(0.0);
  }

  public void countColors()
  {
    currentColor = stringColor();
    if(previousColor != currentColor)
    {
      if(stringColor() == "Red")
      {
        m_redCount++;
        previousColor = "Red";
      }
      else if(stringColor() == "Blue")
      {
        m_blueCount++;
        previousColor = "Blue";
      }
      else if(stringColor() == "Yellow")
      {
        m_yellowCount++;
        previousColor = "Yellow";
      }
      else if(stringColor() == "Green")
      {
        m_greenCount++;
        previousColor = "Green";
      }
    }
  }
  public void resetCount()
  {
    m_redCount = 0;
    m_greenCount = 0;
    m_yellowCount = 0;
    m_blueCount = 0;
  }

  public int getCount(String color)
  {
    if(color == "Red")
    {
      return m_redCount;
    }
    else if(color == "Blue")
    {
      return m_blueCount;
    }
    else if(color == "Yellow")
    {
      return m_yellowCount;
    }
    else if(color == "Green")
    {
      return m_greenCount;
    }
    return 0;
  }

  public String stringColor()
  {
    Color detectedColor = getColor();

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
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    return colorString;

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
