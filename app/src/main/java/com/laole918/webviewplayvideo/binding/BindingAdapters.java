package com.laole918.webviewplayvideo.binding;

import android.databinding.BindingAdapter;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * Created by laole918 on 2016/3/30.
 */
public class BindingAdapters {

    @BindingAdapter({"onSpinnerItemSelectedListener"})
    public static void onSpinnerItemSelectedListener(Spinner spinner, AdapterView.OnItemSelectedListener onSpinnerItemSelectedListener) {
        spinner.setOnItemSelectedListener(onSpinnerItemSelectedListener);
    }

    @BindingAdapter({"onSwitchCheckedChangeListener"})
    public static void onSwitchCheckedChangeListener(Switch _switch, CompoundButton.OnCheckedChangeListener onSwitchCheckedChangeListener) {
        _switch.setOnCheckedChangeListener(onSwitchCheckedChangeListener);
    }

}
