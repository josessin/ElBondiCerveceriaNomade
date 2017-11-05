package com.apps.luma.elbondicervecerianomade;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apps.luma.elbondicervecerianomade.adapters.ProductaAdapter;
import com.apps.luma.elbondicervecerianomade.modelo.Producto;

public class ProductsDetails extends Activity {
    TextView titulo;
    TextView descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_details);
        titulo = (TextView) findViewById(R.id.titulo);
        descripcion = (TextView) findViewById(R.id.descripcion);

        Intent i = getIntent();
        int id = i.getExtras().getInt("id");
        ProductaAdapter productos = i.getExtras().getParcelable("lista");
        Producto producto = productos.getItem(id);
        titulo.setText(producto.getNombre());
        titulo.setText(producto.getDescripcion());
    }
}
