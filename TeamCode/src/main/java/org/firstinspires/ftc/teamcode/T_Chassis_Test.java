package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric D'Urso on 9/24/2017.
 */

public class T_Chassis_Test extends OpMode {
    private DcMotor A;
    private DcMotor B;
    private DcMotor X;
    private DcMotor Y;
    @Override
    public void init() {
        A = hardwareMap.dcMotor.get("A");
        B = hardwareMap.dcMotor.get("B");
        X = hardwareMap.dcMotor.get("X");
        Y = hardwareMap.dcMotor.get("Y");
    }

    @Override
    public void loop() {
        A.setPower(gamepad1.right_stick_y);
        B.setPower(gamepad1.right_stick_y);
        X.setPower(gamepad1.left_stick_y);
        Y.setPower(gamepad1.left_stick_y);
    }
}
