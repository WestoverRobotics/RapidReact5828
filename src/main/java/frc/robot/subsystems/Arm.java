// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class Arm extends SubsystemBase {

  private final CANSparkMax armMotor;
  /** Creates a new Arm. */
  public Arm() {
    armMotor = new CANSparkMax(5,MotorType.kBrushless);
    armMotor.setIdleMode(IdleMode.kBrake);
  }
  /**public void periodic() {
     This method will be called once per scheduler run
    SmartDashboard.putNumber("Arm Motor", armMotor.getSelectedSensorPosition());
  }*/

  /*
  public void runArm(){
    armMotor.set(1);
  }

  public void stopArm(){
    armMotor.set(ControlMode.PercentOutput, 0);
  }

  public void resetArmEncoder(){
    armMotor.setSelectedSensorPosition(0);
  }

  public double getArmEncoder(){
    return armMotor.getSelectedSensorPosition();
  }
  */
  public void armUp() {
    armMotor.set(.3);
  }
  public void armDown() {
    armMotor.set(-.3);
  }
  public void armStop() {
    armMotor.stopMotor();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
