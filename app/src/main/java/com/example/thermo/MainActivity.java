package com.example.thermo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    private SensorManager manager;
    private Sensor tempSensor;
    private Thermometre t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        t = new Thermometre(this);
        setContentView(t);

        this.manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        this.tempSensor = this  .manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        this.manager.registerListener(this,this.tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        t.setTemp(sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}