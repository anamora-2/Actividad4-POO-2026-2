/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio3;

/**
 *
 * @author anamo
 */
public class PuntoMedioRecta {
    public static void calcularPuntoMedio(double x1, double y1, double x2, double y2) {
        try {
            double mx = (x1 + x2) / 2;
            double my = (y1 + y2) / 2;
            if (Double.isNaN(mx) || Double.isInfinite(mx)
                    || Double.isNaN(my) || Double.isInfinite(my)) {
                throw new ArithmeticException("Los valores deben ser numeros finitos");
            }
            System.out.println("Punto medio = (" + mx + ", " + my + ")");
        } catch (ArithmeticException e) {
            System.out.println("No se puede calcular el punto medio: " + e.getMessage());
        }
    }
}