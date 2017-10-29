package com.apps.luma.elbondicervecerianomade.gridFragments;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.apps.luma.elbondicervecerianomade.MenuActivity;
import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.adapters.LocalitationsAdapter;
import com.apps.luma.elbondicervecerianomade.adapters.ProductaAdapter;
import com.apps.luma.elbondicervecerianomade.modelo.Locacion;
import com.apps.luma.elbondicervecerianomade.modelo.Producto;

/**
 * Created by Jrepetto on 28/10/2017.
 */

public class GridFragmentLocalitations extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static GridFragmentLocalitations newInstance(int sectionNumber) {
        GridFragmentLocalitations fragment = new GridFragmentLocalitations();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public GridFragmentLocalitations()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_locacion, container, false);
        GridView grid = (GridView) rootView.findViewById(R.id.gridviewLocation);
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
