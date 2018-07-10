package com.premier.fred.fredpremierandroid;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ExempleNotifsActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private Button bt_immediat;
    private Button bt_delayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemple_notifs);

        bt_immediat = findViewById(R.id.bt_immediat);
        bt_delayed = findViewById(R.id.bt_delayed);

        bt_immediat.setOnClickListener(this);
        bt_delayed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == bt_immediat) {
            NotificationUtils.createInstantNotification(this, "Notification immédiate !");
        } else if (v == bt_delayed) {
//            NotificationUtils.delayedNotification(this, "Notif délayée !", 5000);

            Calendar calendar = Calendar.getInstance();
            //(Context, callback, heure, minute, 24h format)
            //Pour le callback -> Alt+entree -> implémente méthode -> Génère la méthode onTimeSet
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar chosenTime = Calendar.getInstance();
        chosenTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        chosenTime.set(Calendar.MINUTE, minute);

        Calendar now = Calendar.getInstance();

        long delay = chosenTime.getTimeInMillis() - now.getTimeInMillis();

        if (delay < 1000) {
            Toast.makeText(this, "L'heure choisie est antérieure au moment présent !", Toast.LENGTH_SHORT).show();
        } else {
            String stringHeures;
            String stringMinutes;

            if (hourOfDay < 10) {
                stringHeures = "0" + String.valueOf(hourOfDay);
            } else {
                stringHeures = String.valueOf(hourOfDay);
            }
            if (minute < 10) {
                stringMinutes = "0" + String.valueOf(minute);
            } else {
                stringMinutes = String.valueOf(minute);
            }
            NotificationUtils.delayedNotification(this, "Cette notif était prévue à : " + stringHeures + ":" + stringMinutes, delay);
        }
    }
}
