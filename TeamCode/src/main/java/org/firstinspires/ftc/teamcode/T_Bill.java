package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by daviado on 3/11/18.
 */

@Autonomous(name = "Bill_test")
public class Bill extends LinearOpMode {
    //Hardware Declaration
    public DcMotor gray;
    public void runOpMode() throws InterruptedException {
        //Hardware Initalization
        gray = hardwareMap.dcMotor.get("gray");
        waitForStart();
        //Hardware be like doo stuff
        //moov arm
        while (opModeIsActive){
            gray.setPower(1);
        }
    }
}
