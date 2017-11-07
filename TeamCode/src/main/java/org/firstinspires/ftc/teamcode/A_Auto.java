package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static java.lang.Math.atan2;

/**
 * Created by Eric D'Urso on 10/24/2017.
 * This is the only autonomous program
 */
@Autonomous (name = "Auto")
public class A_Auto extends LinearOpMode {
    A_Main main = new A_Main();
    VuforiaLocalizer vuforia;
    boolean PROGRAMSELECTED = false;
    int PROGRAM = 0;
    double MAINPWR = 0.50;
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

        double zAngle = main.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        telemetry.addData("0 deg heading", zAngle);
        telemetry.update();
        //Raise Block and  Do VuMArk
        main.Lift.setPower(0.3);
        sleep(500);
        main.Lift.setPower(0);
        relicTrackables.activate();
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        main.timer.reset();
        while (main.timer.seconds() < 3 && vuMark == RelicRecoveryVuMark.UNKNOWN) {vuMark = RelicRecoveryVuMark.from(relicTemplate);telemetry.addData("VuMark", "not visible");telemetry.update();idle();}
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
                main.DriveNoCorrection (21.5, MAINPWR, MAINPWR);//Drive Forward 21.5 in
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(5, MAINPWR, MAINPWR);
                PlaceGlyph(true);
            } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                main.DriveNoCorrection (29, MAINPWR, MAINPWR);//Drive Forward 29 in
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(5, MAINPWR, MAINPWR);
                PlaceGlyph(true);
            } else if (vuMark == RelicRecoveryVuMark.LEFT) {
                main.DriveNoCorrection (36.5, MAINPWR, MAINPWR);//Drive Forward 36.5 in
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(5, MAINPWR, MAINPWR);
                PlaceGlyph(true);
            } else {
                main.DriveNoCorrection (29, MAINPWR, MAINPWR);//Drive Forward 29 in to center
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(5, MAINPWR, MAINPWR);
                PlaceGlyph(true);
            }



        } else if (PROGRAM == 2){//RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP  RED AUTO TOP
            telemetry.addLine("RED TOP");
            telemetry.update();



            //CODE BELOW
            String jewelColor = KnockOffJewl(true);//false if blue
            telemetry.addData("Color Is", jewelColor);
            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.RIGHT){
                main.DriveNoCorrection( 19 , MAINPWR, MAINPWR);
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection( 7  , MAINPWR, MAINPWR);//drive 7 inches
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(4, MAINPWR, MAINPWR);
                PlaceGlyph(true);
            } else if (vuMark == RelicRecoveryVuMark.CENTER){
                main.DriveNoCorrection(19 , MAINPWR, MAINPWR);
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection( 14.5  , MAINPWR, MAINPWR);//drive 14.5 in
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(4, MAINPWR, MAINPWR);
                PlaceGlyph(true);
            } else if (vuMark == RelicRecoveryVuMark.LEFT){
                main.DriveNoCorrection(19 , MAINPWR, MAINPWR);
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection(22,  MAINPWR, MAINPWR);//drive 22 in
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(4, MAINPWR, MAINPWR);
                PlaceGlyph(true);
            } else{
                main.DriveNoCorrection(19  , MAINPWR, MAINPWR);
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection( 5  , MAINPWR, MAINPWR);//drive to center
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(4, MAINPWR, MAINPWR);
                PlaceGlyph(true);
            }




        } else if (PROGRAM == 3){//BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM  BLUE AUTO BOTTOM
            telemetry.addLine("BLUE BOTTOM");
            telemetry.update();



            //CODE BELOW
            String jewelColor = KnockOffJewl(false);//false if blue
            telemetry.addData("Color Is", jewelColor);
            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.LEFT) {
                main.DriveNoCorrection (-34.25, MAINPWR, MAINPWR);//Drive Forward 34.75 in
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(5, 0.2, 0.2);
                PlaceGlyph(false);
            } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                main.DriveNoCorrection (-41.75, MAINPWR, MAINPWR);//Drive Forward 42.75 in
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(5, 0.2, 0.2);
                PlaceGlyph(false);
            } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
                main.DriveNoCorrection (-49.25, MAINPWR, MAINPWR);//Drive Forward  in
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(5, 0.2, 0.2);
                PlaceGlyph(false);
            } else {
                main.DriveNoCorrection (42.75, MAINPWR, MAINPWR);//Drive Forward to center pos
                main.SmartTurnRight(87, MAINPWR);
                main.DriveNoCorrection(5, 0.2, 0.2);
                PlaceGlyph(false);
            }



        } else if (PROGRAM == 4){//BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP  BLUE AUTO TOP
            telemetry.addLine("BLUE TOP");
            telemetry.update();



            //CODE BELOW
            String jewelColor = KnockOffJewl(false);//false if blue
            telemetry.addData("Color Is", jewelColor);
            telemetry.update();
            if (vuMark == RelicRecoveryVuMark.LEFT){
                main.DriveNoCorrection( -31.75 , MAINPWR, MAINPWR);
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection(6.5  , MAINPWR, MAINPWR);//drive 6.5 in
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection(4, MAINPWR, MAINPWR);
                PlaceGlyph(false);
            } else if (vuMark == RelicRecoveryVuMark.CENTER){
                main.DriveNoCorrection(-31.75 , MAINPWR, MAINPWR);
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection(14   , MAINPWR, MAINPWR);//drive 14 in
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection(4, MAINPWR, MAINPWR);
                PlaceGlyph(false);
            } else if (vuMark == RelicRecoveryVuMark.RIGHT){
                main.DriveNoCorrection(-31.75 , MAINPWR, MAINPWR);
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection(21.5  , MAINPWR, MAINPWR); //drive 21 inches
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection(3, MAINPWR, MAINPWR);
                PlaceGlyph(false);
            } else{
                main.DriveNoCorrection(-31.75  , MAINPWR, MAINPWR);
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection(14  , MAINPWR, MAINPWR);//drive to center
                main.SmartTurnLeft(87, MAINPWR);
                main.DriveNoCorrection(4, MAINPWR, MAINPWR);
                PlaceGlyph(false);
            }



        } else {

        }
        sleep(10000);

    }// METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS
    public String KnockOffJewl(boolean red) {
        String color;
        main.moveArm(120, 0.1);
        sleep(500);
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
                sleep(200);
                main.JewelServoReturn(1);
                sleep(100);
            } else if (color.compareTo("BLUE") == 0){
                main.DontSeeOurColor();
                sleep(200);
                main.JewelServoReturn(1);
                sleep(100);
            } else {
                //UNKNOWN
            }
        } else {//means its blue
            if (color.compareTo("RED") == 0){
                main.DontSeeOurColor();
                sleep(200);
                main.JewelServoReturn(1);
                sleep(100);
            } else if (color.compareTo("BLUE") == 0){
                main.SeeOurColor();
                sleep(200);
                main.JewelServoReturn(1);
                sleep(100);
            } else {
                //UNKNOWN
            }
        }
        main.moveArm(-130, -0.1);//used to be 120
        return color;
    }

    public void PlaceGlyph (boolean red){
        main.MMS.setPosition(1);
        double Direction = 0.01;
        double Ub = 0.80;
        double Lb = 0.20;
        double SetPos;
        double Pos;
        main.MoveSensor.setPosition(0.5 * (Ub + Lb));
        sleep(100);
        if (red) {
            while (main.GlyphSensor.red() < 10) {
                try {
                    Pos = main.MoveSensor.getPosition();
                    SetPos = Pos + Direction;
                    if (SetPos > Ub) {
                        Direction = -Direction;
                        main.DriveNoCorrection(0.5, 0.15, 0.15);
                    } else if (SetPos < Lb) {
                        Direction = -Direction;
                        main.DriveNoCorrection(0.5, 0.15, 0.15);
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
                        main.DriveNoCorrection(0.5, 0.15, 0.15);
                    } else if (SetPos < Lb) {
                        Direction = -Direction;
                        main.DriveNoCorrection(0.5, 0.15, 0.15);
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
        main.MMS.setPosition(0.1);//retract arm and place
        double WheelsToEndOfArmDis = 6;
        double AngularDisplacement = 0.5 - CurrentPos;
        double InchesAway;
        double TurnAmount;//Degrees
        if (AngularDisplacement == 0){
            //Do nothing, skip to drive forward.
        } else {
            InchesAway = (-6.666*AngularDisplacement);
            telemetry.addData("Inches Away", InchesAway);
            telemetry.update();
            TurnAmount = Math.toDegrees(atan2(InchesAway, WheelsToEndOfArmDis));
            //Used below to turn
            main.DriveNoCorrection(1.75, 0.2, 0.2);
            if (TurnAmount > 0.0){
                main.SmartTurnRightD(TurnAmount, 0.15);
            } else {
                main.SmartTurnLeftD(TurnAmount, 0.15);
            }
        }
        // drive forward, Open, reverse, and park in zone
        main.DriveNoCorrection(8.25, 0.25, 0.25);
        main.OpenClaw();
        main.DriveNoCorrection(-3, 0.25, 0.25);
    }





    public void DriveToCryptobox (double Power){
        int newLeftTarget;
        int newRightTarget;
        final double COUNTS_PER_MOTOR_REV = 1440;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
        final double WHEEL_DIAMETER_INCHES = 3.8125;     // For figuring circumference
        final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
        if (opModeIsActive()) {
            newLeftTarget = main.leftDrive.getCurrentPosition() + (int) (10 * COUNTS_PER_INCH);
            newRightTarget = main.rightDrive.getCurrentPosition() + (int) (10 * COUNTS_PER_INCH);
            main.leftDrive.setTargetPosition(newLeftTarget);
            main.rightDrive.setTargetPosition(newRightTarget);
            main.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            main.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            main.leftDrive.setPower(Math.abs(Power));
            main.rightDrive.setPower(Math.abs(Power));

            main.MMS.setPosition(1);
            double Direction = 0.01;
            double Ub = 0.80;
            double Lb = 0.20;
            double SetPos;
            double Pos;
            main.MoveSensor.setPosition(0.5 * (Ub + Lb));
            sleep(100);
            while ((main.leftDrive.isBusy() && main.rightDrive.isBusy())
                    ||((main.GlyphSensor.red()<10)&&(main.GlyphSensor.blue()<10)) ) {


                try {
                    Pos = main.MoveSensor.getPosition();
                    SetPos = Pos + Direction;
                    if (SetPos > Ub) {
                        Direction = -Direction;
                        main.DriveNoCorrection(0.5, 0.15, 0.15);
                    } else if (SetPos < Lb) {
                        Direction = -Direction;
                        main.DriveNoCorrection(0.5, 0.15, 0.15);
                    }
                    SetPos = Pos + Direction;
                    main.MoveSensor.setPosition(SetPos);
                    sleep(50);
                    idle();
                }
                catch (Exception e){
                    telemetry.addLine("Problem in Glyph Sensor");
                    telemetry.update();
                }



            }
            main.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            main.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            main.rightDrive.setPower(0);
            main.leftDrive.setPower(0);
            main.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            main.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            // drive forward, Open, reverse, and park in zone
            main.DriveNoCorrection(7, 0.15, 0.15);
            main.OpenClaw();
            main.DriveNoCorrection(-3, 0.15, 0.15);
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


    public float AngularSeparation (float a, float b){
        float rv;
        rv = Math.abs(a-b);
        if (rv >= 180){
            rv = 360 - rv;
        }
        return rv;
    }
    public void SmartTurnRightTELE(float Angle, double Power){//turn right is clockwise
        //code to turn untill an angle ex 0, 90, -90
        float zAngle;
        float targetAngle;
        zAngle = main.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        //Set target direction in range -180 - 180;
        targetAngle = (zAngle - Angle + 180);
        while (targetAngle > 360){ targetAngle = targetAngle - 360; }
        while (targetAngle < 0){ targetAngle = targetAngle + 360; }
        targetAngle = targetAngle - 180;
        float as;
        as = AngularSeparation(zAngle, targetAngle);
        while (as > 1.0){
            as = AngularSeparation(zAngle, targetAngle);
            if(as < 10) {
                main.leftDrive.setPower(0.5 * Power);
                main.rightDrive.setPower(-0.5 * Power);
            } else {
                main.leftDrive.setPower(Power);
                main.rightDrive.setPower(-Power);
            }
            zAngle = main.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                    AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            telemetry.addData("Gyro angle is ", zAngle);
            telemetry.update();
        }
        main.leftDrive.setPower(0);
        main.rightDrive.setPower(0);
        telemetry.addData("Gyro angle is ", zAngle);
        telemetry.update();

    }
}


