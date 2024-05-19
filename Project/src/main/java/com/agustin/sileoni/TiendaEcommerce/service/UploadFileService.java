package com.agustin.sileoni.TiendaEcommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
    private String folderImage = "images//";

    public String saveImage( MultipartFile file) throws IOException{
        if ( !file.isEmpty() ){
            byte[] bytes_imagen = file.getBytes();
            Path pathImage = Paths.get(folderImage + file.getOriginalFilename());
            Files.write(pathImage, bytes_imagen);
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }

    public void delete(String nameImage){
        String pathFolder = "images//";
        File file = new File(pathFolder + nameImage);
        file.delete();
    }
}
