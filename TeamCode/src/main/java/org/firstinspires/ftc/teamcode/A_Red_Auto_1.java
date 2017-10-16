package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


/**
 * Created by Eric D'Urso on 9/16/2017.
 */
@Autonomous (name = "Red_Auto_1", group = "Red Autonomous")
public class A_Red_Auto_1 extends LinearOpMode {

    //ROBOT CONFIGURE
    H_RobotHardware robot = new H_RobotHardware();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;

    //.

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap, true); //True means this is an autonomous
        OpenClaw();
        telemetry.addLine("Playing Warning Sound");
        telemetry.update();
        //Warning Sound Goes Here
        telemetry.addLine("Closing Claw in 3");
        telemetry.update();
        sleep(1000);
        telemetry.addLine("Closing Claw in 2");
        telemetry.update();
        sleep(1000);
        telemetry.addLine("Closing Claw in 1");
        telemetry.update();
        sleep(1000);
        CloseClaw();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();//leave parameters blank to not display on phone, fill with cameraMonitorViewId to view on phone
        parameters.vuforiaLicenseKey = "AV6yugj/////AAAAGTOHqL6RDUmVgo0jZreKdLgqXGK+wd8vPtaDUOeepBzJahj4mF1oh/urYHvdw40evwj26RACNoqaxJWb1nS9RCaPjg25pDCZJJgFNSmtPHBU+f5AN1Y7ZJbJjNOAg8XvkX99ixa/gD/9HO9Es11cXjv0GkJof4M3ynaDqrh8S18dT5XT8QReygM64YyWkrsqjWI5H7WqZkuBDCSfmq0MVQiQrF9LChxd3/dTjChBJvcD8Rud19FEvu5IXq/Xem4KpPtuWDQAH0gWKJve8AzlcQLomY2nKtjbpcrZLpVjwtoo+C8NCCL5ng14uRCI8eriEg3OFD6v4ZNSZmbZIcUqAuX4YtFQG3t1RL0MT+3fWsBf";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;//change to back to switch camera used
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        //.

        waitForStart();

        //move block off ground
        robot.Lift.setPower(0.3);
        sleep(1000);
        robot.Lift.setPower(0);

        //VUFORIA SCAN
        relicTrackables.activate();
        //all of this code is in COnceptVuMarkIdentification.java
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        robot.timer.reset();
        while ( robot.timer.seconds() < 10 && vuMark == RelicRecoveryVuMark.UNKNOWN ) {//While it cant see vuMark or time is less that 10
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

        KnockOffJewl(true);//would be false if we were blue

        if (vuMark == RelicRecoveryVuMark.RIGHT){
            dumbDrive (28.25, 0.15, 0.15);//Drive Forward 28.25 in
            SmartTurnRight(90, 0.1);
            dumbDrive (3, 0.15, 0.15);
            PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.CENTER){
            dumbDrive (35.75, 0.15, 0.15);//Drive Forward 39.5 in
            SmartTurnRight(90, 0.1);
            dumbDrive (3, 0.15, 0.15);
            PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.LEFT){//use else if construct to "dasiychain" ifs together
            dumbDrive (43.25, 0.15, 0.15);//Drive Forward 48 in
            SmartTurnRight(90, 0.1);
            dumbDrive (3, 0.15, 0.15);
            PlaceGlyph();
        } else{
            dumbDrive (35.75, 0.15, 0.15);//Drive Forward to one of the columns
            SmartTurnRight(90, 0.1);
            dumbDrive (3, 0.15, 0.15);
            PlaceGlyph();
        }

    }
    public void KnockOffJewl(boolean red) {
        String color = "UNKNOWN";
        moveArm(120, 0.1);
        sleep(1000);
        if ((robot.ColorSensor.red() > robot.ColorSensor.blue())){
            color = "RED";
        } else if ((robot.ColorSensor.red() < robot.ColorSensor.blue())){
            color = "BLUE";
        } else {
            color = "UNKNOWN";
        }
        telemetry.addData("Sensed Color Is ", color);
        telemetry.update();
        //sleep(2000);
        if (red) {
            if (color.compareTo("RED") == 0){
                SeeOurColor();
            } else if (color.compareTo("BLUE") == 0){
                DontSeeOurColor();
            } else {
               //UNKNOWN
            }
        } else {//means its blue
            if (color.compareTo("RED") == 0){
                DontSeeOurColor();
            } else if (color.compareTo("BLUE") == 0){
                SeeOurColor();
            } else {
                //UNKNOWN
            }
        }
        moveArm(-120, -0.1);
        sleep(1000);
    }
    public void PlaceGlyph(){
        OpenClaw();
        Drive(-5, 0.1, 0.1);
        FlatClaw();
        Drive(2, 0.1, 0.1);

        /**this place glyph has to be a piece of code in which the robot is
         * perfectly prepositioned in front of the right collumn (vuMark variable
         * cant be identified in an independent method)
         */
    }
   
}

