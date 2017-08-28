package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Eric D'Urso on 8/28/2017.
 */
@Autonomous (name = "GALLIMORE_AUTO_V1", group = "GALLIMORE")
public class GALLIMORE_AUTO_V1 extends LinearOpMode {
    private DcMotor EpWn;
    private DcMotor WpEn;
    OpticalDistanceSensor ODS;
    private double PWR;
    private double STOPPWR = 0.0;
    private int DIRECTION = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        ODS = hardwareMap.opticalDistanceSensor.get("ODS");
        EpWn = hardwareMap.dcMotor.get("EpWn");
        WpEn = hardwareMap.dcMotor.get("WpEn");
        WpEn.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()){
            PWR = 0.15*DIRECTION;
            while(ODS.getRawLightDetected()<1.25){
                EpWn.setPower(PWR);
                WpEn.setPower(PWR);
                idle();
            }
            DIRECTION = -DIRECTION;
            PWR = 0.15*DIRECTION;
            EpWn.setPower(STOPPWR);
            WpEn.setPower(STOPPWR);
            telemetry.addLine("I SEE A LINE YAY!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            EpWn.setPower(PWR);
            WpEn.setPower(PWR);
            sleep(500);
            PWR = 0.15*DIRECTION;
            while(ODS.getRawLightDetected()<1.25){
                EpWn.setPower(PWR);
                WpEn.setPower(PWR);
                idle();
            }
            DIRECTION = -DIRECTION;
            PWR = 0.15*DIRECTION;
            EpWn.setPower(STOPPWR);
            WpEn.setPower(STOPPWR);
            telemetry.addLine("I SEE A ANOTHER LINE YAY!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            EpWn.setPower(PWR);
            WpEn.setPower(PWR);
            sleep(500);
            PWR = 0.15*DIRECTION;
        }
    }
}
