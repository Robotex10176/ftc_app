package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
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
import org.firstinspires.ftc.teamcode.U_vuTime;

import static org.firstinspires.ftc.teamcode.U_vuTime.ranit;


/**
 * Created by Eric D'Urso on 9/16/2017.
 */
@Autonomous (name = "A_Red_Auto_1", group = "Red Autonomous")
public class A_Red_Auto_1 extends LinearOpMode {

    //PART DECLARATION
    private ColorSensor ColorSensor;
    //.

    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    ElapsedTime gyroTime = new ElapsedTime();
    boolean A = true;



    @Override
    public void runOpMode() throws InterruptedException {

        //PART INIT
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        //.

        //Vuforia Init:
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);//leave parameters blank to not display on phone
        parameters.vuforiaLicenseKey = "AV6yugj/////AAAAGTOHqL6RDUmVgo0jZreKdLgqXGK+wd8vPtaDUOeepBzJahj4mF1oh/urYHvdw40evwj26RACNoqaxJWb1nS9RCaPjg25pDCZJJgFNSmtPHBU+f5AN1Y7ZJbJjNOAg8XvkX99ixa/gD/9HO9Es11cXjv0GkJof4M3ynaDqrh8S18dT5XT8QReygM64YyWkrsqjWI5H7WqZkuBDCSfmq0MVQiQrF9LChxd3/dTjChBJvcD8Rud19FEvu5IXq/Xem4KpPtuWDQAH0gWKJve8AzlcQLomY2nKtjbpcrZLpVjwtoo+C8NCCL5ng14uRCI8eriEg3OFD6v4ZNSZmbZIcUqAuX4YtFQG3t1RL0MT+3fWsBf";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;//change to front to switch camera used
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        //.


        waitForStart();

        //VUFORIA SCAN
        relicTrackables.activate();
        //all of this code is in COnceptVuMarkIdentification.java
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        while (vuMark == RelicRecoveryVuMark.UNKNOWN ) {//While it cant see vuMark
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            telemetry.addData("VuMark", "not visible");
            telemetry.update();
            //if (ranit = true){
                //break;
            //}
            idle();
        }
        //break should put us here
        telemetry.addData("VuMark", "%s visible", vuMark);
        telemetry.update();

        KnockOffJewl();
        DriveToSafeZone();//general area

        if (vuMark == RelicRecoveryVuMark.LEFT){
            // move to left
            PlaceGlyph();
        }
        if (vuMark == RelicRecoveryVuMark.CENTER){
            //move to center
            PlaceGlyph();
        }
        if (vuMark == RelicRecoveryVuMark.RIGHT){
            //move to right
            PlaceGlyph();
        }
        else{
            //move to center or what ever is easiest
            PlaceGlyph();
        }

    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
    public void KnockOffJewl(){
        //drive off base
        if (ColorSensor.red()> ColorSensor.blue()){// in this demo, we are red
            //drive forwrd then back, then on base
        }
        if (ColorSensor.red()< ColorSensor.blue()){
           //drive back then forward then back on base
        }
        else {// in this situation, we are unsure of the color of the ball, so we just drive onto the base
            // drive back onto base
        }
    }
    public void DriveToSafeZone(){
        // general area, not to specific LEFT RIGHT OR MIDDLE
    }
    public void PlaceGlyph(){
        /**this place glyph has to be a piece of code in which the robot is
         * perfectly prepositioned in front of the right collumn (vuMark variable
         * cant be identified in an independent method)
         */
    }
}

