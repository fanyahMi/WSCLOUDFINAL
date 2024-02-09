package com.spring.models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import java.util.UUID;

public class Photo {
    private String data;
    private String contentType;

    public Photo() {

    }

    public Photo(String data, String contentType) {
        this.data = data;
        this.contentType = contentType;
    }

    public File toFile() throws FileNotFoundException, IOException {
        // Décoder la chaîne Base64 en un tableau de bytes
        byte[] decodedBytes = Base64.getDecoder().decode(this.data);

        // Créer un nouveau fichier
        File file = new File(this.generateRandomFileName());

        // Écrire le tableau de bytes dans le fichier
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        }

        return file;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String generateRandomFileName() {
        String fileName = UUID.randomUUID().toString().concat("." + this.contentType.split("/")[1]);

        return fileName;
    }

}
