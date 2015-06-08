package com.beepxtra.beepapiexample.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by BG on 03/06/2015.
 */
public class BeepAPI {

    Context mContext;
    private TextView msgText;

    public BeepAPI(Context mContext){
        this.mContext = mContext;
    }

    private String mController;
    private String mResource;
    private String mParams;
    public String mResponse;
    private String mUrl;

    /*
    Setup your credentials
     */

    String apiId = "YOUR API ID"; //Change this to your own API id
    String apiKey = "1234"; //Change this to your own API Key

    /*
    Uses OkHttp Library from square
    pre-requisite: compile 'com.squareup.okhttp:okhttp:2.1.0'  //(For gradle versions)
     */
    public OkHttpClient buildCall(){
        OkHttpClient client = new OkHttpClient();
        return client;
    }

    /*
    Build the useragent with your credentials
     */
    public CharSequence buildUserAgent() {

        String mController = this.getController();
        CharSequence userAgent = "BeepAPI|2.0|"+apiId+"|"+apiKey+"|"+mController+"";
        userAgent = Html.fromHtml((String) userAgent);
        return userAgent;
    }

    /*
    Checks if network is available
    Make sure you have the proper permissions in your app

    *****************************************************
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     */
    public boolean networkIsAvailable() {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    /*
    Builds the proper API request URL
     */

    public String getUrl() {
        if(mParams == null){
            mUrl = "https://api.beepxtra.com/"+mController+"/"+mResource;
        } else {
            mUrl = "https://api.beepxtra.com/"+mController+"/"+mResource+"/"+mParams;
        }

        return mUrl;
    }


    //Getters and Setters for consistency
    public void setUrl(String url) {
        mUrl = url;
    }
    public String getResponse() {
        return mResponse;
    }
    public void setResponse(String response) {
        mResponse = response;
        //Log.v("apiResponseClass", mResponse);
    }
    public String getController() {
        return mController;
    }
    public void setController(String controller) {
        mController = controller;
    }
    public String getResource() {
        return mResource;
    }
    public void setResource(String resource) {
        mResource = resource;
    }
    public String getParams() {
        return mParams;
    }
    public void setParams(String params) {
        mParams = params;
    }
}
