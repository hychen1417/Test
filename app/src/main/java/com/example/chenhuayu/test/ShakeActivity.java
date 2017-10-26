package com.example.chenhuayu.test;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class ShakeActivity extends Activity {

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = Math.abs(event.values[0]);
            float y = Math.abs(event.values[1]);
            float z = Math.abs(event.values[2]);
            if (x > 15 || y > 15 || z > 15){
                Toast.makeText(ShakeActivity.this,"摇一摇",Toast.LENGTH_LONG);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
