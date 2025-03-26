package com.practica.utils;

import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class AudioPlayer {
    private Player player;
    private Thread playThread;

    public void play(String filePath) {
        stop(); // Detener cualquier reproducción anterior
        playThread = new Thread(() -> {
            try {
                FileInputStream fis = new FileInputStream(filePath);
                player = new Player(fis);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        playThread.start(); // Iniciar el hilo
    }

    public void stop() {
        try {
            if (player != null) {
                player.close(); // Cierra el reproductor
            }
            if (playThread != null && playThread.isAlive()) {
                playThread.interrupt(); // Interrumpe el hilo
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
