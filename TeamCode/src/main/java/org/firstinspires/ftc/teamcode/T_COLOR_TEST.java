package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Eric D'Urso on 10/19/2017.
 */
@Autonomous (name = "GLYPH COLOR TEST")
@Disabled
public class T_COLOR_TEST extends OpMode {
    Robot_Hardware_and_Methods robot = new Robot_Hardware_and_Methods();
    @Override
    public void init() {
        robot.init(hardwareMap, true);
    }

    @Override
    public void loop() {
        robot.MMS.setPosition(0);
        telemetry.addData("Servo = ", robot.MoveSensor.getPosition());
        telemetry.addData("Red Value = ", robot.GlyphSensor.red());
        telemetry.addData("Blue Value = ", robot.GlyphSensor.blue());
        telemetry.addData("Green Value = ", robot.GlyphSensor.green());
        telemetry.update();
    }
}
