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
    private DcMotor NorthMotor;
    private DcMotor SouthMotor;
    private DcMotor EastMotor;
    private DcMotor WestMotor;
    OpticalDistanceSensor ODS;
    @Override
    public void runOpMode() throws InterruptedException {
        ODS = hardwareMap.opticalDistanceSensor.get("ODS");
        NorthMotor = hardwareMap.dcMotor.get("NorthDrive");
        SouthMotor = hardwareMap.dcMotor.get("SouthDrive");
        EastMotor = hardwareMap.dcMotor.get("EastDrive");
        WestMotor = hardwareMap.dcMotor.get("WestDrive");
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
        NorthMotor.setPower(-0.75);
        SouthMotor.setPower(0.75);
        EastMotor.setPower(0.75);
        WestMotor.setPower(-0.75);
        sleep(1000);
        while(ODS.getRawLightDetected()> 1.0){
            NorthMotor.setPower(-0.75);
            SouthMotor.setPower(0.75);
            EastMotor.setPower(0.75);
            WestMotor.setPower(-0.75);
            ERROR_CHECK();
        }
    }
    public void UP_LEFT(){
        ERROR_CHECK();
        NorthMotor.setPower(-0.75);
        SouthMotor.setPower(0.75);
        EastMotor.setPower(-0.75);
        WestMotor.setPower(0.75);
        sleep(1000);
        while(ODS.getRawLightDetected()> 1.0){
            NorthMotor.setPower(-0.75);
            SouthMotor.setPower(0.75);
            EastMotor.setPower(-0.75);
            WestMotor.setPower(0.75);
            ERROR_CHECK();
        }

    }
    public void DOWN_LEFT(){
        ERROR_CHECK();
        NorthMotor.setPower(0.75);
        SouthMotor.setPower(-0.75);
        EastMotor.setPower(-0.75);
        WestMotor.setPower(0.75);
        sleep(1000);
        while(ODS.getRawLightDetected()> 1.0){
            NorthMotor.setPower(0.75);
            SouthMotor.setPower(-0.75);
            EastMotor.setPower(-0.75);
            WestMotor.setPower(0.75);
            ERROR_CHECK();
        }

    }
    public void DOWN_RIGHT(){
        ERROR_CHECK();
        NorthMotor.setPower(0.75);
        SouthMotor.setPower(-0.75);
        EastMotor.setPower(0.75);
        WestMotor.setPower(-0.75);
        sleep(1000);
        while(ODS.getRawLightDetected()> 1.0){
            NorthMotor.setPower(0.75);
            SouthMotor.setPower(-0.75);
            EastMotor.setPower(0.75);
            WestMotor.setPower(-0.75);
            ERROR_CHECK();
        }

    }
    public void ERROR_CHECK(){
        if( 1 < ODS.getRawLightDetected() && ODS.getRawLightDetected() < 3.5 ){
            NorthMotor.setPower(0);
            SouthMotor.setPower(0);
            EastMotor.setPower(0);
            WestMotor.setPower(0);
            telemetry.addLine("ODS VALUE ERROR STOP PROGRAM");
        }
    }
}
