package com.subString.irctc.Service;

import com.subString.irctc.DTO.PagedResponse;
import com.subString.irctc.DTO.TrainDTO;
import com.subString.irctc.Entity.Train;
import com.subString.irctc.Exception.ResponseNotFoundException;
import com.subString.irctc.Repository.TrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {


    private TrainRepository trainRepository;


    private ModelMapper modelMapper;

    // it is automatically Autowired with constructor to dependency injection


    public TrainService(TrainRepository trainRepository, ModelMapper modelMapper) {
        this.trainRepository = trainRepository;
        this.modelMapper = modelMapper;
    }


    // Add train
    public TrainDTO add(TrainDTO trainDTO) {
     // Covert the DTO to entity
        // it is used to mannully
//        Train train = new Train();
//        train.setName(trainDTO.getName());
//        train.setName(trainDTO.getName());
//        train.setRouteName(trainDTO.getRouteName());
//        Train saved = trainRepository.save(train);
//
//        TrainDTO trainDTO1 = new TrainDTO();
//        trainDTO1.setTrainNo(saved.getTrainNo());
//        trainDTO1.setName(trainDTO.getName());
//        trainDTO1.setRouteName(trainDTO.getRouteName());
              // DTO to entity
        Train train = modelMapper.map(trainDTO, Train.class);
        Train savedTrain = trainRepository.save(train);

        // Entity → DTO
        return modelMapper.map(savedTrain, TrainDTO.class);
    }

    // get All Train
    public PagedResponse<TrainDTO> all(int page, int size , String sortBy , String sortOrder) {

        // Pagination implementation
        Sort sort = sortBy.trim().equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);


        Page<Train> trainPage = trainRepository.findAll(pageable); //  Correct source of data

        Page<TrainDTO> trainDTOPage = trainPage.map(train -> modelMapper.map(train, TrainDTO.class));//  modelMapper injected hoga

        return PagedResponse.fromPage(trainDTOPage);
    }


    // get Single Train
    public TrainDTO get(String trainNo) {
        Train trainNotFound = trainRepository.findById(trainNo).orElseThrow(() -> new ResponseNotFoundException("Train Not Found"));
        TrainDTO trainDTO = modelMapper.map(trainNotFound, TrainDTO.class);

        return trainDTO;
    }

    // Delete Single train
    public void delete(String trainNo) {

        Train trainNotFound = trainRepository.findById(trainNo).orElseThrow(() -> new ResponseNotFoundException("Train Not Found"));
        trainRepository.delete(trainNotFound);
    }
}
