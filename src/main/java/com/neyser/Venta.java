package com.neyser;

import java.io.*;
import java.util.Arrays;
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
                        "| 3. Mostrar productos\t\t\t|\n" +
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
            case "3":
                mostrarProductos();
                break;
            case "0":
                sc1.close();
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

    public void mostrarProductos() throws IOException {
        File file2 = new File("Productos");
        String[] pathnames;
        pathnames = file2.list();

        System.out.println("______________________________");
        System.out.println("           PRODUCTOS         ");
        System.out.println("______________________________");

        System.out.println("Nombre\t\tPrecio\tCantidad\t");

        for (String pathname: pathnames) {
            File file1 = new File(pathname);

            String prod = file1.getName().substring(0,file1.getName().length()-4);
            System.out.println(leerProducto(prod));
        }

        System.out.println("______________________________");

        System.out.println("Ingresar el producto a comprar:");
        String productoCompra = sc1.nextLine();

        System.out.println("Ingresar la cantidad a comprar:");
        Integer cantCompra = Integer.parseInt(sc1.nextLine());

        if (obtenerCantidadProducto(productoCompra)-cantCompra<0)
        {
            System.out.println("La cantidad debe ser menor a "+cantCompra);
        } else
        {
            actualizarProducto(productoCompra, obtenerPrecioProducto(productoCompra),obtenerCantidadProducto(productoCompra)-cantCompra);
        }




        System.out.println("Cantidad de "+ productoCompra +" = " + obtenerCantidadProducto(productoCompra));

    }

    public String leerProducto(String producto) throws IOException {

        File file1 = new File("Productos/"+producto+".txt");
        file1.createNewFile();

        String resultado = "";

        try
        {
            FileReader fileReader1 = new FileReader(file1);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            String strCurrentLine;

            while ((strCurrentLine=bufferedReader1.readLine()) != null)
            {
                String[] parts = strCurrentLine.split("\\|");

                if (Integer.parseInt(parts[2])!=0)
                {
                    resultado+=parts[0];
                    resultado+="\t\t";
                    resultado+=parts[1];
                    resultado+="\t";
                    resultado+=parts[2];
                }

            }

            if (fileReader1 != null)
            {
                fileReader1.close();
            }

        } catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        } catch (IOException e2)
        {
            e2.printStackTrace();
        }

        return resultado;

    }
    public void comprar(String producto)
    {
        System.out.println();
    }
    public Integer obtenerCantidadProducto(String producto)
    {
        Integer resultado = 0;

        try
        {
            File file1 = new File("Productos/"+producto+".txt");
            FileReader fileReader1 = new FileReader(file1);

            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);

            String strCurrentLine;

            while ((strCurrentLine=bufferedReader1.readLine()) != null)
            {
                //System.out.println(strCurrentLine);

                String[] parts = strCurrentLine.split("\\|");

                if (Integer.parseInt(parts[2])!=0)
                {
                    resultado=Integer.parseInt(parts[2]);
                }


            }

            if (fileReader1 != null)
            {
                fileReader1.close();
            }

        } catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        } catch (IOException e2)
        {
            e2.printStackTrace();
        }

        return resultado;

    }
    public Double obtenerPrecioProducto(String producto)
    {
        Double resultado = 0.0;

        try
        {
            File file1 = new File("Productos/"+producto+".txt");
            FileReader fileReader1 = new FileReader(file1);

            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);

            String strCurrentLine;

            while ((strCurrentLine=bufferedReader1.readLine()) != null)
            {
                //System.out.println(strCurrentLine);

                String[] parts = strCurrentLine.split("\\|");

                if (Integer.parseInt(parts[2])!=0)
                {
                    resultado=Double.parseDouble(parts[1]);
                }


            }

            if (fileReader1 != null)
            {
                fileReader1.close();
            }

        } catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        } catch (IOException e2)
        {
            e2.printStackTrace();
        }

        return resultado;

    }

    public void actualizarProducto(String prod, Double precio, Integer cantidad) throws IOException {
        Producto producto = new Producto();
        producto.setDenominacion(prod);
        producto.setPrecio(precio);
        producto.setDisponibilidad(cantidad);

        File file1 = new File("Productos/"+prod+".txt");

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
