/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio3;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.*;
/**
 *
 * @author anamo
 */
public class InterfazCalculos extends JFrame {
    private final JTextArea salida = new JTextArea(9, 60);

    private final JTextField txtValor = new JTextField(10);
    private final JTextField[] recta = new JTextField[4];   
    private final JTextField[] medio = new JTextField[4];   
    private final JTextField[] cuad = new JTextField[3];    
    private final JTextField txtNumero = new JTextField(10);
    private final JTextField txtBase = new JTextField(10);

    public InterfazCalculos() {
        super("Ejercicio 6.6 - Catchs múltiples");
        redirigirSalida();

        JPanel secciones = new JPanel(new GridLayout(3, 2, 8, 8));
        secciones.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));
        secciones.add(panelCalculosNumericos());
        secciones.add(panelPendiente());
        secciones.add(panelPuntoMedio());
        secciones.add(panelCuadratica());
        secciones.add(panelConversion());
        secciones.add(new JPanel());

        salida.setEditable(false);
        salida.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(salida);
        JButton limpiar = new JButton("Limpiar resultados");
        limpiar.addActionListener(e -> salida.setText(""));
        JPanel inferior = new JPanel(new BorderLayout(4, 4));
        inferior.setBorder(BorderFactory.createTitledBorder("Resultados"));
        inferior.add(scroll, BorderLayout.CENTER);
        inferior.add(limpiar, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(secciones, BorderLayout.CENTER);
        add(inferior, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel panelCalculosNumericos() {
        JPanel p = nuevoPanel("CalculosNumericos");
        p.add(new JLabel("Valor numerico:"));
        p.add(txtValor);
        JButton log = new JButton("Logaritmo neperiano");
        JButton raiz = new JButton("Raiz cuadrada");
        JButton ambos = new JButton("Ambos");
        log.addActionListener(e -> ejecutar(() ->
                CalculosNumericos.calcularLogaritmoNeperiano(leerDouble(txtValor))));
        raiz.addActionListener(e -> ejecutar(() ->
                CalculosNumericos.calcularRaizCuadrada(leerDouble(txtValor))));
        ambos.addActionListener(e -> ejecutar(() -> {
            double v = leerDouble(txtValor);
            CalculosNumericos.calcularLogaritmoNeperiano(v);
            CalculosNumericos.calcularRaizCuadrada(v);
        }));
        p.add(log);
        p.add(raiz);
        p.add(ambos);
        return p;
    }

    private JPanel panelPendiente() {
        JPanel p = nuevoPanel("Pendiente de una recta");
        agregarCampos(p, recta, "x1", "y1", "x2", "y2");
        JButton b = new JButton("Calcular pendiente");
        b.addActionListener(e -> ejecutar(() ->
                PendienteRecta.calcularPendiente(leerDouble(recta[0]), leerDouble(recta[1]),
                        leerDouble(recta[2]), leerDouble(recta[3]))));
        p.add(b);
        return p;
    }

    private JPanel panelPuntoMedio() {
        JPanel p = nuevoPanel("Punto medio de una recta");
        agregarCampos(p, medio, "x1", "y1", "x2", "y2");
        JButton b = new JButton("Calcular punto medio");
        b.addActionListener(e -> ejecutar(() ->
                PuntoMedioRecta.calcularPuntoMedio(leerDouble(medio[0]), leerDouble(medio[1]),
                        leerDouble(medio[2]), leerDouble(medio[3]))));
        p.add(b);
        return p;
    }

    private JPanel panelCuadratica() {
        JPanel p = nuevoPanel("Ecuacion cuadratica  ax\u00B2 + bx + c = 0");
        agregarCampos(p, cuad, "a", "b", "c");
        JButton b = new JButton("Calcular raices");
        b.addActionListener(e -> ejecutar(() ->
                EcuacionCuadratica.calcularRaices(leerDouble(cuad[0]), leerDouble(cuad[1]),
                        leerDouble(cuad[2]))));
        p.add(b);
        return p;
    }

    private JPanel panelConversion() {
        JPanel p = nuevoPanel("Conversion de base 10 a base b");
        p.add(new JLabel("Numero (base 10):"));
        p.add(txtNumero);
        p.add(new JLabel("Base b:"));
        p.add(txtBase);
        JButton b = new JButton("Convertir");
        b.addActionListener(e -> ejecutar(() ->
                ConversionBase.convertirDeBase10(leerEntero(txtNumero), leerEntero(txtBase))));
        p.add(b);
        return p;
    }

    private static JPanel nuevoPanel(String titulo) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        p.setBorder(BorderFactory.createTitledBorder(titulo));
        return p;
    }

    private static void agregarCampos(JPanel p, JTextField[] campos, String... etiquetas) {
        for (int i = 0; i < campos.length; i++) {
            campos[i] = new JTextField(5);
            p.add(new JLabel(etiquetas[i] + ":"));
            p.add(campos[i]);
        }
    }

    private void ejecutar(Runnable accion) {
        try {
            accion.run();
        } catch (InputMismatchException e) {
            System.out.println("El valor ingresado debe ser numerico");
        } catch (NoSuchElementException e) {
            System.out.println("Hay campos vacios: complete todos los valores");
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.flush();
        }
    }

    private static double leerDouble(JTextField campo) {
        Scanner sc = new Scanner(campo.getText().trim().replace(',', '.'));
        sc.useLocale(Locale.US);
        double valor = sc.nextDouble();       
        if (sc.hasNext()) {
            throw new InputMismatchException("Texto adicional despues del numero");
        }
        return valor;
    }

    private static int leerEntero(JTextField campo) {
        Scanner sc = new Scanner(campo.getText().trim());
        int valor = sc.nextInt();             
        if (sc.hasNext()) {
            throw new InputMismatchException("Texto adicional despues del numero");
        }
        return valor;
    }

    private void redirigirSalida() {
        OutputStream os = new OutputStream() {
            private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            @Override public void write(int b) { buffer.write(b); }
            @Override public void write(byte[] b, int off, int len) { buffer.write(b, off, len); }
            @Override public void flush() throws IOException {
                final String texto = new String(buffer.toByteArray(), StandardCharsets.UTF_8);
                buffer.reset();
                if (!texto.isEmpty()) {
                    SwingUtilities.invokeLater(() -> {
                        salida.append(texto);
                        salida.setCaretPosition(salida.getDocument().getLength());
                    });
                }
            }
        };
        try {
            System.setOut(new PrintStream(os, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazCalculos().setVisible(true));
    }
}
