package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
@Autonomous(name = "DRIVE NO GYRO")
public class T_driveNoGyro extends LinearOpMode {
    A_Main robot = new A_Main();
    @Override
    robot.init(hardwareMap, true);
    waitForStart();
        //public void DriveNoCorrection(double DesiredDistance, double RightPower, double LeftPower) {
        DriveNoCorrection(1, 10, 10);
    }
