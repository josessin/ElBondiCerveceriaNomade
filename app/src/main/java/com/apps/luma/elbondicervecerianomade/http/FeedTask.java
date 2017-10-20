package com.apps.luma.elbondicervecerianomade.http;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Usuario on 18/10/2017.
 */

public class FeedTask extends AsyncTask<String, Void, String> {

    private String url = "";
    private List<FeedTaskListener> listeners = new ArrayList<FeedTaskListener>();

    public FeedTask(String url){
        this.url = url;
    }

    public void addListener(FeedTaskListener newListener){
        listeners.add(newListener);
    }

    public void removeListener(FeedTaskListener newListener){
        if(listeners.contains(newListener)){
            listeners.remove(newListener);
        }
    }

    @Override
    public String doInBackground(String... params) {
        try {

            OkHttpClient client = new OkHttpClient();

//                RequestBody postBody = new FormBody.Builder()
//                        .add("token", "")
//                        .add("topKey", "")
//                        .add("bottomKey", "")
//                        .add("siguiente", "")
//                        .build();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Log.d("HEADERS",request.toString());

            Response res = client.newCall(request).execute();
            String result = res.body().string();

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject response = new JSONObject(s);

            for(FeedTaskListener l : listeners){
                l.taskCompleted(response);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
