// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class DriveManual extends CommandBase {
  /** Creates a new DriveManual */
  private final DriveTrain m_drive;
  /** Creates a new DriveManual. */
  public DriveManual(DriveTrain subsystem, DoubleSupplier forward, DoubleSupplier rotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = subsystem;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double speed = RobotContainer.m_driverController.getRawAxis(3) - RobotContainer.m_driverController.getRawAxis(2);
    double rotation = RobotContainer.m_driverController.getRawAxis(0);
    boolean quickTurn = speed > -0.2 && speed < 0.2;

    if( speed > -0.1 && speed < 0.1){
      speed = 0;
    }

    if( rotation > -0.1 && rotation < 0.1){
      rotation = 0;
    }
    
    m_drive.curvatureDrive(-speed*.7, rotation*.7, quickTurn);

    //m_drive.arcadeDrive(-m_forward.getAsDouble()*.8,-m_rotation.getAsDouble()*.8);

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
