package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric D'Urso on 9/24/2017.
 * NOTE THAT WE ARE RED
 */
@Autonomous (name = "JEWL TEST")
public class T_PARTS_Jewl_Test extends LinearOpMode {
    private DcMotor arm;
    private Servo moveFlick;
    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        arm = hardwareMap.dcMotor.get("Arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setPower(0);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        moveFlick = hardwareMap.servo.get("Jewel");
        moveFlick.setPosition(0.5);
        waitForStart();
        moveArm(95, 0.1);
        sleep(1000);
        if ((ColorSensor.red()>ColorSensor.blue())){
            SeeOurColor();
        } else if ((ColorSensor.red()<ColorSensor.blue())){
            DontSeeOurColor();
        } else if ((ColorSensor.red()>ColorSensor.blue())){
            DontSeeOurColor();
        } else if ((ColorSensor.red()<ColorSensor.blue())){
            SeeOurColor();
        } else {

        }
        moveArm(-120, -0.1);
    }
    public void moveArm (double Degrees, double Power){
        int newLeftTarget;
        final double COUNTS_PER_MOTOR_REV = 1120;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DEGREES = (Degrees*(COUNTS_PER_MOTOR_REV/360));//3.111111111111111111111111111 ticks per degree
        if (opModeIsActive()) {
            newLeftTarget = arm.getCurrentPosition() + (int) (DEGREES);
            arm.setTargetPosition(newLeftTarget);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(Math.abs(Power));
            while (opModeIsActive() && (arm.isBusy())) {
// DO NOTHING BECUZ THIS IS DUMB

            }
        }
    }
    public void SeeOurColor (){
        moveFlick.setPosition(0);
        sleep(1000);
        moveFlick.setPosition(0.5);
        sleep(1000);
    }
    public void DontSeeOurColor (){
        moveFlick.setPosition(1);
        sleep(1000);
        moveFlick.setPosition(0.5);
        sleep(1000);
    }
}
