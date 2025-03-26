package com.practica.views;

import com.formdev.flatlaf.FlatDarkLaf;
import com.practica.panels.AlbumPanel;
import com.practica.panels.ControlPanel;
import com.practica.panels.SongTablePanel;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {
        setTitle("Music Player");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Agregar los paneles
        add(new SongTablePanel(), BorderLayout.CENTER); // Tabla de canciones
        add(new AlbumPanel(), BorderLayout.WEST);       // Imagen del álbum
        add(new ControlPanel(), BorderLayout.SOUTH);    // Controles

        setVisible(true);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new Dashboard();
    }
}