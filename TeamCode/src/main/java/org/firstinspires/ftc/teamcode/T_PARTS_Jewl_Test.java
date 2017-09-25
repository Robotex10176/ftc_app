package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric D'Urso on 9/24/2017.
 * NOTE THAT WE ARE RED
 */
@Autonomous (name = "JEWL TEST")
public class T_PARTS_Jewl_Test extends LinearOpMode {
    private Servo flick;
    private Servo moveFlick;
    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;
    @Override
    public void runOpMode() throws InterruptedException {
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        flick = hardwareMap.servo.get("S2");
        moveFlick = hardwareMap.servo.get("S1");
        flick.setPosition(0.5);
        sleep(1000);
        moveFlick.setPosition(0);
        sleep(1000);
        waitForStart();
        moveFlick.setPosition(1);
        sleep(1000);
        if (ColorSensor.red()>ColorSensor.blue()){
            flick.setPosition(1);
            sleep(1000);
        }
        if (ColorSensor.red()<ColorSensor.blue()){
            flick.setPosition(0);
            sleep(1000);
        }
        else{
            flick.setPosition(0.5);
            sleep(1000);
        }
        flick.setPosition(0.5);
        sleep(1000);
        moveFlick.setPosition(0);
        sleep(1000);

    }
}
