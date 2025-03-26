package com.practica.panels;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JLabel timeLabel;
    private boolean running;
    private boolean paused;
    private Thread timerThread;
    private int elapsedSeconds; // Tiempo transcurrido en segundos

    public ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.DARK_GRAY);

        // Botones de control
        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");
        JButton stopButton = new JButton("Stop");

        // Etiqueta para mostrar el tiempo transcurrido
        timeLabel = new JLabel("00:00");
        timeLabel.setForeground(Color.WHITE);

        // Agregar componentes al panel
        add(playButton);
        add(pauseButton);
        add(stopButton);
        add(timeLabel);

        // Acción del botón Play
        playButton.addActionListener(e -> {
            if (running && paused) {
                resumeTimer(); // Reanudar el temporizador si está pausado
            } else if (!running) {
                startTimer(); // Iniciar un nuevo temporizador si no está corriendo
            }
        });

        // Acción del botón Pause
        pauseButton.addActionListener(e -> pauseTimer());

        // Acción del botón Stop
        stopButton.addActionListener(e -> stopTimer());
    }

    private void startTimer() {
        running = true;
        paused = false;
        timerThread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(1000); // Esperar 1 segundo
                    if (!paused) {
                        elapsedSeconds++;
                        int minutes = elapsedSeconds / 60;
                        int remainingSeconds = elapsedSeconds % 60;
                        SwingUtilities.invokeLater(() -> timeLabel.setText(String.format("%02d:%02d", minutes, remainingSeconds)));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        timerThread.start();
    }

    private void pauseTimer() {
        if (running) {
            paused = true; // Pausar el temporizador
        }
    }

    private void resumeTimer() {
        paused = false; // Reanudar el temporizador
    }

    private void stopTimer() {
        running = false;
        paused = false;
        elapsedSeconds = 0; // Reiniciar el tiempo transcurrido
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt(); // Interrumpir el hilo si está activo
        }
        SwingUtilities.invokeLater(() -> timeLabel.setText("00:00")); // Reiniciar el temporizador a 00:00
    }
}
