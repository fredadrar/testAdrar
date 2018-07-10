package com.premier.fred.fredpremierandroid;


import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class ExempleServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_start;
    private Button bt_stop;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_exemple);

        bt_start = findViewById(R.id.bt_start);
        bt_stop = findViewById(R.id.bt_stop);
        textView = findViewById(R.id.textView);

        bt_start.setOnClickListener(this);
        bt_stop.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MonApplication.getBus().register(this);
    }

    @Subscribe
    public void afficherLocMaisCeNomNeSertARien(Location location) {
        String texteCoords = "Longitude : " + location.getLongitude() + " \n Latitude : " + location.getLatitude();
        textView.setText(texteCoords);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MonApplication.getBus().unregister(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_start) {
            startService(new Intent(this, ServiceLocalisationExemple.class));
        } else if (v == bt_stop) {
            stopService(new Intent(this, ServiceLocalisationExemple.class));
        }
    }
}
