package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.constants.TeleOpConstants;

@TeleOp
public class FlyWheelSubsystem {

    boolean closeOn = false, farOn = false;
    boolean lastA = false, lastB = false, enabled = false;
    double targetVel;

    private Gamepad gamepad2;

    private DcMotorEx rightFlywheel, leftFlywheel;

    public static double velocity;

    public FlyWheelSubsystem(HardwareMap hw, Gamepad gamepad2) {
        rightFlywheel = hw.get(DcMotorEx.class, TeleOpConstants.Flywheel.RIGHT_FLYWHEEL_MOTOR_NAME);
        leftFlywheel = hw.get(DcMotorEx.class, TeleOpConstants.Flywheel.LEFT_FLYWHEEL_MOTOR_NAME);

        leftFlywheel.setDirection(DcMotorEx.Direction.FORWARD);
        leftFlywheel.setDirection(DcMotorEx.Direction.REVERSE);
        this.gamepad2 = gamepad2;

    }

    public void teleVelocity(){
        if (gamepad2.a && !lastA) {
            closeOn = !closeOn;
            if (closeOn) farOn = false;  // only one mode at a time
        }
        lastA = gamepad2.a;

        // B toggles FAR mode
        if (gamepad2.b && !lastB) {
            farOn = !farOn;
            if (farOn) closeOn = false;  // only one mode at a time

        }
        lastB = gamepad2.b;

        // Decide target based on toggles
        if (farOn) {
            enabled = true;
            targetVel = TeleOpConstants.Flywheel.FAR_VEL;
        } else if (closeOn) {
            enabled = true;
            targetVel = TeleOpConstants.Flywheel.CLOSE_VEL;
        } else {
            enabled = false;
        }
        update();

    }

    public void autoSetVelocity(double targetVel){
        this.targetVel = targetVel;
    }

    public void update(){

        if(!enabled || targetVel <= 0){
            targetVel = 0;
            rightFlywheel.setPower(0);
            leftFlywheel.setPower(0);
        }
        // Single encoder
        velocity = rightFlywheel.getVelocity();

        double error = targetVel - velocity;
        double feedback = error * TeleOpConstants.Flywheel.KP;

        double feedforward = 0;
        if (targetVel > 0) {
            feedforward = TeleOpConstants.Flywheel.KV * targetVel + TeleOpConstants.Flywheel.KS;
        }

        double power = feedback + feedforward;

        rightFlywheel.setPower(power);
        leftFlywheel.setPower(power);
    }

    public boolean atTargetVelocity(){
        return Math.abs(rightFlywheel.getVelocity() - targetVel) < TeleOpConstants.Flywheel.VELOCITY_TOLERANCE;
    }
    public double currentVelocity(){
        return rightFlywheel.getVelocity();
    }
}