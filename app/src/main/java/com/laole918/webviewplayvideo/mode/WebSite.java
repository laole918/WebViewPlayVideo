package com.laole918.webviewplayvideo.mode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by laole918 on 2016/4/14 0014.
 */
public class WebSite implements Parcelable {

    private String name;
    private String url;

    public WebSite() {
    }

    protected WebSite(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<WebSite> CREATOR = new Creator<WebSite>() {
        @Override
        public WebSite createFromParcel(Parcel in) {
            return new WebSite(in);
        }

        @Override
        public WebSite[] newArray(int size) {
            return new WebSite[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }
}
