package com.example.alexey.newsviewer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.alexey.newsviewer.dialogs.AlarmDialogFragment;

/**
 * Created by alexey on 12/03/17.
 */

public class BaseActivity extends AppCompatActivity {

    public final static String BROADCAST_ACTION = "com.example.alexey.newsviewer";
    public static final String TIME = "time";

    private BroadcastReceiver mReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initReceiver();
    }

    private void initReceiver() {
        mReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                String currentDate = intent.getStringExtra(TIME);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                DialogFragment prev = (DialogFragment) getSupportFragmentManager().findFragmentByTag("alarm");
                if (prev != null) {
                    prev.dismiss();
                    ft.remove(prev);
                }

                DialogFragment newFragment = AlarmDialogFragment.newInstance(currentDate);
                newFragment.show(ft, "alarm");
            }
        };


        IntentFilter filter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mReceiver);
    }
}
