/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2;
/**
 *
 * @author anamo
 */
public class EjercicioPropuesto1 {
    private final Vendedor vendedor;
    
    public EjercicioPropuesto1(String nombre, String apellidos, int edad)
            throws IllegalArgumentException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío.");
        }
        if (apellidos == null || apellidos.trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos no pueden ser nulos ni vacíos.");
        }
        Vendedor v = new Vendedor(nombre.trim(), apellidos.trim());
        v.verificarEdad(edad); 
        this.vendedor = v;
    }
    
    public void imprimir() {
        vendedor.imprimir();
    }
}
