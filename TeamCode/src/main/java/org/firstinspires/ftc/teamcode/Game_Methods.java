package org.firstinspires.ftc.teamcode;

/**
 * Created by Eric D'Urso on 10/16/2017.
 */

public class Game_Methods {

    //Constructor
    public Game_Methods (){}

    Robot_Hardware_and_Methods robot = new Robot_Hardware_and_Methods();

    public void KnockOffJewl(boolean red) {
        String color = "UNKNOWN";
        robot.moveArm(120, 0.1);
        //sleep(1000);
        if ((robot.ColorSensor.red() > robot.ColorSensor.blue())){
            color = "RED";
        } else if ((robot.ColorSensor.red() < robot.ColorSensor.blue())){
            color = "BLUE";
        } else {
            color = "UNKNOWN";
        }
        //telemetry.addData("Sensed Color Is ", color);
        //telemetry.update();
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
    }

    public void PlaceGlyph(){
        robot.OpenClaw();
        robot.DriveNoCorrection(-5, 0.1, 0.1);
        robot.FlatClaw();
        robot.DriveNoCorrection(2, 0.1, 0.1);
    }
    public void RightSeen(boolean Red, boolean Bottom){
        if (Red){
            if (Bottom){
                robot.DriveNoCorrection (28.25, 0.15, 0.15);//Drive Forward 28.25 in
                robot.SmartTurnRight(90, 0.1);
                robot.DriveNoCorrection (3, 0.15, 0.15);
                PlaceGlyph();
            } else {
                robot.DriveNoCorrection( 5 , 0.1, 0.1);
                robot.SmartTurnLeft(90, 0.1);
                robot.DriveNoCorrection( 5  , 0.1, 0.1);
                robot.SmartTurnRight(0, 0.1);
                PlaceGlyph();
            }
        } else {
            if (Bottom){
                robot.DriveNoCorrection (28.25, 0.15, 0.15);//Drive Forward 28.25 in
                robot.SmartTurnLeft(90, 0.1);
                robot.DriveNoCorrection (3, 0.15, 0.15);
                PlaceGlyph();
            } else {
                robot.DriveNoCorrection( 5 , 0.1, 0.1);
                robot.SmartTurnRight(0, 0.1);
                robot.DriveNoCorrection( 5  , 0.1, 0.1);
                robot.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            }
        }
    }
    public void CenterSeen(boolean Red, boolean Bottom){
        if (Red){
            if (Bottom){
                robot.DriveNoCorrection (35.75, 0.15, 0.15);//Drive Forward 39.5 in
                robot.SmartTurnRight(90, 0.1);
                robot.DriveNoCorrection (3, 0.15, 0.15);
                PlaceGlyph();
            } else {
                robot.DriveNoCorrection(5  , 0.1, 0.1);
                robot.SmartTurnLeft(90, 0.1);
                robot.DriveNoCorrection( 5  , 0.1, 0.1);
                robot.SmartTurnRight(0, 0.1);
                PlaceGlyph();
            }
        } else {
            if (Bottom){
                robot.DriveNoCorrection (35.75, 0.15, 0.15);//Drive Forward 39.5 in
                robot.SmartTurnLeft(90, 0.1);
                robot.DriveNoCorrection (3, 0.15, 0.15);
                PlaceGlyph();
            } else {
                robot.DriveNoCorrection(5  , 0.1, 0.1);
                robot.SmartTurnRight(0, 0.1);
                robot.DriveNoCorrection( 5  , 0.1, 0.1);
                robot.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            }
        }
    }
    public void LeftSeen(boolean Red, boolean Bottom){
        if (Red){
            if (Bottom){
                robot.DriveNoCorrection (43.25, 0.15, 0.15);//Drive Forward 48 in
                robot.SmartTurnRight(90, 0.1);
                robot.DriveNoCorrection (3, 0.15, 0.15);
                PlaceGlyph();
            } else {
                robot.DriveNoCorrection(5 , 0.1, 0.1);
                robot.SmartTurnLeft(90, 0.1);
                robot.DriveNoCorrection(5   , 0.1, 0.1);
                robot.SmartTurnRight(0, 0.1);
                PlaceGlyph();
            }
        } else {
            if (Bottom){
                robot.DriveNoCorrection (43.25, 0.15, 0.15);//Drive Forward 48 in
                robot.SmartTurnLeft(90, 0.1);
                robot.DriveNoCorrection (3, 0.15, 0.15);
                PlaceGlyph();
            } else {
                robot.DriveNoCorrection(5  , 0.1, 0.1);
                robot.SmartTurnRight(0, 0.1);
                robot.DriveNoCorrection(5   , 0.1, 0.1);
                robot.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            }
        }
    }
}
