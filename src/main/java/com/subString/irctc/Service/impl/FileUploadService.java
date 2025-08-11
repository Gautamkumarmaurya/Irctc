package com.subString.irctc.Service.impl;

import com.subString.irctc.Entity.ImageMetaData;
import com.subString.irctc.Helper.Helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FileUploadService implements com.subString.irctc.Service.FileUploadService {


    @Value("${file.upload.folder}")
     private String folder;
    @Override
    public ImageMetaData upload(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename: " + originalFilename);
        InputStream inputStream = file.getInputStream();
        String folder = "uploads/";

        //create folder if not exists
        if(!Files.exists(Paths.get(folder))){
            Files.createDirectories(Paths.get(folder));
        }

        String fileNameWithPath = Helper.getFilename(folder, file.getOriginalFilename());
        Files.copy(file.getInputStream(), Paths.get(fileNameWithPath), StandardCopyOption.REPLACE_EXISTING);

        ImageMetaData imageMetaData = new ImageMetaData();
        imageMetaData.getFileId(UUID.randomUUID().toString());
        imageMetaData.setFileName(fileNameWithPath);
        imageMetaData.setFileSize(file.getSize());
        imageMetaData.setContentType(file.getContentType());
        imageMetaData.setUploadedAt(LocalDateTime.now());

        return imageMetaData;
    }
}
