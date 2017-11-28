package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Eric D'Urso on 11/26/2017.
 */

@Autonomous(name = "GLYPH COLOR TEST")
@Disabled
class GLYPH_COLOR_TEST extends OpMode {
    A_Main robot = new A_Main();
    @Override
    public void init() {
        robot.init(hardwareMap, true);
    }

    @Override
    public void loop() {
        robot.MMS.setPosition(0);
        telemetry.addData("Servo = ", robot.MoveSensor.getPosition());
        telemetry.addData("Red Value = ", robot.GlyphSensor.red());
        telemetry.addData("Blue Value = ", robot.GlyphSensor.blue());
        telemetry.addData("Green Value = ", robot.GlyphSensor.green());
        telemetry.update();
    }
}




//NEW CLASS



@Autonomous(name="Concept: VuMark Id", group ="Concept")
@Disabled
class ConceptVuMarkIdentification extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    @Override public void runOpMode() {

        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        /*
         * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
         * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
         * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
         * web site at https://developer.vuforia.com/license-manager.
         *
         * Vuforia license keys are always 380 characters long, and look as if they contain mostly
         * random data. As an example, here is a example of a fragment of a valid key:
         *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
         * Once you've obtained a license key, copy the string from the Vuforia web site
         * and paste it in to your code onthe next line, between the double quotes.
         */
        parameters.vuforiaLicenseKey = "AV6yugj/////AAAAGTOHqL6RDUmVgo0jZreKdLgqXGK+wd8vPtaDUOeepBzJahj4mF1oh/\n" +
                "urYHvdw40evwj26RACNoqaxJWb1nS9RCaPjg25pDCZJJgFNSmtPHBU+f5AN1Y7ZJbJjNOAg8XvkX99ixa/\n" +
                "gD/9HO9Es11cXjv0GkJof4M3ynaDqrh8S18dT5XT8QReygM64YyWkrsqjWI5H7WqZkuBDCSfmq0MVQiQrF9LChxd3/\n" +
                "dTjChBJvcD8Rud19FEvu5IXq/Xem4KpPtuWDQAH0gWKJve8AzlcQLomY2nKtjbpcrZLpVjwtoo+\n" +
                "C8NCCL5ng14uRCI8eriEg3OFD6v4ZNSZmbZIcUqAuX4YtFQG3t1RL0MT+3fWsBf";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {

            /**
             * See if any of the instances of {@link relicTemplate} are currently visible.
             * {@link RelicRecoveryVuMark} is an enum which can have the following values:
             * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
             * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
             */
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                telemetry.addData("VuMark", "%s visible", vuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;
                }
            }
            else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();
        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}



//NEW CLASS



@Autonomous(name = "DRIVE NO GYRO")
class driveNoGyro extends LinearOpMode {
    A_Main robot = new A_Main();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, true);

        waitForStart();
        //public void DriveNoCorrection(double DesiredDistance, double RightPower, double LeftPower) {
        robot.DriveNoCorrection(1, 10, 10);
    }
}




//NEW CLASS






@Autonomous(name="Encoder_Test")
@Disabled
class Encoder_Test extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                robot.leftDrive.getCurrentPosition(),
                robot.rightDrive.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  48,  48, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(TURN_SPEED,   12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
        encoderDrive(DRIVE_SPEED, -24, -24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout

        robot.leftClaw.setPosition(1.0);            // S4: Stop and close the claw.
        robot.rightClaw.setPosition(0.0);
        sleep(1000);     // pause for servos to move

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(Math.abs(speed));
            robot.rightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.leftDrive.getCurrentPosition(),
                        robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}





//NEW CLASS




@Autonomous (name = "Encoder Usage Test", group = "Tests")
@Disabled
class Encoder_Usage extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    double power = 0.15;
    @Override
    public void runOpMode() throws InterruptedException {
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForStart();
        int TETRIX_TICKS_PER_REVOLUTION = 1440;
        int ANDYMARK_TICKS_PER_REVOLUTION = 1120;//1120:CIRCUMPHRENCE of wheel is ratio of ticks to distance traveled
        DriveForwardDistance(power, 12);
    }
    public void DriveForwardDistance(double power, int distance){
        motorRight.setTargetPosition(distance);
        motorLeft.setTargetPosition(distance);
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        DriveForward();
        while (motorLeft.isBusy() && motorRight.isBusy()){
            //exactly, do nothing
        }
        StopMotors ();
    }
    public void DriveForward (){
        motorLeft.setPower(power);
        motorRight.setPower(-power);//________________________________CHANGE NEGATIVE IF NEEDED__________________________!
    }
    public void StopMotors (){
        motorRight.setPower(0);
        motorLeft.setPower(0);
    }
}





//NEW CLASS






@Autonomous (name = "Glyph Test", group = "TEST")
@Disabled
class Glyph_Persision extends LinearOpMode {
    A_Main robot = new A_Main();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, true);
        robot.MMS.setPosition(0.6);
        waitForStart();
        robot.MMS.setPosition(0);
        double Direction = 0.01;
        double Ub = 0.80;
        double Lb = 0.20;
        double SetPos;
        double Pos;
        while (robot.GlyphSensor.red()<10){
            telemetry.addData("Sensing:", robot.GlyphSensor.red());
            telemetry.update();
            Pos = robot.MoveSensor.getPosition();
            SetPos = Pos + Direction;
            if (SetPos > Ub){
                Direction = -Direction;
                robot.DriveNoCorrection(0.4, 0.1, 0.1);
            } else if (SetPos < Lb){
                Direction = -Direction;
                robot.DriveNoCorrection(0.4, 0.1, 0.1);
            }
            SetPos = Pos + Direction;
            robot.MoveSensor.setPosition(SetPos);
            telemetry.addData("Servo = ", robot.MoveSensor.getPosition());
            telemetry.addData("Red Value = ", robot.GlyphSensor.red());
            telemetry.addData("Blue Value = ", robot.GlyphSensor.blue());
            telemetry.addData("Green Value = ", robot.GlyphSensor.green());
            telemetry.update();
            sleep(50);//100 is standard
        }
        double CurrentPos = robot.MoveSensor.getPosition();
        double Displacement = CurrentPos - 0.5;
        telemetry.addData("Servo = ", robot.MoveSensor.getPosition());
        telemetry.addData("Red Value = ", robot.GlyphSensor.red());
        telemetry.addData("Blue Value = ", robot.GlyphSensor.blue());
        telemetry.addData("Green Value = ", robot.GlyphSensor.green());
        telemetry.update();
        sleep(1000000000);

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
            robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightDrive.setPower(0);
            robot.leftDrive.setPower(0);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public double AngularSeparation (double a, double b){
        double rv;
        rv = Math.abs(a-b);
        if (rv >= 180){
            rv = 360 - rv;
        }
        return rv;
    }
    public void SmartTurnRight(double Angle, double Power){//turn right is clockwise
        //code to turn untill an angle ex 0, 90, -90
        double zAngle;
        double targetAngle;
        zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        //Set target direction in range -180 - 180;
        targetAngle = (zAngle - Angle + 180);
        while (targetAngle > 360){ targetAngle = targetAngle - 360; }
        while (targetAngle < 0){ targetAngle = targetAngle + 360; }
        targetAngle = targetAngle - 180;

        while (AngularSeparation(zAngle, targetAngle)> 2.0){
            robot.leftDrive.setPower(Power);
            robot.rightDrive.setPower(-Power);
            telemetry.addData("R Angle:", zAngle);
            telemetry.addData("P ", robot.leftDrive.getPower());
            telemetry.addData("Given Power ", Power);
            telemetry.update();
            zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            idle();
        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

    }
    public void SmartTurnLeft (double Angle, double Power){
        double zAngle;
        double targetAngle;
        zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        //Set target direction in range -180 - 180;
        targetAngle = (zAngle - Angle + 180);
        while (targetAngle > 360){ targetAngle = targetAngle - 360; }
        while (targetAngle < 0){ targetAngle = targetAngle + 360; }
        targetAngle = targetAngle - 180;

        while (AngularSeparation(zAngle, targetAngle)> 2.0){
            robot.leftDrive.setPower(-Power);
            robot.rightDrive.setPower(Power);
            telemetry.addData("L Angle:", zAngle);
            telemetry.addData("P ", robot.leftDrive.getPower());
            telemetry.addData("Given Power ", Power);
            telemetry.update();
            zAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            idle();
        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

    }
}






//NEW CLASS









@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Glyph Arm Test Teleop")
@Disabled
class Glyph_Arm_Test extends LinearOpMode {
    private DcMotor Lift;
    private Servo RightClaw;
    private Servo LeftClaw;
    private boolean Close = false;
    @Override
    public void runOpMode() throws InterruptedException {
        Lift = hardwareMap.dcMotor.get("Lift");
        RightClaw = hardwareMap.servo.get("RightClaw");
        LeftClaw = hardwareMap.servo.get("LeftClaw");
        FlatClaw();
        sleep(1000);
        waitForStart();
        while (opModeIsActive()){
            if (gamepad1.dpad_up){
                Lift.setPower(0.5);
            }
            if (gamepad1.dpad_down){
                Lift.setPower(-0.5);
            }
            if (gamepad1.left_bumper){
                Close = true;
            }
            if (Close){
                CloseClaw ();
            }
            if (!gamepad1.left_bumper){
                Close = false;
            }
            if (!Close){
                OpenClaw();
            }
        }
    }
    public void CloseClaw (){
        RightClaw.setPosition(0.75);
        LeftClaw.setPosition(0.50);
    }
    public void OpenClaw (){
        RightClaw.setPosition(1);
        LeftClaw.setPosition(0.25);
    }
    public void FlatClaw(){
        RightClaw.setPosition(0.3);
        LeftClaw.setPosition(0);
    }
}





//NEW CLASS






@Autonomous (name = "JEWL TEST")
@Disabled
class Jewl_Test extends LinearOpMode {
    private DcMotor arm;
    private Servo moveFlick;
    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
        arm = hardwareMap.dcMotor.get("Arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setPower(0);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        moveFlick = hardwareMap.servo.get("Jewel");
        moveFlick.setPosition(0.5);
        waitForStart();
        moveArm(95, 0.1);
        sleep(1000);
        if ((ColorSensor.red()>ColorSensor.blue())){
            SeeOurColor();
        } else if ((ColorSensor.red()<ColorSensor.blue())){
            DontSeeOurColor();
        } else if ((ColorSensor.red()>ColorSensor.blue())){
            DontSeeOurColor();
        } else if ((ColorSensor.red()<ColorSensor.blue())){
            SeeOurColor();
        } else {

        }
        moveArm(-120, -0.1);
    }
    public void moveArm (double Degrees, double Power){
        int newLeftTarget;
        final double COUNTS_PER_MOTOR_REV = 1120;    // TETRIX MOTORS = 1440, andymark = 1120
        final double DEGREES = (Degrees*(COUNTS_PER_MOTOR_REV/360));//3.111111111111111111111111111 ticks per degree
        if (opModeIsActive()) {
            newLeftTarget = arm.getCurrentPosition() + (int) (DEGREES);
            arm.setTargetPosition(newLeftTarget);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(Math.abs(Power));
            while (opModeIsActive() && (arm.isBusy())) {
// DO NOTHING BECUZ THIS IS DUMB

            }
        }
    }
    public void SeeOurColor (){
        moveFlick.setPosition(0);
        sleep(1000);
        moveFlick.setPosition(0.5);
        sleep(1000);
    }
    public void DontSeeOurColor (){
        moveFlick.setPosition(1);
        sleep(1000);
        moveFlick.setPosition(0.5);
        sleep(1000);
    }
}







//NEW CLASS











@TeleOp(name = "COLOR_SENSOR_VALUE_TEST", group = "SENSOR_VALUE_TESTS")
@Disabled
class COLOR_SENSOR_VALUE_TEST extends OpMode {
    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;

    @Override
    public void init() {
        ColorSensor = hardwareMap.colorSensor.get("ColorSensor");
    }

    @Override
    public void loop() {

        ColorSensor.enableLed(true);
        telemetry.addData("red", ColorSensor.red());
        telemetry.addData("green", ColorSensor.green());
        telemetry.addData("blue", ColorSensor.blue());
        telemetry.addData("rgb", ColorSensor.argb());//hue?
        telemetry.addData("ALPHA?", ColorSensor.alpha());//alpha = brightness


    }
}




//NEW CLASS






@TeleOp(name = "Sensor: MR Gyro", group = "Sensor")
@Disabled
class GYRO_VALUE_TEST extends LinearOpMode {

    /** In this sample, for illustration purposes we use two interfaces on the one gyro object.
     * That's likely atypical: you'll probably use one or the other in any given situation,
     * depending on what you're trying to do. {@link IntegratingGyroscope} (and it's base interface,
     * {@link Gyroscope}) are common interfaces supported by possibly several different gyro
     * implementations. {@link ModernRoboticsI2cGyro}, by contrast, provides functionality that
     * is unique to the Modern Robotics gyro sensor.
     */
    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;

    // A timer helps provide feedback while calibration is taking place
    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {

        boolean lastResetState = false;
        boolean curResetState  = false;

        // Get a reference to a Modern Robotics gyro object. We use several interfaces
        // on this object to illustrate which interfaces support which functionality.
        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;
        // If you're only interested int the IntegratingGyroscope interface, the following will suffice.
        // gyro = hardwareMap.get(IntegratingGyroscope.class, "gyro");
        // A similar approach will work for the Gyroscope interface, if that's all you need.

        // Start calibrating the gyro. This takes a few seconds and is worth performing
        // during the initialization phase at the start of each opMode.
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        modernRoboticsI2cGyro.calibrate();

        // Wait until the gyro calibration is complete
        timer.reset();
        while (!isStopRequested() && modernRoboticsI2cGyro.isCalibrating())  {
            telemetry.addData("calibrating", "%s", Math.round(timer.seconds())%2==0 ? "|.." : "..|");
            telemetry.update();
            sleep(50);
        }

        telemetry.log().clear(); telemetry.log().add("Gyro Calibrated. Press Start.");
        telemetry.clear(); telemetry.update();

        // Wait for the start button to be pressed
        waitForStart();
        telemetry.log().clear();
        telemetry.log().add("Press A & B to reset heading");

        // Loop until we're asked to stop
        while (opModeIsActive())  {

            // If the A and B buttons are pressed just now, reset Z heading.
            curResetState = (gamepad1.a && gamepad1.b);
            if (curResetState && !lastResetState) {
                modernRoboticsI2cGyro.resetZAxisIntegrator();
            }
            lastResetState = curResetState;

            // The raw() methods report the angular rate of change about each of the
            // three axes directly as reported by the underlying sensor IC.
            int rawX = modernRoboticsI2cGyro.rawX();
            int rawY = modernRoboticsI2cGyro.rawY();
            int rawZ = modernRoboticsI2cGyro.rawZ();
            int heading = modernRoboticsI2cGyro.getHeading();
            int integratedZ = modernRoboticsI2cGyro.getIntegratedZValue();

            // Read dimensionalized data from the gyro. This gyro can report angular velocities
            // about all three axes. Additionally, it internally integrates the Z axis to
            // be able to report an absolute angular Z orientation.
            AngularVelocity rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
            float zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

            // Read administrative information from the gyro
            int zAxisOffset = modernRoboticsI2cGyro.getZAxisOffset();
            int zAxisScalingCoefficient = modernRoboticsI2cGyro.getZAxisScalingCoefficient();

            telemetry.addLine()
                    .addData("dx", formatRate(rates.xRotationRate))
                    .addData("dy", formatRate(rates.yRotationRate))
                    .addData("dz", "%s deg/s", formatRate(rates.zRotationRate));
            telemetry.addData("angle", "%s deg", formatFloat(zAngle));
            telemetry.addData("heading", "%3d deg", heading);
            telemetry.addData("integrated Z", "%3d", integratedZ);
            telemetry.addLine()
                    .addData("rawX", formatRaw(rawX))
                    .addData("rawY", formatRaw(rawY))
                    .addData("rawZ", formatRaw(rawZ));
            telemetry.addLine().addData("z offset", zAxisOffset).addData("z coeff", zAxisScalingCoefficient);
            telemetry.update();
        }
    }

    String formatRaw(int rawValue) {
        return String.format("%d", rawValue);
    }

    String formatRate(float rate) {
        return String.format("%.3f", rate);
    }

    String formatFloat(float rate) {
        return String.format("%.3f", rate);
    }

}




//NEW CLASS








@TeleOp(name = "OPTICAL_DISTANCE_SENSOR_VALUE_TEST", group = "SENSOR_VALUE_TESTS")
@Disabled
class ODS_VALUE_TEST extends LinearOpMode {

    OpticalDistanceSensor odsSensor;  // Hardware Device Object


    @Override
    public void runOpMode() {

        // get a reference to our Light Sensor object.
        odsSensor = hardwareMap.opticalDistanceSensor.get("ODS");


        // wait for the start button to be pressed.
        waitForStart();

        // while the op mode is active, loop and read the light levels.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {


            // send the info back to driver station using telemetry function.
            telemetry.addData("Raw",    odsSensor.getRawLightDetected());
            telemetry.addData("Normal", odsSensor.getLightDetected());

            telemetry.update();
        }
    }
}



//NEW CLASS





@Autonomous (name = "STYRO", group = "styro")
@Disabled
class STYROBOT_TESTINGS extends LinearOpMode {

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
    private com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;
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




//NEW CLASS





@TeleOp (name = "Trike Drive")
@Disabled
class Trike_Bot_Drive extends OpMode {
    private DcMotor A;
    private DcMotor B;

    @Override
    public void init() {
        A = hardwareMap.dcMotor.get("A");
        B = hardwareMap.dcMotor.get("B");
    }

    @Override
    public void loop() {
        B.setPower(-gamepad1.right_stick_y);
        A.setPower(gamepad1.left_stick_y);
    }
}




//NEW CLASS



@TeleOp (name = "W_PRACTICE_TELEOP_DRIVE", group = "PRACTICE")
@Disabled
class PRACTICE_TELEOP_DRIVE extends LinearOpMode
{
    private DcMotor motorLeft;
    private DcMotor motorRight;

    @Override
    public void runOpMode () throws InterruptedException
    {
        motorLeft = hardwareMap.dcMotor.get ("Left Motor");
        motorRight = hardwareMap.dcMotor.get ("Right Motor");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart ();

        while (opModeIsActive())
        {

            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            idle();
        }
    }
}




//NEW CLASS





@TeleOp(name="W_PRACTICE_TELEOP_SERVO", group="PRACTICE")
@Disabled
class PRACTICE_TELEOP_SERVO extends OpMode {
    // DcMotor Motor1; //left stick
    //DcMotor Motor2; //right stick

    Servo Servo1; // "A" Button

    @Override
    public void loop() {
        // Motor1.setPower(gamepad1.left_stick_y);
        // Motor2.setPower(-gamepad1.right_stick_y);

        if (gamepad1.a) {
            Servo1.setPosition(0.65); //servo 50 ticks
        } else {
            Servo1.setPosition(0.002); //reset servo(0 ticks)
        }
    }

    @Override
    public void init() {
        //Motor1 = hardwareMap.dcMotor.get("Left Motor");
        //Motor2 = hardwareMap.dcMotor.get("Right Motor");
        Servo1 = hardwareMap.servo.get("s1");

    }
    //This is all the code. The Video just goes over other ways to control the motors.

}

