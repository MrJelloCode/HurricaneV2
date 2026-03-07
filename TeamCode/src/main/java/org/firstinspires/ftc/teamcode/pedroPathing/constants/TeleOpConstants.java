package org.firstinspires.ftc.teamcode.pedroPathing.constants;

public class TeleOpConstants {

    /* ========== DRIVETRAIN ========== */
    public static class Drivetrain {
        public static String FRONT_LEFT_MOTOR_NAME = "frontLeft";
        public static String FRONT_RIGHT_MOTOR_NAME = "frontRight";
        public static String BACK_LEFT_MOTOR_NAME = "backLeft";
        public static String BACK_RIGHT_MOTOR_NAME = "backRight";
    }

    /* ========== FLYWHEEL ========== */
    public static class Flywheel {
        public static String RIGHT_FLYWHEEL_MOTOR_NAME = "flywheel1";
        public static String LEFT_FLYWHEEL_MOTOR_NAME = "flywheel2";

        public static double FLYWHEEL_DIAMETER_INCHES = 3.0;
        public static double FLYWHEEL_HEIGHT_INCHES = 12.38335;
        public static double HOOD_ANGLE_DEGREES = 55;

        public static double KP = 0;
        public static double KV = 0;
        public static double KS = 0;

        public static double FAR_VEL = 2000;
        public static double CLOSE_VEL = 1500;
    }


    public static class Intake{
        public static String INTAKE_MOTOR_NAME = "intake";
        public static double POWER = 0.8;
    }

    public static class Limelight{
        public static String LIMELIGHT_NAME = "limelight";
        public static int LIMELIGHT_POLL_RATE_HZ = 100;
        public static int DEFAULT_PIPELINE = 0;

        public static double LIMELIGHT_HEIGHT_INCHES = 13.718;
        public static double LIMELIGHT_PITCH_DEGREES = 11.33187;

        public static double TARGET_HEIGHT_INCHES = 30.0;


        public static double DISTANCE_OFFSET_INCHES = 0.0;
        public static double RPM_OFFSET = 0.0;

        public static final double GRAVITY = 386.09;



    }


}
