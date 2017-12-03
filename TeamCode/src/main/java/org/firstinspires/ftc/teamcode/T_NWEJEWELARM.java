package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric D'Urso on 12/1/2017.
 */
@TeleOp(name = "jewel")
@Disabled
public class T_NWEJEWELARM extends OpMode {
    A_Main robot = new A_Main();
    boolean A = true;
    @Override
    public void init() {
        robot.init(hardwareMap, false);
    }
    @Override
    public void loop() {
        while(gamepad1.left_bumper){
            robot.arm.setPower(-0.1);
        }
        if(gamepad1.right_bumper){
            robot.arm.setPower(0.1);

        }
    }
    public void moveArm (double Degrees, double Power){
        int newLeftTarget;
        final double COUNTS_PER_MOTOR_REV = 1120;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DEGREES = (Degrees*(COUNTS_PER_MOTOR_REV/360));//3.111111111111111111111111111 ticks per degree
        if (A) {
            newLeftTarget = robot.arm.getCurrentPosition() + (int) (DEGREES);
            robot.arm.setTargetPosition(newLeftTarget);
            robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.arm.setPower(Math.abs(Power));
            while (A && (robot.arm.isBusy())) {

            }
        }
    }
}
