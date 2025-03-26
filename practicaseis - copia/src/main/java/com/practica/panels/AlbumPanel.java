package com.practica.panels;

import javax.swing.*;
import java.awt.*;

public class AlbumPanel extends JPanel {

    public AlbumPanel() {
        setPreferredSize(new Dimension(300, 300)); // Ancho fijo
        setBackground(Color.BLACK);

        // Imagen del álbum
        JLabel albumLabel = new JLabel(new ImageIcon("src/main/resources/Icon.png"));
        albumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(albumLabel);
    }
}
