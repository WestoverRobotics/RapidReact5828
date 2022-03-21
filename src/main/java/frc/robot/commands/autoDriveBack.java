// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.concurrent.TimeoutException;
import edu.wpi.first.wpilibj2.command.*;

/** An example command that uses an example subsystem. */
public class autoDriveBack extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_driveTrain;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public autoDriveBack(DriveTrain subsystem) {
    m_driveTrain = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);

    withTimeout(2.5);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.autoDriveBack();
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
  }
}
