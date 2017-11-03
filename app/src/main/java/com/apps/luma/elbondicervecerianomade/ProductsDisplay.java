package com.apps.luma.elbondicervecerianomade;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.apps.luma.elbondicervecerianomade.modelo.Producto;


/**
 * Created by Jrepetto on 03/11/2017.
 */

public class ProductsDisplay extends AppCompatActivity {
    private Producto producto;
    private ProductsDetails productsDetails;
    private ViewGroup context;

    public ProductsDisplay(Producto producto, ViewGroup context) {
        this.producto = producto;
        this.context = context;
        startProductsDetails();

    }

    private void startProductsDetails() {
        Intent i = new Intent(context.getContext(), ProductsDetails.class);
        startActivity(i);
    }


}
