package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Eric D'Urso on 11/7/2017.
 */
@TeleOp (name = "BSA TeleOp")
public class BSA_TeleOP_Demo extends OpMode {
    BSA_Methods robot = new BSA_Methods();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.leftDrive.setPower(gamepad1.left_stick_y);
        robot.rightDrive.setPower(gamepad1.right_stick_y);
    }
}
