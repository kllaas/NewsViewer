package com.example.alexey.newsviewer.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.alexey.newsviewer.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexey on 12/03/17.
 */

public class DialogService extends Service {

    public int onStartCommand(Intent i, int flags, int startId) {
        Intent intent = new Intent(BaseActivity.BROADCAST_ACTION);

        SimpleDateFormat dateFormat = new SimpleDateFormat("'Текущее время - 'HH:mm");
        String currentDate = dateFormat.format(new Date());

        intent.putExtra(BaseActivity.TIME, currentDate);

        sendBroadcast(intent);

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
