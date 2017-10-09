package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric D'Urso on 9/24/2017.
 * NOTE THAT WE ARE RED
 */
@Autonomous (name = "JEWL TEST")
public class T_PARTS_Jewl_Test extends LinearOpMode {
    private DcMotor arm;
    //private Servo moveFlick;
    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        arm = hardwareMap.dcMotor.get("Arm");
        waitForStart();

    }
}
