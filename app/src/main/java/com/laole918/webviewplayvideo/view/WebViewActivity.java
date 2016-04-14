package com.laole918.webviewplayvideo.view;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.laole918.webviewplayvideo.R;
import com.laole918.webviewplayvideo.databinding.ActivityWebViewBinding;
import com.laole918.webviewplayvideo.mode.Setting;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class WebViewActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;
    private WebView mWebView;
    private InsideWebChromeClient mInsideWebChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        Setting setting = data.getParcelable("setting");
        if (setting == null) {
            return;
        }
        ActivityWebViewBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        mFrameLayout = mBinding.mFrameLayout;
        mWebView = mBinding.mWebView;
        initWebView();
        mWebView.loadUrl(setting.getWebSite().getUrl());
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);
//        settings.setPluginsEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mInsideWebChromeClient = new InsideWebChromeClient();
        InsideWebViewClient mInsideWebViewClient = new InsideWebViewClient();
        mWebView.addJavascriptInterface(new JavascriptInterface(), "java2js");
        mWebView.setWebChromeClient(mInsideWebChromeClient);
        mWebView.setWebViewClient(mInsideWebViewClient);
    }

    @Override
    public void onPause() {// 继承自Activity
        super.onPause();
        mWebView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        mWebView.destroy();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration config) {

        super.onConfigurationChanged(config);

        switch (config.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                break;
        }

    }

    private class InsideWebChromeClient extends WebChromeClient {

        private View mCustomView;
        private CustomViewCallback mCustomViewCallback;

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            mCustomView = view;
            mFrameLayout.addView(mCustomView);
            mCustomViewCallback = callback;
            mWebView.setVisibility(View.GONE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        public void onHideCustomView() {
            mWebView.setVisibility(View.VISIBLE);
            if (mCustomView == null) {
                return;
            }
            mCustomView.setVisibility(View.GONE);
            mFrameLayout.removeView(mCustomView);
            mCustomViewCallback.onCustomViewHidden();
            mCustomView = null;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            super.onHideCustomView();
        }

    }

    private class InsideWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            try {
                FileInputStream fis = getBaseContext().openFileInput("video.js");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                StringBuilder sb = new StringBuilder();
                String buff;
                while ((buff = reader.readLine()) != null) {
                    sb.append(buff);
                }
                sb.insert(0, "javascript:");
                mWebView.loadUrl(sb.toString());
            } catch (Exception ignored) {

            }
        }

    }

    public class JavascriptInterface {

        public final String TAG = JavascriptInterface.class.getSimpleName();

        @android.webkit.JavascriptInterface
        public void notifyVideoPlay() {
            Log.d(TAG, "play");
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    String call = "javascript:enterFullscreen()";
                    mWebView.loadUrl(call);
                }
            });
        }

        @android.webkit.JavascriptInterface
        public void notifyVideoPause() {
            Log.d(TAG, "pause");
        }

        @android.webkit.JavascriptInterface
        public void notifyVideoReset() {
            Log.d(TAG, "reset");
        }

        @android.webkit.JavascriptInterface
        public void notifyVideoEnded() {
            Log.d(TAG, "ended");
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (mInsideWebChromeClient != null) {
                        mInsideWebChromeClient.onHideCustomView();
                    }
                }
            });
        }
    }
}
