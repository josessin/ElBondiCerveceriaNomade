package com.apps.luma.elbondicervecerianomade.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jrepetto on 25/10/2017.
 */

public class Producto {
    @JsonProperty("nombre") private String nombre;
    @JsonProperty("imgUrl") private String imgUrl;
    @JsonProperty("descripcion") private String descripcion;
    @JsonProperty("precio") private String precio;
    private static Producto[] productos;
    public Producto()
    {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
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

    public static Producto[] getProductos()
    {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

}
