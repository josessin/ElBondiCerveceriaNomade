package com.apps.luma.elbondicervecerianomade.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.modelo.Locacion;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Jrepetto on 28/10/2017.
 */

public class LocalitationsAdapter extends BaseAdapter {
    private Context context;
    private Locacion setlocacion;
    private Locacion[] locaciones;
    private TextView setdireccion;
    private TextView setnota;
    private CalendarView calendarView;
    private Button gpsbuttonView;
    private Long FechaError;

    public LocalitationsAdapter(Context context, Locacion[] locaciones) {
        this.context = context;
        this.locaciones = locaciones;
        if (!this.locaciones.equals(null)) {
            this.setlocacion = this.locaciones[0];
        }else{

        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_location, parent, false);
        }
        final TextView direccion = (TextView) convertView.findViewById(R.id.direccionTextV);
        final TextView nota = (TextView) convertView.findViewById(R.id.notaTextV);
        final Button gpsbtn = (Button) convertView.findViewById(R.id.gpsbtn);
        CalendarView calendar = (CalendarView) convertView.findViewById(R.id.calendarV);
        this.setnota = nota;
        this.setdireccion = direccion;
        this.calendarView = calendar;
        this.gpsbuttonView = gpsbtn;
        if(!this.locaciones.equals(null)) {
            this.calendarView.setMinDate(this.locaciones[0].getFecha().getTimeInMillis());
            this.calendarView.setMaxDate(this.locaciones[this.locaciones.length - 1].getFecha().getTimeInMillis());
            if (this.setlocacion == null) {
                this.calendarView.setDate(this.FechaError);
            } else {
                this.calendarView.setDate(this.setlocacion.getFecha().getTimeInMillis());
            }
            cargaDatos(direccion,nota,gpsbtn, calendar,position, convertView, parent);
        }else{
            this.calendarView.setClickable(false);
            this.gpsbuttonView.setClickable(false);
            this.setnota.setText("NO DISPONIBLE");
            this.setdireccion.setText("NO HAY PROXIMAS SALIDAS");
        }



        return convertView;
    }

    public void cargaDatos(TextView direccion,TextView nota, Button gpsbtn, CalendarView calendar,
                           int position, View convertView, ViewGroup parent) {
        final int position2 = position;
        final View convertView2 = convertView;
        final ViewGroup parent2 = parent;
        this.setnota = nota;
        this.setdireccion = direccion;
        this.gpsbuttonView = gpsbtn;
        this.calendarView = calendar;
        if (this.setlocacion != null) {
            this.setdireccion.setText(this.setlocacion.getDireccion());
            this.setnota.setText(this.setlocacion.getNota());

        } else {
            this.calendarView.setClickable(false);
            this.calendarView.setDate(this.FechaError);
            setdireccion.setText("NO TRABAJAMOS ESTE DIA");
            setnota.setText("NO DISPONIBLE");
            gpsbuttonView.setClickable(false);
        }
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c = new GregorianCalendar( year, month, dayOfMonth );
                eleccionFecha(c.getTimeInMillis());
                getView(position2, convertView2 ,parent2);
            }
        });

        this.gpsbuttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void eleccionFecha(Long milli) {
        this.FechaError = milli;
        for (Locacion loca : locaciones) {
            Log.d("LOCOMOUSHON", loca.getDireccion());
        }

        for (Locacion nuevaLocacion : this.locaciones) {
            if (nuevaLocacion.getFecha().getTimeInMillis() == (milli)){
                this.setlocacion = nuevaLocacion;
                break;
            }else{
                this.setlocacion = null;
            }
        }
    }
}

