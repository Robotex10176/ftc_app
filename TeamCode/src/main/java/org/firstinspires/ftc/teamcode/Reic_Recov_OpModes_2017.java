package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name = "Blue Auto Bottom", group = "Blue Autonomous")//disable all other files soon
@Disabled
class Blue_Auto_Bottom extends LinearOpMode {

    //ROBOT CONFIGURE
    A_Main main = new A_Main();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;

    //.

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
        telemetry.addLine("Press Play to start");
        telemetry.update();
        //.

        waitForStart();

        //move block off ground
        main.Lift.setPower(0.3);
        sleep(1000);
        main.Lift.setPower(0);

        //VUFORIA SCAN
        relicTrackables.activate();
        //all of this code is in COnceptVuMarkIdentification.java
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        main.timer.reset();
        while ( main.timer.seconds() < 10 && vuMark == RelicRecoveryVuMark.UNKNOWN ) {//While it cant see vuMark or time is less that 10
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            telemetry.addData("VuMark", "not visible");
            telemetry.update();
            idle();
        }
        if (vuMark != RelicRecoveryVuMark.UNKNOWN){
            telemetry.addData("VuMark", "%s visible", vuMark);
        }
        else {
            telemetry.addData("VuMark", "not visible");
        }
        telemetry.update();

        //jewel

        if (vuMark == RelicRecoveryVuMark.RIGHT){
            main.DriveNoCorrection ((28.25 + 9), 0.15, 0.15);//Drive Forward 28.25 in
            main.SmartTurnLeft(90, 0.1);
            //PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.CENTER){
            main.DriveNoCorrection ((35.75 + 9), 0.15, 0.15);//Drive Forward 39.5 in
            main.SmartTurnLeft(90, 0.1);
            //PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.LEFT){
            main.DriveNoCorrection ((43.25 + 9), 0.15, 0.15);//Drive Forward 48 in
            main.SmartTurnLeft(90, 0.1);
            //PlaceGlyph();
        } else{
            main.DriveNoCorrection ((35.75 + 9), 0.15, 0.15);//Drive Forward 39.5 in
            main.SmartTurnLeft(90, 0.1);
            //PlaceGlyph();
        }

    }
}



//NEW CLASS



@Autonomous(name = "Blue Auto Top", group = "Blue Autonomous")
@Disabled
class Blue_Auto_Top extends LinearOpMode {
    A_Main main = new A_Main();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
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
        telemetry.addLine("Press Play to start");
        telemetry.update();
        waitForStart();
        //move block off ground
        main.Lift.setPower(0.3);
        sleep(1000);
        main.Lift.setPower(0);

        //VUFORIA SCAN
        relicTrackables.activate();
        //all of this code is in COnceptVuMarkIdentification.java
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        main.timer.reset();
        while ( main.timer.seconds() < 10 && vuMark == RelicRecoveryVuMark.UNKNOWN ) {//While it cant see vuMark or time is less that 10
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            telemetry.addData("VuMark", "not visible");
            telemetry.update();
            idle();
        }
        if (vuMark != RelicRecoveryVuMark.UNKNOWN){
            telemetry.addData("VuMark", "%s visible", vuMark);
        }
        else {
            telemetry.addData("VuMark", "not visible");
        }
        telemetry.update();

        //jewel

        if (vuMark == RelicRecoveryVuMark.RIGHT){
            main.DriveNoCorrection( 5 , 0.1, 0.1);
            main.SmartTurnRight(0, 0.1);
            main.DriveNoCorrection( 5  , 0.1, 0.1);
            main.SmartTurnLeft(90, 0.1);
            //PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.CENTER){
            main.DriveNoCorrection(5  , 0.1, 0.1);
            main.SmartTurnRight(0, 0.1);
            main.DriveNoCorrection( 5  , 0.1, 0.1);
            main.SmartTurnLeft(90, 0.1);
            //PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.LEFT){
            main.DriveNoCorrection(5  , 0.1, 0.1);
            main.SmartTurnRight(0, 0.1);
            main.DriveNoCorrection(5   , 0.1, 0.1);
            main.SmartTurnLeft(90, 0.1);
            //PlaceGlyph();
        } else{
            main.DriveNoCorrection(5  , 0.1, 0.1);
            main.SmartTurnRight(0, 0.1);
            main.DriveNoCorrection( 5  , 0.1, 0.1);
            main.SmartTurnLeft(90, 0.1);
            //PlaceGlyph();
        }
    }
}



//NEW CLASS



@Autonomous (name = "Red Auto Bottom", group = "Red Autonomous")
@Disabled
class Red_Auto_Bottom extends LinearOpMode {

    //ROBOT CONFIGURE
    A_Main main = new A_Main();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;

    //.

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
        telemetry.addLine("Press Play to start");
        telemetry.update();
        //.

        waitForStart();

        //move block off ground
        main.Lift.setPower(0.3);
        sleep(1000);
        main.Lift.setPower(0);

        //VUFORIA SCAN
        relicTrackables.activate();
        //all of this code is in COnceptVuMarkIdentification.java
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        main.timer.reset();
        while (main.timer.seconds() < 10 && vuMark == RelicRecoveryVuMark.UNKNOWN) {//While it cant see vuMark or time is less that 10
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            telemetry.addData("VuMark", "not visible");
            telemetry.update();
            idle();
        }
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
            telemetry.addData("VuMark", "%s visible", vuMark);
        } else {
            telemetry.addData("VuMark", "not visible");
        }
        telemetry.update();

        //would be false if we were blue
        //String jewelColor = KnockOffJewl(true);//false if blue
        //telemetry.addData("Color Is", jewelColor);
        //telemetry.update();

        if (vuMark == RelicRecoveryVuMark.RIGHT) {
            main.DriveNoCorrection ((28.25 - 11.5), 0.15, 0.15);//Drive Forward 28.25 in
            main.SmartTurnRight(90, 0.1);
            main.DriveNoCorrection(3, 0.2, 0.2);
            //PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.CENTER) {
            main.DriveNoCorrection ((35.75 - 11.5), 0.15, 0.15);//Drive Forward 39.5 in
            main.SmartTurnRight(90, 0.1);
            main.DriveNoCorrection(3, 0.2, 0.2);
            //PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.LEFT) {
            main.DriveNoCorrection ((43.25 - 11.5), 0.15, 0.15);//Drive Forward 48 in
            main.SmartTurnRight(90, 0.1);
            main.DriveNoCorrection(3, 0.2, 0.2);
            //PlaceGlyph();
        } else {
            main.DriveNoCorrection ((35.75 - 11.5), 0.15, 0.15);//Drive Forward 39.5 in
            main.SmartTurnRight(90, 0.1);
            main.DriveNoCorrection(3, 0.2, 0.2);
            //PlaceGlyph();
        }
    }
}



//NEW CLASS



@Autonomous(name = "Red Auto Top", group = "Red Autonomous")
@Disabled
class Red_Auto_Top extends LinearOpMode {
    A_Main main = new A_Main();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
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
        telemetry.addLine("Press Play to start");
        telemetry.update();
        waitForStart();
        //move block off ground
        main.Lift.setPower(0.3);
        sleep(1000);
        main.Lift.setPower(0);

        //VUFORIA SCAN
        relicTrackables.activate();
        //all of this code is in COnceptVuMarkIdentification.java
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        main.timer.reset();
        while ( main.timer.seconds() < 10 && vuMark == RelicRecoveryVuMark.UNKNOWN ) {//While it cant see vuMark or time is less that 10
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            telemetry.addData("VuMark", "not visible");
            telemetry.update();
            idle();
        }
        if (vuMark != RelicRecoveryVuMark.UNKNOWN){
            telemetry.addData("VuMark", "%s visible", vuMark);
        }
        else {
            telemetry.addData("VuMark", "not visible");
        }
        telemetry.update();

        //jewel

        if (vuMark == RelicRecoveryVuMark.RIGHT){
            main.DriveNoCorrection( 5 , 0.1, 0.1);
            main.SmartTurnLeft(90, 0.1);
            main.DriveNoCorrection( 5  , 0.1, 0.1);
            main.SmartTurnRight(0, 0.1);
            //PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.CENTER){
            main.DriveNoCorrection(5  , 0.1, 0.1);
            main.SmartTurnLeft(90, 0.1);
            main.DriveNoCorrection( 5  , 0.1, 0.1);
            main.SmartTurnRight(0, 0.1);
            //PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.LEFT){
            main.DriveNoCorrection(5 , 0.1, 0.1);
            main.SmartTurnLeft(90, 0.1);
            main.DriveNoCorrection(5   , 0.1, 0.1);
            main.SmartTurnRight(0, 0.1);
            //PlaceGlyph();
        } else{
            main.DriveNoCorrection(5  , 0.1, 0.1);
            main.SmartTurnLeft(90, 0.1);
            main.DriveNoCorrection( 5  , 0.1, 0.1);
            main.SmartTurnRight(0, 0.1);
            //PlaceGlyph();
        }
    }
}



//NEW CLASS



@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
@Disabled
class TeleOp_Old extends OpMode {
    A_Main main = new A_Main();
    boolean closedClaw = false;
    @Override
    public void init() {
        main.init(hardwareMap, false);
    }
    @Override
    public void loop() {
        double x = gamepad1.right_stick_x;
        double y = -gamepad1.right_stick_y;
        /* ry -1 is up, 1 is down, -1 is left, 1 is right*/
        if ((Math.abs(x)<0.1)&&(Math.abs(y)<0.1)){
            //Do Nothing based on controller pos in middle
            //main.leftDrive.setPower(0);
            //main.rightDrive.setPower(0);
            //telemetry.addLine("ME DONT DO NOTHIN");
            //telemetry.update();
            if (gamepad1.left_trigger > 0.2 || gamepad1.right_trigger > 0.2){
                main.rightDrive.setPower(gamepad1.right_trigger);
                main.leftDrive.setPower(gamepad1.left_trigger);
            } else {
                main.rightDrive.setPower(0);
                main.leftDrive.setPower(0);
            }
        } else {
            //do something base on controller position
            if ((y>0)&&(y>(Math.abs(x)))){
                //Top Part, Single stick forward
                main.rightDrive.setPower(main.scaleController(y));
                main.leftDrive.setPower(main.scaleController(y));
                //telemetry.addLine("ME MOVE FORWARD");
                //telemetry.update();
            }
            else if ((y<0)&&(y<((-1.0)*(Math.abs(x))))){
                //bottom part, single stick reverse
                main.rightDrive.setPower(main.scaleController(y));
                main.leftDrive.setPower(main.scaleController(y));
                //telemetry.addLine("ME MOVE BACKWARD");
                //telemetry.update();
            } else if (x>0){
                //right part, turn right
                main.rightDrive.setPower(main.halfPwr(x));
                main.leftDrive.setPower(main.halfPwr(-x));
                //telemetry.addLine("ME TURN RIGHT");
                //telemetry.update();
            }
            else {
                //left part, turn left
                main.rightDrive.setPower(main.halfPwr(x));
                main.leftDrive.setPower(main.halfPwr(-x));
                //telemetry.addLine("ME TURN LEFT");
                //telemetry.update();
            }
        }

        if (gamepad1.dpad_up) {
            main.Lift.setPower(0.3);
        }
        if (gamepad1.dpad_down) {
            main.Lift.setPower(-0.3);
        }
        if (((!gamepad1.dpad_up) && (!gamepad1.dpad_down))) {
            main.Lift.setPower(0);
        }
        //if (gamepad1.left_bumper){
        //    main.CloseClaw();
        //} else {
        //    main.OpenClaw();
        //}
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

        if (gamepad1.left_bumper){
            double rightPos = main.RightClaw.getPosition();
            double leftPos = main.LeftClaw.getPosition();
            if (closedClaw){//check if actually closed (indicated by hardware)
                if (rightPos < 0.4 && leftPos > 0.4){
                    closedClaw = false;//set open
                    main.OpenClaw();
                }
            } else {//check if actually open (indicated by hardware)
                if (rightPos > 0.4 && leftPos < 0.4) {
                    closedClaw = true;//set closed
                    main.CloseClaw();

                }
            }

            telemetry.addData("Right Pos ", rightPos);
            telemetry.addData("Left Pos ", leftPos);
            telemetry.addData("Claw is Closed : ", closedClaw);
            telemetry.update();
        }
        //actually move it below
        if (closedClaw){
            main.CloseClaw();
        } else {
            main.OpenClaw();
        }
    }
}
