package com.practica.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SongTablePanel extends JPanel {

    public SongTablePanel() {
        setLayout(new BorderLayout());

        // Crear la tabla
        String[] columnNames = {"Título", "Artista", "Duración"};
        Object[][] data = {
            {"Canción 1", "Artista 1", "3:45"},
            {"Canción 2", "Artista 2", "4:20"},
            {"Canción 3", "Artista 3", "2:50"}
        };
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }
}
