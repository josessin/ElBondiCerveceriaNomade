package com.apps.luma.elbondicervecerianomade.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jrepetto on 25/10/2017.
 */

public class Producto {
    @JsonProperty("nombre") private String nombre;
    @JsonProperty("imgUrl") private int imgUrl;
    @JsonProperty("descripcion") private String descripcion;
    @JsonProperty("precio") private String precio;


    public Producto(String nombre, int imgUrl, String descripcion, String precio) {
        this.nombre = nombre;
        this.imgUrl = imgUrl;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto()
    {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
