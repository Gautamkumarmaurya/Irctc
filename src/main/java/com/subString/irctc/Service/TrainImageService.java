package com.subString.irctc.Service;

import com.subString.irctc.Config.AppConstant;
import com.subString.irctc.DTO.TrainImageDataWithResource;
import com.subString.irctc.DTO.TrainImageResponse;
import com.subString.irctc.Entity.Train;
import com.subString.irctc.Entity.TrainImage;
import com.subString.irctc.Exception.ResponseNotFoundException;
import com.subString.irctc.Repository.TrainImageRepository;
import com.subString.irctc.Repository.TrainRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class TrainImageService {

    private final TrainImageRepository trainImageRepository;
    private final TrainRepository trainRepository;

    public TrainImageService(TrainImageRepository trainImageRepository, TrainRepository trainRepository) {
        this.trainImageRepository = trainImageRepository;
        this.trainRepository = trainRepository;
    }

    @Value("${train.image.folder.path}")
    private String folderPath;

    // Upload image in Database
    public TrainImageResponse uploadImage(MultipartFile file , String trainNo)throws IOException {

        Train train = trainRepository.findById(trainNo).orElseThrow(() -> new ResponseNotFoundException("Train Not Found"));

        // checking and creating folder
        if (!Files.exists(Path.of(folderPath))){
            System.out.println("Creating folder ");
            Files.createDirectories(Paths.get(folderPath));
        }

        String fullFilePath = folderPath + UUID.randomUUID()+"_"+ file.getOriginalFilename();

        Files.copy(file.getInputStream(), Path.of(fullFilePath) , StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File uploaded successfully");

        TrainImage trainImage = new TrainImage();
        trainImage.setFileName(fullFilePath);
        trainImage.setFileType(file.getContentType());
        trainImage.setSize(file.getSize());

        trainImage.setTrain1(train);
        train.setTrainImage(trainImage);
        Train savedTrain = trainRepository.save(train);


        return TrainImageResponse.from(savedTrain.getTrainImage(), AppConstant.Baseurl,trainNo);
    }

    // Load Image by Train No
    public TrainImageDataWithResource loadImageByTrainNO(String trainNo) throws MalformedURLException {

        // get the train using train no
        Train train = trainRepository.findById(trainNo).orElseThrow(() -> new ResponseNotFoundException("Train Not Found"));
        TrainImage trainImage = train.getTrainImage();
        if (trainImage == null) {
            throw new ResponseNotFoundException("Train Image Not Found");
        }

        Path path = Paths.get(trainImage.getFileName());
        if (!Files.exists(path)){
            throw new ResponseNotFoundException("Train Image Not Found");
        }

        Resource urlResource = new UrlResource(path.toUri());
        TrainImageDataWithResource trainImageDataWithResource = new TrainImageDataWithResource(trainImage, urlResource);

        return trainImageDataWithResource;

    }
}
