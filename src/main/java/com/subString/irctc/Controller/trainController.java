package com.subString.irctc.Controller;

import com.subString.irctc.DTO.errorResponse;
import com.subString.irctc.Entity.ImageMetaData;
import com.subString.irctc.Entity.Train;
import com.subString.irctc.Service.FileUploadService;
 import com.subString.irctc.Service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController   // Controller + ResponseBody
@RequestMapping("/trains")
public class trainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private FileUploadService fileUploadService;

    // Photo and video upload and other file
    @PostMapping("/photo")
    private ImageMetaData trainPhotoUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);

        ImageMetaData imageMetaData = fileUploadService.upload(file);

        // save the data in database logic

        return imageMetaData;


    }

    // get All train
     @RequestMapping("/all")
    public List<Train> listTrain(){
        System.out.println("Get all trains method called");
        return this.trainService.all();
    }

    // Search Single train
    @RequestMapping("/{trainNo}")
    public Train getSingleTrain(@PathVariable("trainNo") String trainNo){
        System.out.println("Get single train method called");
       return this.trainService.get(trainNo);
    }

    // Delete Train
    @DeleteMapping("/{trainNo}")
    public void deleteTrain(@PathVariable("trainNo") String trainNo){
        System.out.println("Delete train method called");
        this.trainService.delete(trainNo);
    }

    // Add All Train Data
   @PostMapping
   public ResponseEntity<Train>  add(@Valid @RequestBody Train train){
         System.out.println("Add train method called");
         return new ResponseEntity<>(trainService.add(train), HttpStatus.CREATED);
   }


//   // Error Handler
//    @ExceptionHandler(NoSuchElementException.class)
//    public errorResponse NoSuchElementException(NoSuchElementException exception){
//         errorResponse errorResponse = new errorResponse("Train Not Found !! "+exception.getMessage(),"404",false);
//         return errorResponse;
//    }


}
