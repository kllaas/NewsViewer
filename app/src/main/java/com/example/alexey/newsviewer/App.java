package com.example.alexey.newsviewer;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.alexey.newsviewer.data.BBCApi;
import com.example.alexey.newsviewer.receivers.AlarmReceiver;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexey on 11/03/17.
 */

public class App extends Application {

    private static BBCApi bbcApi;

    public static BBCApi getApi() {
        return bbcApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BBCApi.Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bbcApi = retrofit.create(BBCApi.class);

        startAlarmReceiver();
    }

    private void startAlarmReceiver() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 2,
                pendingIntent);
    }
}
