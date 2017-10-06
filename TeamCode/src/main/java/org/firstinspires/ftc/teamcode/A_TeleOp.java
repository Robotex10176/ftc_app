package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Eric D'Urso on 10/5/2017.
 */
@TeleOp(name = "Main TeleOp")
public class A_TeleOp extends OpMode {
    H_RobotHardware robot = new H_RobotHardware();
    boolean A = true;
    @Override
    public void init() {
        robot.init(hardwareMap);

        float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        float DesiredAngle = zAngle;
    }
    @Override
    public void loop() {
        float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        double DesiredAngle = 0.0;
        if(gamepad1.right_stick_y>0.05){
            robot.leftDrive.setPower(gamepad1.right_stick_y);
            robot.rightDrive.setPower(gamepad1.right_stick_y);
            if (DesiredAngle > zAngle){
                robot.rightDrive.setPower(gamepad1.right_stick_y + 0.01);
            }
            if (DesiredAngle < zAngle){
                robot.leftDrive.setPower(gamepad1.right_stick_y + 0.01);
            }
        }
        if (gamepad1.right_stick_y < 0.05){
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);
        }
        if(gamepad1.dpad_up){
            robot.Lift.setPower(0.1);
        }
        if(gamepad1.dpad_down){
            robot.Lift.setPower(-0.1);
        }
        if (((!gamepad1.dpad_up) &&(!gamepad1.dpad_down)) ){
            robot.Lift.setPower(0);
        }
        if (gamepad1.left_bumper){
            CloseClaw();
        } else{
            OpenClaw();
        }
    }
    public void CloseClaw (){
        robot.RightClaw.setPosition(0.75);
        robot.LeftClaw.setPosition(0.50);
    }
    public void OpenClaw (){
        robot.RightClaw.setPosition(1);
        robot.LeftClaw.setPosition(0.25);
    }
    public void FlatClaw(){
        robot.RightClaw.setPosition(0.3);
        robot.LeftClaw.setPosition(0);
    }
}
