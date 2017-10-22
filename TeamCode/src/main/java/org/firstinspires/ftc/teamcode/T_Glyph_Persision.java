package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Eric D'Urso on 10/15/2017.
 */
@Autonomous (name = "Glyph Test", group = "TEST")
public class T_Glyph_Persision extends LinearOpMode {
    Robot_Hardware_and_Methods robot = new Robot_Hardware_and_Methods();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, true);
        robot.MMS.setPosition(0.6);
        waitForStart();
        robot.MMS.setPosition(0);
        double Direction = 0.01;
        double Ub = 0.75;
        double Lb = 0.25;
        double SetPos;
        double Pos;
        while (robot.GlyphSensor.red()<10){
            telemetry.addData("Sensing:", robot.GlyphSensor.red());
            telemetry.update();
            Pos = robot.MoveSensor.getPosition();
            SetPos = Pos + Direction;
            if (SetPos > Ub){
                Direction = -Direction;
                robot.DriveNoCorrection(0.4, 0.1, 0.1);
            } else if (SetPos < Lb){
                Direction = -Direction;
                robot.DriveNoCorrection(0.4, 0.1, 0.1);
            }
            SetPos = Pos + Direction;
            robot.MoveSensor.setPosition(SetPos);
            telemetry.addData("Servo = ", robot.MoveSensor.getPosition());
            telemetry.addData("Red Value = ", robot.GlyphSensor.red());
            telemetry.addData("Blue Value = ", robot.GlyphSensor.blue());
            telemetry.addData("Green Value = ", robot.GlyphSensor.green());
            telemetry.update();
            sleep(50);//100 is standard
        }
        double CurrentPos = robot.MoveSensor.getPosition();
        double Displacement = CurrentPos - 0.5;
        telemetry.addData("Servo = ", robot.MoveSensor.getPosition());
        telemetry.addData("Red Value = ", robot.GlyphSensor.red());
        telemetry.addData("Blue Value = ", robot.GlyphSensor.blue());
        telemetry.addData("Green Value = ", robot.GlyphSensor.green());
        telemetry.update();
        sleep(1000000000);

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
