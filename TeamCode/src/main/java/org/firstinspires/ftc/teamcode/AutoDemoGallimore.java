package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric D'Urso on 8/12/2017.
 */

public class AutoDemoGallimore extends LinearOpMode {
    //Identify Variables if any/needed
    //Identify Motors
    private DcMotor NorthMotor;
    private DcMotor SouthMotor;
    private DcMotor EastMotor;
    private DcMotor WestMotor;
    //private Servo RedArm;
    //private Servo BlueArm;
    private Servo iSeeWhite;
    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;

    public AutoDemoGallimore (){

    }

    @Override
    public void runOpMode()throws InterruptedException{
        //Set Up Drive Track
        NorthMotor = hardwareMap.dcMotor.get("NorthDrive");
        SouthMotor = hardwareMap.dcMotor.get("SouthDrive");
        EastMotor = hardwareMap.dcMotor.get("EastDrive");
        WestMotor = hardwareMap.dcMotor.get("WestDrive");
        //Set Up Servo arms                    !!!WE WOULD USE THESE ONCE WE LEARN MORE ABOUT THE COLOR SENSOR
        // RedArm = hardwareMap.servo.get("Red Arm");
        //BlueArm = hardwareMap.servo.get("Blue Arm");
        iSeeWhite = hardwareMap.servo.get("I See White Sign");
        //Set Up Color Sensor
        ColorSensor = hardwareMap.colorSensor.get("Color Sensor");

       iSeeWhite.setPosition(0.001);

        //Start moving

        while (ColorSensor.alpha() < 20){     // This would make it stop at a white line i think
            NorthMotor.setPower(.5);
            SouthMotor.setPower(-.5);
        }

        STOP_MOTORS();
        RAISE_SIGN();



    }
    // Create Methods Below
    public void STOP_MOTORS (){
        NorthMotor.setPower(0);
        SouthMotor.setPower(0);
        EastMotor.setPower(0);
        WestMotor.setPower(0);
    }

    public void RAISE_SIGN (){
        iSeeWhite.setPosition(0.65);
        sleep(6000);
        iSeeWhite.setPosition(0.001);
    }

}

