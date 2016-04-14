package com.laole918.webviewplayvideo.mode;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by laole918 on 2016/3/30.
 */
public class Setting extends BaseObservable implements Parcelable {

    private WebSite webSite;

    public Setting() {
    }

    protected Setting(Parcel in) {
        webSite = in.readParcelable(WebSite.class.getClassLoader());
    }

    public static final Creator<Setting> CREATOR = new Creator<Setting>() {
        @Override
        public Setting createFromParcel(Parcel in) {
            return new Setting(in);
        }

        @Override
        public Setting[] newArray(int size) {
            return new Setting[size];
        }
    };

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(webSite, flags);
    }
}
