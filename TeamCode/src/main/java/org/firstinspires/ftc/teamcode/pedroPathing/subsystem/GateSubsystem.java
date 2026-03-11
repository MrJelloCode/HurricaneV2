package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.TeleOpConstants;


public class GateSubsystem {
    private Servo gate;
    private Gamepad gamepad;

    public static double posA = 0.28;
    public static double posB = 0.50;

    boolean servoState = false;
    boolean lastY = false;




    public GateSubsystem(HardwareMap hw, Gamepad gamepad) {
        gate = hw.get(Servo.class, TeleOpConstants.Intake.INTAKE_MOTOR_NAME);
        this.gamepad = gamepad;
    }


    public void teleUpdate() {
        if (gamepad.y && !lastY) {
            servoState = !servoState;
        }
        lastY = gamepad.y;

        // Apply correct position
        if (servoState) {
            gate.setPosition(posB);
        } else {
            gate.setPosition(posA);
        }
    }


    public void autoPower(double power){

    }
}