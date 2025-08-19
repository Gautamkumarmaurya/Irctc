package com.subString.irctc.DTO;

import com.subString.irctc.Entity.TrainImage;
import org.springframework.core.io.Resource;

public record TrainImageDataWithResource(TrainImage  trainImage , Resource resource) {
}
