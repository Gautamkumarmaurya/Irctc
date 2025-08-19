package com.subString.irctc.DTO;

import com.subString.irctc.Entity.Station;
import com.subString.irctc.Entity.TrainImage;
import com.subString.irctc.Entity.TrainRoute;
import com.subString.irctc.Entity.TrainSchedule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TrainDTO {

    private Long id;

    @NotEmpty(message = "Train number is required !!")
    @Size(min = 3 ,max = 10 ,message = "Invalid length of train no ")
    @Pattern(regexp = "^\\d+$" ,message = "Invalid no. Train no contains only numbers")
    private String number;

    @Pattern(regexp = "^[A-Za-z ]+$" , message = "Invalid Name . Train Name contain only letter Alphabet and Space are Allowed")
    private String name;

//    @Min(value = 1, message = "Coches must be at least 1")
//    @Max(value = 30, message = "Coches must not exceed 30")
//    @ValidCoach
//    private int coches;

    private Integer totalDistance;


    private Station sourceStation;


    private Station destinationStation;


    private List<TrainRoute> routes;


    private List<TrainSchedule> schedules;


    private TrainImage trainImage;

}
