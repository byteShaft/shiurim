package com.byteshaft.shiurim.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.byteshaft.shiurim.R;

public class DocumentTwoFragment extends Fragment {

    private View mBaseView;
    private WebView mWebView;
    private TextView tv_network;
    private static ProgressDialog progressDialog;
    private String url = "https://docs.google.com/document/d/1t22RTWiVOYQxBY1_Xi4Zripyj" +
            "_XT0qjayv9VvQX-pTg/pub?embedded=true";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:{
                    webViewGoBack();
                }break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.doc_two_fragment, container, false);
        tv_network = (TextView) mBaseView.findViewById(R.id.tv_contact);
        mWebView = (WebView) mBaseView.findViewById(R.id.contact_us_webview);
        mWebView.setWebViewClient(new CustomWebView());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.loadUrl(url);
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && mWebView.canGoBack()) {
                    handler.sendEmptyMessage(1);
                    return true;
                }
                return false;
            }
        });

        if (isNetworkAvailable()) {
            mWebView.loadUrl(url);
        } else {
            mWebView.setVisibility(View.GONE);
            tv_network.setVisibility(View.VISIBLE);
        }
        return mBaseView;
    }

    private void webViewGoBack(){
        mWebView.goBack();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void showProgressDialog(Activity activity, String message) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    class CustomWebView extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            showProgressDialog( getActivity() , "Loading...");
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
//            dismissProgressDialog();

        }
    }
}
