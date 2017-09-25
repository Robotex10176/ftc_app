package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric D'Urso on 9/24/2017.
 */
@Autonomous (name = "JEWL TEST")
public class T_Jewl_Test extends LinearOpMode {
    private Servo flick;
    private Servo moveFlick;
    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;
    @Override
    public void runOpMode() throws InterruptedException {
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        flick = hardwareMap.servo.get("S2");
        moveFlick = hardwareMap.servo.get("S1");
        flick.setPosition(0.5);
        moveFlick.setPosition(1);
        waitForStart();

    }
}
