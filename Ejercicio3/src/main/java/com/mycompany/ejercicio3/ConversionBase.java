/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio3;

/**
 *
 * @author anamo
 */
public class ConversionBase {
    private static final String DIGITOS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static void convertirDeBase10(int numero, int base) {
        try {
            if (base == 0) {
                throw new ArithmeticException("la base no puede ser cero");
            }
            if (base < 2 || base > 36) {
                throw new IllegalArgumentException("la base debe estar entre 2 y 36");
            }
            long n = Math.abs((long) numero);
            StringBuilder resultado = new StringBuilder();
            do {
                resultado.append(DIGITOS.charAt((int) (n % base)));
                n = n / base;
            } while (n > 0);
            if (numero < 0) {
                resultado.append('-');
            }
            System.out.println(numero + " en base " + base + " = " + resultado.reverse());
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println("No se puede convertir: " + e.getMessage());
        }
    }
}
