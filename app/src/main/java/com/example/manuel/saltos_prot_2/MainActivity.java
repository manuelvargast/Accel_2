package com.example.manuel.saltos_prot_2;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

//1) IMPLEMENTAR SENSOREVENTLISTENER
public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private Sensor acelerometro;
    private SensorManager smg;
    private TextView ejeX;
    private TextView ejeY;
    private TextView ejeZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2) INSTANCIAR COMPONENTES DE XML
        ejeX = (TextView) findViewById(R.id.txtEjeX);
        ejeY = (TextView) findViewById(R.id.txtEjeY);
        ejeZ = (TextView) findViewById(R.id.txtEjeZ);
    }

    //3) SOBREESCRIBIR MÉTODOS DE ACTIVITY
    protected void onResume(){
        super.onResume();

        smg = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = smg.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(!(acelerometro == null)){
            smg.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    protected void onPause(){

        smg = (SensorManager) getSystemService(SENSOR_SERVICE);
        smg.unregisterListener(this, acelerometro);

        super.onPause();
    }

    protected void onStop(){

        smg = (SensorManager) getSystemService(SENSOR_SERVICE);
        smg.unregisterListener(this, acelerometro);

        super.onStop();
    }


    //4) SOBREESCRIBIR METODO DE INTERFAZ
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x, y, z;

        x = sensorEvent.values[0];
        y = sensorEvent.values[1];
        z = sensorEvent.values[2];

        ejeX.setText("X = " + x);
        ejeY.setText("Y = " + y);
        ejeZ.setText("Z = " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
