package com.example.alexey.newsviewer.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.alexey.newsviewer.services.DialogService;

/**
 * Created by alexey on 12/03/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent serviceIntent = new Intent(context, DialogService.class);

        context.startService(serviceIntent);
    }
}