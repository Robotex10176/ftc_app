package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Eric D'Urso on 10/15/2017.
 */

public class T_Glyph_Persision extends LinearOpMode {
    H_RobotHardware robot = new H_RobotHardware();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, true);
        waitForStart();
        while (robot.GlyphSensor.red()<30){
            double currentPos = robot.MoveSensor.getPosition();
            double quantity = 0.1;
            if ((currentPos + quantity)<=1) {
                robot.MoveSensor.setPosition(currentPos + quantity);
            } else if ((currentPos + quantity)>1){
                robot.MoveSensor.setPosition(currentPos - quantity);
            }// WORK OUT THIS LOGIC
            sleep(10);
        }
        double CurrentPos = robot.MoveSensor.getPosition();
        double Displacement = CurrentPos - 0.5;
        if (Displacement > 0){
            dumbDrive(-2, -0.1, -0.1);
            SmartTurnRight(Displacement, 0.1);
            dumbDrive(3, 0.1, 0.2);
        } else if (Displacement < 0){
            dumbDrive(-2, -0.1, -0.1);
            SmartTurnLeft(Displacement, 0.1);
            dumbDrive(3, 0.2, 0.1);
        } else {

        }
    }
    public void dumbDrive (double DesiredDistance, double RightPower, double LeftPower){
        int newLeftTarget;
        int newRightTarget;
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
// DO NOTHING BECUZ THIS IS DUMB

            }
            robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightDrive.setPower(0);
            robot.leftDrive.setPower(0);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public double AngularSeparation (double a, double b){
        double rv;
        rv = Math.abs(a-b);
        if (rv >= 180){
            rv = 360 - rv;
        }
        return rv;
    }
    public void SmartTurnRight(double Angle, double Power){//turn right is clockwise
        //code to turn untill an angle ex 0, 90, -90
        double zAngle;
        double targetAngle;
        zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        //Set target direction in range -180 - 180;
        targetAngle = (zAngle - Angle + 180);
        while (targetAngle > 360){ targetAngle = targetAngle - 360; }
        while (targetAngle < 0){ targetAngle = targetAngle + 360; }
        targetAngle = targetAngle - 180;

        while (AngularSeparation(zAngle, targetAngle)> 2.0){
            robot.leftDrive.setPower(Power);
            robot.rightDrive.setPower(-Power);
            telemetry.addData("R Angle:", zAngle);
            telemetry.addData("P ", robot.leftDrive.getPower());
            telemetry.addData("Given Power ", Power);
            telemetry.update();
            zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            idle();
        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

    }
    public void SmartTurnLeft (double Angle, double Power){
        double zAngle;
        double targetAngle;
        zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        //Set target direction in range -180 - 180;
        targetAngle = (zAngle - Angle + 180);
        while (targetAngle > 360){ targetAngle = targetAngle - 360; }
        while (targetAngle < 0){ targetAngle = targetAngle + 360; }
        targetAngle = targetAngle - 180;

        while (AngularSeparation(zAngle, targetAngle)> 2.0){
            robot.leftDrive.setPower(-Power);
            robot.rightDrive.setPower(Power);
            telemetry.addData("L Angle:", zAngle);
            telemetry.addData("P ", robot.leftDrive.getPower());
            telemetry.addData("Given Power ", Power);
            telemetry.update();
            zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            idle();
        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

    }
}
