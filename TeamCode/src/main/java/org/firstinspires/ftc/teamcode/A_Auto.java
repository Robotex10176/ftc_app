package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Eric D'Urso on 10/24/2017.
 */
@Autonomous (name = "Auto")
public class A_Auto extends LinearOpMode {
    MAIN main = new MAIN();
    VuforiaLocalizer vuforia;
    boolean PROGRAMSELECTED = false;
    int PROGRAM = 0;
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
        telemetry.addLine("SELECT AUTONOMOUS PROGRAM");
        telemetry.addLine("B is Red Auto Bottom");
        telemetry.addLine("Y is Red Auto Top");
        telemetry.addLine("A is Blue Auto Bottom");
        telemetry.addLine("X is Blue Auto Top");
        telemetry.update();

        while (!PROGRAMSELECTED){
            if(gamepad2.b){// program 1
                PROGRAM = 1;
                PROGRAMSELECTED = true;
            } else if (gamepad2.y){ //program 2
                PROGRAM = 2;
                PROGRAMSELECTED = true;
            } else if (gamepad2.a){// program 3
                PROGRAM = 3;
                PROGRAMSELECTED = true;
            } else if (gamepad2.x){//program 4
                PROGRAM = 4;
                PROGRAMSELECTED = true;
            } else {

            }
        }

        telemetry.addLine("Press Play to start");
        telemetry.update();
        waitForStart();// CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW  CODE BELOW
        if (PROGRAM == 1){ //RED AUTO BOTTOM



            //CODE BELOW




        } else if (PROGRAM == 2){//RED AUTO TOP



            //CODE BELOW





        } else if (PROGRAM == 3){//BLUE AUTO BOTTOM



            //CODE BELOW





        } else if (PROGRAM == 4){//BLUE AUTO TOP



            //CODE BELOW





        } else {

        }
    }// METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS  METHODS AFTER THIS

}


