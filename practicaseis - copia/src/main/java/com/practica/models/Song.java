package com.practica.models;

public class Song {
    private String title;
    private String artist;
    private String duration;
    private String filePath;

    public Song(String title, String artist, String duration, String filePath) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.filePath = filePath;
    }

    // Getters y setters
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getDuration() { return duration; }
    public String getFilePath() { return filePath; }
}
