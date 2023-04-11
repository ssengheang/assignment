package com.example.groupassessment.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadFile {
    private final Path root = Paths.get("uploads");
    private MultipartFile file;
    private UUID uuid;
//    private String path = "http://localhost:8080/borrower-images/load/";
    private String file_name;
    private String path;

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public UploadFile(MultipartFile file, String path){
        this.path = path;
        this.file = file;
    }

    public String uploadFile(){
        this.init();
        String file_path;
        uuid  = UUID.randomUUID();
        file_name = uuid + "-" + file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), root.resolve(file_name));
            file_path = this.path + file_name;
        }catch (Exception e){throw new RuntimeException(e.getMessage());}

        return file_path;
    }

}
