package com.apps.luma.elbondicervecerianomade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.apps.luma.elbondicervecerianomade.gridFragments.GridFragmentProductsDetails;

import org.w3c.dom.Text;

public class ProductsDetails extends AppCompatActivity {
    private static Intent i;
    private TextView titulo;
    private TextView descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_details);
        this.i = getIntent();
        titulo = (TextView) findViewById(R.id.txtTitulo);
        descripcion = (TextView) findViewById(R.id.txtDescripcion);
        ViewPager productsDetails = (ViewPager) findViewById(R.id.productDetails);
        setupViewPager(productsDetails);
        titulo.setText(i.getExtras().getString("nombre"));
        descripcion.setText(i.getExtras().getString("descripcion"));

    }
    private void setupViewPager(ViewPager viewPager) {

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(GridFragmentProductsDetails.newInstance(1));
        viewPager.setAdapter(adapter);
    }

    public static String[] getInformacion(){
        String[] informacion = new String[1];
        informacion[0]=i.getExtras().getString("url");
        return informacion;
    }
}
