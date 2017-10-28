package com.apps.luma.elbondicervecerianomade.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;

/**
 * Created by Jrepetto on 25/10/2017.
 */

public class Locacion {
    @JsonProperty("uid") private String uid;
    @JsonProperty("direccion") private String direccion;
    @JsonProperty ("fecha") private Calendar fecha;
    @JsonProperty("nota") private String nota;
    @JsonProperty("sortFecha") private String sortFecha;
    private static Locacion[] locaciones;

    public Locacion()
    {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSortFecha() {
        return sortFecha;
    }

    public void setSortFecha(String sortFecha) {
        this.sortFecha = sortFecha;
    }

    public static void setLocaciones(Locacion[] locaciones) {
        Locacion.locaciones = locaciones;
    }

    public Locacion(Locacion[] locaciones)
    {
        this.locaciones = locaciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    /*private void convertFecha(String fechatxt)
    {
        String[] arrayFecha = fechatxt.split("-");
        this.fecha.set(Integer.parseInt(arrayFecha[0]),Integer.parseInt(arrayFecha[1]),Integer.parseInt(arrayFecha[2]));
    }*/
    public static Locacion[] getLocaciones()
    {

        return locaciones;
    }
}
