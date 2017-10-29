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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MenuActivity extends AppCompatActivity{

    FirebaseAuth auth;
    private ViewPager paginas;
    private TabLayout tabs;
    private Locacion locacion;
    private Producto producto;
    private static Producto[] productos;
    private static Locacion[] locaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        auth = FirebaseAuth.getInstance();
        tabs = (TabLayout) findViewById(R.id.tabs);
        paginas = (ViewPager) findViewById(R.id.paginas);

        //Data data = new Data(this);//BLOQUEADO PARA PRUEBAS

        try {
            hardCodeoPaginas(); //PARA PRUEBAS
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //       data.pedidoDatos(Tipo.LOCACION);//BLOQUEADO PARA PRUEBAS
 //       data.pedidoDatos(Tipo.PRODUCTOS);//BLOQUEADO PARA PRUEBAS

    }

        private void setupViewPager(ViewPager viewPager) {
            SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(GridFragmentLocalitations.newInstance(1), getResources().getDrawable(R.drawable.gps,this.getTheme()));
            adapter.addFragment(GridFragmentProducts.newInstance(2), getResources().getDrawable(R.drawable.beer,this.getTheme()));
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
                setLocaciones(locaciones);
                this.productos = mapper.readValue(arrayProd.toString(), Producto[].class);
                setProductos(productos);
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

    private void hardCodeoPaginas() throws ParseException, IOException { //PARA PRUEBAS
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String sortFecha = "1509235200000";
        Long millisecond = Long.parseLong(sortFecha);
        Calendar cal = Calendar.getInstance();
        String dateString = formatter.format(new Date(millisecond));
        cal.setTime(formatter.parse(dateString));
        Locacion loc1 = new Locacion("6a65465ds4a654ads","San Martin 3407",cal,"NOTA","2sdas654asd654");
        sortFecha = "1509321600000";
        millisecond = Long.parseLong(sortFecha);
        dateString = formatter.format(new Date(millisecond));
        cal.setTime(formatter.parse(dateString));
        Locacion loc2 = new Locacion("6a65465ds4saddads","San Martin 3408",cal,"NOTA","2sdas654asd554");
        sortFecha = "1509408000000";
        millisecond = Long.parseLong(sortFecha);
        dateString = formatter.format(new Date(millisecond));
        cal.setTime(formatter.parse(dateString));
        Locacion loc3 = new Locacion("6a54465ds4saddads","MI CASA",cal,"NOTA","2sdas644asd554");
        Locacion[] locacionesHard = {loc1, loc2, loc3};
        locaciones = locacionesHard;
        Producto prod1 = new Producto("CERVEZA CACA",R.drawable.beergreen,"DESCRIPCION","$555");
        Producto prod2 = new Producto("CERVEZA IPA",R.drawable.beeryellow,"DESCRIPCION2","$555");
        Producto prod3 = new Producto("CERVEZA DOBLE",R.drawable.gpsclickgreen,"DESCRIPCION3","$555");
        Producto prod4 = new Producto("CERVEZA MAMA",R.drawable.gpsred,"DESCRIPCION4","$555");
        Producto prod5 = new Producto("CERVEZA MIA",R.drawable.gpsclickred,"DESCRIPCION5","$555");
        Producto prod6 = new Producto("CERVEZA SAAA",R.drawable.gpsclickgreen,"DESCRIPCION6","$555");
        Producto prod7 = new Producto("CERVEZA NO SE",R.drawable.beergreen,"DESCRIPCION7","$555");
        Producto prod8 = new Producto("CERVEZA OJALA FUNQUE",R.drawable.beer,"DESCRIPCION8","$555");
        Producto[] productosHard = {prod1,prod2,prod3,prod4,prod5,prod6,prod7,prod8};
        productos = productosHard;
        setupViewPager(paginas);
        tabs.setupWithViewPager(paginas);
    }
    private static void setLocaciones(Locacion[] nana){
        locaciones = nana;
    }
    private static void setProductos(Producto[] nana){
        productos = nana;
    }
    public static Locacion[] getLocaciones(){
        return locaciones;
    }
    public static Producto[] getProductos(){
        return productos;
    }
    public void signOut() {
        AuthUI.getInstance().signOut(this);
    }

}
