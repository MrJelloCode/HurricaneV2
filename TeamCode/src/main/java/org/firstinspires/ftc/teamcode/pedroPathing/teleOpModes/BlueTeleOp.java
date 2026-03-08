package org.firstinspires.ftc.teamcode.pedroPathing.teleOpModes;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheelSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.TurretSubsystem;

@TeleOp(name="BlueTeleop")
public class BlueTeleOp extends OpMode {

    private LimelightSubsystem limelightSubsystem;
    private DrivetrainSubsystem drivetrainSubsystem;
    private FlyWheelSubsystem flyWheelSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private TurretSubsystem turretSubsystem;
    private RevBlinkinLedDriver blinkin;


    @Override
    public void init() {
        limelightSubsystem = new LimelightSubsystem(hardwareMap);
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap,gamepad1);
        flyWheelSubsystem = new FlyWheelSubsystem(hardwareMap,gamepad2);
        intakeSubsystem  = new IntakeSubsystem(hardwareMap, gamepad2);
        turretSubsystem = new TurretSubsystem(hardwareMap);
        blinkin = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
    }

    @Override
    public void loop() {

        limelightSubsystem.update();
        drivetrainSubsystem.robotCentricDrive();
        intakeSubsystem.teleUpdate();
        flyWheelSubsystem.teleVelocity();
        telemetry.update();



        if(flyWheelSubsystem.atTargetVelocity()){
            blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        }else{
            blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.VIOLET);
        }

        Pose pose = follower.getPose();

        // AUTO AIM
        turretSubsystem.update(pose, 0, 0);

        // DRIVER OVERRIDE
        if(Math.abs(gamepad2.right_stick_x) > 0.1){
            turretSubsystem.manualControl(gamepad2.right_stick_x);
        }
        else{
            turretSubsystem.disableManual();
        }

        turretSubsystem.telemetry(telemetry);

        telemetry.update();

    }
}