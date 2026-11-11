/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio5;
import java.io.*;
/**
 *
 * @author anamo
 */
public class LeerArchivo {
    public static void main(String[] args) throws Exception {
        String nombreArchivo = "C:/prueba.txt";
        FileInputStream archivo; 
        InputStreamReader conversor; 
        BufferedReader filtro;
        String linea;
        try {
            archivo = new FileInputStream(nombreArchivo);
            conversor = new InputStreamReader(archivo);
            filtro = new BufferedReader(conversor);
            linea = filtro.readLine();
            while (linea != null) {
                System.out.println(linea); 
                linea = filtro.readLine(); 
            }
            filtro.close();
        }  catch (IOException e) { 
            System.out.println("No se pudo leer el archivo.");
        } 
    }
}
