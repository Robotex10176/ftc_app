package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric D'Urso on 8/25/2017.
 */
@Autonomous (name = "cycling motors")
@Disabled
public class one_motor_turns extends LinearOpMode {
    private DcMotor EpWn;
    private DcMotor WpEn;
    private DcMotor SpNn;
    private DcMotor NpSn;
    @Override
    public void runOpMode() throws InterruptedException {
        EpWn = hardwareMap.dcMotor.get("NorthDrive");
        WpEn = hardwareMap.dcMotor.get("SouthDrive");
        SpNn = hardwareMap.dcMotor.get("EastDrive");
        NpSn = hardwareMap.dcMotor.get("WestDrive");
        waitForStart();
        while (opModeIsActive()){
            telemetry.addLine("N");
            EpWn.setPower(0.5);
            sleep(5000);
            EpWn.setPower(0);
            telemetry.addLine("S");
            WpEn.setPower(0.5);
            sleep(5000);
            WpEn.setPower(0);
            telemetry.addLine("E");
            SpNn.setPower(0.5);
            sleep(5000);
            SpNn.setPower(0);
            telemetry.addLine("W");
            NpSn.setPower(0.5);
            sleep(5000);
            NpSn.setPower(0);
            idle();
        }
    }
}
