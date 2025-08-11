package com.subString.irctc.Service;

import com.subString.irctc.Entity.ImageMetaData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

      ImageMetaData upload (MultipartFile file) throws IOException;
}
