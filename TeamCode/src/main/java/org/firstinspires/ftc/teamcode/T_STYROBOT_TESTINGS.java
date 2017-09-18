package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Eric D'Urso on 9/17/2017.
 */

public class T_STYROBOT_TESTINGS extends LinearOpMode {
    private DcMotor andyMarkWithEncoder1;
    private DcMotor andyMarkWithEncoder2;
    private DcMotor tetrixWithEncoder;
    private DcMotor tetrix;
    private Servo S1;
    private Servo S2;
    private Servo S3;
    private Servo S360;
    private ColorSensor ColorSensor;
    //IDK Y
    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;

    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        //Gyro Stuff
        boolean lastResetState = false;
        boolean curResetState  = false;

        andyMarkWithEncoder1 = hardwareMap.dcMotor.get("AMWE1");
        andyMarkWithEncoder2 = hardwareMap.dcMotor.get("AMWE2");
        tetrixWithEncoder = hardwareMap.dcMotor.get("TWE");
        tetrix = hardwareMap.dcMotor.get("T");
        S1 = hardwareMap.servo.get("S1");
        S2 = hardwareMap.servo.get("S2");
        S3 = hardwareMap.servo.get("S3");
        S360 = hardwareMap.servo.get("S360");
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;

        waitForStart();

    }
}
 