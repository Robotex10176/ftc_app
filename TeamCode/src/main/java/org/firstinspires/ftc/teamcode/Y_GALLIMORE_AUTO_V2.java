package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Eric D'Urso on 8/28/2017.
 */
@Autonomous (name = "Y_GALLIMORE_AUTO_V2", group = "GALLIMORE")
@Disabled
public class Y_GALLIMORE_AUTO_V2 extends LinearOpMode {
    private DcMotor EpWn;
    private DcMotor WpEn;
    private DcMotor SpNn;
    private DcMotor NpSn;
    private int VERT_DIRECTION = 1;
    private double WANTED_PWR = 0.15;
    private double VERT_PWR = VERT_DIRECTION*WANTED_PWR;
    private int HOR_DIRECTION = 1;
    private double HOR_PWR = HOR_DIRECTION*WANTED_PWR;
    private int NO_POW = 0;
    OpticalDistanceSensor ODS;
    @Override
    public void runOpMode() throws InterruptedException {
        ODS = hardwareMap.opticalDistanceSensor.get("ODS");
        EpWn = hardwareMap.dcMotor.get("EpWn");
        WpEn = hardwareMap.dcMotor.get("WpEn");
        SpNn = hardwareMap.dcMotor.get("SpNn");
        NpSn = hardwareMap.dcMotor.get("NpSn");
        WpEn.setDirection(DcMotor.Direction.REVERSE);
        NpSn.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()){
            VERT_PWR = VERT_DIRECTION*WANTED_PWR;
            HOR_PWR = HOR_DIRECTION*WANTED_PWR;
            while (ODS.getRawLightDetected()<1.25){
                EpWn.setPower(HOR_PWR);
                WpEn.setPower(HOR_PWR);
                SpNn.setPower(VERT_PWR);
                NpSn.setPower(VERT_PWR);
            }
            EpWn.setPower(NO_POW);
            WpEn.setPower(NO_POW);
            SpNn.setPower(NO_POW);
            NpSn.setPower(NO_POW);
            VERT_DIRECTION = -VERT_DIRECTION;
            HOR_DIRECTION = -HOR_DIRECTION;
            VERT_PWR = VERT_DIRECTION*WANTED_PWR;
            HOR_PWR = HOR_DIRECTION*WANTED_PWR;
            EpWn.setPower(HOR_PWR);
            WpEn.setPower(HOR_PWR);
            SpNn.setPower(VERT_PWR);
            NpSn.setPower(VERT_PWR);
            sleep(500);
            EpWn.setPower(NO_POW);
            WpEn.setPower(NO_POW);
            SpNn.setPower(NO_POW);
            NpSn.setPower(NO_POW);

        }
    }
}
