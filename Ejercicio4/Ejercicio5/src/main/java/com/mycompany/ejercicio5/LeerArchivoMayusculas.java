/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio5;
import java.io.*;
/**
 *
 * @author anamo
 */
public class LeerArchivoMayusculas {
    public static void main(String[] args) throws IOException {
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);
        System.out.print("Ingrese el nombre del archivo: ");
        String nombreArchivo = teclado.readLine();
        mostrarEnMayusculas(nombreArchivo);
    }
 
    public static void mostrarEnMayusculas(String nombreArchivo) {
        try {
            FileInputStream archivo = new FileInputStream(nombreArchivo);
            InputStreamReader conversor = new InputStreamReader(archivo);
            BufferedReader filtro = new BufferedReader(conversor);
            String linea = filtro.readLine();
            while (linea != null) {
                System.out.println(linea.toUpperCase());
                linea = filtro.readLine();
            }
            filtro.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo.");
            System.out.println("Motivo: " + e.getMessage());
            System.out.println("Ruta buscada: " + new File(nombreArchivo).getAbsolutePath());
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo.");
            System.out.println("Motivo: " + e.getMessage());
        }
    }
}
