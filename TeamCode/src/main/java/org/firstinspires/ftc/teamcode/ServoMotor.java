package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by fritz on 8/1/17.
 */

public class ServoMotor extends OpMode {
    DcMotor Motor1; //left stick
    DcMotor Motor2; //right stick

    Servo Servo1;

    @Override
    public void loop() {
        Motor1.setPower(gamepad1.left_stick_y);
        Motor2.setPower(-gamepad1.right_stick_y);

        if (gamepad1.a) {
            Servo1.setPosition(0.65); //servo 50 ticks
        } else {
            Servo1.setPosition(0.002); //reset servo(0 ticks)
        }
    }

    @Override
    public void init() {
        Motor1 = hardwareMap.dcMotor.get("m1");
        Motor2 = hardwareMap.dcMotor.get("m2");
        Servo1 = hardwareMap.servo.get("s1")

    }
}
