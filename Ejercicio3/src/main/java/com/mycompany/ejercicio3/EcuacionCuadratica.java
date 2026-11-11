/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio3;

/**
 *
 * @author anamo
 */
public class EcuacionCuadratica {
    public static void calcularRaices(double a, double b, double c) {
        try {
            if (a == 0) {
                throw new ArithmeticException("el coeficiente a no puede ser cero");
            }
            double discriminante = b * b - 4 * a * c;
            if (discriminante < 0) {
                throw new ArithmeticException(
                        "no hay raices reales (discriminante = " + discriminante + ")");
            }
            double raiz = Math.sqrt(discriminante);
            double x1 = (-b + raiz) / (2 * a);
            double x2 = (-b - raiz) / (2 * a);
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        } catch (ArithmeticException e) {
            System.out.println("No se pueden calcular las raices: " + e.getMessage());
        }
    }
}
