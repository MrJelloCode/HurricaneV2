package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.qualcomm.robotcore.hardware.*;


public class IntakeSubsystem {
    private DcMotorEx intake;
    private Gamepad gamepad;


    public IntakeSubsystem(HardwareMap hw, Gamepad gamepad) {
        intake = hw.get(DcMotorEx.class, "intake");
        this.gamepad = gamepad;
    }


    public void teleUpdate() {
        if (gamepad.right_trigger > 0.05) {
            intake.setPower(0.8);          // intake in, proportional
        } else if (gamepad.left_trigger > 0.05) {
            intake.setPower(-0.8);         // reverse out, proportional
        } else {
            intake.setPower(0);           // off
        }
    }

    public void autoPower(double power){
        intake.setPower(power);
    }
}