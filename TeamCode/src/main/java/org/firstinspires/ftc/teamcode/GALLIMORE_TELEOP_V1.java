package org.firstinspires.ftc.teamcode ;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Math;

/**
 * Created by Eric D'Urso on 8/13/2017.
 */
@TeleOp (name = "GALLIMORE_TELEOP", group = "GALLIMORE")
public class GALLIMORE_TELEOP_V1 extends OpMode {
    private DcMotor EpWn;
    private DcMotor WpEn;
    private DcMotor SpNn;
    private DcMotor NpSn;
    private float scaleController(float in){
        //return ( java.lang.Math.signum(in)*in*in);
        return (in*in*in);
    }

    @Override
    public void init() {
        EpWn = hardwareMap.dcMotor.get("EpWn");
        WpEn = hardwareMap.dcMotor.get("WpEn");
        SpNn = hardwareMap.dcMotor.get("SpNn");
        NpSn = hardwareMap.dcMotor.get("NpSn");

    }

    @Override
    public void loop() {
        //primary driving block
        if ((gamepad1.right_trigger < 0.05 )&&(gamepad1.left_trigger < 0.05 )) {
            EpWn.setPower(scaleController(-gamepad1.left_stick_x));
            WpEn.setPower(scaleController(gamepad1.left_stick_x));
            SpNn.setPower(scaleController(gamepad1.left_stick_y));
            NpSn.setPower(scaleController(-gamepad1.left_stick_y));
        }
        //done add conditional below
        if ((java.lang.Math.abs(gamepad1.left_stick_x) < 0.05)&&(java.lang.Math.abs(gamepad1.left_stick_x) < 0.05)) {
            //this block rotates the robot right
            EpWn.setPower(scaleController(gamepad1.right_trigger));
            WpEn.setPower(scaleController(gamepad1.right_trigger));
            SpNn.setPower(scaleController(gamepad1.right_trigger));
            NpSn.setPower(scaleController(gamepad1.right_trigger));
        }
        if ((java.lang.Math.abs(gamepad1.left_stick_x) < 0.05)&&(java.lang.Math.abs(gamepad1.left_stick_x) < 0.05)) {
            //this block rotates the robot right
            EpWn.setPower(scaleController(-gamepad1.left_trigger));
            WpEn.setPower(scaleController(-gamepad1.left_trigger));
            SpNn.setPower(scaleController(-gamepad1.left_trigger));
            NpSn.setPower(scaleController(-gamepad1.left_trigger));
        }


    }
}
