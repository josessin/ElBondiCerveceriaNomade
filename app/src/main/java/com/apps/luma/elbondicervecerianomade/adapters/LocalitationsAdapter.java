package com.apps.luma.elbondicervecerianomade.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.modelo.Locacion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private ImageButton gpsbuttonView;
    private Long fechaError;
    private String direccionGoogle;
    private String[] arrayDirecGoogle;

    public LocalitationsAdapter(Context context, Locacion[] locaciones) {
        this.context = context;
        this.locaciones = locaciones;
        if (this.locaciones != null) {
            this.setlocacion = this.locaciones[0];
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
        final ImageButton gpsbtn = (ImageButton) convertView.findViewById(R.id.gpsbtn);
        CalendarView calendar = (CalendarView) convertView.findViewById(R.id.calendarV);
        this.setnota = nota;
        this.setdireccion = direccion;
        this.calendarView = calendar;
        this.gpsbuttonView = gpsbtn;
        if (this.locaciones != null) {
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
            try {
                for (Locacion loca : this.locaciones){
                    Log.i("locacionesFecha", loca.getFecha());
                    Date dateloca = format.parse(loca.getFecha());
                    Log.i("LocacionesFechaEnMilli", String.valueOf(dateloca.getTime()));
                }
                Date minDate  = format.parse(this.locaciones[0].getFecha());
                this.calendarView.setMinDate(minDate.getTime());
                Date maxDate  = format.parse(this.locaciones[this.locaciones.length -1].getFecha());
                this.calendarView.setMaxDate(maxDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (this.setlocacion == null) {
                this.calendarView.setDate(this.fechaError);
            }
            cargaDatos(direccion, nota, gpsbtn, calendar, position, convertView, parent);
        } else {
            this.calendarView.setClickable(false);
            this.gpsbuttonView.setClickable(false);
            this.setnota.setText(R.string.no_disponible);
            this.setdireccion.setText(R.string.no_destinos);
        }
        return convertView;
    }

    public void cargaDatos(TextView direccion, TextView nota, ImageButton gpsbtn, CalendarView calendar,
                           int position, View convertView, ViewGroup parent) {
        final int position2 = position;
        final View convertView2 = convertView;
        final ViewGroup parent2 = parent;
        this.setnota = nota;
        this.setnota.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        this.setdireccion = direccion;
        this.gpsbuttonView = gpsbtn;
        this.calendarView = calendar;
        if (this.setlocacion != null) {
            this.gpsbuttonView.setEnabled(true);
            this.setdireccion.setText(this.setlocacion.getDireccion());
            this.setnota.setText(this.setlocacion.getNota());
        } else {
            this.calendarView.setClickable(false);
            this.calendarView.setDate(this.fechaError);
            this.setdireccion.setText(R.string.viajando);
            this.setnota.setText(R.string.no_disponible);
            this.gpsbuttonView.setEnabled(false);
        }
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String dia;
                if (dayOfMonth <= 9) {
                    dia = "0" + String.valueOf(dayOfMonth);
                } else {
                    dia = String.valueOf(dayOfMonth);
                }
                String fechaCalendario = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + dia;
                eleccionFecha(fechaCalendario, view.getDate());
                getView(position2, convertView2, parent2);
            }
        });

        this.gpsbuttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDireccionGoogle();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + direccionGoogle);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
        });
    }

    private void cargarDireccionGoogle() {
        this.direccionGoogle = setdireccion.getText().toString();

        this.arrayDirecGoogle = this.direccionGoogle.split(" ");
        this.direccionGoogle = "";
        for (int i = 0; i < arrayDirecGoogle.length; i++) {
            direccionGoogle += arrayDirecGoogle[i];
            if (i != arrayDirecGoogle.length - 1) {
                direccionGoogle += "+";
            }
        }
        this.direccionGoogle += ", Mendoza, Argentina";
    }

    public void eleccionFecha(String fechaCalendario, long milli) {
        this.fechaError = milli;

        for (Locacion nuevaLocacion : this.locaciones) {
            if (nuevaLocacion.getFecha().equals(fechaCalendario)) {
                this.setlocacion = nuevaLocacion;
                break;
            } else {
                this.setlocacion = null;
            }
        }
    }
}

