/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio4;

/**
 *
 * @author anamo
 */
public class Contraseña {
    static final int LONGITUD_MINIMA = 8;
    private String valor;
 
    public Contraseña(String contraseña, String confirmación) throws Exception {
        validar(contraseña, confirmación);
        this.valor = contraseña;
    }
 
    static void validar(String contraseña, String confirmación) throws Exception {
        if (contraseña == null || contraseña.isEmpty()) {
            throw new Exception("La contraseña no puede estar vacía.");
        }
        boolean minúscula = false, mayúscula = false, número = false, especial = false;
        for (int i = 0; i < contraseña.length(); i++) {
            char c = contraseña.charAt(i);
            if (Character.isWhitespace(c)) {
                throw new Exception("La contraseña no debe tener espacios en blanco.");
            } else if (Character.isLowerCase(c)) {
                minúscula = true;
            } else if (Character.isUpperCase(c)) {
                mayúscula = true;
            } else if (Character.isDigit(c)) {
                número = true;
            } else {
                especial = true;
            }
        }
        if (contraseña.length() < LONGITUD_MINIMA) {
            throw new Exception("La contraseña debe tener mínimo " + LONGITUD_MINIMA + " caracteres.");
        }
        if (!minúscula) {
            throw new Exception("La contraseña debe tener al menos una letra minúscula.");
        }
        if (!mayúscula) {
            throw new Exception("La contraseña debe tener al menos una letra mayúscula.");
        }
        if (!número) {
            throw new Exception("La contraseña debe tener al menos un número.");
        }
        if (!especial) {
            throw new Exception("La contraseña debe tener al menos un carácter especial.");
        }
        if (!contraseña.equals(confirmación)) {
            throw new Exception("Las contraseñas no son iguales.");
        }
    }

    public String getValor() {
        return valor;
    }
}