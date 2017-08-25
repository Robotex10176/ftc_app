package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric D'Urso on 8/25/2017.
 */
@Autonomous (name = "cycling motors")
public class one_motor_turns extends LinearOpMode {
    private DcMotor NorthMotor;
    private DcMotor SouthMotor;
    private DcMotor EastMotor;
    private DcMotor WestMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        NorthMotor = hardwareMap.dcMotor.get("NorthDrive");
        SouthMotor = hardwareMap.dcMotor.get("SouthDrive");
        EastMotor = hardwareMap.dcMotor.get("EastDrive");
        WestMotor = hardwareMap.dcMotor.get("WestDrive");
        waitForStart();
        while (opModeIsActive()){
            telemetry.addLine("N");
            NorthMotor.setPower(0.5);
            sleep(5000);
            NorthMotor.setPower(0);
            telemetry.addLine("S");
            SouthMotor.setPower(0.5);
            sleep(5000);
            SouthMotor.setPower(0);
            telemetry.addLine("E");
            EastMotor.setPower(0.5);
            sleep(5000);
            EastMotor.setPower(0);
            telemetry.addLine("W");
            WestMotor.setPower(0.5);
            sleep(5000);
            WestMotor.setPower(0);
            idle();
        }
    }
}
