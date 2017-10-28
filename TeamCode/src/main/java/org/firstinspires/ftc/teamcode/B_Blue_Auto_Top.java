package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Eric D'Urso on 10/16/2017.
 */
@Autonomous(name = "Blue Auto Top", group = "Blue Autonomous")
@Disabled
public class B_Blue_Auto_Top extends LinearOpMode {
    A_MAIN main = new A_MAIN();
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
