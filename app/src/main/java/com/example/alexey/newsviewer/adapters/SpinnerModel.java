package com.example.alexey.newsviewer.adapters;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.support.v7.app.AppCompatActivity;

import com.example.alexey.newsviewer.R;

import java.util.Arrays;

/**
 * Created by alexey on 12/03/17.
 */

@InverseBindingMethods({
        @InverseBindingMethod(type = AppCompatActivity.class, attribute = "android:selectedItemPosition"),
})
public class SpinnerModel extends BaseObservable {

    public final ObservableArrayList<String> colors = new ObservableArrayList<>();

    public final ObservableInt currentPosition = new ObservableInt();

    public SpinnerModel(Context context) {
        colors.addAll(Arrays.asList(context.getResources().getStringArray(R.array.spinner_states)));
    }

}


