package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class TurretSubsystem {

    private final DcMotorEx turretMotor;

    /* ================= ENCODER CONSTANTS ================= */

    private static final double TICKS_PER_REV = 435;   // motor encoder CPR
    private static final double GEAR_RATIO = 3;      // MIGHT HAVE TO CHANGE THIS

    /* ================= SAFETY LIMITS ================= */

    private static final double MAX_POWER = 1;
    private static final double MIN_ANGLE = -180.0;
    private static final double MAX_ANGLE = 180.0;

    /* ================= CONSTRUCTOR ================= */

    public TurretSubsystem(DcMotorEx turretMotor) {
        this.turretMotor = turretMotor;
    }

}