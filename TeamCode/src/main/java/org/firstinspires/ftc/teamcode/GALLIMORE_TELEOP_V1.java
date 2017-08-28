package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric D'Urso on 8/13/2017.
 */
@TeleOp (name = "GALLIMORE_TELEOP", group = "GALLIMORE")
public class GALLIMORE_TELEOP_V1 extends OpMode {
    private DcMotor EpWn;
    private DcMotor WpEn;
    private DcMotor SpNn;
    private DcMotor NpSn;

    @Override
    public void init() {
        EpWn = hardwareMap.dcMotor.get("EpWn");
        WpEn = hardwareMap.dcMotor.get("WpEn");
        SpNn = hardwareMap.dcMotor.get("SpNn");
        NpSn = hardwareMap.dcMotor.get("NpSn");

    }

    @Override
    public void loop() {

        EpWn.setPower(gamepad1.right_stick_y);
        WpEn.setPower(-gamepad1.right_stick_y);
        SpNn.setPower(gamepad1.left_stick_y);
        NpSn.setPower(-gamepad1.left_stick_y);

    }
}
