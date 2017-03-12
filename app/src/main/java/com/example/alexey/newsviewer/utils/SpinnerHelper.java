package com.example.alexey.newsviewer.utils;

import android.content.Context;

import com.example.alexey.newsviewer.R;

/**
 * Created by alexey on 12/03/17.
 */

public class SpinnerHelper {

    public static int getColorFromSpinner(int selections, Context mContext) {
        switch (selections) {
            case 0:
                return mContext.getResources().getColor(R.color.colorWhite);
            case 1:
                return mContext.getResources().getColor(R.color.colorSecond);
            case 2:
                return mContext.getResources().getColor(R.color.colorThird);
            default:
                return mContext.getResources().getColor(R.color.colorWhite);
        }
    }

    public static int getSpinnerFromColor(int color) {
        switch (color) {
            case 0xffffff:
                return 0;
            case 0xa5d6a7:
                return 1;
            case 0xbbdefb:
                return 2;
            default:
                return 0;
        }
    }
}
