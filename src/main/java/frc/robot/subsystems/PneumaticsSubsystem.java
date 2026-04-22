// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PneumaticsConstants;

public class PneumaticsSubsystem extends SubsystemBase {
  
  private final DoubleSolenoid solenoid;
  private final Compressor compressor = new Compressor(PneumaticsConstants.PCM_ID, PneumaticsModuleType.CTREPCM);

  private boolean compressorOn = true;

  public PneumaticsSubsystem() {
    solenoid = new DoubleSolenoid(
      PneumaticsConstants.PCM_ID,
      PneumaticsModuleType.CTREPCM,
      PneumaticsConstants.SOLENOID_FORWARD_CHANNEL,
      PneumaticsConstants.SOLENOID_REVERSE_CHANNEL
    );
    solenoid.set(DoubleSolenoid.Value.kReverse);
    solenoid.set(DoubleSolenoid.Value.kForward);
    compressor.enableDigital();
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
    compressor.enableDigital();
  }

  public void stopCompressor() {
    compressor.disable();
  }

  public void toggleCompressor() {
    if (compressorOn) {
      compressor.disable();
    } else {
      compressor.enableDigital();
    }
    compressorOn = !compressorOn;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Pnematics/getCompressorSwitchValue", compressor.getPressureSwitchValue());
    // This method will be called once per scheduler run
  }
}