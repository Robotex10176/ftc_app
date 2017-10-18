package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric D'Urso on 9/25/2017.
 */
@TeleOp(name = "Glyph Arm Test Teleop")
@Disabled
public class T_PART_Glyph_Arm_Test extends LinearOpMode {
    private DcMotor Lift;
    private Servo RightClaw;
    private Servo LeftClaw;
    private boolean Close = false;
    @Override
    public void runOpMode() throws InterruptedException {
        Lift = hardwareMap.dcMotor.get("Lift");
        RightClaw = hardwareMap.servo.get("RightClaw");
        LeftClaw = hardwareMap.servo.get("LeftClaw");
        FlatClaw();
        sleep(1000);
        waitForStart();
        while (opModeIsActive()){
            if (gamepad1.dpad_up){
                Lift.setPower(0.5);
            }
            if (gamepad1.dpad_down){
                Lift.setPower(-0.5);
            }
            if (gamepad1.left_bumper){
                Close = true;
            }
            if (Close){
                CloseClaw ();
            }
            if (!gamepad1.left_bumper){
                Close = false;
            }
            if (!Close){
                OpenClaw();
            }
        }
    }
    public void CloseClaw (){
        RightClaw.setPosition(0.75);
        LeftClaw.setPosition(0.50);
    }
    public void OpenClaw (){
        RightClaw.setPosition(1);
        LeftClaw.setPosition(0.25);
    }
    public void FlatClaw(){
        RightClaw.setPosition(0.3);
        LeftClaw.setPosition(0);
    }
}
