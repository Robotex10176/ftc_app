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
@Autonomous (name = "A_Red_Auto_1", group = "Red Autonomous")
public class A_Red_Auto_1 extends LinearOpMode {

    //ROBOT CONFIGURE
    H_RobotHardware robot = new H_RobotHardware();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    //.

    @Override
    public void runOpMode() throws InterruptedException {

        //ROBOT INIT
        robot.init(hardwareMap);
        Rest();
        OpenClaw();
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
        //DriveToSafeZone();//general area

        if (vuMark == RelicRecoveryVuMark.LEFT){
            DriveForward(0.15, 0.15, 33, 60);
            Turn(-90, -0.15, 0.15);
            DriveForward(0.15, 0.15, 26.5, 60);
            PlaceGlyph();
        }
        if (vuMark == RelicRecoveryVuMark.CENTER){
            DriveForward(0.15, 0.15, 39.5, 60);
            Turn(-90, -0.15, 0.15);
            DriveForward(0.15, 0.15, 26.5, 60);
            PlaceGlyph();
        }
        if (vuMark == RelicRecoveryVuMark.RIGHT){
            DriveForward(0.15, 0.15, 48, 60);
            Turn(-90, -0.15, 0.15);
            DriveForward(0.15, 0.15, 26.5, 60);
            PlaceGlyph();
        }
        else{
            DriveForward(0.15, 0.15, 48, 60);
            Turn(-90, -0.15, 0.15);
            DriveForward(0.15, 0.15, 26.5, 60);
            PlaceGlyph();
        }

    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
    public void KnockOffJewl(boolean red){
        Sensing();
        if (red && robot.ColorSensor.red()>robot.ColorSensor.blue()){
            SeeOurColor();
        }
        if (red && robot.ColorSensor.red()<robot.ColorSensor.blue()){
            DontSeeOurColor();
        }
        if (!red && robot.ColorSensor.red()>robot.ColorSensor.blue()){
            DontSeeOurColor();
        }
        if (!red && robot.ColorSensor.red()<robot.ColorSensor.blue()){
            SeeOurColor();
        }
    }
    public void DriveToSafeZone(){
        // general area, not to specific LEFT RIGHT OR MIDDLE
        DriveForward(0.15, 0.15, 39, 60);//Right Start Power, Left Start Power, DesiredDistance(in), Timeout (secs)
        Turn(-90, 0.15, -0.15);//DesiredAngle, Right PWR, Left PWR
    }
    public void PlaceGlyph(){
        OpenClaw();
        /**this place glyph has to be a piece of code in which the robot is
         * perfectly prepositioned in front of the right collumn (vuMark variable
         * cant be identified in an independent method)
         */
    }
    public void Turn(double Angle, double RightPower, double LeftPower){
        //code to turn untill an angle ex 0, 90, -90
        float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        if (zAngle != Angle){
            robot.leftDrive.setPower(LeftPower);
            robot.rightDrive.setPower(RightPower);
        }
    }
    public void DriveForward(double RightPower, double LeftPower,
                             double DesiredDistance, double TimeoutS){
        float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        float DesiredAngle = zAngle;
        int newLeftTarget;
        int newRightTarget;

        final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // TETRIX MOTORS = 1440, andymark = 1120
        final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
        final double     WHEEL_DIAMETER_INCHES   = 3.8125 ;     // For figuring circumference
        final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);

        if (opModeIsActive()){
            newLeftTarget = robot.leftDrive.getCurrentPosition() + (int)(DesiredDistance * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int)(DesiredDistance * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            robot.timer.reset();
            robot.leftDrive.setPower(Math.abs(LeftPower));
            robot.rightDrive.setPower(Math.abs(RightPower));

            while (opModeIsActive() &&
                    (robot.timer.seconds() < TimeoutS) &&
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())){//___________________ESHWARS PARAMETER__SOMETHING LIKE WHILE MOTORS ARE BUSY____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
                zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC,
                        AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
                if(DesiredAngle < zAngle){
                    //Turn(DesiredAngle, -CommonPower, CommonPower);//common power can be changed
                    //TURN LEFT
                    RightPower = RightPower + 0.01;
                }
                if(DesiredAngle > zAngle){
                    //Turn(DesiredAngle, CommonPower, -CommonPower);//other way
                    //above might include encoder distance
                    //TURN RIGHT
                    LeftPower = LeftPower + 0.01;
                }
                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.leftDrive.getCurrentPosition(),
                        robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

        }

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
        float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        telemetry.addData("angle", "%s deg", formatFloat(zAngle));
        telemetry.update();
    }

    //.

    public void CloseClaw (){
        robot.RightClaw.setPosition(0.75);
        robot.LeftClaw.setPosition(0.50);
    }
    public void OpenClaw (){
        robot.RightClaw.setPosition(1);
        robot.LeftClaw.setPosition(0.25);
    }
    public void FlatClaw(){
        robot.RightClaw.setPosition(0.3);
        robot.LeftClaw.setPosition(0);
    }
    public void Rest (){
        robot.moveFlick.setPosition(0.5);
        sleep(1000);
        robot.flick.setPosition(0);
        sleep(1000);
    }
    public void Sensing () {
        robot.flick.setPosition(1);
        sleep(1000);
        robot.moveFlick.setPosition(0.5);
        sleep(1000);
    }
    public void DontSeeOurColor (){
        robot.moveFlick.setPosition(0.7);
        sleep(1000);
        robot.moveFlick.setPosition(0.5);
        sleep(1000);
    }
    public void SeeOurColor (){
        robot.moveFlick.setPosition(0);
        sleep(1000);
        robot.moveFlick.setPosition(0.5);
        sleep(1000);
    }
}

