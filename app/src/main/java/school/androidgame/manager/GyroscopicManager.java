package school.androidgame.manager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by tobi on 04.12.17.
 */

//TODO static class ????
public class GyroscopicManager implements SensorEventListener {

    private float[] outputAccelerometer;
    private float[] outputMagneticField;

    private float[] startOrientation;
    private float[] orientation;

    private SensorManager sensorManager;
    private Sensor sensorMagneticField;
    private Sensor sensorAccelerometer;

    public GyroscopicManager(Context context) {

        this.orientation = new float[3];
        this.startOrientation = null;

        this.sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);

        if (this.sensorManager != null) {

            this.sensorMagneticField = this.sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            this.sensorAccelerometer = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (this.sensorMagneticField != null && this.sensorAccelerometer != null) {

                this.sensorManager.registerListener(this, this.sensorMagneticField, SensorManager.SENSOR_DELAY_GAME);
                this.sensorManager.registerListener(this, this.sensorAccelerometer, SensorManager.SENSOR_DELAY_GAME);
            }
        }
    }

    public void unregisterListener() {
        this.sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

            this.outputMagneticField = event.values;

        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            this.outputAccelerometer = event.values;
        }

        if (outputMagneticField != null && outputAccelerometer != null) {

            float[] R = new float[9];
            float[] I = new float[9];

            if (SensorManager.getRotationMatrix(R, I, this.outputAccelerometer, this.outputMagneticField)) {

                SensorManager.getOrientation(R, orientation);

                if (this.startOrientation == null) {
                    this.startOrientation = new float[orientation.length];
                    System.arraycopy(this.orientation, 0, this.startOrientation, 0, this.orientation.length);
                }
            }
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public float[] getOrientation() {
        return this.orientation;
    }

    public float[] getStartOrientation() {
        return this.startOrientation;
    }
}
