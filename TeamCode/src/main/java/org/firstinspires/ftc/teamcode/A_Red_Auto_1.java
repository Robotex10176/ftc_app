package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
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


/**
 * Created by Eric D'Urso on 9/16/2017.
 */
@Autonomous (name = "A_Red_Auto_1", group = "Red Autonomous")
public class A_Red_Auto_1 extends LinearOpMode {

    //PART DECLARATION
    private ColorSensor ColorSensor;
    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;
    //.

    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    ElapsedTime timer = new ElapsedTime();
    boolean A = true;




    @Override
    public void runOpMode() throws InterruptedException {

        //PART INIT
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;
        //.

        //GYRO VARIABLE CONFIG
        boolean lastResetState = false;
        boolean curResetState  = false;
        float zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
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

        //CALIBRATE GYRO
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        modernRoboticsI2cGyro.calibrate();
        timer.reset();
        while (!isStopRequested() && modernRoboticsI2cGyro.isCalibrating())  {
            telemetry.addData("calibrating", "%s", Math.round(timer.seconds())%2==0 ? "|.." : "..|");
            telemetry.update();
            sleep(50);
        }
        telemetry.log().clear(); telemetry.log().add("Gyro Calibrated. Press Start.");
        telemetry.clear(); telemetry.update();
        //.


        waitForStart();

        //VUFORIA SCAN
        relicTrackables.activate();
        //all of this code is in COnceptVuMarkIdentification.java
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        timer.reset();
        while ( time < 10 || vuMark == RelicRecoveryVuMark.UNKNOWN ) {//While it cant see vuMark
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
            telemetry.addData("VuMark", "not visible");
            telemetry.update();
            idle();
        }
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
        DriveForward(0.15, 0.15);
        Turn(-90, 0.15, -0.15);//DesiredAngle, Right PWR, Left PWR
        DriveForward(0.15, 0.15);//Right Start Power, Left Start Power
    }
    public void PlaceGlyph(){
        /**this place glyph has to be a piece of code in which the robot is
         * perfectly prepositioned in front of the right collumn (vuMark variable
         * cant be identified in an independent method)
         */
    }
    public void Turn(double Angle, double RightPower, double LeftPower){
        //code to turn untill an angle ex 0, 90, -90
        float zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        if (zAngle != Angle){
//CODE THAT ACTUALLY MAKES IT TURN
        }
    }
    public void DriveForward(double RightPower, double LeftPower){
        float zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        float DesiredAngle = zAngle;
        while (){//___________________ESHWARS PARAMETER__SOMETHING LIKE WHILE MOTORS ARE BUSY____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
            zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            //MOST IMPORTANT code to drive forward for encoder distance.
            //set speed to common power, for eshwars parameter for distance
            if(DesiredAngle > zAngle){
                //Turn(DesiredAngle, -CommonPower, CommonPower);//common power can be changed
                //TURN LEFT
                RightPower = RightPower + 0.01;
            }
            if(DesiredAngle < zAngle){
                //Turn(DesiredAngle, CommonPower, -CommonPower);//other way
                //above might include encoder distance
                //TURN RIGHT
                LeftPower = LeftPower + 0.01;
            }
        }
        //set speed to commonpower
    }
    //GYRO STUFF
    String formatRaw(int rawValue) {
        return String.format("%d", rawValue);
    }
    String formatRate(float rate) {
        return String.format("%.3f", rate);
    }
    String formatFloat(float rate) {
        return String.format("%.3f", rate);
    }
    public void GyroAngleUpdate(){
        float zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        telemetry.addData("angle", "%s deg", formatFloat(zAngle));
        telemetry.update();
    }

    //.
}

