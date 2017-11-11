package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Intended for use of Troop 1537, Plymouth MI
 * This is basically the answer key.
 */
@Autonomous (name = "BSA DEMO SMALL BOT")
public class BoyScoutMeritBadge_OurExample extends LinearOpMode{
    BSA_Methods robot = new BSA_Methods();
    @Override
    public void runOpMode() throws InterruptedException {
        String Color = "UNKNOWN";
        robot.init(hardwareMap);
        waitForStart();
        robot.leftDrive.setPower(0.009);
        robot.rightDrive.setPower(0.009);
        sleep(3000);
        robot.TurnRight(90, 0.05);
        while(robot.ColorSensor.blue()>10  ||
                robot.ColorSensor.green()>10  ||
                robot.ColorSensor.red()>10){//number between 0 and 255
            robot.rightDrive.setPower(0.009);
            robot.leftDrive.setPower(0.009);
        }
        if ((robot.ColorSensor.blue()>(robot.ColorSensor.red())) &&
                (robot.ColorSensor.blue()>(robot.ColorSensor.green())) ){
            Color = "BLUE";
        } else  if ((robot.ColorSensor.red()>(robot.ColorSensor.blue())) &&
                (robot.ColorSensor.red()>(robot.ColorSensor.green())) ){
            Color = "RED";
        } else  if ((robot.ColorSensor.green()>(robot.ColorSensor.red())) &&
                (robot.ColorSensor.green()>(robot.ColorSensor.blue())) ){
            Color = "BLUE";
        } else {
            Color = "ERROR_UNCAUGHT_EXCEPTION";
        }
        telemetry.addData("The Color I See Is ", Color);telemetry.update();
    }
}
