package com.subString.irctc.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "train_seats")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
       // it like Dibba-Coach
public class TrainSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "train_schedule_id")
    private TrainSchedule trainSchedule;



    @Enumerated(EnumType.STRING)
    private CoachType coachType;  // ENUM :-> AC , SLEEPER , GENERAL

    private Integer totalSeats;

    private Integer availableSeats;

    private BigDecimal price;

}
