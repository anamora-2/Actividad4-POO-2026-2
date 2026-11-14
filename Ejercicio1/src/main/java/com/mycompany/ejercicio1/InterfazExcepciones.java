/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;
import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 *
 * @author anamo
 */
public class InterfazExcepciones extends JFrame {
    private JLabel etiquetaNumerador;
    private JLabel etiquetaDenominador;
    private JTextField campoNumerador;
    private JTextField campoDenominador;
    private JButton botonCalcular;
    private JTextArea areaSalida;
    private JScrollPane panelSalida;

    public InterfazExcepciones() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Excepciones");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(456, 370);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel contenedor = new JPanel(null);
        contenedor.setBackground(new Color(240, 240, 240));
        contenedor.setBorder(new LineBorder(new Color(224, 224, 250), 5));
        setContentPane(contenedor);

        etiquetaNumerador = new JLabel("numerador");
        etiquetaNumerador.setBounds(70, 45, 100, 25);

        campoNumerador = new JTextField();
        campoNumerador.setBounds(178, 47, 42, 22);

        etiquetaDenominador = new JLabel("denominador");
        etiquetaDenominador.setBounds(70, 78, 100, 25);

        campoDenominador = new JTextField("0");
        campoDenominador.setBounds(178, 80, 42, 22);

        botonCalcular = new JButton("calcular");
        botonCalcular.setBounds(103, 108, 62, 24);
        botonCalcular.setMargin(new java.awt.Insets(0, 0, 0, 0));
        botonCalcular.addActionListener(this::botonCalcularActionPerformed);
        campoDenominador.addActionListener(this::botonCalcularActionPerformed);

        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setLineWrap(true);
        areaSalida.setWrapStyleWord(true);
        areaSalida.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));

        panelSalida = new JScrollPane(areaSalida);
        panelSalida.setBounds(69, 141, 300, 160);

        contenedor.add(etiquetaNumerador);
        contenedor.add(campoNumerador);
        contenedor.add(etiquetaDenominador);
        contenedor.add(campoDenominador);
        contenedor.add(botonCalcular);
        contenedor.add(panelSalida);
    }

    private void botonCalcularActionPerformed(java.awt.event.ActionEvent evt) {
        PrintStream consolaOriginal = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, "UTF-8"));

            System.out.println("== Numerador / denominador ==");
            calcularValores();

            System.out.println("\n== PruebaExcepciones ==");
            PruebaExcepciones.main(new String[0]);

            System.out.println("\n== ExcepciónFueraLímite ==");
            ExcepcionFueraLimite.main(new String[0]);

            System.out.println("\n== ExcepciónFormatoNúmero ==");
            ExcepcionFormatoNumero.main(new String[0]);
        } catch (UnsupportedEncodingException e) {
        } finally {
            System.out.flush();
            System.setOut(consolaOriginal); 
        }

        String texto = new String(buffer.toByteArray(), StandardCharsets.UTF_8);
        areaSalida.setText(texto);
        areaSalida.setCaretPosition(0);
        mostrarAlertas(texto);
    }

    private void calcularValores() {
        try {
            System.out.println("Ingresando al try");
            int numerador = Integer.parseInt(campoNumerador.getText().trim());
            int denominador = Integer.parseInt(campoDenominador.getText().trim());
            int cociente = numerador / denominador; 
            System.out.println("Cociente = " + cociente);
        } catch (NumberFormatException e) {
            System.out.println("Excepción de formato de número");
        } catch (ArithmeticException e) {
            System.out.println("División por cero");
        } finally {
            System.out.println("Ingresando al finally");
        }
    }

    private void mostrarAlertas(String texto) {
        for (String linea : texto.split("\\R")) {
            String mensaje = linea.trim();
            switch (mensaje) {
                case "División por cero":
                    alerta(mensaje, "Excepción aritmética", JOptionPane.ERROR_MESSAGE);
                    break;
                case "Excepción de formato de número":
                    alerta(mensaje, "Excepción de formato de número", JOptionPane.ERROR_MESSAGE);
                    break;
                case "Ocurrió una excepción":
                    alerta(mensaje, "Excepción general", JOptionPane.WARNING_MESSAGE);
                    break;
                case "Indice de string por fuera del límite":
                    alerta(mensaje, "Excepción de índice", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    break;
            }
        }
    }

    private void alerta(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new InterfazExcepciones().setVisible(true));
    }
}
