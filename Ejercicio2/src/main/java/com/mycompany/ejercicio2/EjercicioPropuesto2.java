/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
/**
 *
 * @author anamo
 */
public class EjercicioPropuesto2 {
    private static final int MINIMO = 0;
    private static final int MAXIMO = 127;
    private static final String[] CONTROL = {
        "NUL", "SOH", "STX", "ETX", "EOT", "ENQ", "ACK", "BEL",
        "BS", "HT", "LF", "VT", "FF", "CR", "SO", "SI",
        "DLE", "DC1", "DC2", "DC3", "DC4", "NAK", "SYN", "ETB",
        "CAN", "EM", "SUB", "ESC", "FS", "GS", "RS", "US"};
 
    private final Map<String, Integer> simbolos = new LinkedHashMap<>();
    public EjercicioPropuesto2() {
        for (int i = 0; i < CONTROL.length; i++) {
            simbolos.put(CONTROL[i], i);
        }
        for (int i = 32; i <= 126; i++) {
            simbolos.put(String.valueOf((char) i), i);
        }
        simbolos.put("DEL", 127);
    }

    public int get(String simbolo) {
        validarSimbolo(simbolo);
        Integer valor = simbolos.get(simbolo);
        if (valor == null) {
            throw new NoSuchElementException("El símbolo \"" + simbolo + "\" no existe en la tabla.");
        }
        return valor;
    }
 
    public void set(String simbolo, int numero) {
        validarSimbolo(simbolo);
        if (numero < MINIMO || numero > MAXIMO) {
            throw new IllegalArgumentException("El número debe estar entre " + MINIMO + " y " + MAXIMO + ".");
        }
        simbolos.put(simbolo, numero);
    }

    public Map<String, Integer> getSimbolos() {
        return Collections.unmodifiableMap(simbolos);
    }
 
    private static void validarSimbolo(String simbolo) {
        if (simbolo == null) {
            throw new IllegalArgumentException("El símbolo no puede ser nulo.");
        }
        if (simbolo.isEmpty()) {
            throw new IllegalArgumentException("El símbolo no puede estar vacío.");
        }
    }
}