package com.example.alexey.newsviewer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

                AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);

                builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
                builder.setTitle(R.string.title_date_dialog);

                View view = getLayoutInflater()
                        .inflate(R.layout.dialog_date, null);
                builder.setView(view);

                TextView tvDate = (TextView) view.findViewById(R.id.tv_text);
                ImageView imageView = (ImageView) view.findViewById(R.id.image_view);


                tvDate.setText(currentDate);

                Picasso.with(BaseActivity.this)
                        .load("https://assets.rbl.ms/4314213/980x.jpg")
                        .into(imageView);

                builder.create().show();
            }
        };


        IntentFilter filter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(mReceiver, filter);
    }

}
