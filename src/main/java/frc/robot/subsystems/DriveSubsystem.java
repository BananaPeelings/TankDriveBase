// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
   // Creating all our variables, we will initialize them and set their values later
   //create motors : motorcontrolers are talon srx/ talon fx in code : check pheonix 5 docs
   //create differential drive or arcade drive : check WPILib docs
    WPI_TalonSRX _leftLeader;
    WPI_TalonSRX _rightLeader;
    WPI_TalonSRX _leftFollower;
    WPI_TalonSRX _rightFollower;
    DifferentialDrive _drive;


    Joystick _joystick = new Joystick(0);
   
  public DriveSubsystem() {
    
   //initialize motor controllers
    _leftLeader = new WPI_TalonSRX(Constants.leftLeaderCANID);
    _leftFollower = new WPI_TalonSRX(Constants.leftFollowerCANID);
    _rightLeader = new WPI_TalonSRX(Constants.rightLeaderCANID);
    _rightFollower = new WPI_TalonSRX(Constants.rightFollowerCANID);
    
    //set to factory defaults
      _leftLeader.configFactoryDefault();
      _rightLeader.configFactoryDefault();

    //set motors to default to braking
      _leftLeader.setNeutralMode(NeutralMode.Brake);
       _rightLeader.setNeutralMode(NeutralMode.Brake);
          

    //create differential drive
     DifferentialDrive _drive = new DifferentialDrive(_leftLeader, _rightLeader); 
    //Makes follower motors do the same thing as the leaders so that we don't have to pass arguments for all four
    _leftFollower.follow(_leftLeader);
    _rightFollower.follow(_rightLeader);

    // invert left motors from the right motors because they are inverted 180 degrees
    
    _leftLeader.setInverted(true);
    
    
  }

  public void drive(double left, double right) {
    //Drive command
    _drive.tankDrive(left, right);
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
