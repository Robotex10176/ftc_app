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
    //Identify Variables
    private boolean Line;
    //Identify Motors
    private DcMotor NorthMotor;
    private DcMotor SouthMotor;
    private DcMotor EastMotor;
    private DcMotor WestMotor;
    private Servo RedArm;
    private Servo BlueArm;
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
        //Set Up Servo arms
        RedArm = hardwareMap.servo.get("Red Arm");
        BlueArm = hardwareMap.servo.get("Blue Arm");
        //Set Up Color Sensor
        ColorSensor = hardwareMap.colorSensor.get("Color Sensor");

        Line = false;

        //Start moving
        NorthMotor.setPower(.5);
        SouthMotor.setPower(-.5);

        while (){

        }

    }

}

