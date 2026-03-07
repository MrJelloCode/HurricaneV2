package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class FlyWheelSubsystem {

    public static double kP = 0,kV = 0,kS = 0;
    public static double maxTargetTPS = 1500; // target speed in ticks/sec
    private static final double OUTPUT_MAX = 1.0;

    boolean closeOn = false, farOn = false;
    boolean lastA = false, lastB = false, enabled = false;
    double farVel = 2000, closeVel = 1500, targetVel;

    private Gamepad gamepad2;

    private DcMotorEx rightFlywheel, leftFlywheel;

    public static double velocity;

    public FlyWheelSubsystem(HardwareMap hw, Gamepad gamepad2) {
        rightFlywheel = hw.get(DcMotorEx.class, "flywheel1");
        leftFlywheel = hw.get(DcMotorEx.class, "flywheel2");

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
            targetVel = farVel;
        } else if (closeOn) {
            enabled = true;
            targetVel = closeVel;
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
        double feedback = error * kP;

        double feedforward = 0;
        if (targetVel > 0) {
            feedforward = kV * targetVel + kS;
        }

        double power = feedback + feedforward;

        rightFlywheel.setPower(power);
        leftFlywheel.setPower(power);
    }
}