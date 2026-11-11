/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio3;

/**
 *
 * @author anamo
 */
public class PendienteRecta {
    public static void calcularPendiente(double x1, double y1, double x2, double y2) {
        try {
            if (Double.isNaN(x1 + y1 + x2 + y2) || Double.isInfinite(x1 + y1 + x2 + y2)) {
                throw new ArithmeticException("Los valores deben ser numeros finitos");
            }
            if (x2 - x1 == 0) {
                throw new ArithmeticException("La recta es vertical (x1 = x2)");
            }
            double pendiente = (y2 - y1) / (x2 - x1);
            System.out.println("Pendiente = " + pendiente);
        } catch (ArithmeticException e) {
            System.out.println("No se puede calcular la pendiente: " + e.getMessage());
        }
    }
}
