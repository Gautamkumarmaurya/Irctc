package com.subString.irctc.Service;

import com.subString.irctc.Entity.Train;
import com.subString.irctc.Exception.ResponseNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {


    List<Train> trainList = new ArrayList<>();

    public TrainService() {
        trainList.add(new Train("15025","Lucknow Express",5));
        trainList.add(new Train("15010","Mumbai Express",8));
    }

    // Add train
    public Train add(Train train) {
        trainList.add(train);
        return train;
    }

    // get All Train
    public List<Train> all() {
        return trainList;
    }

    // get Single Train
    public Train get(String trainNo) {
        // used to Simply Java 8 concept in Stream Api
        Train train1 = trainList.stream().filter(train -> train.getTrainNo().equals(trainNo))
                .findFirst()
                .orElseThrow(() -> new ResponseNotFoundException("Train Not Found in Database"));
        return train1;
    }

    // Delete Single train
    public void delete(String trainNo) {
            trainList.removeIf(train -> train.getTrainNo().equals(trainNo));
    }
}
