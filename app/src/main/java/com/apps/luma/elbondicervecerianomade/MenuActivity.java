package com.apps.luma.elbondicervecerianomade;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apps.luma.elbondicervecerianomade.gridFragments.GridFragmentLocalitations;
import com.apps.luma.elbondicervecerianomade.gridFragments.GridFragmentProducts;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MenuActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private ViewPager paginas;
    public TabLayout tabs;
    private Locacion locacion;
    private Producto producto;
    private static Producto[] productos;
    private static Locacion[] locaciones;
    private TabLayout.Tab iconBeer;
    private TabLayout.Tab iconGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tabs = (TabLayout) findViewById(R.id.tabs);
        auth = FirebaseAuth.getInstance();
        paginas = (ViewPager) findViewById(R.id.paginas);

        try {
            hardCodeoPaginas(); //TODO: PARA PRUEBAS
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Data data = new Data(this);//TODO: BLOQUEADO PARA PRUEBAS
        //       data.pedidoDatos(Tipo.LOCACION);//TODO: BLOQUEADO PARA PRUEBAS
        //       data.pedidoDatos(Tipo.PRODUCTOS);//TODO: BLOQUEADO PARA PRUEBAS


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
        adapter.addFragment(GridFragmentProducts.newInstance(2));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }


    public void receptionLocations(JSONObject objLoca, JSONObject objProd) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Log.d("JSON", objLoca.toString(3));
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

    private void hardCodeoPaginas() throws ParseException, IOException { //TODO: PARA PRUEBAS
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String sortFecha = "1510369200000";
        Long millisecond = Long.parseLong(sortFecha);
        Calendar cal = Calendar.getInstance();
        String dateString = formatter.format(new Date(millisecond));
        cal.setTime(formatter.parse(dateString));
        Locacion loc1 = new Locacion("6a65465ds4a654ads", "CASA LALALA", cal, "Hola jose mira como se ve esto va quedando el diseño ;)dsfdsfdsfdsfd dfs fdsfdsfs dfsdfdsfdsfdsf sdfdsfsdfdsfdsf sdf ", "2sdas654asd654");
        String sortFecha2 = "1510455600000";
        Long millisecond2 = Long.parseLong(sortFecha2);
        Calendar cal2 = Calendar.getInstance();
        String dateString2 = formatter.format(new Date(millisecond2));
        cal2.setTime(formatter.parse(dateString2));
        Locacion loc2 = new Locacion("6a65465ds4saddads", "San Martin 3408", cal2, "NOTA", "2sdas654asd554");
        String sortFecha3 = "1510714800000";
        Long millisecond3 = Long.parseLong(sortFecha3);
        Calendar cal3 = Calendar.getInstance();
        String dateString3 = formatter.format(new Date(millisecond3));
        cal3.setTime(formatter.parse(dateString3));
        Locacion loc3 = new Locacion("6a54465ds4saddads", "MI CASA", cal3, "NOTA", "2sdas644asd554");
        Locacion[] locacionesHard = {loc1, loc2, loc3};
        this.locaciones = locacionesHard;
        Producto prod1 = new Producto("CERVEZA CACA", R.drawable.beergreen, "DESCRIPCION", "$555");
        Producto prod2 = new Producto("CERVEZA IPA", R.drawable.beeryellow, "DESCRIPCION2", "$555");
        Producto prod3 = new Producto("CERVEZA DOBLE", R.drawable.gpsclickgreen, "DESCRIPCION3", "$555");
        Producto prod4 = new Producto("CERVEZA MAMA", R.drawable.gpsred, "DESCRIPCION4", "$555");
        Producto prod5 = new Producto("CERVEZA MIA", R.drawable.gpsclickred, "DESCRIPCION5", "$555");
        Producto prod6 = new Producto("CERVEZA SAAA", R.drawable.gpsclickgreen, "DESCRIPCION6", "$555");
        Producto prod7 = new Producto("CERVEZA NO SE", R.drawable.beergreen, "DESCRIPCION7", "$555");
        Producto prod8 = new Producto("CERVEZA OJALA FUNQUE", R.drawable.beer, "DESCRIPCION8", "$555");
        Producto[] productosHard = {prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8};
        this.productos = productosHard;
        setupViewPager(paginas);

    }//TODO: FIN PRUEBA

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

    }*/}
