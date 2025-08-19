package com.subString.irctc.Controller;

import com.subString.irctc.DTO.PagedResponse;
import com.subString.irctc.DTO.TrainDTO;
import com.subString.irctc.DTO.TrainImageDataWithResource;
import com.subString.irctc.DTO.errorResponse;
import com.subString.irctc.Entity.TrainImage;
import com.subString.irctc.Service.TrainImageService;
import com.subString.irctc.Service.trainService1;
import org.springframework.core.io.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController   // Controller + ResponseBody
@RequestMapping("/trains")
public class trainController {

     private final trainService1 trainService1;
     private final TrainImageService trainImageService;

     // Constructor Dependency Injection [ @Autowired ]
    public trainController(trainService1 trainService1, TrainImageService trainImageService) {
        this.trainService1 = trainService1;
        this.trainImageService = trainImageService;
    }

    // get All train
     @RequestMapping("/all")
    public PagedResponse<TrainDTO> listTrain(
            @RequestParam(value = "page" , defaultValue = "0") int page,
            @RequestParam(value = "size" , defaultValue = "10") int size,
            @RequestParam(value = "sortBy" ,defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir" , defaultValue = "asc") String sortDir
      ){
        System.out.println("Get all trains method called");
        return this.trainService1.all(page, size, sortBy, sortDir);
    }

    // Search Single train
    @RequestMapping("/{trainNo}")
    public ResponseEntity<TrainDTO> getSingleTrain(@PathVariable("trainNo") String trainNo){
        System.out.println("Get single train method called");
        return new ResponseEntity<>(this.trainService1.get(trainNo), HttpStatus.OK);
     }

    // Delete Train
    @DeleteMapping("/{trainNo}")
    public void deleteTrain(@PathVariable("trainNo") String trainNo){
        System.out.println("Delete train method called");
        this.trainService1.delete(trainNo);
    }

    // Add All Train Data
   @PostMapping
   public ResponseEntity<TrainDTO>  add(@Valid @RequestBody TrainDTO trainDTO){
         System.out.println("Add train method called");
         return new ResponseEntity<>(trainService1.add(trainDTO), HttpStatus.CREATED);
   }

    // Upload Image API
    @PostMapping("/{trainNo}/upload")
    public ResponseEntity<?> trainImage(@PathVariable String trainNo,
                                        @RequestParam("image") MultipartFile image)throws IOException {
        String contentType = image.getContentType();
        System.out.printf("contentType: %s\n", contentType);
        assert contentType != null;
        if (contentType.toLowerCase().startsWith("image")){
            return new ResponseEntity<>(trainImageService.uploadImage(image,trainNo), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new errorResponse("Image not Uploaded ", "403", false), HttpStatus.BAD_REQUEST);
    }

    // Served Image in the Database
    @GetMapping("/{trainNo}/image")
    public ResponseEntity<Resource> ServeTrainImage(@PathVariable("trainNo") String trainNo) throws MalformedURLException {
        TrainImageDataWithResource trainImageDataWithResource = trainImageService.loadImageByTrainNO(trainNo);
        TrainImage trainImage = trainImageDataWithResource.trainImage();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(trainImage.getFileType()))
                .body(trainImageDataWithResource.resource());

    }
}
