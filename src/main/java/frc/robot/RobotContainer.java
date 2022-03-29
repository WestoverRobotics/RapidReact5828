// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.DriveManual;
import frc.robot.commands.autoScoreAndBack;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Arm m_arm = new Arm();
  private final Intake m_Intake = new Intake();
  public static final XboxController m_driverController = new XboxController(0);
  public static final XboxController m_armController = new XboxController(1);

  private final SendableChooser<String> m_autoChooser = new SendableChooser<>();

  //auto commands
  //private final SequentialCommandGroup m_autoScoreAndBack = new autoScoreAndBack(m_Intake, m_driveTrain);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
    this.initalizeStartup();

    /* Initialize autonomous command chooser and display on the SmartDashboard. */
    this.initializeAutoChooser();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //Arm commands
    new JoystickButton(m_armController, Button.kA.value)
    .whenPressed(() -> m_arm.armUp());  
    //.whenReleased(() -> m_arm.armStop());

    new JoystickButton(m_armController, Button.kB.value)
    .whenPressed(() -> m_arm.armDown());
    //.whenReleased(() -> m_arm.armStop());

    new JoystickButton(m_armController, Button.kY.value)
    .whenPressed(() -> m_arm.armTop());

    new JoystickButton(m_armController, Button.kX.value)
    .whenPressed(() -> m_arm.armBottom());

    //Intake-Shooter commands
    new JoystickButton(m_armController, Button.kLeftBumper.value)
    .whenPressed(() -> m_Intake.intakeIn())
    .whenReleased(() -> m_Intake.intakeStop());

    new JoystickButton(m_armController, Button.kRightBumper.value)
    .whileHeld(() -> m_Intake.intakeOut())
    .whenReleased(() -> m_Intake.intakeStop());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  private void initalizeStartup() {
    m_driveTrain.setDefaultCommand(
      new DriveManual(m_driveTrain));
  }

  private void initializeAutoChooser()
  {
    /* Add options (which autonomous commands can be selected) to chooser. */
    m_autoChooser.setDefaultOption("Do Nothing", "doNothing");
    m_autoChooser.addOption("Score 1 Back Up", "autoScoreAndBack");

    /* Display chooser on SmartDashboard for operators to select which autonomous command to run during the auto period. */
    SmartDashboard.putData("Autonomous Command", m_autoChooser);
    
  }
  public Command getAutonomousCommand() {

    switch (m_autoChooser.getSelected())
    {
      case "autoScoreAndBack" :
        return new autoScoreAndBack();

      default:
        System.out.println("\nError selecting autonomous command:\nCommand selected: " + m_autoChooser.getSelected() + "\n");
        return null;
    }
  }
}
