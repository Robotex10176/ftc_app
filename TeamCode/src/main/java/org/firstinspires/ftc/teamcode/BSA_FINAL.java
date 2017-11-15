package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;













@Autonomous(name = "BSA")
public class BSA_FINAL extends LinearOpMode {
    public com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;
    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;
    public DcMotor leftDrive;
    public DcMotor rightDrive;















    @Override
    public void runOpMode() throws InterruptedException {
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDrive.setPower(0);
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setPower(0);
        ClaibrateGyro();



        waitForStart();










        //MAIN CODE


        telemetry.addLine("RUNNING AUTONOMOUS NOW");
        telemetry.update();
        DriveForward(0.1, 2.6);
        Turn(0.05);
        DriveToColor(0.05);
        sleep(1000);
        IdentifyColor();













        //DO BELOW IF WE HAVE A CONTROLLER
        //while (!gamepad1.start){
        //    telemetry.addLine("PRESS START TO RUN TELEOP");
        //    telemetry.update();
        //}
        //while (opModeIsActive()){
        //    rightDrive.setPower(cubeInput(gamepad1.right_stick_y));
        //    leftDrive.setPower(cubeInput(gamepad1.left_stick_y));
        //}
    }//end of runopmode















    //METHODS


    public float cubeInput (float in){
        return (in*in*in);
    }
















    public void IdentifyColor(){
        String color = "Unknown";
        if ((ColorSensor.red() > ColorSensor.blue())&&(ColorSensor.red() > ColorSensor.green())){
            color = "Red";
        } else if ((ColorSensor.blue() > ColorSensor.red())&&(ColorSensor.blue() > ColorSensor.green())){
            color = "Blue";
        } else if ((ColorSensor.green() > ColorSensor.blue())&&(ColorSensor.green() > ColorSensor.red())){
            color = "Green";
        } else {}
        telemetry.addData("I See The Color ", color);
        telemetry.update();
    }













    public void DriveToColor(double pwr){
        while (ColorSensor.argb() < 10){
        //while (ColorSensor.red()<10 || ColorSensor.blue()<10 || ColorSensor.green()<10 ){
            leftDrive.setPower(pwr);
            rightDrive.setPower(pwr);
            telemetry.addData("I See   ", ColorSensor.argb());
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }














    public void DriveForward(double pwr, double duration){
        leftDrive.setPower(pwr);
        rightDrive.setPower(pwr);
        sleep(Math.round(duration * 1000));
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(1000);
    }















    public void Turn(double pwr){
        ClaibrateGyro();
        float zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        float ta = 90;
        float tol = 2;
        //while ((zAngle > (ta-tol))&&(zAngle < (ta+tol))) {
        while (zAngle < ta){
            leftDrive.setPower(-pwr);
            rightDrive.setPower(pwr);
            zAngle = java.lang.Math.round(gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
            telemetry.addData("Angle is    ", zAngle);
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
















    public void ClaibrateGyro (){
        modernRoboticsI2cGyro.calibrate();
        while (modernRoboticsI2cGyro.isCalibrating()) {
            telemetry.addLine("Calibrating |. . . ");
            telemetry.update();
            sleep(500);
            telemetry.addLine("Calibrating  .|. . ");
            telemetry.update();
            sleep(500);
            telemetry.addLine("Calibrating  . .|. ");
            telemetry.update();
            sleep(500);
            telemetry.addLine("Calibrating  . . .|");
            telemetry.update();
            sleep(500);
        }
    }

















}//end of class

