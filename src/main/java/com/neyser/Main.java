package com.neyser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Venta venta1 = new Venta();

        venta1.crearDirectorioVenta();
        venta1.crearDirectorioProducto();
        venta1.menu();

    }
}