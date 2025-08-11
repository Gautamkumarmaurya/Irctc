package com.subString.irctc.Service.impl;

import com.subString.irctc.Entity.ImageMetaData;
import com.subString.irctc.Service.FileUploadService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@Primary
public class CloudinaryFileUpload implements FileUploadService {
    @Override
    public ImageMetaData upload(MultipartFile file) throws IOException {

        // logic to upload file to cloudinary
        return null;
    }
}
