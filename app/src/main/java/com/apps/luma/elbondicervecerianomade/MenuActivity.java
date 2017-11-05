package com.apps.luma.elbondicervecerianomade;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.apps.luma.elbondicervecerianomade.api.Information;
import com.apps.luma.elbondicervecerianomade.gridFragments.GridFragmentLocalitations;
import com.apps.luma.elbondicervecerianomade.gridFragments.GridFragmentProducts;
import com.apps.luma.elbondicervecerianomade.modelo.Locacion;
import com.apps.luma.elbondicervecerianomade.modelo.Producto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MenuActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private ViewPager paginas;
    public TabLayout tabs;
    private static Producto[] productos;
    private static Locacion[] locaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tabs = (TabLayout) findViewById(R.id.tabs);
        auth = FirebaseAuth.getInstance();
        paginas = (ViewPager) findViewById(R.id.paginas);
        new Information(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        final int color = ContextCompat.getColor(this, R.color.orange_logo);
        tabs.addTab(tabs.newTab().setIcon(R.drawable.gps));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.beer));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(GridFragmentLocalitations.newInstance(1));
        adapter.addFragment(GridFragmentProducts.newInstance(2, productos));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    public void receptionDatos(JSONObject objLoca, JSONObject objProd) throws JSONException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JSONArray arrayLoca = objLoca.getJSONArray("entradas");
            JSONArray arrayProd = objProd.getJSONArray("productos");

            this.locaciones = mapper.readValue(arrayLoca.toString(), Locacion[].class);
            setLocaciones(locaciones);
            this.productos = mapper.readValue(arrayProd.toString(), Producto[].class);
            setProductos(productos);
            int i = 0;
            for (Locacion locacion : locaciones) {
                i++;
                Log.d("loca", "Direccion " + i + " : " + locacion.getDireccion());
                Log.d("loca", "Fecha " + i + " :" + locacion.getFecha());
                Log.d("loca", "Nota " + i + " :" + locacion.getNota());
            }
            int j = 0;
            for (Producto producto : productos) {
                j++;
                Log.d("loca", "Nombre " + j + " : " + producto.getNombre());
                Log.d("loca", "Descripcion " + j + " :" + producto.getDescripcion());
            }

            if (productos != null && locaciones != null) {
                setupViewPager(paginas);
            }

        } catch (JsonParseException e) {
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            new Information(this);
        } catch (JsonMappingException e) {
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            new Information(this);
        } catch (IOException e) {
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            new Information(this);
        } catch (JSONException e) {
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
            new Information(this);
        }
    }

    private static void setLocaciones(Locacion[] nana) {
        locaciones = nana;
    }

    private static void setProductos(Producto[] nana) {
        productos = nana;
    }

    public static Locacion[] getLocaciones() {
        return locaciones;
    }

    public static Producto[] getProductos() {
        return productos;
    }

    public void signOut() {
        AuthUI.getInstance().signOut(this);
    }

/* @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pedirDatos();
        } else {
            //TODO: Manejar permisos not granted
            finish();
        }
        return;

    }*/
}
