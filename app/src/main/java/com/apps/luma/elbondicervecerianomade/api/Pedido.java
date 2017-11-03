package com.apps.luma.elbondicervecerianomade.api;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Usuario on 01/11/2017.
 */

public class Pedido extends AsyncTask<String, Void, String> {

    private String url;
    private Information info;

    public Pedido(String url, Information info) {
        this.url = url;
        this.info = info;
    }

    @Override
    protected String doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Log.d("HEADERS", request.toString());

        Response res = null;
        try {
            res = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = null;
        try {
            result = res.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject obj = new JSONObject(s);
            info.resivirInfo(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

    }
}
