/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio4;
import java.util.*;

/**
 *
 * @author anamo
 */
public class EquipoMaratonProgramacion {
    String nombreEquipo;
    String universidad;
    String lenguajeProgramacion;
    Programador[] programadores;
    int tamañoEquipo;
   
    EquipoMaratonProgramacion(String nombreEquipo, String universidad, String lenguajeProgramacion) {
        this.nombreEquipo = nombreEquipo;
        this.universidad = this.universidad;
        this.lenguajeProgramacion = this.lenguajeProgramacion;
        this.programadores = this.programadores;
        tamañoEquipo = 0; 
        programadores = new Programador[3]; 
    }

    boolean estaLleno() {
    return tamañoEquipo == programadores.length;
    }
   
    void añadir(Programador programador) throws Exception {
        if (estaLleno()) { 
            throw new Exception ("El equipo está completo. No se pudo agregar programador.");
        }
        programadores[tamañoEquipo] = programador;
        tamañoEquipo++; 
    }

    static void validarCampo(String campo) throws Exception {
        for (int j = 0; j < campo.length(); j++) {
            char c = campo.charAt(j);
            if (Character.isDigit(c)) { 
                throw new Exception("El nombre no puede tener dígitos.");
            }
        }

        if (campo.length() > 20) {
            throw new Exception("La longitud no debe ser superior a 20 caracteres.");
        }
    }
    public static void main(String args[]) throws Exception {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Nombre del equipo = ");
        String nombre = teclado.next();
        System.out.println("Universidad = ");
        String universidad = teclado.next();
        System.out.println("Lenguaje de programación = ");
        String lenguaje = teclado.next();
        EquipoMaratonProgramacion equipo = new
            EquipoMaratonProgramacion(nombre, universidad,
            lenguaje);
        System.out.println("Datos de los integrantes del equipo");
        String nombreProgramador;
        String apellidosProgramador;
        for (int i= 0; i < 3; i++) {
            System.out.println("Nombre del integrante = ");
            nombreProgramador = teclado.next();
            validarCampo(nombreProgramador);
            System.out.println("Apellidos del integrante = ");
            apellidosProgramador = teclado.next();
            validarCampo(apellidosProgramador); 
            Programador programador = new
                Programador(nombreProgramador,
                apellidosProgramador); 
            equipo.añadir(programador); 
        }
    }
}
