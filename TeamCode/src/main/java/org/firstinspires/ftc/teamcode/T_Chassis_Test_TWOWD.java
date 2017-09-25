package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric D'Urso on 9/24/2017.
 */
@TeleOp (name = "Trike Drive")
public class T_Chassis_Test_TWOWD extends OpMode {
    private DcMotor A;
    private DcMotor B;

    @Override
    public void init() {
        A = hardwareMap.dcMotor.get("A");
        B = hardwareMap.dcMotor.get("B");
    }

    @Override
    public void loop() {
        B.setPower(-gamepad1.right_stick_y);
        A.setPower(gamepad1.left_stick_y);
    }
}
