package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by fritz on 8/1/17.
 */
//Commented out motors to get servo to work as we do not have another cable
@TeleOp(name="ServoMotor", group="servo")
public class ServoMotor extends OpMode {
   // DcMotor Motor1; //left stick
    //DcMotor Motor2; //right stick

    Servo Servo1; // "A" Button

    @Override
    public void loop() {
       // Motor1.setPower(gamepad1.left_stick_y);
       // Motor2.setPower(-gamepad1.right_stick_y);

        if (gamepad1.a) {
            Servo1.setPosition(0.65); //servo 50 ticks
        } else {
            Servo1.setPosition(0.002); //reset servo(0 ticks)
        }
    }

    @Override
    public void init() {
        //Motor1 = hardwareMap.dcMotor.get("Left Motor");
        //Motor2 = hardwareMap.dcMotor.get("Right Motor");
        Servo1 = hardwareMap.servo.get("s1");

    }
    //This is all the code. The Video just goes over other ways to control the motors.
    
}
