package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Eric D'Urso on 8/28/2017.
 */
@Autonomous (name = "GALLIMORESAUTONONOMOUNOMUS")
public class AUTOLINESEEIN extends LinearOpMode {
    private DcMotor EpWn;
    private DcMotor WpEn;
    private DcMotor SpNn;
    private DcMotor NpSn;
    private int DIRECTION = 1;
    private double WANTED_PWR = 0.15;
    private double PWR = DIRECTION*WANTED_PWR;
    OpticalDistanceSensor ODS;
    @Override
    public void runOpMode() throws InterruptedException {
        ODS = hardwareMap.opticalDistanceSensor.get("ODS");
        EpWn = hardwareMap.dcMotor.get("EpWn");
        WpEn = hardwareMap.dcMotor.get("WpEn");//
        SpNn = hardwareMap.dcMotor.get("SpNn");
        NpSn = hardwareMap.dcMotor.get("NpSn");//
        WpEn.setDirection(DcMotor.Direction.REVERSE);
        NpSn.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()){
            while (ODS.getRawLightDetected()<1.25){

            }
        }
    }
}
