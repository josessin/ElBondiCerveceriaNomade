package com.apps.luma.elbondicervecerianomade.gridFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.apps.luma.elbondicervecerianomade.MenuActivity;
import com.apps.luma.elbondicervecerianomade.ProductsDetails;
import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.adapters.LocalitationsAdapter;
import com.apps.luma.elbondicervecerianomade.adapters.ProductaAdapter;
import com.apps.luma.elbondicervecerianomade.modelo.Producto;

import static com.firebase.ui.storage.R.id.image;

/**
 * Created by Jrepetto on 26/10/2017.
 */

public class GridFragmentProducts extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static GridFragmentProducts newInstance(int sectionNumber, Producto[] productos) {
        GridFragmentProducts fragment = new GridFragmentProducts();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public GridFragmentProducts() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        final GridView grid = (GridView) rootView.findViewById(R.id.gridviewProduct);
        setUpGridView(grid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ProductsDetails a = new ProductsDetails();
                Intent i = new Intent(parent.getContext(), ProductsDetails.class);
                i.putExtra("id", position);
                Log.d("POSICIONES", String.valueOf(position));
                i.putExtra("lista", String.valueOf(getGrid(grid)));
                //startActivity(i);
            }
        });
        return rootView;
    }

    private void setUpGridView(GridView grid) {
        int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
        switch (section_number) {
            case 1:
                grid.setAdapter(new LocalitationsAdapter(getActivity(), MenuActivity.getLocaciones()));
                break;
            case 2:
                grid.setAdapter(new ProductaAdapter(getActivity(), MenuActivity.getProductos()));
                break;
        }

    }
    private Object getGrid(GridView grid){

        return grid.getAdapter();
    }

}
