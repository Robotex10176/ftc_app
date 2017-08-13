package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric D'Urso on 8/12/2017.
 */
@Autonomous (name = "Gallimore Autonomous", group = "Gallimore")
public class AutoDemoGallimore extends LinearOpMode {
    //Identify Variables if any/needed
    boolean code_running;
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
        code_running = false;
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


        waitForStart ();              //start moving
        code_running = true;
       iSeeWhite.setPosition(0.001);
        sleep(3000);
// This would make it stop at a white line i Think
       while (code_running = true) {
           NSUP();
           STOP_MOTORS();
           RAISE_SIGN();
           EWDOWN();
           STOP_MOTORS();
           RAISE_SIGN();
           NSDOWN();
           STOP_MOTORS();
           RAISE_SIGN();
           EWUP();
           STOP_MOTORS();
           RAISE_SIGN();
       }
        STOP_MOTORS();
        iSeeWhite.setPosition(0.001);
        System.out.println("Code Has Stopped");


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

    public void NSUP (){
        while (ColorSensor.alpha() < 20){     // This would make it stop at a white line i think
            NorthMotor.setPower(.5);
            SouthMotor.setPower(-.5);
        }
    }

    public void NSDOWN (){
        while (ColorSensor.alpha() < 20){     // This would make it stop at a white line i think
            NorthMotor.setPower(-.5);
            SouthMotor.setPower(.5);
        }
    }

    public void EWUP (){
        while (ColorSensor.alpha() < 20){     // This would make it stop at a white line i think
            EastMotor.setPower(.5);
            WestMotor.setPower(-.5);
        }
    }

    public void EWDOWN (){
        while (ColorSensor.alpha() < 20){     // This would make it stop at a white line i think
            EastMotor.setPower(-.5);
            WestMotor.setPower(.5);
        }
    }

}

