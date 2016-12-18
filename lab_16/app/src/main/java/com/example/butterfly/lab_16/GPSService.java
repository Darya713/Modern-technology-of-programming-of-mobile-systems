package com.example.butterfly.lab_16;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class GPSService extends Service {

    private LocationListener listener;
    private LocationManager locationManager;
    private Timer timer;
    private TimerTask timerTask;
    private long interval = 3000;
    MyBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        timer = new Timer();

        schedule();

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent("location_update");
                i.putExtra("coordinates", String.format(Locale.US, "%.2f", location.getLongitude())
                        + " " + String.format(Locale.US, "%.2f", location.getLatitude()));
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null){
            //noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }
    }

    void schedule() {
        if (timerTask != null)
            timerTask.cancel();
        if (interval > 0) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d("SERVICE", String.valueOf(interval));
                    interval -= 1000;
                    if (interval < 0) {
                        stopSelf();
                        timerTask.cancel();
                    }
                }
            };
            timer.schedule(timerTask, 1000, interval);
        }
        else
            this.stopSelf();
    }



    class MyBinder extends Binder {
        GPSService getService() {
            return GPSService.this;
        }
    }
}