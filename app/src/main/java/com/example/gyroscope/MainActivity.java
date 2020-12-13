package com.example.gyroscope;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager mSensorManager;
    TextView textView;
    float sensorX, sensorY, sensorZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView = findViewById(R.id.text_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor gyro = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch(sensorEvent.sensor.getType()){
            case Sensor.TYPE_GYROSCOPE:
                sensorX = sensorEvent.values[0];
                sensorY = sensorEvent.values[1];
                sensorZ = sensorEvent.values[2];

                String acc = "Gyro\n"
                        + " X: " + sensorX + "\n"
                        + " Y: " + sensorY + "\n"
                        + " Z: " + sensorZ;
                textView.setText(acc);
                Log.d("test", "onSensorChanged: \n" + "X:" + sensorX + "\n"+ "Y:" + sensorY + "\n"+ "Z:" + sensorZ + "\n");
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}