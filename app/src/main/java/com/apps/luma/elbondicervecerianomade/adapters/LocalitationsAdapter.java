package com.apps.luma.elbondicervecerianomade.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.modelo.Locacion;

/**
 * Created by Jrepetto on 28/10/2017.
 */

public class LocalitationsAdapter extends BaseAdapter {
    private Context context;
    private Locacion setlocacion;
    private Locacion[] locaciones;
    private TextView setdireccion;
    private TextView setnota;

    public LocalitationsAdapter(Context context, Locacion[] locaciones)
    {
        this.context = context;
        this.locaciones = locaciones;
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
            convertView = inflater.inflate(R.layout.grid_location, parent, false);
        }
        TextView direccion = (TextView) convertView.findViewById(R.id.direccionTextV);
        TextView nota = (TextView) convertView.findViewById(R.id.notaTextV);
        this.setnota = nota;
        this.setdireccion = direccion;
        Button gpsbtn = (Button) convertView.findViewById(R.id.gpsbtn);
        CalendarView calendar = (CalendarView) convertView.findViewById(R.id.calendarV);
        calendar.setMinDate(this.locaciones[0].getFecha().getTimeInMillis());
        calendar.setMaxDate(this.locaciones[0].getFecha().getTimeInMillis() + (this.locaciones[0].getFecha().DAY_OF_MONTH + 15));
        if(this.setlocacion != null) {
            this.setdireccion.setText(this.setlocacion.getDireccion());
            this.setnota.setText(this.setlocacion.getNota());
            }else{
            calendar.setDate(this.locaciones[0].getFecha().getTimeInMillis());
            this.setlocacion = setLocacion(this.locaciones, calendar.getDate());
            this.setdireccion.setText(this.setlocacion.getDireccion());
            this.setnota.setText(this.setlocacion.getNota());
        }
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                setlocacion = setLocacion(locaciones,view.getDate());
                setdireccion.setText(setlocacion.getDireccion());
                setnota.setText(setlocacion.getNota());
            }
        });
        gpsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    public Locacion setLocacion(Locacion[] locaciones, Long fecha){
        for(Locacion setableLocacion : locaciones){
            if(setableLocacion.getFecha().getTimeInMillis() == fecha){
                return setableLocacion;
            }
        }
        return null;
    }


}
