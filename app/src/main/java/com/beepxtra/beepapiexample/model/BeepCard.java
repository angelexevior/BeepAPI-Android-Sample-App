package com.beepxtra.beepapiexample.model;

import org.json.JSONObject;

/**
 * Created by BG on 05/06/2015.
 */
public class BeepCard {
    private JSONObject mResponse;
    private JSONObject mResponseJson;

    private JSONObject mError;
    private JSONObject mRequest;
    private JSONObject mData;
    private int mId;
    private Double mCashback;
    private Double mCommission;
    private Double mLock;
    private int mCardnumber;

    private boolean mSuccess;
    private int mErrorid;
    private String mErrormsg;

    public int getCardnumber() {
        return mCardnumber;
    }

    public void setCardnumber(int cardnumber) {
        mCardnumber = cardnumber;
    }

    public JSONObject getResponse() {
        return mResponse;
    }

    public void setResponse(JSONObject response) {
        mResponse = response;
    }

    public JSONObject getResponseJson() {
        return mResponseJson;
    }

    public void setResponseJson(JSONObject responseJson) {
        mResponseJson = responseJson;
    }

    public JSONObject getError() {
        return mError;
    }

    public void setError(JSONObject error) {
        mError = error;
    }

    public JSONObject getRequest() {
        return mRequest;
    }

    public void setRequest(JSONObject request) {
        mRequest = request;
    }

    public JSONObject getData() {
        return mData;
    }

    public void setData(JSONObject data) {
        mData = data;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Double getCashback() {
        return mCashback;
    }

    public void setCashback(Double cashback) {
        mCashback = cashback;
    }

    public Double getCommission() {
        return mCommission;
    }

    public void setCommission(Double commission) {
        mCommission = commission;
    }

    public Double getLock() {
        return mLock;
    }

    public void setLock(Double lock) {
        mLock = lock;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        mSuccess = success;
    }

    public int getErrorid() {
        return mErrorid;
    }

    public void setErrorid(int errorid) {
        mErrorid = errorid;
    }

    public String getErrormsg() {
        return mErrormsg;
    }

    public void setErrormsg(String errormsg) {
        mErrormsg = errormsg;
    }
}
