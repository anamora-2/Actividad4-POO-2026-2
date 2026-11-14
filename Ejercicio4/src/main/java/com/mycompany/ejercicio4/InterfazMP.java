/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio4;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
/**
 *
 * @author anamo
 */
public class InterfazMP extends JFrame {
    private static final Color ROJO = new Color(0xB00020);
    private static final Color VERDE = new Color(0x1B7F3B);
 
    private EquipoMaratonProgramacion equipo;
    private final JTextField txtEquipo = new JTextField(14);
    private final JTextField txtUniversidad = new JTextField(14);
    private final JTextField txtLenguaje = new JTextField(14);
    private final JTextField txtNombre = new JTextField(14);
    private final JTextField txtApellidos = new JTextField(14);
    private final JButton btnCrear = new JButton("Crear equipo");
    private final JButton btnAñadir = new JButton("Añadir programador");
    private final JButton btnReiniciar = new JButton("Reiniciar");
    private final JLabel lblEstadoEquipo = new JLabel(" ");
    private final JTextArea areaEquipo = new JTextArea(10, 30);

    private final JPasswordField txtPass1 = new JPasswordField(14);
    private final JPasswordField txtPass2 = new JPasswordField(14);
    private final JCheckBox chkMostrar = new JCheckBox("Mostrar contraseñas");
    private final JButton btnValidar = new JButton("Validar contraseña");
    private final JLabel lblEstadoPass = new JLabel(" ");
    private final JTextArea areaPass = new JTextArea(10, 30);
 
    public InterfazMP() {
        super("Ejercicio 6.7. Validación de campos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        JPanel raíz = new JPanel(new GridLayout(1, 2, 10, 10));
        raíz.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        raíz.add(construirPanelEquipo());
        raíz.add(construirPanelContraseña());
        setContentPane(raíz);
 
        configurarEventos();
        estadoInicialEquipo();
        pack();
        setLocationRelativeTo(null);
    }
 
    private JPanel construirPanelEquipo() {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Equipo de maratón de programación",
            TitledBorder.LEFT, TitledBorder.TOP));
 
        JPanel form = new JPanel(new GridBagLayout());
        fila(form, 0, "Nombre del equipo:", txtEquipo);
        fila(form, 1, "Universidad:", txtUniversidad);
        fila(form, 2, "Lenguaje de programación:", txtLenguaje);
        fila(form, 3, null, btnCrear);
        fila(form, 4, "Nombre del integrante:", txtNombre);
        fila(form, 5, "Apellidos del integrante:", txtApellidos);
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        botones.add(btnAñadir);
        botones.add(btnReiniciar);
        fila(form, 6, null, botones);
        fila(form, 7, null, lblEstadoEquipo);
 
        areaEquipo.setEditable(false);
        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(areaEquipo), BorderLayout.CENTER);
        return p;
    }
 
    private JPanel construirPanelContraseña() {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Contraseña válida",
            TitledBorder.LEFT, TitledBorder.TOP));
 
        JPanel form = new JPanel(new GridBagLayout());
        JTextArea requisitos = new JTextArea(
            "Requisitos:\n"
          + " • Mínimo 8 caracteres\n"
          + " • Sin espacios en blanco\n"
          + " • Al menos una minúscula, una mayúscula,\n"
          + "   un número y un carácter especial\n"
          + " • Se ingresa dos veces para confirmar");
        requisitos.setEditable(false);
        requisitos.setOpaque(false);
        fila(form, 0, null, requisitos);
        fila(form, 1, "Contraseña:", txtPass1);
        fila(form, 2, "Confirmar contraseña:", txtPass2);
        fila(form, 3, null, chkMostrar);
        fila(form, 4, null, btnValidar);
        fila(form, 5, null, lblEstadoPass);
 
        areaPass.setEditable(false);
        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(areaPass), BorderLayout.CENTER);
        return p;
    }
 
    private void fila(JPanel panel, int y, String etiqueta, JComponent comp) {
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(3, 3, 3, 3);
        g.gridy = y;
        g.anchor = GridBagConstraints.WEST;
        if (etiqueta != null) {
            g.gridx = 0;
            panel.add(new JLabel(etiqueta), g);
            g.gridx = 1;
            g.fill = GridBagConstraints.HORIZONTAL;
            g.weightx = 1;
            panel.add(comp, g);
        } else {
            g.gridx = 0;
            g.gridwidth = 2;
            panel.add(comp, g);
        }
    }
 
    private void configurarEventos() {
        btnCrear.addActionListener(e -> crearEquipo());
        btnAñadir.addActionListener(e -> añadirProgramador());
        btnReiniciar.addActionListener(e -> {
            equipo = null;
            txtEquipo.setText("");
            txtUniversidad.setText("");
            txtLenguaje.setText("");
            txtNombre.setText("");
            txtApellidos.setText("");
            areaEquipo.setText("");
            estadoInicialEquipo();
        });
 
        btnValidar.addActionListener(e -> validarContraseña());
        txtPass2.addActionListener(e -> validarContraseña());
        chkMostrar.addActionListener(e -> {
            char eco = chkMostrar.isSelected() ? (char) 0 : '•';
            txtPass1.setEchoChar(eco);
            txtPass2.setEchoChar(eco);
        });
    }
 
    private void estadoInicialEquipo() {
        txtEquipo.setEnabled(true);
        txtUniversidad.setEnabled(true);
        txtLenguaje.setEnabled(true);
        btnCrear.setEnabled(true);
        txtNombre.setEnabled(false);
        txtApellidos.setEnabled(false);
        btnAñadir.setEnabled(false);
        mostrar(lblEstadoEquipo, "Primero cree el equipo.", Color.DARK_GRAY);
    }
 
    private static String obligatorio(String texto, String nombreCampo) throws Exception {
        String t = texto.trim();
        if (t.isEmpty()) {
            throw new Exception("El campo \"" + nombreCampo + "\" es obligatorio.");
        }
        return t;
    }
 
    private void crearEquipo() {
        try {
            String nombre = obligatorio(txtEquipo.getText(), "Nombre del equipo");
            String universidad = obligatorio(txtUniversidad.getText(), "Universidad");
            String lenguaje = obligatorio(txtLenguaje.getText(), "Lenguaje de programación");
 
            equipo = new EquipoMaratonProgramacion(nombre, universidad, lenguaje);
 
            txtEquipo.setEnabled(false);
            txtUniversidad.setEnabled(false);
            txtLenguaje.setEnabled(false);
            btnCrear.setEnabled(false);
            txtNombre.setEnabled(true);
            txtApellidos.setEnabled(true);
            btnAñadir.setEnabled(true);
 
            areaEquipo.setText("Equipo creado: " + nombre + " | " + universidad
                + " | " + lenguaje + "\n");
            mostrar(lblEstadoEquipo, "Equipo creado. Integrantes: 0/3 (mínimo 2).", VERDE);
            txtNombre.requestFocus();
        } catch (Exception ex) {
            mostrar(lblEstadoEquipo, "Excepción: " + ex.getMessage(), ROJO);
            areaEquipo.append("Excepción: " + ex.getMessage() + "\n");
        }
    }
 
    private void añadirProgramador() {
        try {
            String nombre = obligatorio(txtNombre.getText(), "Nombre del integrante");
            String apellidos = obligatorio(txtApellidos.getText(), "Apellidos del integrante");

            EquipoMaratonProgramacion.validarCampo(nombre);
            EquipoMaratonProgramacion.validarCampo(apellidos);
 
            Programador programador = new Programador(nombre, apellidos);
            equipo.añadir(programador);
 
            areaEquipo.append("Integrante " + equipo.tamañoEquipo + ": "
                + programador.nombre + " " + programador.apellidos + "\n");
            txtNombre.setText("");
            txtApellidos.setText("");
 
            String estado = "Integrantes: " + equipo.tamañoEquipo + "/3";
            if (equipo.estaLleno()) {
                estado += " — el equipo está completo.";
            } else if (equipo.tamañoEquipo < 2) {
                estado += " (mínimo 2).";
            } else {
                estado += " (ya cumple el mínimo).";
            }
            mostrar(lblEstadoEquipo, estado, VERDE);
            txtNombre.requestFocus();
        } catch (Exception ex) {
            mostrar(lblEstadoEquipo, "Excepción: " + ex.getMessage(), ROJO);
            areaEquipo.append("Excepción: " + ex.getMessage() + "\n");
        }
    }
 
    private void validarContraseña() {
        String c1 = new String(txtPass1.getPassword());
        String c2 = new String(txtPass2.getPassword());
        try {
            Contraseña contraseña = new Contraseña(c1, c2);
            mostrar(lblEstadoPass, "Contraseña válida.", VERDE);
            areaPass.append("Contraseña válida (" + contraseña.getValor().length()
                + " caracteres).\n");
        } catch (Exception ex) {
            mostrar(lblEstadoPass, "Excepción: " + ex.getMessage(), ROJO);
            areaPass.append("Excepción: " + ex.getMessage() + "\n");
        }
    }
 
    private static void mostrar(JLabel etiqueta, String texto, Color color) {
        etiqueta.setForeground(color);
        etiqueta.setText(texto);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazMP().setVisible(true));
    }
}