package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric D'Urso on 8/13/2017.
 */
@TeleOp (name = "Gallimore TeleOp", group = "Gallimore")
public class TeleOpTest_FourOmniWheelDrive extends OpMode {
    private DcMotor NorthMotor;
    private DcMotor SouthMotor;
    private DcMotor EastMotor;
    private DcMotor WestMotor;

    @Override
    public void init() {
        NorthMotor = hardwareMap.dcMotor.get("NorthDrive");
        SouthMotor = hardwareMap.dcMotor.get("SouthDrive");
        EastMotor = hardwareMap.dcMotor.get("EastDrive");
        WestMotor = hardwareMap.dcMotor.get("WestDrive");

    }

    @Override
    public void loop() {

        NorthMotor.setPower(gamepad1.right_stick_y);
        SouthMotor.setPower(-gamepad1.right_stick_y);
        EastMotor.setPower(gamepad1.left_stick_y);
        WestMotor.setPower(-gamepad1.left_stick_y);

    }
}
