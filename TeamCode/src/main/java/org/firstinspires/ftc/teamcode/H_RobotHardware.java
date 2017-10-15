package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class H_RobotHardware
{
    public com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;
    public com.qualcomm.robotcore.hardware.ColorSensor GlyphSensor;
    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor Lift;
    public DcMotor arm;
    public Servo RightClaw;
    public Servo LeftClaw;
    public Servo moveFlick;
    public Servo MoveSensor;
    public Servo MMS;
    public static final ElapsedTime timer = new ElapsedTime();
    public static final boolean A = true;
    HardwareMap hwMap           = null;
    private ElapsedTime period  = new ElapsedTime();
    public H_RobotHardware(){

    }
    public void init(HardwareMap ahwMap, boolean auto) {
        hwMap = ahwMap;
        ColorSensor = hwMap.colorSensor.get("ColorSensor");
        GlyphSensor = hwMap.colorSensor.get("GlyphSensor");
        modernRoboticsI2cGyro = hwMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;
        leftDrive = hwMap.dcMotor.get("leftDrive");
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDrive.setPower(0);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive = hwMap.dcMotor.get("rightDrive");
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setPower(0);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift = hwMap.dcMotor.get("Lift");
        Lift.setDirection(DcMotorSimple.Direction.FORWARD);
        Lift.setPower(0);
        arm = hwMap.dcMotor.get("Arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setPower(0);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        moveFlick = hwMap.servo.get("Jewel");
        moveFlick.setPosition(0.5);
        RightClaw = hwMap.servo.get("RightClaw");
        LeftClaw = hwMap.servo.get("LeftClaw");
        RightClaw.setPosition(1);
        LeftClaw.setPosition(0.25);
        MMS = hwMap.servo.get("MMS");
        MMS.setPosition(0.75);//up, 0 is down
        MoveSensor = hwMap.servo.get("MoveSensor");
        MoveSensor.setPosition(0.5);
        final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // TETRIX MOTORS = 1440, andymark = 1120
        final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
        final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
        final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);
        if(auto){
            modernRoboticsI2cGyro.calibrate();
            timer.reset();
            while (modernRoboticsI2cGyro.isCalibrating()) {

            }
        }

    }
 }

