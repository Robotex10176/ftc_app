package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric D'Urso on 11/26/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "OLD_ROBOT_TELEOP", group = "OLE_ROBOT")
@Disabled
class TELEOP extends OpMode {
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
