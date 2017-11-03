package com.apps.luma.elbondicervecerianomade.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jrepetto on 25/10/2017.
 */

public class Producto {
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("imgUrl")
    private String imgUrl;
    @JsonProperty("descripcion")
    private String descripcion;
    @JsonProperty("precio")
    private String precio;
    @JsonProperty("activo")
    private boolean activo;


    public Producto(String uid, String nombre, String imgUrl, String descripcion, String precio, boolean activo) {
        this.uid = uid;
        this.nombre = nombre;
        this.imgUrl = imgUrl;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
    }

    public Producto() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

}
