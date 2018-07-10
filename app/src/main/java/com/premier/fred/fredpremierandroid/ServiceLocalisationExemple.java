package com.premier.fred.fredpremierandroid;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class ServiceLocalisationExemple extends Service implements LocationListener {

    LocationManager locationMgr;

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Service onCreate", Toast.LENGTH_SHORT).show();

        //Si on a pas la permission on ne s’abonne pas
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //Minimum (et non égale, c’est Android qui gère) 5 secondes et 200m de difference.
        //Le this ici représente l’interface « LocationListener » à implémenter MANUELLEMENT
        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationMgr.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
            locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, this);
        if (locationMgr.getAllProviders().contains(LocationManager.GPS_PROVIDER))
            locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service onStartCommand", Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service onDestroy", Toast.LENGTH_SHORT).show();
        locationMgr.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        MonApplication.getBus().post(location);

        Toast.makeText(this, "Longitude : " + longitude + " \n Latitude : " + latitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
