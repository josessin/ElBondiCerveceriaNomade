package com.apps.luma.elbondicervecerianomade.gridFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.apps.luma.elbondicervecerianomade.MenuActivity;
import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.adapters.LocalitationsAdapter;
import com.apps.luma.elbondicervecerianomade.adapters.ProductaAdapter;

/**
 * Created by Jrepetto on 26/10/2017.
 */

public class GridFragmentProducts extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static GridFragmentProducts newInstance(int sectionNumber) {
        GridFragmentProducts fragment = new GridFragmentProducts();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public GridFragmentProducts() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        GridView grid = (GridView) rootView.findViewById(R.id.gridviewProduct);
        setUpGridView(grid);
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

}
