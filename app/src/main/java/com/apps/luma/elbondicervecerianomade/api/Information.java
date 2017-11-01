package com.apps.luma.elbondicervecerianomade.api;

import android.util.Log;

import com.apps.luma.elbondicervecerianomade.MenuActivity;
import com.apps.luma.elbondicervecerianomade.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 01/11/2017.
 */

public class Information {
    private MenuActivity menuActivity;
    private List<JSONObject> datos = new ArrayList<>();

    public Information(MenuActivity menuActivity){
        this.menuActivity = menuActivity;
        hacerPedidos();
    }

    private void hacerPedidos(){
        Pedido p1 = new Pedido(menuActivity.getResources().getString(R.string.locations_url), this);
        p1.execute();
        Pedido p2 = new Pedido(menuActivity.getResources().getString(R.string.products_url), this);
        p2.execute();

    }

    public void resivirInfo(JSONObject s) throws JSONException {
        datos.add(s);
        Log.d("JSONNN", s.toString(3));
        if(datos.size()==2){
            menuActivity.receptionDatos(datos.get(0),datos.get(1));
        }
    }
}


