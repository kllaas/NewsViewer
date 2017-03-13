package com.example.alexey.newsviewer.services;

import android.app.IntentService;
import android.content.Intent;

import com.example.alexey.newsviewer.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexey on 12/03/17.
 */

public class DialogService extends IntentService {

    public DialogService() {
        super("DialogService");
    }

    @Override
    protected void onHandleIntent(Intent i) {
        Intent intent = new Intent(BaseActivity.BROADCAST_ACTION);

        SimpleDateFormat dateFormat = new SimpleDateFormat("'Текущее время - 'HH:mm");
        String currentDate = dateFormat.format(new Date());

        intent.putExtra(BaseActivity.TIME, currentDate);

        sendBroadcast(intent);

    }

}
