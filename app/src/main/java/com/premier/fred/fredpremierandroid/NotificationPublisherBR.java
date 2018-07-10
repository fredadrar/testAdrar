package com.premier.fred.fredpremierandroid;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationPublisherBR extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //On récupère la notification reçue
        Notification notifDelayed = intent.getParcelableExtra("MaCle");

        //on l'affiche
        NotificationManagerCompat ncm = NotificationManagerCompat.from(context);
        ncm.notify(29, notifDelayed);

    }
}
