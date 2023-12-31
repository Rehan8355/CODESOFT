package com.example.flashlightapplication;

import static com.example.flashlightapplication.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton toggleButton;
    boolean hasCameraFlash = false;
    boolean flashOn =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton =findViewById(R.id.toggleButton );

        hasCameraFlash=getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasCameraFlash){
                    if(flashOn){
                        flashOn=false;
                        toggleButton.setImageResource(drawable.off);
                        try {
                            flashLightOff();
                        } catch (CameraAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        flashOn=true;
                        toggleButton.setImageResource(drawable.on);
                        try {
                            flashLightOn();
                        } catch (CameraAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else{
                    Toast.makeText (MainActivity.this,  "No flash available on your device.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void flashLightOn() throws CameraAccessException {
        CameraManager cameraManager =(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        assert cameraManager !=null;
        String camneraId=cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(camneraId, true);
        Toast.makeText( MainActivity.this,  "FlashLight is On", Toast.LENGTH_SHORT).show();
    }
    private void flashLightOff() throws CameraAccessException {
        CameraManager cameraManager =(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        assert cameraManager !=null;
        String camneraId=cameraManager.getCameraIdList()[0];

        cameraManager.setTorchMode(camneraId, false);
        Toast.makeText( MainActivity.this,  "FlashLight is OFF", Toast.LENGTH_SHORT).show();
    }
}