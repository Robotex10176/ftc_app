package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static java.lang.Math.atan2;

/**
 * Created by Eric D'Urso on 12/7/2017.
 */
@Autonomous(name = "HAY UN CHICO")
@Disabled
public class T_Radar_Test extends LinearOpMode {
    A_Main main = new A_Main();
    @Override
    public void runOpMode() throws InterruptedException {
        main.init(hardwareMap,true);
        waitForStart();
        PlaceGlyph(true);
        sleep(1000000);
    }

    public void PlaceGlyph (boolean red){
        main.MMS.setPosition(1);
        double Direction = 0.01;
        double Ub = 0.80;
        double Lb = 0.20;
        double SetPos;
        double Pos;
        main.MoveSensor.setPosition(0.470 * (Ub + Lb));//0.5
        sleep(100);
        if (red) {
            while (main.GlyphSensor.red() < 10) {
                try {
                    Pos = main.MoveSensor.getPosition();
                    SetPos = Pos + Direction;
                    if (SetPos > Ub) {
                        Direction = -Direction;
                        main.DriveNoCorrection(0.5, 0.15);
                    } else if (SetPos < Lb) {
                        Direction = -Direction;
                        main.DriveNoCorrection(0.5, 0.15);
                    }
                    SetPos = Pos + Direction;
                    main.MoveSensor.setPosition(SetPos);
                    sleep(50);
                    idle();
                    telemetry.addData("Red Value = ", main.GlyphSensor.red());
                    telemetry.addData("Blue Value = ", main.GlyphSensor.blue());
                    telemetry.addData("Green Value = ", main.GlyphSensor.green());
                    telemetry.update();
                } catch (Exception e) {
                    telemetry.addLine("Problem in Glyph Sensor");
                    telemetry.update();
                }
            }
        }

        if (!red) {
            while (main.GlyphSensor.blue() < 10) {
                try {
                    Pos = main.MoveSensor.getPosition();
                    SetPos = Pos + Direction;
                    if (SetPos > Ub) {
                        Direction = -Direction;
                        main.DriveNoCorrection(0.5,  0.15);
                    } else if (SetPos < Lb) {
                        Direction = -Direction;
                        main.DriveNoCorrection(0.5, 0.15);
                    }
                    SetPos = Pos + Direction;
                    main.MoveSensor.setPosition(SetPos);
                    sleep(50);
                    idle();
                } catch (Exception e) {
                    telemetry.addLine("Problem in Glyph Sensor");
                    telemetry.update();
                }
            }
        }


        double CurrentPos = main.MoveSensor.getPosition();
        telemetry.addData("Current Position is ", CurrentPos);
        telemetry.update();
        main.MoveSensor.setPosition(1);
        main.MMS.setPosition(0.1);//retract arm and place
        double WheelsToEndOfArmDis = 4.60;//6.00-1.125;//10.0
        double AngularDisplacement = 0.5 - CurrentPos;
        double InchesAway;
        float TurnAmount;//Degrees
        if (AngularDisplacement == 0){
            //Do nothing, skip to drive forward.
        } else {
            InchesAway = (-6.666*AngularDisplacement);
            telemetry.addData("Inches Away", InchesAway);
            telemetry.update();
            TurnAmount = (float) Math.toDegrees(atan2(InchesAway, WheelsToEndOfArmDis));
            telemetry.addData("Turn Amount is ", TurnAmount);
            telemetry.update();
            //Used below to turn
            main.DriveNoCorrection(1.75,  0.2);/////////////////////////////////////////////////////////drive frwrd
            if (TurnAmount > 0.0){
                main.SmartTurnRight(  TurnAmount, 0.25);
            } else {
                TurnAmount = Math.abs(TurnAmount);
                main.SmartTurnLeft( TurnAmount, 0.25);//0.15 == old
            }
        }
        // drive forward, Open, reverse, and park in zone
        main.DriveNoCorrection(8.25,  0.25);
        main.OpenClaw();
        main.DriveNoCorrection(-3,  0.25);
    }
}
