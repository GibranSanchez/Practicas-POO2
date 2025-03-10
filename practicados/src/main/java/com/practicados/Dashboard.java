package com.practicados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;

import com.formdev.flatlaf.FlatDarculaLaf;

public class Dashboard extends javax.swing.JFrame {

    //Jpanels
    private JPanel Background;
    private JPanel PanelDeOpciones;
    private JPanel PanelDeOpcionAgregar;
    private JPanel PanelDeOpcionImprimir;

    //Jbuttons panel de opciones
    private JButton BotonOpcionAgregar;
    private JButton BotonOpcionImprimir;

    // Componentes de PanelDeOpcionAgregar
    private JTextField CampoTextoMatricula;
    private JButton BotonAgregarMatricula;
    private JTable TablaDeMatriculas;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    private JButton BotonEliminarMatricula;
    private JButton BotonEditarMatricula;

    //componentes de PanelDeOpcionImprimir
    private JButton BotonImprimirMatriculasPDF;

    // Acceso al archivo
    private AccesoArchivo accesoArchivo;
    private final String NombreArchivo = "baseDeDatos.txt";




    public Dashboard() {
        initComponents();
        inicializarArchivo();
        mostrarMatriculas(modeloTabla);
        detectorHabilitarOpciones();
    }

    private void inicializarArchivo() {
        accesoArchivo = new AccesoArchivo(NombreArchivo);
        try {
            if (!accesoArchivo.existeArchivo()) {
                accesoArchivo.crearArchivo();
                JOptionPane.showMessageDialog(this, "Archivo base de datos creado: " + NombreArchivo);
            } else {
                JOptionPane.showMessageDialog(this, "Archivo base de datos abierto: " + NombreArchivo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al inicializar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void initComponents() {
        //Jpanels
        Background = new JPanel();
        PanelDeOpciones = new JPanel();
        PanelDeOpcionAgregar = new JPanel();
        PanelDeOpcionImprimir = new JPanel();

        //Jbuttons panel de opciones
        BotonOpcionAgregar = new JButton("Agregar");
        BotonOpcionImprimir = new JButton("Imprimir");

        //anadir los paneles a la ventana
        add(Background);
        Background.add(PanelDeOpciones);
        Background.add(PanelDeOpcionAgregar);
        Background.add(PanelDeOpcionImprimir);

        // Añadir botones a PanelDeOpciones
        PanelDeOpciones.add(BotonOpcionAgregar);
        PanelDeOpciones.add(BotonOpcionImprimir);

        //anadir botones a PanelDeOpcionImprimir
        BotonImprimirMatriculasPDF = new JButton("Imprimir Matriculas en PDF");
        PanelDeOpcionImprimir.add(BotonImprimirMatriculasPDF);
        BotonImprimirMatriculasPDF.setBounds(50, 50, 350, 40);
        BotonImprimirMatriculasPDF.addActionListener(evt -> BotonImprimirMatriculasPDFActionPerformed(evt));


        // Componentes de PanelDeOpcionAgregar
        CampoTextoMatricula = new JTextField("Ingrese la matricula a registrar: ");
        BotonAgregarMatricula = new JButton("Agregar Matricula");
        TablaDeMatriculas = new JTable();
        modeloTabla = new DefaultTableModel(new Object[]{"Matriculas"}, 0);
        TablaDeMatriculas.setModel(modeloTabla);
        scrollPane = new JScrollPane(TablaDeMatriculas);
        BotonEliminarMatricula = new JButton("Eliminar Matricula");
        BotonEditarMatricula = new JButton("Editar Matricula");

        // Añadir componentes a PanelDeOpcionAgregar
        PanelDeOpcionAgregar.add(CampoTextoMatricula);
        PanelDeOpcionAgregar.add(BotonAgregarMatricula);
        PanelDeOpcionAgregar.add(scrollPane);
        PanelDeOpcionAgregar.add(BotonEliminarMatricula);
        PanelDeOpcionAgregar.add(BotonEditarMatricula);

        // Establecer tamaño y posición de los componentes de PanelDeOpcionAgregar
        CampoTextoMatricula.setBounds(50, 50, 350, 40);
        BotonAgregarMatricula.setBounds(50, 100, 350, 40);
        scrollPane.setBounds(50, 150, 350, 200);
        BotonEliminarMatricula.setBounds(50, 360, 150, 40);
        BotonEditarMatricula.setBounds(250, 360, 150, 40);


        //desactivar layout de los paneles
        Background.setLayout(null);
        PanelDeOpciones.setLayout(null);
        PanelDeOpcionAgregar.setLayout(null);
        PanelDeOpcionImprimir.setLayout(null);

        // Establecer tamaño y posición de los botones de PanelDeOpciones
        BotonOpcionAgregar.setBounds(50, 50, 150, 40);
        BotonOpcionImprimir.setBounds(50, 100, 150, 40);

        //seleccionar tamano, posicion y color de los paneles
        Background.setBackground(new Color(81, 81, 81));
        PanelDeOpciones.setBackground(new Color(85, 85, 85));
        PanelDeOpciones.setBounds(0, 0,250 , 500);
        PanelDeOpcionAgregar.setBounds(250, 0, 450, 500);
        PanelDeOpcionAgregar.setBackground(new Color(81, 81, 81));
        PanelDeOpcionImprimir.setBounds(250, 0, 450, 500);
        PanelDeOpcionImprimir.setBackground(new Color(81, 81, 81));

        // Añadir FocusListener al campo de texto de matricula
        CampoTextoMatricula.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                CampoTextoMatricula.setText("");
            }
        });

        // Añadir ActionListener a los botones de PanelDeOpcionAgregar
        BotonOpcionAgregar.addActionListener(evt -> BotonAgregarActionPerformed(evt));
        BotonOpcionImprimir.addActionListener(evt -> BotonImprimirActionPerformed(evt));


        // Añadir ActionListener a los botones de PanelDeOpcionAgregar
        BotonAgregarMatricula.addActionListener(evt -> BotonAgregarMatriculaActionPerformed(evt));
        BotonEliminarMatricula.addActionListener(evt -> BotonEliminarMatriculaActionPerformed(evt));
        BotonEditarMatricula.addActionListener(evt -> BotonEditarMatriculaActionPerformed(evt));



        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Practica1");
        setResizable(false); // Bloquear la opción de maximizar la ventana

    }

    private void BotonAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        PanelDeOpcionAgregar.setVisible(true);
        PanelDeOpcionImprimir.setVisible(false);
    }

    private void BotonImprimirActionPerformed(java.awt.event.ActionEvent evt) {
        PanelDeOpcionAgregar.setVisible(false);
        PanelDeOpcionImprimir.setVisible(true);
    }

    private void BotonAgregarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {
        agregarMatricula();
    }

    private void BotonEliminarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {
        eliminarMatricula();
    }

    private void BotonEditarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {
        editarMatricula();
    }

    private void BotonImprimirMatriculasPDFActionPerformed(java.awt.event.ActionEvent evt) {
        convertirMatriculasAPDF();
    }

    private void agregarMatricula() {
        String matricula = CampoTextoMatricula.getText();
        if (matricula.isEmpty() || matricula.equals("Ingrese la matricula a registrar: ")) {
            JOptionPane.showMessageDialog(this, "Ingrese una matricula", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (matriculaExiste(matricula)) {
            JOptionPane.showMessageDialog(this, "La matricula ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            CampoTextoMatricula.setText("Ingrese la matricula a registrar: ");
            return;
        }

        try {
            escribirMatricula(matricula);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar la matricula: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        mostrarMatriculas(modeloTabla);
        detectorHabilitarOpciones();


        CampoTextoMatricula.setText("Ingrese la matricula a registrar: ");
        JOptionPane.showMessageDialog(this, "Matricula agregada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean matriculaExiste(String matricula) {
        try (BufferedReader reader = new BufferedReader(new FileReader(NombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.equals(matricula)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void escribirMatricula(String matricula) throws IOException {
        try (FileWriter writer = new FileWriter(NombreArchivo, true)) {
            writer.write(matricula + "\n");
        }
    }

    private void mostrarMatriculas(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(NombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                modeloTabla.addRow(new Object[]{linea});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void detectorHabilitarOpciones() {
        if (modeloTabla.getRowCount() > 0) {
            BotonEliminarMatricula.setEnabled(true);
            BotonEditarMatricula.setEnabled(true);
            BotonOpcionImprimir.setEnabled(true);
        } else {
            BotonEliminarMatricula.setEnabled(false);
            BotonEditarMatricula.setEnabled(false);
            BotonOpcionImprimir.setEnabled(false);
        }
    }

    private void eliminarMatricula(){
        int filaSeleccionada = TablaDeMatriculas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una matricula", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String matricula = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        try {
            eliminarMatriculaArchivo(matricula);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la matricula: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        mostrarMatriculas(modeloTabla);
        detectorHabilitarOpciones();
        JOptionPane.showMessageDialog(this, "Matricula eliminada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarMatriculaArchivo(String matricula) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(NombreArchivo));
            FileWriter writer = new FileWriter("temp.txt")) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.equals(matricula)) {
                    writer.write(linea + "\n");
                }
            }
        }

        try (FileWriter writer = new FileWriter(NombreArchivo);
            BufferedReader reader = new BufferedReader(new FileReader("temp.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                writer.write(linea + "\n");
            }
        }
    }

    private void editarMatricula() {
        int filaSeleccionada = TablaDeMatriculas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una matricula", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String matricula = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        String nuevaMatricula = JOptionPane.showInputDialog(this, "Ingrese la nueva matricula", matricula);
        if (nuevaMatricula == null) {
            return;
        }

        if (nuevaMatricula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una matricula", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (matriculaExiste(nuevaMatricula)) {
            JOptionPane.showMessageDialog(this, "La matricula ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            editarMatriculaArchivo(matricula, nuevaMatricula);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al editar la matricula: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        mostrarMatriculas(modeloTabla);
        detectorHabilitarOpciones();
        JOptionPane.showMessageDialog(this, "Matricula editada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarMatriculaArchivo(String matricula, String nuevaMatricula) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(NombreArchivo));
            FileWriter writer = new FileWriter("temp.txt")) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.equals(matricula)) {
                    writer.write(nuevaMatricula + "\n");
                } else {
                    writer.write(linea + "\n");
                }
            }
        }

        try (FileWriter writer = new FileWriter(NombreArchivo);
            BufferedReader reader = new BufferedReader(new FileReader("temp.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                writer.write(linea + "\n");
            }
        }
    }

    private void convertirMatriculasAPDF() {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("matriculas.pdf"));
        document.open();
        try (BufferedReader reader = new BufferedReader(new FileReader(NombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                document.add(new Paragraph(linea));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        document.close();
        JOptionPane.showMessageDialog(this, "PDF creado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

        // Abrir el PDF automáticamente
        File pdfFile = new File("matriculas.pdf");
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                JOptionPane.showMessageDialog(this, "La funcionalidad de abrir archivos no está soportada en este sistema.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El archivo PDF no se encontró.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (DocumentException | IOException e) {
        JOptionPane.showMessageDialog(this, "Error al crear el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public static void main(String[] args) {
        FlatDarculaLaf.setup();

        Dashboard dashboard = new Dashboard();
        dashboard.setSize(700, 500);
        dashboard.setLocationRelativeTo(null);
        dashboard.setVisible(true);
    }
}
