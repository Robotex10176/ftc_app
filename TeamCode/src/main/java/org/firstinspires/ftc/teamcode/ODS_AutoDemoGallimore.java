package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Eric D'Urso on 8/18/2017.
 */
@Autonomous (name = "Gallimore Auto")  //NOTE the white tape must be facing the back
public class ODS_AutoDemoGallimore extends LinearOpMode {//start in the bottom left corner
    private DcMotor EpWn;
    private DcMotor WpEn;
    private DcMotor SpNn;
    private DcMotor NpSn;
    OpticalDistanceSensor ODS;
    private double PWR = 0.10;
    @Override
    public void runOpMode() throws InterruptedException {
        ODS = hardwareMap.opticalDistanceSensor.get("ODS");
        EpWn = hardwareMap.dcMotor.get("EpWn");
        WpEn = hardwareMap.dcMotor.get("WpEn");
        SpNn = hardwareMap.dcMotor.get("SpNn");
        NpSn = hardwareMap.dcMotor.get("NpSn");
        waitForStart();
        while (opModeIsActive()){
            UP_RIGHT();
            DOWN_RIGHT();
            DOWN_LEFT();
            UP_LEFT();
            idle();
        }
    }

    public void UP_RIGHT(){
        ERROR_CHECK();
        while(ODS.getRawLightDetected()< 1.0){
            EpWn.setPower(-PWR);
            WpEn.setPower(PWR);
            SpNn.setPower(PWR);
            NpSn.setPower(-PWR);
            ERROR_CHECK();
            idle();
        }
        telemetry.addLine("TOP RIGHT");
        sleep(3000);
        EpWn.setPower(PWR);
        WpEn.setPower(-PWR);
        SpNn.setPower(PWR);
        NpSn.setPower(-PWR);
        sleep(500);
    }
    public void UP_LEFT(){
        ERROR_CHECK();
        while(ODS.getRawLightDetected()< 1.0){
            EpWn.setPower(-PWR);
            WpEn.setPower(PWR);
            SpNn.setPower(-PWR);
            NpSn.setPower(PWR);
            ERROR_CHECK();
            idle();
        }
        telemetry.addLine("TOP LEFT");
        sleep(3000);
        EpWn.setPower(PWR);
        WpEn.setPower(-PWR);
        SpNn.setPower(-PWR);
        NpSn.setPower(PWR);
        sleep(500);
    }
    public void DOWN_LEFT(){
        ERROR_CHECK();
        while(ODS.getRawLightDetected()< 1.0){
            EpWn.setPower(PWR);
            WpEn.setPower(-PWR);
            SpNn.setPower(-PWR);
            NpSn.setPower(PWR);
            ERROR_CHECK();
            idle();
        }
        telemetry.addLine("BOTTOM LEFT");
        sleep(3000);
        EpWn.setPower(-PWR);
        WpEn.setPower(PWR);
        SpNn.setPower(-PWR);
        NpSn.setPower(PWR);
        sleep(500);
    }
    public void DOWN_RIGHT(){
        ERROR_CHECK();
        while(ODS.getRawLightDetected()< 1.0){
            EpWn.setPower(PWR);
            WpEn.setPower(-PWR);
            SpNn.setPower(PWR);
            NpSn.setPower(-PWR);
            ERROR_CHECK();
            idle();
        }
        telemetry.addLine("BOTTOM RIGHT");
        sleep(3000);
        EpWn.setPower(-PWR);
        WpEn.setPower(PWR);
        SpNn.setPower(PWR);
        NpSn.setPower(-PWR);
        sleep(500);

    }
    public void ERROR_CHECK(){
        if( (1.5 < ODS.getRawLightDetected()) && (ODS.getRawLightDetected() < 3 )){
            EpWn.setPower(0);
            WpEn.setPower(0);
            SpNn.setPower(0);
            NpSn.setPower(0);
            telemetry.addLine("ODS VALUE ERROR STOP PROGRAM");
        }
    }
}
