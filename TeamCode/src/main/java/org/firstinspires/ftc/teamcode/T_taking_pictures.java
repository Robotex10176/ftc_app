/**
 *

*package org.firstinspires.ftc.teamcode;
*import android.hardware.Camera;
*import android.hardware.camera2;
*import android.hardware.camera;
*import android.widget.FrameLayout;

*import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;


*public class T_taking_pictures {
//this is where I will do research on the photos for the jewel identifier thing
public Camera camera;
    private Camera openFrontFacingCamera() {
        int cameraId = -1;
        Camera cam = null;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                break;
            }
        }
        try {
            cam = Camera.open(cameraId);
        } catch (Exception e) {

        }
        return cam;
    }

    public void initPreview(final Camera camera, final CameraOp context, final Camera.PreviewCallback previewCallback) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.preview = new CameraPreview(FtcRobotControllerActivity.this, camera, previewCallback);
                FrameLayout previewLayout = (FrameLayout) findViewById(R.id.previewLayout);
                previewLayout.addView(context.preview);
            }
        });
    }
*}
*/
