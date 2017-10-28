package com.apps.luma.elbondicervecerianomade.http;

import com.apps.luma.elbondicervecerianomade.MenuActivity;
import com.apps.luma.elbondicervecerianomade.R;

import org.json.JSONObject;

/**
 * Created by Jrepetto on 28/10/2017.
 */

public class Data implements FeedTaskListener{
    private MenuActivity menu;
    int i = 0;

    public Data(MenuActivity menu)
    {
        this.menu = menu;
    }

    public void pedidoDatos(String url)
    {
        FeedTask ft = new FeedTask(url);
        ft.addListener(this);
        ft.execute();
    }

    @Override
    public void taskCompleted(JSONObject obj) {
        this.i++;
        JSONObject paso1 = null;
        JSONObject paso2 = null;
        if(i == 1){
            paso1 = obj;
        }else if(i == 2){
            paso2 = obj;
        }
        if(paso1 != null && paso2 != null){
            this.menu.receptionLocations(paso1, paso2);
        }

    }
}
