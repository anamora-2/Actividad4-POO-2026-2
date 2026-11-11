/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio5;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.*;

/**
 *
 * @author anamo
 */
public class Interfazlectura extends JFrame {
    private final JTextField campoNombre = new JTextField(30);
    private final JTextArea areaSalida = new JTextArea(18, 50);
 
    public Interfazlectura() {
        super("Ejercicio 6.8 - Lectura de archivos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
 
        JPanel filaNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaNombre.add(new JLabel("Nombre del archivo:"));
        filaNombre.add(campoNombre);
        JButton botonExaminar = new JButton("Examinar...");
        filaNombre.add(botonExaminar);
 
        JPanel filaBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botonLeerArchivo = new JButton("LeerArchivo (C:/prueba.txt)");
        JButton botonTeclado = new JButton("Leer archivo por nombre");
        JButton botonMayusculas = new JButton("Mostrar en MAYÚSCULAS");
        JButton botonLimpiar = new JButton("Limpiar");
        filaBotones.add(botonLeerArchivo);
        filaBotones.add(botonTeclado);
        filaBotones.add(botonMayusculas);
        filaBotones.add(botonLimpiar);
 
        JPanel norte = new JPanel(new GridLayout(2, 1));
        norte.add(filaNombre);
        norte.add(filaBotones);
        add(norte, BorderLayout.NORTH);

        areaSalida.setEditable(false);
        areaSalida.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        add(new JScrollPane(areaSalida), BorderLayout.CENTER);

        botonExaminar.addActionListener(e -> examinar());
        botonLeerArchivo.addActionListener(e -> mostrar(capturar(() -> {
            try {
                LeerArchivo.main(new String[0]);
            } catch (Exception ex) {
                System.out.println("No se pudo leer el archivo.");
            }
        })));
        botonTeclado.addActionListener(e -> {
            String nombre = nombreIngresado();
            if (nombre != null) {
                mostrar(capturar(() -> LeerArchivoTeclado.mostrarContenido(nombre)));
            }
        });
        botonMayusculas.addActionListener(e -> {
            String nombre = nombreIngresado();
            if (nombre != null) {
                mostrar(capturar(() -> LeerArchivoMayusculas.mostrarEnMayusculas(nombre)));
            }
        });
        botonLimpiar.addActionListener(e -> areaSalida.setText(""));
 
        pack();
        setLocationRelativeTo(null);
    }
 
    private void examinar() {
        JFileChooser selector = new JFileChooser();
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            campoNombre.setText(selector.getSelectedFile().getAbsolutePath());
        }
    }
 
    private String nombreIngresado() {
        String nombre = campoNombre.getText().trim();
        if (nombre.length() >= 2 && nombre.startsWith("\"") && nombre.endsWith("\"")) {
            nombre = nombre.substring(1, nombre.length() - 1); 
        }
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Escriba el nombre del archivo de texto.",
                    "Falta el nombre", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return nombre;
    }

    private String capturar(Runnable accion) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (PrintStream captura = new PrintStream(buffer, true, "UTF-8")) {
            System.setOut(captura);
            accion.run();
        } catch (UnsupportedEncodingException e) {
            return "Error de codificación.";
        } finally {
            System.setOut(original);
        }
        return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
    }

    private void mostrar(String texto) {
        areaSalida.setText(texto.isEmpty() ? "(El archivo está vacío)" : texto);
        areaSalida.setCaretPosition(0);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interfazlectura().setVisible(true));
    }
}
