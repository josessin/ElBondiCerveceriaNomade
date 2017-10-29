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
    private CalendarView calendarView;
    private Button gpsbuttonView;

    public LocalitationsAdapter(Context context, Locacion[] locaciones) {
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
        Button gpsbtn = (Button) convertView.findViewById(R.id.gpsbtn);
        CalendarView calendar = (CalendarView) convertView.findViewById(R.id.calendarV);
        this.setnota = nota;
        this.setdireccion = direccion;
        this.calendarView = calendar;
        this.gpsbuttonView = gpsbtn;
        cargaDatos();
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String yyyy = String.valueOf(year);
                String MM = String.valueOf(month);
                String dd = String.valueOf(dayOfMonth);
                Long yyyyMilli = Long.parseLong(yyyy) * Long.parseLong("31536000000");
                Long MMMilli = Long.parseLong(MM) * Long.parseLong("2628000000");
                Long ddMilli = Long.parseLong(dd) * Long.parseLong("86400000");
                Long dateMilli = ddMilli + MMMilli + yyyyMilli;
                view.setDate(dateMilli);
                eleccionFecha(dateMilli);
                cargaDatos();
            }
        });
        return convertView;
    }

    public void cargaDatos() {
        this.setlocacion = this.locaciones[0];
        if (this.setlocacion != null) {
            this.calendarView.setMinDate(this.setlocacion.getFecha().getTimeInMillis() - 86400000);
            this.calendarView.setMaxDate(this.setlocacion.getFecha().getTimeInMillis() + 1123200000);
            this.calendarView.setDate(setlocacion.getFecha().getTimeInMillis() - 86400000);
            this.setdireccion.setText(this.setlocacion.getDireccion());
            this.setnota.setText(this.setlocacion.getNota());
        } else {
            this.calendarView.setClickable(false);
            this.calendarView.setDate(Long.parseLong("63608112000000") + Long.parseLong("2628000000") + Long.parseLong("86400000"));
            setdireccion.setText("NO HAY FECHAS CARGADAS");
            setnota.setText("NO DISPONIBLE");
            gpsbuttonView.setClickable(false);
        }

        this.gpsbuttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void eleccionFecha(Long milli) {
        for (Locacion nuevaLocacion : this.locaciones) {
            if (nuevaLocacion.getFecha().getTimeInMillis() == milli) {
                this.setlocacion = nuevaLocacion;
            } else {
                this.setlocacion = null;
            }
        }
    }
}
