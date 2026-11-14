/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;

/**
 *
 * @author anamo
 */
public class ExcepcionFormatoNumero {
    public static void main(String args[]) {
        try {
            int número = Integer.parseInt("Número");
            System.out.println(número);
        } catch(NumberFormatException e) {
            System.out.println("Excepción de formato de número");
        } finally {
            System.out.println("Ingresando al finally");
        }
    }
}
