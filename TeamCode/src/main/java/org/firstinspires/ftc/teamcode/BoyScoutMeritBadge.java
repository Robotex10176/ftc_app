package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Intended for use of Troop 1537, Plymouth MI
 * Have them call "A_Main" class
 * They are only allowed to use "DriveNoCorrection" and "SmartTurn . . . "
 */

public class BoyScoutMeritBadge extends LinearOpMode{
    BSA_Methods methods = new BSA_Methods();
    @Override
    public void runOpMode() throws InterruptedException {
        methods.init(hardwareMap);
        waitForStart();
    }
}
