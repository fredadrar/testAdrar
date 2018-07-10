package com.premier.fred.fredpremierandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MonBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

//        if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
//            int result = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
//
//            if (result == WifiManager.WIFI_STATE_DISABLED) {
//                Toast.makeText(context, "DISABLED", Toast.LENGTH_SHORT).show();
//            } else if (result == WifiManager.WIFI_STATE_ENABLED) {
//                Toast.makeText(context, "ENABLED", Toast.LENGTH_SHORT).show();
//            } else if (result == WifiManager.WIFI_STATE_UNKNOWN) {
//                Toast.makeText(context, "UNKNOWN", Toast.LENGTH_SHORT).show();
//            }
//        } else
        if (intent.getAction().equals("android.intent.action.AIRPLANE_MODE")) {
            String textModeAvion;
            if (intent.getBooleanExtra("state", false)) {
                textModeAvion = "activé";
            } else {
                textModeAvion = "désactivé";
            }
            Toast.makeText(context, "Mode Avion " + textModeAvion, Toast.LENGTH_SHORT).show();
        }
    }
}
