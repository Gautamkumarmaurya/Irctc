package com.subString.irctc.DTO;

import com.subString.irctc.Entity.TrainImage;

import java.time.LocalDateTime;

public record TrainImageResponse(
        Long id,
        String fileName,
        String fileType,
        String url,
        long size,
        LocalDateTime uploadTime
){
   public static TrainImageResponse from(TrainImage trainImage , String Baseurl , String trainNo) {
       return new TrainImageResponse(
               trainImage.getId(),
               trainImage.getFileName(),
               trainImage.getFileType(),
               Baseurl+ "/trains/"+ trainNo +"/image",
               trainImage.getSize(),
               trainImage.getUploadTime()
       );
   }
}
