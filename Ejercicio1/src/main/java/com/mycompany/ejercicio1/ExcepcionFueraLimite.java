/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;

/**
 *
 * @author anamo
 */
public class ExcepcionFueraLimite {
    public static void main(String args[]) {
        try {
            String texto = "Programación";
            char caracter = texto.charAt(14);
            System.out.println(caracter);
        }
        catch(StringIndexOutOfBoundsException e) {
            System.out.println("Indice de string por fuera del límite");
        }
    }
}
