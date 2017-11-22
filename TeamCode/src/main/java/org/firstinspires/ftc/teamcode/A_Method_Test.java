package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


@Autonomous(name = "METHOD TEST")
public class A_Method_Test extends LinearOpMode {
    A_Main robot = new A_Main();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, true);
        waitForStart();
        //Testing Methods Below

        //TurnTestSeries();
        //DriveSeriesNC();
        //DriveSeriesGC();

        telemetry.addLine("FINISHED");
        telemetry.update();
        sleep(1000000000);
        //.
        //TODO - DriveGyroCorrection - get it to work
        //TODO - TURNS - Specific amount
        //TODO - Drive Distance to Work
        //TODO - Update methods as we find these work
    }




    //Methods to turn, drive distance, etc.
    public void TurnTestSeries (){
        //Turns 90 degrees 4 times
        SmartTurnLeft(90, 1);
        telemetry.addLine("TURNED 90 DEGREES");
        telemetry.update();
        sleep(2000);
        SmartTurnLeft(90, 1);
        telemetry.addLine("TURNED 180 DEGREES");
        telemetry.update();
        sleep(2000);
        SmartTurnLeft(90, 1);
        telemetry.addLine("TURNED 270 DEGREES");
        telemetry.update();
        sleep(2000);
        SmartTurnLeft(90, 1);
        telemetry.addLine("TURNED 360 DEGREES");
        telemetry.update();
        sleep(2000);
    }
    public void DriveSeriesNC(){
        int numOfRuns = 4;
        double initialDis = 20.25;//Change these as needed
        double disIncrease = 10.25;//u can use actual auto distances.
        for (int i = 0; i < numOfRuns; i++){
            telemetry.addData("Driving ", initialDis);
            telemetry.update();
            DriveNoCorrection(initialDis, 1, 1);//that power
            telemetry.addLine("Drove");
            telemetry.addLine("Move Me Back To Start");
            telemetry.update();
            sleep(20000);//20 secs
            initialDis += 1.0;
        }
    }
    public void DriveSeriesGC(){
        int numOfRuns = 4;
        double initialDis = 20.25;//Change these as needed
        double disIncrease = 10.25;//u can use actual auto distances.
        for (int i = 0; i < numOfRuns; i++){
            telemetry.addData("Driving ", initialDis);
            telemetry.update();
            DriveGyroCorrection(initialDis, 1, 1);//that power
            telemetry.addLine("Drove");
            telemetry.addLine("Move Me Back To Start");
            telemetry.update();
            sleep(20000);//20 secs
            initialDis += 1.0;
        }
    }














     public double scaleController(double in){
        //return ( java.lang.Math.signum(in)*in*in);
        return (in*in*in);
    }
    public void CloseClaw (){
        robot.RightClaw.setPosition(0.50);
        robot.LeftClaw.setPosition(0.50);
    }
    public void OpenClaw (){
        robot.RightClaw.setPosition(0.9);
        robot.LeftClaw.setPosition(0.20);
    }
    public void FlatClaw(){
        robot.RightClaw.setPosition(0.3);
        robot.LeftClaw.setPosition(0);
    }
    public void CloseRelic() {
        robot.RelicClaw.setPosition(0);
    }
    public void OpenRelic() {
        robot.RelicClaw.setPosition(0.5);
    }
    public void moveArm (double Degrees, double Power){
        int newLeftTarget;
        final double COUNTS_PER_MOTOR_REV = 1120;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DEGREES = (Degrees*(COUNTS_PER_MOTOR_REV/360));//3.111111111111111111111111111 ticks per degree
        if (opModeIsActive()) {
            newLeftTarget = robot.arm.getCurrentPosition() + (int) (DEGREES);
            robot.arm.setTargetPosition(newLeftTarget);
            robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.arm.setPower(Math.abs(Power));
            while (opModeIsActive() && (robot.arm.isBusy())) {

            }
        }
    }
    public void SeeOurColor (){ robot.moveFlick.setPosition(0); }
    public void DontSeeOurColor (){
        robot.moveFlick.setPosition(1);
    }
    public void JewelServoReturn (double KNOW_THAT_YOU_HAVE_TO_SLEEP_BEFORE_THIS){
        robot.moveFlick.setPosition(0.5);
    }
    public void DriveNoCorrection (double DesiredDistance, double RightPower, double LeftPower){
        int newLeftTarget;
        int newRightTarget;
        final double COUNTS_PER_MOTOR_REV = 1440;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared DOWN
        final double WHEEL_DIAMETER_INCHES = 3.95;     // used to be 3.8125
        final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
        if (opModeIsActive()) {
            newLeftTarget = robot.leftDrive.getCurrentPosition() + (int) (DesiredDistance * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int) (DesiredDistance * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftDrive.setPower(Math.abs(LeftPower));
            robot.rightDrive.setPower(Math.abs(RightPower));
            while (opModeIsActive() && (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {}
            robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightDrive.setPower(0);
            robot.leftDrive.setPower(0);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public void DriveGyroCorrection(double DesiredDistance, double RightPower, double LeftPower){
        double dg10incrs = 0.01;
        double dg22incrs = 0.05;
        double dg45incrs = 0.1;
        float DesiredAngle = 0;
        int newLeftTarget;
        int newRightTarget;
        float displacement;
        final double COUNTS_PER_MOTOR_REV = 1440;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
        final double WHEEL_DIAMETER_INCHES = 3.8125;     // For figuring circumference
        final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
        if (opModeIsActive()) {
            newLeftTarget = robot.leftDrive.getCurrentPosition() + (int) (DesiredDistance * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int) (DesiredDistance * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftDrive.setPower(Math.abs(LeftPower));
            robot.rightDrive.setPower(Math.abs(RightPower));
            while (opModeIsActive() && (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {
                float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
                if (zAngle > DesiredAngle){
                    LeftPower = LeftPower + ((zAngle - DesiredAngle)/90) * 0.05;
                    RightPower = RightPower - ((zAngle - DesiredAngle)/90) * 0.05;
                } else {
                    LeftPower = LeftPower - (-1*(zAngle - DesiredAngle)/90) * 0.05;
                    RightPower = RightPower + (-1*(zAngle - DesiredAngle)/90) * 0.05;
                }
                robot.leftDrive.setPower(Math.abs(LeftPower));
                robot.rightDrive.setPower(Math.abs(RightPower));
            }
            float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            if (zAngle != DesiredAngle){
                robot.leftDrive.setPower(-0.05);
                robot.leftDrive.setPower(0.05);
            }
        }
    }
    public float AngularSeparation (float a, float b){
        float rv;
        rv = Math.abs(a-b);
        if (rv >= 180){
            rv = 360 - rv;
        }
        return rv;
    }
    public void SmartTurnRight(float Angle, double Power){//turn right is clockwise
        //code to turn untill an angle ex 0, 90, -90
        float zAngle;
        float targetAngle;
        zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        //Set target direction in range -180 - 180;
        targetAngle = (zAngle - Angle + 180);
        while (targetAngle > 360){ targetAngle = targetAngle - 360; }
        while (targetAngle < 0){ targetAngle = targetAngle + 360; }
        targetAngle = targetAngle - 180;
        float as;
        as = AngularSeparation(zAngle, targetAngle);
        while (as > 1.0){
            as = AngularSeparation(zAngle, targetAngle);
            if(as < 10) {
                robot.leftDrive.setPower(0.2 * Power);
                robot.rightDrive.setPower(-0.2 * Power);
            } else if (as < 40){
                robot.leftDrive.setPower(0.5 * Power);
                robot.rightDrive.setPower(-0.5 * Power);
            }else {
                robot.leftDrive.setPower(Power);
                robot.rightDrive.setPower(-Power);
            }
            zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                    AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }
    public void SmartTurnLeft (float Angle, double Power){
        float zAngle;
        float targetAngle;
        zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        //Set target direction in range -180 - 180;
        targetAngle = (zAngle + Angle + 180);
        while (targetAngle > 360){ targetAngle = targetAngle - 360; }
        while (targetAngle < 0){ targetAngle = targetAngle + 360; }
        targetAngle = targetAngle - 180;
        float as;
        as = AngularSeparation(zAngle, targetAngle);
        while (as > 1.0){
            as = AngularSeparation(zAngle, targetAngle);
            if(as < 10) {
                robot.leftDrive.setPower(-0.2 * Power);
                robot.rightDrive.setPower(0.2 * Power);
            } else if (as < 40){
                robot.leftDrive.setPower(-0.5 * Power);
                robot.rightDrive.setPower(0.5 * Power);
            }else {
                robot.leftDrive.setPower(-Power);
                robot.rightDrive.setPower(Power);
            }
            zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                    AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }
}
