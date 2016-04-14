package com.laole918.webviewplayvideo.viewmode;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.laole918.webviewplayvideo.BR;
import com.laole918.webviewplayvideo.R;
import com.laole918.webviewplayvideo.mode.Setting;
import com.laole918.webviewplayvideo.mode.WebSite;
import com.laole918.webviewplayvideo.view.WebViewActivity;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * Created by laole918 on 2016/4/14 0014.
 */
public class MainViewMode {

    public final ObservableList<WebSite> items = new ObservableArrayList<>();
    public final ItemView websiteItem = ItemView.of(BR.item, R.layout.layout_website_item);
    public final ItemView websiteDropdownItem = ItemView.of(BR.item, R.layout.layout_website_item_dropdown);

    public final ObservableField<Setting> setting = new ObservableField<>();

    public MainViewMode(String[] websites, Setting setting) {
        for (String web : websites) {
            String[] nameAndUrl = web.split("\\|", 2);
            String name = nameAndUrl[0];
            String url = nameAndUrl[1];
            WebSite webSite = new WebSite();
            webSite.setName(name);
            webSite.setUrl(url);
            items.add(webSite);
        }
        setting.setWebSite(items.get(0));
        this.setting.set(setting);
    }

    public final AdapterView.OnItemSelectedListener onSpinnerItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            setting.get().setWebSite(items.get(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public final CompoundButton.OnCheckedChangeListener onSwitchCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    };

    public void onClickOpenWebSite(View view) {
        Context context = view.getContext();
        Intent intent = new Intent();
        intent.putExtra("setting", setting.get());
        intent.setClass(context, WebViewActivity.class);
        context.startActivity(intent);
    }
}
