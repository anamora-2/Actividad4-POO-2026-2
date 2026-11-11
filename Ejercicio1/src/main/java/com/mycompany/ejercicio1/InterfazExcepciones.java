/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
/**
 *
 * @author anamo
 */
public class InterfazExcepciones extends JFrame {
    private interface Ejecutable { void ejecutar(); }
 
    private final JTextArea[] salidas = new JTextArea[3];
    private final Ejecutable[] acciones = new Ejecutable[3];
 
    public InterfazExcepciones() {
        super("Ejercicio 6.4. Excepciones");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        acciones[0] = () -> PruebaExcepciones.main(new String[0]);
        acciones[1] = () -> ExcepcionFueraLimite.main(new String[0]);
        acciones[2] = () -> ExcepcionFormatoNumero.main(new String[0]);
 
        String[] titulos = {
            "PruebaExcepciones",
            "ExcepciónFueraLímite (propuesto 1)",
            "ExcepciónFormatoNúmero (propuesto 2)"
        };
        String[] descripciones = {
            "Dos bloques try: división por cero (ArithmeticException) y "
                + "objeto nulo (Exception), cada uno con su finally.",
            "charAt(14) sobre \"Programación\" lanza "
                + "StringIndexOutOfBoundsException.",
            "Integer.parseInt(\"Número\") lanza NumberFormatException; "
                + "el finally se ejecuta siempre."
        };
 
        JPanel centro = new JPanel(new GridLayout(1, 3, 10, 0));
        centro.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        for (int i = 0; i < 3; i++) {
            centro.add(crearPanel(i, titulos[i], descripciones[i]));
        }
 
        JButton btnTodas = new JButton("Ejecutar todas");
        btnTodas.addActionListener(e -> {
            for (int i = 0; i < 3; i++) ejecutar(i);
        });
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> {
            for (JTextArea a : salidas) a.setText("");
        });
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        sur.add(btnTodas);
        sur.add(btnLimpiar);
 
        add(centro, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);
        setSize(1050, 380);
        setLocationRelativeTo(null);
    }
 
    private JPanel crearPanel(int indice, String titulo, String descripcion) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), titulo,
            TitledBorder.LEFT, TitledBorder.TOP));
 
        JTextArea desc = new JTextArea(descripcion);
        desc.setEditable(false);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setOpaque(false);
        desc.setRows(3);
 
        JTextArea salida = new JTextArea();
        salida.setEditable(false);
        salida.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        salidas[indice] = salida;
 
        JButton btn = new JButton("Ejecutar main");
        btn.addActionListener(e -> ejecutar(indice));
 
        panel.add(desc, BorderLayout.NORTH);
        panel.add(new JScrollPane(salida), BorderLayout.CENTER);
        panel.add(btn, BorderLayout.SOUTH);
        return panel;
    }
 
    private void ejecutar(int indice) {
        JTextArea area = salidas[indice];
        area.setText("");
        PrintStream original = System.out;
        try {
            OutputStream flujo = new OutputStream() {
                private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                @Override public void write(int b) {
                    buffer.write(b);
                }
                @Override public void flush() {
                    try {
                        area.append(buffer.toString("UTF-8"));
                    } catch (UnsupportedEncodingException ex) {
                        area.append(buffer.toString());
                    }
                    buffer.reset();
                }
            };
            System.setOut(new PrintStream(flujo, true, "UTF-8"));
            acciones[indice].ejecutar();
            System.out.flush();
        } catch (UnsupportedEncodingException ex) {
            area.setText("Error de codificación: " + ex.getMessage());
        } finally {
            System.setOut(original);
        }
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazExcepciones().setVisible(true));
    }
}
