/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio2;
import java.util.*;

/**
 *
 * @author anamo
 */
public class Vendedor {
    String nombre; 
    String apellidos; 
    int edad; 
    Vendedor(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    void imprimir() {
        System.out.println("Nombre del vendedor = " + nombre);
        System.out.println("Apellidos del vendedor = " + apellidos);
        System.out.println("Edad del vendedor = " + edad);
    }
    
    void verificarEdad(int edad) {
        if (edad < 18) { 
            throw new IllegalArgumentException("El vendedor debe ser mayor de 18 años.");
        } if (edad >= 0 && edad < 120) { 
            this.edad = edad;
        } else throw new IllegalArgumentException("La edad no puede ser negativa ni mayor a 120.");
    }
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Nombre del vendedor = ");
        String n = teclado.next();
        System.out.println("Apellidos del vendedor = ");
        String a = teclado.next();
        Vendedor vendedor = new Vendedor(n, a);
        System.out.println("Edad del vendedor = ");
        int e = teclado.nextInt();
        vendedor.verificarEdad(e);
        vendedor.imprimir();
    }  
}