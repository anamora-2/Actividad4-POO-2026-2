/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.NoSuchElementException;
/**
 *
 * @author anamo
 */
public class Interfaz extends JFrame {
    private static final Color COLOR_ERROR = new Color(176, 0, 32);
    private static final Color COLOR_OK = new Color(0, 120, 60);
 
    private final EjercicioPropuesto2 tabla = new EjercicioPropuesto2();
    private final DefaultTableModel modeloTabla = new DefaultTableModel(new String[]{"Símbolo", "Valor"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private int intentos = 0; 
    
    public Interfaz() {
        super("Ejercicio 6.5. Lanzamiento de excepciones");
        JPanel izquierda = new JPanel(new GridLayout(2, 1, 0, 8));
        izquierda.add(crearSeccionVendedor());
        izquierda.add(crearSeccionConstructor());
        JPanel contenido = new JPanel(new GridLayout(1, 2, 8, 0));
        contenido.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        contenido.add(izquierda);
        contenido.add(crearSeccionASCII());
        add(contenido);
        refrescarTabla();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 720);
        setLocationRelativeTo(null);
    }
 
    private JPanel crearSeccionVendedor() {
        JTextField txtNombre = new JTextField(20);
        JTextField txtApellidos = new JTextField(20);
        JTextField txtEdad = new JTextField(6);
        JLabel estado = new JLabel(" ");
        JTextArea salida = nuevaAreaSalida();
        JButton btnVerificar = new JButton("Verificar edad y mostrar vendedor");
        JButton btnLimpiar = new JButton("Limpiar");
 
        btnVerificar.addActionListener(e -> {
            try {
                int edad = Integer.parseInt(txtEdad.getText().trim()); 
                Vendedor vendedor = new Vendedor(txtNombre.getText().trim(), txtApellidos.getText().trim());
                vendedor.verificarEdad(edad); 
                salida.setText(capturarSalida(vendedor::imprimir));
                mostrarEstado(estado, "Vendedor válido: no se lanzó ninguna excepción.", true);
            } catch (NumberFormatException ex) {
                salida.setText("");
                mostrarEstado(estado, "NumberFormatException: la edad debe ser un número entero.", false);
            } catch (IllegalArgumentException ex) {
                salida.setText("");
                mostrarEstado(estado, "IllegalArgumentException: " + ex.getMessage(), false);
            }
        });
        btnLimpiar.addActionListener(e -> {
            txtNombre.setText("");
            txtApellidos.setText("");
            txtEdad.setText("");
            salida.setText("");
            estado.setText(" ");
        });
 
        return armarSeccion("1. Clase Vendedor", "Edad válida: entre 18 y 119.",
                new String[]{"Nombre:", "Apellidos:", "Edad:"},
                new JComponent[]{txtNombre, txtApellidos, txtEdad},
                new JButton[]{btnVerificar, btnLimpiar}, estado, new JScrollPane(salida));
    }
 
    private JPanel crearSeccionConstructor() {
        JTextField txtNombre = new JTextField(20);
        JTextField txtApellidos = new JTextField(20);
        JTextField txtEdad = new JTextField(6);
        JLabel estado = new JLabel(" ");
        JTextArea registro = nuevaAreaSalida();
        JButton btnCrear = new JButton("Intentar crear el objeto");
        JButton btnLimpiar = new JButton("Limpiar registro");
 
        btnCrear.addActionListener(e -> {
            intentos++;
            registro.append("Intento " + intentos + ":\n");
            try {
                int edad = Integer.parseInt(txtEdad.getText().trim());
                EjercicioPropuesto1 vs = new EjercicioPropuesto1(txtNombre.getText(), txtApellidos.getText(), edad);
                registro.append("  [OK] Objeto creado.\n");
                for (String linea : capturarSalida(vs::imprimir).split("\\R")) {
                    registro.append("       " + linea + "\n");
                }
                mostrarEstado(estado, "Objeto creado correctamente.", true);
            } catch (NumberFormatException ex) {
                registro.append("  [ERROR] NumberFormatException: la edad debe ser un número entero.\n");
                mostrarEstado(estado, "NumberFormatException: la edad debe ser un número entero.", false);
            } catch (IllegalArgumentException ex) {
                registro.append("  [ERROR] IllegalArgumentException: " + ex.getMessage() + "\n");
                mostrarEstado(estado, "IllegalArgumentException: " + ex.getMessage(), false);
            } finally {
                registro.append("  (fin del intento " + intentos + ")\n\n");
                registro.setCaretPosition(registro.getDocument().getLength());
            }
        });
        btnLimpiar.addActionListener(e -> {
            registro.setText("");
            estado.setText(" ");
            intentos = 0;
        });
 
        return armarSeccion("2. Propuesto 1: constructor que lanza excepción", "Propuesto 2 valida los datos en su constructor.",
                new String[]{"Nombre:", "Apellidos:", "Edad:"},
                new JComponent[]{txtNombre, txtApellidos, txtEdad},
                new JButton[]{btnCrear, btnLimpiar}, estado, new JScrollPane(registro));
    }

    private JPanel crearSeccionASCII() {
        JTextField txtSimbolo = new JTextField(8);
        JTextField txtNumero = new JTextField(6);
        JCheckBox chkNulo = new JCheckBox("Pasar el símbolo como null");
        JLabel estado = new JLabel(" ");
        JButton btnGet = new JButton("get(símbolo)");
        JButton btnSet = new JButton("set(símbolo, número)");
 
        JTable jtabla = new JTable(modeloTabla);
        jtabla.setFillsViewportHeight(true);
 
        btnGet.addActionListener(e -> {
            String simbolo = chkNulo.isSelected() ? null : txtSimbolo.getText();
            try {
                int numero = tabla.get(simbolo);
                mostrarEstado(estado, "get(" + descripcion(simbolo) + ") = " + numero, true);
            } catch (IllegalArgumentException ex) {
                mostrarEstado(estado, "IllegalArgumentException: " + ex.getMessage(), false);
            } catch (NoSuchElementException ex) {
                mostrarEstado(estado, "NoSuchElementException: " + ex.getMessage(), false);
            }
        });
        btnSet.addActionListener(e -> {
            String simbolo = chkNulo.isSelected() ? null : txtSimbolo.getText();
            try {
                int numero = Integer.parseInt(txtNumero.getText().trim());
                tabla.set(simbolo, numero);
                refrescarTabla();
                mostrarEstado(estado, "set(" + descripcion(simbolo) + ", " + numero + ") realizado.", true);
            } catch (NumberFormatException ex) {
                mostrarEstado(estado, "NumberFormatException: el número debe ser un entero.", false);
            } catch (IllegalArgumentException ex) {
                mostrarEstado(estado, "IllegalArgumentException: " + ex.getMessage(), false);
            }
        });
 
        return armarSeccion("3. Propuesto 2: tabla ASCII", "Un espacio en blanco también es un símbolo válido (32).",
                new String[]{"Símbolo:", "Número:", ""},
                new JComponent[]{txtSimbolo, txtNumero, chkNulo},
                new JButton[]{btnGet, btnSet}, estado, new JScrollPane(jtabla));
    }
 
    private static JPanel armarSeccion(String titulo, String descripcion, String[] etiquetas, JComponent[] campos,
                                       JButton[] botones, JLabel estado, JComponent centro) {
        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 4, 3, 4);
        c.anchor = GridBagConstraints.WEST;
        for (int i = 0; i < etiquetas.length; i++) {
            c.gridy = i;
            c.gridx = 0;
            c.weightx = 0;
            c.fill = GridBagConstraints.NONE;
            formulario.add(new JLabel(etiquetas[i]), c);
            c.gridx = 1;
            c.weightx = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            formulario.add(campos[i], c);
        }
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (JButton b : botones) {
            panelBotones.add(b);
        }
        estado.setBorder(BorderFactory.createEmptyBorder(2, 8, 6, 8));
 
        JPanel norte = new JPanel();
        norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
        JLabel lblDescripcion = new JLabel(descripcion);
        lblDescripcion.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));
        for (JComponent comp : new JComponent[]{lblDescripcion, formulario, panelBotones, estado}) {
            comp.setAlignmentX(Component.LEFT_ALIGNMENT);
            norte.add(comp);
        }
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.add(norte, BorderLayout.NORTH);
        panel.add(centro, BorderLayout.CENTER);
        return panel;
    }
 
    private static JTextArea nuevaAreaSalida() {
        JTextArea area = new JTextArea(5, 30);
        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        return area;
    }
 
    private static void mostrarEstado(JLabel etiqueta, String texto, boolean correcto) {
        etiqueta.setForeground(correcto ? COLOR_OK : COLOR_ERROR);
        etiqueta.setText(texto);
    }
 
    private static String descripcion(String simbolo) {
        return simbolo == null ? "null" : "\"" + simbolo + "\"";
    }

    private static String capturarSalida(Runnable accion) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (PrintStream temporal = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(temporal);
            accion.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
 
    private void refrescarTabla() {
        modeloTabla.setRowCount(0);
        for (Map.Entry<String, Integer> entrada : tabla.getSimbolos().entrySet()) {
            String simbolo = entrada.getKey().equals(" ") ? "(espacio)" : entrada.getKey();
            modeloTabla.addRow(new Object[]{simbolo, entrada.getValue()});
        }
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignorada) {
            }
            new Interfaz().setVisible(true);
        });
    }
}