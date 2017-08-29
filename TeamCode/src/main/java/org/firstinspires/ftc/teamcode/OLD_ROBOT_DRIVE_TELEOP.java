package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric D'Urso on 8/29/2017.
 */
@TeleOp(name = "OLD_ROBOT_TELEOP", group = "OLE_ROBOT")
public class OLD_ROBOT_DRIVE_TELEOP extends OpMode {
    private DcMotor LeftMotor;
    private DcMotor RightMotor;
   // private float scaleController(float in){
        //return ( java.lang.Math.signum(in)*in*in);
        //return (in*in*in);
    //}
    @Override
    public void init() {
        LeftMotor = hardwareMap.dcMotor.get("leftmotor");
        RightMotor = hardwareMap.dcMotor.get("rightmotor");
    }

    @Override
    public void loop() {
        LeftMotor.setPower(-gamepad1.right_stick_y);
        RightMotor.setPower(gamepad1.left_stick_y);

    }
}
