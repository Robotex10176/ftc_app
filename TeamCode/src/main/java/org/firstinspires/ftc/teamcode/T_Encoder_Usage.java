package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Eric D'Urso on 9/20/2017.
 */
@Autonomous (name = "Encoder Usage Test", group = "Tests")
public class T_Encoder_Usage extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    double power = 0.15;
    @Override
    public void runOpMode() throws InterruptedException {
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForStart();
        int TETRIX_TICKS_PER_REVOLUTION = 1440;
        int ANDYMARK_TICKS_PER_REVOLUTION = 1120;//1120:CIRCUMPHRENCE of wheel is ratio of ticks to distance traveled
        DriveForwardDistance(power, 12);
    }
    public void DriveForwardDistance(double power, int distance){
        motorRight.setTargetPosition(distance);
        motorLeft.setTargetPosition(distance);
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        DriveForward();
        while (motorLeft.isBusy() && motorRight.isBusy()){
            //exactly, do nothing
        }
        StopMotors ();
    }
    public void DriveForward (){
        motorLeft.setPower(power);
        motorRight.setPower(-power);//________________________________CHANGE NEGATIVE IF NEEDED__________________________!
    }
    public void StopMotors (){
        motorRight.setPower(0);
        motorLeft.setPower(0);
    }
}
