// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PneumaticsConstants;

public class PseudoPneumaticsSubsystem extends SubsystemBase {
  
  private final DoubleSolenoid solenoid;
  private final Compressor otherCompressor = new Compressor(PneumaticsConstants.PCM_2_ID, PneumaticsModuleType.CTREPCM);
  private boolean compressorTwoOn = true;


  public PseudoPneumaticsSubsystem() {
    solenoid = new DoubleSolenoid(
      PneumaticsConstants.PCM_2_ID,
      PneumaticsModuleType.CTREPCM,
      PneumaticsConstants.SOLENOID_FORWARD_CHANNEL,
      PneumaticsConstants.SOLENOID_REVERSE_CHANNEL
    );
    solenoid.set(DoubleSolenoid.Value.kReverse);
    otherCompressor.enableDigital();
  }

  public void extend() {
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void toggle() {
    solenoid.toggle();
  }

  public void off() {
    solenoid.set(DoubleSolenoid.Value.kOff);
  }

  public void startCompressor() {
    otherCompressor.enableDigital();
  }

  public void stopCompressor() {
    otherCompressor.disable();
  }

  public void toggleCompressor() {
    if (compressorTwoOn) {
      otherCompressor.disable();
      compressorTwoOn = false;
    } else {
      otherCompressor.enableDigital();
      compressorTwoOn = true;
    }
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Pnematics/getCompressorSwitchValueFake", otherCompressor.getPressureSwitchValue());
    // This method will be called once per scheduler run
  }
}