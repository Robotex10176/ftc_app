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
        Rest();
        waitForStart();
        sleep(1000);
        Sensing();
        if (ColorSensor.red()>ColorSensor.blue()){
            DontSeeOurColor();
        }
        if (ColorSensor.red()<ColorSensor.blue()){
            SeeOurColor();
        }
        else{
            Sensing();
            Rest();
        }
        Rest();

    }
    public void Rest (){
        moveFlick.setPosition(0.5);
        sleep(1000);
        flick.setPosition(0);
        sleep(1000);
    }
    public void Sensing () {
        flick.setPosition(1);
        sleep(1000);
        moveFlick.setPosition(0.5);
        sleep(1000);
    }
    public void DontSeeOurColor (){
        moveFlick.setPosition(0.7);
        sleep(1000);
        moveFlick.setPosition(0.5);
        sleep(1000);
    }
    public void SeeOurColor (){
        moveFlick.setPosition(0);
        sleep(1000);
        moveFlick.setPosition(0.5);
        sleep(1000);
    }
}
