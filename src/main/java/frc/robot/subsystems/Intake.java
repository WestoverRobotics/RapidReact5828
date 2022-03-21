// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
    private final CANSparkMax intakeMotor;
  /** Creates a new ExampleSubsystem. */
  public Intake() {
    intakeMotor = new CANSparkMax(10,MotorType.kBrushed);
    intakeMotor.setIdleMode(IdleMode.kBrake);
  }

  public void intakeIn() {
      intakeMotor.set(-.5);
  }
  public void intakeOut() {
      intakeMotor.set(.5);
  }
  public void intakeStop() {
      intakeMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
