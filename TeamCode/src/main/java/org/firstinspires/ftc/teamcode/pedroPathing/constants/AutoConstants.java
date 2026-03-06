package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutoConstants {

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(11.97484)
            .forwardZeroPowerAcceleration(0)
            .lateralZeroPowerAcceleration(0)
            .translationalPIDFCoefficients(new PIDFCoefficients(0, 0, 0, 0))
            .headingPIDFCoefficients(new PIDFCoefficients(0, 0, 0, 0.0))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0,0.0,0,0,0.0))
            .centripetalScaling(0);


    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .xVelocity(0)
            .yVelocity(0)

            .rightFrontMotorName("frontRight")
            .rightRearMotorName("backRight")
            .leftRearMotorName("backLeft")
            .leftFrontMotorName("frontLeft")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

    public static ThreeWheelConstants localizerConstants = new ThreeWheelConstants()
            .forwardTicksToInches(0)
            .strafeTicksToInches(0)
            .turnTicksToInches(0)
            .leftPodY(6)
            .rightPodY(-6)
            .strafePodX(-5.25)
            .leftEncoder_HardwareMapName("frontLeft")
            .rightEncoder_HardwareMapName("backRight")
            .strafeEncoder_HardwareMapName("frontRight")
            .leftEncoderDirection(Encoder.FORWARD)
            .rightEncoderDirection(Encoder.REVERSE)
            .strafeEncoderDirection(Encoder.REVERSE);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .build();
    }
}
