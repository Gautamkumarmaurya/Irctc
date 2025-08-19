package com.subString.irctc.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "train_schedule")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate runDate;
    private Integer availableSeats;



    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @OneToMany(mappedBy = "trainSchedule")
    private List<TrainSeat> trainSeats;

    @OneToMany(mappedBy = "trainSchedule")
    private List<Booking> bookings;


}
