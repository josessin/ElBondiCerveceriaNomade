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

import com.apps.luma.elbondicervecerianomade.MenuActivity;
import com.apps.luma.elbondicervecerianomade.ProductsDetails;
import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.adapters.ProductsDetailsAdapter;
import com.apps.luma.elbondicervecerianomade.modelo.Producto;

/**
 * Created by Jrepetto on 28/11/2017.
 */

public class GridFragmentProductsDetails extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static GridFragmentProductsDetails newInstance(int numero) {
        GridFragmentProductsDetails fragment = new GridFragmentProductsDetails();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, numero);
        fragment.setArguments(args);
        return fragment;
    }

    public GridFragmentProductsDetails() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products_details, container, false);
        GridView grid = (GridView) rootView.findViewById(R.id.gridviewProductsDetails);
        setUpGridView(grid);
        return rootView;
    }

    private void setUpGridView(GridView grid) {
        int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
        switch (section_number) {
            case 1:
                grid.setAdapter(new ProductsDetailsAdapter(getActivity(), ProductsDetails.getInformacion()));
                break;
            default:
                break;
        }
    }
}