package com.beepxtra.beepapiexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.beepxtra.beepapiexample.R;
import com.beepxtra.beepapiexample.controller.BeepAPI;
import com.beepxtra.beepapiexample.model.BeepCard;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    public BeepAPI mBeepAPI;
    public BeepCard mCard;
    private Context mContext;
    private TextView mWelcome;
    private TextView mCashback;
    private TextView mCommission;
    private TextView mCardnumber;
    private TextView mErrormsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String email = "angelos@exevior.com";

        mBeepAPI = new BeepAPI(getThis());
        mCard = new BeepCard();
        getCard(email);
    }

    public Context getThis() {
        mContext = this;
        return mContext;
    }

    private void getCard(String email) {
        //Get the user details from api
        mBeepAPI.setController("card");
        mBeepAPI.setResource("balance");
        mBeepAPI.setParams(email);
        OkHttpClient client;
        CharSequence userAgent = mBeepAPI.buildUserAgent();
        if (mBeepAPI.networkIsAvailable()) {
            client = new OkHttpClient();
            Request request = new Request.Builder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", userAgent.toString())
                    .url(mBeepAPI.getUrl())
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            setCardDetails(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay("card");
                                }
                            });

                        } else {
                            Log.v("apiError", response.body().string());

                        }

                    } catch (IOException e) {
                        Log.e("apiIOException", "Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e("apiJSONException", "Exception caught: ", e);
                    }
                }
            });
        } else {
            Log.e("apiNetwork", "Exception caught: " + mBeepAPI.networkIsAvailable());
            Toast.makeText(this, getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
        }
    }


    private BeepCard setCardDetails(String jsonData) throws JSONException {
        JSONObject response = new JSONObject(jsonData);
        JSONObject error = response.getJSONObject("error");
        JSONObject request = response.getJSONObject("request");
        JSONObject data = response.getJSONObject("data");
        mCard.setResponse(response);
        mCard.setError(error);
        mCard.setRequest(request);
        mCard.setData(data);
        //      Log.i("JsonData","success "+response.getBoolean("success")+" - id:"+data.getInt("id"));
        Boolean success = response.getBoolean("success");
        mCard.setSuccess(success);
        if (response.getBoolean("success")) {
            mCard.setId(data.getInt("user_id"));
            mCard.setCashback(data.getDouble("cashback"));
            mCard.setCommission(data.getDouble("commissions"));
            mCard.setLock(data.getDouble("lock_cash_back"));
            mCard.setCardnumber(data.getInt("card_number"));
            mCard.setErrorid(0);
            mCard.setErrormsg("Data Successful");
        } else {
            mCard.setId(0);
            mCard.setCashback(0.00);
            mCard.setCommission(0.00);
            mCard.setLock(0.00);
            mCard.setCardnumber(0000000000000000);
            mCard.setErrorid(error.getInt("errorid"));
            mCard.setErrormsg(error.getString("message"));
        }

        return new BeepCard();
    }

    private void updateDisplay(String method) {
        if(method == "card"){

            int error = mCard.getErrorid();
            //Checks for error to display message
            if(error != 0){
                mErrormsg = (TextView) findViewById(R.id.errorText);
                mErrormsg.setText(mCard.getErrormsg());
            }

            //Display the retrieved data on screen
            mCardnumber = (TextView) findViewById(R.id.cardText);
            mCardnumber.setText(mCard.getCardnumber()+"");
            mCashback = (TextView) findViewById(R.id.cashbackText);
            mCashback.setText("$"+mCard.getCashback().toString());
            mCommission = (TextView) findViewById(R.id.commissionText);
            mCommission.setText("$"+mCard.getCommission().toString());
        }

    }
}
