package com.premier.fred.fredpremierandroid;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final int ALERTDIALOG_ID = 100;
    private static final int TIMEPICKER_ID = 101;
    private static final int DATEPICKER_ID = 102;
    private static final int SERVICE_ID = 103;
    private static final int SERVICE_REQ_CODE = 25;
    private static final int NOTIFS_ID = 104;
    private static final int RECYCLERVIEW_ID = 105;
    private static final int WEBVIEW_ID = 106;
    private static final int TCHAT_ID = 107;

    private Button bt_retour;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("seconde activity");

        bt_retour = findViewById(R.id.bt_retour);

        bt_retour.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ALERTDIALOG_ID, 0, "Alert Dialog").setIcon(R.drawable.baseline_notification_important_black_48).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add(0, TIMEPICKER_ID, 0, "Time Picker").setIcon(R.drawable.baseline_schedule_black_48).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add(0, DATEPICKER_ID, 0, "Date Picker").setIcon(R.drawable.baseline_today_black_48).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add(0, SERVICE_ID, 0, "Service Exemple").setIcon(R.drawable.ic_backup_black_48dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add(0, NOTIFS_ID, 0, "Notifications Exemple").setIcon(R.drawable.ic_backup_black_48dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add(0, RECYCLERVIEW_ID, 0, "Recycler View Exemple").setIcon(R.drawable.ic_backup_black_48dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add(0, WEBVIEW_ID, 0, "Web View Exemple").setIcon(R.drawable.ic_backup_black_48dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add(0, TCHAT_ID, 0, "Tchat Client Exemple").setIcon(R.drawable.ic_backup_black_48dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ALERTDIALOG_ID:
                displayAlert();
                break;
            case TIMEPICKER_ID:
                pickTime();
                break;
            case DATEPICKER_ID:
                pickDate();
                break;
            case SERVICE_ID:
                serviceExemple();
                break;
            case NOTIFS_ID:
                notifsExemple();
                break;
            case RECYCLERVIEW_ID:
                rvExemple();
                break;
            case WEBVIEW_ID:
                webViewExemple();
                break;
            case TCHAT_ID:
                tchatClientExemple();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v == bt_retour) {
            SecondActivity.this.finish();
        }
    }
    private void tchatClientExemple() {
        Intent intent = new Intent(this, LoginTchatActivity.class);
        startActivity(intent);
    }

    private void webViewExemple() {
        Intent intent = new Intent(this, WebViewExempleActivity.class);
        startActivity(intent);
    }

    private void rvExemple() {
        Intent intent = new Intent(this, RecyclerViewExempleActivity.class);
        startActivity(intent);
    }

    public void notifsExemple() {
        Intent intent = new Intent(this, ExempleNotifsActivity.class);
        startActivity(intent);
    }

    public void serviceExemple() {
        //Etape 1 : Est ce qu'on a déjà la serviceExemple ?
        //Attention prendre le Manifest d’android.util
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //On a la permission
            Intent intent = new Intent(this, ExempleServiceActivity.class);
            startActivity(intent);
        } else {
            //Etape 2 : On demande la permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, SERVICE_REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Est ce que c'est la serviceExemple qu'on a demandé ?
        if (requestCode == SERVICE_REQ_CODE) {
            //On verifie la réponse
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //On a la permission
                serviceExemple();
            } else {
                //On n'a pas la permission
                Toast.makeText(SecondActivity.this, "Le service n'est pas accessible sans la localisation !", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void displayAlert() {
        //Préparation de la fenêtre
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //Message
        alertDialogBuilder.setMessage("Hello World Alert !");
        //titre
        alertDialogBuilder.setTitle("Mon AlertDialog");
        //bouton ok
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Affiche un toast apres le click sur le bouton ok
                        Toast.makeText(SecondActivity.this, "Click sur ok", Toast.LENGTH_SHORT).show();
                    }
                });
        alertDialogBuilder.setNegativeButton("Annuler",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SecondActivity.this, "Click sur Annuler", Toast.LENGTH_SHORT).show();
                    }
                });
        //Icone
        alertDialogBuilder.setIcon(R.drawable.ic_check_circle_black_48dp);
        //Afficher la fenêtre
        alertDialogBuilder.show();
    }

    public void pickTime() {

        Calendar calendar = Calendar.getInstance();

        //(Context, callback, heure, minute, 24h format)
        //Pour le callback -> Alt+entree -> implémente méthode -> Génère la méthode onTimeSet
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String stringHour = String.valueOf(hourOfDay);
        String stringMinute = String.valueOf(minute);

        if (hourOfDay < 10) {
            stringHour = "0" + stringHour;
        }
        if (minute < 10) {
            stringMinute = "0" + stringMinute;
        }
        Toast.makeText(SecondActivity.this, "Vous avez choisi " + stringHour + ":" + stringMinute, Toast.LENGTH_SHORT).show();
    }

    public void pickDate() {
        //Gestion de la date
        Calendar calendar = Calendar.getInstance();

        //Création de la fenêtre
        //Pour le callback -> Alt+entree -> implémente méthode -> Génère la méthode onDateSet
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        //Afficher la fenêtre
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String toastText;
        String stringMonth;
        String stringDay;

        month += 1;
        if (year == 0 || dayOfMonth == 0) {
            toastText = "Vous n'avez pas choisi de date";
        } else {
            if (month < 10) {
                stringMonth = "0" + String.valueOf(month);
            } else {
                stringMonth = String.valueOf(month);
            }
            if (dayOfMonth < 10) {
                stringDay = "0" + String.valueOf(dayOfMonth);
            } else {
                stringDay = String.valueOf(dayOfMonth);
            }
            toastText = "Vous avez choisi le " + stringDay + "/" + stringMonth + "/" + year;
        }
        Toast.makeText(SecondActivity.this, toastText, Toast.LENGTH_SHORT).show();
    }
}
