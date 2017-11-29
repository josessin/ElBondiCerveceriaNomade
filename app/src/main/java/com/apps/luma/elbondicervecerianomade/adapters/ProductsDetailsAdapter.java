package com.apps.luma.elbondicervecerianomade.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.modelo.Producto;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import okhttp3.internal.cache.CacheRequest;

/**
 * Created by Jrepetto on 28/11/2017.
 */

public class ProductsDetailsAdapter extends BaseAdapter {
    private Context context;
    private String[] informacion;
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    public ProductsDetailsAdapter(Context context, String[] informacion) {
        this.context = context;
        this.informacion = informacion;
    }
    public ProductsDetailsAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            convertView = inflater.inflate(R.layout.grid_products_details, parent, false);
        }

        ImageView cerveza = (ImageView) convertView.findViewById(R.id.imgCerveza);
        StorageReference storageReference = storage.getReference().child("img-productos/" + informacion[0]);
        Glide.with(cerveza.getContext())
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(cerveza);
        return convertView;
    }
}

