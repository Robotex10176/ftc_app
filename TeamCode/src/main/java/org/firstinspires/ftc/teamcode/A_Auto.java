package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static java.lang.Math.atan2;

/**
 * Created by Eric D'Urso on 10/24/2017.
 */
@Autonomous (name = "Auto")
public class A_Auto extends LinearOpMode {
    A_MAIN main = new A_MAIN();
    VuforiaLocalizer vuforia;
    boolean PROGRAMSELECTED = false;
    int PROGRAM = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        main.init(hardwareMap, true); //True means this is an autonomous
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();//leave parameters blank to not display on phone, fill with cameraMonitorViewId to view on phone
        parameters.vuforiaLicenseKey = "AV6yugj/////AAAAGTOHqL6RDUmVgo0jZreKdLgqXGK+wd8vPtaDUOeepBzJahj4mF1oh/urYHvdw40evwj26RACNoqaxJWb1nS9RCaPjg25pDCZJJgFNSmtPHBU+f5AN1Y7ZJbJjNOAg8XvkX99ixa/gD/9HO9Es11cXjv0GkJof4M3ynaDqrh8S18dT5XT8QReygM64YyWkrsqjWI5H7WqZkuBDCSfmq0MVQiQrF9LChxd3/dTjChBJvcD8Rud19FEvu5IXq/Xem4KpPtuWDQAH0gWKJve8AzlcQLomY2nKtjbpcrZLpVjwtoo+C8NCCL5ng14uRCI8eriEg3OFD6v4ZNSZmbZIcUqAuX4YtFQG3t1RL0MT+3fWsBf";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;//change to back to switch camera used
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        telemetry.addLine("SELECT AUTONOMOUS PROGRAM");
        telemetry.addLine("B is Red Auto Bottom");
        telemetry.addLine("Y is Red Auto Top");
        telemetry.addLine("A is Blue Auto Bottom");
        telemetry.addLine("X is Blue Auto Top");
        telemetry.update();

        while (!PROGRAMSELECTED){
            if(gamepad2.b){// program 1
                PROGRAM = 1;
                PROGRAMSELECTED = true;
            } else if (gamepad2.y){ //program 2
                PROGRAM = 2;
                PROGRAMSELECTED = true;
            } else if (gamepad2.a){// program 3
                PROGRAM = 3;
                PROGRAMSELECTED = true;
            } else if (gamepad2.x){//program 4
                PROGRAM = 4;
                PROGRAMSELECTED = true;
            } else {

            }
        }

        telemetry.addLine("Press Play to start");
        telemetry.update();
        waitForStart();// CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW

        //Raise Block and  Do VuMArk
        main.Lift.setPower(0.3);
        sleep(1000);
        main.Lift.setPower(0);
        relicTrackables.activate();
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        main.timer.reset();
        while (main.timer.seconds() < 10 && vuMark == RelicRecoveryVuMark.UNKNOWN) {vuMark = RelicRecoveryVuMark.from(relicTemplate);telemetry.addData("VuMark", "not visible");telemetry.update();idle();}
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {telemetry.addData("VuMark", "%s visible", vuMark);} else {telemetry.addData("VuMark", "not visible");}telemetry.update();
        //Done raising Block and VuMark
        if (PROGRAM == 1){ //RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM  RED AUTO BOTTOM
            telemetry.addLine("RED BOTTOM");
            telemetry.update();



            //CODE BELOW
            String jewelColor = KnockOffJewl(true);//false if blue
            telemetry.addData("Color Is", jewelColor);
            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.RIGHT) {
                main.DriveNoCorrection ((28.25 - 11.5), 0.15, 0.15);//Drive Forward 28.25 in
                main.SmartTurnRight(90, 0.1);
                main.DriveNoCorrection(3, 0.2, 0.2);
                PlaceGlyph();
            } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                main.DriveNoCorrection ((35.75 - 11.5), 0.15, 0.15);//Drive Forward 39.5 in
                main.SmartTurnRight(90, 0.1);
                main.DriveNoCorrection(3, 0.2, 0.2);
                PlaceGlyph();
            } else if (vuMark == RelicRecoveryVuMark.LEFT) {
                main.DriveNoCorrection ((43.25 - 11.5), 0.15, 0.15);//Drive Forward 48 in
                main.SmartTurnRight(90, 0.1);
                main.DriveNoCorrection(3, 0.2, 0.2);
                PlaceGlyph();
            } else {
                main.DriveNoCorrection ((35.75 - 11.5), 0.15, 0.15);//Drive Forward 39.5 in
                main.SmartTurnRight(90, 0.1);
                main.DriveNoCorrection(3, 0.2, 0.2);
                PlaceGlyph();
            }



        } else if (PROGRAM == 2){//RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP
            telemetry.addLine("RED TOP");
            telemetry.update();



            //CODE BELOW
            String jewelColor = KnockOffJewl(true);//false if blue
            telemetry.addData("Color Is", jewelColor);
            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.RIGHT){
                main.DriveNoCorrection( 5 , 0.1, 0.1);
                main.SmartTurnLeft(90, 0.1);
                main.DriveNoCorrection( 5  , 0.1, 0.1);
                main.SmartTurnRight(0, 0.1);
                PlaceGlyph();
            } else if (vuMark == RelicRecoveryVuMark.CENTER){
                main.DriveNoCorrection(5  , 0.1, 0.1);
                main.SmartTurnLeft(90, 0.1);
                main.DriveNoCorrection( 5  , 0.1, 0.1);
                main.SmartTurnRight(0, 0.1);
                PlaceGlyph();
            } else if (vuMark == RelicRecoveryVuMark.LEFT){
                main.DriveNoCorrection(5 , 0.1, 0.1);
                main.SmartTurnLeft(90, 0.1);
                main.DriveNoCorrection(5   , 0.1, 0.1);
                main.SmartTurnRight(0, 0.1);
                PlaceGlyph();
            } else{
                main.DriveNoCorrection(5  , 0.1, 0.1);
                main.SmartTurnLeft(90, 0.1);
                main.DriveNoCorrection( 5  , 0.1, 0.1);
                main.SmartTurnRight(0, 0.1);
                PlaceGlyph();
            }




        } else if (PROGRAM == 3){//BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM
            telemetry.addLine("BLUE BOTTOM");
            telemetry.update();



            //CODE BELOW
            String jewelColor = KnockOffJewl(false);//false if blue
            telemetry.addData("Color Is", jewelColor);
            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.RIGHT){
                main.DriveNoCorrection ((28.25 + 9), 0.15, 0.15);//Drive Forward 28.25 in
                main.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            } else if (vuMark == RelicRecoveryVuMark.CENTER){
                main.DriveNoCorrection ((35.75 + 9), 0.15, 0.15);//Drive Forward 39.5 in
                main.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            } else if (vuMark == RelicRecoveryVuMark.LEFT){
                main.DriveNoCorrection ((43.25 + 9), 0.15, 0.15);//Drive Forward 48 in
                main.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            } else{
                main.DriveNoCorrection ((35.75 + 9), 0.15, 0.15);//Drive Forward 39.5 in
                main.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            }



        } else if (PROGRAM == 4){//BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP
            telemetry.addLine("BLUE TOP");
            telemetry.update();



            //CODE BELOW
            String jewelColor = KnockOffJewl(false);//false if blue
            telemetry.addData("Color Is", jewelColor);
            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.RIGHT){
                main.DriveNoCorrection( 5 , 0.1, 0.1);
                main.SmartTurnRight(0, 0.1);
                main.DriveNoCorrection( 5  , 0.1, 0.1);
                main.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            } else if (vuMark == RelicRecoveryVuMark.CENTER){
                main.DriveNoCorrection(5  , 0.1, 0.1);
                main.SmartTurnRight(0, 0.1);
                main.DriveNoCorrection( 5  , 0.1, 0.1);
                main.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            } else if (vuMark == RelicRecoveryVuMark.LEFT){
                main.DriveNoCorrection(5  , 0.1, 0.1);
                main.SmartTurnRight(0, 0.1);
                main.DriveNoCorrection(5   , 0.1, 0.1);
                main.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            } else{
                main.DriveNoCorrection(5  , 0.1, 0.1);
                main.SmartTurnRight(0, 0.1);
                main.DriveNoCorrection( 5  , 0.1, 0.1);
                main.SmartTurnLeft(90, 0.1);
                PlaceGlyph();
            }



        } else {

        }
        //TAKE BELOW OUT SOON
        telemetry.addLine("RAN AUTO");
        telemetry.update();
        sleep(100000);

    }// METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS
    public String KnockOffJewl(boolean red) {
        String color;
        main.moveArm(120, 0.1);
        sleep(1000);
        if ((main.ColorSensor.red() > main.ColorSensor.blue())){
            color = "RED";
        } else if ((main.ColorSensor.red() < main.ColorSensor.blue())){
            color = "BLUE";
        } else {
            color = "UNKNOWN";
        }
        if (red) {
            if (color.compareTo("RED") == 0){
                main.SeeOurColor();
                sleep(100);
                main.JewelServoReturn(1);
                sleep(100);
            } else if (color.compareTo("BLUE") == 0){
                main.DontSeeOurColor();
                sleep(100);
                main.JewelServoReturn(1);
                sleep(100);
            } else {
                //UNKNOWN
            }
        } else {//means its blue
            if (color.compareTo("RED") == 0){
                main.DontSeeOurColor();
                sleep(100);
                main.JewelServoReturn(1);
                sleep(100);
            } else if (color.compareTo("BLUE") == 0){
                main.SeeOurColor();
                sleep(100);
                main.JewelServoReturn(1);
                sleep(100);
            } else {
                //UNKNOWN
            }
        }
        main.moveArm(-120, -0.1);
        return color;
    }

    public void PlaceGlyph (){
        main.MMS.setPosition(1);
        double Direction = 0.01;
        double Ub = 0.80;
        double Lb = 0.20;
        double SetPos;
        double Pos;
        main.MoveSensor.setPosition(0.5 * (Ub + Lb));
        sleep(100);
        while (main.GlyphSensor.red()<10){
            Pos = main.MoveSensor.getPosition();
            SetPos = Pos + Direction;
            if (SetPos > Ub){
                Direction = -Direction;
                main.DriveNoCorrection(0.4, 0.1, 0.1);
            } else if (SetPos < Lb){
                Direction = -Direction;
                main.DriveNoCorrection(0.4, 0.1, 0.1);
            }
            SetPos = Pos + Direction;
            main.MoveSensor.setPosition(SetPos);
            sleep(50);
        }
        double CurrentPos = main.MoveSensor.getPosition();
        double RobotLength = 6;
        double AngularDisplacement = 0.5 - CurrentPos;
        double InchesAway;
        double TurnAmount;//Degrees
        if (AngularDisplacement == 0){
            //Do nothing, skip to drive forward.
        } else {
            InchesAway = ((-((6)+(2/3)))*AngularDisplacement);
            TurnAmount = Math.toDegrees(atan2(RobotLength, InchesAway));
            if (TurnAmount < 0){TurnAmount = TurnAmount + 360;}
            //Used below to turn
            if (((InchesAway > -0.50)&&(InchesAway < 0.50))){
                //we are close enough
            } else {
                if (TurnAmount > 180){
                    main.SmartTurnRightD(TurnAmount, 0.15);
                } else {
                    main.SmartTurnLeftD(TurnAmount, 0.15);
                }
            }
        }
    }



    //OLD



    public void PlaceGlyph2 (){
        //drive and scan
        main.MMS.setPosition(1);
        double Direction = 0.01;
        double Ub = 0.80;
        double Lb = 0.20;
        double SetPos;
        double Pos;
        main.MoveSensor.setPosition(0.5 * (Ub + Lb));
        sleep(100);
        while (main.GlyphSensor.red()<10){
            Pos = main.MoveSensor.getPosition();
            SetPos = Pos + Direction;
            if (SetPos > Ub){
                Direction = -Direction;
                main.DriveNoCorrection(0.4, 0.1, 0.1);
            } else if (SetPos < Lb){
                Direction = -Direction;
                main.DriveNoCorrection(0.4, 0.1, 0.1);
            }
            SetPos = Pos + Direction;
            main.MoveSensor.setPosition(SetPos);
            sleep(50);
        }
        double CurrentPos = main.MoveSensor.getPosition();
        if (GlyphTurnAmount(CurrentPos) > 0.5){
            main.SmartTurnRightD(GlyphTurnAmount(CurrentPos), 0.1);
        } else if (GlyphTurnAmount(CurrentPos) < -0.5){
            main.SmartTurnLeftD(GlyphTurnAmount(CurrentPos), 0.1);
        } else {

        }
        //Open, reverse, and park in zone
        main.MMS.setPosition(0.6);//retract arm and place
        main.OpenClaw();
        main.DriveNoCorrection(-5, 0.1, 0.1);
        main.FlatClaw();
        main.DriveNoCorrection(2, 0.1, 0.1);
    }
    public double GlyphTurnAmount (double servoValue){// hope to return degrees
        double robotLength = 24;//in inches
        servoValue =  0.5 - servoValue ;
        if (servoValue == 0){
            //mapped to nothing turn
        } else {
            servoValue = ((-((6)+(2/3)))*servoValue);
        }
        double inchesAway = servoValue;
        double turnAmount;
        turnAmount = Math.toDegrees(atan2(robotLength, inchesAway));
        if (turnAmount < 0.0) {
            turnAmount += 360.0;
        }
        return turnAmount;
    }
}


