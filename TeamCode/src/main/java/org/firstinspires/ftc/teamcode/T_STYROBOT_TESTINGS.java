package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Eric D'Urso on 9/17/2017.
 */
@Autonomous (name = "STYRO", group = "styro")
public class T_STYROBOT_TESTINGS extends LinearOpMode {

    //VUFORIA BUILD
    boolean ranVuforia = false;
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    //.

    private DcMotor andyMarkWithEncoder1;
    private DcMotor andyMarkWithEncoder2;
    private DcMotor tetrixWithEncoder;
    private DcMotor tetrix;
    private Servo S1;
    private Servo S2;
    private Servo S3;
    private Servo S360;
    private ColorSensor ColorSensor;
    //IDK Y there be two of dem, copied
    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;

    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        boolean lastResetState = false;
        boolean curResetState  = false;

        andyMarkWithEncoder1 = hardwareMap.dcMotor.get("AMWE1");
        andyMarkWithEncoder2 = hardwareMap.dcMotor.get("AMWE2");
        tetrixWithEncoder = hardwareMap.dcMotor.get("TWE");
        tetrix = hardwareMap.dcMotor.get("T");
        S1 = hardwareMap.servo.get("S1");
        S2 = hardwareMap.servo.get("S2");
        S3 = hardwareMap.servo.get("S3");
        S360 = hardwareMap.servo.get("S360");
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;

        calibrateGyro();

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

        waitForStart();//________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________-        relicTrackables.activate();

    }
     public void calibrateGyro(){

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
     }
}
