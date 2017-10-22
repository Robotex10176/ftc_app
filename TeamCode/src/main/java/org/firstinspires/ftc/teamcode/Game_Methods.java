package org.firstinspires.ftc.teamcode;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eric D'Urso on 10/16/2017.
 */

public class Game_Methods {
    //Robot_Hardware_and_Methods robot = new Robot_Hardware_and_Methods();
    //Constructor
    public Game_Methods (){}

    public String KnockOffJewl(boolean red, Robot_Hardware_and_Methods robot) {
        String color;
        robot.moveArm(120, 0.1);
        //sleep(1000);
        if ((robot.ColorSensor.red() > robot.ColorSensor.blue())){
            color = "RED";
        } else if ((robot.ColorSensor.red() < robot.ColorSensor.blue())){
            color = "BLUE";
        } else {
            color = "UNKNOWN";
        }
        //sleep(2000);
        if (red) {
            if (color.compareTo("RED") == 0){
                robot.SeeOurColor();
            } else if (color.compareTo("BLUE") == 0){
                robot.DontSeeOurColor();
            } else {
                //UNKNOWN
            }
        } else {//means its blue
            if (color.compareTo("RED") == 0){
                robot.DontSeeOurColor();
            } else if (color.compareTo("BLUE") == 0){
                robot.SeeOurColor();
            } else {
                //UNKNOWN
            }
        }
        robot.moveArm(-120, -0.1);
        //sleep(1000);
        return color;
    }

    public void PlaceGlyphB (Robot_Hardware_and_Methods robot){
        //drive and scan
        robot.MMS.setPosition(1);
        double Direction = 0.01;
        double Ub = 0.80;
        double Lb = 0.20;
        double SetPos;
        double Pos;
        while (robot.GlyphSensor.red()<10){
            Pos = robot.MoveSensor.getPosition();
            SetPos = Pos + Direction;
            if (SetPos > Ub){
                Direction = -Direction;
                robot.DriveNoCorrection(0.4, 0.1, 0.1);
            } else if (SetPos < Lb){
                Direction = -Direction;
                robot.DriveNoCorrection(0.4, 0.1, 0.1);
            }
            SetPos = Pos + Direction;
            robot.MoveSensor.setPosition(SetPos);
        }
        robot.MMS.setPosition(0.6);
        double CurrentPos = robot.MoveSensor.getPosition();
        double Displacement = CurrentPos - 0.5;
        //Open, reverse, and park in zone
        robot.OpenClaw();
        robot.DriveNoCorrection(-5, 0.1, 0.1);
        robot.FlatClaw();
        robot.DriveNoCorrection(2, 0.1, 0.1);
    }
    public void RightSeen(boolean Red, boolean Bottom, Robot_Hardware_and_Methods robot){
        if (Red){
            if (Bottom){
                robot.DriveNoCorrection ((28.25 + 9), 0.15, 0.15);//Drive Forward 28.25 in
                robot.SmartTurnRightON(90, 0.1);
                PlaceGlyphB(robot);
            } else {
                robot.DriveNoCorrection( 5 , 0.1, 0.1);
                robot.SmartTurnLeftON(90, 0.1);
                robot.DriveNoCorrection( 5  , 0.1, 0.1);
                robot.SmartTurnRight(0, 0.1);
                PlaceGlyphB(robot);
            }
        } else {
            if (Bottom){
                robot.DriveNoCorrection ((28.25 + 9), 0.15, 0.15);//Drive Forward 28.25 in
                robot.SmartTurnLeftON(90, 0.1);
                PlaceGlyphB(robot);
            } else {
                robot.DriveNoCorrection( 5 , 0.1, 0.1);
                robot.SmartTurnRight(0, 0.1);
                robot.DriveNoCorrection( 5  , 0.1, 0.1);
                robot.SmartTurnLeftON(90, 0.1);
                PlaceGlyphB(robot);
            }
        }
    }
    public void CenterSeen(boolean Red, boolean Bottom, Robot_Hardware_and_Methods robot){
        if (Red){
            if (Bottom){
                robot.DriveNoCorrection ((35.75 + 9), 0.15, 0.15);//Drive Forward 39.5 in
                robot.SmartTurnRight(90, 0.1);
                PlaceGlyphB(robot);
            } else {
                robot.DriveNoCorrection(5  , 0.1, 0.1);
                robot.SmartTurnLeftON(90, 0.1);
                robot.DriveNoCorrection( 5  , 0.1, 0.1);
                robot.SmartTurnRightON(0, 0.1);
                PlaceGlyphB(robot);
            }
        } else {
            if (Bottom){
                robot.DriveNoCorrection ((35.75 + 9), 0.15, 0.15);//Drive Forward 39.5 in
                robot.SmartTurnLeftON(90, 0.1);
                PlaceGlyphB(robot);
            } else {
                robot.DriveNoCorrection(5  , 0.1, 0.1);
                robot.SmartTurnRightON(0, 0.1);
                robot.DriveNoCorrection( 5  , 0.1, 0.1);
                robot.SmartTurnLeftON(90, 0.1);
                PlaceGlyphB(robot);
            }
        }
    }
    public void LeftSeen(boolean Red, boolean Bottom, Robot_Hardware_and_Methods robot){
        if (Red){
            if (Bottom){
                robot.DriveNoCorrection ((43.25+9), 0.15, 0.15);//Drive Forward 48 in
                robot.SmartTurnRightON(90, 0.1);
                PlaceGlyphB(robot);
            } else {
                robot.DriveNoCorrection(5 , 0.1, 0.1);
                robot.SmartTurnLeftON(90, 0.1);
                robot.DriveNoCorrection(5   , 0.1, 0.1);
                robot.SmartTurnRightON(0, 0.1);
                PlaceGlyphB(robot);
            }
        } else {
            if (Bottom){
                robot.DriveNoCorrection ((43.25 + 9), 0.15, 0.15);//Drive Forward 48 in
                robot.SmartTurnLeftON(90, 0.1);
                PlaceGlyphB(robot);
            } else {
                robot.DriveNoCorrection(5  , 0.1, 0.1);
                robot.SmartTurnRightON(0, 0.1);
                robot.DriveNoCorrection(5   , 0.1, 0.1);
                robot.SmartTurnLeftON(90, 0.1);
                PlaceGlyphB(robot);
            }
        }
    }
}
