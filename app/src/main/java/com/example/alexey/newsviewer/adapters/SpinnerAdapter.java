package com.example.alexey.newsviewer.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alexey.newsviewer.R;

/**
 * Created by alexey on 12/03/17.
 */

public class SpinnerAdapter extends ArrayAdapter {

    private String[] spinnerValues;

    private Context mContext;

    public SpinnerAdapter(Context ctx, int txtViewResourceId,
                          String[] spinnerValues) {
        super(ctx, txtViewResourceId, spinnerValues);
        this.spinnerValues = spinnerValues;
        this.mContext = ctx;
    }

    @Override
    public View getDropDownView(int position, View contentView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @NonNull
    @Override
    public View getView(int pos, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(pos, parent);
    }

    private View getCustomView(int position, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View mySpinner = inflater.inflate(R.layout.spinner_item, parent, false);

        TextView main_text = (TextView) mySpinner
                .findViewById(R.id.spinner_text);
        main_text.setText(spinnerValues[position]);

        View view = mySpinner.findViewById(R.id.spinner_icon);
        GradientDrawable gd = new GradientDrawable();
        if (position == 0)
            gd.setColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        if (position == 1)
            gd.setColor(ContextCompat.getColor(mContext, R.color.colorSecond));
        if (position == 2)
            gd.setColor(ContextCompat.getColor(mContext, R.color.colorThird));

        gd.setShape(GradientDrawable.OVAL);
        view.setBackground(gd);

        return mySpinner;
    }


}
