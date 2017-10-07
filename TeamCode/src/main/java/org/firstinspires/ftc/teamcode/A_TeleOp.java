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
    @Override
    public void init() {
        robot.init(hardwareMap);
    }
    @Override
    public void loop() {
        robot.rightDrive.setPower(scaleController(gamepad1.right_stick_y));
        robot.leftDrive.setPower(scaleController(gamepad1.left_stick_y));
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
        if (gamepad1.right_bumper){
            Sensing();
        } else{
            Rest();
        }
        if (gamepad1.a){
            SeeOurColor();
        } else if (gamepad1.b){
            DontSeeOurColor();
        }else{
            decide();
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
    public void Rest (){
        robot.moveFlick.setPosition(0.5);
        robot.flick.setPosition(0);
    }
    public void Sensing () {
        robot.flick.setPosition(1);
        robot.moveFlick.setPosition(0.5);
    }
    public void DontSeeOurColor (){
        robot.moveFlick.setPosition(0.7);
    }
    public void SeeOurColor (){
        robot.moveFlick.setPosition(0);
    }
    public void decide (){
        robot.moveFlick.setPosition(0.5);
    }
}
