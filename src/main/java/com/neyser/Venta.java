package com.neyser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Venta {

    static Scanner sc1 = new Scanner(System.in);
    String cliente;
    Producto producto;
    String destino;

    public void menu() throws IOException {
        System.out.println("________________________________");
        System.out.println("|         MENU PRINCIPAL        |");
        System.out.println("|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|");
        System.out.println(
                "| 1. Registrar un producto\t\t|\n" +
                "| 2. Registrar una Venta\t\t|\n" +
                "| 0. Cerrar Programa\t\t\t|");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

        String opcion = sc1.nextLine();

        switch (opcion)
        {
            case "1":
                registrarProducto();
                break;
            case "2":
                registrarVenta();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Debe seleccionar una opción válida");

        }
    }

    public void crearDirectorioVenta() throws IOException
    {
        File dir1 = new File("Transacciones");

        if (dir1.mkdir())
        {
            System.out.println("El directorio SI ha sido creado");
        } else
        {
            System.out.println("El directorio NO ha sido creado");
        }

    }
    public void crearDirectorioProducto() throws IOException
    {
        File dir1 = new File("Productos");

        if (dir1.mkdir())
        {
            System.out.println("El directorio SI ha sido creado");
        } else
        {
            System.out.println("El directorio NO ha sido creado");
        }

    }

    public void registrarVenta() throws IOException
    {
        String fichero = "001";

        File file1 = new File("Transacciones/"+fichero);

        try
        {
            if (file1.createNewFile())
            {
                System.out.println("El fichero ha sido creado");
            } else
            {
                System.out.println("El fichero NO ha sido creado");
            }
        } catch (IOException e)
        {
            System.out.println("Error, no se pudo crear el fichero "+e.getMessage());
            e.printStackTrace();
        }

    }

    public void registrarProducto() throws IOException {

        Producto producto = new Producto();

        System.out.println("Escriba el nombre del producto");
        producto.setDenominacion(sc1.nextLine());

        System.out.println("Escriba el precio de: "+producto.getDenominacion());
        producto.setPrecio(Double.parseDouble(sc1.nextLine()));

        System.out.println("Escriba la cantidad de: "+producto.getDenominacion());
        producto.setDisponibilidad(Integer.parseInt(sc1.nextLine()));

        File file1 = new File("Productos/"+producto.getDenominacion()+".txt");

        System.out.println(producto.getDisponibilidad());

        try
        {
            if (file1.createNewFile())
            {
                System.out.println("El fichero ha sido creado");
            } else
            {
                System.out.println("El fichero NO ha sido creado");
            }
        } catch (IOException e)
        {
            System.out.println("Error, no se pudo crear el fichero "+e.getMessage());
            e.printStackTrace();
        }

        // Escribir en fichero
        FileWriter fileWriter1 = new FileWriter(file1, false);
        BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);

        bufferedWriter1.write(producto.getDenominacion()+"|");
        bufferedWriter1.write(producto.getPrecio()+"|");
        bufferedWriter1.write(producto.getDisponibilidad().toString());

        bufferedWriter1.close();
        System.out.println("Se ha escrito en el fichero");

        //sc1.close();

        menu();


    }

}
