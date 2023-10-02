package com.neyser;

public class Producto {
    private String denominacion;
    private Double precio;
    private Integer disponibilidad;

    public Producto() {
    }



    public Producto(String denominacion, Double precio, Integer disponibilidad) {
        this.denominacion = denominacion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
