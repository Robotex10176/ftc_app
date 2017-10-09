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

        if (vuMark == RelicRecoveryVuMark.RIGHT){
            dumbDrive (33, 0.1, 0.1);//Drive Forward 33 in
            Turn(-90, -0.1, 0.1);
            dumbDrive (26.5, 0.1, 0.1);
            PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.CENTER){
            Drive (39.5, 0.1, 0.1);//Drive Forward 39.5 in
            Turn(-90, -0.1, 0.1);
            Drive (26.5, 0.1, 0.1);
            PlaceGlyph();
        } else if (vuMark == RelicRecoveryVuMark.LEFT){//use else if construct to "dasiychain" ifs together
            Drive (48, 0.1, 0.1);//Drive Forward 48 in
            Turn(-90, -0.1, 0.1);
            Drive (26.5, 0.1, 0.1);
            PlaceGlyph();
        }
        else{
            Drive (39.5, 0.1, 0.1);//Drive Forward to one of the columns
            Turn(-90, -0.1, 0.1);
            Drive (26.5, 0.1, 0.1);
            PlaceGlyph();
        }

    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
    public void KnockOffJewl(boolean red){
        Sensing();
        if ((red) && (robot.ColorSensor.red()>robot.ColorSensor.blue())){
            SeeOurColor();
        }
        if ((red) && (robot.ColorSensor.red()<robot.ColorSensor.blue())){
            DontSeeOurColor();
        }
        if ((!red) && (robot.ColorSensor.red()>robot.ColorSensor.blue())){
            DontSeeOurColor();
        }
        if ((!red) && (robot.ColorSensor.red()<robot.ColorSensor.blue())){
            SeeOurColor();
        }
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
    public void Turn(double Angle, double RightPower, double LeftPower){
        //code to turn untill an angle ex 0, 90, -90
        float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        if (zAngle != Angle){
            robot.leftDrive.setPower(LeftPower);
            robot.rightDrive.setPower(RightPower);
        }
    }
    public void Drive(double DesiredDistance, double RightPower, double LeftPower){
        double dg10incrs = 0.01;
        double dg22incrs = 0.05;
        double dg45incrs = 0.1;
        float DesiredAngle = 0;
        int newLeftTarget;
        int newRightTarget;
        float displacement;
        final double COUNTS_PER_MOTOR_REV = 1440;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
        final double WHEEL_DIAMETER_INCHES = 3.8125;     // For figuring circumference
        final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
        if (opModeIsActive()) {
            newLeftTarget = robot.leftDrive.getCurrentPosition() + (int) (DesiredDistance * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int) (DesiredDistance * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftDrive.setPower(Math.abs(LeftPower));
            robot.rightDrive.setPower(Math.abs(RightPower));
            while (opModeIsActive() && (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {
                float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
                if (zAngle > DesiredAngle){
                    LeftPower = LeftPower + ((zAngle - DesiredAngle)/90) * 0.05;
                    RightPower = RightPower - ((zAngle - DesiredAngle)/90) * 0.05;
                } else {
                    LeftPower = LeftPower - (-1*(zAngle - DesiredAngle)/90) * 0.05;
                    RightPower = RightPower + (-1*(zAngle - DesiredAngle)/90) * 0.05;
                }
                robot.leftDrive.setPower(Math.abs(LeftPower));
                robot.rightDrive.setPower(Math.abs(RightPower));
                idle();
            }
            float zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            if (zAngle != DesiredAngle){
                robot.leftDrive.setPower(-0.05);
                robot.leftDrive.setPower(0.05);
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
    public void dumbDrive (double DesiredDistance, double RightPower, double LeftPower){
        int newLeftTarget;
        int newRightTarget;
        final double COUNTS_PER_MOTOR_REV = 1440;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
        final double WHEEL_DIAMETER_INCHES = 3.8125;     // For figuring circumference
        final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
        if (opModeIsActive()) {
            newLeftTarget = robot.leftDrive.getCurrentPosition() + (int) (DesiredDistance * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int) (DesiredDistance * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftDrive.setPower(Math.abs(LeftPower));
            robot.rightDrive.setPower(Math.abs(RightPower));
            while (opModeIsActive() && (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {
// DO NOTHING BECUZ THIS IS DUMB

            }
        }
    }
}

