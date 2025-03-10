package com.practicados;

import java.io.File;
import java.io.IOException;

public class AccesoArchivo {
    private File archivo;

    public AccesoArchivo(String nombreArchivo) {
        this.archivo = new File(nombreArchivo);
    }

    public void crearArchivo() throws IOException {
        if (archivo.createNewFile()) {
            System.out.println("Archivo creado: " + archivo.getName());
        } else {
            System.out.println("El archivo ya existe.");
        }
    }

    public boolean existeArchivo() {
        return archivo.exists();
    }

    
}
