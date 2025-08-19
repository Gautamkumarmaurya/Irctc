package com.subString.irctc.Entity;

 import jakarta.persistence.*;
 import lombok.*;

 import java.util.List;

@Entity
@Table(name = "train")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Train {

    @Id
    private Long id;

    private String number;

    private String name;

    private Integer totalDistance;

    @ManyToOne
    @JoinColumn(name = "source_station_id")
    private Station sourceStation;

    @ManyToOne
    @JoinColumn(name = "destination_station_id")
    private Station destinationStation;

    @OneToMany(mappedBy = "train")
    private List<TrainRoute> routes; // iska matlab hai train kaun kaun se jagah par rukegi

    @OneToMany(mappedBy = "train")
    private List<TrainSchedule> schedules;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private TrainImage trainImage;

}

