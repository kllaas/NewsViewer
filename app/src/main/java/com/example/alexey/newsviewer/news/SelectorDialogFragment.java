package com.example.alexey.newsviewer.news;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.example.alexey.newsviewer.R;

import java.util.Arrays;


/**
 * Created by Alexey on 13.11.2016.
 */

public class SelectorDialogFragment extends DialogFragment {

    private int position;

    public static SelectorDialogFragment newInstance(int position) {
        SelectorDialogFragment dialogFragment = new SelectorDialogFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        dialogFragment.setArguments(args);

        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        position = getArguments().getInt("position");

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
        builderSingle.setTitle("Select color:");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.select_dialog_item);

        arrayAdapter.addAll(Arrays.asList(getContext().getResources().getStringArray(R.array.spinner_states)));


        builderSingle.setNegativeButton(android.R.string.cancel, (DialogInterface dialog, int which) -> {
            dialog.dismiss();
        });

        builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
            sendBackResult(position, which);
            dialog.cancel();
        });

        return builderSingle.create();
    }

    public void sendBackResult(int position, int selection) {
        SelectListener listener = (NewsActivity) getActivity();
        listener.onSelected(position, selection);
        dismiss();
    }

    public interface SelectListener {
        void onSelected(int position, int selection);
    }

}