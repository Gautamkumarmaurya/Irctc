package com.subString.irctc.Entity;

import com.subString.irctc.Annotations.ValidCoach;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

public class Train {


    @NotEmpty(message = "Train number is required !!")
    @Size(min = 3 ,max = 10 ,message = "Invalid length of train no ")
    @Pattern(regexp = "^\\d+$" ,message = "Invalid no. Train no contains only numbers")
    @Id
    private String trainNo;

    @Pattern(regexp = "^[A-Za-z ]+$" , message = "Invalid Name . Train Name contain only letter Alphabet and Space are Allowed")
    private String name;

    @Min(value = 1, message = "Coches must be at least 1")
    @Max(value = 30, message = "Coches must not exceed 30")
    @ValidCoach
    private int coches;


    Train() {}

    public Train(String trainNo, String name, int coches) {
        this.trainNo = trainNo;
        this.name = name;
        this.coches = coches;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoches() {
        return coches;
    }

    public void setCoches(int coches) {
        this.coches = coches;
    }
}
