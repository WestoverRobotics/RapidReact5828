// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;


public class Arm extends SubsystemBase {

  private CANSparkMax armMotor;
  private SparkMaxPIDController armPIDController;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  private RelativeEncoder armEncoder;

  /** Creates a new Arm. */
  public Arm() {
    armMotor = new CANSparkMax(5,MotorType.kBrushless);
    armEncoder = armMotor.getEncoder();
    armMotor.setIdleMode(IdleMode.kBrake);

    //Arm Set Points
    final double TOP = 100;
    final double DOWN = 50;
    //Arm PID Object and Feedback Object
    armPIDController = armMotor.getPIDController();
    armPIDController.setFeedbackDevice(armEncoder);

    // PID coefficients
    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;

    // set PID coefficients
    armPIDController.setP(kP);
    armPIDController.setI(kI);
    armPIDController.setD(kD);
    armPIDController.setIZone(kIz);
    armPIDController.setFF(kFF);
    armPIDController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double rotations = SmartDashboard.getNumber("Set Rotations", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) {armPIDController.setP(p); kP = p; }
    if((i != kI)) {armPIDController.setI(i); kI = i; }
    if((d != kD)) {armPIDController.setD(d); kD = d; }
    if((iz != kIz)) {armPIDController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) {armPIDController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
     armPIDController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 

    armPIDController.setReference(rotations, CANSparkMax.ControlType.kPosition);

    SmartDashboard.putNumber("armMotorTemperature", armMotor.getMotorTemperature());
    SmartDashboard.putNumber("SetPoint", rotations);
    SmartDashboard.putNumber("ProcessVariable", armEncoder.getPosition());
    }
  }

  //Commands
  public void armUp() {
    armMotor.set(.2);
  }
  public void armDown() {
    armMotor.set(-.1);
  }
  public void armStop() {
    armMotor.stopMotor();
  }
  public void armTop() {
    if(armEncoder.getPosition() < 5) {
      armMotor.set(.1);
    } else {
      armMotor.set(0);
    }
  }
    public void armBottom() {
      if(armEncoder.getPosition() > 5) {
        armMotor.set(-.1);
      } else {
        armMotor.set(0);
    }
  }
}