package com.subString.irctc.Repository;

import com.subString.irctc.Entity.TrainImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainImageRepository extends JpaRepository<TrainImage, Long> {
}
