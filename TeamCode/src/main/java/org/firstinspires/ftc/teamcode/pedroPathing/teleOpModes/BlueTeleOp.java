package org.firstinspires.ftc.teamcode.pedroPathing.teleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheelSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.LimelightSubsystem;

//import org.firstinspires.ftc.teamcode.classes.TurretMechanism;

@TeleOp(name="BlueTeleop")
public class BlueTeleOp extends OpMode {

    private LimelightSubsystem limelightSubsystem;
    private DrivetrainSubsystem drivetrainSubsystem;
    private FlyWheelSubsystem flyWheelSubsystem;
    private IntakeSubsystem intakeSubsystem;


    @Override
    public void init() {
        limelightSubsystem = new LimelightSubsystem(hardwareMap);
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap,gamepad1);
        flyWheelSubsystem = new FlyWheelSubsystem(hardwareMap,gamepad2);
        intakeSubsystem  = new IntakeSubsystem(hardwareMap, gamepad2);
    }

    @Override
    public void loop() {

        limelightSubsystem.update();
        drivetrainSubsystem.robotCentricDrive();
        intakeSubsystem.teleUpdate();
        flyWheelSubsystem.teleVelocity();
        telemetry.update();

    }
}