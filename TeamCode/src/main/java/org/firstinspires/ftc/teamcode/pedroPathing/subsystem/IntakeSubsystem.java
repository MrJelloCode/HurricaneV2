package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.TeleOpConstants;


public class IntakeSubsystem {
    private DcMotorEx intake;
    private Gamepad gamepad;


    public IntakeSubsystem(HardwareMap hw, Gamepad gamepad) {
        intake = hw.get(DcMotorEx.class, TeleOpConstants.Intake.INTAKE_MOTOR_NAME);
        this.gamepad = gamepad;
    }


    public void teleUpdate() {
        if (gamepad.right_trigger > 0.05) {
            intake.setPower(TeleOpConstants.Intake.POWER);          // intake in, proportional
        } else if (gamepad.left_trigger > 0.05) {
            intake.setPower(-TeleOpConstants.Intake.POWER);         // reverse out, proportional
        } else {
            intake.setPower(0);           // off
        }
    }

    public void autoPower(double power){
        intake.setPower(power);
    }
}