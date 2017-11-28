package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;


//NEW CLASS



@Autonomous(name = "Y_GALLIMORE_MOTOR_TEST", group = "GALLIMORE")
@Disabled
class motorTest extends LinearOpMode {
    private DcMotor EpWn;
    private DcMotor WpEn;
    private DcMotor SpNn;
    private DcMotor NpSn;
    @Override
    public void runOpMode() throws InterruptedException {
        EpWn = hardwareMap.dcMotor.get("EpWn");
        WpEn = hardwareMap.dcMotor.get("WpEn");
        SpNn = hardwareMap.dcMotor.get("SpNn");
        NpSn = hardwareMap.dcMotor.get("NpSn");
        waitForStart();
        while (opModeIsActive()){
            telemetry.addLine("N");
            EpWn.setPower(0.5);
            sleep(5000);
            EpWn.setPower(0);
            telemetry.addLine("S");
            WpEn.setPower(0.5);
            sleep(5000);
            WpEn.setPower(0);
            telemetry.addLine("E");
            SpNn.setPower(0.5);
            sleep(5000);
            SpNn.setPower(0);
            telemetry.addLine("W");
            NpSn.setPower(0.5);
            sleep(5000);
            NpSn.setPower(0);
            idle();
        }
    }
}




//NEW CLASS




@Autonomous (name = "Y_GALLIMORE_AUTO_V2", group = "GALLIMORE")
@Disabled
class Auto_V2 extends LinearOpMode {
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



//NEW CLASS



@Autonomous (name = "Y_GALLIMORE_AUTO_V3", group = "GALLIMORE")
@Disabled
class Auto_V3 extends LinearOpMode {
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




//NEW CLASS



@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "GALLIMORE_TELEOP", group = "GALLIMORE")
@Disabled
class TeleOp extends OpMode {
    private DcMotor EpWn;
    private DcMotor WpEn;
    private DcMotor SpNn;
    private DcMotor NpSn;
    private float scaleController(float in){
        //return ( java.lang.Math.signum(in)*in*in);
        return (in*in*in);
    }

    @Override
    public void init() {
        EpWn = hardwareMap.dcMotor.get("EpWn");
        WpEn = hardwareMap.dcMotor.get("WpEn");
        SpNn = hardwareMap.dcMotor.get("SpNn");
        NpSn = hardwareMap.dcMotor.get("NpSn");

    }

    @Override
    public void loop() {
        //primary driving block
        if ((gamepad1.right_trigger < 0.05 )&&(gamepad1.left_trigger < 0.05 )) {
            EpWn.setPower(scaleController(-gamepad1.left_stick_x));
            WpEn.setPower(scaleController(gamepad1.left_stick_x));
            SpNn.setPower(scaleController(gamepad1.left_stick_y));
            NpSn.setPower(scaleController(-gamepad1.left_stick_y));
        }
        //done add conditional below
        if ((java.lang.Math.abs(gamepad1.left_stick_x) < 0.05)&&(java.lang.Math.abs(gamepad1.left_stick_x) < 0.05)) {
            //this block rotates the robot right
            EpWn.setPower(scaleController(gamepad1.right_trigger));
            WpEn.setPower(scaleController(gamepad1.right_trigger));
            SpNn.setPower(scaleController(gamepad1.right_trigger));
            NpSn.setPower(scaleController(gamepad1.right_trigger));
        }
        if ((java.lang.Math.abs(gamepad1.left_stick_x) < 0.05)&&(java.lang.Math.abs(gamepad1.left_stick_x) < 0.05)) {
            //this block rotates the robot right
            EpWn.setPower(scaleController(-gamepad1.left_trigger));
            WpEn.setPower(scaleController(-gamepad1.left_trigger));
            SpNn.setPower(scaleController(-gamepad1.left_trigger));
            NpSn.setPower(scaleController(-gamepad1.left_trigger));
        }


    }

}
