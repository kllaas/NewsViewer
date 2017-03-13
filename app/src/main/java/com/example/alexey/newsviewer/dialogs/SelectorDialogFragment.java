package com.example.alexey.newsviewer.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.alexey.newsviewer.R;
import com.example.alexey.newsviewer.news.NewsActivity;


/**
 * Created by Alexey on 13.11.2016.
 */

public class SelectorDialogFragment extends DialogFragment {

    private String url;

    private View secondColor;

    private View thirdColor;

    public static SelectorDialogFragment newInstance(String url) {
        SelectorDialogFragment dialogFragment = new SelectorDialogFragment();

        Bundle args = new Bundle();
        args.putString("position", url);
        dialogFragment.setArguments(args);

        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        url = getArguments().getString("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select color:");

        builder.setNegativeButton(getResources().getString(R.string.action_clear), (dialog, which) -> handleClick(0));

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_select_color, null);
        builder.setView(view);

        initViews(view);

        return builder.create();
    }

    private void initViews(View view) {
        secondColor = view.findViewById(R.id.color_icon_second);
        thirdColor = view.findViewById(R.id.color_icon_third);

        secondColor.setOnClickListener(v -> handleClick(1));

        thirdColor.setOnClickListener(v -> handleClick(2));
    }

    private void handleClick(int selection) {
        getDialog().cancel();
        sendBackResult(url, selection);
    }

    public void sendBackResult(String url, int selection) {
        SelectListener listener = (NewsActivity) getActivity();
        listener.onSelected(url, selection);
        dismiss();
    }

    public interface SelectListener {
        void onSelected(String url, int selection);
    }

}