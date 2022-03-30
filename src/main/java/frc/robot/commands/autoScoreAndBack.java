// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import javax.naming.directory.SearchResult;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class autoScoreAndBack extends SequentialCommandGroup {
  
  public autoScoreAndBack(DriveTrain m_driveTrain, Intake m_Intake) {
    addCommands(
      new intakeOut(m_Intake).withTimeout(2),
      new driveBackSome(m_driveTrain).withTimeout(2)
    );
  }
}
