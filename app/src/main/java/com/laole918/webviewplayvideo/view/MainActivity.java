package com.laole918.webviewplayvideo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.laole918.webviewplayvideo.R;
import com.laole918.webviewplayvideo.databinding.ActivityMainBinding;
import com.laole918.webviewplayvideo.mode.Setting;
import com.laole918.webviewplayvideo.viewmode.MainViewMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        String[] websites = getResources().getStringArray(R.array.website);
        Setting setting = new Setting();
        MainViewMode mainViewMode = new MainViewMode(websites, setting);
        binding.setMainViewMode(mainViewMode);
    }

}
