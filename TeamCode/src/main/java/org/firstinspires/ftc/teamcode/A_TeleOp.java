package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Eric D'Urso on 10/5/2017.
 */
@TeleOp(name = "TeleOp")
public class A_TeleOp extends OpMode {
    A_MAIN main = new A_MAIN();
    @Override
    public void init() {
        main.init(hardwareMap, false);
    }
    @Override
    public void loop() {
        //if ((gamepad1.left_trigger < 0.05) || (gamepad1.right_trigger < 0.05)) {
            main.rightDrive.setPower(main.scaleController(-gamepad1.right_stick_y));
            main.leftDrive.setPower(main.scaleController(-gamepad1.left_stick_y));
        //}
        //if ((gamepad1.left_trigger > 0.05) || (gamepad1.right_trigger > 0.05)) {
          //  main.rightDrive.setPower(main.scaleController((gamepad1.right_trigger) / 2));
            //main.leftDrive.setPower(main.scaleController((gamepad1.left_trigger) / 2));
        //}
        if (gamepad1.dpad_up) {
            main.Lift.setPower(0.3);
        }
        if (gamepad1.dpad_down) {
            main.Lift.setPower(-0.3);
        }
        if (((!gamepad1.dpad_up) && (!gamepad1.dpad_down))) {
            main.Lift.setPower(0);
        }
        if (gamepad1.left_bumper) {
            main.CloseClaw();
        } else {
            main.OpenClaw();
        }
        if (gamepad2.left_bumper){
            main.CloseRelic();
        } else {
            main.OpenRelic();
        }
        if (gamepad2.dpad_left){
            main.ExtendRelic.setPower(0.3);
        } else if (gamepad2.dpad_right){
            main.ExtendRelic.setPower(-0.3);
        } else {
            main.ExtendRelic.setPower(0.0);
        }
        if (gamepad2.dpad_up){
            main.RotateRelic.setPower(0.3);
        } else if (gamepad2.dpad_down){
            main.RotateRelic.setPower(-0.3);
        } else {
            main.RotateRelic.setPower(0.0);
        }
        if (!gamepad2.start){
            main.MMS.setPosition(0.3);//up
        }
    }
}