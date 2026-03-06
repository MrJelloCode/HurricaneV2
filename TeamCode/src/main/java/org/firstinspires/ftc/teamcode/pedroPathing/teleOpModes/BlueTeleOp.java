package org.firstinspires.ftc.teamcode.pedroPathing.teleOpModes;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.LimelightSubsystem;

//import org.firstinspires.ftc.teamcode.classes.TurretMechanism;

@TeleOp(name="Teleop")
public class BlueTeleOp extends OpMode {

    DcMotorEx rightFlywheel, leftFlywheel, intake;
//    private TurretMechanism turret = new TurretMechanism();
    private DrivetrainSubsystem drivetrainSubsystem;
    private LimelightSubsystem limelightSubsystem;

    public static double targetVelocity, velocity;

    public static double P,kV,kS;
    boolean closeOn = false, farOn = false;
    boolean lastA = false, lastB = false;
    double farVel = 2000, closeVel = 1500;


    @Override
    public void init() {
        initHardware();
    }

    @Override
    public void loop() {

        drivetrainSubsystem.fieldCentricDrive(gamepad1);

        if (gamepad1.a && !lastA) {
            closeOn = !closeOn;
            if (closeOn) farOn = false;  // only one mode at a time
        }
        lastA = gamepad1.a;

        // B toggles FAR mode
        if (gamepad1.b && !lastB) {
            farOn = !farOn;
            if (farOn) closeOn = false;  // only one mode at a time
        }
        lastB = gamepad1.b;

        // Decide target based on toggles
        if (farOn) {
            targetVelocity = farVel;
            updateFlywheel(targetVelocity);
        } else if (closeOn) {
            targetVelocity = closeVel;
            updateFlywheel(targetVelocity);
        } else {
            // OFF = hard off
            rightFlywheel.setPower(0);
            leftFlywheel.setPower(0);
        }



        if (gamepad1.right_trigger > 0.05) {
            intake.setPower(0.8);          // intake in, proportional
        } else if (gamepad1.left_trigger > 0.05) {
            intake.setPower(-0.8);         // reverse out, proportional
        } else {
            intake.setPower(0);           // off
        }


        telemetry.update();
        limelightSubsystem.update();
    }

    private void initHardware() {
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap);
        limelightSubsystem = new LimelightSubsystem(hardwareMap);


        // Flywheel
        rightFlywheel = hardwareMap.get(DcMotorEx.class, "flywheel1");
        leftFlywheel = hardwareMap.get(DcMotorEx.class, "flywheel2");

        // Typical mirrored flywheel setup
        leftFlywheel.setDirection(DcMotorSimple.Direction.REVERSE);

        // Intake
        intake = hardwareMap.get(DcMotorEx.class, "intake");

        intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


    }
    private void updateFlywheel(double targetVel) {

        // Single encoder
        velocity = rightFlywheel.getVelocity();

        double error = targetVel - velocity;
        double feedback = error * P;

        double feedforward = 0;
        if (targetVel > 0) {
            feedforward = kV * targetVel + kS;
        }

        double power = feedback + feedforward;

        rightFlywheel.setPower(power);
        leftFlywheel.setPower(power);

    }
}