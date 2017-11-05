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

public class ProductaAdapter extends BaseAdapter {
    private Context context;
    private Producto[] productos;
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    public ProductaAdapter(Context context, Producto[] productos) {
        this.context = context;
        this.productos = productos;
    }
    public ProductaAdapter(Context context) {
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
       /* Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap(); TODO:HACE LA IMAGEN REDONDA (EXPLOA)
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(image.getResources(), bitmap);
        roundedDrawable.setCornerRadius(bitmap.getHeight());
        image.setImageDrawable(roundedDrawable);*/
        TextView name = (TextView) convertView.findViewById(R.id.nombre);
        name.setText(item.getNombre());
        //TextView descripcion = (TextView) convertView.findViewById(R.id.descripcion);
        //descripcion.setText(item.getDescripcion());
        //TODO:SE SETEA AMARGURA
       /* RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating);
        ratingBar.setRating(item.getRating())*/
        return convertView;
    }

}
