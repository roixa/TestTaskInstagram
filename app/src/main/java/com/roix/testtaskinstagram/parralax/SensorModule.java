package com.roix.testtaskinstagram.parralax;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.roix.testtaskinstagram.Constants;

/**
 * Created by u5 on 10/13/16.
 */
public class SensorModule implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor sensorAccel;
    private float[] valuesAccel = new float[3];


    public SensorModule(Context context){
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensorAccel, SensorManager.SENSOR_DELAY_GAME);

    }

    public float gx(){
        return valuesAccel[0];
    }

    public float gy(){
        return valuesAccel[1];
    }
    public float gz(){
        return valuesAccel[2];
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            for (int i = 0; i < 3; i++) {
                valuesAccel[i] = (event.values[i]+valuesAccel[i]*(Constants.BLUR-1))/Constants.BLUR;
            }
        }

    }




    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
