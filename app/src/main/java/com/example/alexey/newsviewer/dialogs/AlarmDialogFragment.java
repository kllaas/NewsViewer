package com.example.alexey.newsviewer.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexey.newsviewer.Constants;
import com.example.alexey.newsviewer.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Alexey on 13.11.2016.
 */

public class AlarmDialogFragment extends DialogFragment {

    public static AlarmDialogFragment newInstance(String time) {
        AlarmDialogFragment dialogFragment = new AlarmDialogFragment();

        Bundle args = new Bundle();
        args.putString("time", time);
        dialogFragment.setArguments(args);

        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String time = getArguments().getString("time");

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
        builder.setTitle(R.string.title_date_dialog);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
        builder.setView(view);

        TextView tvDate = (TextView) view.findViewById(R.id.tv_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);


        tvDate.setText(time);

        Picasso.with(getContext())
                .load(Constants.ALARM_DIALOG_IMAGE_URL)
                .into(imageView);

        return builder.create();
    }

}