package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Eric D'Urso on 10/14/2017.
 */
@Autonomous (name = "ALL CODE", group = "AAAAAAAAAAA")
public class AAA_MAIN_CODE extends LinearOpMode {
    H_RobotHardware robot = new H_RobotHardware();
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
    int Auto = 0;
    boolean knowsWhatCode = false;
    private float scaleController(float in){
        //return ( java.lang.Math.signum(in)*in*in);
        return (in*in*in);
    }
    boolean ranAuto = false;
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap); //True means this is an autonomous
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
        telemetry.addData(">", "SELECT CODE USING CONTROLLER");
        telemetry.update();

        while (!knowsWhatCode){
            if(gamepad2.x){
                //BLUE AUTO 2 GORS HERE
                Auto = 1;
                knowsWhatCode = true;
            } else if (gamepad2.y){
                // RED AUTO 2 GOES HERE
                Auto = 2;
                knowsWhatCode = true;
            } else if (gamepad2.a){
                // BLUE AUTO 1 GOES HERE
                Auto = 3;
                knowsWhatCode = true;
            } else if (gamepad2.b){
                //RED AUTO 1 GOES HERE
                Auto = 4;
                knowsWhatCode = true;
            } else if (gamepad2.right_bumper){
                //TELEOP GOES HERE
                Auto = 0;//means we aint doing auto
                knowsWhatCode = true;
            } else {
                //DO NOTHING
            }
        }

        waitForStart();

        while (!ranAuto) {
            if (Auto == 0) {
                telemetry.addData("AUTO =", Auto);
                telemetry.update();
                sleep(2000);
                ranAuto = true;


                //means skip auto, we dooin telly

            } else if (Auto == 1) {
                telemetry.addData("AUTO =", Auto);
                telemetry.update();
                sleep(2000);


                ranAuto = true;
//auto code goes in these
            } else if (Auto == 2) {
                telemetry.addData("AUTO =", Auto);
                telemetry.update();
                sleep(2000);


                ranAuto = true;
            } else if (Auto == 3) {
                telemetry.addData("AUTO =", Auto);
                telemetry.update();
                sleep(2000);


                ranAuto = true;
            } else if (Auto == 4) {
                telemetry.addData("AUTO =", Auto);
                telemetry.update();
                sleep(2000);


                ranAuto = true;
            } else {
                //should NEVER HAPPEN
            }
        }
        telemetry.addData("Ran Auto = ", ranAuto);
        telemetry.addLine("RUNNING TELEOP NOWWWWWWWW!!!!!!!!!!!!!!! . . . ");
        telemetry.update();
        if (ranAuto){
            while (opModeIsActive()){
                //TELEOP GOES HERE
            }
        }
    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
//METHODS BELOW
    public void KnockOffJewl(boolean red) {
        if (red) {
            moveArm(95, 0.1);
            sleep(1000);
            if ((robot.ColorSensor.red() > robot.ColorSensor.blue())) {
                SeeOurColor();
            } else if ((robot.ColorSensor.red() < robot.ColorSensor.blue())) {
                DontSeeOurColor();
            } else if ((robot.ColorSensor.red() > robot.ColorSensor.blue())) {
                DontSeeOurColor();
            } else if ((robot.ColorSensor.red() < robot.ColorSensor.blue())) {
                SeeOurColor();
            } else {

            }
            moveArm(-120, -0.1);
        } else if (!red) {
            moveArm(95, 0.1);
            sleep(1000);
            if ((robot.ColorSensor.red() < robot.ColorSensor.blue())) {
                SeeOurColor();
            } else if ((robot.ColorSensor.red() > robot.ColorSensor.blue())) {
                DontSeeOurColor();
            } else if ((robot.ColorSensor.red() < robot.ColorSensor.blue())) {
                DontSeeOurColor();
            } else if ((robot.ColorSensor.red() > robot.ColorSensor.blue())) {
                SeeOurColor();
            } else {

            }
            moveArm(-120, -0.1);
        } else {

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
    public void Turn(double Angle, double Power){
        //code to turn untill an angle ex 0, 90, -90
        float zAngle;
        zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        if (Angle < 0){
            while (zAngle != Angle){
                robot.leftDrive.setPower(Power);
                robot.rightDrive.setPower(-Power);
                telemetry.addData("Angle:", zAngle);
                telemetry.update();
                zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

            }
        } else if (Angle > 0){
            while (zAngle != Angle){
                robot.leftDrive.setPower(-Power);
                robot.rightDrive.setPower(Power);
                telemetry.addData("Angle:", zAngle);
                telemetry.update();
                zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

            }
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
    public void moveArm (double Degrees, double Power){
        int newLeftTarget;
        final double COUNTS_PER_MOTOR_REV = 1120;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DEGREES = (Degrees*(COUNTS_PER_MOTOR_REV/360));//3.111111111111111111111111111 ticks per degree
        if (opModeIsActive()) {
            newLeftTarget = robot.arm.getCurrentPosition() + (int) (DEGREES);
            robot.arm.setTargetPosition(newLeftTarget);
            robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.arm.setPower(Math.abs(Power));
            while (opModeIsActive() && (robot.arm.isBusy())) {

            }
        }
    }
    public void SeeOurColor (){
        robot.moveFlick.setPosition(0);
        sleep(1000);
        robot.moveFlick.setPosition(0.5);
        sleep(1000);
    }
    public void DontSeeOurColor (){
        robot.moveFlick.setPosition(1);
        sleep(1000);
        robot.moveFlick.setPosition(0.5);
        sleep(1000);
    }
}
