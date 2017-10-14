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
    private float scaleController(float in){
        //return ( java.lang.Math.signum(in)*in*in);
        return (in*in*in);
    }
    boolean A = true;
    @Override
    public void init() {
        robot.init(hardwareMap);
    }
    @Override
    public void loop() {
        //robot.rightDrive.setPower(scaleController(gamepad1.right_stick_y));
        //robot.leftDrive.setPower(scaleController(gamepad1.left_stick_y));
        if ((gamepad1.left_trigger < 0.05) || (gamepad1.right_trigger < 0.05)){
            robot.rightDrive.setPower(scaleController(-gamepad1.right_stick_y));
            robot.leftDrive.setPower(scaleController(-gamepad1.left_stick_y));
        }
        if ((gamepad1.left_trigger > 0.05) || (gamepad1.right_trigger > 0.05)){
            robot.rightDrive.setPower(scaleController((gamepad1.right_trigger)/2));
            robot.leftDrive.setPower(scaleController((gamepad1.left_trigger)/2));
        }
        if(gamepad1.dpad_up){
            robot.Lift.setPower(0.3);
        }
        if(gamepad1.dpad_down){
            robot.Lift.setPower(-0.3);
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



























































    //oops



    public void Turn(double Angle, double Power){
        //code to turn untill an angle ex 0, 90, -90
        float zAngle;
        zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        if (Angle < 0){
            while (zAngle != Angle){
                robot.leftDrive.setPower(Power);
                robot.rightDrive.setPower(-Power);
                telemetry.addData("Angle:", zAngle);
                telemetry.update();
                zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

            }
        } else if (Angle > 0){
            while (zAngle != Angle){
                robot.leftDrive.setPower(-Power);
                robot.rightDrive.setPower(Power);
                telemetry.addData("Angle:", zAngle);
                telemetry.update();
                zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

            }
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
