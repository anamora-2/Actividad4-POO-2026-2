/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio1;

/**
 *
 * @author anamo
 */
public class PruebaExcepciones {
    public static void main(String args[]) {
        try {
            System.out.println("Ingresando al primer try");
            double cociente = 10000/0; 
            System.out.println("Después de la división"); 
        } catch (ArithmeticException e) { 
            System.out.println("División por cero");
        } finally {
            System.out.println("Ingresando al primer finally");
        }

        try {
            System.out.println("Ingresando al segundo try");
            Object objeto = null;
            objeto.toString();
            System.out.println("Imprimiendo objeto"); 
        } catch (ArithmeticException e) { 
            System.out.println("División por cero");
        } catch (Exception e) { 
            System.out.println("Ocurrió una excepción");
        } finally {
            System.out.println("Ingresando al segundo finally");
        }
    }
}
