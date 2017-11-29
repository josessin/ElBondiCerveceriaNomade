package com.apps.luma.elbondicervecerianomade.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.modelo.Producto;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Jrepetto on 28/10/2017.
 */

public class ProductsAdapter extends BaseAdapter {
    private Context context;
    private Producto[] productos;
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    public ProductsAdapter(Context context, Producto[] productos) {
        this.context = context;
        this.productos = productos;
    }
    public ProductsAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return productos.length;
    }

    @Override
    public Producto getItem(int position) {
        return productos[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_products, parent, false);
        }

        Producto item = getItem(position);
        StorageReference storageReference = storage.getReference().child("img-productos/" + item.getImgUrl());
        ImageView image = (ImageView) convertView.findViewById(R.id.imagen);
        Glide.with(image.getContext())
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(image);
        TextView name = (TextView) convertView.findViewById(R.id.nombre);
        name.setText(item.getNombre());
        return convertView;
    }

}
