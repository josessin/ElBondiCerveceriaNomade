package com.apps.luma.elbondicervecerianomade.http;

import android.os.AsyncTask;
import android.util.Log;

import com.apps.luma.elbondicervecerianomade.MenuActivity;
import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.Tipo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jrepetto on 28/10/2017.
 */
enum Estado
{
    CORRIENDO, PAUSA, FIN;
}

public class Data {
    private MenuActivity menu;
    private FeedTask ft = new FeedTask();
    private JSONObject local;
    private JSONObject produc;

    public Data(MenuActivity menu) {
        this.menu = menu;
        ft.execute();
    }

    public void pedidoDatos(Tipo tipo) {

        ft.setTipo(tipo);
        ft.setEstado(Estado.CORRIENDO);
        if (tipo == Tipo.LOCACION) {
            ft.setUrl(menu.getResources().getString(R.string.locations_url));
        } else {
            ft.setUrl(menu.getResources().getString(R.string.products_url));
        }

    }

    public void ejecutarSentencia()
    {
        if(local != null && produc != null){
            menu.receptionLocations(local,produc);
        }else{
            return;
        }
    }


    private class FeedTask extends AsyncTask<Void, String, Void> {

        private Estado estado = Estado.PAUSA;
        private Tipo tipo;
        private String url = "";

        public void setUrl(String url) {
            this.url = url;
        }

        public void setTipo(Tipo tipo) {
            this.tipo = tipo;
        }
        public void setEstado(Estado estado){
            this.estado = estado;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                while (estado == Estado.CORRIENDO && tipo == Tipo.LOCACION ||
                        estado == Estado.CORRIENDO && tipo == Tipo.PRODUCTOS) {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Log.d("HEADERS", request.toString());

                    Response res = client.newCall(request).execute();
                    String result = res.body().string();

                    publishProgress(result);
                }

                if(estado == Estado.PAUSA){
                    doInBackground();
                }else {
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... result) {
            try {
                JSONObject response = new JSONObject(result[0]);
                Log.d("JSON", result.toString());
                if (tipo == Tipo.LOCACION) {
                    local = response;
                    estado = Estado.PAUSA;
                }else{
                    produc = response;
                    estado = Estado.FIN;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void s) {
            ejecutarSentencia();
            try {
                this.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}