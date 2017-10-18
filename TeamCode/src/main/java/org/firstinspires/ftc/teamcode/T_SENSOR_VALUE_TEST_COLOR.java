package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Eric D'Urso on 8/15/2017.
 * Eric Says Hi
 */
@TeleOp (name = "COLOR_SENSOR_VALUE_TEST", group = "SENSOR_VALUE_TESTS")
@Disabled
public class T_SENSOR_VALUE_TEST_COLOR extends OpMode {
    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;

    @Override
    public void init() {
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
    }

    @Override
    public void loop() {

        ColorSensor.enableLed(true);
        telemetry.addData("red", ColorSensor.red());
        telemetry.addData("green", ColorSensor.green());
        telemetry.addData("blue", ColorSensor.blue());
        telemetry.addData("rgb", ColorSensor.argb());//hue?
        telemetry.addData("ALPHA?", ColorSensor.alpha());//alpha = brightness


    }
}
