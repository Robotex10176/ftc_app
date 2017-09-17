package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
@Autonomous (name = "Y_GALLIMORE_AUTO_V3", group = "GALLIMORE")
@Disabled
public class Y_GALLIMORE_AUTO_V3 extends LinearOpMode {
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
            MOVE();
            HOR_DIRECTION = -HOR_DIRECTION;
            telemetry.addLine("1");
            MOVE();
            VERT_DIRECTION = -VERT_DIRECTION;
            telemetry.addLine("2");
            MOVE();
            HOR_DIRECTION = -HOR_DIRECTION;//used to be vert
            telemetry.addLine("3");
            MOVE();
            VERT_DIRECTION = -VERT_DIRECTION;//used to be hor
            telemetry.addLine("4");
        }
    }
    public void MOVE(){
        PWR_UPDATE();
        MOVE_TILL_LINE();
        STOP();
        REVERSE();
    }
    public void BACK_UP(){
        EpWn.setPower(HOR_PWR);
        WpEn.setPower(HOR_PWR);
        SpNn.setPower(VERT_PWR);
        NpSn.setPower(VERT_PWR);
    }
    public void STOP(){
        EpWn.setPower(NO_POW);
        WpEn.setPower(NO_POW);
        SpNn.setPower(NO_POW);
        NpSn.setPower(NO_POW);
    }
    public void MOVE_TILL_LINE(){
        while (ODS.getRawLightDetected()<1.25){
            BACK_UP();
        }
    }
    public void PWR_UPDATE(){
        VERT_PWR = VERT_DIRECTION*WANTED_PWR;
        HOR_PWR = HOR_DIRECTION*WANTED_PWR;
    }
    public void REVERSE(){
        VERT_DIRECTION = -VERT_DIRECTION;//SET TO REVERSE
        HOR_DIRECTION = -HOR_DIRECTION;
        PWR_UPDATE();
        BACK_UP();//BACKS UP
        sleep(500);
        STOP();
    }
}
