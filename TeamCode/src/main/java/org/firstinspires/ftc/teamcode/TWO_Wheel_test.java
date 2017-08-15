package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric D'Urso on 8/15/2017.
 */
@TeleOp (name = "Test", group = "TEST")
public class TWO_Wheel_test extends OpMode {
    private DcMotor RightBack;
    private DcMotor LeftBack;
    @Override
    public void init() {
        RightBack= hardwareMap.dcMotor.get("RIGHT");
        LeftBack = hardwareMap.dcMotor.get("LEFT");
    }

    @Override
    public void loop() {
        RightBack.setPower(gamepad1.right_stick_y);
        LeftBack.setPower(gamepad1.left_stick_y);
    }
}
