package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp (name = "Robot Sensor Value Tests")
//@Disabled
public class A_Robot_Sensor_Test extends OpMode {
    A_Main robot = new A_Main();
    @Override
    public void init() {
        robot.init(hardwareMap, true);
    }

    @Override
    public void loop() {

        robot.ColorSensor.enableLed(true);
        telemetry.addData("red", robot.ColorSensor.red());
        telemetry.addData("green", robot.ColorSensor.green());
        telemetry.addData("blue", robot.ColorSensor.blue());
        telemetry.addData("rgb", robot.ColorSensor.argb());//hue?
        telemetry.addData("ALPHA?", robot.ColorSensor.alpha());//alpha = brightness
        robot.GlyphSensor.enableLed(true);
        telemetry.addData("red", robot.GlyphSensor.red());
        telemetry.addData("green", robot.GlyphSensor.green());
        telemetry.addData("blue", robot.GlyphSensor.blue());
        telemetry.addData("rgb", robot.GlyphSensor.argb());//hue?
        telemetry.addData("ALPHA?", robot.GlyphSensor.alpha());//alpha = brightness
        telemetry.addData("Gyro Heading", robot.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);


    }
}
