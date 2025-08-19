package com.subString.irctc.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "train_seats")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_schedule_id")
    private TrainSchedule trainSchedule;

    @Enumerated(EnumType.STRING)
    private CoachType coachType;

    private Integer totalSeats;

    private Integer availableSeats;

    private BigDecimal price;

}
