package com.apps.luma.elbondicervecerianomade;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.apps.luma.elbondicervecerianomade.gridFragments.GridFragmentLocalitations;
import com.apps.luma.elbondicervecerianomade.gridFragments.GridFragmentProducts;
import com.apps.luma.elbondicervecerianomade.http.Data;
import com.apps.luma.elbondicervecerianomade.modelo.Locacion;
import com.apps.luma.elbondicervecerianomade.modelo.Producto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MenuActivity extends AppCompatActivity{

    FirebaseAuth auth;
    private ViewPager paginas;
    private TabLayout tabs;
    private Data data = new Data(this);
    private Locacion locacion;
    private Producto producto;
    private Producto[] productos;
    private static Locacion[] locaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        auth = FirebaseAuth.getInstance();
        tabs = (TabLayout) findViewById(R.id.tabs);
        paginas = (ViewPager) findViewById(R.id.paginas);
        tabs.addTab(tabs.newTab().setIcon(R.drawable.gpswhite));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.beerwhite));
        Data data = new Data(this);
        data.pedidoDatos(getResources().getString(R.string.locations_url));
        data.pedidoDatos(getResources().getString(R.string.products_url));

    }

        private void setupViewPager(ViewPager viewPager) {
            SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(GridFragmentLocalitations.newInstance(1), ("local"));
            adapter.addFragment(GridFragmentProducts.newInstance(2), ("product"));
            viewPager.setAdapter(adapter);
        }



    public void receptionLocations(JSONObject objLoca, JSONObject objProd)
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Log.d("JSON", objLoca.toString(3));
            JSONArray arrayLoca = objLoca.getJSONArray("entradas");
            JSONArray arrayProd = objProd.getJSONArray("productos");

                this.locaciones = mapper.readValue(arrayLoca.toString(), Locacion[].class);
                this.locacion.setLocaciones(locaciones);
                this.productos = mapper.readValue(arrayProd.toString(), Producto[].class);
                this.producto.setProductos(productos);
                int i = 0;
                for(Locacion locacion : locaciones) {
                    i++;
                    Log.d("loca", "Direccion " + i + " : " + locacion.getDireccion());
                    Log.d("loca","Fecha " + i + " :" + locacion.getFecha());
                    Log.d("loca","Nota " + i + " :" + locacion.getNota());
                }
                int j = 0;
                for(Producto producto : productos) {
                    j++;
                    Log.d("loca", "Nombre " + j + " : " + producto.getNombre());
                    Log.d("loca","Descripcion " + j + " :" + producto.getDescripcion());
                }

            if(productos != null && locaciones != null)
            {
                setupViewPager(paginas);
                tabs.setupWithViewPager(paginas);
            }



        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //this.activityLocaciones = new LocacionesActivity(info);
    }

    public void signOut() {
        AuthUI.getInstance().signOut(this);
    }

}
